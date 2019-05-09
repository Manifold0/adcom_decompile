// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

class fg implements UncaughtExceptionHandler
{
    final /* synthetic */ ff a;
    
    fg(final ff a) {
        this.a = a;
    }
    
    @Override
    public void uncaughtException(final Thread thread, final Throwable t) {
        this.a.a.c.e("TaskManager", "Caught unhandled exception", t);
    }
}
