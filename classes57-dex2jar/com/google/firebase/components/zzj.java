// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.components;

import com.google.firebase.inject.Provider;

final class zzj<T> implements Provider<T>
{
    private static final Object zza;
    private volatile Object zzb;
    private volatile Provider<T> zzc;
    
    static {
        zza = new Object();
    }
    
    zzj(final ComponentFactory<T> componentFactory, final ComponentContainer componentContainer) {
        this.zzb = zzj.zza;
        this.zzc = (Provider<T>)zzk.zza(componentFactory, componentContainer);
    }
    
    @Override
    public final T get() {
        final Object zzb = this.zzb;
        if (zzb == zzj.zza) {
            synchronized (this) {
                Object zzb2;
                if ((zzb2 = this.zzb) == zzj.zza) {
                    zzb2 = this.zzc.get();
                    this.zzb = zzb2;
                    this.zzc = null;
                }
                return (T)zzb2;
            }
        }
        return (T)zzb;
    }
}
