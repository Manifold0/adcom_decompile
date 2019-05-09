// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.logger;

public class ThreadExceptionHandler implements UncaughtExceptionHandler
{
    @Override
    public void uncaughtException(final Thread thread, final Throwable t) {
        IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "Thread name =" + thread.getName(), t);
    }
}
