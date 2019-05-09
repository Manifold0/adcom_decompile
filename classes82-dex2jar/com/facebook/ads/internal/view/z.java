// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import com.facebook.ads.internal.view.i.b.q;
import com.facebook.ads.internal.view.i.b.h;
import com.facebook.ads.internal.w.b.x;
import android.os.Bundle;
import android.content.Intent;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.content.Context;
import com.facebook.ads.internal.view.i.b.e;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.view.i.b.f;
import com.facebook.ads.internal.view.i.b.d;
import com.facebook.ads.internal.view.i.b.j;
import com.facebook.ads.internal.view.i.b.l;

public class z implements a
{
    private final l a;
    private final j b;
    private final d c;
    private final f d;
    private final AudienceNetworkActivity e;
    private final c f;
    private final com.facebook.ads.internal.view.i.a g;
    private final a h;
    private b i;
    private int j;
    
    public z(final AudienceNetworkActivity e, final c f, final a h) {
        this.a = new l() {
            @Override
            public void a(final k k) {
                z.this.h.a("videoInterstitalEvent", k);
            }
        };
        this.b = new j() {
            @Override
            public void a(final i i) {
                z.this.h.a("videoInterstitalEvent", i);
            }
        };
        this.c = new d() {
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.c c) {
                z.this.h.a("videoInterstitalEvent", c);
            }
        };
        this.d = new f() {
            @Override
            public void a(final e e) {
                z.this.e.finish();
            }
        };
        this.e = e;
        this.f = f;
        (this.g = new com.facebook.ads.internal.view.i.a((Context)e)).a(new com.facebook.ads.internal.view.i.c.b((Context)e));
        this.g.getEventBus().a(this.a, this.b, this.c, this.d);
        this.h = h;
        this.g.setIsFullScreen(true);
        this.g.setVolume(1.0f);
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -2);
        layoutParams.addRule(15);
        this.g.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        h.a((View)this.g);
        final g g = new g((Context)e);
        g.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                e.finish();
            }
        });
        h.a(g);
    }
    
    public void a(final int videoProgressReportIntervalMs) {
        this.g.setVideoProgressReportIntervalMs(videoProgressReportIntervalMs);
    }
    
    @Override
    public void a(final Intent intent, final Bundle bundle, final AudienceNetworkActivity audienceNetworkActivity) {
        final String stringExtra = intent.getStringExtra("useNativeCtaButton");
        if (stringExtra != null && !stringExtra.isEmpty()) {
            final com.facebook.ads.internal.view.e.b b = new com.facebook.ads.internal.view.e.b((Context)audienceNetworkActivity, stringExtra);
            final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-2, -2);
            final int n = (int)(x.b * 16.0f);
            layoutParams.setMargins(n, n, n, n);
            layoutParams.addRule(10);
            layoutParams.addRule(9);
            b.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            b.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    z.this.h.a("performCtaClick");
                }
            });
            this.h.a((View)b);
        }
        this.j = intent.getIntExtra("videoSeekTime", 0);
        this.i = new b((Context)audienceNetworkActivity, this.f, this.g, intent.getStringExtra("clientToken"), intent.getBundleExtra("videoLogger"));
        this.g.setVideoMPD(intent.getStringExtra("videoMPD"));
        this.g.setVideoURI(intent.getStringExtra("videoURL"));
        if (this.j > 0) {
            this.g.a(this.j);
        }
        if (intent.getBooleanExtra("autoplay", false)) {
            this.g.a(com.facebook.ads.internal.view.i.a.a.b);
        }
    }
    
    @Override
    public void a(final Bundle bundle) {
    }
    
    public void a(final View controlsAnchorView) {
        this.g.setControlsAnchorView(controlsAnchorView);
    }
    
    @Override
    public void a_(final boolean b) {
        this.h.a("videoInterstitalEvent", new com.facebook.ads.internal.view.i.b.g());
        this.g.e();
    }
    
    @Override
    public void b(final boolean b) {
        this.h.a("videoInterstitalEvent", new h());
        this.g.a(com.facebook.ads.internal.view.i.a.a.b);
    }
    
    @Override
    public void onDestroy() {
        this.h.a("videoInterstitalEvent", new q(this.j, this.g.getCurrentPositionInMillis()));
        this.i.b(this.g.getCurrentPositionInMillis());
        this.g.g();
        this.g.l();
    }
    
    @Override
    public void setListener(final a a) {
    }
}
