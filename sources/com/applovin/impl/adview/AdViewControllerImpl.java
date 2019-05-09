package com.applovin.impl.adview;

import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.webkit.WebViewDatabase;
import android.widget.RelativeLayout.LayoutParams;
import com.applovin.adview.AdViewController;
import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.adview.ClickTrackingOverlayView;
import com.applovin.impl.sdk.AppLovinAdServiceImpl;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.C1280g;
import com.applovin.impl.sdk.an;
import com.applovin.impl.sdk.ao;
import com.applovin.impl.sdk.ar;
import com.applovin.impl.sdk.bv;
import com.applovin.impl.sdk.ee;
import com.applovin.impl.sdk.gd;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicReference;

public class AdViewControllerImpl implements AdViewController {
    /* renamed from: A */
    private volatile AppLovinAdDisplayListener f1675A;
    /* renamed from: B */
    private volatile AppLovinAdViewEventListener f1676B;
    /* renamed from: C */
    private volatile AppLovinAdClickListener f1677C;
    /* renamed from: D */
    private volatile boolean f1678D;
    /* renamed from: a */
    private Context f1679a;
    /* renamed from: b */
    private ViewGroup f1680b;
    /* renamed from: c */
    private AppLovinSdk f1681c;
    /* renamed from: d */
    private AppLovinAdServiceImpl f1682d;
    /* renamed from: e */
    private AppLovinLogger f1683e;
    /* renamed from: f */
    private AppLovinAdSize f1684f;
    /* renamed from: g */
    private String f1685g;
    /* renamed from: h */
    private String f1686h;
    /* renamed from: i */
    private C1280g f1687i;
    /* renamed from: j */
    private ah f1688j;
    /* renamed from: k */
    private C1257k f1689k;
    /* renamed from: l */
    private C1260n f1690l;
    /* renamed from: m */
    private AppLovinAd f1691m;
    /* renamed from: n */
    private Runnable f1692n;
    /* renamed from: o */
    private Runnable f1693o;
    /* renamed from: p */
    private volatile AppLovinAd f1694p = null;
    /* renamed from: q */
    private volatile AppLovinAd f1695q = null;
    /* renamed from: r */
    private ClickTrackingOverlayView f1696r = null;
    /* renamed from: s */
    private ar f1697s = null;
    /* renamed from: t */
    private ar f1698t = null;
    /* renamed from: u */
    private final AtomicReference<AppLovinAd> f1699u = new AtomicReference();
    /* renamed from: v */
    private volatile boolean f1700v = false;
    /* renamed from: w */
    private volatile boolean f1701w = true;
    /* renamed from: x */
    private volatile boolean f1702x = false;
    /* renamed from: y */
    private volatile boolean f1703y = false;
    /* renamed from: z */
    private volatile AppLovinAdLoadListener f1704z;

    /* renamed from: a */
    private void m1944a(AppLovinAdView appLovinAdView, AppLovinSdk appLovinSdk, AppLovinAdSize appLovinAdSize, String str, Context context) {
        if (appLovinAdView == null) {
            throw new IllegalArgumentException("No parent view specified");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (appLovinAdSize == null) {
            throw new IllegalArgumentException("No ad size specified");
        } else {
            this.f1681c = appLovinSdk;
            this.f1682d = (AppLovinAdServiceImpl) appLovinSdk.getAdService();
            this.f1683e = appLovinSdk.getLogger();
            this.f1684f = appLovinAdSize;
            this.f1685g = str;
            this.f1679a = context;
            this.f1680b = appLovinAdView;
            this.f1691m = new ar();
            this.f1688j = new ah(this, appLovinSdk);
            this.f1693o = new C1254h();
            this.f1692n = new C1256j();
            this.f1689k = new C1257k(this, appLovinSdk);
            if (m1949a(context)) {
                m1947a(appLovinAdSize);
            } else {
                this.f1683e.userError("AppLovinAdView", "Web view database is corrupt, AdView not loaded");
            }
        }
    }

    /* renamed from: a */
    private void m1946a(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, Uri uri) {
        if (this.f1696r != null) {
            this.f1683e.mo4172d("AppLovinAdView", "Skipping click overlay rendering because it already exists");
        } else if (appLovinAdView != null) {
            this.f1683e.mo4172d("AppLovinAdView", "Creating and rendering click overlay");
            this.f1696r = new ClickTrackingOverlayView(appLovinAdView.getContext(), this.f1681c);
            this.f1696r.setLayoutParams(new LayoutParams(-1, -1));
            appLovinAdView.addView(this.f1696r);
            appLovinAdView.bringChildToFront(this.f1696r);
            this.f1682d.trackAndLaunchForegroundClick(appLovinAd, this.f1686h, appLovinAdView, this, uri);
        } else {
            this.f1683e.mo4173e("AppLovinAdView", "Skipping click overlay rendering because AppLovinAdView has been destroyed");
        }
    }

    /* renamed from: a */
    private void m1947a(AppLovinAdSize appLovinAdSize) {
        try {
            this.f1690l = new C1260n(this.f1688j, this.f1681c, this.f1679a);
            this.f1690l.setBackgroundColor(0);
            this.f1690l.setWillNotCacheDrawing(false);
            if (new ee(this.f1681c).m2721v() && VERSION.SDK_INT >= 19) {
                this.f1690l.setLayerType(2, null);
            }
            this.f1680b.setBackgroundColor(0);
            this.f1680b.addView(this.f1690l);
            m1953b(this.f1690l, appLovinAdSize);
            if (((AppLovinSdkImpl) this.f1681c).isFireOS()) {
                ee eeVar = new ee(this.f1681c);
                if (!this.f1700v && eeVar.m2677D()) {
                    m1948a(this.f1693o);
                }
                if (eeVar.m2678E()) {
                    m1948a(new C1255i());
                }
            } else {
                if (!this.f1700v) {
                    m1948a(this.f1693o);
                }
                m1948a(new C1255i());
            }
            this.f1700v = true;
        } catch (Throwable th) {
            this.f1683e.userError("AppLovinAdView", "Failed to create AdView: " + th.getMessage());
        }
    }

    /* renamed from: a */
    private void m1948a(Runnable runnable) {
        AppLovinSdkUtils.runOnUiThread(runnable);
    }

    /* renamed from: a */
    private static boolean m1949a(Context context) {
        try {
            if (VERSION.SDK_INT >= 11) {
                return true;
            }
            WebViewDatabase instance = WebViewDatabase.getInstance(context);
            Method declaredMethod = WebViewDatabase.class.getDeclaredMethod("getCacheTotalSize", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(instance, new Object[0]);
            return true;
        } catch (Throwable e) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", e);
            return true;
        } catch (Throwable e2) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", e2);
            return true;
        } catch (Throwable e22) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", e22);
            return true;
        } catch (Throwable e3) {
            Log.e("AppLovinAdView", "getCacheTotalSize() reported exception", e3);
            return false;
        } catch (Throwable e32) {
            Log.e("AppLovinAdView", "Unexpected error while checking DB state", e32);
            return false;
        }
    }

    /* renamed from: b */
    private void m1952b() {
        this.f1683e.mo4172d("AppLovinAdView", "Destroying...");
        if (this.f1682d != null) {
            this.f1682d.removeAdUpdateListener(this.f1689k, getSize());
        }
        if (this.f1690l != null) {
            try {
                ViewParent parent = this.f1690l.getParent();
                if (parent != null && (parent instanceof ViewGroup)) {
                    ((ViewGroup) parent).removeView(this.f1690l);
                }
                this.f1690l.removeAllViews();
                if (new ee(this.f1681c).ak()) {
                    this.f1690l.loadUrl("about:blank");
                    this.f1690l.onPause();
                    this.f1690l.destroyDrawingCache();
                }
            } catch (Throwable th) {
                this.f1683e.mo4179w("AppLovinAdView", "Unable to destroy ad view", th);
            }
            this.f1690l.destroy();
            this.f1690l = null;
        }
        this.f1702x = true;
    }

    /* renamed from: b */
    private static void m1953b(View view, AppLovinAdSize appLovinAdSize) {
        if (view != null) {
            DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
            int applyDimension = appLovinAdSize.getLabel().equals(AppLovinAdSize.INTERSTITIAL.getLabel()) ? -1 : appLovinAdSize.getWidth() == -1 ? displayMetrics.widthPixels : (int) TypedValue.applyDimension(1, (float) appLovinAdSize.getWidth(), displayMetrics);
            int applyDimension2 = appLovinAdSize.getLabel().equals(AppLovinAdSize.INTERSTITIAL.getLabel()) ? -1 : appLovinAdSize.getHeight() == -1 ? displayMetrics.heightPixels : (int) TypedValue.applyDimension(1, (float) appLovinAdSize.getHeight(), displayMetrics);
            ViewGroup.LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LayoutParams(-2, -2);
            }
            layoutParams.width = applyDimension;
            layoutParams.height = applyDimension2;
            if (layoutParams instanceof LayoutParams) {
                layoutParams.addRule(10);
                layoutParams.addRule(9);
            }
            view.setLayoutParams(layoutParams);
        }
    }

    /* renamed from: c */
    private void m1955c() {
        m1948a(new C1247a(this));
    }

    /* renamed from: d */
    private void m1957d() {
        m1948a(new C1253g(this));
    }

    /* renamed from: e */
    private void m1959e() {
        if (this.f1687i != null) {
            this.f1687i.m2913c();
            this.f1687i = null;
        }
    }

    /* renamed from: a */
    void m1971a() {
        if (this.f1697s == null && this.f1698t == null) {
            this.f1683e.mo4172d("AppLovinAdView", "Ad: " + this.f1694p + " with placement = \"" + this.f1686h + "\" closed.");
            m1948a(this.f1693o);
            bv.m2409b(this.f1675A, this.f1694p, this.f1681c);
            this.f1694p = null;
            this.f1686h = null;
        } else if (new ee(this.f1681c).ao()) {
            contractAd();
        }
    }

    /* renamed from: a */
    void m1972a(int i) {
        if (!this.f1702x) {
            this.f1682d.addAdUpdateListener(this.f1689k, this.f1684f);
            m1948a(this.f1693o);
        }
        m1948a(new C1252f(this, i));
    }

    /* renamed from: a */
    void m1973a(AppLovinAd appLovinAd) {
        if (appLovinAd != null) {
            this.f1703y = true;
            if (this.f1702x) {
                this.f1699u.set(appLovinAd);
                this.f1683e.mo4172d("AppLovinAdView", "Ad view has paused when an ad was received, ad saved for later");
            } else {
                this.f1682d.addAdUpdateListener(this.f1689k, this.f1684f);
                renderAd(appLovinAd);
            }
            m1948a(new C1251e(this, appLovinAd));
            return;
        }
        this.f1683e.mo4173e("AppLovinAdView", "No provided when to the view controller");
        m1972a(-1);
    }

    /* renamed from: a */
    void m1974a(AppLovinAd appLovinAd, String str, AppLovinAdView appLovinAdView, Uri uri) {
        bv.m2400a(this.f1677C, appLovinAd, this.f1681c);
        if (appLovinAdView == null) {
            this.f1683e.mo4173e("AppLovinAdView", "Unable to process ad click - AppLovinAdView destroyed prematurely");
        } else if (!new ee(this.f1681c).m2725z() || uri == null) {
            this.f1682d.trackAndLaunchClick(appLovinAd, str, appLovinAdView, this, uri);
        } else {
            m1946a(appLovinAd, appLovinAdView, uri);
        }
    }

    public void contractAd() {
        m1948a(new C1250d(this));
    }

    public void destroy() {
        if (!(this.f1690l == null || this.f1697s == null)) {
            contractAd();
        }
        m1952b();
    }

    public void dismissInterstitialIfRequired() {
        if ((this.f1679a instanceof AppLovinInterstitialActivity) && (this.f1694p instanceof an)) {
            AppLovinInterstitialActivity appLovinInterstitialActivity = (AppLovinInterstitialActivity) this.f1679a;
            if ((((an) this.f1694p).m1801N() == ao.DISMISS ? 1 : null) != null && appLovinInterstitialActivity.getPoststitialWasDisplayed()) {
                appLovinInterstitialActivity.dismiss();
            }
        }
    }

    public void expandAd() {
        m1948a(new C1248b(this));
    }

    public AppLovinAdViewEventListener getAdViewEventListener() {
        return this.f1676B;
    }

    public C1260n getAdWebView() {
        return this.f1690l;
    }

    public AppLovinAd getCurrentAd() {
        return this.f1694p;
    }

    public AppLovinAdView getParentView() {
        return (AppLovinAdView) this.f1680b;
    }

    public AppLovinSdk getSdk() {
        return this.f1681c;
    }

    public AppLovinAdSize getSize() {
        return this.f1684f;
    }

    public String getZoneId() {
        return this.f1685g;
    }

    public void initializeAdView(AppLovinAdView appLovinAdView, Context context, AppLovinAdSize appLovinAdSize, String str, AppLovinSdk appLovinSdk, AttributeSet attributeSet) {
        if (appLovinAdView == null) {
            throw new IllegalArgumentException("No parent view specified");
        } else if (context == null) {
            Log.e(AppLovinLogger.SDK_TAG, "Unable to build AppLovinAdView: no context provided. Please use a different constructor for this view.");
        } else {
            AppLovinAdSize a;
            if (appLovinAdSize == null) {
                a = C1258l.m2105a(attributeSet);
                if (a == null) {
                    a = AppLovinAdSize.BANNER;
                }
            } else {
                a = appLovinAdSize;
            }
            AppLovinSdk instance = appLovinSdk == null ? AppLovinSdk.getInstance(context) : appLovinSdk;
            if (instance != null && !instance.hasCriticalErrors()) {
                m1944a(appLovinAdView, instance, a, str, context);
                if (C1258l.m2106b(attributeSet)) {
                    loadNextAd();
                }
            }
        }
    }

    public boolean isAdReadyToDisplay() {
        return !TextUtils.isEmpty(this.f1685g) ? this.f1682d.hasPreloadedAdForZoneId(this.f1685g) : this.f1682d.hasPreloadedAd(this.f1684f);
    }

    public boolean isAutoDestroy() {
        return this.f1701w;
    }

    public boolean isForegroundClickInvalidated() {
        return this.f1678D;
    }

    public void loadNextAd() {
        if (this.f1681c == null || this.f1689k == null || this.f1679a == null || !this.f1700v) {
            Log.i(AppLovinLogger.SDK_TAG, "Unable to load next ad: AppLovinAdView is not initialized.");
        } else if (TextUtils.isEmpty(this.f1685g)) {
            this.f1682d.loadNextAd(this.f1684f, this.f1689k);
        } else {
            this.f1682d.loadNextAdForZoneId(this.f1685g, this.f1689k);
        }
    }

    public void onAdHtmlLoaded(WebView webView) {
        if (this.f1694p instanceof an) {
            webView.setVisibility(0);
            try {
                if (this.f1694p != this.f1695q && this.f1675A != null) {
                    this.f1695q = this.f1694p;
                    bv.m2401a(this.f1675A, this.f1694p, this.f1681c);
                }
            } catch (Throwable th) {
                this.f1683e.userError("AppLovinAdView", "Exception while notifying ad display listener", th);
            }
        }
    }

    public void onDetachedFromWindow() {
        if (this.f1700v) {
            if (this.f1694p != this.f1691m) {
                bv.m2409b(this.f1675A, this.f1694p, this.f1681c);
            }
            if (this.f1690l == null || this.f1697s == null) {
                this.f1683e.mo4172d("AppLovinAdView", "onDetachedFromWindowCalled without an expanded ad present");
            } else {
                this.f1683e.mo4172d("AppLovinAdView", "onDetachedFromWindowCalled with expanded ad present");
                if (new ee(this.f1681c).am()) {
                    contractAd();
                } else {
                    m1955c();
                }
            }
            if (this.f1701w) {
                m1952b();
            }
        }
    }

    public void onVisibilityChanged(int i) {
        if (!this.f1700v || !this.f1701w) {
            return;
        }
        if (i == 8 || i == 4) {
            pause();
        } else if (i == 0) {
            resume();
        }
    }

    public void pause() {
        if (this.f1700v) {
            if (new ee(this.f1681c).an()) {
                this.f1682d.removeAdUpdateListener(this.f1689k, getSize());
            }
            AppLovinAd appLovinAd = this.f1694p;
            renderAd(this.f1691m, this.f1686h);
            if (appLovinAd != null) {
                this.f1699u.set(appLovinAd);
            }
            this.f1702x = true;
        }
    }

    public void removeClickTrackingOverlay() {
        if (this.f1696r != null) {
            ViewParent parent = this.f1696r.getParent();
            if (parent != null && (parent instanceof ViewGroup)) {
                ((ViewGroup) parent).removeView(this.f1696r);
                this.f1696r = null;
                return;
            }
            return;
        }
        this.f1683e.mo4172d("AppLovinAdView", "Asked to remove an overlay when none existed. Skipping...");
    }

    public void renderAd(AppLovinAd appLovinAd) {
        renderAd(appLovinAd, null);
    }

    public void renderAd(AppLovinAd appLovinAd, String str) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        } else if (this.f1700v) {
            AppLovinAd a = gd.m2936a(appLovinAd, this.f1681c);
            if (a != null && a != this.f1694p) {
                this.f1683e.mo4172d("AppLovinAdView", "Rendering ad #" + a.getAdIdNumber() + " (" + a.getSize() + ") over placement: " + str);
                if (!(this.f1694p instanceof ar)) {
                    bv.m2409b(this.f1675A, this.f1694p, this.f1681c);
                    if (!((a instanceof ar) || a.getSize() == AppLovinAdSize.INTERSTITIAL)) {
                        m1959e();
                    }
                }
                this.f1699u.set(null);
                this.f1695q = null;
                this.f1694p = a;
                this.f1686h = str;
                if (a.getSize() == this.f1684f) {
                    if (!((a instanceof ar) || this.f1697s == null)) {
                        if (new ee(this.f1681c).al()) {
                            m1957d();
                            this.f1683e.mo4172d("AppLovinAdView", "Fade out the old ad scheduled");
                        } else {
                            m1955c();
                        }
                    }
                    if (!(a instanceof ar) || (this.f1697s == null && this.f1698t == null)) {
                        m1948a(this.f1692n);
                    } else {
                        this.f1683e.mo4172d("AppLovinAdView", "ignoring empty ad render with expanded ad");
                    }
                }
            } else if (a == null) {
                this.f1683e.mo4178w("AppLovinAdView", "Unable to render ad: " + a + ". Internal inconsistency error.");
            } else {
                this.f1683e.mo4178w("AppLovinAdView", "Ad #" + a.getAdIdNumber() + " is already showing, ignoring");
            }
        } else {
            Log.i(AppLovinLogger.SDK_TAG, "Unable to render ad: AppLovinAdView is not initialized.");
        }
    }

    public void resume() {
        if (this.f1700v) {
            if (this.f1703y && new ee(this.f1681c).an()) {
                this.f1682d.addAdUpdateListener(this.f1689k, this.f1684f);
            }
            AppLovinAd appLovinAd = (AppLovinAd) this.f1699u.getAndSet(null);
            if (appLovinAd != null) {
                renderAd(appLovinAd, this.f1686h);
            }
            this.f1702x = false;
        }
    }

    public void setAdClickListener(AppLovinAdClickListener appLovinAdClickListener) {
        this.f1677C = appLovinAdClickListener;
    }

    public void setAdDisplayListener(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.f1675A = appLovinAdDisplayListener;
    }

    public void setAdLoadListener(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.f1704z = appLovinAdLoadListener;
    }

    public void setAdVideoPlaybackListener(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
    }

    public void setAdViewEventListener(AppLovinAdViewEventListener appLovinAdViewEventListener) {
        this.f1676B = appLovinAdViewEventListener;
    }

    public void setAutoDestroy(boolean z) {
        this.f1701w = z;
    }

    public void setIsForegroundClickInvalidated(boolean z) {
        this.f1678D = z;
    }

    public void setStatsManagerHelper(C1280g c1280g) {
        this.f1690l.m2113a(c1280g);
    }
}
