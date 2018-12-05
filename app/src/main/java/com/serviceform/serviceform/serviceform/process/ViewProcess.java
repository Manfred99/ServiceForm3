package com.serviceform.serviceform.serviceform.process;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.serviceform.serviceform.serviceform.Credentials_DBAANDROID;
import com.serviceform.serviceform.serviceform.R;
import com.serviceform.serviceform.serviceform.Tracking.TrackingInsert;
import com.serviceform.serviceform.serviceform.Tracking.TrackingVariables;
import com.serviceform.serviceform.serviceform.state.SelectState;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewProcess extends Activity {

    public static ArrayList<String> procesos = null;
    public static String itemSelectedInterests = "", list_of_states="";
    Credentials_DBAANDROID bdAndroid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_process);

        final Button save, refresh, loadProcess;

        save = (Button) findViewById(R.id.btn_saveState);
        refresh = (Button) findViewById(R.id.btn_refresh);
        loadProcess = (Button) findViewById(R.id.btn_loadState);

        fillExpandibleList();
        final ListView listView = findViewById(R.id.list_viewProcess);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String message = "";
                message+=listView.getItemAtPosition(position).toString();
                int startPosition = 9;
                while(startPosition<message.length()-1){
                    itemSelectedInterests+=message.charAt(startPosition);
                    startPosition++;
                }
                Toast.makeText(ViewProcess.this, "Has seleccionado: "+itemSelectedInterests, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent( ViewProcess.this, PropertiesProcess.class);

                Bundle bundle=new Bundle();
                bundle.putString("NAME", "Properties");

                //coloca el mensaje que la actividad va a transmitir
                intent.putExtras(bundle);


                //hace el paso de actividades

                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(procesos.size()>0) {
                    insertStates(procesos);
                    TrackingInsert tci = new TrackingInsert();
                    TrackingVariables trace = new TrackingVariables();
                    trace.serverUsed = "Development Server";
                    trace.userUsed = "scdv4001";//Cambia cuando llega a FTP
                    tci.createTraceHoursStuff(trace.serverUsed,trace.userUsed,"Save List Process in DB");
                }else {
                    Toast.makeText(ViewProcess.this, "No existen procesos", Toast.LENGTH_LONG).show();
                }
                }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView lv = (ListView) findViewById(R.id.list_viewProcess);

                lv.setAdapter(null);
                Process_Admin processA = new Process_Admin();
                processA.loadProcessToServer();
                TrackingInsert tci = new TrackingInsert();
                TrackingVariables trace = new TrackingVariables();
                trace.serverUsed = "Development Server";
                trace.userUsed = "scdv4001";//Cambia cuando llega a FTP
                tci.createTraceHoursStuff(trace.serverUsed,trace.userUsed,"Refresh List Process");
                Intent intent=new Intent( ViewProcess.this, ViewProcess.class);

                Bundle bundle=new Bundle();
                bundle.putString("NAME", "Procesos");

                //coloca el mensaje que la actividad va a transmitir
                intent.putExtras(bundle);


                //hace el paso de actividades

                startActivity(intent);
                Toast.makeText(ViewProcess.this, "Process", Toast.LENGTH_LONG).show();
            }
        });

        loadProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameString="";// = username.getText().toString();
                String passwordString="";// = password.getText().toString();

                ConnectionClass connectionClass= new ConnectionClass();
                Connection connection=null;
                connection=conectedDatabase();
                /*try {
                    connection = connectionClass.createConnection("estudiantesrp", "estudiantesrp", "IF4100_B77436_2018", "163.178.173.148");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }*/


                if (connection==null){

                    Toast.makeText(ViewProcess.this, "Error de conección con BD",
                            Toast.LENGTH_SHORT).show();
                }else{

                    String queryRol= "SELECT [NumberofState] FROM ["+bdAndroid.SERVER_DBAAndroid.getDatabase()+"].[dbo].[ServerStates] ORDER BY NumberofState desc";
                    int numberState=0;
                    try{
                        //prepara la conección para luego consultarla
                        Statement statementRol= connection.createStatement();
                        //ejecuta la consulta y obtiene resultado
                        ResultSet resultSetRol = statementRol.executeQuery(queryRol);

                        resultSetRol.next();
                        numberState=((Number)resultSetRol.getObject(1)).intValue();
                    }catch (Exception ex){

                        ex.printStackTrace();
                    }
                    list_of_states= "";
                    for(int i = numberState;i>0;i--){
                        String queryRol1= "SELECT [NumberofState] FROM ["+bdAndroid.SERVER_DBAAndroid.getDatabase()+"].[dbo].[ServerStates] WHERE NumberofState="+i;
                        try{
                            //prepara la conección para luego consultarla
                            Statement statementRol= connection.createStatement();
                            //ejecuta la consulta y obtiene resultado
                            ResultSet resultSetRol = statementRol.executeQuery(queryRol1);

                            resultSetRol.next();
                            list_of_states+=((Number)resultSetRol.getObject(1)).intValue()+"-";
                        }catch (Exception ex){

                            ex.printStackTrace();
                        }
                    }
                }
                TrackingInsert tci = new TrackingInsert();
                TrackingVariables trace = new TrackingVariables();
                trace.serverUsed = "Development Server";
                trace.userUsed = "scdv4001";//Cambia cuando llega a FTP
                tci.createTraceHoursStuff(trace.serverUsed,trace.userUsed,"Load List Process");
                Intent intent=new Intent( ViewProcess.this, SelectState.class);

                Bundle bundle=new Bundle();
                bundle.putString("NAME", "Procesos");

                //coloca el mensaje que la actividad va a transmitir
                intent.putExtras(bundle);


                //hace el paso de actividades

                startActivity(intent);
                //Toast.makeText(ViewProcess.this, "Load State", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void fillExpandibleList() {

        List<Map<String, String>> interestsList = new ArrayList<>();
        int conta=0, numberProcess=0;
        List_Process list = new List_Process();
        String values = list.listOfProcess;
        procesos = new ArrayList<>();
        String nameP="";
        boolean retVal=false;
        for(int i =0; i < values.length();i++){
            char letter = values.charAt(i);
            if(conta==2 && !retVal){
                interestsList.add(createProcess("process", nameP));
                retVal=true;
                nameP+=""+letter;
            }else if(conta==7 || i==values.length()-1){
                procesos.add(nameP);
                nameP=""+letter;
                conta=0;
            }else{

                if(letter=='\n'){
                    conta++;
                    nameP+=" ";
                    retVal=false;
                }else
                    nameP+=letter;
            }
        }

        SimpleAdapter simpleAdpterForListView =
                new SimpleAdapter(this, interestsList, android.R.layout.simple_list_item_checked,
                        new String[] {"process"}, new int[] {android.R.id.text1});

        ListView lv = (ListView) findViewById(R.id.list_viewProcess);

        lv.setAdapter(simpleAdpterForListView);

    }

    public Connection conectedDatabase(){
        ConnectionClass connectionClass= new ConnectionClass();
        Connection connection=null;
        try {
            connection = connectionClass.createConnection(bdAndroid.SERVER_DBAAndroid.getUser(), bdAndroid.SERVER_DBAAndroid.getPassword(), bdAndroid.SERVER_DBAAndroid.getDatabase(), bdAndroid.SERVER_DBAAndroid.getServer());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertStates(ArrayList<String> list_process){
        ConnectionClass connectionClass= new ConnectionClass();
        Connection connection=null;
        PropertiesProcess properties = new PropertiesProcess();
        List_Process list = new List_Process();
        connection=conectedDatabase();
        /*try {
            connection= conectedDatabase();
            //connection = connectionClass.createConnection("estudiantesrp", "estudiantesrp", "IF4100_B77436_2018", "163.178.173.148");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/


        if (connection==null){

            Toast.makeText(ViewProcess.this, "Error de conección con BD",
                    Toast.LENGTH_SHORT).show();
        }else{
            int i=0;
            String queryRol= "SELECT [NumberofState] FROM ["+bdAndroid.SERVER_DBAAndroid.getDatabase()+"].[dbo].[ServerStates] ORDER BY NumberofState DESC";
            int numberState=0;
            try{
                //prepara la conección para luego consultarla
                Statement statementRol= connection.createStatement();
                //ejecuta la consulta y obtiene resultado
                ResultSet resultSetRol = statementRol.executeQuery(queryRol);

                resultSetRol.next();
                numberState=((Number)resultSetRol.getObject(1)).intValue();
            }catch (Exception ex){

                ex.printStackTrace();
            }
            numberState++;
            while(i<list_process.size()){
                String register =list_process.get(i);
                String pid = properties.idfindProcess(register);
                String name = properties.getId(register, 1);
                String realMemory  = properties.getId(register, 9);
                String virtualMemory = properties.getId(register, 5);
                String cpu = properties.getId(register, 11);
                String priority = properties.getId(register, 3);
                String time = properties.getId(register, 7);

                String query= "INSERT INTO ["+bdAndroid.SERVER_DBAAndroid.getDatabase()+"].[dbo].[ServerStates] (ServerName, DateState, PID,NameP, RealMemory, VirtualMemory, CPU, PriorityP, TimeExecuting, NumberofState) "+
                        "VALUES ('"+list.userName+"', getDate(), '"+pid+"', '"+name+"', '"+realMemory+"','"+virtualMemory+"','"+cpu+"', '"+priority+"','"+time+"', "+numberState+")";
                try{
                    //prepara la conección para luego insertar registro
                    Statement statement= connection.createStatement();
                    //ejecuta la consulta y obtiene resultado
                    ResultSet resultSet = statement.executeQuery(query);

                    //pregunta si la consulta trajo resultados
                    if (resultSet.next()){

                        Toast.makeText(ViewProcess.this, "Datos Guardados",
                                Toast.LENGTH_SHORT).show();


                    }else {


                        Toast.makeText(ViewProcess.this, "ERROR!!!",
                                Toast.LENGTH_SHORT).show();

                    }


                }catch (Exception ex){

                    ex.printStackTrace();
                }
                i++;
            }

        }

    }


    private HashMap<String, String> createProcess(String key, String name) {
        HashMap<String, String> process = new HashMap<String, String>();
        process.put(key, name);
        return process;

    }
}
