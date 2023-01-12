package Database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Database {

    public static void main(String[] args) {
        Database pro = new Database();
        pro.connect();
        //pro.createConnection();
        while (true) {
            createConnection();
        }
    }

    public static void insertbuiten(String tijdstip, float luchtkwaliteit_buiten_fijnstof) {
        String sql = "INSERT INTO luchtkwaliteit_buiten(luchtkwaliteit_timestampbuiten, luchtkwaliteit_buiten_fijnstof) VALUES(?,?)";
        try {
            PreparedStatement preparedStatement = connect().prepareStatement(sql);
            preparedStatement.setString(1, tijdstip);
            preparedStatement.setFloat(2, luchtkwaliteit_buiten_fijnstof);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void insertbinnen(String tijdstip, float luchtkwaliteit_binnen_CO2) {
        String sql = "INSERT INTO luchtkwaliteit_binnen(luchtkwaliteit_timestampbinnen, luchtkwaliteit_binnen_CO2) VALUES(?,?)";
        try {
            PreparedStatement preparedStatement = connect().prepareStatement(sql);
            preparedStatement.setString(1, tijdstip);
            preparedStatement.setFloat(2, luchtkwaliteit_binnen_CO2);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    public static void createConnection() {
        try { //dit haalt de laatste waardes uit de database
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
        try { //dit haalt de laatste waardes van fijnstof
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

        private static Connection connect() {
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

