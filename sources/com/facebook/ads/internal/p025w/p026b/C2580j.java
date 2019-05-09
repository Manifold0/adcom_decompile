package com.facebook.ads.internal.p025w.p026b;

import android.os.Build.VERSION;
import android.view.View;

/* renamed from: com.facebook.ads.internal.w.b.j */
public enum C2580j {
    INTERNAL_NO_TAG(0),
    INTERNAL_NO_CLICK(1),
    INTERNAL_API_TOO_LOW(2),
    INTERNAL_WRONG_TAG_CLASS(3),
    INTERNAL_NULL_VIEW(4),
    INTERNAL_AD_ICON(5),
    INTERNAL_AD_TITLE(6),
    INTERNAL_AD_COVER_IMAGE(7),
    INTERNAL_AD_SUBTITLE(8),
    INTERNAL_AD_BODY(9),
    INTERNAL_AD_CALL_TO_ACTION(10),
    INTERNAL_AD_SOCIAL_CONTEXT(11),
    INTERNAL_AD_CHOICES_ICON(12),
    INTERNAL_AD_MEDIA(13),
    INTERNAL_AD_OPTIONS_VIEW(12);
    
    /* renamed from: p */
    public static int f6358p;
    /* renamed from: q */
    private final int f6360q;

    static {
        f6358p = -1593835521;
    }

    private C2580j(int i) {
        this.f6360q = i;
    }

    /* renamed from: a */
    public static void m6643a(View view, C2580j c2580j) {
        if (view != null && c2580j != null && VERSION.SDK_INT > 4) {
            view.setTag(f6358p, c2580j);
        }
    }

    /* renamed from: a */
    public int m6644a() {
        return this.f6360q;
    }
}
