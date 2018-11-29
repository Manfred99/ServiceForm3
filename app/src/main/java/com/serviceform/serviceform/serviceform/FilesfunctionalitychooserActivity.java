package com.serviceform.serviceform.serviceform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.serviceform.serviceform.serviceform.files.Files_Admin;
import com.serviceform.serviceform.serviceform.ftp.MainfilesftpActivity;

public class FilesfunctionalitychooserActivity extends Activity {
     public static int id_Role;
     Credentials_DBA credentials_dba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filesfunctionalitychooser);

        Button btn_FTP = findViewById(R.id.btn_FilesChooserFTP);
        btn_FTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilesfunctionalitychooserActivity.this, MainfilesftpActivity.class);
                startActivity(intent);
            }
        });
        Button btn_Management = findViewById(R.id.btn_FilesChooserManagement);
        btn_Management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilesfunctionalitychooserActivity.this, Files_Admin.class);
                startActivity(intent);
            }
        });
    }

  /*  @Override
    protected String doInBackground(String...params) {
        return doInBackground();
    }*/

}
