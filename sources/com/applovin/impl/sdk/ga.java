package com.applovin.impl.sdk;

import java.util.Timer;
import java.util.TimerTask;

public class ga {
    /* renamed from: a */
    private final AppLovinSdkImpl f2556a;
    /* renamed from: b */
    private Timer f2557b;
    /* renamed from: c */
    private long f2558c;
    /* renamed from: d */
    private long f2559d;
    /* renamed from: e */
    private final Runnable f2560e;
    /* renamed from: f */
    private long f2561f;
    /* renamed from: g */
    private final Object f2562g = new Object();

    private ga(AppLovinSdkImpl appLovinSdkImpl, Runnable runnable) {
        this.f2556a = appLovinSdkImpl;
        this.f2560e = runnable;
    }

    /* renamed from: a */
    public static ga m2918a(long j, AppLovinSdkImpl appLovinSdkImpl, Runnable runnable) {
        if (j < 0) {
            throw new IllegalArgumentException("Cannot create a scheduled timer. Invalid fire time passed in: " + j + ".");
        } else if (runnable == null) {
            throw new IllegalArgumentException("Cannot create a scheduled timer. Runnable is null.");
        } else {
            ga gaVar = new ga(appLovinSdkImpl, runnable);
            gaVar.f2558c = System.currentTimeMillis();
            gaVar.f2559d = j;
            gaVar.f2557b = new Timer();
            gaVar.f2557b.schedule(gaVar.m2923c(), j);
            return gaVar;
        }
    }

    /* renamed from: c */
    private TimerTask m2923c() {
        return new gb(this);
    }

    /* renamed from: a */
    public void m2924a() {
        synchronized (this.f2562g) {
            if (this.f2557b != null) {
                try {
                    this.f2557b.cancel();
                    this.f2561f = System.currentTimeMillis() - this.f2558c;
                    this.f2557b = null;
                } catch (Throwable th) {
                    this.f2557b = null;
                }
            }
        }
    }

    /* renamed from: b */
    public void m2925b() {
        synchronized (this.f2562g) {
            if (this.f2561f > 0) {
                try {
                    this.f2559d -= this.f2561f;
                    if (this.f2559d < 0) {
                        this.f2559d = 0;
                    }
                    this.f2557b = new Timer();
                    this.f2557b.schedule(m2923c(), this.f2559d);
                    this.f2558c = System.currentTimeMillis();
                    this.f2561f = 0;
                } catch (Throwable th) {
                    this.f2561f = 0;
                }
            }
        }
    }
}
