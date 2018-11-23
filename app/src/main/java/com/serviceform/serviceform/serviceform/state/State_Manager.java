package com.serviceform.serviceform.serviceform.state;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.serviceform.serviceform.serviceform.R;

import java.util.ArrayList;
import java.util.List;

public class State_Manager extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state__manager);
        fillCountriesSpinner();
    }
    public void fillCountriesSpinner() {
        List<String> countrySpinnerArray = new ArrayList<String>(); countrySpinnerArray.add("QAT_UATFormServerControllingDataBase"); countrySpinnerArray.add("QAT_UATFormServerControlling");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countrySpinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner countrySpinner = (Spinner) findViewById(R.id.spinnerTemp); countrySpinner.setAdapter(adapter);
    }
}
