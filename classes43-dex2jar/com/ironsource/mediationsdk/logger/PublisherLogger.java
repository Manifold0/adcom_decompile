// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.logger;

public class PublisherLogger extends IronSourceLogger
{
    private static final String NAME = "publisher";
    private LogListener mLogListener;
    
    private PublisherLogger() {
        super("publisher");
    }
    
    public PublisherLogger(final LogListener mLogListener, final int n) {
        super("publisher", n);
        this.mLogListener = mLogListener;
    }
    
    @Override
    public void log(final IronSourceTag ironSourceTag, final String s, final int n) {
        synchronized (this) {
            if (this.mLogListener != null && s != null) {
                this.mLogListener.onLog(ironSourceTag, s, n);
            }
        }
    }
    
    @Override
    public void logException(final IronSourceTag ironSourceTag, final String s, final Throwable t) {
        if (t != null) {
            this.log(ironSourceTag, t.getMessage(), 3);
        }
    }
    
    public void setLogListener(final LogListener mLogListener) {
        this.mLogListener = mLogListener;
    }
}
