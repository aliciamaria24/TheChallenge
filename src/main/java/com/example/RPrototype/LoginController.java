package com.example.RPrototype;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;

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
    private TextField passwordField;

    @FXML
    private TextField username;


    @FXML
    void loginButtonOnAction(ActionEvent event) {
        info.setText("JIJ STREST MIJ");

    }


    public void login(ActionEvent event) throws SQLException {

        Window owner = loginButton.getScene().getWindow();

        System.out.println(username.getText());
        System.out.println(passwordField.getText());

        if (username.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email id");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String emailId = username.getText();
        String password = passwordField.getText();

        JdbcDao jdbcDao = new JdbcDao();
        boolean flag = jdbcDao.validate(emailId, password);

        if (!flag) {
            infoBox("Please enter correct Email and Password", null, "Failed");
        } else {
            infoBox("Login Successful!", null, "Failed");
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
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
