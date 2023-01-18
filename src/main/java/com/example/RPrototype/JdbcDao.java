package com.example.RPrototype;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
* Dit is de super geweldige JdbcDao, de class heet zo omdat echt geen idee, ik was niet heel creatief.
* Deze class gebruiken we zodat we niet meerdere keren hoeven te verbinden met onze database.
* Dit is 1 class die we gebruiken om in verschillende Controllers dit te kunnen aanroepen en de methodes
* die gemaakt zijn hierin.
*
* */
public class JdbcDao {

    /*
    * Hier maken we Private static final statements aan zodat we evt een andere database kunnen connecten.
    * Hiervoor hoef je dan alleen de waardes hier aan te passen en niet overal los in deze class.
    * Database url is gewoon de url van onze database
    * Alles spreekt eigenlijk wel voor zich
    * */
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/project";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "Amit23@";

    /*
    * Deze SELECT query gebruiken we voor het inloggen, nogmaal, da zetten we zo neer zodat
    * we niet overal alles los hoeven te veranderen maar het maar 1x hoeven te doen.
    * Deze query selecteerd alles van de tabel gebruiker, waar email adres en wachtwoord ? is.
    * ? betekend dat we niet precies weten wie we nodig hebben uit het database.
    * INSET_QUERY word gebruikt om gebruikers te kunnen inserten. Dit gebruiken wij voor onze register
    * GUI
    * */
    private static final String SELECT_QUERY = "SELECT * FROM gebruiker  WHERE emailadres = ? and wachtwoord = ?";
    private static final String INSERT_QUERY = "INSERT INTO gebruiker (voornaam, achternaam, emailadres, wachtwoord) VALUES (?, ?, ?, ?)";


    /*
    * Deze method gaat kijken of de input in de GUI overeenkomt met wat wij in ons database
    * hebben staan en als dat wel zo is, dan word deze persoon toegelaten tot het volgende scherm
    * zo niet, word er een foutmelding weergegeven.
    * */
    public boolean validate(String emailadres, String wachtwoord)throws SQLException {

        //TRY CATCH: Betekend dat die eerst probeert om connectie te maken
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             //Hier maakt die een statement waar die zegt dat emailadres op plek 1 moet en wachtwoord op 2
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, emailadres);
            preparedStatement.setString(2, wachtwoord);

            //Dit is om te checken of het goed gaat, hij print in onze console uit wat we hebben ingevoerd.
            System.out.println(preparedStatement);

            //Hier voert die de query uit
            ResultSet resultSet = preparedStatement.executeQuery();
            //Als deze dus word uitgevoerd, word het TRUE
            if (resultSet.next()) {
                return true;
            }


            //De catch bevat de FALSE
            //DE SQLException laat zien wat er fout gaat in de code.
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return false;
    }

    /*
    * Deze method zorgt ervoor dat er in de invoer velden van onze Register gui ook daadwerkelijk in onze database komt.
    * Hij probeert eerst weer een connectie te maken, vult dan alles in op plek 1 tm 4
    * Dan print die uit wat we hebben ingetypt in onze terminal
    * dam excute die de query
    * in onze catch laat die weer zien wat er fout gaat.
    * */
    public void insertRecord(String firstName, String lastName, String Email, String Password) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, Email);
            preparedStatement.setString(4, Password);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }


    /*Voor de PrintSQLException is een method geschreven
    * Dit is zodat die foutcode laat zien zoals wij willen.
    * Dus eerst de SQLState, dus wat is de status, dan de Error code en de message
    * Dan vraagt die om de cause weer te geven, als die niet 0 is, print die cause uit,
    * zo niet, komt die niet in de while loop en komt er geen geprinte cause.
    * */
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}