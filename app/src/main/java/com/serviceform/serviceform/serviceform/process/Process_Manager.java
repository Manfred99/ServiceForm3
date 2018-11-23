package com.serviceform.serviceform.serviceform.process;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.serviceform.serviceform.serviceform.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Process_Manager extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process__manager);
        fillExpandibleList();
        fillCountriesSpinner();
    }
    public void fillCountriesSpinner() {
        List<String> countrySpinnerArray = new ArrayList<String>(); countrySpinnerArray.add("QAT_UATFormServerControllingDataBase"); countrySpinnerArray.add("QAT_UATFormServerControlling");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countrySpinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner countrySpinner = (Spinner) findViewById(R.id.spinnerTemp); countrySpinner.setAdapter(adapter);
    }
    public void fillExpandibleList() {
        List<Map<String, String>> interestsList = new ArrayList<Map<String,String>>();
        interestsList.add(createInterest("interest", "3333 Firefox")); interestsList.add(createInterest("interest", "2222 Word")); interestsList.add(createInterest("interest", "3139 Android Studio"));
        SimpleAdapter simpleAdpterForListView = new SimpleAdapter(this, interestsList, android.R.layout.simple_list_item_checked, new String[]
                {"interest"}, new int[] {android.R.id.text1});
        ListView lv = (ListView) findViewById(R.id.listTemp);
        lv.setAdapter(simpleAdpterForListView);
    }
    private HashMap<String, String> createInterest(String key, String name) { HashMap<String, String> interest = new HashMap<String, String>();
        interest.put(key, name);
        return interest;
    }
}
