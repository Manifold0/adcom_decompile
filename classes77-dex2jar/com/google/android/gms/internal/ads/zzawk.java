// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public enum zzawk implements zzbbr
{
    private static final zzbbs<zzawk> zzall;
    
    zzdjc(0), 
    zzdjd(1), 
    zzdje(2), 
    zzdjf(-1);
    
    private final int value;
    
    static {
        zzall = (zzbbs)new zzawl();
    }
    
    private zzawk(final int value) {
        this.value = value;
    }
    
    public static zzawk zzaq(final int n) {
        switch (n) {
            default: {
                return null;
            }
            case 0: {
                return zzawk.zzdjc;
            }
            case 1: {
                return zzawk.zzdjd;
            }
            case 2: {
                return zzawk.zzdje;
            }
        }
    }
    
    public final int zzhq() {
        if (this == zzawk.zzdjf) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        return this.value;
    }
}
