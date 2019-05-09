// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.logger;

import android.util.Log;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ServerLogger extends IronSourceLogger
{
    public static final String NAME = "server";
    private final int SERVER_LOGS_SIZE_LIMIT;
    private ArrayList<ServerLogEntry> mLogs;
    
    public ServerLogger() {
        super("server");
        this.SERVER_LOGS_SIZE_LIMIT = 1000;
        this.mLogs = new ArrayList<ServerLogEntry>();
    }
    
    public ServerLogger(final int n) {
        super("server", n);
        this.SERVER_LOGS_SIZE_LIMIT = 1000;
        this.mLogs = new ArrayList<ServerLogEntry>();
    }
    
    private void addLogEntry(final ServerLogEntry serverLogEntry) {
        synchronized (this) {
            this.mLogs.add(serverLogEntry);
            if (this.shouldSendLogs()) {
                this.send();
            }
            else if (this.mLogs.size() > 1000) {
                try {
                    final ArrayList<ServerLogEntry> mLogs = new ArrayList<ServerLogEntry>();
                    for (int i = 500; i < this.mLogs.size(); ++i) {
                        mLogs.add(this.mLogs.get(i));
                    }
                    this.mLogs = mLogs;
                }
                catch (Exception ex) {
                    this.mLogs = new ArrayList<ServerLogEntry>();
                }
            }
        }
    }
    
    private String getTimestamp() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
    }
    
    private void send() {
        IronSourceUtils.createAndStartWorker(new LogsSender(this.mLogs), "LogsSender");
        this.mLogs = new ArrayList<ServerLogEntry>();
    }
    
    private boolean shouldSendLogs() {
        return this.mLogs.get(this.mLogs.size() - 1).getLogLevel() == 3;
    }
    
    @Override
    public void log(final IronSourceTag ironSourceTag, final String s, final int n) {
        synchronized (this) {
            this.addLogEntry(new ServerLogEntry(ironSourceTag, this.getTimestamp(), s, n));
        }
    }
    
    @Override
    public void logException(final IronSourceTag ironSourceTag, final String s, final Throwable t) {
        synchronized (this) {
            final StringBuilder sb = new StringBuilder(s);
            if (t != null) {
                sb.append(":stacktrace[");
                sb.append(Log.getStackTraceString(t)).append("]");
            }
            this.addLogEntry(new ServerLogEntry(ironSourceTag, this.getTimestamp(), sb.toString(), 3));
        }
    }
    
    private class SendingCalc
    {
        private int DEFAULT_DEBUG_LEVEL;
        private int DEFAULT_SIZE;
        private int DEFAULT_TIME;
        
        public SendingCalc() {
            this.DEFAULT_SIZE = 1;
            this.DEFAULT_TIME = 1;
            this.DEFAULT_DEBUG_LEVEL = 3;
            this.initDefaults();
        }
        
        private boolean calc(final int n) {
            return this.error(n) || this.size() || this.time();
        }
        
        private boolean error(final int n) {
            return n == 3;
        }
        
        private void initDefaults() {
        }
        
        private boolean size() {
            return false;
        }
        
        private boolean time() {
            return false;
        }
        
        public void notifyEvent(final int n) {
            if (this.calc(n)) {
                ServerLogger.this.send();
            }
        }
    }
}
