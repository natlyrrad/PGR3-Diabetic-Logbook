package drawingUI.logPage;

import drawingUI.entryPage.FoodPanel;

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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class table<first> extends JPanel {

    JLabel l = new JLabel("Date: ");
    public static JTextField ltext = new JTextField(10);
    JLabel t = new JLabel("Time: ");
    public static JTextField ttext = new JTextField(10);

    int day = java.util.Calendar.getInstance().get(Calendar.DAY_OF_MONTH); // Get current Day
    int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH); // Get current Month
    int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);; // Get current Year

    String[] header = { "Time","Blood sugar(MMol/L)", "Food Type", "Amount(g)"};

    //ArrayLists to collect data
    int counter = 0;
    ArrayList<String> exerciseList = new ArrayList<>();
    ArrayList<ExercisePanel> entryList = new ArrayList<>();

    JButton addExercise = new JButton("Add Exercise");

    public table()
    {
        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 0, 0, 0);

        //panel for date and time
        JPanel p1 = new JPanel(new GridLayout(1, 4));
        p1.add(l);
        p1.add(ltext);
        p1.add(t);
        p1.add(ttext);

        //panel for tables
        JPanel p2 = new JPanel(new GridLayout(5, 1));
        p2.setPreferredSize(new Dimension(700, 600));

        String[] amounts = {"50", "10", "150", "54", "85", "65"};

        for (int i=0; i<5; i++)
        {
            miniTable mtable = new miniTable("12:25", "5.5", amounts, "insulin");
            JScrollPane scrollPane = new JScrollPane(mtable);
            mtable.setFillsViewportHeight(true);
            p2.add(scrollPane);
        }

        //panel for exercise
        JPanel pEx = new JPanel(new GridLayout());

        JPanel exPanel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 1, 1, 1);          //From maria

        entryList.add(new ExercisePanel());

        c.gridx = 0;
        c.gridy = 0;
        exPanel.add(entryList.get(0), c);

        //add exercise button
        addExercise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                counter = counter + 1;
                entryList.add(counter, new ExercisePanel());

                //remove all then add new components
                exPanel.removeAll();
                for(int i=0; i<(counter+1); i++){
                    c.gridx = 0;
                    c.gridy = i+1;
                    exPanel.add(entryList.get(i), c);
                }

                //revalidate and display new fdPanel
                exPanel.revalidate();
                exPanel.repaint();
                exPanel.setVisible(true);
            }
        });
        pEx.add(exPanel, constraints);

        //panel for additional comments
        JPanel p3 = new JPanel(new GridLayout(1, 1));
        p3.setPreferredSize(new Dimension(700, 100));

        JTextArea textbox = new JTextArea("Additional comments: ",30, 60);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        textbox.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        p3.add(textbox);

        //panel for previous, today and next
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

        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 0;
        constraints.gridy = 3;
        newPanel.add(p4, constraints);

        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.WEST;
        newPanel.add(addExercise, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        newPanel.add(pEx, constraints);

        JScrollPane scrollPane = new JScrollPane(newPanel,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(750, 900));

        add(scrollPane);
    }

    String getExercise(){
        String listString = new String();
        for(int i=0; i<(counter+1); i++){
            exerciseList.add(i, entryList.get(i).dataEx());
            listString += (exerciseList.get(i) + ", ");
        }
        return listString;
    }
}
