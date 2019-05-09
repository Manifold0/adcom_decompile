// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.b;

import com.facebook.ads.AdError;
import android.view.View;
import com.facebook.ads.internal.adapters.BannerAdapterListener;
import com.facebook.ads.internal.adapters.e;
import java.util.Map;
import com.facebook.ads.internal.adapters.AdAdapter;
import android.content.Context;

public class b extends c
{
    public b(final Context context, final com.facebook.ads.internal.b.a a) {
        super(context, a);
    }
    
    @Override
    protected void a() {
        if (this.d != null) {
            this.c.a(this.d);
        }
    }
    
    @Override
    protected void a(final AdAdapter adAdapter, final com.facebook.ads.internal.m.c c, final com.facebook.ads.internal.m.a a, final Map<String, Object> map) {
        final e e = (e)adAdapter;
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                com.facebook.ads.internal.b.b.this.a(e);
                com.facebook.ads.internal.b.b.this.i();
            }
        };
        this.j().postDelayed((Runnable)runnable, (long)c.a().j());
        e.a(this.b, this.g, this.h.c, new BannerAdapterListener() {
            @Override
            public void onBannerAdClicked(final e e) {
                com.facebook.ads.internal.b.b.this.c.a();
            }
            
            @Override
            public void onBannerAdLoaded(final e f, final View d) {
                if (f != com.facebook.ads.internal.b.b.this.e) {
                    return;
                }
                com.facebook.ads.internal.b.b.this.j().removeCallbacks(runnable);
                final AdAdapter f2 = com.facebook.ads.internal.b.b.this.f;
                com.facebook.ads.internal.b.b.this.f = f;
                com.facebook.ads.internal.b.b.this.d = d;
                if (!com.facebook.ads.internal.b.b.this.a) {
                    com.facebook.ads.internal.b.b.this.c.a(f);
                    return;
                }
                com.facebook.ads.internal.b.b.this.c.a(d);
                com.facebook.ads.internal.b.b.this.a(f2);
            }
            
            @Override
            public void onBannerError(final e e, final AdError adError) {
                if (e != com.facebook.ads.internal.b.b.this.e) {
                    return;
                }
                com.facebook.ads.internal.b.b.this.j().removeCallbacks(runnable);
                com.facebook.ads.internal.b.b.this.a(e);
                com.facebook.ads.internal.b.b.this.i();
            }
            
            @Override
            public void onBannerLoggingImpression(final e e) {
                com.facebook.ads.internal.b.b.this.c.b();
            }
        }, map);
    }
    
    @Override
    protected void a(final String s) {
        final com.facebook.ads.internal.protocol.a a = com.facebook.ads.internal.b.e.a(this.b, 0);
        if (a != null) {
            this.a(a);
            return;
        }
        super.a(s);
    }
}
