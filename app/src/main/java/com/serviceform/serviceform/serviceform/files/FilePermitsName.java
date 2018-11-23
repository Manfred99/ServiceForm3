package com.serviceform.serviceform.serviceform.files;

public class FilePermitsName {

    public String permits;
    public String nameFile;

    public FilePermitsName() {
    }

    public FilePermitsName(String process, String nameFile) {
        this.permits = process;
        this.nameFile = nameFile;
    }

    public String getProcess() {
        return permits;
    }

    public void setProcess(String processName) {
        this.permits = permits;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFiles) {
        this.nameFile = nameFile;
    }

    @Override
    public String toString() {
        return " Permits='" + permits+" nameFile='" + nameFile;
    }
}
