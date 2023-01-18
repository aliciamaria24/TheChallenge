package Database;

import java.sql.*;

public class Database {
    /*
    * Deze file laat zien of we een database connectie hebben
    * Dit zorgt ervoor dat we niet zo diep in onze fouten hoeven te kijken en dus hier
    * gelijk kunnen zien of het komt omdat we geen connectie hebben of wel.
    * */

    public static void main(String[] args) {
        Connection conn = connect(); // hier wordt er met een connect method een statement object gecreeërd met behulp van een connectie
        try {
            Statement stmt = conn.createStatement();

            String checkSQL = "SELECT time FROM connection_status ORDER BY time DESC LIMIT 1"; // sql statement om de meest recente "time" value van de connection_status table
            ResultSet rs = stmt.executeQuery(checkSQL); // het resultaat daarvan wordt hier opgeslagen
            Timestamp lastConnected = null;
            if (rs.next()) {
                lastConnected = rs.getTimestamp(1); // en hier wordt de volgende result set opgehaald en opgeslagen in variable Lastconnected
            }
            long threshold = 1*60*1000; // 1 minute in milliseconds. Hier is er per x ( in dit geval 1) minuten een treshold. Dit betekend na elke minuut als er weer connectie wordt gemaakt dan wordt dit ingevoert in de database.
            if(lastConnected == null || lastConnected.getTime() + threshold < System.currentTimeMillis()) {
                String insertSQL = "INSERT INTO connection_status (status,time) VALUES ('connected',DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s'))";
                stmt.executeUpdate(insertSQL); // connectie met database is gemaakt en de waardes 'connected' en tijd van inlog worden vastgelegd en ingevoerd.
                System.out.println("Connected to the database");
            } else {
                System.out.println("Already connected to the database"); // Als er te vaak binnen 1 minuut connectie gemaakt probeert te worden. Zorgt ervoor dat de database niet volloopt met inloggegevens.
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close(); // hier wordt de database connectie afgesloten
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    private static Connection connect() {
        Connection conn = null;
        String driver = "com.mysql.cj.jdbc.Driver"; //
        // MySQL connection string
        String connection = "jdbc:mysql://localhost:3306/project"; // hier wordt met de driver connectie gemaakt met de database 'project'
        String user = "root"; // username
        String password = "Amit23@"; // wachtwoord
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(connection, user, password);
        } catch (Exception e) {
            System.err.println(e.getMessage()); // als er een bijzonderheid plaatsvindt dan geeft het een error bericht
        }
        return conn; // hier gaat het weer terug naar de object connectie
    }

}