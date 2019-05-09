package com.ironsource.mediationsdk.model;

public class ApplicationLogger {
    private int mConsole;
    private int mPublisher;
    private int mServer;

    public ApplicationLogger(int serverLoggerLevel, int publisherLoggerLevel, int consoleLoggerLevel) {
        this.mServer = serverLoggerLevel;
        this.mPublisher = publisherLoggerLevel;
        this.mConsole = consoleLoggerLevel;
    }

    public int getServerLoggerLevel() {
        return this.mServer;
    }

    public int getPublisherLoggerLevel() {
        return this.mPublisher;
    }

    public int getConsoleLoggerLevel() {
        return this.mConsole;
    }
}
