package drawingUI.Graph;

import drawingUI.LoadingFrame;
import javaMailAPI.jakartaMailAPI;
import org.jfree.date.DateUtilities;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import drawingUI.entryPage.Entry;

import static SQLDatabase.pullAzure.pullDoctorEmail;
import static SQLDatabase.pushAzure.pushEntryDetails;
import static drawingUI.emailPage.emailPanel.etext;
import static drawingUI.logPage.table.ltext;

public class CompDates extends JPanel {
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.S");
    private JLabel labelstart = new JLabel("Start date:");
    private JLabel labelend = new JLabel("End date:");
    private JLabel dateformat = new JLabel("Please input date as yyyy/MM/dd");
    private static JTextField startdate = new JTextField(20);
    private static JTextField enddate = new JTextField(20);
    private JButton buttonenter = new JButton("Enter");

    //Declare loading frame
    LoadingFrame load = new LoadingFrame();

    static GraphicsConfiguration gc;

    public CompDates() {
        JPanel newPanel = new JPanel(new GridBagLayout());

        //when click enter button, a graph within the interval input will be plotted
        buttonenter.addActionListener(new ActionListener() {
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
                        //get the input start date and end date. The interval is inclusive.
                        String a = startdate.getText()+" 00:00:00.0";
                        String b = enddate.getText()+" 23:59:59.9";
                        
                        //parse the date input into date format. 
                        java.util.Date start = null;
                        try {
                            start = df.parse(a);
                        } catch (ParseException ex) {
                            System.out.println("Please enter dates in the correct format");
                        }
                        System.out.println(start);
                        java.util.Date end = null;
                        try {
                            end = df.parse(b);
                        } catch (ParseException ex) {
                            System.out.print("Please enter dates in the correct format");
                        }

                        //plot graph within the time interval
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
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(labelstart, constraints);

        constraints.gridx = 1;
        newPanel.add(startdate, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(labelend, constraints);

        constraints.gridx = 1;
        newPanel.add(enddate, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(dateformat, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonenter, constraints);

        add(newPanel);


    }
}





