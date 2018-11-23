package com.serviceform.serviceform.serviceform;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.serviceform.serviceform.serviceform.files.Files_Admin;

public class Technical extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technical);
        //creacion del boton tickets
        final Button tickets;
        tickets=(Button) findViewById(R.id.btn_Tickets);
        //creacion del boton users
        final Button users;
        users=(Button) findViewById(R.id.btn_Users);
        //creacion del boton update
        final Button update;
        update=(Button) findViewById(R.id.btn_Update);
        //creacion del boton support
        final Button support;
        support=(Button) findViewById(R.id.btn_Support);


        tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vincula la actividad main con la home screen
                Intent intent = new Intent(Technical.this, Tickets.class);

                //pasamos el nombre de usuario y la actividad
                Bundle bundle = new Bundle();


                //coloca el mensaje que la actividad va a transmitir
                intent.putExtras(bundle);

                //hace el paso de actividades
                startActivity(intent);

            }
        });//en caso de que presione el boton de files siendo admin
        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vincula la actividad main con la home screen
                Intent intent = new Intent(Technical.this, ListUser.class);

                //pasamos el nombre de usuario y la actividad
                Bundle bundle = new Bundle();


                //coloca el mensaje que la actividad va a transmitir
                intent.putExtras(bundle);

                //hace el paso de actividades
                startActivity(intent);

            }
        });//en caso de que presione el boton de files siendo admin
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vincula la actividad main con la home screen
                Intent intent = new Intent(Technical.this, Update.class);

                //pasamos el nombre de usuario y la actividad
                Bundle bundle = new Bundle();


                //coloca el mensaje que la actividad va a transmitir
                intent.putExtras(bundle);

                //hace el paso de actividades
                startActivity(intent);

            }
        });//en caso de que presione el boton de files siendo admin
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vincula la actividad main con la home screen
                Intent intent = new Intent(Technical.this, Support.class);

                //pasamos el nombre de usuario y la actividad
                Bundle bundle = new Bundle();


                //coloca el mensaje que la actividad va a transmitir
                intent.putExtras(bundle);

                //hace el paso de actividades
                startActivity(intent);

            }
        });//en caso de que presione el boton de files siendo admin
    }
}
