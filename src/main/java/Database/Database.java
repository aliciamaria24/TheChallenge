package Database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Database {

    public static void main(String[] args) {
        Database pro = new Database();
        pro.connect();
        //pro.createConnection();
        createConnection();

    }

    public static void createConnection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Amit23@");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT luchtkwaliteit_binnen_CO2 FROM luchtkwaliteit_binnen ORDER BY luchtkwaliteit_binnen_id DESC LIMIT 1 ");
            while (rs.next()) {
                int gemiddelde = rs.getInt("luchtkwaliteit_binnen_CO2");
                System.out.println(gemiddelde);

            }
            System.out.println("Database Connection Succes");
        } catch (
                SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Amit23@");
            Statement stmt = con.createStatement();
            ResultSet luchtbuiten = stmt.executeQuery("SELECT luchtkwaliteit_buiten_fijnstof FROM luchtkwaliteit_buiten ORDER BY luchtkwaliteit_buiten_id DESC LIMIT 1 ");
            while (luchtbuiten.next()) {
                int gemiddelde = luchtbuiten.getInt("luchtkwaliteit_buiten_fijnstof");
                System.out.println(gemiddelde);
            }
        } catch (
                SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

        private Connection connect () {
            Connection conn = null;
            String driver = "com.mysql.cj.jdbc.Driver";
            // MySQL connection string, pas zonodig het pad aan:
            String connection = "jdbc:mysql://localhost:3306/project";
            String user = "root";
            String password = "Amit23@";
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(connection, user, password);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            return conn;
        }

}


//

//
//    public static void main(String[] args) {
//        Database pro = new Database();
//        pro.createConnection();
//    }
//    void createConnection() {
//        try {
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3360/project", "root", "Amit23@");
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT luchtkwaliteit_binnen_CO2 FROM luchtkwaliteit_binnen");
//            while (rs.next()) {
//                int gemiddelde = rs.getInt("luchtkwaliteit_binnen_CO2");
//            }
//            System.out.println("Database Connection Succes");
//        } catch (SQLException ex) {
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//}

