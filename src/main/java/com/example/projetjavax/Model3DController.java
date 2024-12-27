package com.example.projetjavax;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Box;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Model3DController implements Initializable {

//    @FXML
//    public Pane scene;

    @FXML
    public Button idHomme;


    @FXML
    public SubScene modelId;

    @FXML
    public ImageView image1;

    @FXML
    public ImageView image2;

    @FXML
    public ImageView image3;

    private int nb=0;

    private String[] models={"methane2.fxml","ethane.fxml","propane.fxml"};

    @FXML
    public void onActionMethane(ActionEvent actionEvent) {
//        modelLoader(image1,0);
    }

    @FXML
    public void onActionEthane(ActionEvent actionEvent) {

//        modelLoader(image2,1);
    }

    @FXML
    public void onActionPropane(ActionEvent actionEvent) {

//        modelLoader(image3,2);
    }

    private double anchorAngleX=0;
    private double AnchorAngleY=0;

    private double anchorX;
    private double anchorY;

    private final DoubleProperty angleX=new SimpleDoubleProperty(0);
    private final DoubleProperty angleY=new SimpleDoubleProperty(0);

    @FXML
    private SubScene subscene;

    @FXML
    private Pane pane;

    Group model1;

    @FXML
    void Vcellule(ActionEvent event) {
        if (model1 != null) {
            // Supprimer le modèle actuel s'il existe
            pane.getChildren().remove(subscene);
        }


        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1.5);
//        camera.

        //model1 = loadModel(getClass().getResource("/stem/d2a/stem/model/CellAn/ImageToStl.com_mitochondria_-_cell_organelles/ImageToStl.com_mitochondria_-_cell_organelles.obj"));
        model1 = loadModel(HelloApplication.class.getResource("Ag/ImageToStl.com_golgi_apparatuscomplex.obj"));

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (model1 != null) {
            // Supprimer le modèle actuel s'il existe
            pane.getChildren().remove(subscene);
        }


        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1.5);
//        camera.

        //model1 = loadModel(getClass().getResource("/stem/d2a/stem/model/CellAn/ImageToStl.com_mitochondria_-_cell_organelles/ImageToStl.com_mitochondria_-_cell_organelles.obj"));
        model1 = loadModel(HelloApplication.class.getResource("Ag/ImageToStl.com_golgi_apparatuscomplex.obj"));

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

//        TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(8),image1);
//        translateTransition.setByY(40);
////        translateTransition.set;
//        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
//        translateTransition.play();
//
//
//        TranslateTransition translateTransition2=new TranslateTransition(Duration.seconds(8),image2);
//        translateTransition2.setByY(40);
////        translateTransition.set;
//        translateTransition2.setCycleCount(TranslateTransition.INDEFINITE);
//        translateTransition2.play();
//
//
//        TranslateTransition translateTransition3=new TranslateTransition(Duration.seconds(8),image3);
//        translateTransition3.setByY(40);
////        translateTransition.set;
//        translateTransition3.setCycleCount(TranslateTransition.INDEFINITE);
//        translateTransition3.play();
    }

    private Group loadModel(URL url) {
        Group modelRoot = new Group();
        ObjModelImporter importer = new ObjModelImporter();
        importer.read(url);

        for (MeshView view : importer.getImport()) {
            modelRoot.getChildren().add(view);
        }

        return modelRoot;
    }
//    private void modelLoader(ImageView img,int nb){
//        FXMLLoader fxmlLoader=  new FXMLLoader(HelloApplication.class.getResource(models[nb]));
//        try {
//            Parent contenu= fxmlLoader.load();
//
//            ObjetController objetController=fxmlLoader.getController();
//
//            scene.setCenter(contenu);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


    public void onActionHomme(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("acceuil.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) idHomme.getScene().getWindow();


        currentStage.setScene(scene);
    }
}
