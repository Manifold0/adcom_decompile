// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.b;

import com.facebook.ads.AdError;
import com.facebook.ads.internal.w.h.b;
import android.net.Uri;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.facebook.ads.internal.adapters.InterstitialAdapterListener;
import com.facebook.ads.internal.protocol.AdErrorType;
import java.util.Map;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.g;
import android.content.Context;

public class f extends c
{
    public f(final Context context, final com.facebook.ads.internal.b.a a) {
        super(context, a);
    }
    
    @Override
    protected void a() {
        ((g)this.f).a();
    }
    
    @Override
    protected void a(final AdAdapter adAdapter, final com.facebook.ads.internal.m.c c, final com.facebook.ads.internal.m.a a, final Map<String, Object> map) {
        final g g = (g)adAdapter;
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                f.this.a(g);
                if (com.facebook.ads.internal.r.a.ac(f.this.b)) {
                    f.this.e = null;
                    f.this.c.a(new com.facebook.ads.internal.protocol.a(AdErrorType.INTERSTITIAL_AD_TIMEOUT, ""));
                    return;
                }
                f.this.i();
            }
        };
        this.j().postDelayed((Runnable)runnable, (long)c.a().j());
        g.a(this.b, new InterstitialAdapterListener() {
            final /* synthetic */ Runnable a = runnable;
            
            @Override
            public void onInterstitialActivityDestroyed() {
                f.this.c.f();
            }
            
            @Override
            public void onInterstitialAdClicked(final g g, final String s, final boolean b) {
                f.this.c.a();
                boolean b2;
                if (!TextUtils.isEmpty((CharSequence)s)) {
                    b2 = true;
                }
                else {
                    b2 = false;
                }
                if (b && b2) {
                    final Intent intent = new Intent("android.intent.action.VIEW");
                    if (!(f.this.b instanceof Activity)) {
                        intent.addFlags(268435456);
                    }
                    intent.setData(Uri.parse(s));
                    f.this.b.startActivity(intent);
                }
            }
            
            @Override
            public void onInterstitialAdDismissed(final g g) {
                f.this.c.d();
            }
            
            @Override
            public void onInterstitialAdDisplayed(final g g) {
                f.this.c.e();
            }
            
            @Override
            public void onInterstitialAdLoaded(final g f) {
                if (f != f.this.e) {
                    return;
                }
                if (f == null) {
                    com.facebook.ads.internal.w.h.a.b(f.this.b, "api", com.facebook.ads.internal.w.h.b.b, new com.facebook.ads.internal.protocol.b(AdErrorType.NO_ADAPTER_ON_LOAD, "Adapter is null on loadInterstitialAd"));
                    this.onInterstitialError(f, AdError.internalError(2004));
                    return;
                }
                f.this.j().removeCallbacks(this.a);
                f.this.f = f;
                f.this.c.a(f);
            }
            
            @Override
            public void onInterstitialError(final g g, final AdError adError) {
                if (g != f.this.e) {
                    return;
                }
                f.this.j().removeCallbacks(this.a);
                f.this.a(g);
                if (!com.facebook.ads.internal.r.a.ac(f.this.b)) {
                    f.this.i();
                }
                f.this.c.a(new com.facebook.ads.internal.protocol.a(adError.getErrorCode(), adError.getErrorMessage()));
            }
            
            @Override
            public void onInterstitialLoggingImpression(final g g) {
                f.this.c.b();
            }
        }, map, this.g, this.h.d, this.h.e);
    }
}
