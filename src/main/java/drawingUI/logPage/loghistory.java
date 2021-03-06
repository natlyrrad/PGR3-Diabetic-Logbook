package drawingUI.logPage;

import SQLDatabase.pullAzure;
import drawingUI.LoadingFrame;
import drawingUI.QuestPage.Questionnaire;
import drawingUI.Graph.PlotGraph;
import drawingUI.calendarPage.CalendarUIController;
import drawingUI.calendarPage.DatePicker;
import drawingUI.detailsPage.DetailsUIController;
import drawingUI.entryPage.EntryUIController;
import drawingUI.entryPage.FoodPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static drawingUI.emailPage.emailPanel.etext;

public class loghistory extends JPanel {

    static GraphicsConfiguration gc; // Class field containing config info

    FlowLayout flayout = new FlowLayout();

    //Declare loading frame
    LoadingFrame load = new LoadingFrame();

    // Declare the buttons included on the panel
    JButton btgraph = new JButton("Graph");
    JButton calendar = new JButton("Calendar");
    public JButton Quest = new JButton(" Questionnaire");
    JButton btedit = new JButton("Edit Details");


    ////////////PULL THIRD TABLE HERE
    String com = pullAzure.pullComments("15", "Date");
    //String com = "qscore;Commentsssssss;Exercise 1: 123,Exercise2: 1234";

    // Process com
    String[] c = com.split(";");


    public loghistory() {
        
        if (com != "") {
            System.out.println(Arrays.toString(c));
            Quest.setText("Questionnaire score: "+ c[0]);
            Quest.setEnabled(false);
            Quest.setToolTipText("Completed");
            System.out.println("in");

            //default questionnaire textbox + default
        }
        c[0] = "0";
        System.out.println("out");
        table t = new table(c);

        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 1, 10, 1);

        //https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/FlowLayoutDemoProject/src/layout/FlowLayoutDemo.java
        JPanel bp = new JPanel(new GridLayout(1, 4));
        bp.setLayout(flayout);

        calendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame cal_frame = new JFrame(gc); // Create a new JFrame
                cal_frame.setSize(600, 400);

                CalendarUIController uical = new CalendarUIController(cal_frame);

                cal_frame.setVisible(true);
                // This next line closes the program when the frame is closed
                cal_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                // declare JFrame currently open as "frame"
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setVisible(false); // set current open frame as invisible
                /* end of reference 2 */
            }
        });
        bp.add(calendar);

        /* Button Action: button will open the graph application frame */
        //A default graph of blood sugar level versus time until today is plotted
        btgraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.S");
                        Date start = null;
                        try {
                            start = df.parse("0000/00/00 00:00:00.0");//set the default start date as 0
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        Date d = new Date();
                        String dt = df.format(d) + " 23:59:59.9";//set the default end date as today
                        Date end = null;
                        try {
                            end = df.parse(dt);
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        PlotGraph chart = new PlotGraph("title", SQLDatabase.pullAzure.pullUserID(etext.getText()), start, end);
                        chart.pack();
                        chart.setVisible(true);


                        load.setVisible(false);

                        /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                        Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                        // declare JFrame currently open as "frame"
                        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                        frame.setVisible(false); // set current open frame as invisible
                        /* end of reference 2 */

                        latch.countDown();
                    }
                }).start();
            }
        });
        bp.add(btgraph);

        /* Button Action: the "edit details button will take the user back to the details page
         with the filled in text fields previously entered, and will set the calendar frame
         as invisible */
        btedit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                        // Reopen the Details page
                        JFrame details_frame = new JFrame(gc); // Create a new JFrame
                        details_frame.setSize(500, 450);

                        DetailsUIController uidetails = new DetailsUIController(details_frame, 1);

                        load.setVisible(false);

                        details_frame.setVisible(true);
                        // This next line closes the program when the frame is closed
                        details_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                        /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                        Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                        // declare JFrame currently open as "frame"
                        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                        frame.setVisible(false); // set current open frame as invisible
                        /* end of reference 2 */

                        latch.countDown();
                    }
                }).start();
            }
        });
        bp.add(btedit);

        /* Button Action: will open the Questionnaire window */
        constraints.gridx = 3;
        Quest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("View questionnaire");

                JFrame details_frame = new JFrame(gc); // Create a new JFrame
                details_frame.setSize(800, 900);

                drawingUI.QuestPage.Questionnaire q = new Questionnaire();
                q.setVisible(true);
                details_frame.add(q);
                details_frame.setVisible(true);
                // This next line closes the program when the frame is closed
                details_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                // declare JFrame currently open as "frame"
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setVisible(false); // set current open frame as invisible
                /* end of reference 2 */
            }

        });
        bp.add(Quest);

        /* Following code will place each component in a specific point on the grid */
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(bp, constraints);

        constraints.insets = new Insets(5, 10, 0, 10);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(t, constraints);

        add(newPanel);
    }
}
