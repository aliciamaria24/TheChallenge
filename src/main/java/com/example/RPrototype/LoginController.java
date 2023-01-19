package com.example.RPrototype;

import com.fazecast.jSerialComm.SerialPort;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {

    Scanner scanner = new Scanner(System.in);

    //Hier weer het zelfde, dit is weer zodat je alles makkelijk in 1x kunt aanpassen
    private Stage stage;
    private Scene scene;
    private Parent root;


    /*Dit is weer de skeleton van FXML File, dit zijn dus ID's die aan de Buttons, labels en textfield hangen*/
    @FXML
    private Button loginButton;

    @FXML
    private Label info;

    @FXML
    private PasswordField passwordF;

    @FXML
    private TextField username;


    /*
     * Als we op een button klikken zoals uitloggen, home gebruiken we de methode SwitchScene.
     * Deze methode zorgt ervoor dat als je ergens op klikt of scrolt of wat je ook gebruikt, naar
     * de GuiRdevice gaat!
     * */
    @FXML
    public void switchToScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GuiRdevice.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    //Hier geld het zelfde als hierboven maar dan voor Regristreren.
    @FXML
    public void switchToRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Regristreren.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    /*
     * Deze methode zorgt ervoor dat er ingelogd kan worden met behulp van onze klas JdbcDao.
     * Hij print de username en password die gegeven is
     * dan komt de If statement, dit zorgt ervoor dat als een veld leeg is, er een Aler komt
     * dat het veld ingevoerd moet worden
     * */
    public void login(ActionEvent event) throws SQLException, IOException {

        Window owner = loginButton.getScene().getWindow();

        System.out.println(username.getText());
        System.out.println(passwordF.getText());

        if (username.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email id");
            return;
        }
        if (passwordF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }
        /*
         * Hier roepen we de class JdbcDao aan en zeggen we dus dat we een nieuwe connectie willen.
         * Dan roepen we de method Validate aan die we in de Class JdbcDao hebben gemaakt.
         * Hier kijkt die dus pas of email en wachtwoord overeen komt met een wachtwoord en email uit Database
         * */

        String emailId = username.getText();
        String password = passwordF.getText();

        JdbcDao jdbcDao = new JdbcDao();
        boolean flag = jdbcDao.validate(emailId, password);

        /*
         * Als dit niet het geval is, dus het klopt niet dan vraagt die of je een correcte Email en Wachtwoord
         * wilt invullen
         * Zo niet, dan roept die de method SwitchToScene(event) aan zodat deze naar GuiRdevice gaat.
         * */
        if (!flag) {
            infoBox("Please enter correct Email and Password", null, "Failed");
        } else {
            switchToScene(event);
        }
    }


    /*
     * Dit is de info box method, die een string infomessage, string header text en string title heeft
     * Deze 3 worden in de alert weergegeven. eerst word een nieuwe alert gemaakt
     * dan word er per Variabele infomessage, Headertext en Title allert gemaakt
     * aan het eind laat die de hele altert zien.
     * */
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    /*
     * Hier word de method ShowAlert gemaakt, waar het alertype, owner, title en message word weergegeven.
     * Elke keer als je een alert maakt, moet je zelf het type geven, title, message en owner.
     * Nadat je dat hebt gedaan laat die de alert zien.
     * */
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }


//    private static Connection connect() {
//        Connection conn = null;
//        String driver = "com.mysql.cj.jdbc.Driver";
//        // MySQL connection string, pas zonodig het pad aan:
//        String connection = "jdbc:mysql://localhost:3306/project";
//        String user = "root";
//        String password = "Amit23@";
//        try {
//            Class.forName(driver);
//            conn = DriverManager.getConnection(connection, user, password);
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//        return conn;
//    }

}
