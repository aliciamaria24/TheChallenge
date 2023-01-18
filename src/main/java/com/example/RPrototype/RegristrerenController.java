package com.example.RPrototype;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class RegristrerenController {

    private Stage stage;
    private Scene scene;
    private Parent root;

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
    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GUILogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void RegisterSuccesfull(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GuiRdevice.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    public void register(ActionEvent event) throws SQLException, IOException {

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

        RegisterSuccesfull(event);


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