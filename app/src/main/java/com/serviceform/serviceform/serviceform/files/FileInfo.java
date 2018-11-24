package com.serviceform.serviceform.serviceform.files;

public class FileInfo {
    private String chainOfPermits;
    private String userUsed;
    private String nameOfFile;

    public FileInfo() {
        this.chainOfPermits = "";
        this.userUsed = "";
        this.nameOfFile = "";
    }

    public FileInfo(String chainOfPermits, String userUsed, String nameOfFile) {
        this.chainOfPermits = chainOfPermits;
        this.userUsed = userUsed;
        this.nameOfFile = nameOfFile;
    }

    public String getChainOfPermits() {
        return chainOfPermits;
    }

    public void setChainOfPermits(String chainOfPermits) {
        this.chainOfPermits = chainOfPermits;
    }

    public String getUserUsed() {
        return userUsed;
    }

    public void setUserUsed(String userUsed) {
        this.userUsed = userUsed;
    }

    public String getNameOfFile() {
        return nameOfFile;
    }

    public void setNameOfFile(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }
}
