package com.serviceform.serviceform.serviceform;

public enum Credentials_DBAANDROID {

    SERVER_DBAAndroid("estudiantesrp","estudiantesrp","IF4100_B73331_2018","163.178.173.148");
    //SERVER_DBA("SA","sql_devdata4001","DevFormServerControllingDataBase","192.168.43.53");
    private final String user;
    private final String password;
    private final String database;
    private final String server;

    Credentials_DBAANDROID(String user, String password, String database, String server) {
        this.user = user;
        this.password = password;
        this.database = database;
        this.server = server;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabase() {
        return database;
    }

    public String getServer() {
        return server;
    }
}
