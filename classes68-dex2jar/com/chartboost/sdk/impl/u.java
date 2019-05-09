// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.widget.ImageView$ScaleType;
import com.chartboost.sdk.Libraries.CBUtility;
import android.view.MotionEvent;
import android.view.View;
import com.chartboost.sdk.g;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.ImageView;
import android.content.Context;
import android.graphics.Point;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Libraries.CBLogging;
import org.json.JSONObject;
import android.view.ViewGroup$LayoutParams;
import android.os.Handler;
import com.chartboost.sdk.Model.c;
import com.chartboost.sdk.Libraries.h;
import com.chartboost.sdk.e;

public class u extends e
{
    h j;
    h k;
    h l;
    h m;
    h n;
    h o;
    protected float p;
    private final String q;
    
    public u(final c c, final Handler handler, final com.chartboost.sdk.c c2) {
        super(c, handler, c2);
        this.q = "ImageViewProtocol";
        this.p = 1.0f;
        this.j = new h(this);
        this.k = new h(this);
        this.l = new h(this);
        this.m = new h(this);
        this.n = new h(this);
        this.o = new h(this);
    }
    
    public void a(final ViewGroup$LayoutParams viewGroup$LayoutParams, final h h, final float n) {
        if (h != null && h.d()) {
            viewGroup$LayoutParams.width = (int)(h.a() / h.f() * n);
            viewGroup$LayoutParams.height = (int)(h.b() / h.f() * n);
        }
    }
    
    @Override
    public boolean a(final JSONObject jsonObject) {
        if (!super.a(jsonObject)) {
            return false;
        }
        if (this.d.isNull("frame-portrait") || this.d.isNull("close-portrait")) {
            this.h = false;
        }
        if (this.d.isNull("frame-landscape") || this.d.isNull("close-landscape")) {
            this.i = false;
        }
        if (this.d.isNull("ad-portrait")) {
            this.h = false;
        }
        if (this.d.isNull("ad-landscape")) {
            this.i = false;
        }
        if (!this.k.a("frame-landscape") || !this.j.a("frame-portrait") || !this.m.a("close-landscape") || !this.l.a("close-portrait") || !this.o.a("ad-landscape") || !this.n.a("ad-portrait")) {
            CBLogging.b("ImageViewProtocol", "Error while downloading the assets");
            this.a(CBError.CBImpressionError.ASSETS_DOWNLOAD_FAILURE);
            return false;
        }
        return true;
    }
    
    protected Point b(final String s) {
        final JSONObject a = com.chartboost.sdk.Libraries.e.a(this.d, new String[] { s, "offset" });
        if (a != null) {
            return new Point(a.optInt("x"), a.optInt("y"));
        }
        return new Point(0, 0);
    }
    
    @Override
    protected e.a b(final Context context) {
        return new a(context);
    }
    
    @Override
    public void d() {
        super.d();
        this.k.c();
        this.j.c();
        this.m.c();
        this.l.c();
        this.o.c();
        this.n.c();
        this.k = null;
        this.j = null;
        this.m = null;
        this.l = null;
        this.o = null;
        this.n = null;
    }
    
    public class a extends e.a
    {
        protected ay c;
        protected az d;
        protected az e;
        protected ImageView f;
        private boolean h;
        
        protected a(final Context context) {
            super(context);
            this.h = false;
            this.setBackgroundColor(0);
            this.setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
            final g a = com.chartboost.sdk.g.a();
            this.addView((View)(this.c = a.a(new ay(context))), (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
            ((e.a)this).a((View)(this.e = a.a(new az(context) {
                @Override
                protected void a(final MotionEvent motionEvent) {
                    u.a.this.a(motionEvent.getX(), motionEvent.getY(), (float)u.a.this.e.getWidth(), (float)u.a.this.e.getHeight());
                }
            })));
            this.e.setContentDescription((CharSequence)"CBAd");
            (this.f = a.a(new ImageView(context))).setBackgroundColor(-16777216);
            this.addView((View)this.f);
            this.addView((View)this.e);
        }
        
        protected void a(final float n, final float n2, final float n3, final float n4) {
            u.this.b(com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("x", n), com.chartboost.sdk.Libraries.e.a("y", n2), com.chartboost.sdk.Libraries.e.a("w", n3), com.chartboost.sdk.Libraries.e.a("h", n4)));
        }
        
        @Override
        protected void a(final int n, final int n2) {
            if (!this.h) {
                this.c();
                this.h = true;
            }
            final boolean a = CBUtility.a(u.this.a());
            h h;
            if (a) {
                h = u.this.j;
            }
            else {
                h = u.this.k;
            }
            h h2;
            if (a) {
                h2 = u.this.l;
            }
            else {
                h2 = u.this.m;
            }
            h h3 = h;
            if (!h.d()) {
                if (h == u.this.j) {
                    h3 = u.this.k;
                }
                else {
                    h3 = u.this.j;
                }
            }
            h h4 = h2;
            if (!h2.d()) {
                if (h2 == u.this.l) {
                    h4 = u.this.m;
                }
                else {
                    h4 = u.this.l;
                }
            }
            final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-2, -2);
            final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams(-2, -2);
            u.this.a((ViewGroup$LayoutParams)layoutParams, h3, 1.0f);
            u.this.p = Math.min(Math.min(n / (float)layoutParams.width, n2 / (float)layoutParams.height), 1.0f);
            layoutParams.width *= (int)u.this.p;
            layoutParams.height *= (int)u.this.p;
            final u g = u.this;
            String s;
            if (a) {
                s = "frame-portrait";
            }
            else {
                s = "frame-landscape";
            }
            final Point b = g.b(s);
            layoutParams.leftMargin = Math.round((n - layoutParams.width) / 2.0f + b.x / h3.f() * u.this.p);
            layoutParams.topMargin = Math.round(b.y / h3.f() * u.this.p + (n2 - layoutParams.height) / 2.0f);
            u.this.a((ViewGroup$LayoutParams)layoutParams2, h4, 1.0f);
            final u g2 = u.this;
            String s2;
            if (a) {
                s2 = "close-portrait";
            }
            else {
                s2 = "close-landscape";
            }
            final Point b2 = g2.b(s2);
            int round;
            int round2;
            if (b2.x == 0 && b2.y == 0) {
                round = Math.round(-layoutParams2.width / 2.0f) + (layoutParams.leftMargin + layoutParams.width);
                round2 = layoutParams.topMargin + Math.round(-layoutParams2.height / 2.0f);
            }
            else {
                round = Math.round(layoutParams.leftMargin + layoutParams.width / 2.0f + b2.x - layoutParams2.width / 2.0f);
                round2 = Math.round(b2.y + (layoutParams.topMargin + layoutParams.height / 2.0f) - layoutParams2.height / 2.0f);
            }
            layoutParams2.leftMargin = Math.min(Math.max(0, round), n - layoutParams2.width);
            layoutParams2.topMargin = Math.min(Math.max(0, round2), n2 - layoutParams2.height);
            this.c.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            this.d.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
            this.c.setScaleType(ImageView$ScaleType.FIT_CENTER);
            this.c.a(h3);
            this.d.a(h4);
            h h5;
            if (a) {
                h5 = u.this.n;
            }
            else {
                h5 = u.this.o;
            }
            h h6 = h5;
            if (!h5.d()) {
                if (h5 == u.this.n) {
                    h6 = u.this.o;
                }
                else {
                    h6 = u.this.n;
                }
            }
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-2, -2);
            u.this.a((ViewGroup$LayoutParams)relativeLayout$LayoutParams, h6, u.this.p);
            final u g3 = u.this;
            String s3;
            if (a) {
                s3 = "ad-portrait";
            }
            else {
                s3 = "ad-landscape";
            }
            final Point b3 = g3.b(s3);
            relativeLayout$LayoutParams.leftMargin = Math.round((n - relativeLayout$LayoutParams.width) / 2.0f + b3.x / h6.f() * u.this.p);
            relativeLayout$LayoutParams.topMargin = Math.round(b3.y / h6.f() * u.this.p + (n2 - relativeLayout$LayoutParams.height) / 2.0f);
            this.f.setLayoutParams((ViewGroup$LayoutParams)relativeLayout$LayoutParams);
            this.e.setLayoutParams((ViewGroup$LayoutParams)relativeLayout$LayoutParams);
            this.e.a(ImageView$ScaleType.FIT_CENTER);
            this.e.a(h6);
        }
        
        @Override
        public void b() {
            super.b();
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
        }
        
        protected void c() {
            (this.d = new az(this.getContext()) {
                @Override
                protected void a(final MotionEvent motionEvent) {
                    u.a.this.d();
                }
            }).setContentDescription((CharSequence)"CBClose");
            this.addView((View)this.d);
        }
        
        protected void d() {
            u.this.h();
        }
    }
}
