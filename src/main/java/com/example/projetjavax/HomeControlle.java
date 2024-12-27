package com.example.projetjavax;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;

import java.io.IOException;
import java.util.Objects;

public class HomeControlle {

    @FXML
    public Sphere sphere2;

    @FXML
    public SubScene subScene2;

    @FXML
    public PerspectiveCamera camera2;


    @FXML
    public PerspectiveCamera camera3;


    @FXML
    public AnchorPane anchorId;

    @FXML
    public ImageView imageId;

    @FXML
    public SubScene subScene4;

    @FXML
    private SubScene subScene;

    @FXML
    private PerspectiveCamera camera;

    @FXML
    private Sphere sphere;

    private double mouseX, mouseY;
    private double initialTranslateX, initialTranslateY;

    public void initialize() throws IOException {
          initMousseControl(subScene, sphere.getParent());
          initMousseControl(subScene2, sphere2.getParent());
        initMousseControl(subScene4, imageId.getParent());
    }

    public void initMousseControl(SubScene subScene, Parent sphere){
        subScene.setOnMousePressed(event -> {
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
            initialTranslateX = sphere.getTranslateX();
            initialTranslateY = sphere.getTranslateY();
        });

        subScene.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - mouseX;
            double deltaY = event.getSceneY() - mouseY;

            // Mettre à jour les translations
            sphere.setTranslateX(initialTranslateX + deltaX);
            sphere.setTranslateY(initialTranslateY + deltaY);
        });
    }
}
