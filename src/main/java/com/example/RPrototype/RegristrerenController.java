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


    /*
     * Spreekt voor zichzelf, hier weer een switch gemaakt zodat deze gekoppeld kan worden aan bv een Button
     * En dan switched naar het login scherm. De IOException laat zien of er enige foutmeldingen zijn
     * die bij de IO foutmeldingen hoort. Ik kan dit niet echt beter uitleggen...
     *
     * De Action Event word gebruikt als er een actie word uitgeverd om iets te laten gebeuren.
     * Dus bijvoorbeeld een button click.
     * */
    @FXML
    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GUILogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void RegisterSuccesfull(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SetupMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    /*
     * Hier is de Methode Register, deze methode zorgt ervoor dat er iemand word geregristreerd en in het database
     * komt.
     * Hij print weer alle data uit in onze terminal wat is ingevoerd in de velden.
     * Als er velden leeg zijn, laat die weer een alert zien.
     * */
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

        /*
         * Hier connecten we weer met onze database en gebruiken we de methode InsertRecord die we daar
         * in hebben geschreven. Dan roepen we de methode RegisterSuccesfull(event) aan, om weer van
         * scene te switchen, in dit geval naar GuiRdevice.
         * */
        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertRecord(firstName, lastName, emailId, password);

        RegisterSuccesfull(event);


    }

    /*
     * Hier weer de methode voor alert, omdat we deze in de vorige class
     * private hebben gemaakt, kunnen we hem niet aanroepen en maken we hem hier dus weer.
     * We zouden dit kunnen versimpelen om hem public te maken.
     * */

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}