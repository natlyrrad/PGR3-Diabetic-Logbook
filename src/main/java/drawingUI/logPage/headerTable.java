package drawingUI.logPage;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class headerTable extends JTable {
    String[] header = { "Time","Blood sugar(MMol/L)", "Food Type", "Amount(g)", "Medication"};
    Object data[][] = {{"Time","Blood sugar(MMol/L)", "Food Type", "Amount(g)", "Medication"}};

    public headerTable(){
        int h = header.length;

        DefaultTableModel model = new DefaultTableModel(data, header);

        setModel(model);

        TableColumn column = null;
        for (int i = 0; i < h; i++) {
            column = getColumnModel().getColumn(i);
            if (i == 2) {
                column.setPreferredWidth(150); //third column is bigger
            } else {
                column.setPreferredWidth(110);
            }
        }
    }
}
