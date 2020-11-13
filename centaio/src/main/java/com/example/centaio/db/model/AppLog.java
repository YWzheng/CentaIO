package com.example.centaio.db.model;

public class AppLog {

    private final String logInfo;

    public AppLog(String data) {
        this.logInfo = data;
    }

    public String getData() {
        return logInfo;
    }
}
