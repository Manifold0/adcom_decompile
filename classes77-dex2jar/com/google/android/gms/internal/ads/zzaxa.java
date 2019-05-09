// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public enum zzaxa implements zzbbr
{
    private static final zzbbs<zzaxa> zzall;
    
    zzdkf(0), 
    zzdkg(1), 
    zzdkh(2), 
    zzdki(3), 
    zzdkj(4), 
    zzdkk(-1);
    
    private final int value;
    
    static {
        zzall = (zzbbs)new zzaxb();
    }
    
    private zzaxa(final int value) {
        this.value = value;
    }
    
    public static zzaxa zzau(final int n) {
        switch (n) {
            default: {
                return null;
            }
            case 0: {
                return zzaxa.zzdkf;
            }
            case 1: {
                return zzaxa.zzdkg;
            }
            case 2: {
                return zzaxa.zzdkh;
            }
            case 3: {
                return zzaxa.zzdki;
            }
            case 4: {
                return zzaxa.zzdkj;
            }
        }
    }
    
    public final int zzhq() {
        if (this == zzaxa.zzdkk) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        return this.value;
    }
}
