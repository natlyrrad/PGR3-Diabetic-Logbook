package SQLDatabase;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class pullAzure {
    public static String hostName = "pgr3.database.windows.net"; // update me
    public static String dbName = "pgr3DiabeticLogbook"; // update me
    public static String user = "logBookAdmin"; // update me
    public static String password = "fTG*U@QL"; // update me
    public static String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
            + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);

    public static boolean verifyEmail(String email) {
//        System.out.println(SQLDatabase.pullAzure.verifyEmail("dasdadasd"));
        Connection connection;
        boolean verifyStatus = false;

        try {
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String selectSql = String.format("SELECT * FROM userDetails WHERE userEmail='%s'", email);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                if (resultSet.next()) {
                    verifyStatus = true;
                }

                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verifyStatus;
    }
}
