// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.model;

public class ApplicationLogger
{
    private int mConsole;
    private int mPublisher;
    private int mServer;
    
    public ApplicationLogger() {
    }
    
    public ApplicationLogger(final int mServer, final int mPublisher, final int mConsole) {
        this.mServer = mServer;
        this.mPublisher = mPublisher;
        this.mConsole = mConsole;
    }
    
    public int getConsoleLoggerLevel() {
        return this.mConsole;
    }
    
    public int getPublisherLoggerLevel() {
        return this.mPublisher;
    }
    
    public int getServerLoggerLevel() {
        return this.mServer;
    }
}
