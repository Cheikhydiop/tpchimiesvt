package com.example.projetjavax;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AcceuilController implements Initializable {


    @FXML
    private Label messageLabel;

    @FXML
    private Button idd;

    @FXML
    public Button idGermination;

    @FXML
    private Button btnChimeId;

    int id=(int) Session.getAttribute("idUser");


    @FXML
    void onActionDeconnexion(ActionEvent event) throws IOException {
        Session.removeAttribute("idUser");
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("login.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) idd.getScene().getWindow();


        currentStage.setScene(scene);

        currentStage.show();
    }

    @FXML
    void onActionHomChimie(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("homeChimie.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) btnChimeId.getScene().getWindow();

        currentStage.setScene(scene);
    }

    @FXML
    void onActionHomSvt(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("SVT/homeSvt.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) idGermination.getScene().getWindow();

        currentStage.setScene(scene);
    }


    @FXML
    public void onActionHomme(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("acceuil.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) idGermination.getScene().getWindow();


        currentStage.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB= connectNow.getConnection();

        String verify= "select * from users where id="+id;

        try{
            Statement statement= connectionDB.createStatement();

            ResultSet R=statement.executeQuery(verify);

            if(R.next()){
                messageLabel.setText("Bien venue "+R.getString("prenom")+"  "+R.getString("nom"));
            }
            R.close();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
