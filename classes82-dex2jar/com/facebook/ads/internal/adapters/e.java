// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.protocol.AdPlacementType;
import android.view.View;
import java.lang.ref.WeakReference;
import com.facebook.ads.internal.a.b;
import android.util.Log;
import android.net.Uri;
import com.facebook.ads.AdError;
import org.json.JSONObject;
import com.facebook.ads.internal.protocol.d;
import android.content.Context;
import com.facebook.ads.internal.s.c;
import java.util.Map;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.view.c.a;

public class e implements AdAdapter
{
    private static final String a;
    @Nullable
    private a.b b;
    @Nullable
    private a c;
    private m d;
    private BannerAdapterListener e;
    private Map<String, Object> f;
    @Nullable
    private c g;
    private String h;
    private Context i;
    
    static {
        a = e.class.getSimpleName();
    }
    
    public void a(final Context i, final c g, final d d, final BannerAdapterListener e, final Map<String, Object> f) {
        this.i = i;
        this.g = g;
        this.e = e;
        this.f = f;
        final com.facebook.ads.internal.m.d d2 = this.f.get("definition");
        final l a = l.a(this.f.get("data"));
        this.h = a.getClientToken();
        if (e.a(this.i, (com.facebook.ads.internal.a.e.a)a, this.g)) {
            this.e.onBannerError(this, AdError.internalError(2006));
        }
        else {
            this.b = new a.c() {
                @Override
                public void a() {
                    e.this.d.b();
                }
                
                @Override
                public void a(final String s, final Map<String, String> map) {
                    final Uri parse = Uri.parse(s);
                    if ("fbad".equals(parse.getScheme()) && com.facebook.ads.internal.a.c.a(parse.getAuthority()) && e.this.e != null) {
                        e.this.e.onBannerAdClicked(e.this);
                    }
                    final com.facebook.ads.internal.a.b a = com.facebook.ads.internal.a.c.a(e.this.i, e.this.g, a.getClientToken(), parse, map);
                    if (a == null) {
                        return;
                    }
                    try {
                        a.a();
                    }
                    catch (Exception ex) {
                        Log.e(e.a, "Error executing action", (Throwable)ex);
                    }
                }
                
                @Override
                public void b() {
                    if (e.this.d != null) {
                        e.this.d.a();
                    }
                }
            };
            (this.c = new a(this.i, new WeakReference<a.b>(this.b), d2.f())).a(d2.h(), d2.i());
            (this.d = new m(this.i, this.g, this.c, this.c.getViewabilityChecker(), new com.facebook.ads.internal.adapters.c() {
                @Override
                public void a() {
                    if (com.facebook.ads.internal.adapters.e.this.e != null) {
                        com.facebook.ads.internal.adapters.e.this.e.onBannerLoggingImpression(com.facebook.ads.internal.adapters.e.this);
                    }
                }
            })).a(a);
            this.c.loadDataWithBaseURL(com.facebook.ads.internal.w.e.b.a(), a.c(), "text/html", "utf-8", (String)null);
            if (this.e != null) {
                this.e.onBannerAdLoaded(this, (View)this.c);
            }
        }
    }
    
    @Override
    public String getClientToken() {
        return this.h;
    }
    
    @Override
    public final AdPlacementType getPlacementType() {
        return AdPlacementType.BANNER;
    }
    
    @Override
    public void onDestroy() {
        if (this.c != null) {
            this.c.destroy();
            this.c = null;
            this.b = null;
        }
    }
}
