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
            + "hostNameInCertificate=*.database.windows.net;loginTimeout=5;", hostName, dbName, user, password);

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

    // "tan;darryl;0123;sadsadnsa;d"

    public static void pushUserDetails(String userDetails) {
        String[] userArray = userDetails.split(";");
        String queryStatement = String.format("INSERT INTO userDetails " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');", userArray[0],
                userArray[1], userArray[2], userArray[3], userArray[4], userArray[5], userArray[6],
                userArray[7], userArray[8], userArray[9], userArray[10]);

        push(queryStatement);
    }

    public static void pushEntryDetails(String entryDetails) {
        String[] entryArray = entryDetails.split(";", -2);
        String queryStatement = String.format("INSERT INTO entryDetails " +
                        "VALUES ('%s', '%s', '%s', '%s', '%s', '%s');", entryArray[0],
                entryArray[1], entryArray[2], entryArray[3], entryArray[4], entryArray[5]);

        push(queryStatement);
    }

    public static void pushCommentDetails(String entryDetails) {
        String[] entryArray = entryDetails.split(";", -2);
        String queryStatement = String.format("INSERT INTO entryDetails " +
                        "VALUES ('%s', '%s', '%s', '%s', '%s');", entryArray[0],
                entryArray[1], entryArray[2], entryArray[3], entryArray[4]);

        push(queryStatement);
    }

    public static void deleteRecent() {
        String queryStatement = "DELETE FROM entryDetails WHERE entryDateTime=(SELECT MAX(entryDateTime) FROM entryDetails)";

        push(queryStatement);
    }
}

//SQLDatabase.pushAzure.pushUserDetails("here;now;@mail.com;SSS777;123;Type 1;insuline type;pen;@doctor;FFF" +
//        ";333");

//            SQLDatabase.pushAzure.pushEntryDetails("12;2019-12-11 00:12:12;11.1;meat;12.3;Type 2;44.4;exercise;comment");