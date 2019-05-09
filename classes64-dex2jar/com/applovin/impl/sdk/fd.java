// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import com.applovin.sdk.AppLovinLogger;

public class fd
{
    private final String a;
    private final AppLovinSdkImpl b;
    private final AppLovinLogger c;
    private final ScheduledThreadPoolExecutor d;
    private final ScheduledThreadPoolExecutor e;
    private final ScheduledThreadPoolExecutor f;
    private final List<fh> g;
    private final Object h;
    private boolean i;
    
    fd(final AppLovinSdkImpl b) {
        this.a = "TaskManager";
        this.g = new ArrayList<fh>(5);
        this.h = new Object();
        this.b = b;
        this.c = b.getLogger();
        this.d = this.a("main");
        this.e = this.a("back");
        this.f = this.a("postbacks");
    }
    
    private long a(final fe fe) {
        if (fe == fe.a) {
            return this.d.getTaskCount() - this.d.getCompletedTaskCount();
        }
        if (fe == fe.b) {
            return this.e.getTaskCount() - this.e.getCompletedTaskCount();
        }
        if (fe == fe.c) {
            return this.f.getTaskCount() - this.f.getCompletedTaskCount();
        }
        return 0L;
    }
    
    private ScheduledThreadPoolExecutor a(final String s) {
        return new ScheduledThreadPoolExecutor(1, new ff(this, s));
    }
    
    private static void a(final Runnable runnable, final long n, final ScheduledExecutorService scheduledExecutorService) {
        if (n > 0L) {
            scheduledExecutorService.schedule(runnable, n, TimeUnit.MILLISECONDS);
            return;
        }
        scheduledExecutorService.submit(runnable);
    }
    
    private boolean a(final fh fh) {
        if (fh.c.g) {
            return false;
        }
        synchronized (this.h) {
            if (this.i) {
                return false;
            }
        }
        final fh fh2;
        this.g.add(fh2);
        // monitorexit(o)
        return true;
    }
    
    void a() {
        synchronized (this.h) {
            this.i = false;
        }
    }
    
    void a(final dx dx) {
        if (dx != null) {
            try {
                this.c.i("TaskManager", "Executing " + dx.a() + " immediately...");
                dx.run();
                this.c.i("TaskManager", dx.a() + " finished executing...");
                return;
            }
            catch (Throwable t) {
                this.c.e("TaskManager", "Task failed execution", t);
                return;
            }
        }
        this.c.e("TaskManager", "Attempted to execute null task immediately");
    }
    
    public void a(final dx dx, final fe fe) {
        this.a(dx, fe, 0L);
    }
    
    void a(final dx dx, final fe fe, final long n) {
        if (dx == null) {
            throw new IllegalArgumentException("No task specified");
        }
        if (n < 0L) {
            throw new IllegalArgumentException("Invalid delay specified: " + n);
        }
        if (fe != fe.a && fe != fe.b && fe != fe.c) {
            throw new IllegalArgumentException("Invalid queue specified");
        }
        final fh fh = new fh(this, dx, fe);
        if (!this.a(fh)) {
            this.c.d("TaskManager", "Scheduling " + dx.c + " on " + fe + " queue in " + n + "ms with new queue size " + (this.a(fe) + 1L));
            if (fe == fe.a) {
                a(fh, n, this.d);
            }
            else {
                if (fe == fe.b) {
                    a(fh, n, this.e);
                    return;
                }
                if (fe == fe.c) {
                    a(fh, n, this.f);
                }
            }
            return;
        }
        this.c.i(dx.a(), "Task " + dx.a() + " execution delayed until after init");
    }
    
    void b() {
        synchronized (this.h) {
            this.i = true;
            for (final fh fh : this.g) {
                this.a(fh.c, fh.d);
            }
        }
        this.g.clear();
    }
    // monitorexit(o)
}
