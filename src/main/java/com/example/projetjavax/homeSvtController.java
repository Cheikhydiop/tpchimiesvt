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

public class homeSvtController {

    public Button idHoms;
    @FXML
    private Label labelID;


    @FXML
    private Button dId;

    @FXML
    private Button idCour;


    @FXML
    private Button idTp;

    HelloApplication helloApplication=new HelloApplication();

    @FXML
    void onActionCour(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("SVT/courGermination.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) dId.getScene().getWindow();


        currentStage.setScene(scene);
    }

    @FXML
    void onActionDeconnexion(ActionEvent event) throws IOException {
        Session.removeAttribute("idUser");
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("login.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) dId.getScene().getWindow();


        currentStage.setScene(scene);

        currentStage.show();

    }


    @FXML
    void onActionHoms(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("acceuil.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) idHoms.getScene().getWindow();


        currentStage.setScene(scene);
    }

    @FXML
    void onActionTp(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("SVT/tpGermination.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) idTp.getScene().getWindow();


        currentStage.setScene(scene);
    }

}
