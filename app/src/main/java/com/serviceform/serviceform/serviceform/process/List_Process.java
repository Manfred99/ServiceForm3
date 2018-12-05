package com.serviceform.serviceform.serviceform.process;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import com.serviceform.serviceform.serviceform.Credentials_DBA;
import com.serviceform.serviceform.serviceform.Credentials_DEVServer;
import com.serviceform.serviceform.serviceform.ftp.ServerCredentials;

import java.io.InputStream;

public class List_Process {

    public static String listOfProcess = "";
    public static String userName="", hostServer="";
    ServerCredentials sc = new ServerCredentials();
    public void lista(){
        try{
            JSch jsch=new JSch();

            String host=null;
//      if(arg.length>0){
//        host=arg[0];
//      }
//      else{
            host=sc.domainDevelopmentServer; // enter username and ipaddress for machine you need to connect
//      }
            String user=host.substring(0, host.indexOf('@'));
            host=host.substring(host.indexOf('@')+1);
            userName = user;
            hostServer = host;
            Session session=jsch.getSession(user, host, 22);

            // username and password will be given via UserInfo interface.
            UserInfo ui=new MyUserInfo();
            session.setUserInfo(ui);
            session.connect();

            String command=  "cd Desktop && ./Procesos"; // enter any command you need to execute

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
                    listOfProcess += new String(tmp, 0, i);
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

    public boolean modifyProcess(String action,String pid){
        boolean retVal=false;
        if(!userName.equals("") && !hostServer.equals("")){
            try{

                JSch jsch=new JSch();



                Session session=jsch.getSession(userName, hostServer, 22);

                // username and password will be given via UserInfo interface.
                UserInfo ui=new MyUserInfo();
                session.setUserInfo(ui);
                session.connect();

                String command=  "cd Desktop && ./"+action+" "+pid; // enter any command you need to execute

                Channel channel=session.openChannel("exec");
                ((ChannelExec)channel).setCommand(command);


                channel.setInputStream(null);

                ((ChannelExec)channel).setErrStream(System.err);

                InputStream in=channel.getInputStream();

                channel.connect();
                channel.disconnect();
                session.disconnect();
                retVal=true;
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        return retVal;
    }

    public static class MyUserInfo implements UserInfo {
        public String getPassword(){ return passwd; }
        public boolean promptYesNo(String str){
            str = "Yes";
            return true;
        }

        String passwd;

        public String getPassphrase(){
            return null;
        }
        public boolean promptPassphrase(String message){
            return true;
        }
        public boolean promptPassword(String message){
            passwd="SCDV4001DEV"; // enter the password for the machine you want to connect.
            return true;
        }
        public void showMessage(String message){

        }

    }
}
