// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.webkit.WebView;
import com.facebook.ads.internal.w.b.k;
import java.util.HashMap;
import android.text.TextUtils;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import java.lang.ref.WeakReference;
import com.facebook.ads.internal.a.b;
import android.util.Log;
import android.content.Context;
import android.net.Uri;
import java.util.Map;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.l;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.adapters.m;

public class h implements a
{
    private static final String a;
    private final a b;
    private final com.facebook.ads.internal.view.c.a c;
    private final com.facebook.ads.internal.view.c.a.b d;
    private final m e;
    private final c f;
    private l g;
    
    static {
        a = h.class.getSimpleName();
    }
    
    public h(final AudienceNetworkActivity audienceNetworkActivity, final c f, final a b) {
        this.b = b;
        this.f = f;
        this.d = new com.facebook.ads.internal.view.c.a.c() {
            private long d = 0L;
            
            @Override
            public void a() {
                h.this.e.b();
            }
            
            @Override
            public void a(final String s, final Map<String, String> map) {
                final Uri parse = Uri.parse(s);
                if ("fbad".equals(parse.getScheme()) && "close".equals(parse.getAuthority())) {
                    audienceNetworkActivity.finish();
                }
                else {
                    final long d = this.d;
                    this.d = System.currentTimeMillis();
                    if (this.d - d >= 1000L) {
                        if ("fbad".equals(parse.getScheme()) && com.facebook.ads.internal.a.c.a(parse.getAuthority())) {
                            h.this.b.a("com.facebook.ads.interstitial.clicked");
                        }
                        final com.facebook.ads.internal.a.b a = com.facebook.ads.internal.a.c.a((Context)audienceNetworkActivity, f, h.this.g.getClientToken(), parse, map);
                        if (a != null) {
                            try {
                                a.a();
                            }
                            catch (Exception ex) {
                                Log.e(h.a, "Error executing action", (Throwable)ex);
                            }
                        }
                    }
                }
            }
            
            @Override
            public void b() {
                h.this.e.a();
            }
        };
        (this.c = new com.facebook.ads.internal.view.c.a((Context)audienceNetworkActivity, new WeakReference<com.facebook.ads.internal.view.c.a.b>(this.d), 1)).setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
        this.e = new m((Context)audienceNetworkActivity, f, this.c, this.c.getViewabilityChecker(), new com.facebook.ads.internal.adapters.c() {
            @Override
            public void a() {
                h.this.b.a("com.facebook.ads.interstitial.impression.logged");
            }
        });
        b.a((View)this.c);
    }
    
    @Override
    public void a(final Intent intent, final Bundle bundle, final AudienceNetworkActivity audienceNetworkActivity) {
        if (bundle != null && bundle.containsKey("dataModel")) {
            this.g = l.a(bundle.getBundle("dataModel"));
            if (this.g != null) {
                this.c.loadDataWithBaseURL(com.facebook.ads.internal.w.e.b.a(), this.g.c(), "text/html", "utf-8", (String)null);
                this.c.a(this.g.f(), this.g.g());
            }
        }
        else {
            this.g = l.b(intent);
            if (this.g != null) {
                this.e.a(this.g);
                this.c.loadDataWithBaseURL(com.facebook.ads.internal.w.e.b.a(), this.g.c(), "text/html", "utf-8", (String)null);
                this.c.a(this.g.f(), this.g.g());
            }
        }
    }
    
    @Override
    public void a(final Bundle bundle) {
        if (this.g != null) {
            bundle.putBundle("dataModel", this.g.h());
        }
    }
    
    @Override
    public void a_(final boolean b) {
        this.c.onPause();
    }
    
    @Override
    public void b(final boolean b) {
        this.c.onResume();
    }
    
    @Override
    public void onDestroy() {
        if (this.g != null && !TextUtils.isEmpty((CharSequence)this.g.getClientToken())) {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            this.c.getViewabilityChecker().a(hashMap);
            hashMap.put("touch", k.a(this.c.getTouchData()));
            this.f.l(this.g.getClientToken(), hashMap);
        }
        com.facebook.ads.internal.w.e.b.a(this.c);
        this.c.destroy();
    }
    
    @Override
    public void setListener(final a a) {
    }
}
