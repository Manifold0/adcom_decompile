// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.request;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class CancelableThreadPoolExecutor extends ThreadPoolExecutor
{
    private final List<Runnable> _activeRunnable;
    
    public CancelableThreadPoolExecutor(final int n, final int n2, final long n3, final TimeUnit timeUnit, final LinkedBlockingQueue<Runnable> linkedBlockingQueue) {
        super(n, n2, n3, timeUnit, linkedBlockingQueue);
        this._activeRunnable = new LinkedList<Runnable>();
    }
    
    @Override
    protected void afterExecute(final Runnable runnable, final Throwable t) {
        synchronized (this) {
            super.afterExecute(runnable, t);
            this._activeRunnable.remove(runnable);
        }
    }
    
    @Override
    protected void beforeExecute(final Thread thread, final Runnable runnable) {
        synchronized (this) {
            super.beforeExecute(thread, runnable);
            this._activeRunnable.add(runnable);
        }
    }
    
    public void cancel() {
        synchronized (this) {
            for (final Runnable runnable : this._activeRunnable) {
                if (runnable instanceof WebRequestRunnable) {
                    ((WebRequestRunnable)runnable).setCancelStatus(true);
                }
            }
        }
    }
    // monitorexit(this)
}
