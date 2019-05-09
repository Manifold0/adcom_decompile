package com.applovin.impl.sdk;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import com.applovin.mediation.AppLovinMediationAdapterInfo;
import com.applovin.mediation.AppLovinMediationAdapterStats;
import com.applovin.mediation.AppLovinMediationAdapterStatus;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinMediationService;
import java.util.ArrayList;
import java.util.Collection;

public class MediationServiceImpl implements AppLovinMediationService {
    public static final String TAG = "MediationServiceImpl";
    /* renamed from: a */
    private final AppLovinSdkImpl f1979a;
    /* renamed from: b */
    private final AppLovinLogger f1980b;
    /* renamed from: c */
    private final cn f1981c;
    /* renamed from: d */
    private final Object f1982d = new Object();
    /* renamed from: e */
    private long f1983e = 0;
    /* renamed from: f */
    private String f1984f = null;

    MediationServiceImpl(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f1979a = appLovinSdkImpl;
        this.f1980b = appLovinSdkImpl.getLogger();
        this.f1981c = new cn(appLovinSdkImpl);
    }

    /* renamed from: a */
    private void m2168a(int i, ck ckVar) {
        if (((Boolean) this.f1979a.get(ea.dG)).booleanValue()) {
            m2175a(NotificationCompat.CATEGORY_ERROR, i, ckVar);
        }
    }

    /* renamed from: a */
    private void m2172a(ck ckVar, int i, C1281h c1281h) {
        c1281h.m2997b((AppLovinAd) ckVar);
    }

    /* renamed from: a */
    private void m2173a(ck ckVar, int i, AppLovinAdLoadListener appLovinAdLoadListener) {
        m2168a(i, ckVar);
        if (appLovinAdLoadListener == null) {
            return;
        }
        if (appLovinAdLoadListener instanceof at) {
            ((at) appLovinAdLoadListener).mo4133a(ckVar.mo3997t(), i);
        } else {
            appLovinAdLoadListener.failedToReceiveAd(i);
        }
    }

    /* renamed from: a */
    private void m2174a(AppLovinAd appLovinAd, AppLovinAdLoadListener appLovinAdLoadListener) {
        if (appLovinAdLoadListener != null) {
            appLovinAdLoadListener.adReceived(appLovinAd);
        }
    }

    /* renamed from: a */
    private void m2175a(String str, int i, ck ckVar) {
        try {
            this.f1979a.getPostbackService().dispatchPostbackAsync(Uri.parse((String) this.f1979a.get(ea.f2418o)).buildUpon().appendQueryParameter("event", str).appendQueryParameter("ec", String.valueOf(i)).appendQueryParameter("clcode", ckVar.mo3996n()).appendQueryParameter("an", ckVar.m2424c()).appendQueryParameter("ac", ckVar.m2423b()).build().toString(), null);
        } catch (Throwable th) {
            this.f1980b.mo4174e(TAG, "Unable to create post-back URL for mediated '" + str + "'", th);
        }
    }

    /* renamed from: b */
    private void m2178b(ck ckVar) {
        if (((Boolean) this.f1979a.get(ea.dE)).booleanValue()) {
            m2175a("imp", 0, ckVar);
        }
    }

    /* renamed from: c */
    private void m2180c(ck ckVar) {
        if (((Boolean) this.f1979a.get(ea.dF)).booleanValue()) {
            m2175a("clk", 0, ckVar);
        }
    }

    /* renamed from: a */
    void m2181a() {
        this.f1981c.m2446a();
    }

    /* renamed from: a */
    void m2182a(ck ckVar) {
        if (ckVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        }
        this.f1980b.mo4172d(TAG, "Loading " + ckVar + "...");
        cp a = this.f1981c.m2445a(ckVar.m2424c(), ckVar.m2423b(), ckVar.m2426e());
        if (a != null) {
            a.m2468a(ckVar);
        } else {
            this.f1980b.mo4178w(TAG, "Failed to prepare" + ckVar + ": adapter not loaded");
        }
    }

    /* renamed from: a */
    void m2183a(ck ckVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        if (ckVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        }
        this.f1980b.mo4172d(TAG, "Loading " + ckVar + "...");
        cp a = this.f1981c.m2445a(ckVar.m2424c(), ckVar.m2423b(), ckVar.m2426e());
        if (a != null) {
            a.m2470a(ckVar, new cy(this, System.currentTimeMillis(), a, ckVar, appLovinAdLoadListener));
            return;
        }
        this.f1980b.mo4178w(TAG, "Failed to load " + ckVar + ": adapter not loaded");
        m2173a(ckVar, (int) AppLovinErrorCodes.MEDIATION_ADAPTER_LOAD_FAILED, appLovinAdLoadListener);
    }

    public Collection<AppLovinMediationAdapterInfo> getAdapterInfo() {
        Collection b = this.f1981c.m2448b();
        Collection<cp> c = this.f1981c.m2449c();
        Collection arrayList = new ArrayList(c.size());
        for (cp cpVar : c) {
            String a = cpVar.m2467a();
            String f = cpVar.m2477f();
            String e = cpVar.m2476e();
            if (b.contains(f)) {
                arrayList.add(new AppLovinMediationAdapterInfo(a, f, e, AppLovinMediationAdapterStatus.ERROR_LOAD));
            } else if (!cpVar.m2473b()) {
                arrayList.add(new AppLovinMediationAdapterInfo(a, f, e, AppLovinMediationAdapterStatus.ERROR_LOAD));
            } else if (cpVar.m2474c()) {
                arrayList.add(new AppLovinMediationAdapterInfo(a, f, e, AppLovinMediationAdapterStatus.READY, cpVar.m2475d(), cpVar.m2478g()));
            } else {
                arrayList.add(new AppLovinMediationAdapterInfo(a, f, e, AppLovinMediationAdapterStatus.ERROR_NOT_READY));
            }
        }
        return arrayList;
    }

    public AppLovinMediationAdapterStats getLastAdapterStats() {
        synchronized (this.f1982d) {
            if (this.f1984f != null) {
                AppLovinMediationAdapterStats appLovinMediationAdapterStats = new AppLovinMediationAdapterStats(this.f1984f, this.f1983e);
                return appLovinMediationAdapterStats;
            }
            return null;
        }
    }

    public void showAd(ck ckVar, String str, Activity activity, C1281h c1281h) {
        if (ckVar == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        } else if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        } else if (c1281h == null) {
            throw new IllegalArgumentException("No listeners specified");
        } else if (ckVar.mo4000a()) {
            cp a = this.f1981c.m2445a(ckVar.m2424c(), ckVar.m2423b(), ckVar.m2426e());
            if (a != null) {
                c1281h.m2999b(new cz(this, ckVar));
                c1281h.m2998b(new da(this, ckVar));
                a.m2469a(ckVar, activity, c1281h);
                return;
            }
            m2172a(ckVar, (int) AppLovinErrorCodes.MEDIATION_ADAPTER_LOAD_FAILED_ON_RENDER, c1281h);
            this.f1980b.mo4178w(TAG, "Failed to show " + ckVar + ": adapter not loaded");
            this.f1980b.userError(TAG, "There may be an integration problem with the mediated '" + ckVar.m2424c() + "'. Please check if you have a supported version of that SDK integrated into your project.");
        } else {
            m2172a(ckVar, (int) AppLovinErrorCodes.MEDIATION_ADAPTER_RENDER_NOT_READY_AD, c1281h);
            this.f1980b.mo4173e(TAG, "Ad " + ckVar + " was not ready when provided requestsed to show.");
        }
    }
}
