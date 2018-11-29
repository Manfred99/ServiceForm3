package com.serviceform.serviceform.serviceform.state;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.serviceform.serviceform.serviceform.R;
import com.serviceform.serviceform.serviceform.process.ViewProcess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectState extends Activity {

    public static String numberofState="";
    List<Map<String, String>> interestsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_state);
        interestsList = new ArrayList<>();
        fillExpandibleList();
        final ListView listView = findViewById(R.id.list_states);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String message = "";
                message+=listView.getItemAtPosition(position).toString();
                int startPosition = 9;
                while(startPosition<message.length()-1){
                    numberofState+=message.charAt(startPosition);
                    startPosition++;
                }
                Toast.makeText(SelectState.this, "Has seleccionado: "+numberofState, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent( SelectState.this, ViewState.class);

                Bundle bundle=new Bundle();
                bundle.putString("NAME", "Process");

                //coloca el mensaje que la actividad va a transmitir
                intent.putExtras(bundle);


                //hace el paso de actividades

                startActivity(intent);
            }
        });
    }


    public void fillExpandibleList() {
        interestsList= new ArrayList<>();

        int conta=0, numberProcess=0;
        ViewProcess view = new ViewProcess();
        String values = view.list_of_states;
        String state="";
        for(int i = 0; i < values.length();i++){
            char letter = values.charAt(i);
            if(letter=='-' || i==values.length()-1) {
                interestsList.add(createProcess("process", state));
                state="";
            }else
                state+=letter;
        }


        SimpleAdapter simpleAdpterForListView =
                new SimpleAdapter(this, interestsList, android.R.layout.simple_list_item_checked,
                        new String[] {"process"}, new int[] {android.R.id.text1});

        ListView lv = (ListView) findViewById(R.id.list_states);

        lv.setAdapter(simpleAdpterForListView);

    }


    private HashMap<String, String> createProcess(String key, String name) {
        HashMap<String, String> process = new HashMap<String, String>();
        process.put(key, name);
        return process;

    }
}
