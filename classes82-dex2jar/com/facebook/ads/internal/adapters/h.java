// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.view.i.b.u;
import android.content.res.Configuration;
import android.annotation.TargetApi;
import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import org.json.JSONObject;
import com.facebook.ads.internal.view.i.c.l;
import com.facebook.ads.internal.view.i.c.k;
import android.text.TextUtils;
import android.graphics.Typeface;
import com.facebook.ads.internal.view.i.c.g;
import java.util.Map;
import com.facebook.ads.internal.a.c;
import java.util.HashMap;
import android.net.Uri;
import com.facebook.ads.internal.view.i.a.b;
import android.os.Build$VERSION;
import android.util.Log;
import android.graphics.Rect;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.text.TextUtils$TruncateAt;
import android.graphics.drawable.Drawable;
import android.widget.RelativeLayout;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable$Orientation;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.facebook.ads.internal.w.b.x;
import android.view.MotionEvent;
import android.view.View;
import com.facebook.ads.internal.view.i.c.j;
import com.facebook.ads.internal.view.i.c.d;
import android.view.ViewGroup;
import com.facebook.ads.internal.view.i.c.n;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.AudienceNetworkActivity;
import android.app.Activity;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.view.a;
import android.view.View$OnTouchListener;

public class h extends f implements View$OnTouchListener, a
{
    static final /* synthetic */ boolean i;
    private static final String j;
    private int A;
    private int B;
    private boolean C;
    @Nullable
    private com.facebook.ads.internal.view.i.a.a D;
    final int f;
    final int g;
    final int h;
    @Nullable
    private a k;
    @Nullable
    private Activity l;
    private AudienceNetworkActivity.BackButtonInterceptor m;
    private final View$OnTouchListener n;
    private com.facebook.ads.internal.view.c.f o;
    private final w p;
    private com.facebook.ads.internal.view.e.a q;
    private TextView r;
    private TextView s;
    private ImageView t;
    @Nullable
    private a.a u;
    private com.facebook.ads.internal.view.i.c.n v;
    private ViewGroup w;
    private d x;
    private j y;
    private int z;
    
    static {
        i = !h.class.desiredAssertionStatus();
        j = h.class.getSimpleName();
    }
    
    public h() {
        this.f = 64;
        this.g = 64;
        this.h = 16;
        this.m = new AudienceNetworkActivity.BackButtonInterceptor() {
            @Override
            public boolean interceptBackButton() {
                if (h.this.y != null) {
                    if (!h.this.y.a()) {
                        return true;
                    }
                    if (h.this.y.getSkipSeconds() != 0 && h.this.b != null) {
                        h.this.b.f();
                    }
                    if (h.this.b != null) {
                        h.this.b.g();
                        return false;
                    }
                }
                return false;
            }
        };
        this.n = (View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    if (com.facebook.ads.internal.adapters.h.this.y == null) {
                        com.facebook.ads.internal.adapters.h.this.l.finish();
                        return true;
                    }
                    if (com.facebook.ads.internal.adapters.h.this.y.a()) {
                        if (com.facebook.ads.internal.adapters.h.this.y.getSkipSeconds() != 0 && com.facebook.ads.internal.adapters.h.this.b != null) {
                            com.facebook.ads.internal.adapters.h.this.b.f();
                        }
                        if (com.facebook.ads.internal.adapters.h.this.b != null) {
                            com.facebook.ads.internal.adapters.h.this.b.g();
                        }
                        com.facebook.ads.internal.adapters.h.this.l.finish();
                        return true;
                    }
                }
                return true;
            }
        };
        this.o = com.facebook.ads.internal.view.c.f.a;
        this.p = new w();
        this.z = -1;
        this.A = -10525069;
        this.B = -12286980;
        this.C = false;
    }
    
    private void a(int n) {
        final float b = com.facebook.ads.internal.w.b.x.b;
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams((int)(56.0f * b), (int)(56.0f * b));
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        final int n2 = (int)(16.0f * b);
        this.y.setPadding(n2, n2, n2, n2);
        this.y.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        d.a a;
        if (this.h()) {
            a = com.facebook.ads.internal.view.i.c.d.a.c;
        }
        else {
            a = com.facebook.ads.internal.view.i.c.d.a.a;
        }
        final int id = this.b.getId();
        while (true) {
            Label_0871: {
                if (n != 1) {
                    break Label_0871;
                }
                float n3;
                if (this.b.getVideoHeight() > 0) {
                    n3 = this.b.getVideoWidth() / (float)this.b.getVideoHeight();
                }
                else {
                    n3 = -1.0f;
                }
                boolean b2;
                if (n3 <= 0.9) {
                    b2 = true;
                }
                else {
                    b2 = false;
                }
                if (!b2 && !this.k()) {
                    break Label_0871;
                }
                final GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable$Orientation.TOP_BOTTOM, new int[] { 0, -15658735 });
                gradientDrawable.setCornerRadius(0.0f);
                final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams(-1, -2);
                layoutParams2.addRule(10);
                this.b.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
                this.a((View)this.b);
                this.a(this.y);
                if (this.u != null) {
                    this.a((View)this.u);
                }
                if (this.q != null) {
                    n = 64;
                }
                else {
                    n = 0;
                }
                final RelativeLayout$LayoutParams layoutParams3 = new RelativeLayout$LayoutParams(-1, (int)((n + 60 + 16 + 16 + 16) * b));
                layoutParams3.addRule(12);
                final RelativeLayout w = new RelativeLayout(this.d);
                com.facebook.ads.internal.w.b.x.a((View)w, (Drawable)gradientDrawable);
                w.setLayoutParams((ViewGroup$LayoutParams)layoutParams3);
                if (this.q != null) {
                    n = 64;
                }
                else {
                    n = 0;
                }
                w.setPadding(n2, 0, n2, (int)((n + 16 + 16) * b));
                this.w = (ViewGroup)w;
                if (!this.C) {
                    this.x.a((View)w, a);
                }
                this.a((View)w);
                if (this.v != null) {
                    final RelativeLayout$LayoutParams layoutParams4 = new RelativeLayout$LayoutParams(-1, (int)(6.0f * b));
                    layoutParams4.addRule(12);
                    layoutParams4.topMargin = (int)(-6.0f * b);
                    this.v.setLayoutParams((ViewGroup$LayoutParams)layoutParams4);
                    this.a(this.v);
                }
                if (this.q != null) {
                    final RelativeLayout$LayoutParams layoutParams5 = new RelativeLayout$LayoutParams(-1, (int)(64.0f * b));
                    layoutParams5.bottomMargin = (int)(16.0f * b);
                    layoutParams5.leftMargin = (int)(16.0f * b);
                    layoutParams5.rightMargin = (int)(16.0f * b);
                    layoutParams5.addRule(1);
                    layoutParams5.addRule(12);
                    this.q.setLayoutParams((ViewGroup$LayoutParams)layoutParams5);
                    this.a((View)this.q);
                }
                if (this.t != null) {
                    final RelativeLayout$LayoutParams layoutParams6 = new RelativeLayout$LayoutParams((int)(60.0f * b), (int)(60.0f * b));
                    layoutParams6.addRule(12);
                    layoutParams6.addRule(9);
                    this.t.setLayoutParams((ViewGroup$LayoutParams)layoutParams6);
                    this.a((ViewGroup)w, (View)this.t);
                }
                if (this.r != null) {
                    final RelativeLayout$LayoutParams layoutParams7 = new RelativeLayout$LayoutParams(-1, -2);
                    layoutParams7.bottomMargin = (int)(36.0f * b);
                    layoutParams7.addRule(12);
                    layoutParams7.addRule(9);
                    this.r.setEllipsize(TextUtils$TruncateAt.END);
                    this.r.setGravity(8388611);
                    this.r.setLayoutParams((ViewGroup$LayoutParams)layoutParams7);
                    this.r.setMaxLines(1);
                    this.r.setPadding((int)(72.0f * b), 0, 0, 0);
                    this.r.setTextColor(-1);
                    this.r.setTextSize(18.0f);
                    this.a((ViewGroup)w, (View)this.r);
                }
                if (this.s != null) {
                    final RelativeLayout$LayoutParams layoutParams8 = new RelativeLayout$LayoutParams(-1, -2);
                    layoutParams8.addRule(12);
                    layoutParams8.addRule(9);
                    layoutParams8.bottomMargin = (int)(4.0f * b);
                    this.s.setEllipsize(TextUtils$TruncateAt.END);
                    this.s.setGravity(8388611);
                    this.s.setLayoutParams((ViewGroup$LayoutParams)layoutParams8);
                    this.s.setMaxLines(1);
                    this.s.setPadding((int)(72.0f * b), 0, 0, 0);
                    this.s.setTextColor(-1);
                    this.a((ViewGroup)w, (View)this.s);
                }
                com.facebook.ads.internal.w.b.x.a((View)this.b.getParent(), -16777216);
                final View rootView = this.b.getRootView();
                if (rootView != null) {
                    rootView.setOnTouchListener((View$OnTouchListener)this);
                }
                return;
            }
            if (n == 1) {
                final RelativeLayout$LayoutParams layoutParams9 = new RelativeLayout$LayoutParams(-1, -2);
                layoutParams9.addRule(10);
                this.b.setLayoutParams((ViewGroup$LayoutParams)layoutParams9);
                this.a((View)this.b);
                this.a(this.y);
                if (this.u != null) {
                    this.a((View)this.u);
                }
                final LinearLayout w2 = new LinearLayout(this.d);
                ((LinearLayout)(this.w = (ViewGroup)w2)).setGravity(112);
                w2.setOrientation(1);
                final RelativeLayout$LayoutParams layoutParams10 = new RelativeLayout$LayoutParams(-1, -1);
                layoutParams10.leftMargin = (int)(33.0f * b);
                layoutParams10.rightMargin = (int)(33.0f * b);
                layoutParams10.topMargin = (int)(8.0f * b);
                if (this.q == null) {
                    layoutParams10.bottomMargin = (int)(16.0f * b);
                }
                else {
                    layoutParams10.bottomMargin = (int)(80.0f * b);
                }
                layoutParams10.addRule(3, id);
                w2.setLayoutParams((ViewGroup$LayoutParams)layoutParams10);
                this.a((View)w2);
                if (this.q != null) {
                    final RelativeLayout$LayoutParams layoutParams11 = new RelativeLayout$LayoutParams(-1, (int)(64.0f * b));
                    layoutParams11.bottomMargin = (int)(16.0f * b);
                    layoutParams11.leftMargin = (int)(33.0f * b);
                    layoutParams11.rightMargin = (int)(33.0f * b);
                    layoutParams11.addRule(1);
                    layoutParams11.addRule(12);
                    this.q.setLayoutParams((ViewGroup$LayoutParams)layoutParams11);
                    this.a((View)this.q);
                }
                if (this.r != null) {
                    final LinearLayout$LayoutParams layoutParams12 = new LinearLayout$LayoutParams(-2, -2);
                    layoutParams12.weight = 2.0f;
                    layoutParams12.gravity = 17;
                    this.r.setEllipsize(TextUtils$TruncateAt.END);
                    this.r.setGravity(17);
                    this.r.setLayoutParams((ViewGroup$LayoutParams)layoutParams12);
                    this.r.setMaxLines(2);
                    this.r.setPadding(0, 0, 0, 0);
                    this.r.setTextColor(this.A);
                    this.r.setTextSize(24.0f);
                    this.a((ViewGroup)w2, (View)this.r);
                }
                if (this.t != null) {
                    final LinearLayout$LayoutParams layoutParams13 = new LinearLayout$LayoutParams((int)(64.0f * b), (int)(64.0f * b));
                    layoutParams13.weight = 0.0f;
                    layoutParams13.gravity = 17;
                    this.t.setLayoutParams((ViewGroup$LayoutParams)layoutParams13);
                    this.a((ViewGroup)w2, (View)this.t);
                }
                if (this.s != null) {
                    final LinearLayout$LayoutParams layoutParams14 = new LinearLayout$LayoutParams(-1, -2);
                    layoutParams14.weight = 2.0f;
                    layoutParams14.gravity = 16;
                    this.s.setEllipsize(TextUtils$TruncateAt.END);
                    this.s.setGravity(16);
                    this.s.setLayoutParams((ViewGroup$LayoutParams)layoutParams14);
                    this.s.setMaxLines(2);
                    this.s.setPadding(0, 0, 0, 0);
                    this.s.setTextColor(this.A);
                    this.a((ViewGroup)w2, (View)this.s);
                }
                if (this.v != null) {
                    final RelativeLayout$LayoutParams layoutParams15 = new RelativeLayout$LayoutParams(-1, (int)(b * 6.0f));
                    layoutParams15.addRule(3, id);
                    this.v.setLayoutParams((ViewGroup$LayoutParams)layoutParams15);
                    this.a(this.v);
                }
                com.facebook.ads.internal.w.b.x.a((View)this.b.getParent(), this.z);
                continue;
            }
            float n4;
            if (this.b.getVideoHeight() > 0) {
                n4 = this.b.getVideoWidth() / (float)this.b.getVideoHeight();
            }
            else {
                n4 = -1.0f;
            }
            if (n4 > 0.9 && n4 < 1.1) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n != 0 && !this.k()) {
                final RelativeLayout$LayoutParams layoutParams16 = new RelativeLayout$LayoutParams(-2, -1);
                layoutParams16.addRule(9);
                this.b.setLayoutParams((ViewGroup$LayoutParams)layoutParams16);
                this.a((View)this.b);
                this.a(this.y);
                if (this.u != null) {
                    this.a((View)this.u);
                }
                final LinearLayout w3 = new LinearLayout(this.d);
                ((LinearLayout)(this.w = (ViewGroup)w3)).setGravity(112);
                w3.setOrientation(1);
                final RelativeLayout$LayoutParams layoutParams17 = new RelativeLayout$LayoutParams(-1, -1);
                layoutParams17.leftMargin = (int)(16.0f * b);
                layoutParams17.rightMargin = (int)(16.0f * b);
                layoutParams17.topMargin = (int)(8.0f * b);
                layoutParams17.bottomMargin = (int)(80.0f * b);
                layoutParams17.addRule(1, id);
                w3.setLayoutParams((ViewGroup$LayoutParams)layoutParams17);
                this.a((View)w3);
                if (this.v != null) {
                    final RelativeLayout$LayoutParams layoutParams18 = new RelativeLayout$LayoutParams(-1, (int)(6.0f * b));
                    layoutParams18.addRule(5, id);
                    layoutParams18.addRule(7, id);
                    layoutParams18.addRule(3, id);
                    layoutParams18.topMargin = (int)(-6.0f * b);
                    this.v.setLayoutParams((ViewGroup$LayoutParams)layoutParams18);
                    this.a(this.v);
                }
                if (this.r != null) {
                    final LinearLayout$LayoutParams layoutParams19 = new LinearLayout$LayoutParams(-2, -2);
                    layoutParams19.weight = 2.0f;
                    layoutParams19.gravity = 17;
                    this.r.setEllipsize(TextUtils$TruncateAt.END);
                    this.r.setGravity(17);
                    this.r.setLayoutParams((ViewGroup$LayoutParams)layoutParams19);
                    this.r.setMaxLines(10);
                    this.r.setPadding(0, 0, 0, 0);
                    this.r.setTextColor(this.A);
                    this.r.setTextSize(24.0f);
                    this.a((ViewGroup)w3, (View)this.r);
                }
                if (this.t != null) {
                    final LinearLayout$LayoutParams layoutParams20 = new LinearLayout$LayoutParams((int)(64.0f * b), (int)(64.0f * b));
                    layoutParams20.weight = 0.0f;
                    layoutParams20.gravity = 17;
                    this.t.setLayoutParams((ViewGroup$LayoutParams)layoutParams20);
                    this.a((ViewGroup)w3, (View)this.t);
                }
                if (this.s != null) {
                    final LinearLayout$LayoutParams layoutParams21 = new LinearLayout$LayoutParams(-1, -2);
                    layoutParams21.weight = 2.0f;
                    layoutParams21.gravity = 16;
                    this.s.setEllipsize(TextUtils$TruncateAt.END);
                    this.s.setGravity(17);
                    this.s.setLayoutParams((ViewGroup$LayoutParams)layoutParams21);
                    this.s.setMaxLines(10);
                    this.s.setPadding(0, 0, 0, 0);
                    this.s.setTextColor(this.A);
                    this.a((ViewGroup)w3, (View)this.s);
                }
                if (this.q != null) {
                    final RelativeLayout$LayoutParams layoutParams22 = new RelativeLayout$LayoutParams(-1, (int)(64.0f * b));
                    layoutParams22.bottomMargin = (int)(16.0f * b);
                    layoutParams22.leftMargin = (int)(16.0f * b);
                    layoutParams22.rightMargin = (int)(16.0f * b);
                    layoutParams22.addRule(1);
                    layoutParams22.addRule(12);
                    layoutParams22.addRule(1, id);
                    this.q.setLayoutParams((ViewGroup$LayoutParams)layoutParams22);
                    this.a((View)this.q);
                }
                com.facebook.ads.internal.w.b.x.a((View)this.b.getParent(), this.z);
                continue;
            }
            final GradientDrawable gradientDrawable2 = new GradientDrawable(GradientDrawable$Orientation.TOP_BOTTOM, new int[] { 0, -15658735 });
            gradientDrawable2.setCornerRadius(0.0f);
            this.b.setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
            this.a((View)this.b);
            this.a(this.y);
            if (this.u != null) {
                this.a((View)this.u);
            }
            final RelativeLayout$LayoutParams layoutParams23 = new RelativeLayout$LayoutParams(-1, (int)(124.0f * b));
            layoutParams23.addRule(12);
            final RelativeLayout w4 = new RelativeLayout(this.d);
            com.facebook.ads.internal.w.b.x.a((View)w4, (Drawable)gradientDrawable2);
            w4.setLayoutParams((ViewGroup$LayoutParams)layoutParams23);
            w4.setPadding(n2, 0, n2, n2);
            this.w = (ViewGroup)w4;
            if (!this.C) {
                this.x.a((View)w4, a);
            }
            this.a((View)w4);
            if (this.q != null) {
                final RelativeLayout$LayoutParams layoutParams24 = new RelativeLayout$LayoutParams((int)(110.0f * b), (int)(56.0f * b));
                layoutParams24.rightMargin = (int)(16.0f * b);
                layoutParams24.bottomMargin = (int)(16.0f * b);
                layoutParams24.addRule(12);
                layoutParams24.addRule(11);
                this.q.setLayoutParams((ViewGroup$LayoutParams)layoutParams24);
                this.a((View)this.q);
            }
            if (this.t != null) {
                final RelativeLayout$LayoutParams layoutParams25 = new RelativeLayout$LayoutParams((int)(64.0f * b), (int)(64.0f * b));
                layoutParams25.addRule(12);
                layoutParams25.addRule(9);
                layoutParams25.bottomMargin = (int)(8.0f * b);
                this.t.setLayoutParams((ViewGroup$LayoutParams)layoutParams25);
                this.a((ViewGroup)w4, (View)this.t);
            }
            if (this.r != null) {
                final RelativeLayout$LayoutParams layoutParams26 = new RelativeLayout$LayoutParams(-1, -2);
                layoutParams26.bottomMargin = (int)(48.0f * b);
                layoutParams26.addRule(12);
                layoutParams26.addRule(9);
                this.r.setEllipsize(TextUtils$TruncateAt.END);
                this.r.setGravity(8388611);
                this.r.setLayoutParams((ViewGroup$LayoutParams)layoutParams26);
                this.r.setMaxLines(1);
                final TextView r = this.r;
                final int n5 = (int)(80.0f * b);
                if (this.q != null) {
                    n = (int)(126.0f * b);
                }
                else {
                    n = 0;
                }
                r.setPadding(n5, 0, n, 0);
                this.r.setTextColor(-1);
                this.r.setTextSize(24.0f);
                this.a((ViewGroup)w4, (View)this.r);
            }
            if (this.s != null) {
                final RelativeLayout$LayoutParams layoutParams27 = new RelativeLayout$LayoutParams(-1, -2);
                layoutParams27.addRule(12);
                layoutParams27.addRule(9);
                this.s.setEllipsize(TextUtils$TruncateAt.END);
                this.s.setGravity(8388611);
                this.s.setLayoutParams((ViewGroup$LayoutParams)layoutParams27);
                this.s.setMaxLines(2);
                this.s.setTextColor(-1);
                final TextView s = this.s;
                final int n6 = (int)(80.0f * b);
                if (this.q != null) {
                    n = (int)(126.0f * b);
                }
                else {
                    n = 0;
                }
                s.setPadding(n6, 0, n, 0);
                this.a((ViewGroup)w4, (View)this.s);
            }
            if (this.v != null) {
                final RelativeLayout$LayoutParams layoutParams28 = new RelativeLayout$LayoutParams(-1, (int)(b * 6.0f));
                layoutParams28.addRule(12);
                this.v.setLayoutParams((ViewGroup$LayoutParams)layoutParams28);
                this.a(this.v);
            }
            com.facebook.ads.internal.w.b.x.a((View)this.b.getParent(), -16777216);
            continue;
        }
    }
    
    private void a(final View view) {
        if (this.k == null) {
            return;
        }
        this.k.a(view);
    }
    
    private void a(@Nullable final ViewGroup viewGroup, @Nullable final View view) {
        if (viewGroup != null) {
            viewGroup.addView(view);
        }
    }
    
    private void b(final View view) {
        if (view != null) {
            final ViewGroup viewGroup = (ViewGroup)view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
    }
    
    private boolean k() {
        boolean b = true;
        if (this.b.getVideoHeight() <= 0) {
            b = false;
        }
        else {
            final Rect rect = new Rect();
            this.l.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            if (rect.width() > rect.height()) {
                if (rect.width() - rect.height() * this.b.getVideoWidth() / this.b.getVideoHeight() - 192.0f * com.facebook.ads.internal.w.b.x.b >= 0.0f) {
                    return false;
                }
            }
            else if (rect.height() - rect.width() * this.b.getVideoHeight() / this.b.getVideoWidth() - com.facebook.ads.internal.w.b.x.b * 64.0f - com.facebook.ads.internal.w.b.x.b * 64.0f - 40.0f * com.facebook.ads.internal.w.b.x.b >= 0.0f) {
                return false;
            }
        }
        return b;
    }
    
    private void l() {
        this.b((View)this.b);
        this.b((View)this.q);
        this.b((View)this.r);
        this.b((View)this.s);
        this.b((View)this.t);
        this.b(this.v);
        this.b((View)this.w);
        this.b(this.y);
        if (this.u != null) {
            this.b((View)this.u);
        }
    }
    
    @Override
    protected void a() {
        if (this.c == null) {
            Log.e(com.facebook.ads.internal.adapters.h.j, "Unable to add UI without a valid ad response.");
            return;
        }
        final String string = this.c.getString("ct");
        final String optString = this.c.getJSONObject("context").optString("orientation");
        if (!optString.isEmpty()) {
            this.o = com.facebook.ads.internal.view.c.f.a(Integer.parseInt(optString));
        }
        if (this.c.has("layout") && !this.c.isNull("layout")) {
            final JSONObject jsonObject = this.c.getJSONObject("layout");
            this.z = (int)jsonObject.optLong("bgColor", (long)this.z);
            this.A = (int)jsonObject.optLong("textColor", (long)this.A);
            this.B = (int)jsonObject.optLong("accentColor", (long)this.B);
            this.C = jsonObject.optBoolean("persistentAdDetails", this.C);
        }
        final JSONObject jsonObject2 = this.c.getJSONObject("text");
        int id;
        if (Build$VERSION.SDK_INT >= 17) {
            id = View.generateViewId();
        }
        else {
            id = com.facebook.ads.internal.w.b.x.a();
        }
        this.b.setId(id);
        final int c = this.c();
        final Context d = this.d;
        int n = c;
        if (c < 0) {
            n = 0;
        }
        (this.y = new j(d, n, this.B)).setOnTouchListener(this.n);
        this.b.a(this.y);
        if (this.c.has("cta") && !this.c.isNull("cta")) {
            final JSONObject jsonObject3 = this.c.getJSONObject("cta");
            this.q = new com.facebook.ads.internal.view.e.a(this.d, this.p, jsonObject3.getString("url"), jsonObject3.getString("text"), this.B, this.b, this.a, string);
            com.facebook.ads.internal.a.c.a(this.d, this.a, string, Uri.parse(jsonObject3.getString("url")), new HashMap<String, String>());
        }
        if (this.c.has("icon") && !this.c.isNull("icon")) {
            final JSONObject jsonObject4 = this.c.getJSONObject("icon");
            this.t = new ImageView(this.d);
            new com.facebook.ads.internal.view.c.d(this.t).a((int)(com.facebook.ads.internal.w.b.x.b * 64.0f), (int)(com.facebook.ads.internal.w.b.x.b * 64.0f)).a(jsonObject4.getString("url"));
        }
        if (this.c.has("image") && !this.c.isNull("image")) {
            final JSONObject jsonObject5 = this.c.getJSONObject("image");
            final g g = new g(this.d);
            this.b.a(g);
            g.setImage(jsonObject5.getString("url"));
        }
        final String optString2 = jsonObject2.optString("title");
        if (!optString2.isEmpty()) {
            (this.r = new TextView(this.d)).setText((CharSequence)optString2);
            this.r.setTypeface(Typeface.defaultFromStyle(1));
        }
        final String optString3 = jsonObject2.optString("subtitle");
        if (!optString3.isEmpty()) {
            (this.s = new TextView(this.d)).setText((CharSequence)optString3);
            this.s.setTextSize(16.0f);
        }
        this.v = new com.facebook.ads.internal.view.i.c.n(this.d);
        this.b.a(this.v);
        final String d2 = this.d();
        if (!TextUtils.isEmpty((CharSequence)d2)) {
            this.u = new a.a(this.d, "AdChoices", d2, new float[] { 0.0f, 0.0f, 8.0f, 0.0f }, string);
            final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-2, -2);
            layoutParams.addRule(10);
            layoutParams.addRule(9);
            this.u.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        }
        this.b.a(new k(this.d));
        final l l = new l(this.d);
        this.b.a(l);
        d.a a;
        if (this.h()) {
            a = com.facebook.ads.internal.view.i.c.d.a.c;
        }
        else {
            a = com.facebook.ads.internal.view.i.c.d.a.a;
        }
        this.b.a(new d((View)l, a));
        this.x = new d((View)new RelativeLayout(this.d), a);
        this.b.a(this.x);
    }
    
    @TargetApi(17)
    public void a(final Intent intent, final Bundle bundle, final AudienceNetworkActivity l) {
        this.l = l;
        if (!com.facebook.ads.internal.adapters.h.i && this.k == null) {
            throw new AssertionError();
        }
        l.addBackButtonInterceptor(this.m);
        this.l();
        this.a(this.l.getResources().getConfiguration().orientation);
        if (this.h()) {
            this.e();
            return;
        }
        this.f();
    }
    
    public void a(final Configuration configuration) {
        this.l();
        this.a(configuration.orientation);
    }
    
    public void a(final Bundle bundle) {
    }
    
    public void a_(final boolean b) {
        if (this.b != null && this.b.getState() == com.facebook.ads.internal.view.i.d.d.d) {
            this.D = this.b.getVideoStartReason();
            this.b.a(false);
        }
    }
    
    public void b(final boolean b) {
        if (this.b != null && this.D != null) {
            this.b.a(this.D);
        }
    }
    
    protected boolean h() {
        if (!com.facebook.ads.internal.adapters.h.i && this.c == null) {
            throw new AssertionError();
        }
        try {
            return this.c.getJSONObject("video").getBoolean("autoplay");
        }
        catch (Exception ex) {
            Log.w(String.valueOf(h.class), "Invalid JSON", (Throwable)ex);
            return true;
        }
    }
    
    public com.facebook.ads.internal.view.c.f i() {
        return this.o;
    }
    
    public void j() {
        if (this.l != null) {
            this.l.finish();
        }
    }
    
    @Override
    public void onDestroy() {
        if (this.c != null && this.a != null) {
            final String optString = this.c.optString("ct");
            if (!TextUtils.isEmpty((CharSequence)optString)) {
                this.a.l(optString, new HashMap<String, String>());
            }
        }
        if (this.b != null) {
            this.b.g();
        }
        com.facebook.ads.internal.adapters.g.a(this);
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        this.p.a(motionEvent, view.getRootView(), view);
        if (this.b != null) {
            this.b.getEventBus().a(new u(view, motionEvent));
        }
        return true;
    }
    
    public void setListener(final a k) {
        this.k = k;
    }
}
