package com.facebook.ads.internal.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p027a.C1843c;
import com.facebook.ads.internal.view.C1921a;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.p019c.C2252d;
import com.facebook.ads.internal.view.p019c.C2253f;
import com.facebook.ads.internal.view.p022i.p023b.C2417u;
import com.facebook.ads.internal.view.p022i.p065a.C2389a;
import com.facebook.ads.internal.view.p022i.p065a.C2390b;
import com.facebook.ads.internal.view.p022i.p066d.C2501d;
import com.facebook.ads.internal.view.p022i.p067c.C2434a.C2433a;
import com.facebook.ads.internal.view.p022i.p067c.C2451d;
import com.facebook.ads.internal.view.p022i.p067c.C2451d.C2450a;
import com.facebook.ads.internal.view.p022i.p067c.C2459g;
import com.facebook.ads.internal.view.p022i.p067c.C2473j;
import com.facebook.ads.internal.view.p022i.p067c.C2475k;
import com.facebook.ads.internal.view.p022i.p067c.C2481l;
import com.facebook.ads.internal.view.p022i.p067c.C2486n;
import com.facebook.ads.internal.view.p061e.C2328a;
import com.facebook.places.model.PlaceFields;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.HashMap;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.adapters.h */
public class C1922h extends C1912f implements OnTouchListener, C1921a {
    /* renamed from: i */
    static final /* synthetic */ boolean f4105i = (!C1922h.class.desiredAssertionStatus());
    /* renamed from: j */
    private static final String f4106j = C1922h.class.getSimpleName();
    /* renamed from: A */
    private int f4107A = -10525069;
    /* renamed from: B */
    private int f4108B = -12286980;
    /* renamed from: C */
    private boolean f4109C = false;
    @Nullable
    /* renamed from: D */
    private C2389a f4110D;
    /* renamed from: f */
    final int f4111f = 64;
    /* renamed from: g */
    final int f4112g = 64;
    /* renamed from: h */
    final int f4113h = 16;
    @Nullable
    /* renamed from: k */
    private C1789a f4114k;
    @Nullable
    /* renamed from: l */
    private Activity f4115l;
    /* renamed from: m */
    private BackButtonInterceptor f4116m = new C19191(this);
    /* renamed from: n */
    private final OnTouchListener f4117n = new C19202(this);
    /* renamed from: o */
    private C2253f f4118o = C2253f.UNSPECIFIED;
    /* renamed from: p */
    private final C2598w f4119p = new C2598w();
    /* renamed from: q */
    private C2328a f4120q;
    /* renamed from: r */
    private TextView f4121r;
    /* renamed from: s */
    private TextView f4122s;
    /* renamed from: t */
    private ImageView f4123t;
    @Nullable
    /* renamed from: u */
    private C2433a f4124u;
    /* renamed from: v */
    private C2486n f4125v;
    /* renamed from: w */
    private ViewGroup f4126w;
    /* renamed from: x */
    private C2451d f4127x;
    /* renamed from: y */
    private C2473j f4128y;
    /* renamed from: z */
    private int f4129z = -1;

    /* renamed from: com.facebook.ads.internal.adapters.h$1 */
    class C19191 implements BackButtonInterceptor {
        /* renamed from: a */
        final /* synthetic */ C1922h f4103a;

        C19191(C1922h c1922h) {
            this.f4103a = c1922h;
        }

        public boolean interceptBackButton() {
            if (this.f4103a.f4128y == null) {
                return false;
            }
            if (!this.f4103a.f4128y.m6364a()) {
                return true;
            }
            if (!(this.f4103a.f4128y.getSkipSeconds() == 0 || this.f4103a.b == null)) {
                this.f4103a.b.m6164f();
            }
            if (this.f4103a.b == null) {
                return false;
            }
            this.f4103a.b.m6165g();
            return false;
        }
    }

    /* renamed from: com.facebook.ads.internal.adapters.h$2 */
    class C19202 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ C1922h f4104a;

        C19202(C1922h c1922h) {
            this.f4104a = c1922h;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                if (this.f4104a.f4128y == null) {
                    this.f4104a.f4115l.finish();
                } else if (this.f4104a.f4128y.m6364a()) {
                    if (!(this.f4104a.f4128y.getSkipSeconds() == 0 || this.f4104a.b == null)) {
                        this.f4104a.b.m6164f();
                    }
                    if (this.f4104a.b != null) {
                        this.f4104a.b.m6165g();
                    }
                    this.f4104a.f4115l.finish();
                }
            }
            return true;
        }
    }

    /* renamed from: a */
    private void m4470a(int i) {
        View rootView;
        float f = C2600x.f6420b;
        LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (56.0f * f), (int) (56.0f * f));
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        int i2 = (int) (16.0f * f);
        this.f4128y.setPadding(i2, i2, i2, i2);
        this.f4128y.setLayoutParams(layoutParams);
        C2450a c2450a = mo5407h() ? C2450a.FADE_OUT_ON_PLAY : C2450a.VISIBLE;
        int id = this.b.getId();
        if (i == 1) {
            if ((((double) (this.b.getVideoHeight() > 0 ? ((float) this.b.getVideoWidth()) / ((float) this.b.getVideoHeight()) : -1.0f)) <= 0.9d ? 1 : null) != null || m4475k()) {
                Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -15658735});
                gradientDrawable.setCornerRadius(0.0f);
                LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams2.addRule(10);
                this.b.setLayoutParams(layoutParams2);
                m4471a(this.b);
                m4471a(this.f4128y);
                if (this.f4124u != null) {
                    m4471a(this.f4124u);
                }
                LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, (int) (((float) (((((this.f4120q != null ? 64 : 0) + 60) + 16) + 16) + 16)) * f));
                layoutParams3.addRule(12);
                View relativeLayout = new RelativeLayout(this.d);
                C2600x.m6681a(relativeLayout, gradientDrawable);
                relativeLayout.setLayoutParams(layoutParams3);
                relativeLayout.setPadding(i2, 0, i2, (int) (((float) (((this.f4120q != null ? 64 : 0) + 16) + 16)) * f));
                this.f4126w = relativeLayout;
                if (!this.f4109C) {
                    this.f4127x.m6297a(relativeLayout, c2450a);
                }
                m4471a(relativeLayout);
                if (this.f4125v != null) {
                    layoutParams = new RelativeLayout.LayoutParams(-1, (int) (6.0f * f));
                    layoutParams.addRule(12);
                    layoutParams.topMargin = (int) (-6.0f * f);
                    this.f4125v.setLayoutParams(layoutParams);
                    m4471a(this.f4125v);
                }
                if (this.f4120q != null) {
                    layoutParams = new RelativeLayout.LayoutParams(-1, (int) (64.0f * f));
                    layoutParams.bottomMargin = (int) (16.0f * f);
                    layoutParams.leftMargin = (int) (16.0f * f);
                    layoutParams.rightMargin = (int) (16.0f * f);
                    layoutParams.addRule(1);
                    layoutParams.addRule(12);
                    this.f4120q.setLayoutParams(layoutParams);
                    m4471a(this.f4120q);
                }
                if (this.f4123t != null) {
                    layoutParams = new RelativeLayout.LayoutParams((int) (60.0f * f), (int) (60.0f * f));
                    layoutParams.addRule(12);
                    layoutParams.addRule(9);
                    this.f4123t.setLayoutParams(layoutParams);
                    m4472a(relativeLayout, this.f4123t);
                }
                if (this.f4121r != null) {
                    layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                    layoutParams.bottomMargin = (int) (36.0f * f);
                    layoutParams.addRule(12);
                    layoutParams.addRule(9);
                    this.f4121r.setEllipsize(TruncateAt.END);
                    this.f4121r.setGravity(GravityCompat.START);
                    this.f4121r.setLayoutParams(layoutParams);
                    this.f4121r.setMaxLines(1);
                    this.f4121r.setPadding((int) (72.0f * f), 0, 0, 0);
                    this.f4121r.setTextColor(-1);
                    this.f4121r.setTextSize(18.0f);
                    m4472a(relativeLayout, this.f4121r);
                }
                if (this.f4122s != null) {
                    layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                    layoutParams.addRule(12);
                    layoutParams.addRule(9);
                    layoutParams.bottomMargin = (int) (4.0f * f);
                    this.f4122s.setEllipsize(TruncateAt.END);
                    this.f4122s.setGravity(GravityCompat.START);
                    this.f4122s.setLayoutParams(layoutParams);
                    this.f4122s.setMaxLines(1);
                    this.f4122s.setPadding((int) (72.0f * f), 0, 0, 0);
                    this.f4122s.setTextColor(-1);
                    m4472a(relativeLayout, this.f4122s);
                }
                C2600x.m6680a((View) this.b.getParent(), (int) ViewCompat.MEASURED_STATE_MASK);
                rootView = this.b.getRootView();
                if (rootView != null) {
                    rootView.setOnTouchListener(this);
                }
            }
        }
        if (i == 1) {
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(10);
            this.b.setLayoutParams(layoutParams);
            m4471a(this.b);
            m4471a(this.f4128y);
            if (this.f4124u != null) {
                m4471a(this.f4124u);
            }
            rootView = new LinearLayout(this.d);
            this.f4126w = rootView;
            rootView.setGravity(112);
            rootView.setOrientation(1);
            layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams2.leftMargin = (int) (33.0f * f);
            layoutParams2.rightMargin = (int) (33.0f * f);
            layoutParams2.topMargin = (int) (8.0f * f);
            if (this.f4120q == null) {
                layoutParams2.bottomMargin = (int) (16.0f * f);
            } else {
                layoutParams2.bottomMargin = (int) (80.0f * f);
            }
            layoutParams2.addRule(3, id);
            rootView.setLayoutParams(layoutParams2);
            m4471a(rootView);
            if (this.f4120q != null) {
                layoutParams2 = new RelativeLayout.LayoutParams(-1, (int) (64.0f * f));
                layoutParams2.bottomMargin = (int) (16.0f * f);
                layoutParams2.leftMargin = (int) (33.0f * f);
                layoutParams2.rightMargin = (int) (33.0f * f);
                layoutParams2.addRule(1);
                layoutParams2.addRule(12);
                this.f4120q.setLayoutParams(layoutParams2);
                m4471a(this.f4120q);
            }
            if (this.f4121r != null) {
                layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                layoutParams2.weight = 2.0f;
                layoutParams2.gravity = 17;
                this.f4121r.setEllipsize(TruncateAt.END);
                this.f4121r.setGravity(17);
                this.f4121r.setLayoutParams(layoutParams2);
                this.f4121r.setMaxLines(2);
                this.f4121r.setPadding(0, 0, 0, 0);
                this.f4121r.setTextColor(this.f4107A);
                this.f4121r.setTextSize(24.0f);
                m4472a(rootView, this.f4121r);
            }
            if (this.f4123t != null) {
                layoutParams2 = new LinearLayout.LayoutParams((int) (64.0f * f), (int) (64.0f * f));
                layoutParams2.weight = 0.0f;
                layoutParams2.gravity = 17;
                this.f4123t.setLayoutParams(layoutParams2);
                m4472a(rootView, this.f4123t);
            }
            if (this.f4122s != null) {
                layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams2.weight = 2.0f;
                layoutParams2.gravity = 16;
                this.f4122s.setEllipsize(TruncateAt.END);
                this.f4122s.setGravity(16);
                this.f4122s.setLayoutParams(layoutParams2);
                this.f4122s.setMaxLines(2);
                this.f4122s.setPadding(0, 0, 0, 0);
                this.f4122s.setTextColor(this.f4107A);
                m4472a(rootView, this.f4122s);
            }
            if (this.f4125v != null) {
                layoutParams = new RelativeLayout.LayoutParams(-1, (int) (f * 6.0f));
                layoutParams.addRule(3, id);
                this.f4125v.setLayoutParams(layoutParams);
                m4471a(this.f4125v);
            }
            C2600x.m6680a((View) this.b.getParent(), this.f4129z);
        } else {
            float videoWidth = this.b.getVideoHeight() > 0 ? ((float) this.b.getVideoWidth()) / ((float) this.b.getVideoHeight()) : -1.0f;
            Object obj = (((double) videoWidth) <= 0.9d || ((double) videoWidth) >= 1.1d) ? null : 1;
            if (obj == null || m4475k()) {
                Drawable gradientDrawable2 = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -15658735});
                gradientDrawable2.setCornerRadius(0.0f);
                this.b.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                m4471a(this.b);
                m4471a(this.f4128y);
                if (this.f4124u != null) {
                    m4471a(this.f4124u);
                }
                LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, (int) (124.0f * f));
                layoutParams4.addRule(12);
                View relativeLayout2 = new RelativeLayout(this.d);
                C2600x.m6681a(relativeLayout2, gradientDrawable2);
                relativeLayout2.setLayoutParams(layoutParams4);
                relativeLayout2.setPadding(i2, 0, i2, i2);
                this.f4126w = relativeLayout2;
                if (!this.f4109C) {
                    this.f4127x.m6297a(relativeLayout2, c2450a);
                }
                m4471a(relativeLayout2);
                if (this.f4120q != null) {
                    layoutParams = new RelativeLayout.LayoutParams((int) (110.0f * f), (int) (56.0f * f));
                    layoutParams.rightMargin = (int) (16.0f * f);
                    layoutParams.bottomMargin = (int) (16.0f * f);
                    layoutParams.addRule(12);
                    layoutParams.addRule(11);
                    this.f4120q.setLayoutParams(layoutParams);
                    m4471a(this.f4120q);
                }
                if (this.f4123t != null) {
                    layoutParams = new RelativeLayout.LayoutParams((int) (64.0f * f), (int) (64.0f * f));
                    layoutParams.addRule(12);
                    layoutParams.addRule(9);
                    layoutParams.bottomMargin = (int) (8.0f * f);
                    this.f4123t.setLayoutParams(layoutParams);
                    m4472a(relativeLayout2, this.f4123t);
                }
                if (this.f4121r != null) {
                    layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                    layoutParams.bottomMargin = (int) (48.0f * f);
                    layoutParams.addRule(12);
                    layoutParams.addRule(9);
                    this.f4121r.setEllipsize(TruncateAt.END);
                    this.f4121r.setGravity(GravityCompat.START);
                    this.f4121r.setLayoutParams(layoutParams);
                    this.f4121r.setMaxLines(1);
                    this.f4121r.setPadding((int) (80.0f * f), 0, this.f4120q != null ? (int) (126.0f * f) : 0, 0);
                    this.f4121r.setTextColor(-1);
                    this.f4121r.setTextSize(24.0f);
                    m4472a(relativeLayout2, this.f4121r);
                }
                if (this.f4122s != null) {
                    layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                    layoutParams.addRule(12);
                    layoutParams.addRule(9);
                    this.f4122s.setEllipsize(TruncateAt.END);
                    this.f4122s.setGravity(GravityCompat.START);
                    this.f4122s.setLayoutParams(layoutParams);
                    this.f4122s.setMaxLines(2);
                    this.f4122s.setTextColor(-1);
                    this.f4122s.setPadding((int) (80.0f * f), 0, this.f4120q != null ? (int) (126.0f * f) : 0, 0);
                    m4472a(relativeLayout2, this.f4122s);
                }
                if (this.f4125v != null) {
                    layoutParams = new RelativeLayout.LayoutParams(-1, (int) (f * 6.0f));
                    layoutParams.addRule(12);
                    this.f4125v.setLayoutParams(layoutParams);
                    m4471a(this.f4125v);
                }
                C2600x.m6680a((View) this.b.getParent(), (int) ViewCompat.MEASURED_STATE_MASK);
            } else {
                layoutParams = new RelativeLayout.LayoutParams(-2, -1);
                layoutParams.addRule(9);
                this.b.setLayoutParams(layoutParams);
                m4471a(this.b);
                m4471a(this.f4128y);
                if (this.f4124u != null) {
                    m4471a(this.f4124u);
                }
                rootView = new LinearLayout(this.d);
                this.f4126w = rootView;
                rootView.setGravity(112);
                rootView.setOrientation(1);
                layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams2.leftMargin = (int) (16.0f * f);
                layoutParams2.rightMargin = (int) (16.0f * f);
                layoutParams2.topMargin = (int) (8.0f * f);
                layoutParams2.bottomMargin = (int) (80.0f * f);
                layoutParams2.addRule(1, id);
                rootView.setLayoutParams(layoutParams2);
                m4471a(rootView);
                if (this.f4125v != null) {
                    layoutParams2 = new RelativeLayout.LayoutParams(-1, (int) (6.0f * f));
                    layoutParams2.addRule(5, id);
                    layoutParams2.addRule(7, id);
                    layoutParams2.addRule(3, id);
                    layoutParams2.topMargin = (int) (-6.0f * f);
                    this.f4125v.setLayoutParams(layoutParams2);
                    m4471a(this.f4125v);
                }
                if (this.f4121r != null) {
                    layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams2.weight = 2.0f;
                    layoutParams2.gravity = 17;
                    this.f4121r.setEllipsize(TruncateAt.END);
                    this.f4121r.setGravity(17);
                    this.f4121r.setLayoutParams(layoutParams2);
                    this.f4121r.setMaxLines(10);
                    this.f4121r.setPadding(0, 0, 0, 0);
                    this.f4121r.setTextColor(this.f4107A);
                    this.f4121r.setTextSize(24.0f);
                    m4472a(rootView, this.f4121r);
                }
                if (this.f4123t != null) {
                    layoutParams2 = new LinearLayout.LayoutParams((int) (64.0f * f), (int) (64.0f * f));
                    layoutParams2.weight = 0.0f;
                    layoutParams2.gravity = 17;
                    this.f4123t.setLayoutParams(layoutParams2);
                    m4472a(rootView, this.f4123t);
                }
                if (this.f4122s != null) {
                    layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                    layoutParams2.weight = 2.0f;
                    layoutParams2.gravity = 16;
                    this.f4122s.setEllipsize(TruncateAt.END);
                    this.f4122s.setGravity(17);
                    this.f4122s.setLayoutParams(layoutParams2);
                    this.f4122s.setMaxLines(10);
                    this.f4122s.setPadding(0, 0, 0, 0);
                    this.f4122s.setTextColor(this.f4107A);
                    m4472a(rootView, this.f4122s);
                }
                if (this.f4120q != null) {
                    layoutParams = new RelativeLayout.LayoutParams(-1, (int) (64.0f * f));
                    layoutParams.bottomMargin = (int) (16.0f * f);
                    layoutParams.leftMargin = (int) (16.0f * f);
                    layoutParams.rightMargin = (int) (16.0f * f);
                    layoutParams.addRule(1);
                    layoutParams.addRule(12);
                    layoutParams.addRule(1, id);
                    this.f4120q.setLayoutParams(layoutParams);
                    m4471a(this.f4120q);
                }
                C2600x.m6680a((View) this.b.getParent(), this.f4129z);
            }
        }
        rootView = this.b.getRootView();
        if (rootView != null) {
            rootView.setOnTouchListener(this);
        }
    }

    /* renamed from: a */
    private void m4471a(View view) {
        if (this.f4114k != null) {
            this.f4114k.mo5333a(view);
        }
    }

    /* renamed from: a */
    private void m4472a(@Nullable ViewGroup viewGroup, @Nullable View view) {
        if (viewGroup != null) {
            viewGroup.addView(view);
        }
    }

    /* renamed from: b */
    private void m4474b(View view) {
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
    }

    /* renamed from: k */
    private boolean m4475k() {
        if (this.b.getVideoHeight() <= 0) {
            return false;
        }
        Rect rect = new Rect();
        this.f4115l.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        if (rect.width() > rect.height()) {
            return ((float) (rect.width() - ((rect.height() * this.b.getVideoWidth()) / this.b.getVideoHeight()))) - (192.0f * C2600x.f6420b) < 0.0f;
        } else {
            return ((((float) (rect.height() - ((rect.width() * this.b.getVideoHeight()) / this.b.getVideoWidth()))) - (C2600x.f6420b * 64.0f)) - (C2600x.f6420b * 64.0f)) - (40.0f * C2600x.f6420b) < 0.0f;
        }
    }

    /* renamed from: l */
    private void m4476l() {
        m4474b(this.b);
        m4474b(this.f4120q);
        m4474b(this.f4121r);
        m4474b(this.f4122s);
        m4474b(this.f4123t);
        m4474b(this.f4125v);
        m4474b(this.f4126w);
        m4474b(this.f4128y);
        if (this.f4124u != null) {
            m4474b(this.f4124u);
        }
    }

    /* renamed from: a */
    protected void mo5402a() {
        if (this.c == null) {
            Log.e(f4106j, "Unable to add UI without a valid ad response.");
            return;
        }
        C2390b c2459g;
        String string = this.c.getString("ct");
        String optString = this.c.getJSONObject(PlaceFields.CONTEXT).optString("orientation");
        if (!optString.isEmpty()) {
            this.f4118o = C2253f.m5778a(Integer.parseInt(optString));
        }
        if (this.c.has("layout") && !this.c.isNull("layout")) {
            JSONObject jSONObject = this.c.getJSONObject("layout");
            this.f4129z = (int) jSONObject.optLong("bgColor", (long) this.f4129z);
            this.f4107A = (int) jSONObject.optLong("textColor", (long) this.f4107A);
            this.f4108B = (int) jSONObject.optLong("accentColor", (long) this.f4108B);
            this.f4109C = jSONObject.optBoolean("persistentAdDetails", this.f4109C);
        }
        JSONObject jSONObject2 = this.c.getJSONObject("text");
        this.b.setId(VERSION.SDK_INT >= 17 ? View.generateViewId() : C2600x.m6677a());
        int c = m4430c();
        Context context = this.d;
        if (c < 0) {
            c = 0;
        }
        this.f4128y = new C2473j(context, c, this.f4108B);
        this.f4128y.setOnTouchListener(this.f4117n);
        this.b.m6158a(this.f4128y);
        if (this.c.has("cta") && !this.c.isNull("cta")) {
            JSONObject jSONObject3 = this.c.getJSONObject("cta");
            this.f4120q = new C2328a(this.d, this.f4119p, jSONObject3.getString("url"), jSONObject3.getString("text"), this.f4108B, this.b, this.a, string);
            C1843c.m4142a(this.d, this.a, string, Uri.parse(jSONObject3.getString("url")), new HashMap());
        }
        if (this.c.has("icon") && !this.c.isNull("icon")) {
            jSONObject = this.c.getJSONObject("icon");
            this.f4123t = new ImageView(this.d);
            new C2252d(this.f4123t).m5772a((int) (C2600x.f6420b * 64.0f), (int) (C2600x.f6420b * 64.0f)).m5775a(jSONObject.getString("url"));
        }
        if (this.c.has(MessengerShareContentUtility.MEDIA_IMAGE) && !this.c.isNull(MessengerShareContentUtility.MEDIA_IMAGE)) {
            jSONObject = this.c.getJSONObject(MessengerShareContentUtility.MEDIA_IMAGE);
            c2459g = new C2459g(this.d);
            this.b.m6158a(c2459g);
            c2459g.setImage(jSONObject.getString("url"));
        }
        CharSequence optString2 = jSONObject2.optString("title");
        if (!optString2.isEmpty()) {
            this.f4121r = new TextView(this.d);
            this.f4121r.setText(optString2);
            this.f4121r.setTypeface(Typeface.defaultFromStyle(1));
        }
        optString2 = jSONObject2.optString(MessengerShareContentUtility.SUBTITLE);
        if (!optString2.isEmpty()) {
            this.f4122s = new TextView(this.d);
            this.f4122s.setText(optString2);
            this.f4122s.setTextSize(16.0f);
        }
        this.f4125v = new C2486n(this.d);
        this.b.m6158a(this.f4125v);
        Object d = m4431d();
        if (!TextUtils.isEmpty(d)) {
            this.f4124u = new C2433a(this.d, "AdChoices", d, new float[]{0.0f, 0.0f, 8.0f, 0.0f}, string);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(10);
            layoutParams.addRule(9);
            this.f4124u.setLayoutParams(layoutParams);
        }
        this.b.m6158a(new C2475k(this.d));
        c2459g = new C2481l(this.d);
        this.b.m6158a(c2459g);
        C2450a c2450a = mo5407h() ? C2450a.FADE_OUT_ON_PLAY : C2450a.VISIBLE;
        this.b.m6158a(new C2451d(c2459g, c2450a));
        this.f4127x = new C2451d(new RelativeLayout(this.d), c2450a);
        this.b.m6158a(this.f4127x);
    }

    @TargetApi(17)
    /* renamed from: a */
    public void mo5403a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        this.f4115l = audienceNetworkActivity;
        if (f4105i || this.f4114k != null) {
            audienceNetworkActivity.addBackButtonInterceptor(this.f4116m);
            m4476l();
            m4470a(this.f4115l.getResources().getConfiguration().orientation);
            if (mo5407h()) {
                mo5397e();
                return;
            } else {
                m4433f();
                return;
            }
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public void m4479a(Configuration configuration) {
        m4476l();
        m4470a(configuration.orientation);
    }

    /* renamed from: a */
    public void mo5404a(Bundle bundle) {
    }

    public void a_(boolean z) {
        if (this.b != null && this.b.getState() == C2501d.STARTED) {
            this.f4110D = this.b.getVideoStartReason();
            this.b.m6160a(false);
        }
    }

    /* renamed from: b */
    public void mo5406b(boolean z) {
        if (this.b != null && this.f4110D != null) {
            this.b.m6157a(this.f4110D);
        }
    }

    /* renamed from: h */
    protected boolean mo5407h() {
        if (f4105i || this.c != null) {
            try {
                return this.c.getJSONObject("video").getBoolean(AudienceNetworkActivity.AUTOPLAY);
            } catch (Throwable e) {
                Log.w(String.valueOf(C1922h.class), "Invalid JSON", e);
                return true;
            }
        }
        throw new AssertionError();
    }

    /* renamed from: i */
    public C2253f m4483i() {
        return this.f4118o;
    }

    /* renamed from: j */
    public void m4484j() {
        if (this.f4115l != null) {
            this.f4115l.finish();
        }
    }

    public void onDestroy() {
        if (!(this.c == null || this.a == null)) {
            Object optString = this.c.optString("ct");
            if (!TextUtils.isEmpty(optString)) {
                this.a.mo5483l(optString, new HashMap());
            }
        }
        if (this.b != null) {
            this.b.m6165g();
        }
        C1918g.m4457a((C1921a) this);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.f4119p.m6671a(motionEvent, view.getRootView(), view);
        if (this.b != null) {
            this.b.getEventBus().m5028a(new C2417u(view, motionEvent));
        }
        return true;
    }

    public void setListener(C1789a c1789a) {
        this.f4114k = c1789a;
    }
}
