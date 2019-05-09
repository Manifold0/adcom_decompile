// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import android.text.TextUtils;
import android.support.annotation.Nullable;
import java.util.regex.Pattern;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@VisibleForTesting
public class Strings
{
    private static final Pattern zzhh;
    
    static {
        zzhh = Pattern.compile("\\$\\{(.*?)\\}");
    }
    
    private Strings() {
    }
    
    @Nullable
    @KeepForSdk
    public static String emptyToNull(@Nullable final String s) {
        String s2 = s;
        if (TextUtils.isEmpty((CharSequence)s)) {
            s2 = null;
        }
        return s2;
    }
    
    @KeepForSdk
    public static boolean isEmptyOrWhitespace(@Nullable final String s) {
        return s == null || s.trim().isEmpty();
    }
}
