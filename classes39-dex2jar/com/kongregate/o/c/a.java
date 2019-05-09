// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.c;

import android.util.AndroidRuntimeException;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import com.kongregate.android.internal.util.j;
import android.os.Looper;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import android.os.AsyncTask;

public final class a extends AsyncTask<Void, Void, Void>
{
    private static final f a;
    private final Runnable b;
    
    static {
        a = new f(1, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), Executors.defaultThreadFactory());
    }
    
    private a(final Runnable b) {
        this.b = b;
        this.a();
    }
    
    private void a() {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            try {
                throw new RuntimeException();
            }
            catch (RuntimeException ex) {
                j.c("BackgroundTask.execute called from non-UI thread", ex);
            }
        }
    }
    
    public static void a(final Runnable runnable) {
        a(runnable, false);
    }
    
    public static void a(final Runnable runnable, final boolean b) {
        com.kongregate.android.internal.util.a.a(com.kongregate.o.c.a.a);
        try {
            try {
                new a(runnable).execute((Object[])new Void[0]);
                return;
            }
            catch (RejectedExecutionException ex) {
                j.c("Task execution rejected, running with backup executor", ex);
            }
            catch (AndroidRuntimeException ex2) {
                if (ex2.getClass().getName().equals("android.view.ViewRoot$CalledFromWrongThreadException")) {
                    j.c("CalledFromWrongThreadException in BackgroundTask, running with backup executor", (Throwable)ex2);
                    goto Label_0030;
                }
                throw ex2;
            }
        }
        catch (OutOfMemoryError outOfMemoryError2) {}
        final OutOfMemoryError outOfMemoryError;
        j.c("OOM thrown inside non-critical background task", outOfMemoryError);
    }
    
    protected Void a(final Void... array) {
        this.b.run();
        return null;
    }
}
