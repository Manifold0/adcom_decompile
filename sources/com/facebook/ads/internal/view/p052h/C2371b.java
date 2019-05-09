package com.facebook.ads.internal.view.p052h;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.internal.p017t.C2114e;
import com.facebook.ads.internal.p017t.C2117j;
import com.facebook.ads.internal.p017t.C2118k;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import java.util.ArrayList;

/* renamed from: com.facebook.ads.internal.view.h.b */
public class C2371b extends LinearLayout implements C2096c {
    /* renamed from: a */
    private static final int f5737a = ((int) (C2600x.f6420b * 6.0f));
    /* renamed from: b */
    private final C2114e f5738b;
    /* renamed from: c */
    private final ArrayList<View> f5739c = new ArrayList();

    public C2371b(Context context, C2114e c2114e, C2117j c2117j, C2118k c2118k, MediaView mediaView, AdOptionsView adOptionsView) {
        super(context);
        this.f5738b = c2114e;
        setOrientation(0);
        int b = (int) (C2600x.f6420b * ((float) c2118k.m5382b()));
        View c2370a = new C2370a(getContext(), this.f5738b, c2117j, adOptionsView);
        c2370a.setPadding(f5737a, f5737a, f5737a, f5737a);
        TextView button = new Button(getContext());
        c2117j.m5369c(button);
        button.setText(c2114e.m5309a("call_to_action"));
        addView(mediaView, new LayoutParams(b, b));
        ViewGroup.LayoutParams layoutParams = new LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        layoutParams.gravity = 16;
        addView(c2370a, layoutParams);
        addView(button, new LayoutParams(b * 2, b));
        this.f5739c.add(mediaView);
        this.f5739c.add(button);
    }

    /* renamed from: a */
    public void mo5496a() {
        this.f5738b.m5350z();
    }

    public View getView() {
        return this;
    }

    public ArrayList<View> getViewsForInteraction() {
        return this.f5739c;
    }
}
