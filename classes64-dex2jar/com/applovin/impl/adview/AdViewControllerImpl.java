// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.impl.sdk.gd;
import android.webkit.WebView;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.applovin.impl.sdk.ao;
import com.applovin.impl.sdk.an;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.impl.sdk.bv;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewParent;
import com.applovin.sdk.AppLovinAdUpdateListener;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import android.util.Log;
import android.webkit.WebViewDatabase;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import android.graphics.Paint;
import android.os.Build$VERSION;
import com.applovin.impl.sdk.ee;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.net.Uri;
import com.applovin.adview.AppLovinAdView;
import android.view.View;
import com.applovin.sdk.AppLovinAdLoadListener;
import java.util.concurrent.atomic.AtomicReference;
import com.applovin.adview.ClickTrackingOverlayView;
import com.applovin.sdk.AppLovinAd;
import com.applovin.impl.sdk.g;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.impl.sdk.AppLovinAdServiceImpl;
import com.applovin.sdk.AppLovinSdk;
import android.view.ViewGroup;
import android.content.Context;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.adview.AdViewController;

public class AdViewControllerImpl implements AdViewController
{
    private volatile AppLovinAdDisplayListener A;
    private volatile AppLovinAdViewEventListener B;
    private volatile AppLovinAdClickListener C;
    private volatile boolean D;
    private Context a;
    private ViewGroup b;
    private AppLovinSdk c;
    private AppLovinAdServiceImpl d;
    private AppLovinLogger e;
    private AppLovinAdSize f;
    private String g;
    private String h;
    private g i;
    private ah j;
    private k k;
    private n l;
    private AppLovinAd m;
    private Runnable n;
    private Runnable o;
    private volatile AppLovinAd p;
    private volatile AppLovinAd q;
    private ClickTrackingOverlayView r;
    private ar s;
    private ar t;
    private final AtomicReference<AppLovinAd> u;
    private volatile boolean v;
    private volatile boolean w;
    private volatile boolean x;
    private volatile boolean y;
    private volatile AppLovinAdLoadListener z;
    
    public AdViewControllerImpl() {
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = new AtomicReference<AppLovinAd>();
        this.v = false;
        this.w = true;
        this.x = false;
        this.y = false;
    }
    
    private void a(final AppLovinAdView b, final AppLovinSdk c, final AppLovinAdSize f, final String g, final Context a) {
        if (b == null) {
            throw new IllegalArgumentException("No parent view specified");
        }
        if (c == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        if (f == null) {
            throw new IllegalArgumentException("No ad size specified");
        }
        this.c = c;
        this.d = (AppLovinAdServiceImpl)c.getAdService();
        this.e = c.getLogger();
        this.f = f;
        this.g = g;
        this.a = a;
        this.b = (ViewGroup)b;
        this.m = new com.applovin.impl.sdk.ar();
        this.j = new ah(this, c);
        this.o = new h(this, null);
        this.n = new j(this, null);
        this.k = new k(this, c);
        if (a(a)) {
            this.a(f);
            return;
        }
        this.e.userError("AppLovinAdView", "Web view database is corrupt, AdView not loaded");
    }
    
    private void a(final AppLovinAd appLovinAd, final AppLovinAdView appLovinAdView, final Uri uri) {
        if (this.r != null) {
            this.e.d("AppLovinAdView", "Skipping click overlay rendering because it already exists");
            return;
        }
        if (appLovinAdView != null) {
            this.e.d("AppLovinAdView", "Creating and rendering click overlay");
            (this.r = new ClickTrackingOverlayView(appLovinAdView.getContext(), this.c)).setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
            appLovinAdView.addView((View)this.r);
            appLovinAdView.bringChildToFront((View)this.r);
            this.d.trackAndLaunchForegroundClick(appLovinAd, this.h, appLovinAdView, this, uri);
            return;
        }
        this.e.e("AppLovinAdView", "Skipping click overlay rendering because AppLovinAdView has been destroyed");
    }
    
    private void a(final AppLovinAdSize appLovinAdSize) {
        try {
            (this.l = new n(this.j, this.c, this.a)).setBackgroundColor(0);
            this.l.setWillNotCacheDrawing(false);
            if (new ee(this.c).v() && Build$VERSION.SDK_INT >= 19) {
                this.l.setLayerType(2, (Paint)null);
            }
            this.b.setBackgroundColor(0);
            this.b.addView((View)this.l);
            b((View)this.l, appLovinAdSize);
            if (((AppLovinSdkImpl)this.c).isFireOS()) {
                final ee ee = new ee(this.c);
                if (!this.v && ee.D()) {
                    this.a(this.o);
                }
                if (ee.E()) {
                    this.a(new i(this, null));
                }
            }
            else {
                if (!this.v) {
                    this.a(this.o);
                }
                this.a(new i(this, null));
            }
            this.v = true;
        }
        catch (Throwable t) {
            this.e.userError("AppLovinAdView", "Failed to create AdView: " + t.getMessage());
        }
    }
    
    private void a(final Runnable runnable) {
        AppLovinSdkUtils.runOnUiThread(runnable);
    }
    
    private static boolean a(final Context context) {
        try {
            if (Build$VERSION.SDK_INT < 11) {
                final WebViewDatabase instance = WebViewDatabase.getInstance(context);
                final Method declaredMethod = WebViewDatabase.class.getDeclaredMethod("getCacheTotalSize", (Class<?>[])new Class[0]);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(instance, new Object[0]);
            }
            return true;
        }
        catch (NoSuchMethodException ex) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", (Throwable)ex);
            return true;
        }
        catch (IllegalArgumentException ex2) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", (Throwable)ex2);
            return true;
        }
        catch (IllegalAccessException ex3) {
            Log.e("AppLovinAdView", "Error invoking getCacheTotalSize()", (Throwable)ex3);
            return true;
        }
        catch (InvocationTargetException ex4) {
            Log.e("AppLovinAdView", "getCacheTotalSize() reported exception", (Throwable)ex4);
            return false;
        }
        catch (Throwable t) {
            Log.e("AppLovinAdView", "Unexpected error while checking DB state", t);
            return false;
        }
    }
    
    private void b() {
        this.e.d("AppLovinAdView", "Destroying...");
        if (this.d != null) {
            this.d.removeAdUpdateListener(this.k, this.getSize());
        }
        while (true) {
            if (this.l == null) {
                break Label_0135;
            }
            try {
                final ViewParent parent = this.l.getParent();
                if (parent != null && parent instanceof ViewGroup) {
                    ((ViewGroup)parent).removeView((View)this.l);
                }
                this.l.removeAllViews();
                Label_0123: {
                    if (!new ee(this.c).ak()) {
                        break Label_0123;
                    }
                    try {
                        this.l.loadUrl("about:blank");
                        this.l.onPause();
                        this.l.destroyDrawingCache();
                        this.l.destroy();
                        this.l = null;
                        this.x = true;
                    }
                    catch (Throwable t) {
                        this.e.e("AppLovinAdView", "Encountered error while cleaning up WebView", t);
                    }
                }
            }
            catch (Throwable t2) {
                this.e.w("AppLovinAdView", "Unable to destroy ad view", t2);
                continue;
            }
            break;
        }
    }
    
    private static void b(final View view, final AppLovinAdSize appLovinAdSize) {
        if (view == null) {
            return;
        }
        final DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
        int widthPixels;
        if (appLovinAdSize.getLabel().equals(AppLovinAdSize.INTERSTITIAL.getLabel())) {
            widthPixels = -1;
        }
        else if (appLovinAdSize.getWidth() == -1) {
            widthPixels = displayMetrics.widthPixels;
        }
        else {
            widthPixels = (int)TypedValue.applyDimension(1, (float)appLovinAdSize.getWidth(), displayMetrics);
        }
        int heightPixels;
        if (appLovinAdSize.getLabel().equals(AppLovinAdSize.INTERSTITIAL.getLabel())) {
            heightPixels = -1;
        }
        else if (appLovinAdSize.getHeight() == -1) {
            heightPixels = displayMetrics.heightPixels;
        }
        else {
            heightPixels = (int)TypedValue.applyDimension(1, (float)appLovinAdSize.getHeight(), displayMetrics);
        }
        RelativeLayout$LayoutParams layoutParams;
        if ((layoutParams = (RelativeLayout$LayoutParams)view.getLayoutParams()) == null) {
            layoutParams = new RelativeLayout$LayoutParams(-2, -2);
        }
        layoutParams.width = widthPixels;
        layoutParams.height = heightPixels;
        if (layoutParams instanceof RelativeLayout$LayoutParams) {
            layoutParams.addRule(10);
            layoutParams.addRule(9);
        }
        view.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
    
    private void c() {
        this.a(new a(this));
    }
    
    private void d() {
        this.a(new com.applovin.impl.adview.g(this));
    }
    
    private void e() {
        if (this.i != null) {
            this.i.c();
            this.i = null;
        }
    }
    
    void a() {
        if (this.s != null || this.t != null) {
            if (new ee(this.c).ao()) {
                this.contractAd();
            }
            return;
        }
        this.e.d("AppLovinAdView", "Ad: " + this.p + " with placement = \"" + this.h + "\" closed.");
        this.a(this.o);
        bv.b(this.A, this.p, this.c);
        this.p = null;
        this.h = null;
    }
    
    void a(final int n) {
        if (!this.x) {
            this.d.addAdUpdateListener(this.k, this.f);
            this.a(this.o);
        }
        this.a(new f(this, n));
    }
    
    void a(final AppLovinAd appLovinAd) {
        if (appLovinAd != null) {
            this.y = true;
            if (!this.x) {
                this.d.addAdUpdateListener(this.k, this.f);
                this.renderAd(appLovinAd);
            }
            else {
                this.u.set(appLovinAd);
                this.e.d("AppLovinAdView", "Ad view has paused when an ad was received, ad saved for later");
            }
            this.a(new e(this, appLovinAd));
            return;
        }
        this.e.e("AppLovinAdView", "No provided when to the view controller");
        this.a(-1);
    }
    
    void a(final AppLovinAd appLovinAd, final String s, final AppLovinAdView appLovinAdView, final Uri uri) {
        bv.a(this.C, appLovinAd, this.c);
        if (appLovinAdView == null) {
            this.e.e("AppLovinAdView", "Unable to process ad click - AppLovinAdView destroyed prematurely");
            return;
        }
        if (new ee(this.c).z() && uri != null) {
            this.a(appLovinAd, appLovinAdView, uri);
            return;
        }
        this.d.trackAndLaunchClick(appLovinAd, s, appLovinAdView, this, uri);
    }
    
    @Override
    public void contractAd() {
        this.a(new d(this));
    }
    
    @Override
    public void destroy() {
        if (this.l != null && this.s != null) {
            this.contractAd();
        }
        this.b();
    }
    
    public void dismissInterstitialIfRequired() {
        if (this.a instanceof AppLovinInterstitialActivity && this.p instanceof an) {
            boolean b;
            if (((an)this.p).N() == ao.b) {
                b = true;
            }
            else {
                b = false;
            }
            final AppLovinInterstitialActivity appLovinInterstitialActivity = (AppLovinInterstitialActivity)this.a;
            if (b && appLovinInterstitialActivity.getPoststitialWasDisplayed()) {
                appLovinInterstitialActivity.dismiss();
            }
        }
    }
    
    @Override
    public void expandAd() {
        this.a(new b(this));
    }
    
    public AppLovinAdViewEventListener getAdViewEventListener() {
        return this.B;
    }
    
    public n getAdWebView() {
        return this.l;
    }
    
    public AppLovinAd getCurrentAd() {
        return this.p;
    }
    
    public AppLovinAdView getParentView() {
        return (AppLovinAdView)this.b;
    }
    
    public AppLovinSdk getSdk() {
        return this.c;
    }
    
    @Override
    public AppLovinAdSize getSize() {
        return this.f;
    }
    
    @Override
    public String getZoneId() {
        return this.g;
    }
    
    @Override
    public void initializeAdView(final AppLovinAdView appLovinAdView, final Context context, AppLovinAdSize appLovinAdSize, final String s, AppLovinSdk instance, final AttributeSet set) {
        if (appLovinAdView == null) {
            throw new IllegalArgumentException("No parent view specified");
        }
        if (context == null) {
            Log.e("AppLovinSdk", "Unable to build AppLovinAdView: no context provided. Please use a different constructor for this view.");
        }
        else {
            if (appLovinAdSize == null) {
                if ((appLovinAdSize = com.applovin.impl.adview.l.a(set)) == null) {
                    appLovinAdSize = AppLovinAdSize.BANNER;
                }
            }
            if (instance == null) {
                instance = AppLovinSdk.getInstance(context);
            }
            if (instance != null && !instance.hasCriticalErrors()) {
                this.a(appLovinAdView, instance, appLovinAdSize, s, context);
                if (com.applovin.impl.adview.l.b(set)) {
                    this.loadNextAd();
                }
            }
        }
    }
    
    @Override
    public boolean isAdReadyToDisplay() {
        if (!TextUtils.isEmpty((CharSequence)this.g)) {
            return this.d.hasPreloadedAdForZoneId(this.g);
        }
        return this.d.hasPreloadedAd(this.f);
    }
    
    @Override
    public boolean isAutoDestroy() {
        return this.w;
    }
    
    public boolean isForegroundClickInvalidated() {
        return this.D;
    }
    
    @Override
    public void loadNextAd() {
        if (this.c == null || this.k == null || this.a == null || !this.v) {
            Log.i("AppLovinSdk", "Unable to load next ad: AppLovinAdView is not initialized.");
            return;
        }
        if (!TextUtils.isEmpty((CharSequence)this.g)) {
            this.d.loadNextAdForZoneId(this.g, this.k);
            return;
        }
        this.d.loadNextAd(this.f, this.k);
    }
    
    public void onAdHtmlLoaded(final WebView webView) {
        if (!(this.p instanceof an)) {
            return;
        }
        webView.setVisibility(0);
        try {
            if (this.p != this.q && this.A != null) {
                this.q = this.p;
                bv.a(this.A, this.p, this.c);
            }
        }
        catch (Throwable t) {
            this.e.userError("AppLovinAdView", "Exception while notifying ad display listener", t);
        }
    }
    
    @Override
    public void onDetachedFromWindow() {
        if (this.v) {
            if (this.p != this.m) {
                bv.b(this.A, this.p, this.c);
            }
            if (this.l != null && this.s != null) {
                this.e.d("AppLovinAdView", "onDetachedFromWindowCalled with expanded ad present");
                if (new ee(this.c).am()) {
                    this.contractAd();
                }
                else {
                    this.c();
                }
            }
            else {
                this.e.d("AppLovinAdView", "onDetachedFromWindowCalled without an expanded ad present");
            }
            if (this.w) {
                this.b();
            }
        }
    }
    
    @Override
    public void onVisibilityChanged(final int n) {
        if (this.v && this.w) {
            if (n == 8 || n == 4) {
                this.pause();
                return;
            }
            if (n == 0) {
                this.resume();
            }
        }
    }
    
    @Override
    public void pause() {
        if (!this.v) {
            return;
        }
        if (new ee(this.c).an()) {
            this.d.removeAdUpdateListener(this.k, this.getSize());
        }
        final AppLovinAd p = this.p;
        this.renderAd(this.m, this.h);
        if (p != null) {
            this.u.set(p);
        }
        this.x = true;
    }
    
    public void removeClickTrackingOverlay() {
        if (this.r != null) {
            final ViewParent parent = this.r.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ((ViewGroup)parent).removeView((View)this.r);
                this.r = null;
            }
            return;
        }
        this.e.d("AppLovinAdView", "Asked to remove an overlay when none existed. Skipping...");
    }
    
    @Override
    public void renderAd(final AppLovinAd appLovinAd) {
        this.renderAd(appLovinAd, null);
    }
    
    @Override
    public void renderAd(AppLovinAd a, final String h) {
        if (a == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        if (!this.v) {
            Log.i("AppLovinSdk", "Unable to render ad: AppLovinAdView is not initialized.");
            return;
        }
        a = gd.a(a, this.c);
        if (a != null && a != this.p) {
            this.e.d("AppLovinAdView", "Rendering ad #" + a.getAdIdNumber() + " (" + a.getSize() + ") over placement: " + h);
            if (!(this.p instanceof com.applovin.impl.sdk.ar)) {
                bv.b(this.A, this.p, this.c);
                if (!(a instanceof com.applovin.impl.sdk.ar) && a.getSize() != AppLovinAdSize.INTERSTITIAL) {
                    this.e();
                }
            }
            this.u.set(null);
            this.q = null;
            this.p = a;
            this.h = h;
            if (a.getSize() == this.f) {
                if (!(a instanceof com.applovin.impl.sdk.ar) && this.s != null) {
                    if (new ee(this.c).al()) {
                        this.d();
                        this.e.d("AppLovinAdView", "Fade out the old ad scheduled");
                    }
                    else {
                        this.c();
                    }
                }
                if (!(a instanceof com.applovin.impl.sdk.ar) || (this.s == null && this.t == null)) {
                    this.a(this.n);
                    return;
                }
                this.e.d("AppLovinAdView", "ignoring empty ad render with expanded ad");
            }
            return;
        }
        if (a == null) {
            this.e.w("AppLovinAdView", "Unable to render ad: " + a + ". Internal inconsistency error.");
            return;
        }
        this.e.w("AppLovinAdView", "Ad #" + a.getAdIdNumber() + " is already showing, ignoring");
    }
    
    @Override
    public void resume() {
        if (!this.v) {
            return;
        }
        if (this.y && new ee(this.c).an()) {
            this.d.addAdUpdateListener(this.k, this.f);
        }
        final AppLovinAd appLovinAd = this.u.getAndSet(null);
        if (appLovinAd != null) {
            this.renderAd(appLovinAd, this.h);
        }
        this.x = false;
    }
    
    @Override
    public void setAdClickListener(final AppLovinAdClickListener c) {
        this.C = c;
    }
    
    @Override
    public void setAdDisplayListener(final AppLovinAdDisplayListener a) {
        this.A = a;
    }
    
    @Override
    public void setAdLoadListener(final AppLovinAdLoadListener z) {
        this.z = z;
    }
    
    @Override
    public void setAdVideoPlaybackListener(final AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
    }
    
    @Override
    public void setAdViewEventListener(final AppLovinAdViewEventListener b) {
        this.B = b;
    }
    
    @Override
    public void setAutoDestroy(final boolean w) {
        this.w = w;
    }
    
    public void setIsForegroundClickInvalidated(final boolean d) {
        this.D = d;
    }
    
    public void setStatsManagerHelper(final g g) {
        this.l.a(g);
    }
}
