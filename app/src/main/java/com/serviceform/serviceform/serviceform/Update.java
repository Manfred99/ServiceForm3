package com.serviceform.serviceform.serviceform;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Update extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        fillCountriesSpinner();
    }
    public void fillCountriesSpinner() {
        List<String> countrySpinnerArray = new ArrayList<String>(); countrySpinnerArray.add("QAT_UATFormServerControllingDataBase"); countrySpinnerArray.add("QAT_UATFormServerControlling");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countrySpinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner countrySpinner = (Spinner) findViewById(R.id.spinnerTemp); countrySpinner.setAdapter(adapter);
    }
}

