// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.components;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class Dependency
{
    private final Class<?> zza;
    private final int zzb;
    private final int zzc;
    
    private Dependency(final Class<?> clazz, final int zzb, final int zzc) {
        this.zza = (Class<?>)Preconditions.checkNotNull((Object)clazz, (Object)"Null dependency anInterface.");
        this.zzb = zzb;
        this.zzc = zzc;
    }
    
    @KeepForSdk
    public static Dependency optional(final Class<?> clazz) {
        return new Dependency(clazz, 0, 0);
    }
    
    @KeepForSdk
    public static Dependency optionalProvider(final Class<?> clazz) {
        return new Dependency(clazz, 0, 1);
    }
    
    @KeepForSdk
    public static Dependency required(final Class<?> clazz) {
        return new Dependency(clazz, 1, 0);
    }
    
    @KeepForSdk
    public static Dependency requiredProvider(final Class<?> clazz) {
        return new Dependency(clazz, 1, 1);
    }
    
    @Override
    public final boolean equals(final Object o) {
        boolean b2;
        final boolean b = b2 = false;
        if (o instanceof Dependency) {
            final Dependency dependency = (Dependency)o;
            b2 = b;
            if (this.zza == dependency.zza) {
                b2 = b;
                if (this.zzb == dependency.zzb) {
                    b2 = b;
                    if (this.zzc == dependency.zzc) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public final int hashCode() {
        return ((this.zza.hashCode() ^ 0xF4243) * 1000003 ^ this.zzb) * 1000003 ^ this.zzc;
    }
    
    @Override
    public final String toString() {
        final boolean b = true;
        return "Dependency{anInterface=" + this.zza + ", required=" + (this.zzb == 1) + ", direct=" + (this.zzc == 0 && b) + "}";
    }
    
    public final Class<?> zza() {
        return this.zza;
    }
    
    public final boolean zzb() {
        return this.zzb == 1;
    }
    
    public final boolean zzc() {
        return this.zzc == 0;
    }
}
