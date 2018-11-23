package com.serviceform.serviceform.serviceform.traffic;

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

public class Traffic_Admin extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic__admin);
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
        interestsList.add(createInterest("interest", "192.168.0.10")); interestsList.add(createInterest("interest", "192.168.0.12")); interestsList.add(createInterest("interest", "192.168.0.10")); interestsList.add(createInterest("interest", "192.168.0.16"));
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
