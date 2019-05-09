// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import java.util.regex.Pattern;

public final class zzb
{
    private static Pattern zzgx;
    
    static {
        zzb.zzgx = null;
    }
    
    public static int zzc(final int n) {
        if (n == -1) {
            return -1;
        }
        return n / 1000;
    }
}
