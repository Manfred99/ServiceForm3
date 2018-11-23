package com.serviceform.serviceform.serviceform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends Activity {
     public static int id_Role;
     Credentials_DBA credentials_dba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText username, password;
        final Button accept, cancel;


        //obtener el texto ingresado en el txtUserName
        username = (EditText) findViewById(R.id.txtUserName);

        //obtener el texto ingresado en el txtPassword
        password = (EditText) findViewById(R.id.txtPassword);

        accept = (Button) findViewById(R.id.btnAccept);
        cancel = (Button) findViewById(R.id.btnCancel);

        //le da funcionalidad al botón Accept
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernameString = username.getText().toString();
                String passwordString = password.getText().toString();

                ConnectionClass connectionClass= new ConnectionClass();
                Connection connection=null;

                try {
                    connection = connectionClass.createConnection(credentials_dba.SERVER_DBA.getUser(), credentials_dba.SERVER_DBA.getPassword(), credentials_dba.SERVER_DBA.getDatabase(), credentials_dba.SERVER_DBA.getServer());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                if (connection==null){

                    Toast.makeText(MainActivity.this, "Error de conección con BD",
                            Toast.LENGTH_SHORT).show();
                }else{

                                       String query= "SELECT * FROM [IF4100_B63817_2018].[dbo].[Users] WHERE Username='"+usernameString
                            +"' AND Password='"+passwordString+"'";

                    try{
                        //prepara la conección para luego consultarla
                        Statement statement= connection.createStatement();
                        //ejecuta la consulta y obtiene resultado
                        ResultSet resultSet = statement.executeQuery(query);

                        //pregunta si la consulta trajo resultados
                        if (resultSet.next()){

                            Toast.makeText(MainActivity.this, "Éxito!!!",
                                   Toast.LENGTH_SHORT).show();


                            //usamos el rol del usuario para darle permisos

                            String queryRol= "SELECT [Role] FROM [IF4100_B63817_2018].[dbo].[Users] WHERE Username='"+usernameString
                                    +"' AND Password='"+passwordString+"'";

                            //prepara la conección para luego consultarla
                            Statement statementRol= connection.createStatement();
                            //ejecuta la consulta y obtiene resultado
                            ResultSet resultSetRol = statementRol.executeQuery(queryRol);

                            resultSetRol.next();
                            id_Role=((Number)resultSetRol.getObject(1)).intValue();



                           if(id_Role==1 || id_Role==2) {
                               //vincula la actividad main con la home screen
                               Intent intent = new Intent(MainActivity.this, SystemAdministrator.class);

                               //pasamos el nombre de usuario y la actividad
                               Bundle bundle = new Bundle();
                               bundle.putString("NAME", usernameString);

                               //coloca el mensaje que la actividad va a transmitir
                               intent.putExtras(bundle);

                               //hace el paso de actividades
                               startActivity(intent);
                           }else  if(id_Role==3){
                               //vincula la actividad main con la home screen
                               Intent intent = new Intent(MainActivity.this, Technical.class);

                               //pasamos el nombre de usuario y la actividad
                               Bundle bundle = new Bundle();


                               //coloca el mensaje que la actividad va a transmitir
                               intent.putExtras(bundle);

                               //hace el paso de actividades
                               startActivity(intent);
                           }
                        }


                    }catch (Exception ex){

                        ex.printStackTrace();
                    }
                }




                // Toast.makeText(MainActivity.this, "Usuario: "+usernameString+ " Contraseña: "+passwordString,
                //        Toast.LENGTH_SHORT).show();





            }
        });
    }

  /*  @Override
    protected String doInBackground(String...params) {
        return doInBackground();
    }*/

}
