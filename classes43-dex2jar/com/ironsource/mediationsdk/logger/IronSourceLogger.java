// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.logger;

public abstract class IronSourceLogger
{
    int mDebugLevel;
    private String mLoggerName;
    
    IronSourceLogger(final String mLoggerName) {
        this.mLoggerName = mLoggerName;
        this.mDebugLevel = 0;
    }
    
    IronSourceLogger(final String mLoggerName, final int mDebugLevel) {
        this.mLoggerName = mLoggerName;
        this.mDebugLevel = mDebugLevel;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b2;
        final boolean b = b2 = false;
        if (o != null) {
            b2 = b;
            if (o instanceof IronSourceLogger) {
                final IronSourceLogger ironSourceLogger = (IronSourceLogger)o;
                b2 = b;
                if (this.mLoggerName != null) {
                    b2 = b;
                    if (this.mLoggerName.equals(ironSourceLogger.mLoggerName)) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    int getDebugLevel() {
        return this.mDebugLevel;
    }
    
    String getLoggerName() {
        return this.mLoggerName;
    }
    
    public abstract void log(final IronSourceTag p0, final String p1, final int p2);
    
    public abstract void logException(final IronSourceTag p0, final String p1, final Throwable p2);
    
    public void setDebugLevel(final int mDebugLevel) {
        this.mDebugLevel = mDebugLevel;
    }
    
    public class IronSourceLogLevel
    {
        public static final int ERROR = 3;
        public static final int INFO = 1;
        public static final int VERBOSE = 0;
        public static final int WARNING = 2;
    }
    
    public enum IronSourceTag
    {
        ADAPTER_API, 
        ADAPTER_CALLBACK, 
        API, 
        CALLBACK, 
        EVENT, 
        INTERNAL, 
        NATIVE, 
        NETWORK;
    }
}
