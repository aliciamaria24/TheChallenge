package com.example.RPrototype;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.fazecast.jSerialComm.SerialPort;
import javafx.stage.Window;
import org.w3c.dom.Text;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Scanner;

import java.io.IOException;
import java.util.Scanner;

public class DeviceController {

    @FXML
    private Button CO2Safe;

    @FXML
    private Button CO2Unhealthy;

    @FXML
    private Button LogOut;
    @FXML
    private Button NotNecessary;

    @FXML
    private ToggleButton AirButton;


    @FXML
    private Button gaNaarSettings;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void goToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GUILogin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void goToSettings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GUILogin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
            if (!dataswitch) {
                chosenPort = SerialPort.getCommPort("COM3");
            } else {
                chosenPort = SerialPort.getCommPort("COM6");
            }
            chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
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
                if (number < 2000) {
                    CO2 = true;
                } else {
                    CO2 = false;
                }

                dataswitch = true; //switch naar port van fijnstof
            } else { // fijnstof
                if (number < 1000) {
                    fijnstof = true;
                } else {
                    fijnstof = false;
                }

                dataswitch = false; //switch naar port van CO2
            }

//            if (CO2 && fijnstof) {
//                NotNecessary.setText("Not necessary to open a window");
//            } else if (CO2 || !CO2 && !fijnstof) {
//                NotNecessary.setText("Not safe to open a window");
//            } else if (!CO2 && fijnstof) {
//                NotNecessary.setText("Open a window to improve producivity");
//            }


//            if (CO2 && fijnstof) {
//                showAlert(Alert.AlertType.ERROR,
//                        "ALERT WINDOW", "Not necessarry to open a window");
//
//            } else if (CO2 || !CO2 && !fijnstof) {
//                showAlert(Alert.AlertType.ERROR,
//                        "ALERT WINDOW", "Not safe to open a window");
//
//            } else if (!CO2 && fijnstof) {
//                showAlert(Alert.AlertType.ERROR,
//                        "ALERT WINDOW", "Open a window to improve productivity");
//            }


        }


    }
    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }



}
