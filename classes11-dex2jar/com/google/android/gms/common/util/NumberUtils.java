// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@VisibleForTesting
public class NumberUtils
{
    private NumberUtils() {
    }
    
    @KeepForSdk
    public static long parseHexLong(final String s) {
        if (s.length() > 16) {
            throw new NumberFormatException(new StringBuilder(String.valueOf(s).length() + 37).append("Invalid input: ").append(s).append(" exceeds 16 characters").toString());
        }
        if (s.length() == 16) {
            return Long.parseLong(s.substring(8), 16) | Long.parseLong(s.substring(0, 8), 16) << 32;
        }
        return Long.parseLong(s, 16);
    }
}
