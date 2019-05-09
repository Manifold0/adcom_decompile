// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.e.a;

import android.support.v7.widget.SnapHelper;
import android.view.MotionEvent;
import android.content.res.Configuration;
import com.facebook.ads.internal.adapters.b.l;
import java.util.ArrayList;
import com.facebook.ads.AudienceNetworkActivity;
import android.content.Intent;
import com.facebook.ads.internal.adapters.b.h;
import android.widget.LinearLayout$LayoutParams;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView$Adapter;
import android.view.View;
import com.facebook.ads.internal.w.b.k;
import android.text.TextUtils;
import java.util.HashMap;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.os.Bundle;
import java.util.Map;
import com.facebook.ads.internal.s.c;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.view.d;
import java.util.List;
import android.widget.LinearLayout;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.h.b;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.view.o;

public class e extends o
{
    private static final int d;
    private static final int e;
    private static final int f;
    private static final int g;
    private static final int h;
    private final w i;
    @Nullable
    private b j;
    @Nullable
    private LinearLayout k;
    private String l;
    private List<com.facebook.ads.internal.view.e.a.b> m;
    private a n;
    @Nullable
    private com.facebook.ads.internal.view.component.e o;
    @Nullable
    private d p;
    private com.facebook.ads.internal.x.a q;
    private a.a r;
    private int s;
    private int t;
    
    static {
        d = (int)(48.0f * x.b);
        e = (int)(x.b * 8.0f);
        f = (int)(x.b * 8.0f);
        g = (int)(56.0f * x.b);
        h = (int)(12.0f * x.b);
    }
    
    public e(final Context context, final c c, @Nullable final b j, final a.a a) {
        super(context, c, a);
        this.i = new w();
        this.j = j;
    }
    
    public void a() {
        if (this.k != null) {
            this.k.removeAllViews();
            this.k = null;
        }
        if (this.p != null) {
            this.p.removeAllViews();
            this.p = null;
        }
        if (this.o != null) {
            this.o.removeAllViews();
            this.o = null;
        }
    }
    
    public void a(final int n, @Nullable final Bundle bundle) {
        this.k = new LinearLayout(this.getContext());
        if (n == 1) {
            this.k.setGravity(17);
        }
        else {
            this.k.setGravity(48);
        }
        this.k.setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
        this.k.setOrientation(1);
        final int widthPixels = x.a.widthPixels;
        final int heightPixels = x.a.heightPixels;
        int min;
        int e;
        int n2;
        if (n == 1) {
            min = Math.min(widthPixels - com.facebook.ads.internal.view.e.a.e.e * 4, heightPixels / 2);
            e = (widthPixels - min) / 8;
            n2 = e * 4;
        }
        else {
            min = heightPixels - (com.facebook.ads.internal.view.e.a.e.g + com.facebook.ads.internal.view.e.a.e.d + com.facebook.ads.internal.view.e.a.e.e * 2);
            e = com.facebook.ads.internal.view.e.a.e.e;
            n2 = e * 2;
        }
        this.r = new a.a() {
            @Override
            public void a() {
                final HashMap<String, String> hashMap = new HashMap<String, String>();
                if (!e.this.i.b()) {
                    e.this.i.a();
                    if (o.this.getAudienceNetworkListener() != null) {
                        o.this.getAudienceNetworkListener().a("com.facebook.ads.interstitial.impression.logged");
                    }
                    if (!TextUtils.isEmpty((CharSequence)e.this.l)) {
                        e.this.q.a(hashMap);
                        hashMap.put("touch", k.a(e.this.i.e()));
                        o.this.a(hashMap);
                        e.this.a.a(e.this.l, hashMap);
                    }
                }
            }
        };
        (this.q = new com.facebook.ads.internal.x.a((View)this, 1, this.r)).a(this.s);
        this.q.b(this.t);
        (this.p = new d(this.getContext())).setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -2));
        this.n = new a(this.p, n, this.m, this.q, bundle);
        final d p2 = this.p;
        final List<com.facebook.ads.internal.view.e.a.b> m = this.m;
        final c a = this.a;
        final b j = this.j;
        final com.facebook.ads.internal.x.a q = this.q;
        final w i = this.i;
        final a.a audienceNetworkListener = this.getAudienceNetworkListener();
        h h;
        if (n == 1) {
            h = this.c.a();
        }
        else {
            h = this.c.b();
        }
        p2.setAdapter((RecyclerView$Adapter)new com.facebook.ads.internal.view.e.a.c(m, a, j, q, i, audienceNetworkListener, h, this.l, min, e, n2, n, this.n));
        if (n == 1) {
            final a n3 = this.n;
            ((SnapHelper)new PagerSnapHelper()).attachToRecyclerView((RecyclerView)this.p);
            n3.a(new com.facebook.ads.internal.view.e.a.c.a() {
                @Override
                public void a(final int n) {
                    if (e.this.o != null) {
                        e.this.o.a(n);
                    }
                }
            });
            this.o = new com.facebook.ads.internal.view.component.e(this.getContext(), this.c.a(), this.m.size());
            final LinearLayout$LayoutParams layoutParams = new LinearLayout$LayoutParams(-1, com.facebook.ads.internal.view.e.a.e.f);
            layoutParams.setMargins(0, com.facebook.ads.internal.view.e.a.e.h, 0, 0);
            this.o.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        }
        this.k.addView((View)this.p);
        if (this.o != null) {
            this.k.addView((View)this.o);
        }
        this.a((View)this.k, false, n);
    }
    
    public void a(final Intent intent, final Bundle bundle, final AudienceNetworkActivity audienceNetworkActivity) {
        final com.facebook.ads.internal.adapters.b.k k = (com.facebook.ads.internal.adapters.b.k)intent.getSerializableExtra("ad_data_bundle");
        super.a(audienceNetworkActivity, k);
        this.l = k.c();
        this.s = k.f();
        this.t = k.g();
        final List<l> d = k.d();
        this.m = new ArrayList<com.facebook.ads.internal.view.e.a.b>(d.size());
        for (int i = 0; i < d.size(); ++i) {
            this.m.add(new com.facebook.ads.internal.view.e.a.b(i, d.size(), d.get(i)));
        }
        this.a(audienceNetworkActivity.getResources().getConfiguration().orientation, bundle);
    }
    
    public void a(final Bundle bundle) {
        if (this.n != null) {
            this.n.a(bundle);
        }
    }
    
    public void a_(final boolean b) {
        if (this.n != null) {
            this.n.a();
        }
    }
    
    public void b(final boolean b) {
        this.n.b();
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        final Bundle bundle = new Bundle();
        this.a(bundle);
        this.a();
        this.a(configuration.orientation, bundle);
        super.onConfigurationChanged(configuration);
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!TextUtils.isEmpty((CharSequence)this.l)) {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            this.q.a(hashMap);
            hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(this.i.e()));
            this.a.l(this.l, hashMap);
        }
        this.a();
        this.q.c();
        this.q = null;
        this.r = null;
        this.m = null;
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        this.i.a(motionEvent, (View)this, (View)this);
        return super.onInterceptTouchEvent(motionEvent);
    }
}
