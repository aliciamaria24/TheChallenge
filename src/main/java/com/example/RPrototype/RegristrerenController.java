package com.example.RPrototype;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class RegristrerenController {

    @FXML
    private TextField Email;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    @FXML
    private PasswordField Password;

    @FXML
    private Button RegisterButton;

    @FXML
    private Button SwitchLogin;


    @FXML
    public void register(ActionEvent event) throws SQLException {

        Window owner = RegisterButton.getScene().getWindow();

        System.out.println(FirstName.getText());
        System.out.println(LastName.getText());
        System.out.println(Email.getText());
        System.out.println(Password.getText());

        if (FirstName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your first name");
            return;
        }

        if (LastName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your last name");
            return;
        }
        if (Email.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email");
            return;
        }
        if (Password.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String firstName = FirstName.getText();
        String lastName = LastName.getText();
        String emailId = Email.getText();
        String password = Password.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertRecord(firstName, lastName, emailId, password);

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                "Welcome " + FirstName.getText());
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}