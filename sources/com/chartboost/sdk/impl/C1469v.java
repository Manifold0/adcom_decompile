package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.chartboost.sdk.C1397c;
import com.chartboost.sdk.C1403e;
import com.chartboost.sdk.C1403e.C1402a;
import com.chartboost.sdk.C1405g;
import com.chartboost.sdk.Libraries.C1377e;
import com.chartboost.sdk.Libraries.C1377e.C1376a;
import com.chartboost.sdk.Libraries.C1378f;
import com.chartboost.sdk.Libraries.C1381h;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C1388c;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Tracking.C1391a;
import com.chartboost.sdk.impl.C1461u.C1460a;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.mraid.controller.Abstract;
import java.io.File;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.impl.v */
public class C1469v extends C1461u {
    /* renamed from: A */
    protected boolean f3365A;
    /* renamed from: B */
    protected boolean f3366B;
    /* renamed from: C */
    protected boolean f3367C = false;
    /* renamed from: D */
    protected int f3368D = 0;
    /* renamed from: E */
    protected C1381h f3369E;
    /* renamed from: F */
    protected C1381h f3370F;
    /* renamed from: G */
    protected C1381h f3371G;
    /* renamed from: H */
    protected C1381h f3372H;
    /* renamed from: I */
    protected C1381h f3373I;
    /* renamed from: J */
    protected C1381h f3374J;
    /* renamed from: K */
    protected C1381h f3375K;
    /* renamed from: L */
    protected C1381h f3376L;
    /* renamed from: M */
    protected boolean f3377M = false;
    /* renamed from: N */
    protected boolean f3378N = false;
    /* renamed from: O */
    protected boolean f3379O = false;
    /* renamed from: P */
    private boolean f3380P = false;
    /* renamed from: Q */
    private boolean f3381Q = false;
    /* renamed from: R */
    private boolean f3382R = false;
    /* renamed from: q */
    final C1378f f3383q;
    /* renamed from: r */
    protected int f3384r = 0;
    /* renamed from: s */
    protected int f3385s;
    /* renamed from: t */
    protected String f3386t;
    /* renamed from: u */
    protected String f3387u;
    /* renamed from: v */
    protected int f3388v = 0;
    /* renamed from: w */
    protected int f3389w = 0;
    /* renamed from: x */
    JSONObject f3390x;
    /* renamed from: y */
    protected boolean f3391y;
    /* renamed from: z */
    protected boolean f3392z;

    /* renamed from: com.chartboost.sdk.impl.v$a */
    public class C1468a extends C1460a {
        /* renamed from: h */
        final ab f3357h;
        /* renamed from: i */
        C1473y f3358i;
        /* renamed from: j */
        final C1457t f3359j;
        /* renamed from: k */
        final C1470w f3360k;
        /* renamed from: l */
        final /* synthetic */ C1469v f3361l;
        /* renamed from: m */
        private final az f3362m;
        /* renamed from: n */
        private View f3363n;
        /* renamed from: o */
        private final az f3364o;

        /* renamed from: com.chartboost.sdk.impl.v$a$3 */
        class C14653 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C1468a f3354a;

            C14653(C1468a c1468a) {
                this.f3354a = c1468a;
            }

            public void run() {
                boolean z;
                String str = "InterstitialVideoViewProtocol";
                String str2 = "controls %s automatically from timer";
                Object[] objArr = new Object[1];
                objArr[0] = this.f3354a.f3361l.f3391y ? "hidden" : "shown";
                CBLogging.m3101c(str, String.format(str2, objArr));
                ab abVar = this.f3354a.f3357h;
                if (this.f3354a.f3361l.f3391y) {
                    z = false;
                } else {
                    z = true;
                }
                abVar.m3351a(z, true);
                synchronized (this.f3354a.f3361l.g) {
                    this.f3354a.f3361l.g.remove(this.f3354a.f3357h);
                }
            }
        }

        /* renamed from: com.chartboost.sdk.impl.v$a$4 */
        class C14664 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C1468a f3355a;

            C14664(C1468a c1468a) {
                this.f3355a = c1468a;
            }

            public void run() {
                this.f3355a.f3360k.m3641a(false);
            }
        }

        /* renamed from: com.chartboost.sdk.impl.v$a$5 */
        class C14675 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C1468a f3356a;

            C14675(C1468a c1468a) {
                this.f3356a = c1468a;
            }

            public void run() {
                try {
                    this.f3356a.f3361l.mo4301h();
                } catch (Exception e) {
                    C1391a.m3206a(C1468a.class, "onCloseButton Runnable.run", e);
                }
            }
        }

        private C1468a(final C1469v c1469v, Context context) {
            this.f3361l = c1469v;
            super(c1469v, context);
            C1405g a = C1405g.m3317a();
            if (c1469v.f3378N) {
                this.f3363n = new View(context);
                this.f3363n.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                this.f3363n.setVisibility(8);
                addView(this.f3363n);
            }
            if (c1469v.e.f2768n == 2) {
                this.f3358i = (C1473y) a.m3318a(new C1473y(context, c1469v));
                this.f3358i.setVisibility(8);
                addView(this.f3358i);
            }
            this.f3357h = (ab) a.m3318a(new ab(context, c1469v));
            m3290a(this.f3357h.f2972g);
            this.f3357h.setVisibility(8);
            addView(this.f3357h);
            this.f3359j = (C1457t) a.m3318a(new C1457t(context, c1469v));
            this.f3359j.setVisibility(8);
            addView(this.f3359j);
            if (c1469v.e.f2768n == 2) {
                this.f3360k = (C1470w) a.m3318a(new C1470w(context, c1469v));
                this.f3360k.setVisibility(8);
                addView(this.f3360k);
            } else {
                this.f3360k = null;
            }
            this.f3362m = new az(this, getContext()) {
                /* renamed from: b */
                final /* synthetic */ C1468a f3351b;

                /* renamed from: a */
                protected void mo4279a(MotionEvent motionEvent) {
                    if (this.f3351b.f3361l.e.f2768n == 2) {
                        this.f3351b.f3360k.m3641a(false);
                    }
                    if (this.f3351b.f3361l.f3384r == 1) {
                        this.f3351b.m3668c(false);
                    }
                    this.f3351b.m3666b(true);
                }
            };
            this.f3362m.setVisibility(8);
            addView(this.f3362m);
            this.f3364o = new az(this, getContext()) {
                /* renamed from: b */
                final /* synthetic */ C1468a f3353b;

                /* renamed from: a */
                protected void mo4279a(MotionEvent motionEvent) {
                    this.f3353b.mo4321d();
                }
            };
            this.f3364o.setVisibility(8);
            this.f3364o.setContentDescription("CBClose");
            addView(this.f3364o);
            JSONObject optJSONObject = c1469v.f3390x.optJSONObject(NotificationCompat.CATEGORY_PROGRESS);
            JSONObject optJSONObject2 = c1469v.f3390x.optJSONObject("video-controls-background");
            if (!(optJSONObject == null || optJSONObject.isNull("background-color") || optJSONObject.isNull("border-color") || optJSONObject.isNull("progress-color") || optJSONObject.isNull("radius"))) {
                c1469v.f3377M = true;
                C1471x c = this.f3357h.m3354c();
                c.m3699a(C1403e.m3294a(optJSONObject.optString("background-color")));
                c.m3702b(C1403e.m3294a(optJSONObject.optString("border-color")));
                c.m3703c(C1403e.m3294a(optJSONObject.optString("progress-color")));
                c.m3701b((float) optJSONObject.optDouble("radius", 0.0d));
            }
            if (!(optJSONObject2 == null || optJSONObject2.isNull(ParametersKeys.COLOR))) {
                this.f3357h.m3348a(C1403e.m3294a(optJSONObject2.optString(ParametersKeys.COLOR)));
            }
            if (c1469v.e.f2768n == 2 && c1469v.f3365A) {
                optJSONObject = c1469v.f3390x.optJSONObject("post-video-toaster");
                if (optJSONObject != null) {
                    this.f3359j.m3644a(optJSONObject.optString("title"), optJSONObject.optString("tagline"));
                }
            }
            if (c1469v.e.f2768n == 2 && c1469v.f3392z) {
                optJSONObject = c1469v.f3390x.optJSONObject("confirmation");
                if (optJSONObject != null) {
                    this.f3358i.m3706a(optJSONObject.optString("text"), C1403e.m3294a(optJSONObject.optString(ParametersKeys.COLOR)));
                }
            }
            if (c1469v.e.f2768n == 2 && c1469v.f3366B) {
                optJSONObject2 = C1377e.m3129a(c1469v.f3390x, "post-video-reward-toaster");
                int i = (optJSONObject2 == null || !optJSONObject2.optString(ParametersKeys.POSITION).equals("inside-top")) ? 1 : 0;
                this.f3360k.m3640a(i);
                this.f3360k.m3695a(optJSONObject2 != null ? optJSONObject2.optString("text") : "");
                if (c1469v.f3374J.m3155d()) {
                    this.f3360k.m3694a(c1469v.f3376L);
                }
            }
            optJSONObject2 = c1469v.m3309g();
            if (optJSONObject2 == null || optJSONObject2.isNull("video-click-button")) {
                this.f3357h.m3356d();
            }
            this.f3357h.m3357d(c1469v.f3390x.optBoolean("video-progress-timer-enabled"));
            if (c1469v.f3379O || c1469v.f3378N) {
                this.f.setVisibility(4);
            }
            String[] strArr = new String[1];
            strArr[0] = CBUtility.m3111a(c1469v.m3296a()) ? "video-portrait" : "video-landscape";
            optJSONObject = C1377e.m3129a(optJSONObject2, strArr);
            c1469v.f3387u = optJSONObject != null ? optJSONObject.optString("id") : "";
            if (c1469v.f3387u.isEmpty()) {
                c1469v.m3298a(CBImpressionError.VIDEO_ID_MISSING);
                return;
            }
            if (c1469v.f3386t == null) {
                c1469v.f3386t = c1469v.f3383q.m3132a(c1469v.f3387u);
            }
            if (c1469v.f3386t == null) {
                c1469v.m3298a(CBImpressionError.VIDEO_UNAVAILABLE);
            } else {
                this.f3357h.m3349a(c1469v.f3386t);
            }
        }

        /* renamed from: c */
        protected void mo4320c() {
            super.mo4320c();
            if (this.f3361l.f3384r != 0 || (this.f3361l.f3392z && !this.f3361l.m3685o())) {
                m3660a(this.f3361l.f3384r, false);
            } else {
                m3666b(false);
            }
        }

        /* renamed from: e */
        public void m3671e() {
            m3668c(true);
            this.f3357h.m3361h();
            C1469v c1469v = this.f3361l;
            c1469v.f3385s++;
            if (this.f3361l.f3385s <= 1 && !this.f3361l.m3691u() && this.f3361l.f3388v >= 1) {
                this.f3361l.e.m3179e();
            }
        }

        /* renamed from: a */
        protected void mo4296a(int i, int i2) {
            super.mo4296a(i, i2);
            m3660a(this.f3361l.f3384r, false);
            boolean a = CBUtility.m3111a(this.f3361l.m3296a());
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
            LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -1);
            LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, -1);
            RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) this.c.getLayoutParams();
            this.f3361l.m3653a(layoutParams2, a ? this.f3361l.f3370F : this.f3361l.f3369E, 1.0f);
            Point b = this.f3361l.m3655b(a ? "replay-portrait" : "replay-landscape");
            int round = Math.round(((((float) layoutParams6.leftMargin) + (((float) layoutParams6.width) / 2.0f)) + ((float) b.x)) - (((float) layoutParams2.width) / 2.0f));
            int round2 = Math.round((((((float) layoutParams6.height) / 2.0f) + ((float) layoutParams6.topMargin)) + ((float) b.y)) - (((float) layoutParams2.height) / 2.0f));
            layoutParams2.leftMargin = Math.min(Math.max(0, round), i - layoutParams2.width);
            layoutParams2.topMargin = Math.min(Math.max(0, round2), i2 - layoutParams2.height);
            this.f3362m.bringToFront();
            if (a) {
                this.f3362m.m3342a(this.f3361l.f3370F);
            } else {
                this.f3362m.m3342a(this.f3361l.f3369E);
            }
            layoutParams6 = (RelativeLayout.LayoutParams) this.e.getLayoutParams();
            if (this.f3361l.m3689s()) {
                LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-2, -2);
                C1381h c1381h = a ? this.f3361l.l : this.f3361l.m;
                this.f3361l.m3653a(layoutParams7, c1381h, 1.0f);
                layoutParams7.leftMargin = 0;
                layoutParams7.topMargin = 0;
                layoutParams7.addRule(11);
                this.f3364o.setLayoutParams(layoutParams7);
                this.f3364o.m3342a(c1381h);
            } else {
                layoutParams3.width = layoutParams6.width;
                layoutParams3.height = layoutParams6.height;
                layoutParams3.leftMargin = layoutParams6.leftMargin;
                layoutParams3.topMargin = layoutParams6.topMargin;
                layoutParams4.width = layoutParams6.width;
                layoutParams4.height = layoutParams6.height;
                layoutParams4.leftMargin = layoutParams6.leftMargin;
                layoutParams4.topMargin = layoutParams6.topMargin;
            }
            layoutParams5.width = layoutParams6.width;
            layoutParams5.height = 72;
            layoutParams5.leftMargin = layoutParams6.leftMargin;
            layoutParams5.topMargin = (layoutParams6.height + layoutParams6.topMargin) - 72;
            if (this.f3361l.f3378N) {
                this.f3363n.setLayoutParams(layoutParams);
            }
            if (this.f3361l.e.f2768n == 2) {
                this.f3358i.setLayoutParams(layoutParams3);
            }
            this.f3357h.setLayoutParams(layoutParams4);
            this.f3359j.setLayoutParams(layoutParams5);
            this.f3362m.setLayoutParams(layoutParams2);
            if (this.f3361l.e.f2768n == 2) {
                this.f3358i.m3705a();
            }
            this.f3357h.m3347a();
        }

        /* renamed from: b */
        void m3666b(boolean z) {
            if (this.f3361l.f3384r != 1) {
                if (this.f3361l.f3392z) {
                    m3660a(0, z);
                    return;
                }
                m3660a(1, z);
                JSONObject a = C1377e.m3129a(this.f3361l.f3390x, "timer");
                if (this.f3361l.f3385s >= 1 || a == null || a.isNull("delay")) {
                    this.f3357h.m3353b(!this.f3361l.f3391y);
                } else {
                    String str = "InterstitialVideoViewProtocol";
                    String str2 = "controls starting %s, setting timer";
                    Object[] objArr = new Object[1];
                    objArr[0] = this.f3361l.f3391y ? String.VISIBLE : "hidden";
                    CBLogging.m3101c(str, String.format(str2, objArr));
                    this.f3357h.m3353b(this.f3361l.f3391y);
                    this.f3361l.m3297a(this.f3357h, new C14653(this), Math.round(a.optDouble("delay", 0.0d) * 1000.0d));
                }
                this.f3357h.m3358e();
                if (this.f3361l.f3385s <= 1) {
                    this.f3361l.e.m3180f();
                }
            }
        }

        /* renamed from: c */
        void m3668c(boolean z) {
            this.f3357h.m3359f();
            if (this.f3361l.f3384r == 1 && z) {
                if (this.f3361l.f3385s < 1 && this.f3361l.f3390x != null && !this.f3361l.f3390x.isNull("post-video-reward-toaster") && this.f3361l.f3366B && this.f3361l.f3374J.m3155d() && this.f3361l.f3375K.m3155d()) {
                    m3661e(true);
                }
                m3660a(2, true);
                if (CBUtility.m3111a(CBUtility.m3107a())) {
                    requestLayout();
                }
            }
        }

        /* renamed from: e */
        private void m3661e(boolean z) {
            if (z) {
                this.f3360k.m3641a(true);
            } else {
                this.f3360k.setVisibility(0);
            }
            this.f3361l.a.postDelayed(new C14664(this), 2500);
        }

        /* renamed from: a */
        private void m3660a(int i, boolean z) {
            boolean z2;
            boolean z3 = true;
            this.f3361l.f3384r = i;
            switch (i) {
                case 0:
                    this.f3361l.m3300a(!this.f3361l.m3689s(), this.e, z);
                    if (this.f3361l.e.f2768n == 2) {
                        this.f3361l.m3300a(true, this.f3358i, z);
                    }
                    if (this.f3361l.f3378N) {
                        this.f3361l.m3300a(false, this.f3363n, z);
                    }
                    this.f3361l.m3300a(false, this.f3357h, z);
                    this.f3361l.m3300a(false, this.f3362m, z);
                    this.f3361l.m3300a(false, this.f3359j, z);
                    this.e.setEnabled(false);
                    this.f3362m.setEnabled(false);
                    this.f3357h.setEnabled(false);
                    break;
                case 1:
                    this.f3361l.m3300a(false, this.e, z);
                    if (this.f3361l.e.f2768n == 2) {
                        this.f3361l.m3300a(false, this.f3358i, z);
                    }
                    if (this.f3361l.f3378N) {
                        this.f3361l.m3300a(true, this.f3363n, z);
                    }
                    this.f3361l.m3300a(true, this.f3357h, z);
                    this.f3361l.m3300a(false, this.f3362m, z);
                    this.f3361l.m3300a(false, this.f3359j, z);
                    this.e.setEnabled(true);
                    this.f3362m.setEnabled(false);
                    this.f3357h.setEnabled(true);
                    break;
                case 2:
                    this.f3361l.m3300a(true, this.e, z);
                    if (this.f3361l.e.f2768n == 2) {
                        this.f3361l.m3300a(false, this.f3358i, z);
                    }
                    if (this.f3361l.f3378N) {
                        this.f3361l.m3300a(false, this.f3363n, z);
                    }
                    this.f3361l.m3300a(false, this.f3357h, z);
                    this.f3361l.m3300a(true, this.f3362m, z);
                    z2 = this.f3361l.f3375K.m3155d() && this.f3361l.f3374J.m3155d() && this.f3361l.f3365A;
                    this.f3361l.m3300a(z2, this.f3359j, z);
                    this.f3362m.setEnabled(true);
                    this.e.setEnabled(true);
                    this.f3357h.setEnabled(false);
                    if (this.f3361l.f3367C) {
                        m3661e(false);
                        break;
                    }
                    break;
            }
            z2 = m3672f();
            View d = m3669d(true);
            d.setEnabled(z2);
            this.f3361l.m3300a(z2, d, z);
            View d2 = m3669d(false);
            d2.setEnabled(false);
            this.f3361l.m3300a(false, d2, z);
            if (this.f3361l.f3379O || this.f3361l.f3378N) {
                this.f3361l.m3300a(!this.f3361l.m3689s(), this.f, z);
            }
            C1469v c1469v = this.f3361l;
            if (this.f3361l.m3689s()) {
                z2 = false;
            } else {
                z2 = true;
            }
            c1469v.m3300a(z2, this.c, z);
            if (i == 0) {
                z3 = false;
            }
            m3291a(z3);
        }

        /* renamed from: f */
        protected boolean m3672f() {
            if (this.f3361l.f3384r == 1 && this.f3361l.f3385s < 1) {
                String str = "close-" + (CBUtility.m3111a(this.f3361l.m3296a()) ? "portrait" : "landscape");
                JSONObject a = C1377e.m3129a(this.f3361l.m3309g(), str);
                float optDouble = a != null ? (float) a.optDouble("delay", -1.0d) : -1.0f;
                int round = optDouble >= 0.0f ? Math.round(optDouble * 1000.0f) : -1;
                this.f3361l.f3368D = round;
                if (round < 0) {
                    return false;
                }
                if (round > this.f3357h.m3352b().mo4294d()) {
                    return false;
                }
            }
            return true;
        }

        /* renamed from: b */
        public void mo4318b() {
            this.f3361l.mo4306n();
            super.mo4318b();
        }

        /* renamed from: d */
        protected void mo4321d() {
            if (this.f3361l.f3384r != 1 || this.f3361l.e.f2755a.f3209a != 1) {
                if (this.f3361l.f3384r == 1) {
                    m3668c(false);
                    this.f3357h.m3361h();
                    if (this.f3361l.f3385s < 1) {
                        C1469v c1469v = this.f3361l;
                        c1469v.f3385s++;
                        this.f3361l.e.m3179e();
                    }
                }
                this.f3361l.a.post(new C14675(this));
            }
        }

        /* renamed from: a */
        protected void mo4319a(float f, float f2, float f3, float f4) {
            if ((!this.f3361l.f3391y || this.f3361l.f3384r != 1) && this.f3361l.f3384r != 0) {
                m3665b(f, f2, f3, f4);
            }
        }

        /* renamed from: b */
        protected void m3665b(float f, float f2, float f3, float f4) {
            if (this.f3361l.f3384r == 1) {
                m3668c(false);
            }
            this.f3361l.m3304b(C1377e.m3130a(C1377e.m3128a("x", Float.valueOf(f)), C1377e.m3128a("y", Float.valueOf(f2)), C1377e.m3128a("w", Float.valueOf(f3)), C1377e.m3128a("h", Float.valueOf(f4))));
        }

        /* renamed from: g */
        protected void m3673g() {
            this.f3361l.f3392z = false;
            m3666b(true);
        }

        /* renamed from: d */
        public az m3669d(boolean z) {
            return (!(this.f3361l.m3689s() && z) && (this.f3361l.m3689s() || z)) ? this.d : this.f3364o;
        }
    }

    /* renamed from: e */
    public /* synthetic */ C1402a mo4300e() {
        return m3687q();
    }

    public C1469v(C1388c c1388c, C1378f c1378f, Handler handler, C1397c c1397c) {
        super(c1388c, handler, c1397c);
        this.f3383q = c1378f;
        this.f3384r = 0;
        this.f3369E = new C1381h(this);
        this.f3370F = new C1381h(this);
        this.f3371G = new C1381h(this);
        this.f3372H = new C1381h(this);
        this.f3373I = new C1381h(this);
        this.f3374J = new C1381h(this);
        this.f3375K = new C1381h(this);
        this.f3376L = new C1381h(this);
        this.f3385s = 0;
    }

    /* renamed from: o */
    public boolean m3685o() {
        return this.e.f2768n == 1;
    }

    /* renamed from: b */
    protected C1402a mo4298b(Context context) {
        return new C1468a(context);
    }

    /* renamed from: l */
    public boolean mo4304l() {
        m3687q().mo4321d();
        return true;
    }

    /* renamed from: m */
    public void mo4305m() {
        super.mo4305m();
        if (this.f3384r == 1 && this.f3380P) {
            m3687q().f3357h.m3352b().mo4286a(this.f3388v);
            m3687q().f3357h.m3358e();
        }
        this.f3380P = false;
    }

    /* renamed from: n */
    public void mo4306n() {
        super.mo4306n();
        if (this.f3384r == 1 && !this.f3380P) {
            this.f3380P = true;
            m3687q().f3357h.m3360g();
        }
    }

    /* renamed from: a */
    public boolean mo4297a(JSONObject jSONObject) {
        if (!super.mo4297a(jSONObject)) {
            return false;
        }
        this.f3390x = jSONObject.optJSONObject("ux");
        if (this.f3390x == null) {
            this.f3390x = C1377e.m3130a(new C1376a[0]);
        }
        if (this.d.isNull("video-landscape") || this.d.isNull("replay-landscape")) {
            this.i = false;
        }
        if (this.f3369E.m3151a("replay-landscape") && this.f3370F.m3151a("replay-portrait") && this.f3373I.m3151a("video-click-button") && this.f3374J.m3151a("post-video-reward-icon") && this.f3375K.m3151a("post-video-button") && this.f3371G.m3151a("video-confirmation-button") && this.f3372H.m3151a("video-confirmation-icon") && this.f3376L.m3151a("post-video-reward-icon")) {
            this.f3391y = this.f3390x.optBoolean("video-controls-togglable");
            this.f3378N = jSONObject.optBoolean(Abstract.FULL_SCREEN);
            this.f3379O = jSONObject.optBoolean("preroll_popup_fullscreen");
            if (this.e.f2768n == 2) {
                JSONObject optJSONObject = this.f3390x.optJSONObject("confirmation");
                JSONObject optJSONObject2 = this.f3390x.optJSONObject("post-video-toaster");
                if (!(optJSONObject2 == null || optJSONObject2.isNull("title") || optJSONObject2.isNull("tagline"))) {
                    this.f3365A = true;
                }
                if (!(optJSONObject == null || optJSONObject.isNull("text") || optJSONObject.isNull(ParametersKeys.COLOR))) {
                    this.f3392z = true;
                }
                if (!this.f3390x.isNull("post-video-reward-toaster")) {
                    this.f3366B = true;
                }
            }
            return true;
        }
        CBLogging.m3099b("InterstitialVideoViewProtocol", "Error while downloading the assets");
        m3298a(CBImpressionError.ASSETS_DOWNLOAD_FAILURE);
        return false;
    }

    /* renamed from: i */
    protected void mo4322i() {
        if (this.f3392z && !(this.f3371G.m3155d() && this.f3372H.m3155d())) {
            this.f3392z = false;
        }
        super.mo4322i();
    }

    /* renamed from: d */
    public void mo4299d() {
        super.mo4299d();
        this.f3369E.m3154c();
        this.f3370F.m3154c();
        this.f3373I.m3154c();
        this.f3374J.m3154c();
        this.f3375K.m3154c();
        this.f3371G.m3154c();
        this.f3372H.m3154c();
        this.f3376L.m3154c();
        this.f3369E = null;
        this.f3370F = null;
        this.f3373I = null;
        this.f3374J = null;
        this.f3375K = null;
        this.f3371G = null;
        this.f3372H = null;
        this.f3376L = null;
    }

    /* renamed from: p */
    public boolean m3686p() {
        return this.f3384r == 1;
    }

    /* renamed from: q */
    public C1468a m3687q() {
        return (C1468a) super.mo4300e();
    }

    /* renamed from: r */
    protected void m3688r() {
        this.e.m3190p();
    }

    /* renamed from: s */
    protected boolean m3689s() {
        switch (this.f3384r) {
            case 0:
                if (this.f3379O || CBUtility.m3111a(CBUtility.m3107a())) {
                    return true;
                }
                return false;
            case 1:
                if (this.f3378N || CBUtility.m3111a(CBUtility.m3107a())) {
                    return true;
                }
                return false;
            case 2:
                return false;
            default:
                return true;
        }
    }

    /* renamed from: t */
    public boolean m3690t() {
        return this.f3381Q;
    }

    /* renamed from: a */
    public void m3674a(boolean z) {
        this.f3381Q = z;
    }

    /* renamed from: u */
    public boolean m3691u() {
        return this.f3382R;
    }

    /* renamed from: v */
    public void m3692v() {
        if (this.f3386t != null) {
            new File(this.f3386t).delete();
        }
        this.f3382R = true;
        m3298a(CBImpressionError.ERROR_PLAYING_VIDEO);
    }

    /* renamed from: j */
    public float mo4302j() {
        return (float) this.f3389w;
    }

    /* renamed from: k */
    public float mo4303k() {
        return (float) this.f3388v;
    }
}
