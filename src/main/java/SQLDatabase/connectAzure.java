package SQLDatabase;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class connectAzure {
    public static void connectAzure() {
        // Connect to database
        String hostName = "pgr3.database.windows.net"; // update me
        String dbName = "pgr3DiabeticLogbook"; // update me
        String user = "logBookAdmin"; // update me
        String password = "fTG*U@QL"; // update me
        String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
                + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
            String schema = connection.getSchema();
            System.out.println("Successful connection - Schema: " + schema);

            System.out.println("Query data example:");
            System.out.println("=========================================");

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * FROM Users ";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                // Print results from select statement
                System.out.println("Top 20 categories:");
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + " "
                            + resultSet.getString(2) + " " + resultSet.getString(3));
                }
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}