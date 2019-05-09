package com.moat.analytics.mobile.iro;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import com.moat.analytics.mobile.iro.C0756b.C0755a;
import com.moat.analytics.mobile.iro.C0803t.C0773a;
import com.moat.analytics.mobile.iro.C0803t.C0800c;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.iro.j */
final class C0774j extends MoatAnalytics implements C0773a {
    /* renamed from: ʼ */
    private String f1218;
    @Nullable
    /* renamed from: ˊ */
    C0756b f1219;
    /* renamed from: ˊॱ */
    private MoatOptions f1220;
    /* renamed from: ˋ */
    WeakReference<Context> f1221;
    /* renamed from: ˎ */
    boolean f1222 = false;
    /* renamed from: ˏ */
    boolean f1223 = false;
    /* renamed from: ॱ */
    boolean f1224 = false;
    /* renamed from: ᐝ */
    private boolean f1225 = false;

    C0774j() {
    }

    public final void start(Application application) {
        start(new MoatOptions(), application);
    }

    @UiThread
    public final void prepareNativeDisplayTracking(String str) {
        this.f1218 = str;
        if (C0803t.m1393().f1298 != C0800c.f1285) {
            try {
                m1319();
            } catch (Exception e) {
                C0785o.m1351(e);
            }
        }
    }

    @UiThread
    /* renamed from: ˋ */
    private void m1319() {
        if (this.f1219 == null) {
            this.f1219 = new C0756b(C0752a.m1226(), C0755a.f1128);
            this.f1219.m1237(this.f1218);
            C0756b.m1234(3, "Analytics", this, "Preparing native display tracking with partner code " + this.f1218);
            C0756b.m1232("[SUCCESS] ", "Prepared for native display tracking with partner code " + this.f1218);
        }
    }

    /* renamed from: ˊ */
    final boolean m1320() {
        return this.f1225;
    }

    /* renamed from: ˏ */
    final boolean m1321() {
        return this.f1220 != null && this.f1220.disableLocationServices;
    }

    /* renamed from: ॱ */
    public final void mo1645() throws C0785o {
        C0785o.m1348();
        C0777k.m1332();
        if (this.f1218 != null) {
            try {
                m1319();
            } catch (Exception e) {
                C0785o.m1351(e);
            }
        }
    }

    public final void start(MoatOptions moatOptions, Application application) {
        try {
            if (this.f1225) {
                C0756b.m1234(3, "Analytics", this, "Moat SDK has already been started.");
                return;
            }
            this.f1220 = moatOptions;
            C0803t.m1393().m1402();
            this.f1223 = moatOptions.disableLocationServices;
            if (application == null) {
                throw new C0785o("Moat Analytics SDK didn't start, application was null");
            }
            if (moatOptions.loggingEnabled && C0789p.m1364(application.getApplicationContext())) {
                this.f1224 = true;
            }
            this.f1221 = new WeakReference(application.getApplicationContext());
            this.f1225 = true;
            this.f1222 = moatOptions.autoTrackGMAInterstitials;
            C0752a.m1224(application);
            C0803t.m1393().m1403((C0773a) this);
            if (!moatOptions.disableAdIdCollection) {
                C0789p.m1359(application);
            }
            C0756b.m1232("[SUCCESS] ", "Moat Analytics SDK Version 2.4.0 started");
        } catch (Exception e) {
            C0785o.m1351(e);
        }
    }
}
