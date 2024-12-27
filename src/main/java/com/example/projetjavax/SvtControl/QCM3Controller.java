package com.example.projetjavax.SvtControl;

import com.example.projetjavax.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Sphere;

import java.net.URL;
import java.util.ResourceBundle;

public class QCM3Controller implements Initializable {
    @FXML
    public Sphere sphere1;

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
    public Label labelValide;



    private double mouseX, mouseY;


    private double initialTranslateX, initialTranslateY;

    private double compteur=0;

    int id=(int) Session.getAttribute("idUser");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        sphere1.setMaterial(Color.RED);
//        initMousseControl(anchorId,sphere1);
        initMousseControl2(pane1);
        initMousseControl2(pane2);

//        initMousseControl2(anchorId,img3);
//        initMousseControl2(anchorId,img4);
//        initMousseControl2(anchorId,img5);

    }

    public void initMousseControl(AnchorPane subScene, Sphere sphere){
        subScene.setOnMousePressed(event -> {
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
            initialTranslateX = sphere.getTranslateX();
            initialTranslateY = sphere.getTranslateY();
        });

        sphere.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - mouseX;
            double deltaY = event.getSceneY() - mouseY;

            //calculer la nouvelle possition
            double newX=initialTranslateX + deltaX;
            double newY=initialTranslateY + deltaY;
            // Mettre à jour les translations
            sphere.setTranslateX(newX);
            sphere.setTranslateY(newY);
        });
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
            labelValide.setText("valide :)");
            compteur=0;
        }else {
            labelValide.setText("invalide position");
            compteur=0;
        }
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
