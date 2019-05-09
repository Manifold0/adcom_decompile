// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public enum zzayd implements zzbbr
{
    private static final zzbbs<zzayd> zzall;
    
    zzdmk(0), 
    zzdml(1), 
    zzdmm(2), 
    zzdmn(3), 
    zzdmo(4), 
    zzdmp(-1);
    
    private final int value;
    
    static {
        zzall = (zzbbs)new zzaye();
    }
    
    private zzayd(final int value) {
        this.value = value;
    }
    
    public static zzayd zzbg(final int n) {
        switch (n) {
            default: {
                return null;
            }
            case 0: {
                return zzayd.zzdmk;
            }
            case 1: {
                return zzayd.zzdml;
            }
            case 2: {
                return zzayd.zzdmm;
            }
            case 3: {
                return zzayd.zzdmn;
            }
            case 4: {
                return zzayd.zzdmo;
            }
        }
    }
    
    public final int zzhq() {
        if (this == zzayd.zzdmp) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        return this.value;
    }
}
