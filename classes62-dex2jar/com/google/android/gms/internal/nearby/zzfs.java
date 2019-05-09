// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

public final class zzfs
{
    private final zzfq zzed;
    
    public zzfs() {
        this.zzed = new zzfq(null);
    }
    
    public final zzfs zza(final zzdj zzdj) {
        this.zzed.zzec = zzdj;
        return this;
    }
    
    public final zzfs zza(final zzdm zzdm) {
        this.zzed.zzeb = zzdm;
        return this;
    }
    
    public final zzfs zzb(final zzdg zzdg) {
        this.zzed.zzas = zzdg;
        return this;
    }
    
    public final zzfs zzc(final byte[] array) {
        this.zzed.zzau = array;
        return this;
    }
    
    public final zzfs zzd(final zzdz zzdz) {
        this.zzed.zzar = zzdz;
        return this;
    }
    
    public final zzfs zzg(final String s) {
        this.zzed.name = s;
        return this;
    }
    
    public final zzfs zzh(final String s) {
        this.zzed.zzat = s;
        return this;
    }
    
    public final zzfq zzt() {
        return this.zzed;
    }
}
