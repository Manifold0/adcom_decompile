// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.DiscoveryOptions;

public final class zzge
{
    private final zzgc zzeo;
    
    public zzge() {
        this.zzeo = new zzgc(null);
    }
    
    public final zzge zza(final zzdr zzdr) {
        this.zzeo.zzen = zzdr;
        return this;
    }
    
    public final zzge zze(final long n) {
        this.zzeo.durationMillis = n;
        return this;
    }
    
    public final zzge zze(final DiscoveryOptions discoveryOptions) {
        this.zzeo.zzem = discoveryOptions;
        return this;
    }
    
    public final zzge zzf(final zzdz zzdz) {
        this.zzeo.zzar = zzdz;
        return this;
    }
    
    public final zzge zzk(final String s) {
        this.zzeo.zzu = s;
        return this;
    }
    
    public final zzgc zzw() {
        return this.zzeo;
    }
}
