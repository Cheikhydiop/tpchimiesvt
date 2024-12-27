package com.example.projetjavax;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;


public class ObjetControl2 implements Initializable {

    @FXML
    private Pane acethyleneId;

    @FXML
    private Pane buthaneId;

    @FXML
    private Pane butheneId;

    @FXML
    private Pane pane2;

    @FXML
    private Pane pane3;

    @FXML
    private Pane pane4;

    @FXML
    private SubScene subscene2;

    @FXML
    private SubScene subscene3;

    @FXML
    private SubScene subscene4;

    @FXML
    public Pane mathaneId;
    @FXML
    private SubScene subscene;
    @FXML
    private Pane pane;


    @FXML
    public Pane ethaneId;
    @FXML
    private SubScene subscene1;
    @FXML
    private Pane pane1;


    @FXML
    public Pane homeId;
    @FXML
    public MediaView mediaViewId;


    @FXML
    private Button idHomme;

    @FXML
    private Button idd;



    private double mouseX, mouseY;

    private Group model1,model2,model3;
    private double anchorX;
    private double anchorY;
    private double getAnchorY;
    private double anchorAngleX=0;
    private double AnchorAngleY=0;
    private double getAnchorAngleY=0;
    private final DoubleProperty angleX=new SimpleDoubleProperty(0);
    private final DoubleProperty angleY=new SimpleDoubleProperty(0);

    MediaPlayer mediaPlayer;


    private Group loadModel(URL url) {
        Group modelRoot = new Group();
        ObjModelImporter importer = new ObjModelImporter();

        importer.read(url);

        for (MeshView view : importer.getImport()) {
            modelRoot.getChildren().add(view);
        }

        return modelRoot;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(model1 ==null){
            File videoFile = new File("/home/diop/Musique/projetJavax2/src/main/resources/com/example/projetjavax/alcanes/video.mp4");
            Media media = new Media(videoFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaViewId.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
        }
    }

    @FXML
    void onActionDeconneexion(ActionEvent event) throws IOException {
        Session.removeAttribute("idUser");
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("login.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) idd.getScene().getWindow();


        currentStage.setScene(scene);

        currentStage.show();
    }


    @FXML
    void onActionHome(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("acceuil.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) idHomme.getScene().getWindow();


        currentStage.setScene(scene);
    }

    @FXML
    void onActionMethane(MouseEvent event) {
//        Acc.setVisible(false);

        homeId.setVisible(false);
        mathaneId.setVisible(true);
        ethaneId.setVisible(false);
        acethyleneId.setVisible(false);
        butheneId.setVisible(false);

        buthaneId.setVisible(false);
        mediaPlayer.stop();

        if (model1 != null) {
            // Supprimer le modèle actuel s'il existe
            pane.getChildren().remove(subscene);
            pane1.getChildren().remove(subscene1);
            pane2.getChildren().remove(subscene2);
            pane3.getChildren().remove(subscene3);
            pane4.getChildren().remove(subscene4);
            homeId.getChildren().remove(mediaViewId);
        }


        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-10.0);


        //model1 = loadModel(getClass().getResource("/stem/d2a/stem/model/CellAn/ImageToStl.com_mitochondria_-_cell_organelles/ImageToStl.com_mitochondria_-_cell_organelles.obj"));
        model1 = loadModel(HelloApplication.class.getResource("alcanes/methane/methane.obj"));


        subscene = new SubScene(model1, pane.getWidth(), pane.getHeight(), true, null);
        subscene.setCamera(camera);
        pane.getChildren().add(subscene);

//        subscene.setOnScroll(event2 ->{
//            double delta= event2.getDeltaY();
//            double scale =(delta>0)?0.8:1.1;
//            camera.setTranslateZ(camera.getTranslateZ()*scale);
//        } );

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

    @FXML
    void onActionEthane(MouseEvent event) {
//        Acc.setVisible(false);

        homeId.setVisible(false);
        mathaneId.setVisible(false);
        ethaneId.setVisible(true);
        acethyleneId.setVisible(false);
        butheneId.setVisible(false);

        buthaneId.setVisible(false);
        mediaPlayer.stop();
        if (model1 != null) {
            // Supprimer le modèle actuel s'il existe
            pane.getChildren().remove(subscene);
            pane1.getChildren().remove(subscene1);
            pane2.getChildren().remove(subscene2);
            pane3.getChildren().remove(subscene3);
            pane4.getChildren().remove(subscene4);
            homeId.getChildren().remove(mediaViewId);
        }



        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-10.0);


        //model1 = loadModel(getClass().getResource("/stem/d2a/stem/model/CellAn/ImageToStl.com_mitochondria_-_cell_organelles/ImageToStl.com_mitochondria_-_cell_organelles.obj"));
        model1 = loadModel(HelloApplication.class.getResource("alcanes/ethane/ImageToStl.com_ethane_eclipsed.obj"));

        subscene1 = new SubScene(model1, pane1.getWidth(), pane1.getHeight(), true, null);
        subscene1.setCamera(camera);
        pane1.getChildren().add(subscene1);

//        subscene1.setOnScroll(event2 ->{
//            double delta= event2.getDeltaY();
//            double scale =(delta>0)?0.8:1.1;
//            camera.setTranslateZ(camera.getTranslateZ()*scale);
//        } );

        Rotate xRotate;
        Rotate yRotate;
        model1.getTransforms().addAll(
                xRotate=new Rotate(0,Rotate.X_AXIS),
                yRotate=new Rotate(0,Rotate.Y_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        pane1.setOnMousePressed(event1->{
            anchorX=event1.getSceneX();
            anchorY=event1.getSceneY(); //recuperer les coordonnee qui sont cliquee
            anchorAngleX=angleX.get();
            AnchorAngleY=angleY.get();

        });


        pane1.setOnMouseDragged(event2 -> {
            angleX.set(anchorAngleX-(anchorY-event2.getSceneY()));
            angleY.set(AnchorAngleY+anchorX-event2.getSceneX());
        });

    }

    @FXML
    void onActionButhane(MouseEvent event) {
        homeId.setVisible(false);
        mathaneId.setVisible(false);
        ethaneId.setVisible(false);
        acethyleneId.setVisible(false);
        butheneId.setVisible(false);

        buthaneId.setVisible(true);
        mediaPlayer.stop();

        if (model1 != null) {
            // Supprimer le modèle actuel s'il existe
            pane.getChildren().remove(subscene);
            pane1.getChildren().remove(subscene1);
            pane2.getChildren().remove(subscene2);
            pane3.getChildren().remove(subscene3);
            pane4.getChildren().remove(subscene4);
            homeId.getChildren().remove(mediaViewId);
        }



        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-10.0);


        //model1 = loadModel(getClass().getResource("/stem/d2a/stem/model/CellAn/ImageToStl.com_mitochondria_-_cell_organelles/ImageToStl.com_mitochondria_-_cell_organelles.obj"));
        model1 = loadModel(HelloApplication.class.getResource("Alcane/Butane/ImageToStl.com_butane_rotation_smooth.obj"));

        subscene2 = new SubScene(model1, pane2.getWidth(), pane2.getHeight(), true, null);
        subscene2.setCamera(camera);
        pane2.getChildren().add(subscene2);

//        subscene1.setOnScroll(event2 ->{
//            double delta= event2.getDeltaY();
//            double scale =(delta>0)?0.8:1.1;
//            camera.setTranslateZ(camera.getTranslateZ()*scale);
//        } );

        Rotate xRotate;
        Rotate yRotate;
        model1.getTransforms().addAll(
                xRotate=new Rotate(0,Rotate.X_AXIS),
                yRotate=new Rotate(0,Rotate.Y_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        pane2.setOnMousePressed(event1->{
            anchorX=event1.getSceneX();
            anchorY=event1.getSceneY(); //recuperer les coordonnee qui sont cliquee
            anchorAngleX=angleX.get();
            AnchorAngleY=angleY.get();

        });


        pane2.setOnMouseDragged(event2 -> {
            angleX.set(anchorAngleX-(anchorY-event2.getSceneY()));
            angleY.set(AnchorAngleY+anchorX-event2.getSceneX());
        });

    }


    @FXML
    void onActionAcethylene(MouseEvent event) {
        homeId.setVisible(false);
        mathaneId.setVisible(false);
        ethaneId.setVisible(false);
        acethyleneId.setVisible(true);
        butheneId.setVisible(false);

        buthaneId.setVisible(false);
        mediaPlayer.stop();

        if (model1 != null) {
            // Supprimer le modèle actuel s'il existe
            pane.getChildren().remove(subscene);
            pane1.getChildren().remove(subscene1);
            pane2.getChildren().remove(subscene2);
            pane3.getChildren().remove(subscene3);
            pane4.getChildren().remove(subscene4);
            homeId.getChildren().remove(mediaViewId);
        }



        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-10.0);


        //model1 = loadModel(getClass().getResource("/stem/d2a/stem/model/CellAn/ImageToStl.com_mitochondria_-_cell_organelles/ImageToStl.com_mitochondria_-_cell_organelles.obj"));
        model1 = loadModel(HelloApplication.class.getResource("Alcene/Acethylene/ImageToStl.com_acetylene_ethyne_molecule.obj"));

        subscene3 = new SubScene(model1, pane3.getWidth(), pane3.getHeight(), true, null);
        subscene3.setCamera(camera);
        pane3.getChildren().add(subscene3);

//        subscene1.setOnScroll(event2 ->{
//            double delta= event2.getDeltaY();
//            double scale =(delta>0)?0.8:1.1;
//            camera.setTranslateZ(camera.getTranslateZ()*scale);
//        } );

        Rotate xRotate;
        Rotate yRotate;
        model1.getTransforms().addAll(
                xRotate=new Rotate(0,Rotate.X_AXIS),
                yRotate=new Rotate(0,Rotate.Y_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        pane3.setOnMousePressed(event1->{
            anchorX=event1.getSceneX();
            anchorY=event1.getSceneY(); //recuperer les coordonnee qui sont cliquee
            anchorAngleX=angleX.get();
            AnchorAngleY=angleY.get();

        });


        pane3.setOnMouseDragged(event2 -> {
            angleX.set(anchorAngleX-(anchorY-event2.getSceneY()));
            angleY.set(AnchorAngleY+anchorX-event2.getSceneX());
        });

    }

    @FXML
    void onActionButhene(MouseEvent event) {
        homeId.setVisible(false);
        mathaneId.setVisible(false);
        ethaneId.setVisible(false);
        acethyleneId.setVisible(false);
        butheneId.setVisible(true);
        buthaneId.setVisible(false);
        mediaPlayer.stop();

        if (model1 != null) {
            // Supprimer le modèle actuel s'il existe
            pane.getChildren().remove(subscene);
            pane1.getChildren().remove(subscene1);
            pane2.getChildren().remove(subscene2);
            pane3.getChildren().remove(subscene3);
            pane4.getChildren().remove(subscene4);
            homeId.getChildren().remove(mediaViewId);
        }



        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-10.0);


        //model1 = loadModel(getClass().getResource("/stem/d2a/stem/model/CellAn/ImageToStl.com_mitochondria_-_cell_organelles/ImageToStl.com_mitochondria_-_cell_organelles.obj"));
        model1 = loadModel(HelloApplication.class.getResource("Alcene/butene/ImageToStl.com_but-2-ene.obj"));

        subscene4 = new SubScene(model1, pane4.getWidth(), pane4.getHeight(), true, null);
        subscene4.setCamera(camera);
        pane4.getChildren().add(subscene4);

//        subscene1.setOnScroll(event2 ->{
//            double delta= event2.getDeltaY();
//            double scale =(delta>0)?0.8:1.1;
//            camera.setTranslateZ(camera.getTranslateZ()*scale);
//        } );

        Rotate xRotate;
        Rotate yRotate;
        model1.getTransforms().addAll(
                xRotate=new Rotate(0,Rotate.X_AXIS),
                yRotate=new Rotate(0,Rotate.Y_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        pane4.setOnMousePressed(event1->{
            anchorX=event1.getSceneX();
            anchorY=event1.getSceneY(); //recuperer les coordonnee qui sont cliquee
            anchorAngleX=angleX.get();
            AnchorAngleY=angleY.get();

        });


        pane4.setOnMouseDragged(event2 -> {
            angleX.set(anchorAngleX-(anchorY-event2.getSceneY()));
            angleY.set(AnchorAngleY+anchorX-event2.getSceneX());
        });
    }
}
