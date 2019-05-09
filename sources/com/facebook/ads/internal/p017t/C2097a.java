package com.facebook.ads.internal.p017t;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.view.component.C2297d;
import com.facebook.ads.internal.view.component.C2301h;
import com.facebook.ads.internal.view.p052h.C2096c;
import java.util.ArrayList;

/* renamed from: com.facebook.ads.internal.t.a */
public class C2097a extends FrameLayout implements C2096c {
    /* renamed from: a */
    private static final int f4798a = ((int) (C2600x.f6420b * 110.0f));
    /* renamed from: b */
    private final C2117j f4799b;
    /* renamed from: c */
    private final C2114e f4800c;
    /* renamed from: d */
    private final AdOptionsView f4801d;
    /* renamed from: e */
    private ArrayList<View> f4802e = new ArrayList();

    public C2097a(Context context, C2114e c2114e, AdOptionsView adOptionsView, @Nullable MediaView mediaView, MediaView mediaView2, C2118k c2118k, C2117j c2117j) {
        View c2301h;
        super(context);
        this.f4799b = c2117j;
        this.f4800c = c2114e;
        this.f4801d = adOptionsView;
        View linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        switch (c2118k) {
            case HEIGHT_400:
                c2301h = new C2301h(getContext(), this.f4800c, this.f4799b);
                c2301h.setLayoutParams(new LayoutParams(-1, f4798a));
                linearLayout.addView(c2301h);
                break;
            case HEIGHT_300:
                break;
        }
        c2301h = new RelativeLayout(getContext());
        c2301h.setLayoutParams(new LayoutParams(-1, (int) (180.0f * C2600x.f6420b)));
        C2600x.m6680a(c2301h, this.f4799b.m5363b());
        c2301h.addView(mediaView);
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) (180.0f * C2600x.f6420b));
        layoutParams.addRule(13, -1);
        mediaView.setLayoutParams(layoutParams);
        linearLayout.addView(c2301h);
        this.f4802e.add(mediaView);
        Context context2 = getContext();
        C2114e c2114e2 = this.f4800c;
        C2117j c2117j2 = this.f4799b;
        AdOptionsView adOptionsView2 = this.f4801d;
        boolean z = c2118k == C2118k.HEIGHT_300 || c2118k == C2118k.HEIGHT_120;
        c2301h = new C2297d(context2, c2114e2, c2117j2, mediaView2, adOptionsView2, z, C2097a.m5230a(c2118k));
        c2301h.setLayoutParams(new LayoutParams(-1, (int) (((float) C2097a.m5230a(c2118k)) * C2600x.f6420b)));
        linearLayout.addView(c2301h);
        this.f4802e.add(c2301h.getIconView());
        this.f4802e.add(c2301h.getCallToActionView());
        addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
    }

    /* renamed from: a */
    private static int m5230a(C2118k c2118k) {
        switch (c2118k) {
            case HEIGHT_400:
                return (c2118k.m5382b() - 180) / 2;
            case HEIGHT_300:
                return c2118k.m5382b() - 180;
            case HEIGHT_50:
            case HEIGHT_100:
            case HEIGHT_120:
                return c2118k.m5382b();
            default:
                return 0;
        }
    }

    /* renamed from: a */
    public void mo5496a() {
        this.f4800c.m5350z();
    }

    public View getView() {
        return this;
    }

    public ArrayList<View> getViewsForInteraction() {
        return this.f4802e;
    }
}
