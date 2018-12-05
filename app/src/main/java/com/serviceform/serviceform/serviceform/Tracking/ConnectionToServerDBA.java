package com.serviceform.serviceform.serviceform.Tracking;

import com.serviceform.serviceform.serviceform.Credentials_DBA;
import com.serviceform.serviceform.serviceform.proves.Proveclass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionToServerDBA {
    static Credentials_DBA credentials;
    public  Object stablishConectionDataBase() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //jdbc:sqlserver://192.168.1.9:1433;databaseName=DevFormServerControllingDataBase [SA on db_accessadmin]
            String connectionURL = "jdbc:sqlserver://"+credentials.SERVER_DBA.getServer()+":1433;databaseName="+
                    credentials.SERVER_DBA.getDatabase()+
                    ";user="+credentials.SERVER_DBA.getUser()+";password="+credentials.SERVER_DBA.getPassword();
            Connection con = DriverManager.getConnection(connectionURL);
            return con;
        } catch (Exception ex) {
            Logger.getLogger(Proveclass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
