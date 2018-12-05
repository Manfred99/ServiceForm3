package com.serviceform.serviceform.serviceform;

public enum Credentials_QATServer {

    SERVIDOR_QAT("scdv4001","SCDV4001DEV","192.168.43.239","/home/scdv4001/Documents");

    private final String userName;
    private final String  password;
    private final String host;
    private final String  patch;

    Credentials_QATServer(String userName, String password, String host, String patch) {
        this.userName = userName;
        this.password = password;
        this.host = host;
        this.patch = patch;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public String getPatch() {
        return patch;
    }
}
