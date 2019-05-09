// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.concurrent.ThreadFactory;

class ff implements ThreadFactory
{
    final /* synthetic */ fd a;
    private final String b;
    
    public ff(final fd a, final String b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        final Thread thread = new Thread(runnable, "AppLovinSdk:" + this.b + ":" + gd.a(this.a.b.getSdkKey()));
        thread.setDaemon(true);
        thread.setPriority(10);
        thread.setUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new fg(this));
        return thread;
    }
}
