package drawingUI.Graph;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import drawingUI.LoadingFrame;
import drawingUI.logPage.createAndShowLog;
import javaMailAPI.jakartaMailAPI;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;

import static SQLDatabase.pullAzure.pullDoctorEmail;
import static SQLDatabase.pushAzure.pushEntryDetails;
import static drawingUI.logPage.table.RefreshTable;
import static drawingUI.logPage.table.ltext;


public class PlotGraph extends ApplicationFrame {
    public static String hostName = "pgr3.database.windows.net"; // update me
    public static String dbName = "pgr3DiabeticLogbook"; // update me
    public static String user = "logBookAdmin"; // update me
    public static String password = "fTG*U@QL"; // update me
    public static String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
            + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);

    // Declares a new Jbutton
    JButton back = new JButton(" << Back ");
    JButton newGraph= new JButton("Graph for Specific Time Interval");

    //Declare loading frame
    LoadingFrame load = new LoadingFrame();

    static GraphicsConfiguration gc; // Class field containing config info

    public PlotGraph(String title,String user,Date start,Date end) {
        super(title);
        JFreeChart lineChart = ChartFactory.createLineChart(
                "chartTitle",
                "Date and time", "Blood Sugar Level",
                createDataset(user,start,end),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));

        JPanel newPanel = new JPanel(new GridBagLayout()); // create a new panel with GridBagLayout manager

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(chartPanel);

        back.addActionListener(new ActionListener() {
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
                        //create new frame to loghistory
                        createAndShowLog uilog = new createAndShowLog();
                        RefreshTable();

                        load.setVisible(false);

                        /* Reference 2 - taken from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
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


        newGraph.addActionListener(new ActionListener(){
            @Override

            public void actionPerformed(ActionEvent e){
                JFrame dateframe=new JFrame(gc);
                dateframe.setSize(500,300);
                CompDates p=new CompDates();
                p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
                dateframe.getContentPane().add(p);
                dateframe.setVisible(true);
                dateframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                // declare JFrame currently open as "frame"
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setVisible(false); // set current open frame as invisible
                /* end of reference 2 */
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.WEST;
        newPanel.add(back, constraints);

        constraints.gridx=1;
        newPanel.add(newGraph, constraints);

        add(newPanel);
    }

    private DefaultCategoryDataset createDataset(String userid,Date start, Date end) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        try
        {
            // create our mysql database connection
            Connection conn= DriverManager.getConnection(url);

            // our SQL SELECT query.
            // if you only need a few columns, specify them by name instead of using "*"
            String query = String.format("SELECT * FROM entryDetails WHERE userID='%s'", userid);

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next())
            {
                String datetime= rs.getString(2);
                int bsl = rs.getInt(3);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                java.util.Date dt = null;
                dt = format.parse(datetime);
                if(!dt.before(start) && !dt.after(end)){
                    dataset.addValue(bsl,"blood sugar level",dt);
                }


                // print the results
                System.out.format("%s,  %s\n", datetime, bsl);


            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return dataset;
    }
}