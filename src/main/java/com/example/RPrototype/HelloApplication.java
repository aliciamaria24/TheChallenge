package com.example.RPrototype;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/*
 * Deze applicatie is een extend van de Class Application.
 * De Class Application hebben wij niet gemaakt, maar krijgen wij mee als we een nieuw project bouwen.
 * Dit is omdat het best complex is om een heel scherm te laten zien met alleen deze lijn code die er staat.
 * */
public class HelloApplication extends Application {

    /*
     * Hier maken we een methode aan genaamd start. Hier zetten wij onze FXMLLoader, die dus
     * de juiste resource.FXML file pakt. Dit is dus ons begin scherm aka GUILogin.FXML
     * We zetten de scene neer met de juiste afmetingen die we willen voor onze applicatie.
     * Dan vragen we weer om de stage waar de scenen dus bij hoort.
     * De Stage geven we een title (Dit komt helemaal boven aan in het scherm)
     * Dan zetten we weer de juiste persoon op het podium
     * en dan laten we het "podium" zien
     * */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("GUILogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 598, 410);
        stage.setTitle("R-DEVICE!");
        stage.setScene(scene);
        stage.show();

//        // Hier zie je hoe een hasher werkt
//        String password = "HeelSterkWachtwoord123!";
//        System.out.println("het wachtwoord is: " + password);
//        // Hier ga ik het wachtwoord hashen
//        String hash = Hasher.getSHA256Hash(password);
//        System.out.println("hash: " + hash);
    }

    /*
     * Aangezien dit onze Main application is willen deze class kunnen laten runnen
     * hiervoor gebruiken we een main method die alles Launched.
     * */
    public static void main(String[] args) {

        launch();

        DeviceController device = new DeviceController();
    }
}