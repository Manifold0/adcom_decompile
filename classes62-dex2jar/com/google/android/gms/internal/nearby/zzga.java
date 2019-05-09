// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.AdvertisingOptions;

public final class zzga
{
    private final zzfy zzek;
    
    public zzga() {
        this.zzek = new zzfy(null);
    }
    
    public final zzga zza(final zzdd zzdd) {
        this.zzek.zzei = zzdd;
        return this;
    }
    
    public final zzga zza(final zzec zzec) {
        this.zzek.zzeh = zzec;
        return this;
    }
    
    public final zzga zzb(final zzdj zzdj) {
        this.zzek.zzec = zzdj;
        return this;
    }
    
    public final zzga zzd(final long n) {
        this.zzek.durationMillis = n;
        return this;
    }
    
    public final zzga zzg(final AdvertisingOptions advertisingOptions) {
        this.zzek.zzej = advertisingOptions;
        return this;
    }
    
    public final zzga zzi(final String s) {
        this.zzek.name = s;
        return this;
    }
    
    public final zzga zzj(final String s) {
        this.zzek.zzu = s;
        return this;
    }
    
    public final zzfy zzv() {
        return this.zzek;
    }
}
