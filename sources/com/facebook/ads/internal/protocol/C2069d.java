package com.facebook.ads.internal.protocol;

import com.tonyodev.fetch.FetchService;
import java.io.Serializable;

/* renamed from: com.facebook.ads.internal.protocol.d */
public enum C2069d implements Serializable {
    BANNER_320_50(FetchService.ACTION_LOGGING, 50),
    INTERSTITIAL(0, 0),
    BANNER_HEIGHT_50(-1, 50),
    BANNER_HEIGHT_90(-1, 90),
    RECTANGLE_HEIGHT_250(-1, 250);
    
    /* renamed from: f */
    private final int f4660f;
    /* renamed from: g */
    private final int f4661g;

    private C2069d(int i, int i2) {
        this.f4660f = i;
        this.f4661g = i2;
    }

    /* renamed from: a */
    public static C2069d m5044a(int i, int i2) {
        return (INTERSTITIAL.f4661g == i2 && INTERSTITIAL.f4660f == i) ? INTERSTITIAL : (BANNER_320_50.f4661g == i2 && BANNER_320_50.f4660f == i) ? BANNER_320_50 : (BANNER_HEIGHT_50.f4661g == i2 && BANNER_HEIGHT_50.f4660f == i) ? BANNER_HEIGHT_50 : (BANNER_HEIGHT_90.f4661g == i2 && BANNER_HEIGHT_90.f4660f == i) ? BANNER_HEIGHT_90 : (RECTANGLE_HEIGHT_250.f4661g == i2 && RECTANGLE_HEIGHT_250.f4660f == i) ? RECTANGLE_HEIGHT_250 : null;
    }

    /* renamed from: a */
    public int m5045a() {
        return this.f4660f;
    }

    /* renamed from: b */
    public int m5046b() {
        return this.f4661g;
    }
}
