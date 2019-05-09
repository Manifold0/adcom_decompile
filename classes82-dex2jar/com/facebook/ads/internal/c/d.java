// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.c;

import com.facebook.ads.internal.adapters.AdAdapter;
import android.view.View;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.protocol.AdErrorType;
import android.util.Log;
import android.support.annotation.Nullable;
import com.facebook.ads.CacheFlag;
import java.util.EnumSet;
import com.facebook.ads.internal.adapters.a;
import com.facebook.ads.internal.c.a.b;
import com.facebook.ads.InterstitialAdExtendedListener;
import com.facebook.ads.internal.b.f;

public class d implements c
{
    private static final String a;
    private f b;
    private boolean c;
    private boolean d;
    private final g e;
    private final InterstitialAdExtendedListener f;
    
    static {
        a = d.class.getSimpleName();
    }
    
    public d(final g e, final com.facebook.ads.internal.c.a.c c, final String s) {
        this.e = e;
        this.f = new b(s, c, this);
    }
    
    @Override
    public void a() {
        if (this.b != null) {
            this.b.a(new a() {});
            this.b.a(true);
            this.b = null;
            this.c = false;
            this.d = false;
        }
    }
    
    public void a(final EnumSet<CacheFlag> set, @Nullable final String s) {
        if (!this.c && this.b != null) {
            Log.w(com.facebook.ads.internal.c.d.a, "An ad load is already in progress. You should wait for adLoaded() to be called");
        }
        this.c = false;
        if (this.d) {
            com.facebook.ads.internal.w.h.a.b(this.e.a, "api", com.facebook.ads.internal.w.h.b.f, new com.facebook.ads.internal.protocol.b(AdErrorType.NO_ADAPTER_ON_LOAD, "Interstitial load called while showing interstitial."));
            this.f.onError(this.e.a(), new AdError(AdErrorType.LOAD_CALLED_WHILE_SHOWING_AD.getErrorCode(), AdErrorType.LOAD_CALLED_WHILE_SHOWING_AD.getDefaultErrorMessage()));
            return;
        }
        if (this.b != null) {
            this.b.a(new a() {});
            this.b.f();
            this.b = null;
        }
        final com.facebook.ads.internal.b.a a = new com.facebook.ads.internal.b.a(this.e.b, com.facebook.ads.internal.protocol.f.a(this.e.a.getResources().getDisplayMetrics()), AdPlacementType.INTERSTITIAL, com.facebook.ads.internal.protocol.d.b, 1, set);
        a.a(this.e.d);
        (this.b = new f(this.e.a, a)).a(new a() {
            @Override
            public void a() {
                com.facebook.ads.internal.c.d.this.f.onAdClicked(com.facebook.ads.internal.c.d.this.e.a());
            }
            
            @Override
            public void a(final View view) {
            }
            
            @Override
            public void a(final AdAdapter adAdapter) {
                com.facebook.ads.internal.c.d.this.c = true;
                com.facebook.ads.internal.c.d.this.f.onAdLoaded(com.facebook.ads.internal.c.d.this.e.a());
            }
            
            @Override
            public void a(final com.facebook.ads.internal.protocol.a a) {
                com.facebook.ads.internal.c.d.this.f.onError(com.facebook.ads.internal.c.d.this.e.a(), AdError.getAdErrorFromWrapper(a));
            }
            
            @Override
            public void b() {
                com.facebook.ads.internal.c.d.this.f.onLoggingImpression(com.facebook.ads.internal.c.d.this.e.a());
            }
            
            @Override
            public void d() {
                com.facebook.ads.internal.c.d.this.d = false;
                if (com.facebook.ads.internal.c.d.this.b != null) {
                    com.facebook.ads.internal.c.d.this.b.a(new a() {});
                    com.facebook.ads.internal.c.d.this.b.f();
                    com.facebook.ads.internal.c.d.this.b = null;
                }
                com.facebook.ads.internal.c.d.this.f.onInterstitialDismissed(com.facebook.ads.internal.c.d.this.e.a());
            }
            
            @Override
            public void e() {
                com.facebook.ads.internal.c.d.this.f.onInterstitialDisplayed(com.facebook.ads.internal.c.d.this.e.a());
            }
            
            @Override
            public void f() {
                com.facebook.ads.internal.c.d.this.d = false;
                com.facebook.ads.internal.c.d.this.f.onInterstitialActivityDestroyed();
            }
        });
        this.b.b(s);
    }
    
    public long b() {
        if (this.b != null) {
            return this.b.h();
        }
        return -1L;
    }
    
    public boolean c() {
        return this.b == null || this.b.g();
    }
    
    public boolean d() {
        return this.c;
    }
    
    public boolean e() {
        if (!this.c) {
            this.f.onError(this.e.a(), AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
            return false;
        }
        if (this.b == null) {
            com.facebook.ads.internal.w.h.a.b(this.e.a, "api", com.facebook.ads.internal.w.h.b.g, new com.facebook.ads.internal.protocol.b(AdErrorType.INTERSTITIAL_CONTROLLER_IS_NULL, AdErrorType.INTERSTITIAL_CONTROLLER_IS_NULL.getDefaultErrorMessage()));
            this.f.onError(this.e.a(), AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
            return false;
        }
        this.b.e();
        this.d = true;
        this.c = false;
        return true;
    }
}
