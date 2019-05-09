// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public enum zzawy implements zzbbr
{
    private static final zzbbs<zzawy> zzall;
    
    zzdjy(0), 
    zzdjz(1), 
    zzdka(2), 
    zzdkb(3), 
    zzdkc(4), 
    zzdkd(-1);
    
    private final int value;
    
    static {
        zzall = (zzbbs)new zzawz();
    }
    
    private zzawy(final int value) {
        this.value = value;
    }
    
    public static zzawy zzat(final int n) {
        switch (n) {
            default: {
                return null;
            }
            case 0: {
                return zzawy.zzdjy;
            }
            case 1: {
                return zzawy.zzdjz;
            }
            case 2: {
                return zzawy.zzdka;
            }
            case 3: {
                return zzawy.zzdkb;
            }
            case 4: {
                return zzawy.zzdkc;
            }
        }
    }
    
    public final int zzhq() {
        if (this == zzawy.zzdkd) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        return this.value;
    }
}
