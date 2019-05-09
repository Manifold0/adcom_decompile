// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import android.support.annotation.Nullable;

final class zznu extends zznr
{
    @Nullable
    private static String zzam(@Nullable final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            int n = 0;
            final int length = s.length();
            int n2;
            while (true) {
                n2 = length;
                if (n >= s.length()) {
                    break;
                }
                n2 = length;
                if (s.charAt(n) != ',') {
                    break;
                }
                ++n;
            }
            while (n2 > 0 && s.charAt(n2 - 1) == ',') {
                --n2;
            }
            if (n2 < n) {
                return null;
            }
            if (n != 0 || n2 != s.length()) {
                return s.substring(n, n2);
            }
        }
        return s;
    }
    
    @Override
    public final String zzd(@Nullable String zzam, String zzam2) {
        zzam = zzam(zzam);
        zzam2 = zzam(zzam2);
        if (TextUtils.isEmpty((CharSequence)zzam)) {
            return zzam2;
        }
        if (TextUtils.isEmpty((CharSequence)zzam2)) {
            return zzam;
        }
        return new StringBuilder(String.valueOf(zzam).length() + 1 + String.valueOf(zzam2).length()).append(zzam).append(",").append(zzam2).toString();
    }
}
