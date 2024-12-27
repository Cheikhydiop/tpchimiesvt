package com.example.projetjavax;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {


    public Button idSignUp;

    @FXML
    private Label welcomeText;


    @FXML
    public void onActionSignUp(ActionEvent actionEvent) {
        System.out.println("Welcome Moussa");
    }
}