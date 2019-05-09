// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ThreadFactory;

public class n implements ThreadFactory
{
    protected final AtomicLong a;
    private int b;
    
    public n() {
        this.a = new AtomicLong();
        this.b = Thread.currentThread().getPriority();
    }
    
    protected String a() {
        return String.format(Locale.US, "com.facebook.ads thread-%d %tF %<tT", this.a.incrementAndGet(), System.currentTimeMillis());
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        final Thread thread = new Thread(null, runnable, this.a(), 0L);
        thread.setPriority(this.b);
        return thread;
    }
}
