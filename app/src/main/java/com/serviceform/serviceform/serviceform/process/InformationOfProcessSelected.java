package com.serviceform.serviceform.serviceform.process;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.serviceform.serviceform.serviceform.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InformationOfProcessSelected extends Activity {

    public PropertiesProcess propertiesProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_of_process_selected);

        showProcess();
    }

    public void showProcess(){
        propertiesProcess = new PropertiesProcess();
        List<Map<String, String>> characterList = new ArrayList<>();
        characterList.add(showProcess("process", propertiesProcess.processToExecute));

        SimpleAdapter simpleAdpterForListView =
                new SimpleAdapter(this, characterList, android.R.layout.simple_list_item_checked,
                        new String[] {"process"}, new int[] {android.R.id.text1});

        ListView lv = (ListView) findViewById(R.id.list_selected);

        lv.setAdapter(simpleAdpterForListView);
    }


    private HashMap<String, String> showProcess(String key, String name) {
        HashMap<String, String> processID = new HashMap<String, String>();
        processID.put(key, name);
        return processID;

    }

}
