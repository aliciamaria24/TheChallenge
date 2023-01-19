package com.example.RPrototype;

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

import java.io.IOException;
import java.sql.SQLException;

public class SettingsController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField LocationOfRoom;

    @FXML
    private TextField SizeOfRoom;

    @FXML
    private Button confirmPassword;

    @FXML
    private Button gaNaarHome1;

    @FXML
    private TextField nameOfRoom;

    @FXML
    private PasswordField oldPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField passwordSecond;

    @FXML
    private Button CancelButton;

    public void changeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GuiRdevice.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    public void updateRoomInfo(ActionEvent event) throws SQLException, IOException {

        Window owner = gaNaarHome1.getScene().getWindow();
        System.out.println(nameOfRoom.getText());
        System.out.println(SizeOfRoom.getText());
        System.out.println(LocationOfRoom.getText());

        if (nameOfRoom.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter room name");
            return;
        }

        if (LocationOfRoom.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter location of room");
            return;
        }

        String roomName = nameOfRoom.getText();
        String size = SizeOfRoom.getText();
        String location = LocationOfRoom.getText();

        /*
         * Hier connecten we weer met onze database en gebruiken we de methode InsertRecord die we daar
         * in hebben geschreven. Dan roepen we de methode RegisterSuccesfull(event) aan, om weer van
         * scene te switchen, in dit geval naar GuiRdevice.
         * */
        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertRoomData(roomName, size, location);

        changeScene(event);


    }

    public void updatePassword(ActionEvent event) throws SQLException, IOException {

        Window owner = gaNaarHome1.getScene().getWindow();
        System.out.println(oldPassword.getText());
        System.out.println(newPassword.getText());

        if (newPassword.equals(passwordSecond)) {

            String oldPass = oldPassword.getText();
            String Newpass = passwordSecond.getText();

            /*
             * Hier connecten we weer met onze database en gebruiken we de methode InsertRecord die we daar
             * in hebben geschreven. Dan roepen we de methode RegisterSuccesfull(event) aan, om weer van
             * scene te switchen, in dit geval naar GuiRdevice.
             * */
            JdbcDao jdbcDao = new JdbcDao();
            jdbcDao.updatePassword(oldPass, Newpass);



        }


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
