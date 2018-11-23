package com.serviceform.serviceform.serviceform.files;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import static com.serviceform.serviceform.serviceform.R.id.editText;

public class Files_Admin extends Activity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    Spinner spinner;
    Spinner spinner_Permits;
    private List<String> servers;
    private List<String> permits;
    private static String nameSelect;
    private static String processSelect;
    public static String nameServer;
    public static String namePermits;
    Credentials_DEVServer credentials_devServer;
    Credentials_QATServer credentialsQatServer;





    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files__admin);
        nameServer="DEV_FormServerControlling";
        final SshClient sshClient = new SshClient();
        final EditText editNameFile;
        editNameFile = (EditText) findViewById(editText);

        try {
            fillExpandibleList();
        } catch (IOException e) {
            e.printStackTrace();
        }


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



 final Button button = findViewById(R.id.delete);
        final Button button_List = findViewById(R.id.List);
        final Button button_create = findViewById(R.id.create);
        final Button button_modify = findViewById(R.id.modify);
        final Button button_permits = findViewById(R.id.permits);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (nameServer.equals("DEV_FormServerControlling")) {

                        sshClient.deleteFile(credentials_devServer.SERVIDOR_DEV.getUserName(), credentials_devServer.SERVIDOR_DEV.getPassword(), credentials_devServer.SERVIDOR_DEV.getHost(),nameSelect);

                    } else if(nameServer.equals("QAT_UATFormServerControllingDataBase")) {
                        sshClient.deleteFile(credentialsQatServer.SERVIDOR_QAT.getUserName(), credentialsQatServer.SERVIDOR_QAT.getPassword(), credentialsQatServer.SERVIDOR_QAT.getHost(),nameSelect);

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
                    fillExpandibleList();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        button_create.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String editName = editNameFile.getText().toString();
                try {
                    if (nameServer.equals("DEV_FormServerControlling")) {

                        sshClient.createFile(credentials_devServer.SERVIDOR_DEV.getUserName(), credentials_devServer.SERVIDOR_DEV.getPassword(), credentials_devServer.SERVIDOR_DEV.getHost(),editName);

                    } else if(nameServer.equals("QAT_UATFormServerControllingDataBase")) {
                        sshClient.createFile(credentialsQatServer.SERVIDOR_QAT.getUserName(), credentialsQatServer.SERVIDOR_QAT.getPassword(), credentialsQatServer.SERVIDOR_QAT.getHost(),editName);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        button_modify.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String editName = editNameFile.getText().toString();
                try {
                    if (nameServer.equals("DEV_FormServerControlling")) {

                        sshClient.modifyFile(credentials_devServer.SERVIDOR_DEV.getUserName(), credentials_devServer.SERVIDOR_DEV.getPassword(), credentials_devServer.SERVIDOR_DEV.getHost(),editName,nameSelect);

                    } else if(nameServer.equals("QAT_UATFormServerControllingDataBase")) {
                        sshClient.modifyFile(credentialsQatServer.SERVIDOR_QAT.getUserName(), credentialsQatServer.SERVIDOR_QAT.getPassword(), credentialsQatServer.SERVIDOR_QAT.getHost(),editName,nameSelect);

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


                try {
                    if (nameServer.equals("DEV_FormServerControlling")) {

                        sshClient.permitsFile(credentials_devServer.SERVIDOR_DEV.getUserName(), credentials_devServer.SERVIDOR_DEV.getPassword(), credentials_devServer.SERVIDOR_DEV.getHost(),namePermits,nameSelect);

                    } else if(nameServer.equals("QAT_UATFormServerControllingDataBase")) {
                        sshClient.permitsFile(credentialsQatServer.SERVIDOR_QAT.getUserName(), credentialsQatServer.SERVIDOR_QAT.getPassword(), credentialsQatServer.SERVIDOR_QAT.getHost(),namePermits,nameSelect);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
            }

    public void fillExpandibleList() throws IOException {
        final List<FilePermitsName> files;
        SshClient sshClient = new SshClient();
        final FilePermitsName filePermitsName2 = new FilePermitsName();
        if (nameServer.equals("DEV_FormServerControlling")) {

            files = (ArrayList) sshClient.listFiles_Files(credentials_devServer.SERVIDOR_DEV.getUserName(), credentials_devServer.SERVIDOR_DEV.getPassword(), credentials_devServer.SERVIDOR_DEV.getHost(), credentials_devServer.SERVIDOR_DEV.getPatch());
        } else {
            files = (ArrayList) sshClient.listFiles_Files(credentialsQatServer.SERVIDOR_QAT.getUserName(), credentialsQatServer.SERVIDOR_QAT.getPassword(), credentialsQatServer.SERVIDOR_QAT.getHost(), credentialsQatServer.SERVIDOR_QAT.getPatch());
    }

       int i=0;


        final ArrayAdapter<FilePermitsName> simpleAdpterForListView = new ArrayAdapter<FilePermitsName>(Files_Admin.this, android.R.layout.simple_list_item_1,files);
        ListView lv = (ListView) findViewById(R.id.listTemp);
        lv.setAdapter(simpleAdpterForListView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               FilePermitsName f=  files.get(position);
               nameSelect=f.getNameFile();
               processSelect=f.getProcess();
                Toast.makeText(Files_Admin.this,"Seleccionado"+f.getProcess()+""+f.getNameFile()+"----"+nameSelect+""+processSelect,Toast.LENGTH_LONG).show();

            }
        });
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
