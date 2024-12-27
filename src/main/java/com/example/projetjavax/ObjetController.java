package com.example.projetjavax;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;

public class ObjetController implements Initializable {

    @FXML
    private SubScene subscene;

    @FXML
    private Pane pane;

    Group model1;

    private double mouseX, mouseY;
    private double initialTranslateX, initialTranslateY;

    private double anchorAngleX=0;
    private double AnchorAngleY=0;

    private double anchorX;
    private double anchorY;

    private final DoubleProperty angleX=new SimpleDoubleProperty(0);
    private final DoubleProperty angleY=new SimpleDoubleProperty(0);


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1.5);
//        camera.

        //model1 = loadModel(getClass().getResource("/stem/d2a/stem/model/CellAn/ImageToStl.com_mitochondria_-_cell_organelles/ImageToStl.com_mitochondria_-_cell_organelles.obj"));
        model1 = loadModel(HelloApplication.class.getResource("Ag/ImageToStl.com_golgi_apparatuscomplex.obj"));


        pane.getChildren().remove(subscene);
        subscene = new SubScene(model1, pane.getWidth(), pane.getHeight(), true, null);
        subscene.setCamera(camera);
        pane.getChildren().add(subscene);

        subscene.setOnScroll(event2 ->{
            double delta= event2.getDeltaY();
            double scale =(delta>0)?0.8:1.1;
            camera.setTranslateZ(camera.getTranslateZ()*scale);
        } );

        Rotate xRotate;
        Rotate yRotate;
        model1.getTransforms().addAll(
                xRotate=new Rotate(0,Rotate.X_AXIS),
                yRotate=new Rotate(0,Rotate.Y_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        pane.setOnMousePressed(event1->{
            anchorX=event1.getSceneX();
            anchorY=event1.getSceneY(); //recuperer les coordonnee qui sont cliquee
            anchorAngleX=angleX.get();
            AnchorAngleY=angleY.get();

        });


        pane.setOnMouseDragged(event2 -> {
            angleX.set(anchorAngleX-(anchorY-event2.getSceneY()));
            angleY.set(AnchorAngleY+anchorX-event2.getSceneX());
        });

    }

    //methode de chargement du model 3D
    private Group loadModel(URL url) {
        Group modelRoot = new Group();
        ObjModelImporter importer = new ObjModelImporter();
        importer.read(url);

        for (MeshView view : importer.getImport()) {
            modelRoot.getChildren().add(view);
        }

        return modelRoot;
    }

}
