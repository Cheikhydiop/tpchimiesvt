package com.example.projetjavax;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private Scene currentPageScene;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        navigateTo("inscription.fxml");
    }

    public void navigateTo(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlPath));
            Parent root = loader.load();
            currentPageScene = new Scene(root);

            // Charger le logo au démarrage
            Image logoImage = new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("logo.png")));
            primaryStage.getIcons().add(logoImage);

            primaryStage.setScene(currentPageScene);
            primaryStage.setFullScreen(true); // Activer le mode plein écran
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
