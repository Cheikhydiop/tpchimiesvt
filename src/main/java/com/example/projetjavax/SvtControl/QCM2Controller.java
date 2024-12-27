package com.example.projetjavax.SvtControl;

import com.example.projetjavax.DatabaseConnection;
import com.example.projetjavax.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class QCM2Controller implements Initializable {

    @FXML
    public AnchorPane anchorId;

    @FXML
    private Button btnQCM2;

    @FXML
    private Pane img1;

    @FXML
    private Pane img2;

    @FXML
    private Pane img3;

    @FXML
    private Pane img4;

    @FXML
    private Pane img5;

    @FXML
    private AnchorPane pane1;

    @FXML
    private AnchorPane pane2;

    @FXML
    private AnchorPane pane3;

    @FXML
    private AnchorPane pane4;

    @FXML
    private AnchorPane pane5;

    @FXML
    public Label labelOK;

    @FXML
    public Label labelKO;


    private double mouseX, mouseY;


    private double initialTranslateX, initialTranslateY;

    private double compteur=0;

    int id=(int) Session.getAttribute("idUser");


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(valide()){
            btnQCM2.setVisible(false);
            labelOK.setVisible(!labelOK.isVisible());

            img1.setLayoutX(25+15);
            img1.setLayoutY(44+30);

            img2.setLayoutX(168+15);
            img2.setLayoutY(44+30);

            img3.setLayoutX(312+15);
            img3.setLayoutY(44+30);

            img4.setLayoutX(477+15);
            img4.setLayoutY(44+30);

            img5.setLayoutX(633+15);
            img5.setLayoutY(44+30);

        }else {
            initMousseControl(img1);
            initMousseControl(img2);
            initMousseControl(img3);
            initMousseControl(img4);
            initMousseControl(img5);
        }
    }

    public void initMousseControl(Pane pane){
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
        if(compareCoordonnees(pane1,img1)){
            compteur++;
        }
        if(compareCoordonnees(pane2,img2)){
            compteur++;
        }
        if(compareCoordonnees(pane3,img3)){
            compteur++;
        }
        if(compareCoordonnees(pane4,img4)){
            compteur++;
        }
        if(compareCoordonnees(pane5,img5)){
            compteur++;
        }

        if(compteur==5){
            labelOK.setVisible(!labelOK.isVisible());
            labelKO.setVisible(false);
            validateQcm();
        }else {
            compteur=0;
            labelKO.setVisible(!labelKO.isVisible());
        }
    }

    //metre a jour la validiter du qcm2
    public  void validateQcm(){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB= connectNow.getConnection();


        String verify="update qcm set valider='oui' where etape='qcm2' and module='svt' and userId="+id;

        try{
            Statement statement= connectionDB.createStatement();
            int queryResult=statement.executeUpdate(verify);

            if (queryResult==1){
                //afficher le message valide
                btnQCM2.setVisible(!btnQCM2.isVisible());
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public  boolean valide(){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB= connectNow.getConnection();


        String verify="select count(*) from qcm where valider='oui' and etape='qcm2' and module='svt' and userId="+id;

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
