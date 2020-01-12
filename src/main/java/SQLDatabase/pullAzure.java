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



    //////////////////////////////////////////////// FIRST TABLE //////////////////////////////////////////////////
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

    /////////////// Pull patient individual details
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

    public static String pullPatientFirstName(String id) {                                            //to fetch user id for pulling/ pushing
        Connection connection;
        String PatientfirstName = "";

        try {
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String selectSql = String.format("SELECT * FROM userDetails WHERE userID='%s'", id);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                while (resultSet.next()) {
                    PatientfirstName = resultSet.getString(2);
                }

                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String FirstName = PatientfirstName;
        System.out.println(FirstName);
        return FirstName;
    }

    public static String pullPatientLastName(String id) {                                            //to fetch user id for pulling/ pushing
        Connection connection;
        String PatientlastName = "";

        try {
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String selectSql = String.format("SELECT * FROM userDetails WHERE userID='%s'", id);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                while (resultSet.next()) {
                    PatientlastName = resultSet.getString(3);
                }

                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String LastName = PatientlastName;
        System.out.println(LastName);
        return LastName;
    }

    public static String pullPatientEmail(String id) {                                            //to fetch user id for pulling/ pushing
        Connection connection;
        String PatientEmail = "";
        try {
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String selectSql = String.format("SELECT * FROM userDetails WHERE userID='%s'", id);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                while (resultSet.next()) {
                    PatientEmail = resultSet.getString(4);
                }

                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String Email = PatientEmail;
        System.out.println(Email);
        return Email;
    }

    public static String pullPatientAddress(String id) {                                            //to fetch user id for pulling/ pushing
        Connection connection;
        String PatientAddress = "";

        try {
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String selectSql = String.format("SELECT * FROM userDetails WHERE userID='%s'", id);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                while (resultSet.next()) {
                    PatientAddress = resultSet.getString(5);
                }

                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String Address = PatientAddress;
        System.out.println(Address);
        return Address;
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

    ///////////// Pull diabetes individual details
    public static String pullDiabetesType(String id) {                                            //to fetch user id for pulling/ pushing
        Connection connection;
        String type = "";

        try {
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String selectSql = String.format("SELECT * FROM userDetails WHERE userID='%s'", id);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                while (resultSet.next()) {

                    type = resultSet.getString(7);
                }

                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(type);
        return type;
    }

    public static String pullDiabetesInsulin(String id) {                                            //to fetch user id for pulling/ pushing
        Connection connection;
        String insulin = "";

        try {
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String selectSql = String.format("SELECT * FROM userDetails WHERE userID='%s'", id);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                while (resultSet.next()) {

                    insulin = resultSet.getString(8);
                }

                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(insulin);
        return insulin;
    }

    public static String pullDiabetesAdmin(String id) {                                            //to fetch user id for pulling/ pushing
        Connection connection;
        String admin = "";

        try {
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String selectSql = String.format("SELECT * FROM userDetails WHERE userID='%s'", id);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

    //////////////////////////////////////////////// SECOND TABLE //////////////////////////////////////////////////
                while (resultSet.next()) {

                    admin = resultSet.getString(9);
                }

                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(admin);
        return admin;
    }

    ///////////// Pull doctor individual details
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

    public static String pullDoctorAddress(String id) {                                            //to fetch user id for pulling/ pushing
        Connection connection;
        String doctorAddress = "";

        try {
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String selectSql = String.format("SELECT * FROM userDetails WHERE userID='%s'", id);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                while (resultSet.next()) {

                    doctorAddress = resultSet.getString(11);
                }

                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(doctorAddress);
        return doctorAddress;
    }

    public static String pullDoctorPhone(String id) {                                            //to fetch user id for pulling/ pushing
        Connection connection;
        String doctorPhone = "";

        try {
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            String selectSql = String.format("SELECT * FROM userDetails WHERE userID='%s'", id);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                while (resultSet.next()) {

                    doctorPhone = resultSet.getString(12);
                }

                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(doctorPhone);
        return doctorPhone;
    }

    ///////////////////////////////////
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


    //////////////////////////////////////////////// THIRD TABLE //////////////////////////////////////////////////
    public static String pullComments(String userID, String date) {                              //to display log history
        Connection connection;
        boolean verifyStatus = false;
        String res = "";

        try {
            connection = DriverManager.getConnection(url);

            // Create and execute a SELECT SQL statement.
            // Select the most recent update
            // if no match, return null?????
            String selectSql = String.format("SELECT * FROM entryDetails WHERE userID='%s' AND entryDateTime >= '%s' " +
                    "AND entryDateTime <= '%s 23:59:59.9'", userID, date, date);
            System.out.println(selectSql);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                while (resultSet.next()) {
                    for (int i = 2; i < 7; i++) {
                        res += resultSet.getString(i) + ";";
                    }
                    res = "";
                }

                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


}

