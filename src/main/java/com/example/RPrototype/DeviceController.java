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

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

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

    public void showText(ActionEvent event) throws IOException, SQLException {
        System.out.println(roomName.getText());
        String RoomName = roomName.getText();
        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.showRoomName(RoomName);
        roomName.setText(RoomName);

    }

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
        boolean dataswitch = false; //false:neem eerst co2 true:neem fijnstof
        boolean CO2 = true; // true: safe false:unhealthy
        boolean fijnstof = true; //true: safe false: unhealthy
        //   int raamopen = 0; //0- raam hoeft niet open 1-raam moet open  2- is het niet safe om te openen

// portnames ; COM3 en COM6 op AMIT PC


        while (true) {
            //switchen van COM ports
//            if (!dataswitch) {//CO2
            chosenPort = SerialPort.getCommPort("COM18");
//            } else {//fijnstof
//                chosenPort = SerialPort.getCommPort("COM6");

//            chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
            //------openPort
            chosenPort.openPort();


            Scanner scanner = new Scanner(chosenPort.getInputStream());

            String line = scanner.nextLine();
            int number = Integer.parseInt(line);
            System.out.println(number);


            scanner.close();

            //----- close port
            chosenPort.closePort();
            if (!dataswitch) { //CO2
                if (number < 800) {
                    CO2 = true;
                } else {
                    CO2 = false;
                }

//                dataswitch = true; //switch naar port van fijnstof
//            } else { // fijnstof
//                if (number < 800) {
//                    fijnstof = true;
//                } else {
//                    fijnstof = false;
//                }

                dataswitch = false; //switch naar port van CO2
            }


//            if ((CO2 || !CO2) && !fijnstof) {
////                NotNecessary.setText("Not safe to open a window");
//                infoBox("Not safe to open a window", null, "ALERT!");
//                MeasureData(event);
//                break;
//
//            }

            if (CO2 || !CO2) {
//                NotNecessary.setText("Not safe to open a window");
                infoBox("Not safe to open a window", null, "ALERT!");
                MeasureData(event);

            }

            if (CO2) {
//                NotNecessary.setText("Not safe to open a window");
                infoBox("Not necessary to open window", null, "ALERT!");
                MeasureData(event);

            }

            if (CO2) {
//                NotNecessary.setText("Please open window to boost productivity");
                infoBox("Please open window to boost productivity", null, "ALERT!");
                MeasureData(event);

            }
//
//            if (CO2 && fijnstof) {
////                NotNecessary.setText("Not safe to open a window");
//                infoBox("Not necessary to open window", null, "ALERT!");
//                MeasureData(event);
//
//            }

//            if ((!CO2 && fijnstof)) {
////                NotNecessary.setText("Please open window to boost productivity");
//                infoBox("Please open window to boost productivity", null, "ALERT!");
//                MeasureData(event);
//
//            }
        }
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
