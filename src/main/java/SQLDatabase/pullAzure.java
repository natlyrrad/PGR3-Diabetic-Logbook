package SQLDatabase;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.ArrayList;

public class pullAzure {
    public static String hostName = "pgr3.database.windows.net"; // update me
    public static String dbName = "pgr3DiabeticLogbook"; // update me
    public static String user = "logBookAdmin"; // update me
    public static String password = "fTG*U@QL"; // update me
    public static String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
            + "hostNameInCertificate=*.database.windows.net;loginTimeout=5;", hostName, dbName, user, password);

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

    public static String pullUserID(String email) {                                            //to fetch user id for pulling/ pushing
        Connection connection;
        String userID = "";

        try {
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String selectSql = String.format("SELECT * FROM userDetails WHERE userEmail='%s'", email);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                while (resultSet.next()) {

                    userID = resultSet.getString(1);
                }

                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(userID);
        return userID;
    }

    public static String pullDoctorEmail(String id) {                                            //to fetch user id for pulling/ pushing
        Connection connection;
        String doctorEmail = "";

        try {
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String selectSql = String.format("SELECT * FROM userDetails WHERE userID='%s'", id);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                while (resultSet.next()) {

                    doctorEmail = resultSet.getString(10);
                }

                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(doctorEmail);
        return doctorEmail;
    }

    public static String pullPersonalDetails(String userID) {                              //for users to edit personal details
//        SQLDatabase.pullAzure.pullEntryDetails("12");
        Connection connection;
        boolean verifyStatus = false;
        String res = "";

        try {
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String selectSql = String.format("SELECT * FROM entryDetails WHERE userID='%s'", userID);          //change destination

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                while (resultSet.next()){
                    for (int i = 1; i < 12; i++) {
                        res += resultSet.getString(i) + ";";
                    }
                    System.out.println(res);
                }

                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String pullPatientName(String id) {                                            //to fetch user id for pulling/ pushing
        Connection connection;
        String PatientfirstName = "";
        String PatientlastName = "";

        try {
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String selectSql = String.format("SELECT * FROM userDetails WHERE userID='%s'", id);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                while (resultSet.next()) {
                    PatientfirstName = resultSet.getString(2);
                    PatientlastName = resultSet.getString(3);
                }

                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String Name = PatientfirstName + " " + PatientlastName;
        System.out.println(Name);
        return Name;
    }

    public static String pullPatientPhone(String id) {                                            //to fetch user id for pulling/ pushing
        Connection connection;
        String patientphone = "";

        try {
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String selectSql = String.format("SELECT * FROM userDetails WHERE userID='%s'", id);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                while (resultSet.next()) {
                    patientphone = resultSet.getString(6);
                }

                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(patientphone);
        return patientphone;
    }


    public static String[] pullEntryDetails(String userID, String date) {                              //to display log history
        Connection connection;
        boolean verifyStatus = false;
        String res = "";
        ArrayList<String> entries = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String selectSql = String.format("SELECT * FROM entryDetails WHERE userID='%s' AND entryDateTime >= '%s' " +
                    "AND entryDateTime <= '%s 23:59:59.9'", userID, date, date);
            System.out.println(selectSql);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                while (resultSet.next()) {
                    for (int i = 2; i < 7; i++) {
                        res += resultSet.getString(i) + ";";
                    }
                    entries.add(res);
                    res = "";
                }

                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] array = entries.toArray(new String[entries.size()]);
        return array;
    }
}

