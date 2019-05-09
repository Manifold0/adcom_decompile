package com.facebook.ads.internal.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.facebook.ads.internal.p017t.C1783f;
import com.facebook.ads.internal.p025w.p026b.C2580j;
import com.facebook.ads.internal.p025w.p026b.C2600x;

/* renamed from: com.facebook.ads.internal.view.k */
public class C2507k extends C1783f {
    /* renamed from: a */
    private static final int f6120a = ((int) (1.0f * C2600x.f6420b));
    /* renamed from: b */
    private final ImageView f6121b;

    public C2507k(Context context) {
        super(context);
        this.f6121b = new C2554x(context);
        this.f6121b.setScaleType(ScaleType.CENTER_CROP);
        C2580j.m6643a(this.f6121b, C2580j.INTERNAL_AD_MEDIA);
        addView(this.f6121b, new LayoutParams(-1, -1));
        C2600x.m6680a(this.f6121b, -2130706433);
        setPadding(f6120a, f6120a, f6120a, f6120a);
    }

    public View getAdContentsView() {
        return this.f6121b;
    }

    public ImageView getImageCardView() {
        return this.f6121b;
    }
}
