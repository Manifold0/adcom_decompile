// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.protocol;

import java.util.Locale;
import android.text.TextUtils;

public enum AdPlacementType
{
    BANNER("banner"), 
    INSTREAM("instream"), 
    INTERSTITIAL("interstitial"), 
    NATIVE("native"), 
    NATIVE_BANNER("native_banner"), 
    REWARDED_VIDEO("rewarded_video"), 
    UNKNOWN("unknown");
    
    private String a;
    
    private AdPlacementType(final String a) {
        this.a = a;
    }
    
    public static AdPlacementType fromString(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return AdPlacementType.UNKNOWN;
        }
        try {
            return valueOf(s.toUpperCase(Locale.US));
        }
        catch (Exception ex) {
            return AdPlacementType.UNKNOWN;
        }
    }
    
    @Override
    public String toString() {
        return this.a;
    }
}
