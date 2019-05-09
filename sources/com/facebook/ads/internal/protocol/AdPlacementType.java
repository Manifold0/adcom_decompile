package com.facebook.ads.internal.protocol;

import android.text.TextUtils;
import java.util.Locale;

public enum AdPlacementType {
    BANNER("banner"),
    INSTREAM("instream"),
    INTERSTITIAL("interstitial"),
    NATIVE("native"),
    NATIVE_BANNER("native_banner"),
    REWARDED_VIDEO("rewarded_video"),
    UNKNOWN("unknown");
    
    /* renamed from: a */
    private String f4641a;

    private AdPlacementType(String str) {
        this.f4641a = str;
    }

    public static AdPlacementType fromString(String str) {
        if (TextUtils.isEmpty(str)) {
            return UNKNOWN;
        }
        try {
            return valueOf(str.toUpperCase(Locale.US));
        } catch (Exception e) {
            return UNKNOWN;
        }
    }

    public String toString() {
        return this.f4641a;
    }
}
