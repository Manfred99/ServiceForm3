package com.serviceform.serviceform.serviceform.state;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.serviceform.serviceform.serviceform.Credentials_DBAANDROID;
import com.serviceform.serviceform.serviceform.R;
import com.serviceform.serviceform.serviceform.Tracking.TrackingInsert;
import com.serviceform.serviceform.serviceform.Tracking.TrackingVariables;
import com.serviceform.serviceform.serviceform.process.ConnectionClass;
import com.serviceform.serviceform.serviceform.process.ViewProcess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewState extends Activity {

    public ViewProcess view = new ViewProcess();
    Credentials_DBAANDROID bdAndroid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_state);

        fillExpandibleList();
        final Button btn_delete;
        final ListView listView = findViewById(R.id.list_antState);
        btn_delete = (Button) findViewById(R.id.btn_deleteStates);



        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionClass connectionClass= new ConnectionClass();
                Connection connection=null;
                connection = view.conectedDatabase();
                /*try {
                    connection = connectionClass.createConnection("estudiantesrp", "estudiantesrp", "IF4100_B77436_2018", "163.178.173.148");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }*/
                if (connection==null){

                    Toast.makeText(ViewState.this, "Error de conección con BD",
                            Toast.LENGTH_SHORT).show();
                }else {
                    SelectState sele = new SelectState();
                    String values = sele.numberofState;
                    String queryRol = "Delete ["+bdAndroid.SERVER_DBAAndroid.getDatabase()+"].[dbo].[ServerStates] WHERE NumberofState=" + values;
                    String row = "";
                    try {
                        //prepara la conección para luego consultarla
                        Statement statementRol = connection.createStatement();
                        //ejecuta la consulta y obtiene resultado
                        ResultSet resultSetRol = statementRol.executeQuery(queryRol);

                        resultSetRol.next();
                        TrackingInsert tci = new TrackingInsert();
                        TrackingVariables trace = new TrackingVariables();
                        trace.serverUsed = "Development Server";
                        trace.userUsed = "scdv4001";//Cambia cuando llega a FTP
                        tci.createTraceHoursStuff(trace.serverUsed,trace.userUsed,"Delete a State from DB");
                        //row = ((String) resultSetRol.getObject()
                        //id_Role=((Number)resultSetRol.getObject(1)).intValue();
                    } catch (Exception ex) {

                        ex.printStackTrace();
                    }
                }
            }
        });
    }


    public void fillExpandibleList() {

        List<Map<String, String>> interestsList = new ArrayList<>();
        SelectState sele = new SelectState();
        String values = sele.numberofState;

        Connection connection = view.conectedDatabase();


        if (connection==null){

            Toast.makeText(ViewState.this, "Error de conección con BD",
                    Toast.LENGTH_SHORT).show();
        }else {
            String queryRol= "SELECT * FROM ["+bdAndroid.SERVER_DBAAndroid.getDatabase()+"].[dbo].[ServerStates] WHERE NumberofState="+values;
            Object row="";
            try{
                //prepara la conección para luego consultarla
                Statement statementRol= connection.createStatement();
                //ejecuta la consulta y obtiene resultado
                ResultSet resultSetRol = statementRol.executeQuery(queryRol);

                //resultSetRol.next();
                while(resultSetRol.next()){
                    row = resultSetRol.getObject("NameP");
                    String name = (String)row;
                    interestsList.add(createProcess("process", name));
                }


                //int id_Role=((Number)resultSetRol.getObject(1)).intValue();
            }catch (Exception ex){

                ex.printStackTrace();
            }


            SimpleAdapter simpleAdpterForListView =
                    new SimpleAdapter(this, interestsList, android.R.layout.simple_list_item_checked,
                            new String[] {"process"}, new int[] {android.R.id.text1});

            ListView lv = (ListView) findViewById(R.id.list_antState);

            lv.setAdapter(simpleAdpterForListView);
        }

    }


    private HashMap<String, String> createProcess(String key, String name) {
        HashMap<String, String> process = new HashMap<String, String>();
        process.put(key, name);
        return process;

    }
}
