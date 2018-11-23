package com.serviceform.serviceform.serviceform;

public enum Credentials_DEVServer {

    SERVIDOR_DEV("dfonse","darkside9030","192.168.1.9","Documents");

    private final String userName;
    private final String  password;
    private final String host;
    private final String  patch;

    Credentials_DEVServer(String userName, String password, String host, String patch) {
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
