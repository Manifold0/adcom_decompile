// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzh implements zzab
{
    private int zzr;
    private int zzs;
    private final int zzt;
    private final float zzu;
    
    public zzh() {
        this(2500, 1, 1.0f);
    }
    
    private zzh(final int n, final int n2, final float n3) {
        this.zzr = 2500;
        this.zzt = 1;
        this.zzu = 1.0f;
    }
    
    @Override
    public final void zza(final zzae zzae) throws zzae {
        ++this.zzs;
        this.zzr += (int)(this.zzr * this.zzu);
        int n;
        if (this.zzs <= this.zzt) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n == 0) {
            throw zzae;
        }
    }
    
    @Override
    public final int zzc() {
        return this.zzr;
    }
    
    @Override
    public final int zzd() {
        return this.zzs;
    }
}
