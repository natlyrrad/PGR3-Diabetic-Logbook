import java.sql.*;

public class connectDB {
    private static String url= "jdbc:postgresql://localhost:5432/postgres";//load the database
    private static String username = "postgres";//user name for the database
    private static String password = "<your password for the database>";//password for the database
    private static Connection conn;
    public static Connection connect() {
        try {
            Class.forName("org.postgresql.Driver"); //load the JDBC driver
            System.out.println("ok");
            conn = DriverManager.getConnection(url, username, password);
            {

                if (conn != null) {
                    System.out.println("Connected to the database!");
                } else {
                    System.out.println("Failed to make connection!");
                }//get connection to database
            }
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Failed to create the database connection");
        }
        return conn;

    }
}
