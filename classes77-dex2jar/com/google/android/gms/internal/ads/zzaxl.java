// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public enum zzaxl implements zzbbr
{
    private static final zzbbs<zzaxl> zzall;
    
    zzdld(0), 
    zzdle(1), 
    zzdlf(2), 
    zzdlg(3), 
    zzdlh(-1);
    
    private final int value;
    
    static {
        zzall = (zzbbs)new zzaxm();
    }
    
    private zzaxl(final int value) {
        this.value = value;
    }
    
    public static zzaxl zzax(final int n) {
        switch (n) {
            default: {
                return null;
            }
            case 0: {
                return zzaxl.zzdld;
            }
            case 1: {
                return zzaxl.zzdle;
            }
            case 2: {
                return zzaxl.zzdlf;
            }
            case 3: {
                return zzaxl.zzdlg;
            }
        }
    }
    
    public final int zzhq() {
        if (this == zzaxl.zzdlh) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        return this.value;
    }
}
