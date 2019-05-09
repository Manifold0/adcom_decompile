package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.AdvertisingOptions;

public final class zzga {
    private final zzfy zzek = new zzfy();

    public final zzga zza(zzdd zzdd) {
        this.zzek.zzei = zzdd;
        return this;
    }

    public final zzga zza(zzec zzec) {
        this.zzek.zzeh = zzec;
        return this;
    }

    public final zzga zzb(zzdj zzdj) {
        this.zzek.zzec = zzdj;
        return this;
    }

    public final zzga zzd(long j) {
        this.zzek.durationMillis = j;
        return this;
    }

    public final zzga zzg(AdvertisingOptions advertisingOptions) {
        this.zzek.zzej = advertisingOptions;
        return this;
    }

    public final zzga zzi(String str) {
        this.zzek.name = str;
        return this;
    }

    public final zzga zzj(String str) {
        this.zzek.zzu = str;
        return this;
    }

    public final zzfy zzv() {
        return this.zzek;
    }
}
