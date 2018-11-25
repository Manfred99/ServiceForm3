package com.serviceform.serviceform.serviceform.files;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.serviceform.serviceform.serviceform.Credentials_DEVServer;
import com.serviceform.serviceform.serviceform.Credentials_QATServer;
import com.serviceform.serviceform.serviceform.R;
import com.serviceform.serviceform.serviceform.SshClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.serviceform.serviceform.serviceform.R.id.editText;

public class Files_Admin extends Activity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    Spinner spinner;
    Spinner spinner_Permits;
    String itemSelectedInterests="";
    private List<String> servers;
    private List<String> permits;
    List<FileInfo> files;
    private static String chainOfPermits;
    private static String nameOfFile;
    private static String userUsed;
    public static String nameServer;
    public static String namePermits="";
    Credentials_DEVServer credentials_devServer;
    Credentials_QATServer credentialsQatServer;
    List<Map<String, String>> interestsList;




    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files__admin);
        nameServer="DEV_FormServerControlling";
        final SshClient sshClient = new SshClient();
        final EditText editNameFile;
        editNameFile = (EditText) findViewById(editText);


            interestsList = new ArrayList<Map<String,String>>();
            fillList();

        final ListView listView = findViewById(R.id.listTemp);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String message = "";
                message+=listView.getItemAtPosition(position).toString();
                //Toast.makeText(Files_Admin.this, "Has seleccionado UNOOOO: "+message, Toast.LENGTH_SHORT).show();
                itemSelectedInterests="";
                int startPosition = 29;
                while(startPosition<message.length()-1){
                    itemSelectedInterests+=message.charAt(startPosition);
                    startPosition++;
                }
                /**
                String word="";
                int i = 1;
                while(i<itemSelectedInterests.length()){
                    word+=itemSelectedInterests.charAt(i);
                    i++;
                }
                itemSelectedInterests = word;
                 **/
                Toast.makeText(Files_Admin.this, "Has seleccionado: "+itemSelectedInterests, Toast.LENGTH_SHORT).show();
            }
        });

        spinner = (Spinner) findViewById(R.id.spinnerTemp);

        spinner.setOnItemSelectedListener(this);


        servers = new ArrayList<String>();
        servers.add("QAT_UATFormServerControllingDataBase");
        servers.add("DEV_FormServerControlling");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, servers);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(dataAdapter);




        spinner_Permits = (Spinner) findViewById(R.id.spinnerPermits);
        permits = new ArrayList<String>();
        permits.add("222");
        permits.add("444");
        permits.add("555");
        permits.add("777");
        ArrayAdapter<String> dataAdapter_Permits = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, permits);
        spinner_Permits.setAdapter(dataAdapter_Permits);


/**
 * Intent intent = new Intent(MainActivity.this, SystemAdministrator.class);
 * startActivity(intent);
 */
 final Button button = findViewById(R.id.delete);
        final Button button_List = findViewById(R.id.List);
        final Button button_create = findViewById(R.id.create);
        final Button button_modify = findViewById(R.id.modify);
        final Button button_permits = findViewById(R.id.permits);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(!itemSelectedInterests.equals("")){
                        if (nameServer.equals("DEV_FormServerControlling")) {

                            sshClient.deleteFile(credentials_devServer.SERVIDOR_DEV.getUserName(), credentials_devServer.SERVIDOR_DEV.getPassword(), credentials_devServer.SERVIDOR_DEV.getHost(),itemSelectedInterests, credentials_devServer.SERVIDOR_DEV.getPatch());

                        } else if(nameServer.equals("QAT_UATFormServerControllingDataBase")) {
                            sshClient.deleteFile(credentialsQatServer.SERVIDOR_QAT.getUserName(), credentialsQatServer.SERVIDOR_QAT.getPassword(), credentialsQatServer.SERVIDOR_QAT.getHost(),itemSelectedInterests,  credentialsQatServer.SERVIDOR_QAT.getPatch());

                        }
                        fillList();
                        Intent intent = new Intent(Files_Admin.this, Files_Admin.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Files_Admin.this, "You have to select a file of the list", Toast.LENGTH_SHORT).show();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        button_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    fillList();
                     Intent intent = new Intent(Files_Admin.this, Files_Admin.class);
                     startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        button_create.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String editName = editNameFile.getText().toString();
                if(!editName.equals("")){
                    if (nameServer.equals("DEV_FormServerControlling")) {

                        sshClient.createFile(credentials_devServer.SERVIDOR_DEV.getUserName(), credentials_devServer.SERVIDOR_DEV.getPassword(), credentials_devServer.SERVIDOR_DEV.getHost(), editName, credentials_devServer.SERVIDOR_DEV.getPatch());

                    } else if (nameServer.equals("QAT_UATFormServerControllingDataBase")) {
                        sshClient.createFile(credentialsQatServer.SERVIDOR_QAT.getUserName(), credentialsQatServer.SERVIDOR_QAT.getPassword(), credentialsQatServer.SERVIDOR_QAT.getHost(), editName, credentialsQatServer.SERVIDOR_QAT.getPatch());

                    }
                    fillList();
                    Intent intent = new Intent(Files_Admin.this, Files_Admin.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Files_Admin.this, "You have to enter a name of the new file", Toast.LENGTH_SHORT).show();
                }

            }
        });

        button_modify.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String editName = editNameFile.getText().toString();
                try {
                    if(!editName.equals("")&&!itemSelectedInterests.equals("")){
                        if (nameServer.equals("DEV_FormServerControlling")) {

                            sshClient.modifyFile(credentials_devServer.SERVIDOR_DEV.getUserName(), credentials_devServer.SERVIDOR_DEV.getPassword(), credentials_devServer.SERVIDOR_DEV.getHost(),editName,itemSelectedInterests,credentials_devServer.SERVIDOR_DEV.getPatch());

                        } else if(nameServer.equals("QAT_UATFormServerControllingDataBase")) {
                            sshClient.modifyFile(credentialsQatServer.SERVIDOR_QAT.getUserName(), credentialsQatServer.SERVIDOR_QAT.getPassword(), credentialsQatServer.SERVIDOR_QAT.getHost(),editName,itemSelectedInterests,credentialsQatServer.SERVIDOR_QAT.getPatch());

                        }
                        fillList();
                        Intent intent = new Intent(Files_Admin.this, Files_Admin.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Files_Admin.this, "You have to select a file of the list or\n" +
                                "you have to enter a name of the new file", Toast.LENGTH_SHORT).show();
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        button_permits.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)  {
                 namePermits = spinner_Permits.getSelectedItem().toString();
                Toast.makeText(Files_Admin.this,"Seleccionado"+namePermits,Toast.LENGTH_LONG).show();
                if(!namePermits.equals("")&&!itemSelectedInterests.equals("")){
                    try {
                        if (nameServer.equals("DEV_FormServerControlling")) {

                            sshClient.permitsFile(credentials_devServer.SERVIDOR_DEV.getUserName(), credentials_devServer.SERVIDOR_DEV.getPassword(), credentials_devServer.SERVIDOR_DEV.getHost(),namePermits,itemSelectedInterests,credentials_devServer.SERVIDOR_DEV.getPatch());

                        } else if(nameServer.equals("QAT_UATFormServerControllingDataBase")) {
                            sshClient.permitsFile(credentialsQatServer.SERVIDOR_QAT.getUserName(), credentialsQatServer.SERVIDOR_QAT.getPassword(), credentialsQatServer.SERVIDOR_QAT.getHost(),namePermits,itemSelectedInterests,credentialsQatServer.SERVIDOR_QAT.getPatch());

                        }
                        fillList();
                        Intent intent = new Intent(Files_Admin.this, Files_Admin.class);
                        startActivity(intent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(Files_Admin.this, "You have to select a file of the list or\n" +
                            "you have to enter a name of the new file", Toast.LENGTH_SHORT).show();
                }


            }
        });
            }


    private void fillList(){
        try{
            interestsList = new ArrayList<Map<String,String>>();
        SshClient sshClient = new SshClient();
        if (nameServer.equals("DEV_FormServerControlling")) {

            files = (ArrayList) sshClient.listFiles_Files(credentials_devServer.SERVIDOR_DEV.getUserName(), credentials_devServer.SERVIDOR_DEV.getPassword(), credentials_devServer.SERVIDOR_DEV.getHost(), credentials_devServer.SERVIDOR_DEV.getPatch());
        } else {
            files = (ArrayList) sshClient.listFiles_Files(credentialsQatServer.SERVIDOR_QAT.getUserName(), credentialsQatServer.SERVIDOR_QAT.getPassword(), credentialsQatServer.SERVIDOR_QAT.getHost(), credentialsQatServer.SERVIDOR_QAT.getPatch());
        }
        if(files.size()==0){
            if (nameServer.equals("DEV_FormServerControlling")) {

                files =  sshClient.listFiles_Files(credentials_devServer.SERVIDOR_DEV.getUserName(), credentials_devServer.SERVIDOR_DEV.getPassword(), credentials_devServer.SERVIDOR_DEV.getHost(), credentials_devServer.SERVIDOR_DEV.getPatch());

            } else {
                files =  sshClient.listFiles_Files(credentialsQatServer.SERVIDOR_QAT.getUserName(), credentialsQatServer.SERVIDOR_QAT.getPassword(), credentialsQatServer.SERVIDOR_QAT.getHost(), credentialsQatServer.SERVIDOR_QAT.getPatch());
            }
        }
        for(int i =0; i<files.size(); i++){
            interestsList.add(createInterest("interest", files.get(i).getChainOfPermits()+" "
            +files.get(i).getUserUsed()+" "+files.get(i).getNameOfFile()));
        }
        SimpleAdapter simpleAdpterForListView =
                new SimpleAdapter(this, interestsList, android.R.layout.simple_list_item_checked,
                        new String[] {"interest"}, new int[] {android.R.id.text1});

        ListView lv = (ListView) findViewById(R.id.listTemp);

        lv.setAdapter(simpleAdpterForListView);
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    private HashMap<String, String> createInterest(String key, String name) { HashMap<String, String> interest = new HashMap<String, String>();
        interest.put(key, name);
        return interest;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item= parent.getItemAtPosition(position).toString();
        nameServer=item;

        Toast.makeText(parent.getContext() ,"Select: "+ item,Toast.LENGTH_LONG).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
