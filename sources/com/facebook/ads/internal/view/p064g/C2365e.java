package com.facebook.ads.internal.view.p064g;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.view.component.C2300g;
import com.facebook.ads.internal.view.p019c.C2252d;

/* renamed from: com.facebook.ads.internal.view.g.e */
class C2365e extends C2300g {
    /* renamed from: b */
    private final ImageView f5725b;

    public C2365e(Context context) {
        super(context);
        this.f5725b = new ImageView(context);
        this.f5725b.setAdjustViewBounds(true);
        addView(this.f5725b, new LayoutParams(-2, -1));
    }

    /* renamed from: a */
    public void m6113a(String str) {
        C2252d c2252d = new C2252d(this.f5725b);
        c2252d.m5771a();
        c2252d.m5775a(str);
    }
}
