package com.moat.analytics.mobile.cha;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import com.moat.analytics.mobile.cha.C1487a.C1486d;
import com.moat.analytics.mobile.cha.C1536t.C1494b;
import com.moat.analytics.mobile.cha.C1536t.C1532a;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.cha.f */
final class C1495f extends MoatAnalytics implements C1494b {
    /* renamed from: ʻ */
    private boolean f3467 = false;
    /* renamed from: ʼ */
    private String f3468;
    /* renamed from: ʽ */
    private MoatOptions f3469;
    /* renamed from: ˊ */
    WeakReference<Context> f3470;
    /* renamed from: ˋ */
    boolean f3471 = false;
    /* renamed from: ˎ */
    boolean f3472 = false;
    /* renamed from: ˏ */
    boolean f3473 = false;
    @Nullable
    /* renamed from: ॱ */
    C1487a f3474;

    C1495f() {
    }

    public final void start(Application application) {
        start(new MoatOptions(), application);
    }

    @UiThread
    public final void prepareNativeDisplayTracking(String str) {
        this.f3468 = str;
        if (C1536t.m3887().f3610 != C1532a.f3593) {
            try {
                m3754();
            } catch (Exception e) {
                C1518o.m3840(e);
            }
        }
    }

    @UiThread
    /* renamed from: ˏ */
    private void m3754() {
        if (this.f3474 == null) {
            this.f3474 = new C1487a(C1492c.m3748(), C1486d.f3429);
            this.f3474.m3719(this.f3468);
            C1487a.m3715(3, "Analytics", this, "Preparing native display tracking with partner code " + this.f3468);
            C1487a.m3712("[SUCCESS] ", "Prepared for native display tracking with partner code " + this.f3468);
        }
    }

    /* renamed from: ˊ */
    final boolean m3755() {
        return this.f3467;
    }

    /* renamed from: ˋ */
    final boolean m3756() {
        return this.f3469 != null && this.f3469.disableLocationServices;
    }

    /* renamed from: ˎ */
    public final void mo4370() throws C1518o {
        C1518o.m3841();
        C1517n.m3826();
        if (this.f3468 != null) {
            try {
                m3754();
            } catch (Exception e) {
                C1518o.m3840(e);
            }
        }
    }

    public final void start(MoatOptions moatOptions, Application application) {
        try {
            if (this.f3467) {
                C1487a.m3715(3, "Analytics", this, "Moat SDK has already been started.");
                return;
            }
            this.f3469 = moatOptions;
            C1536t.m3887().m3893();
            this.f3471 = moatOptions.disableLocationServices;
            if (application == null) {
                throw new C1518o("Moat Analytics SDK didn't start, application was null");
            }
            if (moatOptions.loggingEnabled && C1526r.m3862(application.getApplicationContext())) {
                this.f3472 = true;
            }
            this.f3470 = new WeakReference(application.getApplicationContext());
            this.f3467 = true;
            this.f3473 = moatOptions.autoTrackGMAInterstitials;
            C1492c.m3750(application);
            C1536t.m3887().m3892((C1494b) this);
            if (!moatOptions.disableAdIdCollection) {
                C1526r.m3865(application);
            }
            C1487a.m3712("[SUCCESS] ", "Moat Analytics SDK Version 2.4.1 started");
        } catch (Exception e) {
            C1518o.m3840(e);
        }
    }
}
