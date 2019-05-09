package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinLogger;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class fd {
    /* renamed from: a */
    private final String f2495a = "TaskManager";
    /* renamed from: b */
    private final AppLovinSdkImpl f2496b;
    /* renamed from: c */
    private final AppLovinLogger f2497c;
    /* renamed from: d */
    private final ScheduledThreadPoolExecutor f2498d;
    /* renamed from: e */
    private final ScheduledThreadPoolExecutor f2499e;
    /* renamed from: f */
    private final ScheduledThreadPoolExecutor f2500f;
    /* renamed from: g */
    private final List<fh> f2501g = new ArrayList(5);
    /* renamed from: h */
    private final Object f2502h = new Object();
    /* renamed from: i */
    private boolean f2503i;

    fd(AppLovinSdkImpl appLovinSdkImpl) {
        this.f2496b = appLovinSdkImpl;
        this.f2497c = appLovinSdkImpl.getLogger();
        this.f2498d = m2849a(ParametersKeys.MAIN);
        this.f2499e = m2849a("back");
        this.f2500f = m2849a("postbacks");
    }

    /* renamed from: a */
    private long m2847a(fe feVar) {
        return feVar == fe.MAIN ? this.f2498d.getTaskCount() - this.f2498d.getCompletedTaskCount() : feVar == fe.BACKGROUND ? this.f2499e.getTaskCount() - this.f2499e.getCompletedTaskCount() : feVar == fe.POSTBACKS ? this.f2500f.getTaskCount() - this.f2500f.getCompletedTaskCount() : 0;
    }

    /* renamed from: a */
    private ScheduledThreadPoolExecutor m2849a(String str) {
        return new ScheduledThreadPoolExecutor(1, new ff(this, str));
    }

    /* renamed from: a */
    private static void m2850a(Runnable runnable, long j, ScheduledExecutorService scheduledExecutorService) {
        if (j > 0) {
            scheduledExecutorService.schedule(runnable, j, TimeUnit.MILLISECONDS);
        } else {
            scheduledExecutorService.submit(runnable);
        }
    }

    /* renamed from: a */
    private boolean m2851a(fh fhVar) {
        boolean z = false;
        if (!fhVar.f2513c.f2261g) {
            synchronized (this.f2502h) {
                if (this.f2503i) {
                } else {
                    this.f2501g.add(fhVar);
                    z = true;
                }
            }
        }
        return z;
    }

    /* renamed from: a */
    void m2853a() {
        synchronized (this.f2502h) {
            this.f2503i = false;
        }
    }

    /* renamed from: a */
    void m2854a(dx dxVar) {
        if (dxVar != null) {
            try {
                this.f2497c.mo4175i("TaskManager", "Executing " + dxVar.m2482a() + " immediately...");
                dxVar.run();
                this.f2497c.mo4175i("TaskManager", dxVar.m2482a() + " finished executing...");
                return;
            } catch (Throwable th) {
                this.f2497c.mo4174e("TaskManager", "Task failed execution", th);
                return;
            }
        }
        this.f2497c.mo4173e("TaskManager", "Attempted to execute null task immediately");
    }

    /* renamed from: a */
    public void m2855a(dx dxVar, fe feVar) {
        m2856a(dxVar, feVar, 0);
    }

    /* renamed from: a */
    void m2856a(dx dxVar, fe feVar, long j) {
        if (dxVar == null) {
            throw new IllegalArgumentException("No task specified");
        } else if (j < 0) {
            throw new IllegalArgumentException("Invalid delay specified: " + j);
        } else if (feVar == fe.MAIN || feVar == fe.BACKGROUND || feVar == fe.POSTBACKS) {
            Runnable fhVar = new fh(this, dxVar, feVar);
            if (m2851a((fh) fhVar)) {
                this.f2497c.mo4175i(dxVar.m2482a(), "Task " + dxVar.m2482a() + " execution delayed until after init");
                return;
            }
            this.f2497c.mo4172d("TaskManager", "Scheduling " + dxVar.f2257c + " on " + feVar + " queue in " + j + "ms with new queue size " + (m2847a(feVar) + 1));
            if (feVar == fe.MAIN) {
                m2850a(fhVar, j, this.f2498d);
            } else if (feVar == fe.BACKGROUND) {
                m2850a(fhVar, j, this.f2499e);
            } else if (feVar == fe.POSTBACKS) {
                m2850a(fhVar, j, this.f2500f);
            }
        } else {
            throw new IllegalArgumentException("Invalid queue specified");
        }
    }

    /* renamed from: b */
    void m2857b() {
        synchronized (this.f2502h) {
            this.f2503i = true;
            for (fh fhVar : this.f2501g) {
                m2855a(fhVar.f2513c, fhVar.f2514d);
            }
            this.f2501g.clear();
        }
    }
}
