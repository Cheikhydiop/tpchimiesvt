package com.example.projetjavax;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class IncriptionController {

    @FXML
    public Button signupId;
    @FXML
    public TextField nomText;
    @FXML
    public TextField prenomText;
    @FXML
    public TextField emailText;
    @FXML
    public TextField passwordText;
    @FXML
    public TextField passwordConfirmText;

    @FXML
    public Label MessageLabel;

    private int idUser;

    String nom;
    String prenom;
    String email;
    String password;
    String passwordConfirm;

    @FXML
    public void onActionSignup(ActionEvent actionEvent) throws IOException {
        nom=nomText.getText();
        prenom=prenomText.getText();
        email=emailText.getText();
        password=passwordText.getText();
        passwordConfirm=passwordConfirmText.getText();

        if(nom.isEmpty()&&prenom.isEmpty()&&email.isEmpty()&&password.isEmpty()&&passwordConfirm.isEmpty()){
            MessageLabel.setText("Vous devez saisir tous les champs");
        }else if(!Objects.equals(password, passwordConfirm)){
            MessageLabel.setText("Mot de passe ne correspond pas");
        }else {
            signup();
        }
    }

    @FXML
    public void onActionRedirectLogin(ActionEvent actionEvent) throws IOException {
        redirectLogin();
    }

    private void redirectLogin() throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("login.fxml"));

        Parent root= fxmlLoader.load();

        Scene scene=new Scene(root);

        Stage currentStage= (Stage) signupId.getScene().getWindow();

        currentStage.setScene(scene);
    }
    private void signup(){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB= connectNow.getConnection();



        String verifyLogin="select count(1) from users where email='"+email+"'";

        try{
            Statement statement= connectionDB.createStatement();
            ResultSet queryResult=statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                //verification si le compte exist
                if(queryResult.getInt(1)==1){
                    MessageLabel.setText("Ce compte existe dejat!");
                }else {
                    //creation du compte user
                    String verify="INSERT INTO `users`(`nom`, `prenom`, `email`, `password`) " +
                            "VALUES ('"+nom+"','"+prenom+"','"+email+"','"+password+"')";

                    try{
                        Statement statement2= connectionDB.createStatement();
                        int queryInsert=statement2.executeUpdate(verify);

                        if(queryInsert==1){
                            //recuperation du dernier id insert
                            String queryID="SELECT LAST_INSERT_ID() as userId";

                            try {
                                Statement statementID= connectionDB.createStatement();
                                ResultSet queryResultID=statementID.executeQuery(queryID);

                                if(queryResultID.next()) {
                                        idUser= Integer.parseInt(queryResultID.getString("userId"));
                                        //insertion du 1ere qcm svt
                                        String verifyQcm1="INSERT INTO `qcm`(`module`, `etape`, `valider`, `userId`) VALUES ('svt','qcm1','non','"+idUser+"')";
                                        try {
                                            Statement statementQcm1= connectionDB.createStatement();
                                            int queryQcm1=statementQcm1.executeUpdate(verifyQcm1);
                                            if(queryQcm1==1){
                                                //insertion du 2ere qcm svt
                                                String verifyQcm2="INSERT INTO `qcm`(`module`, `etape`, `valider`, `userId`) VALUES ('svt','qcm2','non','"+idUser+"')";
                                                try {
                                                    Statement statementQcm2= connectionDB.createStatement();
                                                    int queryQcm2=statementQcm2.executeUpdate(verifyQcm2);
                                                    if(queryQcm2==1){
                                                        //insertion du 1ere qcm svt
                                                        String verifyQcm3="INSERT INTO `qcm`(`module`, `etape`, `valider`, `userId`) VALUES ('svt','qcm3','non','"+idUser+"')";
                                                        try {
                                                            Statement statementQcm3= connectionDB.createStatement();
                                                            int queryQcm3=statementQcm3.executeUpdate(verifyQcm3);
                                                            if(queryQcm3==1){
                                                                String verifyQcm4="INSERT INTO `qcm`(`module`, `etape`, `valider`, `userId`) VALUES ('chimie','qcm1','non','"+idUser+"')";
                                                                try {
                                                                    Statement statementQcm4= connectionDB.createStatement();
                                                                    int queryQcm4=statementQcm4.executeUpdate(verifyQcm4);
                                                                    if(queryQcm4==1){
                                                                        //insertion du 2ere qcm svt
                                                                        String verifyQcm5="INSERT INTO `qcm`(`module`, `etape`, `valider`, `userId`) VALUES ('chimie','qcm2','non','"+idUser+"')";
                                                                        try {
                                                                            Statement statementQcm5= connectionDB.createStatement();
                                                                            int queryQcm5=statementQcm5.executeUpdate(verifyQcm5);
                                                                            if(queryQcm5==1){
                                                                                //insertion du 1ere qcm svt
                                                                                String verifyQcm6="INSERT INTO `qcm`(`module`, `etape`, `valider`, `userId`) VALUES ('chimie','qcm3','non','"+idUser+"')";
                                                                                try {
                                                                                    Statement statementQcm6= connectionDB.createStatement();
                                                                                    int queryQcm6=statementQcm6.executeUpdate(verifyQcm6);
                                                                                    if(queryQcm6==1){
                                                                                        //redirection dans login
                                                                                        redirectLogin();
                                                                                    }
                                                                                }catch (Exception e){
                                                                                    e.printStackTrace();
                                                                                    e.getCause();
                                                                                }
                                                                            }
                                                                        }catch (Exception e){
                                                                            e.printStackTrace();
                                                                            e.getCause();
                                                                        }
                                                                    }
                                                                }catch (Exception e){
                                                                    e.printStackTrace();
                                                                    e.getCause();
                                                                }
                                                            }
                                                        }catch (Exception e){
                                                            e.printStackTrace();
                                                            e.getCause();
                                                        }
                                                    }
                                                }catch (Exception e){
                                                    e.printStackTrace();
                                                    e.getCause();
                                                }
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                            e.getCause();
                                        }
                                }

                            }catch (Exception e){
                                e.printStackTrace();
                                e.getCause();
                            }

                        } //fin
                    }catch (Exception e){
                        e.printStackTrace();
                        e.getCause();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
}
