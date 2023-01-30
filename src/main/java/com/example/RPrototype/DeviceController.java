package com.example.RPrototype;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeviceController {

    /*
     * Alle @FXML zijn afkomstig van de FXML files. In de GUI kan je ID's meegeven aan buttons
     * deze buttons staan dus hier onder weergegeven. Dit zodat je in Java methodes kan schrijven en deze
     * kan koppelen aan een Button, TextField, ToggleButton etc.
     * */


    @FXML
    private Button CO2Safe;

    @FXML
    private Button CO2Unhealthy;

    @FXML
    private Button LogOut;
    @FXML
    private TextField NotNecessary;

    @FXML
    private ToggleButton AirButton;

    @FXML
    private Button gaNaarSettings;

    @FXML
    private Label roomName;
    @FXML
    private Label userName;


    /*
     * Deze Stage, Scene en Parent zijn private, zodat we deze niet kunnen aanroepen in een andere klas.
     * Deze 3 variabelen worden gebruikt om de code in de Methode goToLogin sneller en makkelijker te kunnen
     * schrijven.
     * */
    private Stage stage;
    private Scene scene;
    private Parent root;

//    public void showText(ActionEvent event) throws IOException, SQLException {
//        System.out.println(roomName.getText());
//        String RoomName = roomName.getText();
//        JdbcDao jdbcDao = new JdbcDao();
//        jdbcDao.showRoomName(RoomName);
//        roomName.setText(RoomName);
//
//    }

    /*
     * Deze methode zorgt ervoor dat je van Scene kan wisselen.
     * Je kan deze methode in je GUI koppelen aan een button, en als je daar op klikt, gebeurd dat.
     * */
    public void goToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GUILogin.fxml"));// Parent laat zijn dat die het hoofd gedeelte is van de code.
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //Stage haalt de "Window" op, zoals aan het einde .getWindow -> zorgt ervoor dat de juiste window word opgehaald
        scene = new Scene(root);//Scene laat de nieuwe scene binnen komen
        /*
         * beeld je hier in dat er een persoon (scene) op het podium (stage) staat. Wat hier dus eigenlijk staat
         * is dat die dus de juiste persoon op het podium laat zien.
         * */
        stage.setScene(scene);
        stage.show(); // Hier laat je daadwerkelijk de stage zien.

    }

    public void goToSettings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SettingsMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    static SerialPort chosenPort;

    //static int x = 0;

    public void MeasureData(ActionEvent event) throws SQLException, IOException {
        COMPortReader cp = new COMPortReader(comPortNora, baudRate);
        cp.openPort();
        int ppm = cp.readData();
        System.out.println(ppm);
        if (ppm > SafePPM) {
            infoBox("Please open window to boost productivity", null, "ALERT!");
            MeasureData(event);
        }
        cp.closePort();
    }


    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

//    public void showText(ActionEvent event) throws IOException {
//        NotNecessary.setText("Not necessary to open a window");
//    }
//
//    public void showSafeText(ActionEvent event) throws IOException {
//        NotNecessary.setText("Open window to improve productivity");
//    }
//
//    public void showNotSafe(ActionEvent event) throws IOException {
//        NotNecessary.setText("It's not safe to open a window");
//    }

    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }


}
