package com.applovin.impl.sdk;

import android.app.Activity;
import com.applovin.mediation.AppLovinMediatedAdInfo;
import com.applovin.mediation.AppLovinMediationAdapter;
import com.applovin.mediation.AppLovinMediationAdapterConfig;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinLogger;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

class cp {
    /* renamed from: a */
    private final String f2235a;
    /* renamed from: b */
    private final AppLovinMediationAdapter f2236b;
    /* renamed from: c */
    private final AppLovinSdkImpl f2237c;
    /* renamed from: d */
    private final AppLovinLogger f2238d;
    /* renamed from: e */
    private cm f2239e;
    /* renamed from: f */
    private final AtomicBoolean f2240f;

    cp(String str, AppLovinMediationAdapter appLovinMediationAdapter, AppLovinSdkImpl appLovinSdkImpl) {
        if (str == null) {
            throw new IllegalArgumentException("No implementation name specified");
        } else if (appLovinMediationAdapter == null) {
            throw new IllegalArgumentException("No implementation specified");
        } else if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else {
            this.f2235a = str;
            this.f2236b = appLovinMediationAdapter;
            this.f2237c = appLovinSdkImpl;
            this.f2238d = appLovinSdkImpl.getLogger();
            this.f2239e = new cm(str, appLovinSdkImpl);
            this.f2240f = new AtomicBoolean(true);
        }
    }

    /* renamed from: a */
    private void m2457a(int i, cw cwVar) {
        if (cwVar.f2256c.compareAndSet(false, true) && cwVar.f2255b != null) {
            cwVar.f2255b.failedToReceiveAd(i);
        }
    }

    /* renamed from: a */
    private void m2461a(AppLovinMediatedAdInfo appLovinMediatedAdInfo, cw cwVar) {
        if (cwVar.f2256c.compareAndSet(false, true) && cwVar.f2255b != null) {
            cwVar.f2255b.adReceived(new ck(cwVar.f2254a, true, appLovinMediatedAdInfo));
        }
    }

    /* renamed from: a */
    private void m2462a(String str, Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("No operation specified");
        }
        try {
            this.f2238d.mo4172d("MediationAdapterWrapper", "Running implementation operation '" + str + "'...");
            runnable.run();
        } catch (Throwable th) {
            this.f2238d.mo4174e("MediationAdapterWrapper", "Unable to implementation operation run " + str + ", marking " + this + " as disabled", th);
            m2471a("fail_" + str);
        }
    }

    /* renamed from: b */
    private void m2464b(ck ckVar) {
        Map e = ckVar.m2426e();
        if (e != null) {
            this.f2239e.m2436b(e);
        }
    }

    /* renamed from: a */
    public String m2467a() {
        return this.f2235a;
    }

    /* renamed from: a */
    void m2468a(ck ckVar) {
        if (ckVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        } else if (!this.f2240f.get()) {
            this.f2238d.mo4178w("MediationAdapterWrapper", "Mediation implementation '" + m2477f() + "' was disabled due to earlier failures. Preparing ads with this implementation is disabled.");
        } else if (this.f2236b.isReady()) {
            m2462a("ad_prepare", new ct(this, ckVar));
        } else {
            this.f2238d.userError("MediationAdapterWrapper", "Mediation implementation '" + m2477f() + "' is not ready.");
        }
    }

    /* renamed from: a */
    void m2469a(ck ckVar, Activity activity, C1281h c1281h) {
        if (ckVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        } else if (!ckVar.mo4000a()) {
            throw new IllegalArgumentException("Mediated ad is not ready");
        } else if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        } else if (c1281h == null) {
            throw new IllegalArgumentException("No listeners specified");
        } else {
            m2462a("ad_render", new cu(this, c1281h, ckVar, activity));
        }
    }

    /* renamed from: a */
    void m2470a(ck ckVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        if (ckVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        } else if (!this.f2240f.get()) {
            this.f2238d.userError("MediationAdapterWrapper", "Mediation implementation '" + m2477f() + "' was disabled due to earlier failures. Loading ads with this implementation is disabled.");
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.failedToReceiveAd(AppLovinErrorCodes.MEDIATION_ADAPTER_DISABLED);
            }
        } else if (this.f2236b.isReady()) {
            m2462a("ad_load", new cr(this, ckVar, new cw(ckVar, appLovinAdLoadListener)));
        } else {
            this.f2238d.userError("MediationAdapterWrapper", "Mediation implementation '" + m2477f() + "' is not ready.");
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.failedToReceiveAd(AppLovinErrorCodes.MEDIATION_ADAPTER_READY_AD);
            }
        }
    }

    /* renamed from: a */
    void m2471a(String str) {
        this.f2238d.mo4175i("MediationAdapterWrapper", "Marking " + m2477f() + " as disabled due to: " + str);
        this.f2240f.set(false);
    }

    /* renamed from: a */
    void m2472a(Map<String, String> map) {
        m2462a("init", new cq(this, map));
    }

    /* renamed from: b */
    boolean m2473b() {
        return this.f2240f.get();
    }

    /* renamed from: c */
    boolean m2474c() {
        return m2473b() && this.f2236b.isReady();
    }

    /* renamed from: d */
    AppLovinMediationAdapter m2475d() {
        return this.f2236b;
    }

    /* renamed from: e */
    String m2476e() {
        if (this.f2236b != null) {
            try {
                return this.f2236b.getVersion();
            } catch (Throwable th) {
                this.f2238d.mo4174e("MediationAdapterWrapper", "Unable to get implementation version, marking " + this + " as disabled", th);
                m2471a("fail_version");
            }
        }
        return null;
    }

    /* renamed from: f */
    String m2477f() {
        return this.f2236b.getClass().getName();
    }

    /* renamed from: g */
    AppLovinMediationAdapterConfig m2478g() {
        return this.f2239e;
    }

    public String toString() {
        return "[MediationAdapterWrapper implementation: " + m2477f() + RequestParameters.RIGHT_BRACKETS;
    }
}
