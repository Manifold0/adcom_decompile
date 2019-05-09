// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.logger;

import java.util.Iterator;
import java.util.ArrayList;

public class IronSourceLoggerManager extends IronSourceLogger implements LogListener
{
    private static IronSourceLoggerManager mInstance;
    private boolean mIsDebugEnabled;
    private ArrayList<IronSourceLogger> mLoggers;
    
    private IronSourceLoggerManager(final String s) {
        super(s);
        this.mIsDebugEnabled = false;
        this.mLoggers = new ArrayList<IronSourceLogger>();
        this.initSubLoggers();
    }
    
    private IronSourceLoggerManager(final String s, final int n) {
        super(s, n);
        this.mIsDebugEnabled = false;
        this.mLoggers = new ArrayList<IronSourceLogger>();
        this.initSubLoggers();
    }
    
    private IronSourceLogger findLoggerByName(final String s) {
        final IronSourceLogger ironSourceLogger = null;
        final Iterator<IronSourceLogger> iterator = this.mLoggers.iterator();
        IronSourceLogger ironSourceLogger2;
        do {
            ironSourceLogger2 = ironSourceLogger;
            if (!iterator.hasNext()) {
                break;
            }
            ironSourceLogger2 = iterator.next();
        } while (!ironSourceLogger2.getLoggerName().equals(s));
        return ironSourceLogger2;
    }
    
    public static IronSourceLoggerManager getLogger() {
        synchronized (IronSourceLoggerManager.class) {
            if (IronSourceLoggerManager.mInstance == null) {
                IronSourceLoggerManager.mInstance = new IronSourceLoggerManager(IronSourceLoggerManager.class.getSimpleName());
            }
            return IronSourceLoggerManager.mInstance;
        }
    }
    
    public static IronSourceLoggerManager getLogger(final int mDebugLevel) {
        synchronized (IronSourceLoggerManager.class) {
            if (IronSourceLoggerManager.mInstance == null) {
                IronSourceLoggerManager.mInstance = new IronSourceLoggerManager(IronSourceLoggerManager.class.getSimpleName());
            }
            else {
                IronSourceLoggerManager.mInstance.mDebugLevel = mDebugLevel;
            }
            return IronSourceLoggerManager.mInstance;
        }
    }
    
    private void initSubLoggers() {
        this.mLoggers.add(new ConsoleLogger(1));
    }
    
    public void addLogger(final IronSourceLogger ironSourceLogger) {
        this.mLoggers.add(ironSourceLogger);
    }
    
    public boolean isDebugEnabled() {
        return this.mIsDebugEnabled;
    }
    
    @Override
    public void log(final IronSourceTag ironSourceTag, final String s, final int n) {
        synchronized (this) {
            if (n >= this.mDebugLevel) {
                for (final IronSourceLogger ironSourceLogger : this.mLoggers) {
                    if (ironSourceLogger.getDebugLevel() <= n) {
                        ironSourceLogger.log(ironSourceTag, s, n);
                    }
                }
            }
        }
    }
    
    @Override
    public void logException(final IronSourceTag ironSourceTag, final String s, final Throwable t) {
        // monitorenter(this)
        Label_0084: {
            if (t == null) {
                try {
                    final Iterator<IronSourceLogger> iterator = this.mLoggers.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().log(ironSourceTag, s, 3);
                    }
                    break Label_0084;
                }
                finally {
                }
                // monitorexit(this)
            }
            final Iterator<IronSourceLogger> iterator2 = this.mLoggers.iterator();
            while (iterator2.hasNext()) {
                iterator2.next().logException(ironSourceTag, s, t);
            }
        }
    }
    // monitorexit(this)
    
    @Override
    public void onLog(final IronSourceTag ironSourceTag, final String s, final int n) {
        synchronized (this) {
            this.log(ironSourceTag, s, n);
        }
    }
    
    public void setAdaptersDebug(final boolean mIsDebugEnabled) {
        this.mIsDebugEnabled = mIsDebugEnabled;
    }
    
    public void setLoggerDebugLevel(final String s, final int debugLevel) {
        if (s == null) {
            return;
        }
        final IronSourceLogger loggerByName = this.findLoggerByName(s);
        if (loggerByName == null) {
            this.log(IronSourceTag.NATIVE, "Failed to find logger:setLoggerDebugLevel(loggerName:" + s + " ,debugLevel:" + debugLevel + ")", 0);
            return;
        }
        if (debugLevel >= 0 && debugLevel <= 3) {
            this.log(IronSourceTag.NATIVE, "setLoggerDebugLevel(loggerName:" + s + " ,debugLevel:" + debugLevel + ")", 0);
            loggerByName.setDebugLevel(debugLevel);
            return;
        }
        this.mLoggers.remove(loggerByName);
    }
}
