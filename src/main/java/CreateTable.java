import java.sql.*;

public class CreateTable{
    public static void create(String requirement, Connection conn)  {
            try {
               PreparedStatement st = conn.prepareStatement(requirement);
               st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
        }

    }
}
