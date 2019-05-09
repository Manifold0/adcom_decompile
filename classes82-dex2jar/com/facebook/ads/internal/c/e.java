// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.c;

import com.facebook.ads.internal.w.h.b;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.adapters.s;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.Ad;
import com.facebook.ads.internal.protocol.AdPlacementType;
import android.util.Log;
import com.facebook.ads.RewardData;
import com.facebook.ads.internal.adapters.a;
import com.facebook.ads.internal.c.a.d;
import com.facebook.ads.S2SRewardedVideoAdExtendedListener;
import com.facebook.ads.internal.b.h;

public class e implements c
{
    private static final String a;
    private final j b;
    private h c;
    private boolean d;
    private final S2SRewardedVideoAdExtendedListener e;
    
    static {
        a = e.class.getSimpleName();
    }
    
    public e(final j b, final com.facebook.ads.internal.c.a.c c, final String s) {
        this.d = false;
        this.b = b;
        this.e = new d(s, c, this, b);
    }
    
    private void a(final boolean b) {
        if (this.c != null) {
            this.c.a(new a() {});
            this.c.a(b);
            this.c = null;
        }
    }
    
    @Override
    public void a() {
        this.a(true);
    }
    
    public void a(final RewardData e) {
        this.b.e = e;
        if (this.d) {
            this.c.a(e);
        }
    }
    
    public void a(final String s, final boolean b) {
        try {
            if (!this.d && this.c != null) {
                Log.w(com.facebook.ads.internal.c.e.a, "An ad load is already in progress. You should wait for adLoaded() to be called");
            }
            this.a(false);
            this.d = false;
            final com.facebook.ads.internal.b.a a = new com.facebook.ads.internal.b.a(this.b.b, com.facebook.ads.internal.protocol.e.m, AdPlacementType.REWARDED_VIDEO, com.facebook.ads.internal.protocol.d.b, 1);
            a.a(b);
            a.a(this.b.d);
            (this.c = new h(this.b.a, a)).a(new a() {
                @Override
                public void a() {
                    com.facebook.ads.internal.c.e.this.e.onAdClicked(com.facebook.ads.internal.c.e.this.b.a());
                }
                
                @Override
                public void a(final AdAdapter adAdapter) {
                    final s s = (s)adAdapter;
                    if (com.facebook.ads.internal.c.e.this.b.e != null) {
                        s.a(com.facebook.ads.internal.c.e.this.b.e);
                    }
                    com.facebook.ads.internal.c.e.this.b.h = s.a();
                    com.facebook.ads.internal.c.e.this.d = true;
                    com.facebook.ads.internal.c.e.this.e.onAdLoaded(com.facebook.ads.internal.c.e.this.b.a());
                }
                
                @Override
                public void a(final com.facebook.ads.internal.protocol.a a) {
                    com.facebook.ads.internal.c.e.this.a(true);
                    com.facebook.ads.internal.c.e.this.e.onError(com.facebook.ads.internal.c.e.this.b.a(), AdError.getAdErrorFromWrapper(a));
                }
                
                @Override
                public void b() {
                    com.facebook.ads.internal.c.e.this.e.onLoggingImpression(com.facebook.ads.internal.c.e.this.b.a());
                }
                
                @Override
                public void g() {
                    com.facebook.ads.internal.c.e.this.e.onRewardedVideoCompleted();
                }
                
                @Override
                public void h() {
                    com.facebook.ads.internal.c.e.this.e.onRewardedVideoClosed();
                }
                
                @Override
                public void i() {
                    com.facebook.ads.internal.c.e.this.e.onRewardServerFailed();
                }
                
                @Override
                public void j() {
                    com.facebook.ads.internal.c.e.this.e.onRewardServerSuccess();
                }
                
                @Override
                public void k() {
                    com.facebook.ads.internal.c.e.this.e.onRewardedVideoActivityDestroyed();
                }
            });
            this.c.b(s);
        }
        catch (Exception ex) {
            Log.e(com.facebook.ads.internal.c.e.a, "Error loading rewarded video ad", (Throwable)ex);
            com.facebook.ads.internal.w.h.a.b(this.b.a, "api", b.i, ex);
            this.e.onError(this.b.a(), AdError.internalError(2004));
        }
    }
    
    public boolean a(final int n) {
        if (!this.d) {
            this.e.onError(this.b.a(), AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
            return false;
        }
        if (this.c != null) {
            this.c.h.a(n);
            this.c.e();
            this.d = false;
            return true;
        }
        return this.d = false;
    }
    
    public long b() {
        if (this.c != null) {
            return this.c.h();
        }
        return -1L;
    }
    
    public boolean c() {
        return this.c == null || this.c.g();
    }
    
    public boolean d() {
        return this.d;
    }
}
