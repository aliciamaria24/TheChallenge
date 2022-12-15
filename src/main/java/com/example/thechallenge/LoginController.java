package com.example.thechallenge;

import javafx.fxml.FXML;

import java.awt.*;

public class LoginController {

    @FXML
    private TextField user;
    @FXML private TextField password;
    @FXML private Button loginButton;


    public void initManager(final LoginManager loginManager) {
        loginButton.setOnAction(event -> {
            String sessionID1 = authorize();
            if (sessionID1 != null) {
                loginManager.authenticated();
            }
        });
    }
}
