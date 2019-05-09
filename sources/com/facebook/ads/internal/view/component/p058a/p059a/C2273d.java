package com.facebook.ads.internal.view.component.p058a.p059a;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.view.component.p058a.C2279e;
import com.facebook.ads.internal.view.p061e.p062a.C2315a;

/* renamed from: com.facebook.ads.internal.view.component.a.a.d */
public class C2273d extends C2260b {
    /* renamed from: c */
    private static final int f5354c = ((int) (20.0f * C2600x.f6420b));
    /* renamed from: d */
    private static final int f5355d = ((int) (16.0f * C2600x.f6420b));

    C2273d(C2279e c2279e, C1876h c1876h, String str, C2315a c2315a) {
        super(c2279e, c1876h, false, str, c2315a);
    }

    /* renamed from: a */
    protected void mo5557a(Context context) {
        View titleDescContainer = getTitleDescContainer();
        titleDescContainer.setAlignment(3);
        titleDescContainer.setLayoutParams(new LayoutParams(-1, -2));
        titleDescContainer.setPadding(0, 0, 0, f5354c);
        getCtaButton().setLayoutParams(new LayoutParams(-1, -2));
        View linearLayout = new LinearLayout(context);
        C2600x.m6681a(linearLayout, new ColorDrawable(-1));
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(3, getMediaContainer().getId());
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(f5355d, f5355d, f5355d, f5355d);
        linearLayout.addView(titleDescContainer);
        linearLayout.addView(getCtaButton());
        addView(getMediaContainer());
        addView(linearLayout);
    }

    /* renamed from: b */
    protected boolean mo5559b() {
        return false;
    }
}
