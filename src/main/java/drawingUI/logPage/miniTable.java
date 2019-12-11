package drawingUI.logPage;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class miniTable extends JTable {
    String[] header = { "Time","Blood sugar(MMol/L)", "Food Type", "Amount(g)"};
    Object[][] data  = new Object[1][header.length];

    public miniTable(String time, String sugar, String[] amount)
    {
        /* Reference - http://blog.marcnuri.com/displaying-a-jtable-inside-another-jtable-jtable-cellrenderer/*/
        data[0][0] = time;
        data[0][1] = sugar;

        String[] types = {"Complex Carbs (fibre)", "Simple Carbs (sugar)",
                "Fats", "Protein", "Fruit & Veg", "Dairy"};
        data[0][2] = types;
        data[0][3] = amount;

        /* First we create the main model
	    We overide the AbstractTableModel necessary methods*/
        AbstractTableModel modelo = new AbstractTableModel() {
            public String getColumnName(int col) {
                return header[col];
            }
            public Class getColumnClass(int col) {
                if(getRowCount() <1) {
                    return null;
                }
                return data[0][col].getClass();
            }
            public int getRowCount() {
                return data.length;
            }
            public int getColumnCount() {
                return header.length;
            }
            public Object getValueAt(int row, int col) {
                return data[row][col];
            }
            public boolean isCellEditable(int row, int col){
                return false;
            }
            public void setValueAt(Object value, int row, int col) {
                data[row][col] = value; fireTableCellUpdated(row, col);
            }
        };

        setModel(modelo);

        TableColumn column = null;
        for (int i = 0; i < 4; i++) {
            column = getColumnModel().getColumn(i);
            if (i == 2) {
                column.setPreferredWidth(200); //third column is bigger
            } else {
                column.setPreferredWidth(100);
            }
        }

        /* We create a cell Renderer to display the data of the multivalue fields*/
        TableCellRenderer jTableCellRenderer = new TableCellRenderer() {
            /* These are necessary variables to store the row's height */
            private int minHeight = -1;
            private int currHeight = -1;
            /* Magic Happens */
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                /* If what we're displaying isn't an array of values we return the normal renderer*/
                if(!value.getClass().isArray()){
                    return table.getDefaultRenderer(value.getClass())
                            .getTableCellRendererComponent(table, value, isSelected, hasFocus,row, column);
                } else{
                    final Object[] passed = (Object[])value;
                    /* We calculate the row's height to display data
                     *  This is not complete and has some bugs that
                     *  will be analyzed in further articles */
                    if(minHeight == -1){
                        minHeight = table.getRowHeight();
                    }
                    if(currHeight != passed.length*minHeight){
                        currHeight = passed.length * minHeight;
                        table.setRowHeight(row,currHeight);
                    }
                    /* We create the table that will hold the multivalue
                     *  fields and that will be embedded in the main table */
                    return new JTable(new AbstractTableModel() {
                        public int getColumnCount() {
                            return 1;
                        }
                        public int getRowCount() {
                            return passed.length;
                        }
                        public Object getValueAt(int rowIndex, int columnIndex) {
                            return passed[rowIndex];
                        }
                        public boolean isCellEditable(int row, int col){
                            return true;
                        }
                    });
                }
            }
        }; /* Finally we apply the new cellRenderer to the whole table */
        TableColumnModel tcm = getColumnModel();
        for(int it = 0; it < tcm.getColumnCount(); it++){
            tcm.getColumn(it).setCellRenderer(jTableCellRenderer);
        }
        /* end of reference */
    }

}
