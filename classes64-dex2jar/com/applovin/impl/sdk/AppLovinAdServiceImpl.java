// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinPostbackListener;
import java.util.List;
import android.text.TextUtils;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdUpdateListener;
import android.os.PowerManager;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinAd;
import com.applovin.impl.adview.AdViewControllerImpl;
import com.applovin.adview.AppLovinAdView;
import android.net.Uri;
import java.util.HashMap;
import android.os.Looper;
import java.util.Map;
import android.os.Handler;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinAdService;

public class AppLovinAdServiceImpl implements AppLovinAdService
{
    public static String URI_LOAD_URL;
    public static String URI_NO_OP;
    public static String URI_SKIP_AD;
    public static String URI_TRACK_CLICK_IMMEDIATELY;
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private Handler c;
    private final Map<n, v> d;
    private final Object e;
    
    static {
        AppLovinAdServiceImpl.URI_NO_OP = "/adservice/no_op";
        AppLovinAdServiceImpl.URI_TRACK_CLICK_IMMEDIATELY = "/adservice/track_click_now";
        AppLovinAdServiceImpl.URI_SKIP_AD = "/adservice/skip";
        AppLovinAdServiceImpl.URI_LOAD_URL = "/adservice/load_url";
    }
    
    AppLovinAdServiceImpl(final AppLovinSdkImpl a) {
        this.e = new Object();
        if (a == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = a;
        this.b = a.getLogger();
        this.c = new Handler(Looper.getMainLooper());
        (this.d = new HashMap<n, v>(8)).put(n.c(a), new v(null));
        this.d.put(n.d(a), new v(null));
        this.d.put(n.e(a), new v(null));
        this.d.put(n.f(a), new v(null));
        this.d.put(n.g(a), new v(null));
        this.d.put(n.h(a), new v(null));
        this.d.put(n.i(a), new v(null));
    }
    
    private v a(final n n) {
        synchronized (this.e) {
            v v;
            if ((v = this.d.get(n)) == null) {
                v = new v(null);
                this.d.put(n, v);
            }
            return v;
        }
    }
    
    private void a(final Uri uri, final an an, final AppLovinAdView appLovinAdView, final AdViewControllerImpl adViewControllerImpl) {
        if (appLovinAdView != null) {
            adViewControllerImpl.removeClickTrackingOverlay();
            this.expireAdLoadState(an);
            if (AppLovinSdkUtils.openUri(appLovinAdView.getContext(), uri, this.a)) {
                bv.c(adViewControllerImpl.getAdViewEventListener(), an, appLovinAdView, this.a);
            }
            adViewControllerImpl.dismissInterstitialIfRequired();
            return;
        }
        this.b.e("AppLovinAdService", "Unable to launch click - adView has been prematurely destroyed");
    }
    
    private void a(final an an, final String s) {
        final String b = an.b(s);
        if (AppLovinSdkUtils.isValidString(b)) {
            this.a.getPersistentPostbackManager().a(b, null);
        }
    }
    
    private void a(final ex ex, final AppLovinAdLoadListener appLovinAdLoadListener) {
        if (!ag.a(this.a.getApplicationContext(), this.a) && !this.a.get(ea.cF)) {
            this.b.userError("AppLovinAdService", "Failing ad load due to no internet connection.");
            appLovinAdLoadListener.failedToReceiveAd(-103);
            return;
        }
        this.a.f();
        this.b.i("AppLovinAdService", "Loading ad using '" + ex.getClass().getSimpleName() + "'...");
        this.a.getTaskManager().a(ex, fe.a);
    }
    
    private void a(final n n, final u u) {
        final AppLovinAd appLovinAd = (AppLovinAd)this.a.c().e(n);
        if (appLovinAd != null) {
            this.b.d("AppLovinAdService", "Using pre-loaded ad: " + appLovinAd + " for " + n);
            u.adReceived(appLovinAd);
        }
        else {
            this.a(new ex(n, u, this.a), u);
        }
        if (!n.l() || appLovinAd != null) {
            if (n.m()) {
                this.a.c().j(n);
            }
            else if (appLovinAd != null && n.h() > 0) {
                this.a.c().j(n);
            }
        }
    }
    
    private void a(final n n, final AppLovinAdLoadListener appLovinAdLoadListener) {
        if (n == null) {
            throw new IllegalArgumentException("No zone specified");
        }
        if (appLovinAdLoadListener == null) {
            throw new IllegalArgumentException("No callback specified");
        }
        if (!ag.a(this.a.getApplicationContext(), this.a) && !this.a.get(ea.cF)) {
            this.b.userError("AppLovinAdService", "Failing ad load due to no internet connection.");
            appLovinAdLoadListener.failedToReceiveAd(-103);
        }
        else {
            if (this.a.get(ea.cZ) && !n.m() && this.a.getZoneManager().a() && !this.a.getZoneManager().a(n)) {
                this.b.userError("AppLovinAdService", "Failed to load ad for zone (" + n.a() + "). Please check that the zone has been added to your AppLovin account.");
                appLovinAdLoadListener.failedToReceiveAd(-7);
                return;
            }
            while (true) {
                this.a.getLogger().d("AppLovinAdService", "Loading next ad of zone {" + n + "}...");
                Object a = this.a(n);
                while (true) {
                    Label_0465: {
                        Label_0446: {
                            synchronized (((v)a).a) {
                                boolean b;
                                if (System.currentTimeMillis() > ((v)a).c) {
                                    b = true;
                                }
                                else {
                                    b = false;
                                }
                                if (((v)a).b != null && !b) {
                                    final AppLovinAd b2 = ((v)a).b;
                                    // monitorexit(a.a)
                                    if (b2 != null) {
                                        appLovinAdLoadListener.adReceived(b2);
                                        return;
                                    }
                                    break;
                                }
                                else {
                                    ((v)a).f.add(appLovinAdLoadListener);
                                    if (((v)a).d) {
                                        break Label_0446;
                                    }
                                    this.b.d("AppLovinAdService", "Loading next ad...");
                                    ((v)a).d = true;
                                    a = new u(this, (v)a, null);
                                    if (n.k()) {
                                        if (this.a.c().a(n, a)) {
                                            this.b.d("AppLovinAdService", "Attaching load listener to initial preload task...");
                                            break Label_0465;
                                        }
                                        this.b.d("AppLovinAdService", "Skipped attach of initial preload callback.");
                                        this.a(n, (u)a);
                                        break Label_0465;
                                    }
                                }
                            }
                            this.b.d("AppLovinAdService", "Task merge not necessary.");
                            final n n2;
                            this.a(n2, (u)a);
                            break Label_0465;
                        }
                        this.b.d("AppLovinAdService", "Already waiting on an ad load...");
                        final AppLovinAd b2 = null;
                        continue;
                    }
                    final AppLovinAd b2 = null;
                    continue;
                }
            }
        }
    }
    
    private boolean a() {
        return ((PowerManager)this.a.getApplicationContext().getSystemService("power")).isScreenOn();
    }
    
    private void b(final n n) {
        final long j = n.j();
        if (j > 0L) {
            this.a.getTaskManager().a(new w(this, n, null), fe.a, (j + 2L) * 1000L);
        }
    }
    
    void a(final AppLovinAdLoadListener appLovinAdLoadListener) {
        this.a(n.h(this.a), appLovinAdLoadListener);
    }
    
    @Override
    public void addAdUpdateListener(final AppLovinAdUpdateListener appLovinAdUpdateListener) {
        this.addAdUpdateListener(appLovinAdUpdateListener, AppLovinAdSize.BANNER);
    }
    
    @Override
    public void addAdUpdateListener(final AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize a) {
        if (appLovinAdUpdateListener == null) {
            throw new IllegalArgumentException("No ad listener specified");
        }
        if (a == null) {
            throw new IllegalArgumentException("No ad size specified");
        }
        final n a2 = n.a(a, AppLovinAdType.REGULAR, o.b, this.a);
        final v a3 = this.a(a2);
        final boolean b = false;
        a = (AppLovinAdSize)a3.a;
        // monitorenter(a)
        int n = b ? 1 : 0;
        try {
            if (a3.c > 0L) {
                n = (b ? 1 : 0);
                if (!a3.e.contains(appLovinAdUpdateListener)) {
                    a3.e.add(appLovinAdUpdateListener);
                    n = 1;
                    this.b.d("AppLovinAdService", "Added update listener: " + appLovinAdUpdateListener);
                }
            }
            // monitorexit(a)
            if (n != 0) {
                this.a.getTaskManager().a(new w(this, a2, null), fe.a);
            }
        }
        finally {
        }
        // monitorexit(a)
    }
    
    void b(final AppLovinAdLoadListener appLovinAdLoadListener) {
        this.a(n.i(this.a), appLovinAdLoadListener);
    }
    
    public AppLovinAd dequeueAd(final n n) {
        final AppLovinAd appLovinAd = (AppLovinAd)this.a.c().d(n);
        this.b.d("AppLovinAdService", "Dequeued ad: " + appLovinAd + " for zone: " + n + "...");
        return appLovinAd;
    }
    
    public void expireAdLoadState(final AppLovinAd appLovinAd) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        if (!(appLovinAd instanceof q)) {
            throw new IllegalArgumentException("Unknown ad type specified: " + appLovinAd.getClass().getName());
        }
        final v a = this.a(((q)appLovinAd).t());
        synchronized (a.a) {
            a.b = null;
            a.c = 0L;
        }
    }
    
    @Override
    public boolean hasPreloadedAd(final AppLovinAdSize appLovinAdSize) {
        return this.a.c().h(n.a(appLovinAdSize, AppLovinAdType.REGULAR, o.b, this.a));
    }
    
    @Override
    public boolean hasPreloadedAdForZoneId(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            this.b.userError("AppLovinAdService", "Unable to check if ad is preloaded - invalid zone id");
            return false;
        }
        return this.a.c().h(n.a(s, this.a));
    }
    
    @Override
    public void loadNextAd(final AppLovinAdSize appLovinAdSize, final AppLovinAdLoadListener appLovinAdLoadListener) {
        this.a(n.a(appLovinAdSize, AppLovinAdType.REGULAR, o.b, this.a), appLovinAdLoadListener);
    }
    
    @Override
    public void loadNextAdForAdToken(String trim, final AppLovinAdLoadListener appLovinAdLoadListener) {
        if (trim != null) {
            trim = trim.trim();
        }
        else {
            trim = null;
        }
        if (TextUtils.isEmpty((CharSequence)trim)) {
            this.b.userError("AppLovinAdService", "Invalid ad token specified");
            appLovinAdLoadListener.failedToReceiveAd(-8);
            return;
        }
        this.b.d("AppLovinAdService", "Loading next ad for token: " + trim);
        this.a(new fa(trim, appLovinAdLoadListener, this.a), appLovinAdLoadListener);
    }
    
    @Override
    public void loadNextAdForZoneId(final String s, final AppLovinAdLoadListener appLovinAdLoadListener) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("No zone id specified");
        }
        this.b.d("AppLovinAdService", "Loading next ad of zone {" + s + "}");
        this.a(n.a(s, this.a), appLovinAdLoadListener);
    }
    
    @Override
    public void loadNextAdForZoneIds(final List<String> list, final AppLovinAdLoadListener appLovinAdLoadListener) {
        final List<String> a = aa.a(list);
        if (a == null || a.isEmpty()) {
            this.b.userError("AppLovinAdService", "No zones were provided");
            appLovinAdLoadListener.failedToReceiveAd(-7);
            return;
        }
        this.b.d("AppLovinAdService", "Loading next ad for zones: " + a);
        this.a(new ew(a, appLovinAdLoadListener, this.a), appLovinAdLoadListener);
    }
    
    @Override
    public void loadNextMediatedAd(final AppLovinAdSize appLovinAdSize, final AppLovinAdLoadListener appLovinAdLoadListener) {
        this.a(n.g(this.a), appLovinAdLoadListener);
    }
    
    @Override
    public void preloadAd(final AppLovinAdSize appLovinAdSize) {
        this.a.f();
        this.a.c().j(n.a(appLovinAdSize, AppLovinAdType.REGULAR, o.b, this.a));
    }
    
    @Override
    public void preloadAdForZoneId(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            this.b.userError("AppLovinAdService", "Unable to preload ad for invalid zone identifier");
            return;
        }
        final n a = n.a(s, this.a);
        this.a.c().i(a);
        this.a.c().j(a);
    }
    
    public void preloadAds(final n n) {
        this.a.c().i(n);
        int h;
        final int n2 = h = n.h();
        if (n2 == 0) {
            h = n2;
            if (this.a.c().b(n)) {
                h = 1;
            }
        }
        this.a.c().b(n, h);
    }
    
    @Override
    public void removeAdUpdateListener(final AppLovinAdUpdateListener appLovinAdUpdateListener, final AppLovinAdSize appLovinAdSize) {
        if (appLovinAdSize == null) {
            throw new IllegalArgumentException("No ad size specified");
        }
        if (appLovinAdUpdateListener == null) {
            return;
        }
        final v a = this.a(n.a(appLovinAdSize, AppLovinAdType.REGULAR, o.b, this.a));
        synchronized (a.a) {
            if (a.e.contains(appLovinAdUpdateListener)) {
                a.e.remove(appLovinAdUpdateListener);
                this.b.d("AppLovinAdService", "Removed update listener: " + appLovinAdUpdateListener);
            }
        }
    }
    
    public void trackAndLaunchClick(final AppLovinAd appLovinAd, final String s, final AppLovinAdView appLovinAdView, final AdViewControllerImpl adViewControllerImpl, final Uri uri) {
        final an an = (an)appLovinAd;
        this.a(an, s);
        this.a(uri, an, appLovinAdView, adViewControllerImpl);
    }
    
    public void trackAndLaunchForegroundClick(final AppLovinAd appLovinAd, final String s, final AppLovinAdView appLovinAdView, final AdViewControllerImpl adViewControllerImpl, final Uri uri) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        final an an = (an)appLovinAd;
        this.b.d("AppLovinAdService", "Tracking foreground click on an ad...");
        this.a.getPostbackService().dispatchPostbackAsync(an.b(s), null, null, this.a.get(ea.bX), this.a.get(ea.bY), this.a.get(ea.bZ), new r(this, adViewControllerImpl, uri, an, appLovinAdView));
    }
    
    public void trackAndLaunchVideoClick(final AppLovinAd appLovinAd, final String s, final AppLovinAdView appLovinAdView, final Uri uri) {
        this.a((an)appLovinAd, s);
        AppLovinSdkUtils.openUri(appLovinAdView.getContext(), uri, this.a);
    }
}
