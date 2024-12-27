package com.example.projetjavax;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class courController implements Initializable {
    @FXML
    private Button idHomme;

    @FXML
    private ImageView imagepdfId;

    //Gestion du pdf

    private PDDocument document;
    private PDFRenderer pdfRenderer;
    private int currentPage = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Charger le document PDF
        try {
            document = PDDocument.load(new File("C:\\Users\\morlaye\\Documents\\JAVAFX\\chimie.pdf"));
            pdfRenderer = new PDFRenderer(document);
            showPage();
//            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void nextPage(ActionEvent event) {
        if (currentPage < document.getNumberOfPages() - 1) {
            currentPage++;
            showPage();
//            document.close();
        }
    }

    @FXML
    void onActionDeconnexion(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("login.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) idHomme.getScene().getWindow();


        currentStage.setScene(scene);
    }

    @FXML
    void onActionHomme(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("acceuil.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) idHomme.getScene().getWindow();


        currentStage.setScene(scene);
    }

    @FXML
    void prevPage(ActionEvent event) {
        if (currentPage > 0) {
            currentPage--;
            showPage();
        }
    }

    private void showPage() {
        try {
            BufferedImage image = pdfRenderer.renderImage(currentPage);
            Image fxImage = SwingFXUtils.toFXImage(image, null);


            imagepdfId.setImage(fxImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
