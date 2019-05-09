// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.events;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class Event<T>
{
    private final Class<T> zza;
    private final T zzb;
    
    @KeepForSdk
    public Event(final Class<T> clazz, final T t) {
        this.zza = (Class<T>)Preconditions.checkNotNull((Object)clazz);
        this.zzb = (T)Preconditions.checkNotNull((Object)t);
    }
    
    @KeepForSdk
    public T getPayload() {
        return this.zzb;
    }
    
    @KeepForSdk
    public Class<T> getType() {
        return this.zza;
    }
    
    @Override
    public String toString() {
        return String.format("Event{type: %s, payload: %s}", this.zza, this.zzb);
    }
}
