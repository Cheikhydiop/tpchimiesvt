package com.example.projetjavax;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class homeChimieControl {
    @FXML
    private Label idC;

    @FXML
    private Button idHomme;

    @FXML
    private Label idM;

    @FXML
    private Label idT;

    @FXML
    private Button idd;



    @FXML
    void onActionDeconnexion(ActionEvent event) throws IOException {
        Session.removeAttribute("idUser");
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("login.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) idd.getScene().getWindow();


        currentStage.setScene(scene);

        currentStage.show();
    }

    @FXML
    void onActionHomme(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("acceuil.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) idHomme.getScene().getWindow();


        currentStage.setScene(scene);
    }

    @FXML
    void onActionCour(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("coursGermination.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) idC.getScene().getWindow();


        currentStage.setScene(scene);

        currentStage.show();
    }

    @FXML
    void onActionModel(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("methane2.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) idM.getScene().getWindow();


        currentStage.setScene(scene);

        currentStage.show();
    }

    @FXML
    void onActionTp(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("tdChimie.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) idT.getScene().getWindow();


        currentStage.setScene(scene);

        currentStage.show();
    }
}
