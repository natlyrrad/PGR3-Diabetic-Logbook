package drawingUI.Graph;

import java.sql.*;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;



public class PlotGraph extends ApplicationFrame {
    public static String hostName = "pgr3.database.windows.net"; // update me
    public static String dbName = "pgr3DiabeticLogbook"; // update me
    public static String user = "logBookAdmin"; // update me
    public static String password = "fTG*U@QL"; // update me
    public static String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
            + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);

    public PlotGraph(String title,String userid) {
        super(title);
        JFreeChart lineChart = ChartFactory.createLineChart(
                "chartTitle",
                "Date and time","Blood Sugar Level",
                createDataset(userid),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }



    private DefaultCategoryDataset createDataset(String userid ) {
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
                dataset.addValue(bsl,"blood sugar level",dt);

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
