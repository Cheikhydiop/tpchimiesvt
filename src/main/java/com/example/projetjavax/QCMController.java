package com.example.projetjavax;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Sphere;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class QCMController implements Initializable{
    @FXML
    public CheckBox Resp1;

    @FXML
    public CheckBox Resp2;

    @FXML
    public CheckBox Resp3;
    @FXML
    public Button buttonValide1;



    @FXML
    public Label valideOk;

    @FXML
    public Label valideKO;

    int id=(int) Session.getAttribute("idUser");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        initMousseControl(anchorId,sphere1);

        if(valide()){
            buttonValide1.setVisible(false);
            Resp1.setDisable(true);
            Resp1.setSelected(true);
            Resp2.setDisable(true);
            Resp3.setDisable(true);
            if(valideOk!=null)
                  valideOk.setVisible(true);
        }

    }

    @FXML
    public void onActionValider(ActionEvent actionEvent) {

        if(Resp1.isSelected()&&!Resp2.isSelected()&&!Resp3.isSelected()){
            validateQcm();
            //changer la visibiliter des button
            buttonValide1.setVisible(!buttonValide1.isVisible());
            valideKO.setVisible(false);
            //desactives les checkbox
            Resp1.setDisable(true);
            Resp2.setDisable(true);
            Resp3.setDisable(true);


        }else if(Resp2.isSelected()||Resp3.isSelected()) {
            valideKO.setVisible(true);
        }
    }

    //metre a jour l'information valide dans la table QCM
    public  void validateQcm(){


        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB= connectNow.getConnection();

        String verify= "update qcm set valider='oui' where etape='qcm1' and module='chimie' and userId=" + id;

        try{
            Statement statement= connectionDB.createStatement();
            int queryResult=statement.executeUpdate(verify);

           if (queryResult==1){
                valideOk.setVisible(!valideOk.isVisible());
           }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    //verifier si le QCM est valide dans la base de donnees
    public  boolean valide(){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB= connectNow.getConnection();


        String verify="select count(*) from qcm where valider='oui' and module='chimie' and etape='qcm1' and userId="+id;

        try{
            Statement statement= connectionDB.createStatement();
//            int queryResult=statement.executeUpdate(verify);
            ResultSet queryResult=statement.executeQuery(verify);

            while (queryResult.next()){
                if(queryResult.getInt(1)==1){
                   return  true;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return false;
    }

    @FXML
    public void onActionNextQcm(ActionEvent actionEvent) {
    }

}
