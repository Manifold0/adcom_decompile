// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.webkit.WebView;
import android.os.Bundle;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.view.View;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.view.b.f;
import com.facebook.ads.AudienceNetworkActivity;
import android.annotation.TargetApi;

@TargetApi(19)
public class b implements a
{
    private static final String a;
    private final AudienceNetworkActivity b;
    private final com.facebook.ads.internal.view.b.a c;
    private final f d;
    private final com.facebook.ads.internal.view.b.b e;
    private final c f;
    private final AudienceNetworkActivity.BackButtonInterceptor g;
    private String h;
    private String i;
    private long j;
    private boolean k;
    private long l;
    private boolean m;
    
    static {
        a = b.class.getSimpleName();
    }
    
    public b(final AudienceNetworkActivity b, final c f, final a a) {
        this.g = new AudienceNetworkActivity.BackButtonInterceptor() {
            @Override
            public boolean interceptBackButton() {
                if (b.this.d.canGoBack()) {
                    b.this.d.goBack();
                    return true;
                }
                return false;
            }
        };
        this.k = true;
        this.l = -1L;
        this.m = true;
        this.b = b;
        this.f = f;
        final int n = (int)(2.0f * x.b);
        (this.c = new com.facebook.ads.internal.view.b.a((Context)b)).setId(View.generateViewId());
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -2);
        layoutParams.addRule(10);
        this.c.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.c.setListener((a.a)new a.a() {
            @Override
            public void a() {
                b.finish();
            }
        });
        a.a((View)this.c);
        this.d = new f((Context)b);
        final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams(-1, -2);
        layoutParams2.addRule(3, this.c.getId());
        layoutParams2.addRule(12);
        this.d.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
        this.d.setListener((f.a)new f.a() {
            @Override
            public void a(final int progress) {
                if (b.this.k) {
                    b.this.e.setProgress(progress);
                }
            }
            
            @Override
            public void a(final String url) {
                b.this.k = true;
                b.this.c.setUrl(url);
            }
            
            @Override
            public void b(final String title) {
                b.this.c.setTitle(title);
            }
            
            @Override
            public void c(final String s) {
                b.this.e.setProgress(100);
                b.this.k = false;
            }
        });
        a.a((View)this.d);
        this.e = new com.facebook.ads.internal.view.b.b((Context)b, null, 16842872);
        final RelativeLayout$LayoutParams layoutParams3 = new RelativeLayout$LayoutParams(-1, n);
        layoutParams3.addRule(3, this.c.getId());
        this.e.setLayoutParams((ViewGroup$LayoutParams)layoutParams3);
        this.e.setProgress(0);
        a.a((View)this.e);
        b.addBackButtonInterceptor(this.g);
    }
    
    @Override
    public void a(final Intent intent, final Bundle bundle, final AudienceNetworkActivity audienceNetworkActivity) {
        if (this.l < 0L) {
            this.l = System.currentTimeMillis();
        }
        if (bundle == null) {
            this.h = intent.getStringExtra("browserURL");
            this.i = intent.getStringExtra("clientToken");
            this.j = intent.getLongExtra("handlerTime", -1L);
        }
        else {
            this.h = bundle.getString("browserURL");
            this.i = bundle.getString("clientToken");
            this.j = bundle.getLong("handlerTime", -1L);
        }
        String h;
        if (this.h != null) {
            h = this.h;
        }
        else {
            h = "about:blank";
        }
        this.c.setUrl(h);
        this.d.loadUrl(h);
    }
    
    @Override
    public void a(final Bundle bundle) {
        bundle.putString("browserURL", this.h);
    }
    
    @Override
    public void a_(final boolean b) {
        this.d.onPause();
        if (this.m) {
            this.m = false;
            this.f.g(this.i, new com.facebook.ads.internal.view.b.c.a(this.d.getFirstUrl()).a(this.j).b(this.l).c(this.d.getResponseEndMs()).d(this.d.getDomContentLoadedMs()).e(this.d.getScrollReadyMs()).f(this.d.getLoadFinishMs()).g(System.currentTimeMillis()).a().a());
        }
    }
    
    @Override
    public void b(final boolean b) {
        this.d.onResume();
    }
    
    @Override
    public void onDestroy() {
        this.b.removeBackButtonInterceptor(this.g);
        com.facebook.ads.internal.w.e.b.a(this.d);
        this.d.destroy();
    }
    
    @Override
    public void setListener(final a a) {
    }
}
