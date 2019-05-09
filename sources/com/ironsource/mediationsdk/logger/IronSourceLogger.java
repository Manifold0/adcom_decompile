package com.ironsource.mediationsdk.logger;

public abstract class IronSourceLogger {
    int mDebugLevel;
    private String mLoggerName;

    public class IronSourceLogLevel {
        public static final int ERROR = 3;
        public static final int INFO = 1;
        public static final int VERBOSE = 0;
        public static final int WARNING = 2;
    }

    public enum IronSourceTag {
        API,
        ADAPTER_API,
        CALLBACK,
        ADAPTER_CALLBACK,
        NETWORK,
        INTERNAL,
        NATIVE,
        EVENT
    }

    public abstract void log(IronSourceTag ironSourceTag, String str, int i);

    public abstract void logException(IronSourceTag ironSourceTag, String str, Throwable th);

    IronSourceLogger(String loggerName) {
        this.mLoggerName = loggerName;
        this.mDebugLevel = 0;
    }

    IronSourceLogger(String loggerName, int debugLevel) {
        this.mLoggerName = loggerName;
        this.mDebugLevel = debugLevel;
    }

    String getLoggerName() {
        return this.mLoggerName;
    }

    int getDebugLevel() {
        return this.mDebugLevel;
    }

    public void setDebugLevel(int debugLevel) {
        this.mDebugLevel = debugLevel;
    }

    public boolean equals(Object other) {
        if (other == null || !(other instanceof IronSourceLogger)) {
            return false;
        }
        IronSourceLogger otherLogger = (IronSourceLogger) other;
        if (this.mLoggerName == null || !this.mLoggerName.equals(otherLogger.mLoggerName)) {
            return false;
        }
        return true;
    }
}
