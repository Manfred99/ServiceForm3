package com.serviceform.serviceform.serviceform;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class InformationOfProcessSelected extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_of_process_selected);
    }

    public void showProcess(){
        ViewProcess view = new ViewProcess();
        for(int i=0;i<view.procesos.size();i++){
            String character = view.procesos.get(i);
        }
    }

}
