package com.applovin.impl.adview;

import android.os.Handler;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public final class am {
    /* renamed from: a */
    private final AppLovinLogger f1767a;
    /* renamed from: b */
    private final Handler f1768b;
    /* renamed from: c */
    private final Set<ap> f1769c = new HashSet();
    /* renamed from: d */
    private final AtomicInteger f1770d = new AtomicInteger();

    public am(Handler handler, AppLovinSdk appLovinSdk) {
        if (handler == null) {
            throw new IllegalArgumentException("No handler specified.");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified.");
        } else {
            this.f1768b = handler;
            this.f1767a = appLovinSdk.getLogger();
        }
    }

    /* renamed from: a */
    private void m2011a(ap apVar, int i) {
        this.f1768b.postDelayed(new an(this, apVar, i), apVar.m2021b());
    }

    /* renamed from: a */
    public void m2013a() {
        Set<ap> hashSet = new HashSet(this.f1769c);
        this.f1767a.mo4172d("CountdownManager", "Starting " + hashSet.size() + " countdowns...");
        int incrementAndGet = this.f1770d.incrementAndGet();
        for (ap apVar : hashSet) {
            this.f1767a.mo4172d("CountdownManager", "Starting countdown: " + apVar.m2019a() + " for generation " + incrementAndGet + "...");
            m2011a(apVar, incrementAndGet);
        }
    }

    /* renamed from: a */
    public void m2014a(String str, long j, ao aoVar) {
        if (j <= 0) {
            throw new IllegalArgumentException("Invalid step specified.");
        } else if (this.f1768b == null) {
            throw new IllegalArgumentException("No handler specified.");
        } else {
            this.f1767a.mo4172d("CountdownManager", "Adding countdown: " + str);
            this.f1769c.add(new ap(str, j, aoVar));
        }
    }

    /* renamed from: b */
    public void m2015b() {
        this.f1767a.mo4172d("CountdownManager", "Removing all countdowns...");
        m2016c();
        this.f1769c.clear();
    }

    /* renamed from: c */
    public void m2016c() {
        this.f1767a.mo4172d("CountdownManager", "Stopping countdowns...");
        this.f1770d.incrementAndGet();
        this.f1768b.removeCallbacksAndMessages(null);
    }
}
