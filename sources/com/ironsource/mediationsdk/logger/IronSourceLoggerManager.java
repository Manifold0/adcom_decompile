package com.ironsource.mediationsdk.logger;

import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import java.util.ArrayList;
import java.util.Iterator;

public class IronSourceLoggerManager extends IronSourceLogger implements LogListener {
    private static IronSourceLoggerManager mInstance;
    private boolean mIsDebugEnabled = false;
    private ArrayList<IronSourceLogger> mLoggers = new ArrayList();

    private IronSourceLoggerManager(String loggerName) {
        super(loggerName);
        initSubLoggers();
    }

    private IronSourceLoggerManager(String loggerName, int debugLevel) {
        super(loggerName, debugLevel);
        initSubLoggers();
    }

    private void initSubLoggers() {
        this.mLoggers.add(new ConsoleLogger(1));
    }

    public static synchronized IronSourceLoggerManager getLogger() {
        IronSourceLoggerManager ironSourceLoggerManager;
        synchronized (IronSourceLoggerManager.class) {
            if (mInstance == null) {
                mInstance = new IronSourceLoggerManager(IronSourceLoggerManager.class.getSimpleName());
            }
            ironSourceLoggerManager = mInstance;
        }
        return ironSourceLoggerManager;
    }

    public static synchronized IronSourceLoggerManager getLogger(int debugLevel) {
        IronSourceLoggerManager ironSourceLoggerManager;
        synchronized (IronSourceLoggerManager.class) {
            if (mInstance == null) {
                mInstance = new IronSourceLoggerManager(IronSourceLoggerManager.class.getSimpleName());
            } else {
                mInstance.mDebugLevel = debugLevel;
            }
            ironSourceLoggerManager = mInstance;
        }
        return ironSourceLoggerManager;
    }

    public void addLogger(IronSourceLogger toAdd) {
        this.mLoggers.add(toAdd);
    }

    public synchronized void log(IronSourceTag tag, String message, int logLevel) {
        if (logLevel >= this.mDebugLevel) {
            Iterator it = this.mLoggers.iterator();
            while (it.hasNext()) {
                IronSourceLogger logger = (IronSourceLogger) it.next();
                if (logger.getDebugLevel() <= logLevel) {
                    logger.log(tag, message, logLevel);
                }
            }
        }
    }

    public synchronized void onLog(IronSourceTag tag, String message, int logLevel) {
        log(tag, message, logLevel);
    }

    public synchronized void logException(IronSourceTag tag, String message, Throwable e) {
        Iterator it;
        if (e == null) {
            it = this.mLoggers.iterator();
            while (it.hasNext()) {
                ((IronSourceLogger) it.next()).log(tag, message, 3);
            }
        } else {
            it = this.mLoggers.iterator();
            while (it.hasNext()) {
                ((IronSourceLogger) it.next()).logException(tag, message, e);
            }
        }
    }

    private IronSourceLogger findLoggerByName(String loggerName) {
        Iterator it = this.mLoggers.iterator();
        while (it.hasNext()) {
            IronSourceLogger logger = (IronSourceLogger) it.next();
            if (logger.getLoggerName().equals(loggerName)) {
                return logger;
            }
        }
        return null;
    }

    public void setLoggerDebugLevel(String loggerName, int debugLevel) {
        if (loggerName != null) {
            IronSourceLogger logger = findLoggerByName(loggerName);
            if (logger == null) {
                log(IronSourceTag.NATIVE, "Failed to find logger:setLoggerDebugLevel(loggerName:" + loggerName + " ,debugLevel:" + debugLevel + ")", 0);
            } else if (debugLevel < 0 || debugLevel > 3) {
                this.mLoggers.remove(logger);
            } else {
                log(IronSourceTag.NATIVE, "setLoggerDebugLevel(loggerName:" + loggerName + " ,debugLevel:" + debugLevel + ")", 0);
                logger.setDebugLevel(debugLevel);
            }
        }
    }

    public boolean isDebugEnabled() {
        return this.mIsDebugEnabled;
    }

    public void setAdaptersDebug(boolean enabled) {
        this.mIsDebugEnabled = enabled;
    }
}
