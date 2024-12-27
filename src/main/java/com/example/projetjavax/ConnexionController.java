package com.example.projetjavax;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ConnexionController implements Initializable {

    @FXML
    public Button loginId;

    @FXML
    public Label idSignup;

    @FXML
    public TextField emailText;

    @FXML
    public TextField passwordText;

    @FXML
    public Label loginMessageLabel;

    private Boolean cont=false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    @FXML
    public void onActionSignup(MouseEvent mouseEvent) throws IOException {

    }


    public  void validateLogin(){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB= connectNow.getConnection();


        String verifyLogin="select count(1) from users where email='"+emailText.getText()+"' and password='"+passwordText.getText()+"'";

        try{
            Statement statement= connectionDB.createStatement();
            ResultSet queryResult=statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if(queryResult.getInt(1)==1){
                   cont=true;
                }else {
                    loginMessageLabel.setText("Login invalid! please try again");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void onActionLogin(ActionEvent actionEvent) throws IOException {
        validateLogin();
        if (cont){
            FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("acceuil.fxml"));

            Parent root= fxmlLoader.load();

            Scene scene=new Scene(root);

            //obtenez le scene  controlleur de la 2eme page
//        HomeCtroller homeCtroller= fxmlLoader.getController();

            //accedez a la scene actuelle et remplacer la par la nouvelle scene
            Stage currentStage= (Stage) loginId.getScene().getWindow();


            currentStage.setScene(scene);
        }
    }
}
