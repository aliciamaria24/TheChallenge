package com.example.RPrototype;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;

public class DeviceCodeController {

    private Parent parent;
    private Stage stage;
    private Scene scene;

    @FXML
    private TextField DeviceCode;

    @FXML
    private Button SwitchLogin;

    @FXML
    private Label info;

    @FXML
    private Button nextButton;

    @FXML
    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GUILogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Regristreren.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void InsertCode(ActionEvent event) throws SQLException, IOException {

        Window owner = nextButton.getScene().getWindow();

        System.out.println(DeviceCode.getText());

        if (DeviceCode.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter an username");
            return;
        }

        String deviceCode = DeviceCode.getText();

        /*
         * Hier connecten we weer met onze database en gebruiken we de methode InsertRecord die we daar
         * in hebben geschreven. Dan roepen we de methode RegisterSuccesfull(event) aan, om weer van
         * scene te switchen, in dit geval naar GuiRdevice.
         * */
        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertDevice(deviceCode);

        switchToRegister(event);


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
