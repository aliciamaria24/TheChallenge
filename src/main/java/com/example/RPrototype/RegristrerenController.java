package com.example.RPrototype;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;

public class RegristrerenController {

    private Parent parent;
    private Stage stage;
    private Scene scene;

    @FXML
    private TextField DeviceCode;

    @FXML
    private PasswordField Password;

    @FXML
    private Button RegisterButton;

    @FXML
    private Button SwitchLogin;

    @FXML
    private TextField Username;

    @FXML
    private Label info;


    @FXML
    public void switchToDeviceCode(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RegristratieCode.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    /*
     * Spreekt voor zichzelf, hier weer een switch gemaakt zodat deze gekoppeld kan worden aan bv een Button
     * En dan switched naar het login scherm. De IOException laat zien of er enige foutmeldingen zijn
     * die bij de IO foutmeldingen hoort. Ik kan dit niet echt beter uitleggen...
     *
     * De Action Event word gebruikt als er een actie word uitgeverd om iets te laten gebeuren.
     * Dus bijvoorbeeld een button click.
     * */

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

        System.out.println(Username.getText());
        System.out.println(Password.getText());

        if (Username.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter an username");
            return;
        }

        if (Password.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }
        if (isSafeString(Usernasme.getText()) && isSafeString(Password.getText())) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "You are using a character that is not allowed. you can only use a-z A-Z ,.:;-@!");
            return;
        }

        String username = Username.getText();
        String password = Password.getText();

        /*
         * Hier connecten we weer met onze database en gebruiken we de methode InsertRecord die we daar
         * in hebben geschreven. Dan roepen we de methode RegisterSuccesfull(event) aan, om weer van
         * scene te switchen, in dit geval naar GuiRdevice.
         * */
        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertRecord(username, password);

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

    public static boolean isSafeString(String input) {
        // Regular expression to match potentially malicious characters in the input string
        String pattern = "[^a-zA-Z0-9 .,;:@!-]";

        // Use the matches() method of the Pattern class to check if the input string contains any malicious characters
        return input.matches(pattern);
    }
}