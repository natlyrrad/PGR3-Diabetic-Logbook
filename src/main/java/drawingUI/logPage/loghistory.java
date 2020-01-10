package drawingUI.logPage;

import drawingUI.QuestPage.Questionnaire;
import drawingUI.Graph.PlotGraph;
import drawingUI.calendarPage.CalendarUIController;
import drawingUI.detailsPage.DetailsUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import static drawingUI.emailPage.emailPanel.etext;

public class loghistory extends JPanel {

    static GraphicsConfiguration gc; // Class field containing config info

    FlowLayout flayout = new FlowLayout();

    //Declare loading frame
    JFrame load = new JFrame();

    // Declare the buttons included on the panel
    JButton btgraph = new JButton("Graph");
    JButton calendar = new JButton("Calendar");
    JButton Questionnaire = new JButton( " Questionnaire");
    JButton btedit = new JButton("Edit Details");

    table t = new table();

    public loghistory(){
        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 1, 10, 1);

        //https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/FlowLayoutDemoProject/src/layout/FlowLayoutDemo.java
        JPanel bp = new JPanel(new GridLayout(1, 4));
        bp.setLayout(flayout);

        calendar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JFrame cal_frame= new JFrame(gc); // Create a new JFrame
                cal_frame.setSize(600,400);

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
        btgraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* Reference - https://stackoverflow.com/questions/34906220/running-two-tasks-at-the-same-time-in-java */
                CountDownLatch latch = new CountDownLatch(2);
                new Thread(new Runnable() {
                    public void run() {
                        /* Reference loading frame - https://stackoverflow.com/questions/7634402/creating-a-nice-loading-animation */
                        ImageIcon loading = new ImageIcon("ajax-loader.gif");

                        JLabel loadlabel = new JLabel(" Connecting... ", loading, JLabel.CENTER);
                        loadlabel.setFont(new Font("Monospaced", Font.PLAIN, 18));

                        load.add(loadlabel);
                        load.getContentPane().setBackground( Color.white );

                        /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                        Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                        // declare JFrame currently open as "frame"
                        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                        frame.setVisible(false); // set current open frame as invisible
                        /* end of reference 2 */

                        load.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        load.setSize(400, 300);
                        load.setVisible(true);
                        latch.countDown();
                    }
                }).start();

                new Thread(new Runnable() {
                    public void run() {
                        PlotGraph chart = new PlotGraph("title", SQLDatabase.pullAzure.pullUserID(etext.getText()));
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
                        /* Reference loading frame - https://stackoverflow.com/questions/7634402/creating-a-nice-loading-animation */
                        ImageIcon loading = new ImageIcon("ajax-loader.gif");

                        JLabel loadlabel = new JLabel(" Connecting... ", loading, JLabel.CENTER);
                        loadlabel.setFont(new Font("Monospaced", Font.PLAIN, 18));

                        load.add(loadlabel);
                        load.getContentPane().setBackground( Color.white );

                        /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                        Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                        // declare JFrame currently open as "frame"
                        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                        frame.setVisible(false); // set current open frame as invisible
                        /* end of reference 2 */

                        load.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        load.setSize(400, 300);
                        load.setVisible(true);
                        latch.countDown();
                    }
                }).start();

                new Thread(new Runnable() {
                    public void run() {
                        // Reopen the Details page
                        JFrame details_frame= new JFrame(gc); // Create a new JFrame
                        details_frame.setSize(500,450);

                        DetailsUIController uidetails = new DetailsUIController(details_frame);

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
        Questionnaire.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("View questionnaire");

                JFrame details_frame= new JFrame(gc); // Create a new JFrame
                details_frame.setSize(800,900);

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
        bp.add(Questionnaire);

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
