package com.example.projetjavax;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class LoginController {
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

    private int idUser;


    public  void validateLogin(){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB= connectNow.getConnection();


        String verifyLogin="select count(1) from users where email='"+emailText.getText()+"' and password='"+passwordText.getText()+"'";

        try{
            Statement statement= connectionDB.createStatement();
            ResultSet queryResult=statement.executeQuery(verifyLogin);

            if(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    String queryID="select id from users where email='"+emailText.getText()+"' and password='"+passwordText.getText()+"'";
                    try {
                        Statement statementID= connectionDB.createStatement();
                        ResultSet queryResultID=statementID.executeQuery(queryID);
                        if (queryResultID.next()){
                            idUser= queryResultID.getInt(1);
                            cont=true;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        e.getCause();
                    }
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
    public void onActionRedirectRegister(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("inscription.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) loginId.getScene().getWindow();

        currentStage.setScene(scene);
    }

    @FXML
    public void onActionLogin(ActionEvent actionEvent) throws IOException {
        validateLogin();
        if (cont){
            //creation de la session
            Session.setAttribute("idUser",idUser);
            //redirection a la page d'acceuil
            FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("acceuil.fxml"));

            Parent root= fxmlLoader.load();

            Scene scene=new Scene(root);

            Stage currentStage= (Stage) loginId.getScene().getWindow();


            currentStage.setScene(scene);
        }
    }
}
