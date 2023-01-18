package com.example.RPrototype;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


/*
* Deze class gebruik ik om even wat meer uit te leggen over hoe de FXML werkt.
*
* <AnchorPane xmlns="http://javafx.com/javafx/17.0.2-ea" xmlns:fx="http://javafx.com/fxml/1"
* fx:controller="com.example.RPrototype.LoginController">
* Dit is een stukje wat boven aan elke FXML file staat. Hier zetten we namelijk in welke controller
* we connecten met welke GUI. Dus in de GUILogin, connecten we in FXML met LoginController.
* Zo kan de GUI de methodes zien die we hebben geschreven in de controllers en kan de Controller
* zien welke ID namen we hebben gegeven aan bepaalde variabelen.
*
* We beginnen in de GUI met een Border pane, hier zetten we de width en
* height in van de Applicatie
*
* Dan hebben we de anchor pane, die is net zo groot als de borderpane
* maar op de anchor pane kunnen we ook textfield, passwordfiels etc, neerzetten
* Op een border pane kan dit niet, daarbij kan je meerdere anchorpanes gebruiken om
* creatief te worden met kleuren en verschillende grotes in je scherm
*
* Dan krijgen we children,
* Dit zijn eigenlijk de kinderen van de anchorPane, in de children zet je dus eigenlijk al
* je gewilde variabelen.
*
* Dan komen de fonts, labels, textfield, buttons etc die je er in zet.
* Dit kan je allemaal een ID geven, een method toewijzen en OnClick, Ondrag etc schrijven
* zodat je dus je methodes kan toewijzen aan een van deze fiels, buttons etc.
*
* Als je iets een ID geeft, zet je neer
* fx:id=" naam die je wilt geven " dan kan je het ook nog een On statement geven
* dus bv onAction="#naam van method" er moet een # voor een method uit een class.

 * */
public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}