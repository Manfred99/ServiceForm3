package com.serviceform.serviceform.serviceform;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.StreamGobbler;

public class ExecuteCommand {

    public static List<String> result = new ArrayList<>();
    public static String serverOutput = null;
    private static Connection connectTo(String host, String userName, String password) throws IOException {
        Connection connection = new Connection(host);
        connection.connect();
        connection.authenticateWithPassword(userName, password);

        System.out.println("pasa2");
        return connection;
    }

    public static void executeCommandSSH(String pUser, String pPass, String pHost,
                                         int pPort, String pComando) throws Exception {
        JSch ssh = new JSch();
        // Instancio el objeto session para la transferencia
        Session session = null;
        // instancio el canal sftp
        ChannelExec channelssh = null;
        try {
            // Inciciamos el JSch con el usuario, host y puerto
            session = ssh.getSession(pUser, pHost, pPort);
            // Seteamos el password
            session.setPassword(pPass);
            // El SFTP requiere un intercambio de claves
            // con esta propiedad le decimos que acepte la clave
            // sin pedir confirmación
            Properties prop = new Properties();
            prop.put("StrictHostKeyChecking", "no");
            session.setConfig(prop);
            session.connect();

            // Abrimos el canal de sftp y conectamos
            channelssh = (ChannelExec) session.openChannel("exec");
            // seteamos el comando a ejecutar
            channelssh.setCommand(pComando);
            // conectar y ejecutar
            channelssh.connect();
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            // Cerramos el canal y session
            if (channelssh.isConnected())
                channelssh.disconnect();
            if (session.isConnected())
                session.disconnect();
        }// end try
    }// EjecutarSSH
    public static void executeCommandSSH2(String pUser, String pPass, String pHost,
                                       int pPort, String pComando) throws Exception {
        try{
        JSch jsch=new JSch();
        String host=null;
        host= pUser+"@"+pHost; // enter username and ipaddress for machine you need to connect
        String user=host.substring(0, host.indexOf('@'));
        host=host.substring(host.indexOf('@')+1);

        Session session=jsch.getSession(user, host, 22);
        UserInfo ui=new MyUserInfo();
        session.setUserInfo(ui);
        session.connect();

        String command=  pComando; // enter any command you need to execute

        Channel channel=session.openChannel("exec");
        ((ChannelExec)channel).setCommand(command);


        channel.setInputStream(null);

        ((ChannelExec)channel).setErrStream(System.err);

        InputStream in=channel.getInputStream();

        channel.connect();

        byte[] tmp=new byte[1024];
        while(true){
            while(in.available()>0){
                int i=in.read(tmp, 0, 1024);
                if(i<0)break;
                serverOutput = new String(tmp, 0, i);
                result.add(serverOutput);
                System.out.print(new String(tmp, 0, i));
            }
            if(channel.isClosed()){
                System.out.println("exit-status: "+channel.getExitStatus());
                break;
            }
            try{Thread.sleep(1000);}catch(Exception ee){}
        }

        channel.disconnect();
        session.disconnect();
    }
        catch(Exception e){
        System.out.println(e);
    }
}

public static class MyUserInfo implements UserInfo {
    public String getPassword() {
        return passwd;
    }

    public boolean promptYesNo(String str) {
        str = "Yes";
        return true;
    }

    String passwd;

    public String getPassphrase() {
        return null;
    }

    public boolean promptPassphrase(String message) {
        return true;
    }

    public boolean promptPassword(String message) {
        passwd = "SCDV4001DEV"; // enter the password for the machine you want to connect.
        return true;
    }

    public void showMessage(String message) {

    }
}
}
