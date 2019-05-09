package com.applovin.impl.sdk;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.text.TextUtils;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.adview.AdViewControllerImpl;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdUpdateListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppLovinAdServiceImpl implements AppLovinAdService {
    public static String URI_LOAD_URL = "/adservice/load_url";
    public static String URI_NO_OP = "/adservice/no_op";
    public static String URI_SKIP_AD = "/adservice/skip";
    public static String URI_TRACK_CLICK_IMMEDIATELY = "/adservice/track_click_now";
    /* renamed from: a */
    private final AppLovinSdkImpl f1935a;
    /* renamed from: b */
    private final AppLovinLogger f1936b;
    /* renamed from: c */
    private Handler f1937c;
    /* renamed from: d */
    private final Map<C1287n, C1294v> f1938d;
    /* renamed from: e */
    private final Object f1939e = new Object();

    AppLovinAdServiceImpl(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f1935a = appLovinSdkImpl;
        this.f1936b = appLovinSdkImpl.getLogger();
        this.f1937c = new Handler(Looper.getMainLooper());
        this.f1938d = new HashMap(8);
        this.f1938d.put(C1287n.m3042c(appLovinSdkImpl), new C1294v());
        this.f1938d.put(C1287n.m3043d(appLovinSdkImpl), new C1294v());
        this.f1938d.put(C1287n.m3044e(appLovinSdkImpl), new C1294v());
        this.f1938d.put(C1287n.m3045f(appLovinSdkImpl), new C1294v());
        this.f1938d.put(C1287n.m3046g(appLovinSdkImpl), new C1294v());
        this.f1938d.put(C1287n.m3047h(appLovinSdkImpl), new C1294v());
        this.f1938d.put(C1287n.m3048i(appLovinSdkImpl), new C1294v());
    }

    /* renamed from: a */
    private C1294v m2120a(C1287n c1287n) {
        C1294v c1294v;
        synchronized (this.f1939e) {
            c1294v = (C1294v) this.f1938d.get(c1287n);
            if (c1294v == null) {
                c1294v = new C1294v();
                this.f1938d.put(c1287n, c1294v);
            }
        }
        return c1294v;
    }

    /* renamed from: a */
    private void m2121a(Uri uri, an anVar, AppLovinAdView appLovinAdView, AdViewControllerImpl adViewControllerImpl) {
        if (appLovinAdView != null) {
            adViewControllerImpl.removeClickTrackingOverlay();
            expireAdLoadState(anVar);
            if (AppLovinSdkUtils.openUri(appLovinAdView.getContext(), uri, this.f1935a)) {
                bv.m2411c(adViewControllerImpl.getAdViewEventListener(), (AppLovinAd) anVar, appLovinAdView, this.f1935a);
            }
            adViewControllerImpl.dismissInterstitialIfRequired();
            return;
        }
        this.f1936b.mo4173e("AppLovinAdService", "Unable to launch click - adView has been prematurely destroyed");
    }

    /* renamed from: a */
    private void m2125a(an anVar, String str) {
        String b = anVar.m1816b(str);
        if (AppLovinSdkUtils.isValidString(b)) {
            this.f1935a.getPersistentPostbackManager().m2610a(b, null);
        }
    }

    /* renamed from: a */
    private void m2126a(ex exVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        if (ag.m2242a(this.f1935a.getApplicationContext(), this.f1935a) || ((Boolean) this.f1935a.get(ea.cF)).booleanValue()) {
            this.f1935a.m2145f();
            this.f1936b.mo4175i("AppLovinAdService", "Loading ad using '" + exVar.getClass().getSimpleName() + "'...");
            this.f1935a.getTaskManager().m2855a((dx) exVar, fe.MAIN);
            return;
        }
        this.f1936b.userError("AppLovinAdService", "Failing ad load due to no internet connection.");
        appLovinAdLoadListener.failedToReceiveAd(AppLovinErrorCodes.NO_NETWORK);
    }

    /* renamed from: a */
    private void m2127a(C1287n c1287n, C1293u c1293u) {
        AppLovinAd appLovinAd = (AppLovinAd) this.f1935a.m2142c().mo4143e(c1287n);
        if (appLovinAd != null) {
            this.f1936b.mo4172d("AppLovinAdService", "Using pre-loaded ad: " + appLovinAd + " for " + c1287n);
            c1293u.adReceived(appLovinAd);
        } else {
            m2126a(new ex(c1287n, c1293u, this.f1935a), (AppLovinAdLoadListener) c1293u);
        }
        if (!c1287n.m3063l() || appLovinAd != null) {
            if (c1287n.m3064m()) {
                this.f1935a.m2142c().mo4148j(c1287n);
            } else if (appLovinAd != null && c1287n.m3059h() > 0) {
                this.f1935a.m2142c().mo4148j(c1287n);
            }
        }
    }

    /* renamed from: a */
    private void m2128a(C1287n c1287n, AppLovinAdLoadListener appLovinAdLoadListener) {
        if (c1287n == null) {
            throw new IllegalArgumentException("No zone specified");
        } else if (appLovinAdLoadListener == null) {
            throw new IllegalArgumentException("No callback specified");
        } else if (!ag.m2242a(this.f1935a.getApplicationContext(), this.f1935a) && !((Boolean) this.f1935a.get(ea.cF)).booleanValue()) {
            this.f1936b.userError("AppLovinAdService", "Failing ad load due to no internet connection.");
            appLovinAdLoadListener.failedToReceiveAd(AppLovinErrorCodes.NO_NETWORK);
        } else if (!((Boolean) this.f1935a.get(ea.cZ)).booleanValue() || c1287n.m3064m() || !this.f1935a.getZoneManager().m3070a() || this.f1935a.getZoneManager().m3071a(c1287n)) {
            AppLovinAd appLovinAd;
            this.f1935a.getLogger().mo4172d("AppLovinAdService", "Loading next ad of zone {" + c1287n + "}...");
            C1294v a = m2120a(c1287n);
            synchronized (a.f2626a) {
                Object obj = System.currentTimeMillis() > a.f2628c ? 1 : null;
                if (a.f2627b == null || obj != null) {
                    a.f2631f.add(appLovinAdLoadListener);
                    if (a.f2629d) {
                        this.f1936b.mo4172d("AppLovinAdService", "Already waiting on an ad load...");
                        appLovinAd = null;
                    } else {
                        this.f1936b.mo4172d("AppLovinAdService", "Loading next ad...");
                        a.f2629d = true;
                        C1293u c1293u = new C1293u(this, a);
                        if (!c1287n.m3062k()) {
                            this.f1936b.mo4172d("AppLovinAdService", "Task merge not necessary.");
                            m2127a(c1287n, c1293u);
                        } else if (this.f1935a.m2142c().mo4137a(c1287n, (Object) c1293u)) {
                            this.f1936b.mo4172d("AppLovinAdService", "Attaching load listener to initial preload task...");
                        } else {
                            this.f1936b.mo4172d("AppLovinAdService", "Skipped attach of initial preload callback.");
                            m2127a(c1287n, c1293u);
                        }
                        appLovinAd = null;
                    }
                } else {
                    appLovinAd = a.f2627b;
                }
            }
            if (appLovinAd != null) {
                appLovinAdLoadListener.adReceived(appLovinAd);
            }
        } else {
            this.f1936b.userError("AppLovinAdService", "Failed to load ad for zone (" + c1287n.m3051a() + "). Please check that the zone has been added to your AppLovin account.");
            appLovinAdLoadListener.failedToReceiveAd(-7);
        }
    }

    /* renamed from: a */
    private boolean m2129a() {
        return ((PowerManager) this.f1935a.getApplicationContext().getSystemService("power")).isScreenOn();
    }

    /* renamed from: b */
    private void m2132b(C1287n c1287n) {
        long j = c1287n.m3061j();
        if (j > 0) {
            this.f1935a.getTaskManager().m2856a(new C1295w(this, c1287n, null), fe.MAIN, (j + 2) * 1000);
        }
    }

    /* renamed from: a */
    void m2135a(AppLovinAdLoadListener appLovinAdLoadListener) {
        m2128a(C1287n.m3047h(this.f1935a), appLovinAdLoadListener);
    }

    public void addAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener) {
        addAdUpdateListener(appLovinAdUpdateListener, AppLovinAdSize.BANNER);
    }

    public void addAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize) {
        if (appLovinAdUpdateListener == null) {
            throw new IllegalArgumentException("No ad listener specified");
        } else if (appLovinAdSize == null) {
            throw new IllegalArgumentException("No ad size specified");
        } else {
            C1287n a = C1287n.m3035a(appLovinAdSize, AppLovinAdType.REGULAR, C1288o.DIRECT, this.f1935a);
            C1294v a2 = m2120a(a);
            Object obj = null;
            synchronized (a2.f2626a) {
                if (a2.f2628c > 0 && !a2.f2630e.contains(appLovinAdUpdateListener)) {
                    a2.f2630e.add(appLovinAdUpdateListener);
                    obj = 1;
                    this.f1936b.mo4172d("AppLovinAdService", "Added update listener: " + appLovinAdUpdateListener);
                }
            }
            if (obj != null) {
                this.f1935a.getTaskManager().m2855a(new C1295w(this, a, null), fe.MAIN);
            }
        }
    }

    /* renamed from: b */
    void m2136b(AppLovinAdLoadListener appLovinAdLoadListener) {
        m2128a(C1287n.m3048i(this.f1935a), appLovinAdLoadListener);
    }

    public AppLovinAd dequeueAd(C1287n c1287n) {
        AppLovinAd appLovinAd = (AppLovinAd) this.f1935a.m2142c().mo4142d(c1287n);
        this.f1936b.mo4172d("AppLovinAdService", "Dequeued ad: " + appLovinAd + " for zone: " + c1287n + "...");
        return appLovinAd;
    }

    public void expireAdLoadState(AppLovinAd appLovinAd) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        } else if (appLovinAd instanceof C1227q) {
            C1294v a = m2120a(((C1227q) appLovinAd).mo3997t());
            synchronized (a.f2626a) {
                a.f2627b = null;
                a.f2628c = 0;
            }
        } else {
            throw new IllegalArgumentException("Unknown ad type specified: " + appLovinAd.getClass().getName());
        }
    }

    public boolean hasPreloadedAd(AppLovinAdSize appLovinAdSize) {
        return this.f1935a.m2142c().mo4146h(C1287n.m3035a(appLovinAdSize, AppLovinAdType.REGULAR, C1288o.DIRECT, this.f1935a));
    }

    public boolean hasPreloadedAdForZoneId(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f1936b.userError("AppLovinAdService", "Unable to check if ad is preloaded - invalid zone id");
            return false;
        }
        return this.f1935a.m2142c().mo4146h(C1287n.m3037a(str, this.f1935a));
    }

    public void loadNextAd(AppLovinAdSize appLovinAdSize, AppLovinAdLoadListener appLovinAdLoadListener) {
        m2128a(C1287n.m3035a(appLovinAdSize, AppLovinAdType.REGULAR, C1288o.DIRECT, this.f1935a), appLovinAdLoadListener);
    }

    public void loadNextAdForAdToken(String str, AppLovinAdLoadListener appLovinAdLoadListener) {
        String trim = str != null ? str.trim() : null;
        if (TextUtils.isEmpty(trim)) {
            this.f1936b.userError("AppLovinAdService", "Invalid ad token specified");
            appLovinAdLoadListener.failedToReceiveAd(-8);
            return;
        }
        this.f1936b.mo4172d("AppLovinAdService", "Loading next ad for token: " + trim);
        m2126a(new fa(trim, appLovinAdLoadListener, this.f1935a), appLovinAdLoadListener);
    }

    public void loadNextAdForZoneId(String str, AppLovinAdLoadListener appLovinAdLoadListener) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("No zone id specified");
        }
        this.f1936b.mo4172d("AppLovinAdService", "Loading next ad of zone {" + str + "}");
        m2128a(C1287n.m3037a(str, this.f1935a), appLovinAdLoadListener);
    }

    public void loadNextAdForZoneIds(List<String> list, AppLovinAdLoadListener appLovinAdLoadListener) {
        List a = aa.m2195a((List) list);
        if (a == null || a.isEmpty()) {
            this.f1936b.userError("AppLovinAdService", "No zones were provided");
            appLovinAdLoadListener.failedToReceiveAd(-7);
            return;
        }
        this.f1936b.mo4172d("AppLovinAdService", "Loading next ad for zones: " + a);
        m2126a(new ew(a, appLovinAdLoadListener, this.f1935a), appLovinAdLoadListener);
    }

    public void loadNextMediatedAd(AppLovinAdSize appLovinAdSize, AppLovinAdLoadListener appLovinAdLoadListener) {
        m2128a(C1287n.m3046g(this.f1935a), appLovinAdLoadListener);
    }

    public void preloadAd(AppLovinAdSize appLovinAdSize) {
        this.f1935a.m2145f();
        this.f1935a.m2142c().mo4148j(C1287n.m3035a(appLovinAdSize, AppLovinAdType.REGULAR, C1288o.DIRECT, this.f1935a));
    }

    public void preloadAdForZoneId(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f1936b.userError("AppLovinAdService", "Unable to preload ad for invalid zone identifier");
            return;
        }
        C1287n a = C1287n.m3037a(str, this.f1935a);
        this.f1935a.m2142c().mo4147i(a);
        this.f1935a.m2142c().mo4148j(a);
    }

    public void preloadAds(C1287n c1287n) {
        this.f1935a.m2142c().mo4147i(c1287n);
        int h = c1287n.m3059h();
        if (h == 0 && this.f1935a.m2142c().mo4140b(c1287n)) {
            h = 1;
        }
        this.f1935a.m2142c().mo4138b(c1287n, h);
    }

    public void removeAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize) {
        if (appLovinAdSize == null) {
            throw new IllegalArgumentException("No ad size specified");
        } else if (appLovinAdUpdateListener != null) {
            C1294v a = m2120a(C1287n.m3035a(appLovinAdSize, AppLovinAdType.REGULAR, C1288o.DIRECT, this.f1935a));
            synchronized (a.f2626a) {
                if (a.f2630e.contains(appLovinAdUpdateListener)) {
                    a.f2630e.remove(appLovinAdUpdateListener);
                    this.f1936b.mo4172d("AppLovinAdService", "Removed update listener: " + appLovinAdUpdateListener);
                }
            }
        }
    }

    public void trackAndLaunchClick(AppLovinAd appLovinAd, String str, AppLovinAdView appLovinAdView, AdViewControllerImpl adViewControllerImpl, Uri uri) {
        an anVar = (an) appLovinAd;
        m2125a(anVar, str);
        m2121a(uri, anVar, appLovinAdView, adViewControllerImpl);
    }

    public void trackAndLaunchForegroundClick(AppLovinAd appLovinAd, String str, AppLovinAdView appLovinAdView, AdViewControllerImpl adViewControllerImpl, Uri uri) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        an anVar = (an) appLovinAd;
        this.f1936b.mo4172d("AppLovinAdService", "Tracking foreground click on an ad...");
        int intValue = ((Integer) this.f1935a.get(ea.bX)).intValue();
        int intValue2 = ((Integer) this.f1935a.get(ea.bY)).intValue();
        int intValue3 = ((Integer) this.f1935a.get(ea.bZ)).intValue();
        this.f1935a.getPostbackService().dispatchPostbackAsync(anVar.m1816b(str), null, null, intValue, (long) intValue2, intValue3, new C1290r(this, adViewControllerImpl, uri, anVar, appLovinAdView));
    }

    public void trackAndLaunchVideoClick(AppLovinAd appLovinAd, String str, AppLovinAdView appLovinAdView, Uri uri) {
        m2125a((an) appLovinAd, str);
        AppLovinSdkUtils.openUri(appLovinAdView.getContext(), uri, this.f1935a);
    }
}
