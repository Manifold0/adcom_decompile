package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chartboost.sdk.C1405g;
import com.chartboost.sdk.Libraries.C1377e;
import com.chartboost.sdk.Libraries.C1381h;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.impl.C1469v.C1468a;
import com.chartboost.sdk.impl.av.C1421a;
import java.util.Locale;
import org.json.JSONObject;

@SuppressLint({"ViewConstructor"})
public class ab extends RelativeLayout implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    /* renamed from: k */
    private static final CharSequence f2965k = "00:00";
    /* renamed from: a */
    final RelativeLayout f2966a;
    /* renamed from: b */
    final aa f2967b;
    /* renamed from: c */
    final aa f2968c;
    /* renamed from: d */
    final az f2969d;
    /* renamed from: e */
    final TextView f2970e;
    /* renamed from: f */
    final C1471x f2971f;
    /* renamed from: g */
    final av f2972g;
    /* renamed from: h */
    final C1469v f2973h;
    /* renamed from: i */
    final Handler f2974i;
    /* renamed from: j */
    final Runnable f2975j = new C14154(this);
    /* renamed from: l */
    private boolean f2976l = false;
    /* renamed from: m */
    private boolean f2977m = false;
    /* renamed from: n */
    private final Runnable f2978n = new C14132(this);
    /* renamed from: o */
    private final Runnable f2979o = new C14143(this);

    /* renamed from: com.chartboost.sdk.impl.ab$2 */
    class C14132 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ ab f2960a;

        C14132(ab abVar) {
            this.f2960a = abVar;
        }

        public void run() {
            this.f2960a.m3350a(false);
        }
    }

    /* renamed from: com.chartboost.sdk.impl.ab$3 */
    class C14143 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ ab f2961a;

        C14143(ab abVar) {
            this.f2961a = abVar;
        }

        public void run() {
            if (this.f2961a.f2967b != null) {
                this.f2961a.f2967b.setVisibility(8);
            }
            if (this.f2961a.f2973h.f3377M) {
                this.f2961a.f2971f.setVisibility(8);
            }
            this.f2961a.f2968c.setVisibility(8);
            if (this.f2961a.f2969d != null) {
                this.f2961a.f2969d.setEnabled(false);
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.ab$4 */
    class C14154 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ ab f2962a;
        /* renamed from: b */
        private int f2963b = 0;

        C14154(ab abVar) {
            this.f2962a = abVar;
        }

        public void run() {
            C1468a q = this.f2962a.f2973h.m3687q();
            if (q != null) {
                if (this.f2962a.f2972g.m3461a().mo4295e()) {
                    int d = this.f2962a.f2972g.m3461a().mo4294d();
                    if (d > 0) {
                        this.f2962a.f2973h.f3388v = d;
                        if (((float) this.f2962a.f2973h.f3388v) / 1000.0f > 0.0f && !this.f2962a.f2973h.m3690t()) {
                            this.f2962a.f2973h.m3688r();
                            this.f2962a.f2973h.m3674a(true);
                        }
                    }
                    float c = ((float) d) / ((float) this.f2962a.f2972g.m3461a().mo4293c());
                    if (this.f2962a.f2973h.f3377M) {
                        this.f2962a.f2971f.m3698a(c);
                    }
                    d /= 1000;
                    if (this.f2963b != d) {
                        this.f2963b = d;
                        int i = d / 60;
                        d %= 60;
                        this.f2962a.f2970e.setText(String.format(Locale.US, "%02d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(d)}));
                    }
                }
                if (q.m3672f()) {
                    View d2 = q.m3669d(true);
                    if (d2.getVisibility() == 8) {
                        this.f2962a.f2973h.m3299a(true, d2);
                        d2.setEnabled(true);
                    }
                }
                this.f2962a.f2974i.removeCallbacks(this.f2962a.f2975j);
                this.f2962a.f2974i.postDelayed(this.f2962a.f2975j, 16);
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.ab$5 */
    class C14165 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ ab f2964a;

        C14165(ab abVar) {
            this.f2964a = abVar;
        }

        public void run() {
            this.f2964a.f2972g.setVisibility(0);
        }
    }

    public ab(Context context, C1469v c1469v) {
        super(context);
        this.f2973h = c1469v;
        this.f2974i = c1469v.a;
        JSONObject g = c1469v.m3309g();
        float f = context.getResources().getDisplayMetrics().density;
        int round = Math.round(f * 10.0f);
        C1405g a = C1405g.m3317a();
        this.f2972g = (av) a.m3318a(new av(context));
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(13);
        addView(this.f2972g, layoutParams);
        this.f2966a = (RelativeLayout) a.m3318a(new RelativeLayout(context));
        if (g == null || g.isNull("video-click-button")) {
            this.f2967b = null;
            this.f2969d = null;
        } else {
            this.f2967b = (aa) a.m3318a(new aa(context));
            this.f2967b.setVisibility(8);
            this.f2969d = new az(this, context) {
                /* renamed from: a */
                final /* synthetic */ ab f2959a;

                /* renamed from: a */
                protected void mo4279a(MotionEvent motionEvent) {
                    this.f2959a.f2973h.m3304b(C1377e.m3130a(C1377e.m3128a("x", Float.valueOf(motionEvent.getX())), C1377e.m3128a("y", Float.valueOf(motionEvent.getY())), C1377e.m3128a("w", Integer.valueOf(this.f2959a.f2969d.getWidth())), C1377e.m3128a("h", Integer.valueOf(this.f2959a.f2969d.getHeight()))));
                }
            };
            this.f2969d.m3341a(ScaleType.FIT_CENTER);
            C1381h c1381h = c1469v.f3373I;
            Point b = c1469v.m3655b("video-click-button");
            LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams2.leftMargin = Math.round(((float) b.x) / c1381h.m3157f());
            layoutParams2.topMargin = Math.round(((float) b.y) / c1381h.m3157f());
            c1469v.m3653a(layoutParams2, c1381h, 1.0f);
            this.f2969d.m3342a(c1381h);
            this.f2967b.addView(this.f2969d, layoutParams2);
            layoutParams = new RelativeLayout.LayoutParams(-1, Math.round(((float) layoutParams2.height) + (10.0f * f)));
            layoutParams.addRule(10);
            this.f2966a.addView(this.f2967b, layoutParams);
        }
        this.f2968c = (aa) a.m3318a(new aa(context));
        this.f2968c.setVisibility(8);
        layoutParams = new RelativeLayout.LayoutParams(-1, Math.round(32.5f * f));
        layoutParams.addRule(12);
        this.f2966a.addView(this.f2968c, layoutParams);
        this.f2968c.setGravity(16);
        this.f2968c.setPadding(round, round, round, round);
        this.f2970e = (TextView) a.m3318a(new TextView(context));
        this.f2970e.setTextColor(-1);
        this.f2970e.setTextSize(2, 11.0f);
        this.f2970e.setText(f2965k);
        this.f2970e.setPadding(0, 0, round, 0);
        this.f2970e.setSingleLine();
        this.f2970e.measure(0, 0);
        int measuredWidth = this.f2970e.getMeasuredWidth();
        this.f2970e.setGravity(17);
        this.f2968c.addView(this.f2970e, new LinearLayout.LayoutParams(measuredWidth, -1));
        this.f2971f = (C1471x) a.m3318a(new C1471x(context));
        this.f2971f.setVisibility(8);
        layoutParams = new LinearLayout.LayoutParams(-1, Math.round(10.0f * f));
        layoutParams.setMargins(0, CBUtility.m3108a(1, context), 0, 0);
        this.f2968c.addView(this.f2971f, layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(6, this.f2972g.getId());
        layoutParams.addRule(8, this.f2972g.getId());
        layoutParams.addRule(5, this.f2972g.getId());
        layoutParams.addRule(7, this.f2972g.getId());
        addView(this.f2966a, layoutParams);
        m3347a();
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (this.f2969d != null) {
            this.f2969d.setEnabled(enabled);
        }
        if (enabled) {
            m3353b(false);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f2974i.removeCallbacks(this.f2975j);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent e) {
        if (!this.f2972g.m3461a().mo4295e() || e.getActionMasked() != 0) {
            return false;
        }
        if (this.f2973h == null) {
            return true;
        }
        m3350a(true);
        return true;
    }

    public void onCompletion(MediaPlayer arg0) {
        this.f2973h.f3388v = this.f2972g.m3461a().mo4293c();
        if (this.f2973h.m3687q() != null) {
            this.f2973h.m3687q().m3671e();
        }
    }

    public void onPrepared(MediaPlayer mp) {
        this.f2973h.f3389w = this.f2972g.m3461a().mo4293c();
        this.f2973h.m3687q().m3291a(true);
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        this.f2973h.m3692v();
        return false;
    }

    /* renamed from: a */
    void m3350a(boolean z) {
        m3351a(!this.f2976l, z);
    }

    /* renamed from: a */
    protected void m3351a(boolean z, boolean z2) {
        this.f2974i.removeCallbacks(this.f2978n);
        this.f2974i.removeCallbacks(this.f2979o);
        if (this.f2973h.f3391y && this.f2973h.m3686p() && z != this.f2976l) {
            this.f2976l = z;
            Animation alphaAnimation = this.f2976l ? new AlphaAnimation(0.0f, 1.0f) : new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(z2 ? 100 : 200);
            alphaAnimation.setFillAfter(true);
            if (!(this.f2977m || this.f2967b == null)) {
                this.f2967b.setVisibility(0);
                this.f2967b.startAnimation(alphaAnimation);
                if (this.f2969d != null) {
                    this.f2969d.setEnabled(true);
                }
            }
            if (this.f2973h.f3377M) {
                this.f2971f.setVisibility(0);
            }
            this.f2968c.setVisibility(0);
            this.f2968c.startAnimation(alphaAnimation);
            if (this.f2976l) {
                this.f2974i.postDelayed(this.f2978n, 3000);
            } else {
                this.f2974i.postDelayed(this.f2979o, alphaAnimation.getDuration());
            }
        }
    }

    /* renamed from: b */
    public void m3353b(boolean z) {
        this.f2974i.removeCallbacks(this.f2978n);
        this.f2974i.removeCallbacks(this.f2979o);
        if (z) {
            if (!(this.f2977m || this.f2967b == null)) {
                this.f2967b.setVisibility(0);
            }
            if (this.f2973h.f3377M) {
                this.f2971f.setVisibility(0);
            }
            this.f2968c.setVisibility(0);
            if (this.f2969d != null) {
                this.f2969d.setEnabled(true);
            }
        } else {
            if (this.f2967b != null) {
                this.f2967b.clearAnimation();
                this.f2967b.setVisibility(8);
            }
            this.f2968c.clearAnimation();
            if (this.f2973h.f3377M) {
                this.f2971f.setVisibility(8);
            }
            this.f2968c.setVisibility(8);
            if (this.f2969d != null) {
                this.f2969d.setEnabled(false);
            }
        }
        this.f2976l = z;
    }

    /* renamed from: c */
    public void m3355c(boolean z) {
        setBackgroundColor(z ? ViewCompat.MEASURED_STATE_MASK : 0);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        if (!z) {
            layoutParams.addRule(6, this.f2972g.getId());
            layoutParams.addRule(8, this.f2972g.getId());
            layoutParams.addRule(5, this.f2972g.getId());
            layoutParams.addRule(7, this.f2972g.getId());
        }
        this.f2966a.setLayoutParams(layoutParams);
        if (this.f2967b != null) {
            this.f2967b.setGravity(8388627);
            this.f2967b.requestLayout();
        }
    }

    /* renamed from: a */
    public void m3347a() {
        m3355c(CBUtility.m3111a(CBUtility.m3107a()));
    }

    /* renamed from: b */
    public C1421a m3352b() {
        return this.f2972g.m3461a();
    }

    /* renamed from: c */
    public C1471x m3354c() {
        return this.f2971f;
    }

    /* renamed from: a */
    public void m3348a(int i) {
        if (this.f2967b != null) {
            this.f2967b.setBackgroundColor(i);
        }
        this.f2968c.setBackgroundColor(i);
    }

    /* renamed from: d */
    public void m3356d() {
        if (this.f2967b != null) {
            this.f2967b.setVisibility(8);
        }
        this.f2977m = true;
        if (this.f2969d != null) {
            this.f2969d.setEnabled(false);
        }
    }

    /* renamed from: d */
    public void m3357d(boolean z) {
        this.f2970e.setVisibility(z ? 0 : 8);
    }

    /* renamed from: a */
    public void m3349a(String str) {
        this.f2972g.m3461a().mo4288a((OnCompletionListener) this);
        this.f2972g.m3461a().mo4289a((OnErrorListener) this);
        this.f2972g.m3461a().mo4290a((OnPreparedListener) this);
        this.f2972g.m3461a().mo4291a(Uri.parse(str));
    }

    /* renamed from: e */
    public void m3358e() {
        this.f2974i.postDelayed(new C14165(this), 500);
        this.f2972g.m3461a().mo4285a();
        this.f2974i.removeCallbacks(this.f2975j);
        this.f2974i.postDelayed(this.f2975j, 16);
    }

    /* renamed from: f */
    public void m3359f() {
        if (this.f2972g.m3461a().mo4295e()) {
            this.f2973h.f3388v = this.f2972g.m3461a().mo4294d();
            this.f2972g.m3461a().mo4292b();
        }
        if (this.f2973h.m3687q().e.getVisibility() == 0) {
            this.f2973h.m3687q().e.postInvalidate();
        }
        this.f2974i.removeCallbacks(this.f2975j);
    }

    /* renamed from: g */
    public void m3360g() {
        if (this.f2972g.m3461a().mo4295e()) {
            this.f2973h.f3388v = this.f2972g.m3461a().mo4294d();
        }
        this.f2972g.m3461a().mo4292b();
        this.f2974i.removeCallbacks(this.f2975j);
    }

    /* renamed from: h */
    public void m3361h() {
        this.f2972g.setVisibility(8);
        invalidate();
    }
}
