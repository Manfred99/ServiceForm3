package com.serviceform.serviceform.serviceform.proves;

import com.serviceform.serviceform.serviceform.Credentials_DBA;
import com.serviceform.serviceform.serviceform.MainActivity;
import com.serviceform.serviceform.serviceform.Tracking.ConnectionToServerDBA;
import com.serviceform.serviceform.serviceform.Tracking.Trace;
import com.serviceform.serviceform.serviceform.Tracking.TrackingInsert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Proveclass {
    static Credentials_DBA credentials_dba;
    public static void main(String[] args){

        try{

            //Connection connection = (Connection) stablishConectionDataBase();
            //Connection connection = (Connection) ConnectionToServerDBA.stablishConectionDataBase();
            Connection connection;
         ConnectionToServerDBA connectionToServerDBA = new ConnectionToServerDBA();

         connection = (Connection) connectionToServerDBA.stablishConectionDataBase();
            String queryEmail= "SELECT Email FROM UserApp WHERE Username='Manfred' AND Password='123'";
            //prepara la conección para luego consultarla
            Statement statementEmail= connection.createStatement();
            //ejecuta la consulta y obtiene resultado
            ResultSet resultSetEmail = statementEmail.executeQuery(queryEmail);

            resultSetEmail.next();
            String email=(resultSetEmail.getObject(1)).toString();
            System.out.println("It works!!!!!!  "+email);
        }catch (Exception ex){
            System.out.println("It doesn't Works");
        }


    }
    public static Object stablishConectionDataBase() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //jdbc:sqlserver://192.168.1.9:1433;databaseName=DevFormServerControllingDataBase [SA on db_accessadmin]
            String connectionURL = "jdbc:sqlserver://192.168.43.53:1433;databaseName=DevFormServerControllingDataBase;user=SA;password=sql_devdata4001";
            Connection con = DriverManager.getConnection(connectionURL);
            return con;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Proveclass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Proveclass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
