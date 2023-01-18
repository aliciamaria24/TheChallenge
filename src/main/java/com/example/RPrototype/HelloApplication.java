package com.example.RPrototype;

import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import static javafx.application.Application.launch;

public class HelloApplication extends Application {

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("GUILogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 598, 410);
        stage.setTitle("R-DEVICE!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
        DeviceController device = new DeviceController();
    }
}