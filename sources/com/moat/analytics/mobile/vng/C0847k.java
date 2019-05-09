package com.moat.analytics.mobile.vng;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import com.moat.analytics.mobile.vng.C0834g.C0833a;
import com.moat.analytics.mobile.vng.C0879w.C0846b;
import com.moat.analytics.mobile.vng.C0879w.C0878d;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.vng.k */
class C0847k extends MoatAnalytics implements C0846b {
    /* renamed from: a */
    boolean f1423a = false;
    /* renamed from: b */
    boolean f1424b = false;
    /* renamed from: c */
    boolean f1425c = false;
    @Nullable
    /* renamed from: d */
    C0834g f1426d;
    /* renamed from: e */
    WeakReference<Context> f1427e;
    /* renamed from: f */
    private boolean f1428f = false;
    /* renamed from: g */
    private String f1429g;

    C0847k() {
    }

    /* renamed from: a */
    private void m1529a(MoatOptions moatOptions, Application application) {
        if (this.f1428f) {
            C0858p.m1577a(3, "Analytics", (Object) this, "Moat SDK has already been started.");
            return;
        }
        C0879w.m1610a().m1623b();
        if (moatOptions.loggingEnabled && C0847k.m1530a(application.getApplicationContext())) {
            this.f1423a = true;
        }
        this.f1425c = moatOptions.disableLocationServices;
        if (application == null) {
            C0858p.m1579a("[ERROR] ", "Moat Analytics SDK didn't start, application was null");
            return;
        }
        this.f1427e = new WeakReference(application.getApplicationContext());
        this.f1428f = true;
        this.f1424b = moatOptions.autoTrackGMAInterstitials;
        C0821a.m1440a(application);
        C0879w.m1610a().m1622a((C0846b) this);
        if (!moatOptions.disableAdIdCollection) {
            C0862s.m1588a((Context) application);
        }
        C0858p.m1579a("[SUCCESS] ", "Moat Analytics SDK Version 2.2.0 started");
    }

    /* renamed from: a */
    private static boolean m1530a(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    /* renamed from: d */
    private void m1531d() {
        if (this.f1426d == null) {
            this.f1426d = new C0834g(C0821a.m1439a(), C0833a.DISPLAY);
            this.f1426d.m1477a(this.f1429g);
            C0858p.m1577a(3, "Analytics", (Object) this, "Preparing native display tracking with partner code " + this.f1429g);
            C0858p.m1579a("[SUCCESS] ", "Prepared for native display tracking with partner code " + this.f1429g);
        }
    }

    /* renamed from: a */
    boolean m1532a() {
        return this.f1428f;
    }

    /* renamed from: b */
    public void mo1669b() {
        C0857o.m1554a();
        if (this.f1429g != null) {
            try {
                m1531d();
            } catch (Exception e) {
                C0849m.m1543a(e);
            }
        }
    }

    /* renamed from: c */
    public void mo1670c() {
    }

    public void prepareNativeDisplayTracking(String str) {
        this.f1429g = str;
        if (C0879w.m1610a().f1478a != C0878d.OFF) {
            try {
                m1531d();
            } catch (Exception e) {
                C0849m.m1543a(e);
            }
        }
    }

    public void start(Application application) {
        start(new MoatOptions(), application);
    }

    public void start(MoatOptions moatOptions, Application application) {
        try {
            m1529a(moatOptions, application);
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
    }
}
