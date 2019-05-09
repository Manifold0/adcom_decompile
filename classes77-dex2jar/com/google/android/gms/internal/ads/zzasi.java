// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

@zzadh
public final class zzasi
{
    public final int heightPixels;
    private final int type;
    public final int widthPixels;
    
    private zzasi(final int type, final int widthPixels, final int heightPixels) {
        this.type = type;
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
    }
    
    public static zzasi zzb(final zzjn zzjn) {
        if (zzjn.zzarc) {
            return new zzasi(3, 0, 0);
        }
        if (zzjn.zzarf) {
            return new zzasi(2, 0, 0);
        }
        if (zzjn.zzare) {
            return zzvq();
        }
        return zzi(zzjn.widthPixels, zzjn.heightPixels);
    }
    
    public static zzasi zzi(final int n, final int n2) {
        return new zzasi(1, n, n2);
    }
    
    public static zzasi zzvq() {
        return new zzasi(0, 0, 0);
    }
    
    public static zzasi zzvr() {
        return new zzasi(4, 0, 0);
    }
    
    public final boolean isFluid() {
        return this.type == 2;
    }
    
    public final boolean zzvs() {
        return this.type == 3;
    }
    
    public final boolean zzvt() {
        return this.type == 0;
    }
    
    public final boolean zzvu() {
        return this.type == 4;
    }
}
