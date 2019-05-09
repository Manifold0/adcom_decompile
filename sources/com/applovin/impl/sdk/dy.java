package com.applovin.impl.sdk;

import android.app.Application;
import android.content.Context;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class dy {
    /* renamed from: a */
    private static final String[] f2360a = new String[]{"paused", "saved_instance_state"};
    /* renamed from: b */
    private static final String[] f2361b = new String[]{"paused", "saved_instance_state", "stopped", ParametersKeys.VIDEO_STATUS_STARTED};
    /* renamed from: c */
    private static final String[] f2362c = new String[]{"paused", "stopped", "saved_instance_state", ParametersKeys.VIDEO_STATUS_STARTED};
    /* renamed from: d */
    private static final String[] f2363d = new String[]{"saved_instance_state", "paused", "stopped", ParametersKeys.VIDEO_STATUS_STARTED};
    /* renamed from: e */
    private final AppLovinSdkImpl f2364e;
    /* renamed from: f */
    private final List<String> f2365f = new ArrayList();
    /* renamed from: g */
    private final AtomicBoolean f2366g = new AtomicBoolean();
    /* renamed from: h */
    private final AtomicBoolean f2367h = new AtomicBoolean();
    /* renamed from: i */
    private final AtomicBoolean f2368i = new AtomicBoolean();
    /* renamed from: j */
    private Date f2369j;
    /* renamed from: k */
    private Date f2370k;

    dy(AppLovinSdkImpl appLovinSdkImpl) {
        this.f2364e = appLovinSdkImpl;
    }

    /* renamed from: a */
    private static boolean m2635a(List<String> list, String[] strArr) {
        int size = list.size();
        int length = strArr.length;
        if (size == 0 || length == 0) {
            return false;
        }
        if (size < strArr.length) {
            return false;
        }
        int i = size - length;
        for (int i2 = i; i2 < length; i2++) {
            if (!((String) list.get(i2)).equals(strArr[i2 - i])) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: e */
    private void m2639e() {
        this.f2365f.add("paused");
    }

    /* renamed from: f */
    private void m2641f() {
        this.f2365f.add("saved_instance_state");
    }

    /* renamed from: g */
    private void m2643g() {
        if (!this.f2368i.get()) {
            if (((Boolean) this.f2364e.get(ea.dp)).booleanValue() && m2635a(this.f2365f, f2360a)) {
                boolean booleanValue = ((Boolean) this.f2364e.get(ea.dm)).booleanValue();
                long toMillis = TimeUnit.MINUTES.toMillis(((Long) this.f2364e.get(ea.f2407do)).longValue());
                if (this.f2369j == null || System.currentTimeMillis() - this.f2369j.getTime() >= toMillis) {
                    ((EventServiceImpl) this.f2364e.getEventService()).m2164a("paused", false);
                    if (booleanValue) {
                        this.f2369j = new Date();
                    }
                }
                if (!booleanValue) {
                    this.f2369j = new Date();
                }
            }
            this.f2365f.add("stopped");
        }
    }

    /* renamed from: h */
    private void m2644h() {
        if (!this.f2365f.isEmpty()) {
            String str = (String) this.f2365f.get(this.f2365f.size() - 1);
            if ("stopped".equals(str) || "saved_instance_state".equals(str)) {
                this.f2365f.add(ParametersKeys.VIDEO_STATUS_STARTED);
            } else {
                this.f2365f.clear();
            }
        }
    }

    /* renamed from: i */
    private void m2645i() {
        if (!this.f2368i.getAndSet(false)) {
            if (m2635a(this.f2365f, f2361b) || m2635a(this.f2365f, f2362c) || m2635a(this.f2365f, f2363d)) {
                boolean booleanValue = ((Boolean) this.f2364e.get(ea.dm)).booleanValue();
                long toMillis = TimeUnit.MINUTES.toMillis(((Long) this.f2364e.get(ea.dn)).longValue());
                if (this.f2370k == null || System.currentTimeMillis() - this.f2370k.getTime() >= toMillis) {
                    ((EventServiceImpl) this.f2364e.getEventService()).m2164a("resumed", false);
                    if (booleanValue) {
                        this.f2370k = new Date();
                    }
                }
                if (!booleanValue) {
                    this.f2370k = new Date();
                }
                this.f2364e.m2139a().m2310a("app_paused_and_resumed");
                this.f2367h.set(true);
            }
            this.f2365f.clear();
        }
    }

    /* renamed from: j */
    private void m2646j() {
        this.f2365f.clear();
    }

    /* renamed from: a */
    public void m2647a() {
        this.f2368i.set(true);
    }

    /* renamed from: a */
    void m2648a(Context context) {
        if (context != null && ab.m2203c() && ((Boolean) this.f2364e.get(ea.dl)).booleanValue() && !this.f2366g.getAndSet(true)) {
            ((Application) (context instanceof Application ? context : context.getApplicationContext())).registerActivityLifecycleCallbacks(new dz(this));
        }
    }

    /* renamed from: b */
    public void m2649b() {
        this.f2368i.set(false);
    }

    /* renamed from: c */
    boolean m2650c() {
        return this.f2366g.get();
    }

    /* renamed from: d */
    boolean m2651d() {
        return this.f2367h.getAndSet(false);
    }
}
