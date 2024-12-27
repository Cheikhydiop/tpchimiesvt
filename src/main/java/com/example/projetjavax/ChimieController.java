package com.example.projetjavax;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ChimieController {


    @FXML
    public Button idGermination;

    @FXML
    public Button idHomme;

    @FXML
    public void onActionGermination(ActionEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("coursGermination.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        //accedez a la scene actuelle et remplacer la par la nouvelle scene
        Stage currentStage= (Stage) idGermination.getScene().getWindow();


        currentStage.setScene(scene);
    }

    @FXML
    public void onActionHomme(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("acceuil.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) idHomme.getScene().getWindow();


        currentStage.setScene(scene);
    }
}
