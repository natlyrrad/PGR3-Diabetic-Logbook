package drawingUI.logPage;

import drawingUI.LoadingFrame;
import drawingUI.QuestPage.Questionnaire;
import drawingUI.emailPage.emailPanel;
import drawingUI.detailsPage.DetailsUIController;
import drawingUI.entryPage.EntryUIController;
import drawingUI.entryPage.FoodPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
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
import java.util.concurrent.CountDownLatch;

import static SQLDatabase.pushAzure.deleteRecent;
import static SQLDatabase.pushAzure.pushCommentDetails;
import static drawingUI.QuestPage.Questionnaire.score;

public class table extends JPanel {

    static JLabel l = new JLabel("Date: ");
    public static JTextField ltext = new JTextField(7);
    static JLabel t = new JLabel("Time: ");
    public static JTextField ttext = new JTextField(7);
    static JButton save = new JButton("Save");
    static JButton delete = new JButton("Delete Recent");
    static JButton newrow = new JButton("New");

    public static JTextArea textbox = new JTextArea("Additional comments: (e.g. Special activities, stress level...)",20, 50);

    static JButton previous = new JButton("<< Previous");
    static JButton today = new JButton("Today");
    static JButton next = new JButton("Next >>");


    //Declare loading frame
    LoadingFrame load = new LoadingFrame();

    int day = java.util.Calendar.getInstance().get(Calendar.DAY_OF_MONTH); // Get current Day
    int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH); // Get current Month
    int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);; // Get current Year

    //ArrayLists to collect data
    static int counter = 0;
    static ArrayList<String> exerciseList = new ArrayList<>();
    static ArrayList<ExerciseEntry> entryList = new ArrayList<>();

    JButton addExercise = new JButton("Add Exercise");

    //PULL ID HERE//////////////////////////////////////////////////////////////////////////////////////////////////
    static String[] entry;

    //Panels for layout
    static JPanel newPanel = new JPanel(new GridBagLayout());
    static JPanel p1 = new JPanel();                                   //date time bla bla
    static JPanel p2 = new JPanel();                                   //minitable
    static JPanel p3 = new JPanel(new GridLayout(1, 1));   //comments
    static JPanel p4 = new JPanel(new GridLayout(1, 3));   //prev today next
    static JPanel ph = new JPanel();                                   // header table

    public table(String[] str, String date) {
        //Set current date and time
        String[] a = date.split("/");
        System.out.println(a);
        String aDate = String.join("/", a[2] ,a[1] ,a[0]);
        ltext.setText(aDate);
        Date time = new Date();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        ttext.setText(timeFormat.format(time));

        //PULL ENTRY HERE//////////////////////////////////////////////////////////////////////////////////////////////////
        entry = pullAzure.pullEntryDetails(loghistory.id, date);
        //String[] entry = {"dt1;bsl1;Coke:23,Cheese:34,Chicken:8;med;13", "dt2;bsl1;Carrot cake:56,Coke:23,Cheese:34,Chicken:8;med;13", "dt3;bsl1;Chinese Tea:86,Carrot cake:56,Coke:23,Cheese:34,Chicken:8;med;13"};

        //Panel 1 for date and time
        p1.add(l);
        p1.add(ltext);
        p1.add(t);
        p1.add(ttext);
        p1.add(delete);
        p1.add(newrow);

        //add header table for minitable
        headerTable htable = new headerTable();
        ph.add(htable);

        //Panel 2 for table
        miniTable mtable = new miniTable(entry);
        mtable.setFillsViewportHeight(true);
        System.out.println(Arrays.toString(entry));

        if(Arrays.toString(entry) == "[]"){
            JLabel empty = new JLabel("No entries for today");
            System.out.println(empty);
            p2.add(empty);
            mtable.setFillsViewportHeight(false);
            ph.setVisible(false);
        }
        p2.add(mtable);

        //Panel 3 for additional comments
        p3.setPreferredSize(new Dimension(600, 80));

        //// if comments arent empty, set comments
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        textbox.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        p3.add(textbox);


        //Panel for exercise
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

        today.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                if(ltext.getText() != dateFormat.format(date)){
                    ltext.setText(dateFormat.format(date));
                    RefreshTable();
                }
            }

        });
        p4.add(today);

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
                deleteRecent();
                RefreshTable();
            }
        });

        newrow.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                /* Reference - https://stackoverflow.com/questions/34906220/running-two-tasks-at-the-same-time-in-java */
                CountDownLatch latch = new CountDownLatch(2);
                new Thread(new Runnable() {
                    public void run() {
                        load.createframe();

                        /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                        Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                        // declare JFrame currently open as "frame"
                        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                        frame.setVisible(false); // set current open frame as invisible
                        /* end of reference 2 */

                        load.showframe();
                        latch.countDown();
                    }
                }).start();

                new Thread(new Runnable() {
                    public void run() {
                        EntryUIController frame = new EntryUIController();
                        frame.show();

                        load.setVisible(false);

                        /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                        Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                        // declare JFrame currently open as "frame"
                        JFrame f = (JFrame) SwingUtilities.getRoot(component);
                        f.setVisible(false); // set current open frame as invisible
                        /* end of reference 2 */

                        latch.countDown();
                    }
                }).start();
            }
        });

        //////////////save questionaire score, additional comments and exercise entries
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = String.join(";", loghistory.id, date, score.getText(), textbox.getText(), getExercise(), " ");
                /////// push str
                pushCommentDetails(str);
                System.out.println(str);
                System.out.println("Saved!");
            }
        });
        save.setToolTipText("Save Questionnaire score, comments and exercise");

        //if data is pulled, replace default UI set up with data
        if(str.length != 1){
            textbox.setText(str[1]);

            //Display exercise
            String exercise = str[2];
            exPanel.removeAll();
            String[] temp = exercise.split(",");
            String[][] eList = new String[temp.length][2];

            for(int i = 0; i < temp.length; i++){
                eList[i] = temp[i].split(":");
            }

            for(int i=0; i<(temp.length); i++){
                entryList.add(new ExerciseEntry());
                entryList.get(i).setEx(eList[i][0], eList[i][1]);
                c.gridx = 0;
                c.gridy = i+1;
                exPanel.add(entryList.get(i), c);
            }

            //revalidate and display new fdPanel
            exPanel.revalidate();
            exPanel.repaint();
            exPanel.setVisible(true);
        }

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 0, 0, 0);

        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(ph, constraints);

        constraints.gridy = 1;
        newPanel.add(p2, constraints);

        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridy = 2;
        newPanel.add(p3, constraints);

        constraints.gridy = 3;
        newPanel.add(addExercise, constraints);

        constraints.gridy = 5;
        newPanel.add(pEx, constraints);

        // create scroll pane for data
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

    public static void RefreshTable() {
        p1.removeAll();
        ph.removeAll();
        p2.removeAll();
        p4.removeAll();

        String[] d = ltext.getText().split("/");
        System.out.println(Arrays.toString(d));
        String a = String.join("/", d[2], d[1], d[0]);
        String[] ref = pullAzure.pullEntryDetails(loghistory.id, a);

        miniTable m = new miniTable(ref);
        headerTable h = new headerTable();

        p1.add(l);
        p1.add(ltext);
        p1.add(t);
        p1.add(ttext);
        p1.add(delete);
        p1.add(newrow);

        ph.add(h);
        ph.setVisible(true);
        p2.add(m);

        p4.add(previous);
        p4.add(today);
        p4.add(next);

        if(Arrays.toString(ref) == "[]"){
            JLabel empty = new JLabel("No entries for today");
            p2.removeAll();
            p2.add(empty);
            m.setFillsViewportHeight(false);
            ph.setVisible(false);
        }

        p1.revalidate();
        p1.repaint();
        ph.revalidate();
        ph.repaint();
        p2.revalidate();
        p2.repaint();
        p4.revalidate();
        p4.repaint();
    }


    static String getExercise(){
        String listString = new String();
        for(int i=0; i<(counter+1); i++){
            exerciseList.add(i, entryList.get(i).dataEx());
            listString += (exerciseList.get(i) + ", ");
            System.out.println(listString);
        }
        return listString;
    }

    void exerciseLog(String exercise){
        // set up exercise log
//        entryList.add(counter, new ExerciseEntry());
//
//        //remove all then add new components

    }

    public static void enterQscore(String sc, String date){
        String str = String.join(";", loghistory.id, date, sc, textbox.getText(), getExercise(), " ");
        /////// push str
        pushCommentDetails(str);
    }
}
