package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import com.chartboost.sdk.C1397c;
import com.chartboost.sdk.C1403e;
import com.chartboost.sdk.C1403e.C1402a;
import com.chartboost.sdk.C1405g;
import com.chartboost.sdk.Libraries.C1377e;
import com.chartboost.sdk.Libraries.C1381h;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C1388c;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.impl.u */
public class C1461u extends C1403e {
    /* renamed from: j */
    C1381h f3342j = new C1381h(this);
    /* renamed from: k */
    C1381h f3343k = new C1381h(this);
    /* renamed from: l */
    C1381h f3344l = new C1381h(this);
    /* renamed from: m */
    C1381h f3345m = new C1381h(this);
    /* renamed from: n */
    C1381h f3346n = new C1381h(this);
    /* renamed from: o */
    C1381h f3347o = new C1381h(this);
    /* renamed from: p */
    protected float f3348p = 1.0f;
    /* renamed from: q */
    private final String f3349q = "ImageViewProtocol";

    /* renamed from: com.chartboost.sdk.impl.u$a */
    public class C1460a extends C1402a {
        /* renamed from: c */
        protected ay f3336c;
        /* renamed from: d */
        protected az f3337d;
        /* renamed from: e */
        protected az f3338e;
        /* renamed from: f */
        protected ImageView f3339f;
        /* renamed from: g */
        final /* synthetic */ C1461u f3340g;
        /* renamed from: h */
        private boolean f3341h = false;

        protected C1460a(final C1461u c1461u, Context context) {
            this.f3340g = c1461u;
            super(c1461u, context);
            setBackgroundColor(0);
            setLayoutParams(new LayoutParams(-1, -1));
            C1405g a = C1405g.m3317a();
            this.f3336c = (ay) a.m3318a(new ay(context));
            addView(this.f3336c, new LayoutParams(-1, -1));
            this.f3338e = (az) a.m3318a(new az(this, context) {
                /* renamed from: b */
                final /* synthetic */ C1460a f3334b;

                /* renamed from: a */
                protected void mo4279a(MotionEvent motionEvent) {
                    this.f3334b.mo4319a(motionEvent.getX(), motionEvent.getY(), (float) this.f3334b.f3338e.getWidth(), (float) this.f3334b.f3338e.getHeight());
                }
            });
            m3290a(this.f3338e);
            this.f3338e.setContentDescription("CBAd");
            this.f3339f = (ImageView) a.m3318a(new ImageView(context));
            this.f3339f.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            addView(this.f3339f);
            addView(this.f3338e);
        }

        /* renamed from: c */
        protected void mo4320c() {
            this.f3337d = new az(this, getContext()) {
                /* renamed from: a */
                final /* synthetic */ C1460a f3335a;

                /* renamed from: a */
                protected void mo4279a(MotionEvent motionEvent) {
                    this.f3335a.mo4321d();
                }
            };
            this.f3337d.setContentDescription("CBClose");
            addView(this.f3337d);
        }

        /* renamed from: a */
        protected void mo4319a(float f, float f2, float f3, float f4) {
            this.f3340g.m3304b(C1377e.m3130a(C1377e.m3128a("x", Float.valueOf(f)), C1377e.m3128a("y", Float.valueOf(f2)), C1377e.m3128a("w", Float.valueOf(f3)), C1377e.m3128a("h", Float.valueOf(f4))));
        }

        /* renamed from: a */
        protected void mo4296a(int i, int i2) {
            int round;
            int round2;
            if (!this.f3341h) {
                mo4320c();
                this.f3341h = true;
            }
            boolean a = CBUtility.m3111a(this.f3340g.m3296a());
            C1381h c1381h = a ? this.f3340g.f3342j : this.f3340g.f3343k;
            C1381h c1381h2 = a ? this.f3340g.f3344l : this.f3340g.f3345m;
            if (!c1381h.m3155d()) {
                if (c1381h == this.f3340g.f3342j) {
                    c1381h = this.f3340g.f3343k;
                } else {
                    c1381h = this.f3340g.f3342j;
                }
            }
            if (!c1381h2.m3155d()) {
                if (c1381h2 == this.f3340g.f3344l) {
                    c1381h2 = this.f3340g.f3345m;
                } else {
                    c1381h2 = this.f3340g.f3344l;
                }
            }
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-2, -2);
            this.f3340g.m3653a(layoutParams, c1381h, 1.0f);
            this.f3340g.f3348p = Math.min(Math.min(((float) i) / ((float) layoutParams.width), ((float) i2) / ((float) layoutParams.height)), 1.0f);
            layoutParams.width = (int) (((float) layoutParams.width) * this.f3340g.f3348p);
            layoutParams.height = (int) (((float) layoutParams.height) * this.f3340g.f3348p);
            Point b = this.f3340g.m3655b(a ? "frame-portrait" : "frame-landscape");
            layoutParams.leftMargin = Math.round((((float) (i - layoutParams.width)) / 2.0f) + ((((float) b.x) / c1381h.m3157f()) * this.f3340g.f3348p));
            layoutParams.topMargin = Math.round(((((float) b.y) / c1381h.m3157f()) * this.f3340g.f3348p) + (((float) (i2 - layoutParams.height)) / 2.0f));
            this.f3340g.m3653a(layoutParams2, c1381h2, 1.0f);
            b = this.f3340g.m3655b(a ? "close-portrait" : "close-landscape");
            if (b.x == 0 && b.y == 0) {
                round = Math.round(((float) (-layoutParams2.width)) / 2.0f) + (layoutParams.leftMargin + layoutParams.width);
                round2 = layoutParams.topMargin + Math.round(((float) (-layoutParams2.height)) / 2.0f);
            } else {
                round = Math.round(((((float) layoutParams.leftMargin) + (((float) layoutParams.width) / 2.0f)) + ((float) b.x)) - (((float) layoutParams2.width) / 2.0f));
                round2 = Math.round((((float) b.y) + (((float) layoutParams.topMargin) + (((float) layoutParams.height) / 2.0f))) - (((float) layoutParams2.height) / 2.0f));
            }
            layoutParams2.leftMargin = Math.min(Math.max(0, round), i - layoutParams2.width);
            layoutParams2.topMargin = Math.min(Math.max(0, round2), i2 - layoutParams2.height);
            this.f3336c.setLayoutParams(layoutParams);
            this.f3337d.setLayoutParams(layoutParams2);
            this.f3336c.setScaleType(ScaleType.FIT_CENTER);
            this.f3336c.m3471a(c1381h);
            this.f3337d.m3342a(c1381h2);
            c1381h2 = a ? this.f3340g.f3346n : this.f3340g.f3347o;
            if (!c1381h2.m3155d()) {
                if (c1381h2 == this.f3340g.f3346n) {
                    c1381h2 = this.f3340g.f3347o;
                } else {
                    c1381h2 = this.f3340g.f3346n;
                }
            }
            ViewGroup.LayoutParams layoutParams3 = new LayoutParams(-2, -2);
            this.f3340g.m3653a(layoutParams3, c1381h2, this.f3340g.f3348p);
            Point b2 = this.f3340g.m3655b(a ? "ad-portrait" : "ad-landscape");
            layoutParams3.leftMargin = Math.round((((float) (i - layoutParams3.width)) / 2.0f) + ((((float) b2.x) / c1381h2.m3157f()) * this.f3340g.f3348p));
            layoutParams3.topMargin = Math.round(((((float) b2.y) / c1381h2.m3157f()) * this.f3340g.f3348p) + (((float) (i2 - layoutParams3.height)) / 2.0f));
            this.f3339f.setLayoutParams(layoutParams3);
            this.f3338e.setLayoutParams(layoutParams3);
            this.f3338e.m3341a(ScaleType.FIT_CENTER);
            this.f3338e.m3342a(c1381h2);
        }

        /* renamed from: d */
        protected void mo4321d() {
            this.f3340g.mo4301h();
        }

        /* renamed from: b */
        public void mo4318b() {
            super.mo4318b();
            this.f3336c = null;
            this.f3337d = null;
            this.f3338e = null;
            this.f3339f = null;
        }
    }

    public C1461u(C1388c c1388c, Handler handler, C1397c c1397c) {
        super(c1388c, handler, c1397c);
    }

    /* renamed from: b */
    protected C1402a mo4298b(Context context) {
        return new C1460a(this, context);
    }

    /* renamed from: a */
    public boolean mo4297a(JSONObject jSONObject) {
        if (!super.mo4297a(jSONObject)) {
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
        if (this.f3343k.m3151a("frame-landscape") && this.f3342j.m3151a("frame-portrait") && this.f3345m.m3151a("close-landscape") && this.f3344l.m3151a("close-portrait") && this.f3347o.m3151a("ad-landscape") && this.f3346n.m3151a("ad-portrait")) {
            return true;
        }
        CBLogging.m3099b("ImageViewProtocol", "Error while downloading the assets");
        m3298a(CBImpressionError.ASSETS_DOWNLOAD_FAILURE);
        return false;
    }

    /* renamed from: b */
    protected Point m3655b(String str) {
        JSONObject a = C1377e.m3129a(this.d, str, "offset");
        if (a != null) {
            return new Point(a.optInt("x"), a.optInt("y"));
        }
        return new Point(0, 0);
    }

    /* renamed from: a */
    public void m3653a(ViewGroup.LayoutParams layoutParams, C1381h c1381h, float f) {
        if (c1381h != null && c1381h.m3155d()) {
            layoutParams.width = (int) ((((float) c1381h.m3150a()) / c1381h.m3157f()) * f);
            layoutParams.height = (int) ((((float) c1381h.m3153b()) / c1381h.m3157f()) * f);
        }
    }

    /* renamed from: d */
    public void mo4299d() {
        super.mo4299d();
        this.f3343k.m3154c();
        this.f3342j.m3154c();
        this.f3345m.m3154c();
        this.f3344l.m3154c();
        this.f3347o.m3154c();
        this.f3346n.m3154c();
        this.f3343k = null;
        this.f3342j = null;
        this.f3345m = null;
        this.f3344l = null;
        this.f3347o = null;
        this.f3346n = null;
    }
}
