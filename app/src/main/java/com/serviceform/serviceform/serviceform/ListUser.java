package com.serviceform.serviceform.serviceform;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListUser extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
    }

    public void fillExpandibleList() {
        List<Map<String, String>> interestsList = new ArrayList<Map<String, String>>();
        interestsList.add(createInterest("interest", "Valeria Leiva"));
        interestsList.add(createInterest("interest", "Sofía Días"));
        interestsList.add(createInterest("interest", "Gabriela Leiva"));
        interestsList.add(createInterest("interest", "Abigail Fonseca"));
        SimpleAdapter simpleAdpterForListView = new SimpleAdapter(this, interestsList, android.R.layout.simple_list_item_checked, new String[]
                {"interest"}, new int[]{android.R.id.text1});
        ListView lv = (ListView) findViewById(R.id.listTemp);
        lv.setAdapter(simpleAdpterForListView);
    }

    private HashMap<String, String> createInterest(String key, String name) {
        HashMap<String, String> interest = new HashMap<String, String>();
        interest.put(key, name);
        return interest;
    }
}