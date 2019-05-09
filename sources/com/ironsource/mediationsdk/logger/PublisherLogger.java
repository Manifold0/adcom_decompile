package com.ironsource.mediationsdk.logger;

import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;

public class PublisherLogger extends IronSourceLogger {
    private static final String NAME = "publisher";
    private LogListener mLogListener;

    private PublisherLogger() {
        super(NAME);
    }

    public PublisherLogger(LogListener logListener, int debugLevel) {
        super(NAME, debugLevel);
        this.mLogListener = logListener;
    }

    public synchronized void log(IronSourceTag tag, String message, int logLevel) {
        if (!(this.mLogListener == null || message == null)) {
            this.mLogListener.onLog(tag, message, logLevel);
        }
    }

    public void logException(IronSourceTag tag, String message, Throwable e) {
        if (e != null) {
            log(tag, e.getMessage(), 3);
        }
    }

    public void setLogListener(LogListener listener) {
        this.mLogListener = listener;
    }
}
