package drawingUI.logPage;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class table extends JPanel {

    String name = "";
    String date = "";
    String time = "";
    String sugar = "";
    String exercise = "";
    String duration = "";
    String carbs = "";
    String meds = "";

    JTextField[] field = new JTextField[64];
    JLabel l = new JLabel("Date: ", JLabel.CENTER);
    JTextField ltext = new JTextField(5);
    JLabel t = new JLabel("Time: ", JLabel.CENTER);
    JTextField ttext = new JTextField(5);

    public table(JFrame parent)
    {
        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 0, 0, 0);

        String[] header = { "Name", "Date", "Time","Blood sugar","Exercise level","Duration","Carbohydrates","Medication"};
        JPanel p1 = new JPanel(new GridLayout(8, 8));
        p1.setPreferredSize(new Dimension(800, 300));

        for (int x = 0; x < field.length; x++) {
            field[x] = new JTextField(20);
            field[x].setEditable(false);
            field[x].setBackground(Color.white);
            if ((x % 8 == 0) && (x > 7))
            {
                field[x].setText("John Smith");
            }
            if (((x-1)%8 == 0) && (x > 7))
            {
                field[x].setText("04/12/2019");
            }
            if (((x-2)%8 == 0) && (x > 7))
            {
                field[x].setText("12:23");
            }
            if (((x-3)%8 == 0) && (x > 7))
            {
                field[x].setText("5.6MMol/L");
            }
            if (((x-4)%8 == 0) && (x > 7))
            {
                field[x].setText("1/day");
            }
            if (((x-5)%8 == 0) && (x > 7))
            {
                field[x].setText("20 mins");
            }
            if (((x-6)%8 == 0) && (x > 7))
            {
                field[x].setText("46g");
            }
            if (((x-7)%8 == 0) && (x > 7))
            {
                field[x].setText("Insulin");
            }
            if (x < 8) {
                field[x].setText(header[x]);
                field[x].setForeground(Color.red);
            }
            p1.add(field[x]);
        }

        JPanel p2 = new JPanel(new GridLayout(1, 3));
        JButton previous = new JButton("<< Previous");
        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

            }
        });
        p2.add(previous);

        // Reference 6 - https://stackoverflow.com/questions/16285019/loading-date-into-jtextfield
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        ltext.setText(dateFormat.format(date));

        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date time = new Date();
        ttext.setText(timeFormat.format(time));
        /* end of reference 6*/

        p2.add(l);
        p2.add(ltext);
        p2.add(t);
        p2.add(ttext);
        JButton next = new JButton("Next >>");
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

            }
        });
        p2.add(next);

        JPanel p3 = new JPanel(new GridLayout(1, 1));
        p3.setPreferredSize(new Dimension(800, 200));
        JTextArea textbox = new JTextArea("Additional comments: ",50, 60);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        textbox.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        p3.add(textbox);

        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(p1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(p3, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        newPanel.add(p2, constraints);

        add(newPanel);
    }
}
