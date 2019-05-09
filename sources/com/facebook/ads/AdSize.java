package com.facebook.ads;

import com.facebook.ads.internal.protocol.C2069d;
import java.io.Serializable;

public class AdSize implements Serializable {
    @Deprecated
    public static final AdSize BANNER_320_50 = new AdSize(C2069d.BANNER_320_50);
    public static final AdSize BANNER_HEIGHT_50 = new AdSize(C2069d.BANNER_HEIGHT_50);
    public static final AdSize BANNER_HEIGHT_90 = new AdSize(C2069d.BANNER_HEIGHT_90);
    public static final AdSize INTERSTITIAL = new AdSize(C2069d.INTERSTITIAL);
    public static final AdSize RECTANGLE_HEIGHT_250 = new AdSize(C2069d.RECTANGLE_HEIGHT_250);
    /* renamed from: a */
    private final int f3685a;
    /* renamed from: b */
    private final int f3686b;

    public AdSize(int i, int i2) {
        this.f3685a = i;
        this.f3686b = i2;
    }

    private AdSize(C2069d c2069d) {
        this.f3685a = c2069d.m5045a();
        this.f3686b = c2069d.m5046b();
    }

    public static AdSize fromWidthAndHeight(int i, int i2) {
        return (INTERSTITIAL.f3686b == i2 && INTERSTITIAL.f3685a == i) ? INTERSTITIAL : (BANNER_320_50.f3686b == i2 && BANNER_320_50.f3685a == i) ? BANNER_320_50 : (BANNER_HEIGHT_50.f3686b == i2 && BANNER_HEIGHT_50.f3685a == i) ? BANNER_HEIGHT_50 : (BANNER_HEIGHT_90.f3686b == i2 && BANNER_HEIGHT_90.f3685a == i) ? BANNER_HEIGHT_90 : (RECTANGLE_HEIGHT_250.f3686b == i2 && RECTANGLE_HEIGHT_250.f3685a == i) ? RECTANGLE_HEIGHT_250 : null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdSize adSize = (AdSize) obj;
        return this.f3685a != adSize.f3685a ? false : this.f3686b == adSize.f3686b;
    }

    public int getHeight() {
        return this.f3686b;
    }

    public int getWidth() {
        return this.f3685a;
    }

    public int hashCode() {
        return (this.f3685a * 31) + this.f3686b;
    }

    public C2069d toInternalAdSize() {
        return C2069d.m5044a(this.f3685a, this.f3686b);
    }
}
