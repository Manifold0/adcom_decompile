// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import com.applovin.impl.sdk.gd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.impl.sdk.ck;
import com.applovin.impl.a.r;
import android.os.Handler;
import com.applovin.impl.sdk.ee;
import com.applovin.impl.sdk.ab;
import com.applovin.impl.a.a;
import com.applovin.sdk.AppLovinAd;
import android.app.Activity;
import android.content.Intent;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.UUID;
import com.applovin.sdk.AppLovinSdk;
import java.util.Collections;
import java.util.HashMap;
import com.applovin.impl.sdk.ap;
import com.applovin.impl.sdk.an;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.impl.sdk.h;
import android.content.Context;
import java.lang.ref.WeakReference;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import java.util.Map;
import com.applovin.adview.AppLovinInterstitialAdDialog;

class cb implements AppLovinInterstitialAdDialog
{
    public static volatile boolean d;
    public static volatile boolean e;
    private static final Map<String, cb> f;
    private static volatile boolean p;
    protected final String a;
    protected final AppLovinSdkImpl b;
    protected final WeakReference<Context> c;
    private final h g;
    private volatile AppLovinAdLoadListener h;
    private volatile AppLovinAdDisplayListener i;
    private volatile AppLovinAdVideoPlaybackListener j;
    private volatile AppLovinAdClickListener k;
    private volatile an l;
    private volatile ap m;
    private volatile aq n;
    private volatile String o;
    
    static {
        f = Collections.synchronizedMap(new HashMap<String, cb>());
        cb.d = false;
        cb.e = false;
    }
    
    cb(final AppLovinSdk appLovinSdk, final Context context) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        if (context == null) {
            throw new IllegalArgumentException("No context specified");
        }
        this.b = (AppLovinSdkImpl)appLovinSdk;
        this.a = UUID.randomUUID().toString();
        this.g = new h();
        this.c = new WeakReference<Context>(context);
        cb.d = true;
        cb.e = false;
    }
    
    public static cb a(final String s) {
        return cb.f.get(s);
    }
    
    private void a(final int n) {
        AppLovinSdkUtils.runOnUiThread(new cf(this, n));
    }
    
    private void a(final Context context) {
        final Intent intent = new Intent(context, (Class)AppLovinInterstitialActivity.class);
        intent.putExtra("com.applovin.interstitial.wrapper_id", this.a);
        AppLovinInterstitialActivity.lastKnownWrapper = this;
        while (true) {
            Label_0074: {
                if (!(context instanceof Activity)) {
                    break Label_0074;
                }
                try {
                    context.startActivity(intent);
                    ((Activity)context).overridePendingTransition(0, 0);
                    this.a(true);
                    return;
                }
                catch (Throwable t) {
                    this.b.getLogger().e("InterstitialAdDialogWrapper", "Unable to remove pending transition animations", t);
                    continue;
                }
            }
            intent.setFlags(268435456);
            context.startActivity(intent);
            continue;
        }
    }
    
    private void a(final an l, final String o, final Context context) {
        cb.f.put(this.a, this);
        this.l = l;
        this.o = o;
        ap m;
        if (this.l != null) {
            m = this.l.v();
        }
        else {
            m = ap.a;
        }
        this.m = m;
        if (!this.l.b() && this.l.d() != null && !this.b.getFileManager().a(this.l.d().getLastPathSegment(), context)) {
            if (!(this.l instanceof a)) {
                this.b.getLogger().e("InterstitialAdDialogWrapper", "Cached video removed from local filesystem for internal ad. Failing ad show.");
                this.a(l);
                return;
            }
            final r c = ((a)this.l).c();
            if (c == null) {
                this.b.getLogger().e("InterstitialAdDialogWrapper", "Cached video removed from local filesystem for VAST ad and source uri not found. Failing ad show.");
                this.a(l);
                return;
            }
            this.b.getLogger().e("InterstitialAdDialogWrapper", "Cached video removed from local filesystem for VAST ad. Setting videoUri to source: " + c.a());
            c.a(c.a());
        }
        if (ab.a(AppLovinInterstitialActivity.class, context)) {
            final long max = Math.max(0L, new ee(this.b).K());
            this.b.getLogger().d("InterstitialAdDialogWrapper", "Presenting ad with delay of " + max);
            new Handler(context.getMainLooper()).postDelayed((Runnable)new cd(this, context), max);
            return;
        }
        this.b.getLogger().userError("AppLovinInterstitialAdDialog", "Unable to launch ad. ");
        this.a(l);
    }
    
    private void a(final ck ck, final String s, final Activity activity) {
        this.b.getMediationService().showAd(ck, s, activity, this.g);
    }
    
    private void a(final AppLovinAd appLovinAd) {
        if (this.i != null) {
            this.i.adHidden(appLovinAd);
        }
        cb.p = false;
    }
    
    private void b(final AppLovinAd appLovinAd) {
        AppLovinSdkUtils.runOnUiThread(new ce(this, appLovinAd));
    }
    
    private Context i() {
        if (this.c != null) {
            return this.c.get();
        }
        return null;
    }
    
    public AppLovinSdk a() {
        return this.b;
    }
    
    public void a(final aq n) {
        this.n = n;
    }
    
    protected void a(final AppLovinAdLoadListener appLovinAdLoadListener) {
        this.b.getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, appLovinAdLoadListener);
    }
    
    public void a(final boolean p) {
        cb.p = p;
    }
    
    public AppLovinAd b() {
        return this.l;
    }
    
    public AppLovinAdVideoPlaybackListener c() {
        return this.j;
    }
    
    public AppLovinAdDisplayListener d() {
        return this.i;
    }
    
    @Override
    public void dismiss() {
        AppLovinSdkUtils.runOnUiThread(new cg(this));
    }
    
    public AppLovinAdClickListener e() {
        return this.k;
    }
    
    public ap f() {
        return this.m;
    }
    
    public String g() {
        return this.o;
    }
    
    public void h() {
        cb.d = false;
        cb.e = true;
        cb.f.remove(this.a);
    }
    
    @Override
    public boolean isAdReadyToDisplay() {
        return this.b.getAdService().hasPreloadedAd(AppLovinAdSize.INTERSTITIAL);
    }
    
    @Override
    public boolean isShowing() {
        return cb.p;
    }
    
    @Override
    public void setAdClickListener(final AppLovinAdClickListener k) {
        this.k = k;
        this.g.a(k);
    }
    
    @Override
    public void setAdDisplayListener(final AppLovinAdDisplayListener i) {
        this.i = i;
        this.g.a(i);
    }
    
    @Override
    public void setAdLoadListener(final AppLovinAdLoadListener h) {
        this.h = h;
    }
    
    @Override
    public void setAdVideoPlaybackListener(final AppLovinAdVideoPlaybackListener j) {
        this.j = j;
    }
    
    @Override
    public void show() {
        this.show(null);
    }
    
    @Override
    public void show(final String s) {
        this.a(new cc(this, s));
    }
    
    @Override
    public void showAndRender(final AppLovinAd appLovinAd) {
        this.showAndRender(appLovinAd, null);
    }
    
    @Override
    public void showAndRender(final AppLovinAd appLovinAd, final String s) {
        final ee ee = new ee(this.b);
        if (this.isShowing() && !ee.ai()) {
            this.b.getLogger().userError("AppLovinInterstitialAdDialog", "Attempted to show an interstitial while one is already displayed; ignoring.");
            return;
        }
        if (!gd.a(appLovinAd, this.b)) {
            this.a(appLovinAd);
            return;
        }
        final Context i = this.i();
        if (i == null) {
            this.b.getLogger().e("InterstitialAdDialogWrapper", "Failed to show interstitial: stale activity reference provided");
            this.a(appLovinAd);
            return;
        }
        final AppLovinAd a = gd.a(appLovinAd, (AppLovinSdk)this.b);
        if (a == null) {
            this.b.getLogger().e("InterstitialAdDialogWrapper", "Failed to show ad: " + appLovinAd);
            this.a(appLovinAd);
            return;
        }
        if (a instanceof an) {
            this.a((an)a, s, i);
            return;
        }
        if (!(a instanceof ck)) {
            this.b.getLogger().e("InterstitialAdDialogWrapper", "Failed to show interstitial: unknown ad type provided: '" + a + "'");
            this.a(a);
            return;
        }
        if (i instanceof Activity) {
            this.a((ck)a, s, (Activity)i);
            return;
        }
        this.b.getLogger().userError("InterstitialAdDialogWrapper", "Failed to show interstitial: Activity required.");
        this.a(a);
    }
}
