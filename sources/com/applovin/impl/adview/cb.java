package com.applovin.impl.adview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.impl.p016a.C1228a;
import com.applovin.impl.p016a.C1245r;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.C1281h;
import com.applovin.impl.sdk.ab;
import com.applovin.impl.sdk.an;
import com.applovin.impl.sdk.ap;
import com.applovin.impl.sdk.ck;
import com.applovin.impl.sdk.ee;
import com.applovin.impl.sdk.gd;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.google.android.gms.drive.DriveFile;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

class cb implements AppLovinInterstitialAdDialog {
    /* renamed from: d */
    public static volatile boolean f1831d = false;
    /* renamed from: e */
    public static volatile boolean f1832e = false;
    /* renamed from: f */
    private static final Map<String, cb> f1833f = Collections.synchronizedMap(new HashMap());
    /* renamed from: p */
    private static volatile boolean f1834p;
    /* renamed from: a */
    protected final String f1835a;
    /* renamed from: b */
    protected final AppLovinSdkImpl f1836b;
    /* renamed from: c */
    protected final WeakReference<Context> f1837c;
    /* renamed from: g */
    private final C1281h f1838g;
    /* renamed from: h */
    private volatile AppLovinAdLoadListener f1839h;
    /* renamed from: i */
    private volatile AppLovinAdDisplayListener f1840i;
    /* renamed from: j */
    private volatile AppLovinAdVideoPlaybackListener f1841j;
    /* renamed from: k */
    private volatile AppLovinAdClickListener f1842k;
    /* renamed from: l */
    private volatile an f1843l;
    /* renamed from: m */
    private volatile ap f1844m;
    /* renamed from: n */
    private volatile aq f1845n;
    /* renamed from: o */
    private volatile String f1846o;

    cb(AppLovinSdk appLovinSdk, Context context) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (context == null) {
            throw new IllegalArgumentException("No context specified");
        } else {
            this.f1836b = (AppLovinSdkImpl) appLovinSdk;
            this.f1835a = UUID.randomUUID().toString();
            this.f1838g = new C1281h();
            this.f1837c = new WeakReference(context);
            f1831d = true;
            f1832e = false;
        }
    }

    /* renamed from: a */
    public static cb m2048a(String str) {
        return (cb) f1833f.get(str);
    }

    /* renamed from: a */
    private void m2050a(int i) {
        AppLovinSdkUtils.runOnUiThread(new cf(this, i));
    }

    /* renamed from: a */
    private void m2051a(Context context) {
        Intent intent = new Intent(context, AppLovinInterstitialActivity.class);
        intent.putExtra(az.KEY_WRAPPER_ID, this.f1835a);
        AppLovinInterstitialActivity.lastKnownWrapper = this;
        if (context instanceof Activity) {
            try {
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(0, 0);
            } catch (Throwable th) {
                this.f1836b.getLogger().mo4174e("InterstitialAdDialogWrapper", "Unable to remove pending transition animations", th);
            }
        } else {
            intent.setFlags(DriveFile.MODE_READ_ONLY);
            context.startActivity(intent);
        }
        m2064a(true);
    }

    /* renamed from: a */
    private void m2055a(an anVar, String str, Context context) {
        f1833f.put(this.f1835a, this);
        this.f1843l = anVar;
        this.f1846o = str;
        this.f1844m = this.f1843l != null ? this.f1843l.m1827v() : ap.f2067a;
        if (!(this.f1843l.mo4001b() || this.f1843l.mo4002d() == null || this.f1836b.getFileManager().m2304a(this.f1843l.mo4002d().getLastPathSegment(), context))) {
            if (this.f1843l instanceof C1228a) {
                C1245r c = ((C1228a) this.f1843l).m1841c();
                if (c != null) {
                    this.f1836b.getLogger().mo4173e("InterstitialAdDialogWrapper", "Cached video removed from local filesystem for VAST ad. Setting videoUri to source: " + c.m1934a());
                    c.m1935a(c.m1934a());
                } else {
                    this.f1836b.getLogger().mo4173e("InterstitialAdDialogWrapper", "Cached video removed from local filesystem for VAST ad and source uri not found. Failing ad show.");
                    m2057a((AppLovinAd) anVar);
                    return;
                }
            }
            this.f1836b.getLogger().mo4173e("InterstitialAdDialogWrapper", "Cached video removed from local filesystem for internal ad. Failing ad show.");
            m2057a((AppLovinAd) anVar);
            return;
        }
        if (ab.m2198a(AppLovinInterstitialActivity.class, context)) {
            long max = Math.max(0, new ee(this.f1836b).m2684K());
            this.f1836b.getLogger().mo4172d("InterstitialAdDialogWrapper", "Presenting ad with delay of " + max);
            new Handler(context.getMainLooper()).postDelayed(new cd(this, context), max);
            return;
        }
        this.f1836b.getLogger().userError("AppLovinInterstitialAdDialog", "Unable to launch ad. ");
        m2057a((AppLovinAd) anVar);
    }

    /* renamed from: a */
    private void m2056a(ck ckVar, String str, Activity activity) {
        this.f1836b.getMediationService().showAd(ckVar, str, activity, this.f1838g);
    }

    /* renamed from: a */
    private void m2057a(AppLovinAd appLovinAd) {
        if (this.f1840i != null) {
            this.f1840i.adHidden(appLovinAd);
        }
        f1834p = false;
    }

    /* renamed from: b */
    private void m2059b(AppLovinAd appLovinAd) {
        AppLovinSdkUtils.runOnUiThread(new ce(this, appLovinAd));
    }

    /* renamed from: i */
    private Context m2060i() {
        return this.f1837c != null ? (Context) this.f1837c.get() : null;
    }

    /* renamed from: a */
    public AppLovinSdk m2061a() {
        return this.f1836b;
    }

    /* renamed from: a */
    public void m2062a(aq aqVar) {
        this.f1845n = aqVar;
    }

    /* renamed from: a */
    protected void mo4048a(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.f1836b.getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, appLovinAdLoadListener);
    }

    /* renamed from: a */
    public void m2064a(boolean z) {
        f1834p = z;
    }

    /* renamed from: b */
    public AppLovinAd m2065b() {
        return this.f1843l;
    }

    /* renamed from: c */
    public AppLovinAdVideoPlaybackListener m2066c() {
        return this.f1841j;
    }

    /* renamed from: d */
    public AppLovinAdDisplayListener m2067d() {
        return this.f1840i;
    }

    public void dismiss() {
        AppLovinSdkUtils.runOnUiThread(new cg(this));
    }

    /* renamed from: e */
    public AppLovinAdClickListener m2068e() {
        return this.f1842k;
    }

    /* renamed from: f */
    public ap m2069f() {
        return this.f1844m;
    }

    /* renamed from: g */
    public String m2070g() {
        return this.f1846o;
    }

    /* renamed from: h */
    public void m2071h() {
        f1831d = false;
        f1832e = true;
        f1833f.remove(this.f1835a);
    }

    public boolean isAdReadyToDisplay() {
        return this.f1836b.getAdService().hasPreloadedAd(AppLovinAdSize.INTERSTITIAL);
    }

    public boolean isShowing() {
        return f1834p;
    }

    public void setAdClickListener(AppLovinAdClickListener appLovinAdClickListener) {
        this.f1842k = appLovinAdClickListener;
        this.f1838g.m2993a(appLovinAdClickListener);
    }

    public void setAdDisplayListener(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.f1840i = appLovinAdDisplayListener;
        this.f1838g.m2994a(appLovinAdDisplayListener);
    }

    public void setAdLoadListener(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.f1839h = appLovinAdLoadListener;
    }

    public void setAdVideoPlaybackListener(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
        this.f1841j = appLovinAdVideoPlaybackListener;
    }

    public void show() {
        show(null);
    }

    public void show(String str) {
        mo4048a(new cc(this, str));
    }

    public void showAndRender(AppLovinAd appLovinAd) {
        showAndRender(appLovinAd, null);
    }

    public void showAndRender(AppLovinAd appLovinAd, String str) {
        ee eeVar = new ee(this.f1836b);
        if (isShowing() && !eeVar.ai()) {
            this.f1836b.getLogger().userError("AppLovinInterstitialAdDialog", "Attempted to show an interstitial while one is already displayed; ignoring.");
        } else if (gd.m2947a(appLovinAd, this.f1836b)) {
            Context i = m2060i();
            if (i != null) {
                AppLovinAd a = gd.m2936a(appLovinAd, this.f1836b);
                if (a == null) {
                    this.f1836b.getLogger().mo4173e("InterstitialAdDialogWrapper", "Failed to show ad: " + appLovinAd);
                    m2057a(appLovinAd);
                    return;
                } else if (a instanceof an) {
                    m2055a((an) a, str, i);
                    return;
                } else if (!(a instanceof ck)) {
                    this.f1836b.getLogger().mo4173e("InterstitialAdDialogWrapper", "Failed to show interstitial: unknown ad type provided: '" + a + "'");
                    m2057a(a);
                    return;
                } else if (i instanceof Activity) {
                    m2056a((ck) a, str, (Activity) i);
                    return;
                } else {
                    this.f1836b.getLogger().userError("InterstitialAdDialogWrapper", "Failed to show interstitial: Activity required.");
                    m2057a(a);
                    return;
                }
            }
            this.f1836b.getLogger().mo4173e("InterstitialAdDialogWrapper", "Failed to show interstitial: stale activity reference provided");
            m2057a(appLovinAd);
        } else {
            m2057a(appLovinAd);
        }
    }
}
