package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.DiscoveryOptions;

public final class zzge {
    private final zzgc zzeo = new zzgc();

    public final zzge zza(zzdr zzdr) {
        this.zzeo.zzen = zzdr;
        return this;
    }

    public final zzge zze(long j) {
        this.zzeo.durationMillis = j;
        return this;
    }

    public final zzge zze(DiscoveryOptions discoveryOptions) {
        this.zzeo.zzem = discoveryOptions;
        return this;
    }

    public final zzge zzf(zzdz zzdz) {
        this.zzeo.zzar = zzdz;
        return this;
    }

    public final zzge zzk(String str) {
        this.zzeo.zzu = str;
        return this;
    }

    public final zzgc zzw() {
        return this.zzeo;
    }
}
