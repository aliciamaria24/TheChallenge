import java.sql.*;
 
public class connection {
 
    Connection con = null;
 
    public static Connection connectDB()
 
    {
 
        try {
            // Importing and registering drivers
            Class.forName("com.mysql.jdbc.Driver");
 
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hotelman",
                "root", "1234");
            // here,root is the username and 1234 is the
            // password,you can set your own username and
            // password.
            return con;
        }
        catch (SQLException e) {
 
            System.out.println(e);
        }
    }
}