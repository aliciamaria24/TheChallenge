package FXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.tree.ExpandVetoException;

public class GUILogin extends Application {

    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUILogin.fxml"));

        Scene scene = new Scene(root);
        stage.show();

    }

    public static void main(String[] args){
        launch(args);
    }

}
