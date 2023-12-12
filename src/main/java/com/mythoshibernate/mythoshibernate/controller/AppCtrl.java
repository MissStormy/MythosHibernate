package com.mythoshibernate.mythoshibernate.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AppCtrl {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}