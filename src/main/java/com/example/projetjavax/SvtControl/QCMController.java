package com.example.projetjavax.SvtControl;

import com.example.projetjavax.DatabaseConnection;
import com.example.projetjavax.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class QCMController implements Initializable{
    @FXML
    private CheckBox Resp1v;

    @FXML
    private CheckBox Resp2v;

    @FXML
    private CheckBox Resp3f;

    @FXML
    private CheckBox Resp4v;

    @FXML
    private CheckBox Resp5f;

    @FXML
    private CheckBox Resp6f;

    @FXML
    private Button btnVerifierQCM1;



    @FXML
    public Label valideOk;

    @FXML
    public Label valideKO;

    int id=(int) Session.getAttribute("idUser");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        initMousseControl(anchorId,sphere1);

        if(valide()){
            btnVerifierQCM1.setVisible(false);
            //desactiver les checkbox par defaut
            Resp1v.setDisable(true);
            Resp2v.setDisable(true);
            Resp3f.setDisable(true);
            Resp4v.setDisable(true);
            Resp5f.setDisable(true);
            Resp6f.setDisable(true);
            //selectionner par defaut
            Resp1v.setSelected(true);
            Resp2v.setSelected(true);
            Resp4v.setSelected(true);
            if(valideOk!=null)
                  valideOk.setVisible(true);
        }

    }

    @FXML
    public void onActionValider(ActionEvent actionEvent) {
        boolean validV=(Resp1v.isSelected()&&Resp2v.isSelected()&&Resp4v.isSelected());
        boolean validF=(!Resp3f.isSelected()&&!Resp5f.isSelected()&&!Resp6f.isSelected());

        if(validV&&validF){
            validateQcm();
            //changer la visibiliter des button
            btnVerifierQCM1.setVisible(!btnVerifierQCM1.isVisible());
            valideKO.setVisible(false);
            //desactives les checkbox
            Resp1v.setDisable(true);
            Resp2v.setDisable(true);
            Resp3f.setDisable(true);
            Resp4v.setDisable(true);
            Resp5f.setDisable(true);
            Resp6f.setDisable(true);
        }else if(Resp3f.isSelected()||Resp5f.isSelected()||Resp6f.isSelected()) {
            valideKO.setVisible(true);
        }
    }

    //metre a jour l'information valide dans la table QCM
    public  void validateQcm(){
        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectionDB= connectNow.getConnection();


        String verify="update qcm set valider='oui' where etape='qcm1' and module='svt' and userId="+id;

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


        String verify="select count(*) from qcm where valider='oui' and etape='qcm1' and module='svt' and userId="+id;

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


}
