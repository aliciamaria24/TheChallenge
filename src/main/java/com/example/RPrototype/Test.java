package com.example.RPrototype;

import com.fazecast.jSerialComm.SerialPort;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Test {
    static SerialPort chosenPort;

    //static int x = 0;
    public static void main(String[] args) {
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
