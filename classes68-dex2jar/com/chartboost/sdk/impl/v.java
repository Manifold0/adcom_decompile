// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import com.chartboost.sdk.Tracking.a;
import android.graphics.Point;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.view.MotionEvent;
import com.chartboost.sdk.g;
import android.view.View;
import java.io.File;
import com.chartboost.sdk.Libraries.CBUtility;
import android.content.Context;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.e;
import android.os.Handler;
import com.chartboost.sdk.Model.c;
import org.json.JSONObject;
import com.chartboost.sdk.Libraries.f;
import com.chartboost.sdk.Libraries.h;

public class v extends u
{
    protected boolean A;
    protected boolean B;
    protected boolean C;
    protected int D;
    protected h E;
    protected h F;
    protected h G;
    protected h H;
    protected h I;
    protected h J;
    protected h K;
    protected h L;
    protected boolean M;
    protected boolean N;
    protected boolean O;
    private boolean P;
    private boolean Q;
    private boolean R;
    final f q;
    protected int r;
    protected int s;
    protected String t;
    protected String u;
    protected int v;
    protected int w;
    JSONObject x;
    protected boolean y;
    protected boolean z;
    
    public v(final c c, final f q, final Handler handler, final com.chartboost.sdk.c c2) {
        super(c, handler, c2);
        this.r = 0;
        this.P = false;
        this.v = 0;
        this.w = 0;
        this.Q = false;
        this.R = false;
        this.C = false;
        this.D = 0;
        this.M = false;
        this.N = false;
        this.O = false;
        this.q = q;
        this.r = 0;
        this.E = new h(this);
        this.F = new h(this);
        this.G = new h(this);
        this.H = new h(this);
        this.I = new h(this);
        this.J = new h(this);
        this.K = new h(this);
        this.L = new h(this);
        this.s = 0;
    }
    
    public void a(final boolean q) {
        this.Q = q;
    }
    
    @Override
    public boolean a(JSONObject optJSONObject) {
        if (!super.a(optJSONObject)) {
            return false;
        }
        this.x = optJSONObject.optJSONObject("ux");
        if (this.x == null) {
            this.x = com.chartboost.sdk.Libraries.e.a(new com.chartboost.sdk.Libraries.e.a[0]);
        }
        if (this.d.isNull("video-landscape") || this.d.isNull("replay-landscape")) {
            this.i = false;
        }
        if (!this.E.a("replay-landscape") || !this.F.a("replay-portrait") || !this.I.a("video-click-button") || !this.J.a("post-video-reward-icon") || !this.K.a("post-video-button") || !this.G.a("video-confirmation-button") || !this.H.a("video-confirmation-icon") || !this.L.a("post-video-reward-icon")) {
            CBLogging.b("InterstitialVideoViewProtocol", "Error while downloading the assets");
            this.a(CBError.CBImpressionError.ASSETS_DOWNLOAD_FAILURE);
            return false;
        }
        this.y = this.x.optBoolean("video-controls-togglable");
        this.N = optJSONObject.optBoolean("fullscreen");
        this.O = optJSONObject.optBoolean("preroll_popup_fullscreen");
        if (this.e.n == 2) {
            optJSONObject = this.x.optJSONObject("confirmation");
            final JSONObject optJSONObject2 = this.x.optJSONObject("post-video-toaster");
            if (optJSONObject2 != null && !optJSONObject2.isNull("title") && !optJSONObject2.isNull("tagline")) {
                this.A = true;
            }
            if (optJSONObject != null && !optJSONObject.isNull("text") && !optJSONObject.isNull("color")) {
                this.z = true;
            }
            if (!this.x.isNull("post-video-reward-toaster")) {
                this.B = true;
            }
        }
        return true;
    }
    
    @Override
    protected e.a b(final Context context) {
        return new a(context);
    }
    
    @Override
    public void d() {
        super.d();
        this.E.c();
        this.F.c();
        this.I.c();
        this.J.c();
        this.K.c();
        this.G.c();
        this.H.c();
        this.L.c();
        this.E = null;
        this.F = null;
        this.I = null;
        this.J = null;
        this.K = null;
        this.G = null;
        this.H = null;
        this.L = null;
    }
    
    @Override
    protected void i() {
        if (this.z && (!this.G.d() || !this.H.d())) {
            this.z = false;
        }
        super.i();
    }
    
    @Override
    public float j() {
        return (float)this.w;
    }
    
    @Override
    public float k() {
        return (float)this.v;
    }
    
    @Override
    public boolean l() {
        this.q().d();
        return true;
    }
    
    @Override
    public void m() {
        super.m();
        if (this.r == 1 && this.P) {
            this.q().h.b().a(this.v);
            this.q().h.e();
        }
        this.P = false;
    }
    
    @Override
    public void n() {
        super.n();
        if (this.r == 1 && !this.P) {
            this.P = true;
            this.q().h.g();
        }
    }
    
    public boolean o() {
        return this.e.n == 1;
    }
    
    public boolean p() {
        return this.r == 1;
    }
    
    public a q() {
        return (a)super.e();
    }
    
    protected void r() {
        this.e.p();
    }
    
    protected boolean s() {
        boolean b2;
        final boolean b = b2 = false;
        switch (this.r) {
            default: {
                b2 = true;
                return b2;
            }
            case 2: {
                return b2;
            }
            case 0: {
                if (!this.O) {
                    b2 = b;
                    if (!CBUtility.a(CBUtility.a())) {
                        return b2;
                    }
                }
                return true;
            }
            case 1: {
                if (!this.N) {
                    b2 = b;
                    if (!CBUtility.a(CBUtility.a())) {
                        return b2;
                    }
                }
                return true;
            }
        }
    }
    
    public boolean t() {
        return this.Q;
    }
    
    public boolean u() {
        return this.R;
    }
    
    public void v() {
        if (this.t != null) {
            new File(this.t).delete();
        }
        this.R = true;
        this.a(CBError.CBImpressionError.ERROR_PLAYING_VIDEO);
    }
    
    public class a extends u.a
    {
        final ab h;
        y i;
        final t j;
        final w k;
        private final az m;
        private View n;
        private final az o;
        
        private a(final Context context) {
            super(context);
            final g a = g.a();
            if (com.chartboost.sdk.impl.v.this.N) {
                (this.n = new View(context)).setBackgroundColor(-16777216);
                this.n.setVisibility(8);
                this.addView(this.n);
            }
            if (com.chartboost.sdk.impl.v.this.e.n == 2) {
                (this.i = a.a(new y(context, com.chartboost.sdk.impl.v.this))).setVisibility(8);
                this.addView((View)this.i);
            }
            this.h = a.a(new ab(context, com.chartboost.sdk.impl.v.this));
            ((e.a)this).a((View)this.h.g);
            this.h.setVisibility(8);
            this.addView((View)this.h);
            (this.j = a.a(new t(context, com.chartboost.sdk.impl.v.this))).setVisibility(8);
            this.addView((View)this.j);
            if (com.chartboost.sdk.impl.v.this.e.n == 2) {
                (this.k = a.a(new w(context, com.chartboost.sdk.impl.v.this))).setVisibility(8);
                this.addView((View)this.k);
            }
            else {
                this.k = null;
            }
            (this.m = new az(this.getContext()) {
                @Override
                protected void a(final MotionEvent motionEvent) {
                    if (com.chartboost.sdk.impl.v.this.e.n == 2) {
                        com.chartboost.sdk.impl.v.a.this.k.a(false);
                    }
                    if (com.chartboost.sdk.impl.v.this.r == 1) {
                        com.chartboost.sdk.impl.v.a.this.c(false);
                    }
                    com.chartboost.sdk.impl.v.a.this.b(true);
                }
            }).setVisibility(8);
            this.addView((View)this.m);
            (this.o = new az(this.getContext()) {
                @Override
                protected void a(final MotionEvent motionEvent) {
                    com.chartboost.sdk.impl.v.a.this.d();
                }
            }).setVisibility(8);
            this.o.setContentDescription((CharSequence)"CBClose");
            this.addView((View)this.o);
            final JSONObject optJSONObject = com.chartboost.sdk.impl.v.this.x.optJSONObject("progress");
            final JSONObject optJSONObject2 = com.chartboost.sdk.impl.v.this.x.optJSONObject("video-controls-background");
            if (optJSONObject != null && !optJSONObject.isNull("background-color") && !optJSONObject.isNull("border-color") && !optJSONObject.isNull("progress-color") && !optJSONObject.isNull("radius")) {
                com.chartboost.sdk.impl.v.this.M = true;
                final x c = this.h.c();
                c.a(e.a(optJSONObject.optString("background-color")));
                c.b(e.a(optJSONObject.optString("border-color")));
                c.c(e.a(optJSONObject.optString("progress-color")));
                c.b((float)optJSONObject.optDouble("radius", 0.0));
            }
            if (optJSONObject2 != null && !optJSONObject2.isNull("color")) {
                this.h.a(e.a(optJSONObject2.optString("color")));
            }
            if (com.chartboost.sdk.impl.v.this.e.n == 2 && com.chartboost.sdk.impl.v.this.A) {
                final JSONObject optJSONObject3 = com.chartboost.sdk.impl.v.this.x.optJSONObject("post-video-toaster");
                if (optJSONObject3 != null) {
                    this.j.a(optJSONObject3.optString("title"), optJSONObject3.optString("tagline"));
                }
            }
            if (com.chartboost.sdk.impl.v.this.e.n == 2 && com.chartboost.sdk.impl.v.this.z) {
                final JSONObject optJSONObject4 = com.chartboost.sdk.impl.v.this.x.optJSONObject("confirmation");
                if (optJSONObject4 != null) {
                    this.i.a(optJSONObject4.optString("text"), e.a(optJSONObject4.optString("color")));
                }
            }
            if (com.chartboost.sdk.impl.v.this.e.n == 2 && com.chartboost.sdk.impl.v.this.B) {
                final JSONObject a2 = com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.impl.v.this.x, "post-video-reward-toaster");
                int n;
                if (a2 != null && a2.optString("position").equals("inside-top")) {
                    n = 0;
                }
                else {
                    n = 1;
                }
                this.k.a(n);
                final w k = this.k;
                String optString;
                if (a2 != null) {
                    optString = a2.optString("text");
                }
                else {
                    optString = "";
                }
                k.a(optString);
                if (com.chartboost.sdk.impl.v.this.J.d()) {
                    this.k.a(com.chartboost.sdk.impl.v.this.L);
                }
            }
            final JSONObject g = com.chartboost.sdk.impl.v.this.g();
            if (g == null || g.isNull("video-click-button")) {
                this.h.d();
            }
            this.h.d(com.chartboost.sdk.impl.v.this.x.optBoolean("video-progress-timer-enabled"));
            if (com.chartboost.sdk.impl.v.this.O || com.chartboost.sdk.impl.v.this.N) {
                this.f.setVisibility(4);
            }
            String s;
            if (CBUtility.a(com.chartboost.sdk.impl.v.this.a())) {
                s = "video-portrait";
            }
            else {
                s = "video-landscape";
            }
            final JSONObject a3 = com.chartboost.sdk.Libraries.e.a(g, s);
            String optString2;
            if (a3 != null) {
                optString2 = a3.optString("id");
            }
            else {
                optString2 = "";
            }
            com.chartboost.sdk.impl.v.this.u = optString2;
            if (com.chartboost.sdk.impl.v.this.u.isEmpty()) {
                com.chartboost.sdk.impl.v.this.a(CBError.CBImpressionError.VIDEO_ID_MISSING);
                return;
            }
            if (com.chartboost.sdk.impl.v.this.t == null) {
                com.chartboost.sdk.impl.v.this.t = com.chartboost.sdk.impl.v.this.q.a(com.chartboost.sdk.impl.v.this.u);
            }
            if (com.chartboost.sdk.impl.v.this.t == null) {
                com.chartboost.sdk.impl.v.this.a(CBError.CBImpressionError.VIDEO_UNAVAILABLE);
                return;
            }
            this.h.a(com.chartboost.sdk.impl.v.this.t);
        }
        
        private void a(final int r, final boolean b) {
            final boolean b2 = true;
            switch (com.chartboost.sdk.impl.v.this.r = r) {
                case 0: {
                    com.chartboost.sdk.impl.v.this.a(!com.chartboost.sdk.impl.v.this.s(), (View)this.e, b);
                    if (com.chartboost.sdk.impl.v.this.e.n == 2) {
                        com.chartboost.sdk.impl.v.this.a(true, (View)this.i, b);
                    }
                    if (com.chartboost.sdk.impl.v.this.N) {
                        com.chartboost.sdk.impl.v.this.a(false, this.n, b);
                    }
                    com.chartboost.sdk.impl.v.this.a(false, (View)this.h, b);
                    com.chartboost.sdk.impl.v.this.a(false, (View)this.m, b);
                    com.chartboost.sdk.impl.v.this.a(false, (View)this.j, b);
                    this.e.setEnabled(false);
                    this.m.setEnabled(false);
                    this.h.setEnabled(false);
                    break;
                }
                case 1: {
                    com.chartboost.sdk.impl.v.this.a(false, (View)this.e, b);
                    if (com.chartboost.sdk.impl.v.this.e.n == 2) {
                        com.chartboost.sdk.impl.v.this.a(false, (View)this.i, b);
                    }
                    if (com.chartboost.sdk.impl.v.this.N) {
                        com.chartboost.sdk.impl.v.this.a(true, this.n, b);
                    }
                    com.chartboost.sdk.impl.v.this.a(true, (View)this.h, b);
                    com.chartboost.sdk.impl.v.this.a(false, (View)this.m, b);
                    com.chartboost.sdk.impl.v.this.a(false, (View)this.j, b);
                    this.e.setEnabled(true);
                    this.m.setEnabled(false);
                    this.h.setEnabled(true);
                    break;
                }
                case 2: {
                    com.chartboost.sdk.impl.v.this.a(true, (View)this.e, b);
                    if (com.chartboost.sdk.impl.v.this.e.n == 2) {
                        com.chartboost.sdk.impl.v.this.a(false, (View)this.i, b);
                    }
                    if (com.chartboost.sdk.impl.v.this.N) {
                        com.chartboost.sdk.impl.v.this.a(false, this.n, b);
                    }
                    com.chartboost.sdk.impl.v.this.a(false, (View)this.h, b);
                    com.chartboost.sdk.impl.v.this.a(true, (View)this.m, b);
                    com.chartboost.sdk.impl.v.this.a(com.chartboost.sdk.impl.v.this.K.d() && com.chartboost.sdk.impl.v.this.J.d() && com.chartboost.sdk.impl.v.this.A, (View)this.j, b);
                    this.m.setEnabled(true);
                    this.e.setEnabled(true);
                    this.h.setEnabled(false);
                    if (com.chartboost.sdk.impl.v.this.C) {
                        this.e(false);
                        break;
                    }
                    break;
                }
            }
            final boolean f = this.f();
            final az d = this.d(true);
            ((View)d).setEnabled(f);
            com.chartboost.sdk.impl.v.this.a(f, (View)d, b);
            final az d2 = this.d(false);
            ((View)d2).setEnabled(false);
            com.chartboost.sdk.impl.v.this.a(false, (View)d2, b);
            if (com.chartboost.sdk.impl.v.this.O || com.chartboost.sdk.impl.v.this.N) {
                com.chartboost.sdk.impl.v.this.a(!com.chartboost.sdk.impl.v.this.s(), (View)this.f, b);
            }
            com.chartboost.sdk.impl.v.this.a(!com.chartboost.sdk.impl.v.this.s(), (View)this.c, b);
            ((e.a)this).a(r != 0 && b2);
        }
        
        private void e(final boolean b) {
            if (b) {
                this.k.a(true);
            }
            else {
                this.k.setVisibility(0);
            }
            com.chartboost.sdk.impl.v.this.a.postDelayed((Runnable)new Runnable() {
                @Override
                public void run() {
                    com.chartboost.sdk.impl.v.a.this.k.a(false);
                }
            }, 2500L);
        }
        
        @Override
        protected void a(final float n, final float n2, final float n3, final float n4) {
            if ((com.chartboost.sdk.impl.v.this.y && com.chartboost.sdk.impl.v.this.r == 1) || com.chartboost.sdk.impl.v.this.r == 0) {
                return;
            }
            this.b(n, n2, n3, n4);
        }
        
        @Override
        protected void a(int topMargin, final int n) {
            super.a(topMargin, n);
            this.a(com.chartboost.sdk.impl.v.this.r, false);
            final boolean a = CBUtility.a(com.chartboost.sdk.impl.v.this.a());
            final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -1);
            final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams(-2, -2);
            final RelativeLayout$LayoutParams layoutParams3 = new RelativeLayout$LayoutParams(-1, -1);
            final RelativeLayout$LayoutParams layoutParams4 = new RelativeLayout$LayoutParams(-1, -1);
            final RelativeLayout$LayoutParams layoutParams5 = new RelativeLayout$LayoutParams(-1, -1);
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams = (RelativeLayout$LayoutParams)this.c.getLayoutParams();
            final v l = com.chartboost.sdk.impl.v.this;
            h h;
            if (a) {
                h = com.chartboost.sdk.impl.v.this.F;
            }
            else {
                h = com.chartboost.sdk.impl.v.this.E;
            }
            l.a((ViewGroup$LayoutParams)layoutParams2, h, 1.0f);
            final v i = com.chartboost.sdk.impl.v.this;
            String s;
            if (a) {
                s = "replay-portrait";
            }
            else {
                s = "replay-landscape";
            }
            final Point b = i.b(s);
            final int round = Math.round(relativeLayout$LayoutParams.leftMargin + relativeLayout$LayoutParams.width / 2.0f + b.x - layoutParams2.width / 2.0f);
            final int round2 = Math.round(relativeLayout$LayoutParams.height / 2.0f + relativeLayout$LayoutParams.topMargin + b.y - layoutParams2.height / 2.0f);
            layoutParams2.leftMargin = Math.min(Math.max(0, round), topMargin - layoutParams2.width);
            layoutParams2.topMargin = Math.min(Math.max(0, round2), n - layoutParams2.height);
            this.m.bringToFront();
            if (a) {
                this.m.a(com.chartboost.sdk.impl.v.this.F);
            }
            else {
                this.m.a(com.chartboost.sdk.impl.v.this.E);
            }
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = (RelativeLayout$LayoutParams)this.e.getLayoutParams();
            if (!com.chartboost.sdk.impl.v.this.s()) {
                layoutParams3.width = relativeLayout$LayoutParams2.width;
                layoutParams3.height = relativeLayout$LayoutParams2.height;
                layoutParams3.leftMargin = relativeLayout$LayoutParams2.leftMargin;
                layoutParams3.topMargin = relativeLayout$LayoutParams2.topMargin;
                layoutParams4.width = relativeLayout$LayoutParams2.width;
                layoutParams4.height = relativeLayout$LayoutParams2.height;
                layoutParams4.leftMargin = relativeLayout$LayoutParams2.leftMargin;
                layoutParams4.topMargin = relativeLayout$LayoutParams2.topMargin;
            }
            else {
                final RelativeLayout$LayoutParams layoutParams6 = new RelativeLayout$LayoutParams(-2, -2);
                h h2;
                if (a) {
                    h2 = com.chartboost.sdk.impl.v.this.l;
                }
                else {
                    h2 = com.chartboost.sdk.impl.v.this.m;
                }
                com.chartboost.sdk.impl.v.this.a((ViewGroup$LayoutParams)layoutParams6, h2, 1.0f);
                layoutParams6.leftMargin = 0;
                layoutParams6.topMargin = 0;
                layoutParams6.addRule(11);
                this.o.setLayoutParams((ViewGroup$LayoutParams)layoutParams6);
                this.o.a(h2);
            }
            layoutParams5.width = relativeLayout$LayoutParams2.width;
            layoutParams5.height = 72;
            layoutParams5.leftMargin = relativeLayout$LayoutParams2.leftMargin;
            topMargin = relativeLayout$LayoutParams2.topMargin;
            layoutParams5.topMargin = relativeLayout$LayoutParams2.height + topMargin - 72;
            if (com.chartboost.sdk.impl.v.this.N) {
                this.n.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            }
            if (com.chartboost.sdk.impl.v.this.e.n == 2) {
                this.i.setLayoutParams((ViewGroup$LayoutParams)layoutParams3);
            }
            this.h.setLayoutParams((ViewGroup$LayoutParams)layoutParams4);
            this.j.setLayoutParams((ViewGroup$LayoutParams)layoutParams5);
            this.m.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
            if (com.chartboost.sdk.impl.v.this.e.n == 2) {
                this.i.a();
            }
            this.h.a();
        }
        
        @Override
        public void b() {
            com.chartboost.sdk.impl.v.this.n();
            super.b();
        }
        
        protected void b(final float n, final float n2, final float n3, final float n4) {
            if (com.chartboost.sdk.impl.v.this.r == 1) {
                this.c(false);
            }
            com.chartboost.sdk.impl.v.this.b(com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("x", n), com.chartboost.sdk.Libraries.e.a("y", n2), com.chartboost.sdk.Libraries.e.a("w", n3), com.chartboost.sdk.Libraries.e.a("h", n4)));
        }
        
        void b(final boolean b) {
            if (com.chartboost.sdk.impl.v.this.r != 1) {
                if (com.chartboost.sdk.impl.v.this.z) {
                    this.a(0, b);
                    return;
                }
                this.a(1, b);
                final JSONObject a = com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.impl.v.this.x, "timer");
                if (com.chartboost.sdk.impl.v.this.s < 1 && a != null && !a.isNull("delay")) {
                    String s;
                    if (com.chartboost.sdk.impl.v.this.y) {
                        s = "visible";
                    }
                    else {
                        s = "hidden";
                    }
                    CBLogging.c("InterstitialVideoViewProtocol", String.format("controls starting %s, setting timer", s));
                    this.h.b(com.chartboost.sdk.impl.v.this.y);
                    com.chartboost.sdk.impl.v.this.a((View)this.h, new Runnable() {
                        @Override
                        public void run() {
                            while (true) {
                                while (true) {
                                    Object h = null;
                                    Label_0016: {
                                        if (com.chartboost.sdk.impl.v.this.y) {
                                            h = "hidden";
                                            break Label_0016;
                                        }
                                        Label_0102: {
                                            break Label_0102;
                                            while (true) {
                                                final boolean b;
                                                ((ab)h).a(b, true);
                                                synchronized (com.chartboost.sdk.impl.v.this.g) {
                                                    com.chartboost.sdk.impl.v.this.g.remove(com.chartboost.sdk.impl.v.a.this.h);
                                                    return;
                                                    h = "shown";
                                                    break;
                                                    b = false;
                                                }
                                            }
                                        }
                                    }
                                    CBLogging.c("InterstitialVideoViewProtocol", String.format("controls %s automatically from timer", h));
                                    h = com.chartboost.sdk.impl.v.a.this.h;
                                    if (!com.chartboost.sdk.impl.v.this.y) {
                                        final boolean b = true;
                                        continue;
                                    }
                                    break;
                                }
                                continue;
                            }
                        }
                    }, Math.round(a.optDouble("delay", 0.0) * 1000.0));
                }
                else {
                    this.h.b(!com.chartboost.sdk.impl.v.this.y);
                }
                this.h.e();
                if (com.chartboost.sdk.impl.v.this.s <= 1) {
                    com.chartboost.sdk.impl.v.this.e.f();
                }
            }
        }
        
        @Override
        protected void c() {
            super.c();
            if (com.chartboost.sdk.impl.v.this.r == 0 && (!com.chartboost.sdk.impl.v.this.z || com.chartboost.sdk.impl.v.this.o())) {
                this.b(false);
                return;
            }
            this.a(com.chartboost.sdk.impl.v.this.r, false);
        }
        
        void c(final boolean b) {
            this.h.f();
            if (com.chartboost.sdk.impl.v.this.r == 1 && b) {
                if (com.chartboost.sdk.impl.v.this.s < 1 && com.chartboost.sdk.impl.v.this.x != null && !com.chartboost.sdk.impl.v.this.x.isNull("post-video-reward-toaster") && com.chartboost.sdk.impl.v.this.B && com.chartboost.sdk.impl.v.this.J.d() && com.chartboost.sdk.impl.v.this.K.d()) {
                    this.e(true);
                }
                this.a(2, true);
                if (CBUtility.a(CBUtility.a())) {
                    this.requestLayout();
                }
            }
        }
        
        public az d(final boolean b) {
            if ((com.chartboost.sdk.impl.v.this.s() && b) || (!com.chartboost.sdk.impl.v.this.s() && !b)) {
                return this.o;
            }
            return this.d;
        }
        
        @Override
        protected void d() {
            if (com.chartboost.sdk.impl.v.this.r == 1 && com.chartboost.sdk.impl.v.this.e.a.a == 1) {
                return;
            }
            if (com.chartboost.sdk.impl.v.this.r == 1) {
                this.c(false);
                this.h.h();
                if (com.chartboost.sdk.impl.v.this.s < 1) {
                    final v l = com.chartboost.sdk.impl.v.this;
                    ++l.s;
                    com.chartboost.sdk.impl.v.this.e.e();
                }
            }
            com.chartboost.sdk.impl.v.this.a.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        com.chartboost.sdk.impl.v.this.h();
                    }
                    catch (Exception ex) {
                        com.chartboost.sdk.Tracking.a.a(a.class, "onCloseButton Runnable.run", ex);
                    }
                }
            });
        }
        
        public void e() {
            this.c(true);
            this.h.h();
            final v l = com.chartboost.sdk.impl.v.this;
            ++l.s;
            if (com.chartboost.sdk.impl.v.this.s <= 1 && !com.chartboost.sdk.impl.v.this.u() && com.chartboost.sdk.impl.v.this.v >= 1) {
                com.chartboost.sdk.impl.v.this.e.e();
            }
        }
        
        protected boolean f() {
            if (com.chartboost.sdk.impl.v.this.r == 1 && com.chartboost.sdk.impl.v.this.s < 1) {
                final StringBuilder append = new StringBuilder().append("close-");
                String s;
                if (CBUtility.a(com.chartboost.sdk.impl.v.this.a())) {
                    s = "portrait";
                }
                else {
                    s = "landscape";
                }
                final JSONObject a = com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.impl.v.this.g(), append.append(s).toString());
                float n;
                if (a != null) {
                    n = (float)a.optDouble("delay", -1.0);
                }
                else {
                    n = -1.0f;
                }
                int round;
                if (n >= 0.0f) {
                    round = Math.round(n * 1000.0f);
                }
                else {
                    round = -1;
                }
                com.chartboost.sdk.impl.v.this.D = round;
                if (round < 0) {
                    return false;
                }
                if (round > this.h.b().d()) {
                    return false;
                }
            }
            return true;
        }
        
        protected void g() {
            com.chartboost.sdk.impl.v.this.z = false;
            this.b(true);
        }
    }
}
