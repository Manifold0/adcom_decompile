// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.view.MotionEvent;
import android.text.TextUtils;
import android.content.res.Configuration;
import com.facebook.ads.AudienceNetworkActivity;
import android.os.Bundle;
import android.content.Intent;
import com.facebook.ads.internal.view.component.a.g;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.view.c.e;
import com.facebook.ads.internal.view.c.d;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.view.View;
import java.util.Map;
import java.util.HashMap;
import com.facebook.ads.internal.s.c;
import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.x.a;
import com.facebook.ads.internal.adapters.b.k;

public class l extends o
{
    private final k d;
    private final a e;
    private final w f;
    private final a.a g;
    @Nullable
    private com.facebook.ads.internal.view.component.a.l h;
    private boolean i;
    
    public l(final Context context, final k d, final c c, final a.a a) {
        super(context, c, a);
        this.f = new w();
        this.i = false;
        this.d = d;
        this.g = new a.a() {
            @Override
            public void a() {
                if (!l.this.f.b()) {
                    l.this.f.a();
                    final HashMap<String, String> hashMap = new HashMap<String, String>();
                    l.this.e.a(hashMap);
                    hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(l.this.f.e()));
                    l.this.a(hashMap);
                    l.this.a.a(l.this.d.c(), hashMap);
                    if (l.this.getAudienceNetworkListener() != null) {
                        l.this.getAudienceNetworkListener().a("com.facebook.ads.interstitial.impression.logged");
                    }
                }
            }
        };
        (this.e = new a((View)this, 100, this.g)).a(d.f());
    }
    
    private void setUpContent(final int n) {
        final com.facebook.ads.internal.adapters.b.l l = this.d.d().get(0);
        final ImageView imageView = new ImageView(this.getContext());
        imageView.setScaleType(ImageView$ScaleType.CENTER);
        imageView.setAdjustViewBounds(true);
        final d a = new d(imageView).a(l.c().i(), l.c().h());
        a.a(new e() {
            @Override
            public void a(final boolean b) {
                if (b) {
                    l.this.e.a();
                }
            }
        });
        a.a(l.c().g());
        final com.facebook.ads.internal.view.component.a.e a2 = new com.facebook.ads.internal.view.component.a.e.a(this.getContext(), this.a, this.getAudienceNetworkListener(), this.d, (View)imageView, this.e, this.f).a(com.facebook.ads.internal.view.i.a).b(n).a();
        final com.facebook.ads.internal.view.component.a.c a3 = com.facebook.ads.internal.view.component.a.d.a(a2);
        this.h = com.facebook.ads.internal.view.component.a.g.a(a2, x.a.heightPixels - a3.getExactMediaHeightIfAvailable(), x.a.widthPixels - a3.getExactMediaWidthIfAvailable(), this.i);
        u.a a4 = null;
        if (this.h != null) {
            a4 = new u.a() {
                @Override
                public void a() {
                    l.this.h.b();
                }
                
                @Override
                public void b() {
                    l.this.h.a();
                }
            };
        }
        this.a(a3, this.h, a4, a3.getExactMediaHeightIfAvailable(), x.a.widthPixels - a3.getExactMediaWidthIfAvailable(), a3.a(), n);
    }
    
    public void a(final Intent intent, final Bundle bundle, final AudienceNetworkActivity audienceNetworkActivity) {
        super.a(audienceNetworkActivity, this.d);
        audienceNetworkActivity.addBackButtonInterceptor((AudienceNetworkActivity.BackButtonInterceptor)new AudienceNetworkActivity.BackButtonInterceptor() {
            @Override
            public boolean interceptBackButton() {
                return l.this.h != null && l.this.h.c();
            }
        });
        this.setUpContent(audienceNetworkActivity.getResources().getConfiguration().orientation);
    }
    
    public void a(final Bundle bundle) {
    }
    
    public void a_(final boolean b) {
        if (this.h != null) {
            this.h.e();
        }
    }
    
    public void b(final boolean b) {
        if (this.h != null) {
            this.h.f();
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        this.removeAllViews();
        if (this.h != null) {
            x.b((View)this.h);
            this.i = this.h.d();
        }
        this.setUpContent(configuration.orientation);
        super.onConfigurationChanged(configuration);
    }
    
    @Override
    public void onDestroy() {
        if (this.d != null && !TextUtils.isEmpty((CharSequence)this.d.c())) {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            this.e.a(hashMap);
            hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(this.f.e()));
            this.a.l(this.d.c(), hashMap);
        }
        this.e.c();
        if (this.h != null) {
            this.h.g();
        }
        super.onDestroy();
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        this.f.a(motionEvent, (View)this, (View)this);
        return super.onInterceptTouchEvent(motionEvent);
    }
}
