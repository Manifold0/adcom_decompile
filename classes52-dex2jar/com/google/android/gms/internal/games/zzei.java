// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

public final class zzei
{
    public static String zzn(final int n) {
        switch (n) {
            default: {
                throw new IllegalArgumentException(new StringBuilder(29).append("Unknown time span ").append(n).toString());
            }
            case 0: {
                return "DAILY";
            }
            case 1: {
                return "WEEKLY";
            }
            case 2: {
                return "ALL_TIME";
            }
        }
    }
}
