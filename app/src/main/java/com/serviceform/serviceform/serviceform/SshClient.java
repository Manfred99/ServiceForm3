package com.serviceform.serviceform.serviceform;

import android.os.AsyncTask;

import com.serviceform.serviceform.serviceform.files.FilePermitsName;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SshClient {
     ExecuteCommand executeCommand;
    final List<String> result = new ArrayList<>();

    private List<String> listFiles(final String path,final String userName,
                                   final String password, final String host) throws Exception {
        final String command = "ls -la " + path;
        new AsyncTask<Integer, Void, Void>(){
            @Override
            protected Void doInBackground(Integer... params) {
                try {
                    ExecuteCommand executeCommand= new ExecuteCommand();
                    executeCommand.executeCommandSSH2(userName,password,host,22,command);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(1);
        List<String> result = new ArrayList<>();
        result = ExecuteCommand.result;

        return result;
    }

    private Connection connectTo(String host, String userName, String password) throws IOException {
        Connection connection = new Connection(host);
        connection.connect();
        connection.authenticateWithPassword(userName, password);

        System.out.println("pasa2");
        return connection;
    }



    public List<FilePermitsName> listFiles_Files(String userName, String password , String host, String path ) throws Exception {
        System.out.println("pasa4");
        SshClient sshClient = new SshClient();
        List<String> actual = listFiles(path,userName, password, host);
        List<FilePermitsName> files = new ArrayList<>();
      Boolean flat1= false;
      Boolean flat2= false;
        String processName = null;
        String nameFiles=null;
        System.out.println("pasa5");
        int v=0;
        for (String s : actual) {
            System.out.println(s);

            String[] datos = s.split(" ");

            int i = 0;
            int cont=12;
            for (String item : datos) {

                if ((i == 0)) {

                    processName= item;
                    System.out.println(item+"ProcessName");
                    flat1=true;
                    System.out.println(flat2+"falt1");
                }
                if ((i== 12)) {

                    nameFiles= item;
                    System.out.println(item+"Name Files");
                    flat2= true;
                    System.out.println(flat2+"falt2");

                }

                i=i+1;
                }

                if(flat1!=false && flat2!=false){
                    System.out.println("Pasa?");
                    FilePermitsName filePermitsName;
                    filePermitsName = new FilePermitsName(processName,nameFiles);
                    System.out.println(filePermitsName.toString()+"Es esto?");

                    files.add(filePermitsName);
                    flat1=false;
                    flat2=false;
                }



            }




        for (   FilePermitsName d : files) {

            System.out.println(d.toString());

        }
        return files;
    }

    public void deleteFile( final String userName, final String password, final String host, final String name) throws Exception {
        new AsyncTask<Integer, Void, Void>(){
            @Override
            protected Void doInBackground(Integer... params) {
                try {
                    ExecuteCommand executeCommand= new ExecuteCommand();
                    ExecuteCommand.executeCommandSSH(userName,password,host,22,"cd Documents && rm -r "+name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(1);

    }

    public void createFile(final String userName,final String password,final String host,final String editName) throws Exception {

        new AsyncTask<Integer, Void, Void>(){
            @Override
            protected Void doInBackground(Integer... params) {
                try {
                    ExecuteCommand executeCommand= new ExecuteCommand();
                    ExecuteCommand.executeCommandSSH(userName,password,host,22,"cd Documents && touch "+ editName+".txt");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(1);
    }

    public void modifyFile(final String userName,final String password,final String host,final String modifyName,final String editName) throws Exception {

        new AsyncTask<Integer, Void, Void>(){
            @Override
            protected Void doInBackground(Integer... params) {
                try {
                    ExecuteCommand executeCommand= new ExecuteCommand();
                    ExecuteCommand.executeCommandSSH(userName,password,host,22,"cd Documents && mv "+editName+" "+modifyName+".txt");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(1);
    }



    public void permitsFile(final String userName, final String password, final String host, final String permitsFile,final String file) throws Exception {
        new AsyncTask<Integer, Void, Void>(){
            @Override
            protected Void doInBackground(Integer... params) {
                try {
                    ExecuteCommand executeCommand= new ExecuteCommand();
                    ExecuteCommand.executeCommandSSH(userName,password,host,22,"cd Documents && ./permisos 1 "+Integer.parseInt(permitsFile)+" "+file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(1);


    }


}
