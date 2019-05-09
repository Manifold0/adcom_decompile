// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzia
{
    public static int zzd(final int n) {
        if ((n < 0 || n > 1) && (n < 1000 || n > 1000)) {
            throw new IllegalArgumentException(new StringBuilder(43).append(n).append(" is not a valid enum EnumBoolean").toString());
        }
        return n;
    }
}
