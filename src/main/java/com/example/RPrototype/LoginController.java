package com.example.RPrototype;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {

    Scanner scanner = new Scanner(System.in);

    @FXML
    private Button loginButton;

    @FXML
    private Label info;

    @FXML
    private TextField password;

    @FXML
    private TextField username;


    @FXML
    void loginButtonOnAction(ActionEvent event) {
        info.setText("JIJ STREST MIJ");

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
