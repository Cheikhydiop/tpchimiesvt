package com.example.projetjavax;

import com.example.projetjavax.DatabaseConnection;
import com.example.projetjavax.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class tdControl implements Initializable {
    @FXML
    public AnchorPane anchorPaneId;

    @FXML
    public Button idHomme;

    @FXML
    public Button buttonValide;

    @FXML
    public Button buttonNext;

    @FXML
    public CheckBox Resp1;
    @FXML
    public CheckBox Resp2;
    @FXML
    public CheckBox Resp3;

    @FXML
    public Button buttonValide1;

    @FXML
    public Button nextButton;


    double cmp=1;

    @FXML
    public void onActionHomme(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("acceuil.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) idHomme.getScene().getWindow();


        currentStage.setScene(scene);
    }

    @FXML
    public void onActionNext(ActionEvent actionEvent) {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB= connectNow.getConnection();


        String verify="select * from qcm";

        try{
            Statement statement= connectionDB.createStatement();
//            int queryResult=statement.executeUpdate(verify);
            ResultSet queryResult=statement.executeQuery(verify);

            while (queryResult.next()){
                if(queryResult.getString("etape").equals("qcm1")&&
                        queryResult.getString("valider").equals("oui")&&cmp==1){
                    FXMLLoader fxmlLoader1=  new FXMLLoader(HelloApplication.class.getResource("QCM2.fxml"));
                    try {
                        AnchorPane contenu= fxmlLoader1.load();

                        anchorPaneId.getChildren().add(contenu);

                        cmp=2;
                        break;

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if(queryResult.getString("etape").equals("qcm2")&&
                        queryResult.getString("valider").equals("oui")&&cmp==2){
                    FXMLLoader fxmlLoader1=  new FXMLLoader(HelloApplication.class.getResource("QCM3.fxml"));
                    try {
                        AnchorPane contenu= fxmlLoader1.load();
                        anchorPaneId.getChildren().add(contenu);
                        cmp=3;
                        break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader fxmlLoader=  new FXMLLoader(HelloApplication.class.getResource("QCM.fxml"));
        try {
            AnchorPane contenu= fxmlLoader.load();

//            QCMController qcmControllerController=fxmlLoader.getController();

            anchorPaneId.getChildren().add(contenu);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
