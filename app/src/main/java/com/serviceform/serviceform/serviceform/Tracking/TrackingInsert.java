package com.serviceform.serviceform.serviceform.Tracking;

import com.serviceform.serviceform.serviceform.Credentials_DBA;
//mport com.serviceform.serviceform.serviceform.Credentials_DBAAndroid;
import com.serviceform.serviceform.serviceform.Credentials_DBAANDROID;
import com.serviceform.serviceform.serviceform.process.ConnectionClass;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.StringTokenizer;

public class TrackingInsert {
    //Credentials_DBAAndroid credentials_dba;
    Credentials_DBA credentials_dbaForm;
    Credentials_DBAANDROID credentials_dbaAndroid;

    public Connection conectedDatabase(){
        ConnectionClass connectionClass= new ConnectionClass();
        Connection connection=null;
        try {
            connection = connectionClass.createConnection(credentials_dbaAndroid.SERVER_DBAAndroid.getUser(), credentials_dbaAndroid.SERVER_DBAAndroid.getPassword(), credentials_dbaAndroid.SERVER_DBAAndroid.getDatabase(), credentials_dbaAndroid.SERVER_DBAAndroid.getServer());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public void insertTrace(Trace trace) {

        try {
            String sqlInsert = "INSERT INTO ["+credentials_dbaAndroid.SERVER_DBAAndroid.getDatabase()+"].[dbo].[Tracking] (NameServer, UserAppEmail, DescriptionQuery, "
                    + "EntryTime, ExitTime, DateUser, UserUsed) VALUES ('"+trace.getServidor()+"', '"+trace.getUserEmail()+"'" +
                    ", '"+trace.getDescriptionBinnacle()+"', '"+trace.getEntryTime()+"', '"+
                    trace.getExitTime()+"', '"+trace.getDateUser()+"', '"+trace.getUserUsed()+"')";
            //"SELECT [NumberofState] FROM ["+bdAndroid.SERVER_DBAAndroid.getDatabase()+"].[dbo].[ServerStates] WHERE NumberofState="+i;
            //System.out.println((credentials_dba.SERVER_DBA.getUser()+ credentials_dba.SERVER_DBA.getPassword()+ credentials_dba.SERVER_DBA.getDatabase()+ credentials_dba.SERVER_DBA.getServer()));

            Connection con=conectedDatabase();
            if (con==null){

                System.out.println("Error de conección con BD");
            }else{
                Statement statementRol= con.createStatement();
                //ejecuta la consulta y obtiene resultado
                ResultSet resultSetRol = statementRol.executeQuery(sqlInsert);
                con.close();
                System.out.println("#######################EXIIIIIIIIITTTTTTTTTTTTT");
            }
        } catch (Exception ex) {
            System.out.println("Error!!!!");
        }

    }
    public void createTraceHoursStuff(String server, String userUsed, String action){
        TrackingVariables trackingVariables = new TrackingVariables();
        Date date = new Date();
        String dateString = date.toString();
        StringTokenizer st = new StringTokenizer(dateString, " ");
        int i = 0;
        String timeStart = trackingVariables.timeStart;
        String userEmail = trackingVariables.userEmail;
        String timeExit = "";
        //Mon Dec 03 14:17:43 CST 2018
        String day="";
        String month="";
        String year="";
        while(st.hasMoreTokens()){
            if(i==1){
                month = st.nextToken();
            }else if(i==2){
                day=st.nextToken();
            }else if(i==3){
                timeExit += st.nextToken()+" ";
            }else if(i==5){
                year = st.nextToken();
            }else{
                st.nextToken();
            }
            i++;
        }
        System.out.println(day+"/"+month+"/"+year);
        Trace tracking = new Trace(0, userEmail, server,
                action, timeStart, timeExit,day+"/"+month+"/"+year, userUsed);

        insertTrace(tracking);

    }
    public String getActualTime(){
        int i = 0;
        String time="";
        Date date = new Date();
        String dateString = date.toString();
        StringTokenizer st = new StringTokenizer(dateString, " ");
        while(st.hasMoreTokens()){
            if(i==3){
                time += st.nextToken()+" ";
            }else{
                st.nextToken();
            }
            i++;
        }
        return time;
    }
}
