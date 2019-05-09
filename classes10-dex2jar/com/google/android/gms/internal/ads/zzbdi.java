// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzbdi implements zzbcs
{
    private final String info;
    private final zzbcu zzdwl;
    private final zzbdj zzdxe;
    
    zzbdi(final zzbcu zzdwl, final String info, final Object[] array) {
        this.zzdwl = zzdwl;
        this.info = info;
        this.zzdxe = new zzbdj(zzdwl.getClass(), info, array);
    }
    
    public final int getFieldCount() {
        return this.zzdxe.zzdxh;
    }
    
    @Override
    public final int zzaeh() {
        if ((this.zzdxe.flags & 0x1) == 0x1) {
            return zzbbo.zze.zzdui;
        }
        return zzbbo.zze.zzduj;
    }
    
    @Override
    public final boolean zzaei() {
        return (this.zzdxe.flags & 0x2) == 0x2;
    }
    
    @Override
    public final zzbcu zzaej() {
        return this.zzdwl;
    }
    
    final zzbdj zzaeq() {
        return this.zzdxe;
    }
    
    public final int zzaer() {
        return this.zzdxe.zzdwi;
    }
    
    public final int zzaes() {
        return this.zzdxe.zzdwj;
    }
    
    public final int zzaet() {
        return this.zzdxe.zzdxk;
    }
    
    public final int zzaeu() {
        return this.zzdxe.zzdxm;
    }
    
    final int[] zzaev() {
        return this.zzdxe.zzdwq;
    }
    
    public final int zzaew() {
        return this.zzdxe.zzdxl;
    }
    
    public final int zzaex() {
        return this.zzdxe.zzdwk;
    }
}
