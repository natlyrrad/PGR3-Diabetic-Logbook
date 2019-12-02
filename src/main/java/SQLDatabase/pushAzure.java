package SQLDatabase;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;

public class pushAzure {
    public static String hostName = "pgr3.database.windows.net"; // update me
    public static String dbName = "pgr3DiabeticLogbook"; // update me
    public static String user = "logBookAdmin"; // update me
    public static String password = "fTG*U@QL"; // update me
    public static String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
            + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);

    public static void push(String query) {
        Connection connection;

        try {
            connection = DriverManager.getConnection(url);

            Statement statement = connection.createStatement();
            statement.execute(query);

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pushUserDetails() {
        String userDetails = "INSERT INTO userDetails (firstName, lastName) " +
                "VALUES ('Java', 'Test');";

        push(userDetails);
    }
}
