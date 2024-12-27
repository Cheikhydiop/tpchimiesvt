package com.example.projetjavax;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnection {

    public Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception er_driver)
        {
            System.out.println("erreur driver :"+ er_driver.getMessage());
            er_driver.printStackTrace();
        }
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/schools", "root", "Diop-1234");
        }
        catch (Exception er_con ){
            er_con.printStackTrace();
        }
        return con;
    }
}
