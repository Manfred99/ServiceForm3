package com.serviceform.serviceform.serviceform;

public enum Credentials {

    SERVIDOR_DEV("emmanuel@192.168.43.27","12345"),
    SERVIDOR_QAT("dfonse@192.168.43.27","abc_a*");

    private final String host;
    private final String  contraseña;


    Credentials(String a, String b) {

        host= a;
        contraseña=b;
        }

        public String getHost(){
        return host;
        }

        public String getContraseña(){
        return contraseña;
        }


}


