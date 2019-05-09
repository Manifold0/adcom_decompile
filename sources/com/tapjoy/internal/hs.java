package com.tapjoy.internal;

import android.content.Context;
import android.view.ViewGroup.LayoutParams;

public final class hs extends an {
    /* renamed from: a */
    private final gx f8169a;
    /* renamed from: b */
    private final ht f8170b;
    /* renamed from: c */
    private af f8171c = null;

    public hs(Context context, gx gxVar, ht htVar) {
        super(context);
        this.f8169a = gxVar;
        this.f8170b = htVar;
        addView(htVar, new LayoutParams(-1, -1));
    }

    protected final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        af afVar;
        af a = af.m7153a(getContext());
        if (!this.f8169a.m8010a()) {
            afVar = af.LANDSCAPE;
            if (!a.m7155a()) {
                setRotationCount(0);
            } else if (a.m7157c() == 3) {
                setRotationCount(1);
            } else {
                setRotationCount(1);
            }
        } else if (this.f8169a.m8011b()) {
            if (a.m7155a()) {
                afVar = af.PORTRAIT;
            } else if (a.m7156b() || !af.m7154b(getContext()).m7155a()) {
                afVar = af.LANDSCAPE;
            } else {
                afVar = af.PORTRAIT;
            }
            setRotationCount(0);
        } else {
            afVar = af.PORTRAIT;
            if (!a.m7156b()) {
                setRotationCount(0);
            } else if (a.m7157c() == 3) {
                setRotationCount(1);
            } else {
                setRotationCount(3);
            }
        }
        if (this.f8171c != afVar) {
            this.f8171c = afVar;
            this.f8170b.setLandscape(this.f8171c.m7156b());
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
