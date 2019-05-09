package com.ironsource.mediationsdk.logger;

import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import java.lang.Thread.UncaughtExceptionHandler;

public class ThreadExceptionHandler implements UncaughtExceptionHandler {
    public void uncaughtException(Thread thread, Throwable ex) {
        IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, "Thread name =" + thread.getName(), ex);
    }
}
