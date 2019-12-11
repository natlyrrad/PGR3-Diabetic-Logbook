package drawingUI.logPage;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class table extends JPanel {

    JLabel l = new JLabel("Date: ");
    public static JTextField ltext = new JTextField(10);
    JLabel t = new JLabel("Time: ");
    public static JTextField ttext = new JTextField(10);

    int day = java.util.Calendar.getInstance().get(Calendar.DAY_OF_MONTH); // Get current Day
    int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH); // Get current Month
    int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);; // Get current Year

    public table()
    {
        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 0, 0, 0);

        JPanel p1 = new JPanel(new GridLayout(1, 4));
        p1.add(l);
        p1.add(ltext);
        p1.add(t);
        p1.add(ttext);

        JPanel p2 = new JPanel(new GridLayout(5, 1));
        p2.setPreferredSize(new Dimension(700, 600));

        String[] amounts = {"50", "10", "150", "54", "85", "65"};

        for (int i=0; i<5; i++)
        {
            miniTable mtable = new miniTable("12:25", "5.5", amounts);
            p2.add(mtable);
        }

        JPanel p3 = new JPanel(new GridLayout(1, 1));
        p3.setPreferredSize(new Dimension(700, 100));

        JTextArea textbox = new JTextArea("Additional comments: ",50, 60);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        textbox.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        p3.add(textbox);

        JPanel p4 = new JPanel(new GridLayout(1, 3));
        JButton previous = new JButton("<< Previous");
        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String str = ltext.getText();
                String[] a = str.split("/");
                String nday = a[0];
                day = Integer.valueOf(nday);
                day --;
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                        "dd/MM/yyyy");
                java.util.Calendar cal = java.util.Calendar.getInstance();
                cal.set(year, month, day);
                ltext.setText(sdf.format(cal.getTime()));
            }
        });
        p4.add(previous);

        // Reference 6 - https://stackoverflow.com/questions/16285019/loading-date-into-jtextfield
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        ltext.setText(dateFormat.format(date));

        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date time = new Date();
        ttext.setText(timeFormat.format(time));
        /* end of reference 6*/

        JButton today = new JButton("Today");
        today.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                ltext.setText(dateFormat.format(date));
            }
        });
        p4.add(today);

        JButton next = new JButton("Next >>");
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String str = ltext.getText();
                String[] a = str.split("/");
                String nday = a[0];
                day = Integer.valueOf(nday);
                day ++;
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                        "dd/MM/yyyy");
                java.util.Calendar cal = java.util.Calendar.getInstance();
                cal.set(year, month, day);
                ltext.setText(sdf.format(cal.getTime()));
            }
        });
        p4.add(next);

        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(p1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(p2, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(p3, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        newPanel.add(p4, constraints);

        add(newPanel);
    }
}
