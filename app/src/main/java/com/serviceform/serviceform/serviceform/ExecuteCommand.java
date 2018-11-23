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
        Connection connection = connectTo(pHost,pUser,pPass);
        connection.connect();
        String command = pComando;

        ch.ethz.ssh2.Session session = null;
        System.out.println("pasa3");

        try {
            session = connection.openSession();
            session.execCommand(command);
            InputStream stdout = new StreamGobbler(session.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            try {

                String line = br.readLine();
                while (line != null) {
                    result.add(line);
                    line = br.readLine();
                }
            } catch (IOException e) {
                System.out.println("I/O Error getting string: ");
                throw new IOException(e);
            } finally {
                if (session != null) {
                    session.close();
                }
            }


        } catch (IOException e) {
            System.out.println("I/O Error getting string: ");
        }


    }
}
