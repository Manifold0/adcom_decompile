package com.facebook.ads.internal.adapters.p030b;

/* renamed from: com.facebook.ads.internal.adapters.b.p */
public enum C1886p {
    WEBVIEW_PRECACHE,
    PROXY_PRECACHE,
    FILE_PRECACHE;

    /* renamed from: a */
    public static C1886p m4354a(String str) {
        try {
            return C1886p.valueOf(str);
        } catch (IllegalArgumentException e) {
            return FILE_PRECACHE;
        }
    }
}
