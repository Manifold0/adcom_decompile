// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import android.app.Activity;
import com.applovin.mediation.AppLovinMediationAdapterStats;
import java.util.Iterator;
import com.applovin.mediation.AppLovinMediationAdapterStatus;
import java.util.ArrayList;
import com.applovin.mediation.AppLovinMediationAdapterInfo;
import java.util.Collection;
import com.applovin.sdk.AppLovinPostbackListener;
import android.net.Uri;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinMediationService;

public class MediationServiceImpl implements AppLovinMediationService
{
    public static final String TAG = "MediationServiceImpl";
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private final cn c;
    private final Object d;
    private long e;
    private String f;
    
    MediationServiceImpl(final AppLovinSdkImpl a) {
        this.d = new Object();
        this.e = 0L;
        this.f = null;
        if (a == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = a;
        this.b = a.getLogger();
        this.c = new cn(a);
    }
    
    private void a(final int n, final ck ck) {
        if (this.a.get(ea.dG)) {
            this.a("err", n, ck);
        }
    }
    
    private void a(final ck ck, final int n, final h h) {
        h.b(ck);
    }
    
    private void a(final ck ck, final int n, final AppLovinAdLoadListener appLovinAdLoadListener) {
        this.a(n, ck);
        if (appLovinAdLoadListener != null) {
            if (!(appLovinAdLoadListener instanceof at)) {
                appLovinAdLoadListener.failedToReceiveAd(n);
                return;
            }
            ((at)appLovinAdLoadListener).a(ck.t(), n);
        }
    }
    
    private void a(final AppLovinAd appLovinAd, final AppLovinAdLoadListener appLovinAdLoadListener) {
        if (appLovinAdLoadListener != null) {
            appLovinAdLoadListener.adReceived(appLovinAd);
        }
    }
    
    private void a(final String s, final int n, final ck ck) {
        try {
            this.a.getPostbackService().dispatchPostbackAsync(Uri.parse((String)this.a.get(ea.o)).buildUpon().appendQueryParameter("event", s).appendQueryParameter("ec", String.valueOf(n)).appendQueryParameter("clcode", ck.n()).appendQueryParameter("an", ck.c()).appendQueryParameter("ac", ck.b()).build().toString(), null);
        }
        catch (Throwable t) {
            this.b.e("MediationServiceImpl", "Unable to create post-back URL for mediated '" + s + "'", t);
        }
    }
    
    private void b(final ck ck) {
        if (this.a.get(ea.dE)) {
            this.a("imp", 0, ck);
        }
    }
    
    private void c(final ck ck) {
        if (this.a.get(ea.dF)) {
            this.a("clk", 0, ck);
        }
    }
    
    void a() {
        this.c.a();
    }
    
    void a(final ck ck) {
        if (ck == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        }
        this.b.d("MediationServiceImpl", "Loading " + ck + "...");
        final cp a = this.c.a(ck.c(), ck.b(), ck.e());
        if (a != null) {
            a.a(ck);
            return;
        }
        this.b.w("MediationServiceImpl", "Failed to prepare" + ck + ": adapter not loaded");
    }
    
    void a(final ck ck, final AppLovinAdLoadListener appLovinAdLoadListener) {
        if (ck == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        }
        this.b.d("MediationServiceImpl", "Loading " + ck + "...");
        final cp a = this.c.a(ck.c(), ck.b(), ck.e());
        if (a != null) {
            a.a(ck, new cy(this, System.currentTimeMillis(), a, ck, appLovinAdLoadListener));
            return;
        }
        this.b.w("MediationServiceImpl", "Failed to load " + ck + ": adapter not loaded");
        this.a(ck, -5001, appLovinAdLoadListener);
    }
    
    @Override
    public Collection<AppLovinMediationAdapterInfo> getAdapterInfo() {
        final Collection<String> b = this.c.b();
        final Collection<cp> c = this.c.c();
        final ArrayList list = new ArrayList<AppLovinMediationAdapterInfo>(c.size());
        for (final cp cp : c) {
            final String a = cp.a();
            final String f = cp.f();
            final String e = cp.e();
            if (b.contains(f)) {
                list.add(new AppLovinMediationAdapterInfo(a, f, e, AppLovinMediationAdapterStatus.ERROR_LOAD));
            }
            else if (cp.b()) {
                if (cp.c()) {
                    list.add(new AppLovinMediationAdapterInfo(a, f, e, AppLovinMediationAdapterStatus.READY, cp.d(), cp.g()));
                }
                else {
                    list.add(new AppLovinMediationAdapterInfo(a, f, e, AppLovinMediationAdapterStatus.ERROR_NOT_READY));
                }
            }
            else {
                list.add(new AppLovinMediationAdapterInfo(a, f, e, AppLovinMediationAdapterStatus.ERROR_LOAD));
            }
        }
        return (Collection<AppLovinMediationAdapterInfo>)list;
    }
    
    @Override
    public AppLovinMediationAdapterStats getLastAdapterStats() {
        synchronized (this.d) {
            if (this.f != null) {
                return new AppLovinMediationAdapterStats(this.f, this.e);
            }
            return null;
        }
    }
    
    public void showAd(final ck ck, final String s, final Activity activity, final h h) {
        if (ck == null) {
            throw new IllegalArgumentException("No mediated ad specified");
        }
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        if (h == null) {
            throw new IllegalArgumentException("No listeners specified");
        }
        if (!ck.a()) {
            this.a(ck, -5003, h);
            this.b.e("MediationServiceImpl", "Ad " + ck + " was not ready when provided requestsed to show.");
            return;
        }
        final cp a = this.c.a(ck.c(), ck.b(), ck.e());
        if (a != null) {
            h.b(new cz(this, ck));
            h.b(new da(this, ck));
            a.a(ck, activity, h);
            return;
        }
        this.a(ck, -5002, h);
        this.b.w("MediationServiceImpl", "Failed to show " + ck + ": adapter not loaded");
        this.b.userError("MediationServiceImpl", "There may be an integration problem with the mediated '" + ck.c() + "'. Please check if you have a supported version of that SDK integrated into your project.");
    }
}
