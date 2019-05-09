// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.ParcelFileDescriptor;

public final class zzfj
{
    private final zzfh zzdz;
    
    public zzfj() {
        this.zzdz = new zzfh(null);
    }
    
    public final zzfj zzb(final long n) {
        this.zzdz.id = n;
        return this;
    }
    
    public final zzfj zzb(final byte[] array) {
        this.zzdz.zzy = array;
        return this;
    }
    
    public final zzfj zzc(final long n) {
        this.zzdz.zzdx = n;
        return this;
    }
    
    public final zzfj zzc(final ParcelFileDescriptor parcelFileDescriptor) {
        this.zzdz.zzdv = parcelFileDescriptor;
        return this;
    }
    
    public final zzfj zzd(final int n) {
        this.zzdz.type = n;
        return this;
    }
    
    public final zzfj zzd(final ParcelFileDescriptor parcelFileDescriptor) {
        this.zzdz.zzdy = parcelFileDescriptor;
        return this;
    }
    
    public final zzfj zze(final String s) {
        this.zzdz.zzdw = s;
        return this;
    }
    
    public final zzfh zzr() {
        return this.zzdz;
    }
}
