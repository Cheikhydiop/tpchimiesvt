package com.example.projetjavax;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class QCM2Controller implements Initializable {

    @FXML
    public AnchorPane anchorId;

    @FXML
    public Button buttonValide1;

    @FXML
    public AnchorPane alcaneId;

    @FXML
    public AnchorPane alceneId;

    @FXML
    public Pane pane1;

    @FXML
    public Pane pane2;


    @FXML
    public Label labelOK;



    private double mouseX, mouseY;


    private double initialTranslateX, initialTranslateY;

    private double compteur=0;
    private boolean mouseControl=true;

    int id=(int) Session.getAttribute("idUser");



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(valide()){
            buttonValide1.setVisible(false);
            pane1.setLayoutX(37+45);
            pane1.setLayoutY(42+30);
            pane2.setLayoutX(548+45);
            pane2.setLayoutY(42+30);
        }else {
            initMousseControl2(pane1);
            initMousseControl2(pane2);
        }


    }
    public void initMousseControl2(Pane pane){
        pane.setOnMousePressed(event -> {
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
            initialTranslateX = pane.getTranslateX();
            initialTranslateY = pane.getTranslateY();
        });

        pane.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - mouseX;
            double deltaY = event.getSceneY() - mouseY;

            //calculer la nouvelle possition
            double newX=initialTranslateX + deltaX;
            double newY=initialTranslateY + deltaY;
            // Mettre à jour les translations
            pane.setTranslateX(newX);
            pane.setTranslateY(newY);
        });
    }
    public void onActionValider(ActionEvent actionEvent) {
        if(compareCoordonnees(alcaneId,pane1)){
            compteur++;
        }
        if(compareCoordonnees(alceneId,pane2)){
            compteur++;
        };

        if(compteur==2){
            labelOK.setVisible(!labelOK.isVisible());
            validateQcm();
        }else {
            compteur=0;
        }
    }
    //metre a jour la validiter du qcm2
    public  void validateQcm(){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB= connectNow.getConnection();


        String verify="update qcm set valider='oui' where etape='qcm2' and module='chimie' and userId="+id;

        try{
            Statement statement= connectionDB.createStatement();
            int queryResult=statement.executeUpdate(verify);

            if (queryResult==1){
                //afficher le message valide
                buttonValide1.setVisible(!buttonValide1.isVisible());
                mouseControl=false;
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public  boolean valide(){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB= connectNow.getConnection();


        String verify="select count(*) from qcm where valider='oui' and etape='qcm2' and module='chimie' and userId="+id;

        try{
            Statement statement= connectionDB.createStatement();
//            int queryResult=statement.executeUpdate(verify);
            ResultSet queryResult=statement.executeQuery(verify);

            while (queryResult.next()){
                if(queryResult.getInt(1)==1){
                    return  true;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return false;
    }
    public boolean compareCoordonnees(AnchorPane anchor,Pane pane){
        double p1SceneX1= anchor.localToScene(0,0).getX();
        double p1SceneY1= anchor.localToScene(0,0).getY();

        double p1SceneX2= anchor.localToScene(anchor.getWidth(),anchor.getHeight()).getX();
        double p1SceneY2= anchor.localToScene(anchor.getWidth(),anchor.getHeight()).getY();

        double p2SceneX1= pane.localToScene(0,0).getX();
        double p2SceneY1= pane.localToScene(0,0).getY();

        double p2SceneX2= pane.localToScene(pane.getWidth(),pane.getHeight()).getX();
        double p2SceneY2= pane.localToScene(pane.getWidth(),pane.getHeight()).getY();

        //verifier si le pane est entre les elements anchors
        boolean overlap1= (p2SceneX1>p1SceneX1 && p2SceneX2<p1SceneX2);
        boolean overlap2= (p2SceneY1>p1SceneY1 && p2SceneY2<p1SceneY2);


        if(overlap1&&overlap2){
               return true;
        }
        return false;
    }

}
