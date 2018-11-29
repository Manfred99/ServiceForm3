package com.serviceform.serviceform.serviceform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.serviceform.serviceform.serviceform.files.Files_Admin;
import com.serviceform.serviceform.serviceform.files.Files_Manager;
import com.serviceform.serviceform.serviceform.memory.Memory_Admin;
import com.serviceform.serviceform.serviceform.memory.Memory_Manager;
import com.serviceform.serviceform.serviceform.process.Process_Manager;
import com.serviceform.serviceform.serviceform.state.State_Admin;
import com.serviceform.serviceform.serviceform.state.State_Manager;
import com.serviceform.serviceform.serviceform.traffic.Traffic_Admin;
import com.serviceform.serviceform.serviceform.traffic.Traffic_Manager;

public class SystemAdministrator extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_administrator);
        //creacion del boton files
        final ImageButton files ;
        files=(ImageButton) findViewById(R.id. imageButtonFiles);
        files.setImageResource(R.mipmap.files);
        //creacion del boton process
        final ImageButton process;
        process=(ImageButton) findViewById(R.id.imageButtonProcess);
        process.setImageResource(R.mipmap.process);
        //creacion del boton memory
        final ImageButton memory;
        memory=(ImageButton) findViewById(R.id.imageButtonMemory);
        memory.setImageResource(R.mipmap.memory);
        //creacion del boton state
        final ImageButton state;
        state=(ImageButton) findViewById(R.id.imageButtondrState);
        state.setImageResource(R.mipmap.state);
        //creacion del boton traffic
        final ImageButton traffic;
        traffic=(ImageButton) findViewById(R.id.imageButtonTraffic);
        traffic.setImageResource(R.mipmap.traffic);

        if (MainActivity.id_Role==1){
            files.setOnClickListener(new View.OnClickListener() {
                @Override
                 public void onClick(View v) {
                   //vincula la actividad main con la home screen
                    Intent intent = new Intent(SystemAdministrator.this, FilesfunctionalitychooserActivity.class);

                    //pasamos el nombre de usuario y la actividad
                    Bundle bundle = new Bundle();


                    //coloca el mensaje que la actividad va a transmitir
                    intent.putExtras(bundle);

                    //hace el paso de actividades
                    startActivity(intent);

                    }
        });//en caso de que presione el boton de files siendo admin
        process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vincula la actividad main con la home screen
                Intent intent = new Intent(SystemAdministrator.this, ViewProcess.class);

                //pasamos el nombre de usuario y la actividad
                Bundle bundle = new Bundle();


                //coloca el mensaje que la actividad va a transmitir
                intent.putExtras(bundle);

                //hace el paso de actividades
                startActivity(intent);

            }
        });//en caso de que presione el boton de process siendo admin
         memory.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //vincula la actividad main con la home screen
                 Intent intent = new Intent(SystemAdministrator.this, Memory_Admin.class);

                 //pasamos el nombre de usuario y la actividad
                 Bundle bundle = new Bundle();


                 //coloca el mensaje que la actividad va a transmitir
                 intent.putExtras(bundle);

                 //hace el paso de actividades
                 startActivity(intent);

             }
         });//en caso de que presione el boton de memory siendo admin
            state.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //vincula la actividad main con la home screen
                    Intent intent = new Intent(SystemAdministrator.this, State_Admin.class);

                    //pasamos el nombre de usuario y la actividad
                    Bundle bundle = new Bundle();


                    //coloca el mensaje que la actividad va a transmitir
                    intent.putExtras(bundle);

                    //hace el paso de actividades
                    startActivity(intent);

                }
            });//en caso de que presione el boton de state siendo admin
            traffic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //vincula la actividad main con la home screen
                    Intent intent = new Intent(SystemAdministrator.this, Traffic_Admin.class);

                    //pasamos el nombre de usuario y la actividad
                    Bundle bundle = new Bundle();


                    //coloca el mensaje que la actividad va a transmitir
                    intent.putExtras(bundle);

                    //hace el paso de actividades
                    startActivity(intent);

                }
            });//en caso de que presione el boton de traffic siendo admin
        }
        if (MainActivity.id_Role==2){
            files.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //vincula la actividad main con la home screen
                    Intent intent = new Intent(SystemAdministrator.this, Files_Manager.class);

                    //pasamos el nombre de usuario y la actividad
                    Bundle bundle = new Bundle();


                    //coloca el mensaje que la actividad va a transmitir
                    intent.putExtras(bundle);

                    //hace el paso de actividades
                    startActivity(intent);

                }
            });//en caso de que presione el boton de files siendo manager
            process.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //vincula la actividad main con la home screen
                    Intent intent = new Intent(SystemAdministrator.this, Process_Manager.class);

                    //pasamos el nombre de usuario y la actividad
                    Bundle bundle = new Bundle();


                    //coloca el mensaje que la actividad va a transmitir
                    intent.putExtras(bundle);

                    //hace el paso de actividades
                    startActivity(intent);

                }
            });//en caso de que presione el boton de process siendo manager
            memory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //vincula la actividad main con la home screen
                    Intent intent = new Intent(SystemAdministrator.this, Memory_Manager.class);

                    //pasamos el nombre de usuario y la actividad
                    Bundle bundle = new Bundle();


                    //coloca el mensaje que la actividad va a transmitir
                    intent.putExtras(bundle);

                    //hace el paso de actividades
                    startActivity(intent);

                }
            });//en caso de que presione el boton de memory siendo manager
            state.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //vincula la actividad main con la home screen
                    Intent intent = new Intent(SystemAdministrator.this, State_Manager.class);

                    //pasamos el nombre de usuario y la actividad
                    Bundle bundle = new Bundle();


                    //coloca el mensaje que la actividad va a transmitir
                    intent.putExtras(bundle);

                    //hace el paso de actividades
                    startActivity(intent);

                }
            });//en caso de que presione el boton de state siendo manager
            traffic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //vincula la actividad main con la home screen
                    Intent intent = new Intent(SystemAdministrator.this, Traffic_Manager.class);

                    //pasamos el nombre de usuario y la actividad
                    Bundle bundle = new Bundle();


                    //coloca el mensaje que la actividad va a transmitir
                    intent.putExtras(bundle);

                    //hace el paso de actividades
                    startActivity(intent);

                }
            });//en caso de que presione el boton de traffic siendo manager
        }
    }


}