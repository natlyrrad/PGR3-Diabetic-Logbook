package drawingUI.logPage;

import drawingUI.emailPage.emailPanel;
import drawingUI.entryPage.EntryUIController;

import javax.security.auth.Refreshable;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Ref;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import SQLDatabase.pullAzure;

public class table extends JPanel {

    JLabel l = new JLabel("Date: ");
    public static JTextField ltext = new JTextField(7);
    JLabel t = new JLabel("Time: ");
    public static JTextField ttext = new JTextField(7);
    JButton save = new JButton("Save");
    JButton delete = new JButton("Delete Recent");
    JButton newrow = new JButton("New");

    FlowLayout flayout = new FlowLayout();

    int day = java.util.Calendar.getInstance().get(Calendar.DAY_OF_MONTH); // Get current Day
    int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH); // Get current Month
    int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);; // Get current Year


    //ArrayLists to collect data
    int counter = 0;
    ArrayList<String> exerciseList = new ArrayList<>();
    ArrayList<ExerciseEntry> entryList = new ArrayList<>();

    JButton addExercise = new JButton("Add Exercise");

    //PULL DATA HERE//////////////////////////////////////////////////////////////////////////////////////////////////
    String id = emailPanel.userID();
    //String[] s = {"dt1;bsl1;Coke:23,Cheese:34,Chicken:8;med;13", "dt2;bsl1;Carrot cake:56,Coke:23,Cheese:34,Chicken:8;med;13", "dt3;bsl1;Chinese Tea:86,Carrot cake:56,Coke:23,Cheese:34,Chicken:8;med;13"};


    int counter2 = 0;
    int counter3 = 0;
    JPanel p1 = new JPanel();   //date time bla bla
    JPanel p2 = new JPanel();   //minitable

    public table()

    {

        // Reference 6 - https://stackoverflow.com/questions/16285019/loading-date-into-jtextfield
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        ltext.setText(dateFormat.format(date));

        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        ttext.setText(timeFormat.format(date));
        /* end of reference 6*/

        DateFormat dateFormat2 = new SimpleDateFormat("yyyy/mm/dd");

        String[] entry = pullAzure.pullEntryDetails(id, dateFormat2.format(date));

        JPanel newPanel = new JPanel(new GridBagLayout());

        //panel for date and time
        p1.add(l);
        p1.add(ltext);
        p1.add(t);
        p1.add(ttext);
        p1.add(delete);
        p1.add(newrow);

        //Panel 2 for table
        miniTable mtable = new miniTable(entry);
        mtable.setFillsViewportHeight(true);
        System.out.println(Arrays.toString(entry));
        if(Arrays.toString(entry) == "[]"){
            JLabel empty = new JLabel("No entries for today");
            System.out.println(empty);
            p2.add(empty);
            mtable.setFillsViewportHeight(false);
        }
        p2.add(mtable);


        //Panel 3 for additional comments
        JPanel p3 = new JPanel(new GridLayout(1, 1));
        p3.setPreferredSize(new Dimension(600, 80));

        JTextArea textbox = new JTextArea("Additional comments: (e.g. Special activities, stress level...)",20, 50);
        //// if comments arent empty, set comments
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        textbox.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        p3.add(textbox);


        //panel for exercise
        JPanel pEx = new JPanel(new GridLayout());

        JPanel exPanel = new JPanel(new GridBagLayout());
        entryList.add(new ExerciseEntry());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 1, 1, 1);          //From maria

        c.gridx = 0;
        c.gridy = 0;
        exPanel.add(entryList.get(0), c);

        //add exercise button
        addExercise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                counter = counter + 1;
                entryList.add(counter, new ExerciseEntry());

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
        pEx.add(exPanel);


        //Panel 4 for previous, today and next
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
                RefreshTable();
            }
        });
        p4.add(previous);

        JButton today = new JButton("Today");
        today.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                ltext.setText(dateFormat.format(date));
                RefreshTable();
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
                RefreshTable();
            }
        });
        p4.add(next);


        delete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
//                counter2 = counter2 + 1;
//                for(int i=0; i<(counter2+1); i++)
//                {
//                    int count = lognum - counter2;
//                    p2.removeAll();
//                    miniTable mtable = new miniTable(entry);
//                    JScrollPane scrollPane = new JScrollPane(mtable);
//                    mtable.setFillsViewportHeight(true);
//                    p2.add(scrollPane);
//                }
//                p2.revalidate();
//                p2.repaint();
            }
        });

        newrow.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                EntryUIController frame = new EntryUIController();
                frame.show();

                /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                // declare JFrame currently open as "frame"
                JFrame f = (JFrame) SwingUtilities.getRoot(component);
                f.setVisible(false); // set current open frame as invisible
                /* end of reference 2 */

            }
        });

        //////////////save questionaire score, additional comments and exercise entries
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = String.join(";", id, date.toString(), "Questionnaire score", textbox.getText(), getExercise());
                /////// push str
                System.out.println(str);
                System.out.println("Saved!");
            }
        });
        save.setToolTipText("Save Questionnaire score, comments and exercise");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 0, 5, 0);

        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(p2, constraints);

        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridy = 1;
        newPanel.add(p3, constraints);

        constraints.gridy = 2;
        newPanel.add(addExercise, constraints);

        constraints.gridy = 4;
        newPanel.add(pEx, constraints);

        // create scroll pane for
        JScrollPane scrollPane = new JScrollPane(newPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(620, 500));


        setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();

        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.gridy = 0;
        add(p1, grid);

        grid.gridy = 1;
        add(scrollPane, grid);

        grid.insets = new Insets(7, 0, 7, 0);
        grid.fill = GridBagConstraints.VERTICAL;
        grid.anchor = GridBagConstraints.EAST;
        grid.gridy = 2;
        add(save, grid);

        grid.anchor = GridBagConstraints.CENTER;
        grid.gridy = 3;
        add(p4, grid);
    }

    private void RefreshTable() {
        p2.removeAll();
        String[] ref = pullAzure.pullEntryDetails(id, ltext.getText());
        miniTable t = new miniTable(ref);
        p2.add(t);
        p2.revalidate();
        p2.repaint();
    }

    String getExercise(){
        String listString = new String();
        for(int i=0; i<(counter+1); i++){
            exerciseList.add(i, entryList.get(i).dataEx());
            listString += (exerciseList.get(i) + ", ");
            System.out.println(listString);
        }
        return listString;
    }


}
