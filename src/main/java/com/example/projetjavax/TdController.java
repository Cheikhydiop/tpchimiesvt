package com.example.projetjavax;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TdController implements Initializable {

    @FXML
    private AnchorPane anchorPaneId;

    @FXML
    private Button buttonNext;

    @FXML
    private Button idHomme;

    @FXML
    private Button idd;

    @FXML
    private Label loginMessageLabel;

    int id=(int) Session.getAttribute("idUser");

    @FXML
    void onActionDeconexion(ActionEvent event) throws IOException {
        Session.removeAttribute("idUser");
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("login.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) idd.getScene().getWindow();

        currentStage.setScene(scene);
    }

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
                        queryResult.getString("valider").equals("oui")&&
                        queryResult.getString("module").equals("chimie")&&
                        (queryResult.getInt(5)==id)&&cmp==1){
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
                        queryResult.getString("valider").equals("oui")&&
                        queryResult.getString("module").equals("chimie")&&
                        (queryResult.getInt(5)==id)&&cmp==1){
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
