package com.facebook.ads.internal.p025w.p026b;

import org.json.JSONArray;

/* renamed from: com.facebook.ads.internal.w.b.b */
public enum C2566b {
    APP_AD(0),
    LINK_AD(1),
    APP_AD_V2(2),
    LINK_AD_V2(3),
    APP_ENGAGEMENT_AD(4),
    AD_CHOICES(5),
    JS_TRIGGER(6),
    JS_TRIGGER_NO_AUTO_IMP_LOGGING(7),
    VIDEO_AD(8),
    INLINE_VIDEO_AD(9),
    BANNER_TO_INTERSTITIAL(10),
    NATIVE_CLOSE_BUTTON(11),
    UNIFIED_LOGGING(16),
    HTTP_LINKS(17);
    
    /* renamed from: o */
    public static final C2566b[] f6318o = null;
    /* renamed from: q */
    private static final String f6319q = null;
    /* renamed from: p */
    private final int f6321p;

    static {
        f6318o = new C2566b[]{LINK_AD_V2, APP_ENGAGEMENT_AD, AD_CHOICES, JS_TRIGGER_NO_AUTO_IMP_LOGGING, NATIVE_CLOSE_BUTTON, UNIFIED_LOGGING, HTTP_LINKS};
        JSONArray jSONArray = new JSONArray();
        C2566b[] c2566bArr = f6318o;
        int length = c2566bArr.length;
        int i;
        while (i < length) {
            jSONArray.put(c2566bArr[i].m6619a());
            i++;
        }
        f6319q = jSONArray.toString();
    }

    private C2566b(int i) {
        this.f6321p = i;
    }

    /* renamed from: b */
    public static String m6618b() {
        return f6319q;
    }

    /* renamed from: a */
    public int m6619a() {
        return this.f6321p;
    }

    public String toString() {
        return String.valueOf(this.f6321p);
    }
}
