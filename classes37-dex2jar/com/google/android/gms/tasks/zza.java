// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

final class zza extends CancellationToken
{
    private final zzu<Void> zza;
    
    zza() {
        this.zza = new zzu<Void>();
    }
    
    public final void cancel() {
        this.zza.trySetResult(null);
    }
    
    @Override
    public final boolean isCancellationRequested() {
        return this.zza.isComplete();
    }
    
    @Override
    public final CancellationToken onCanceledRequested(@NonNull final OnTokenCanceledListener onTokenCanceledListener) {
        this.zza.addOnSuccessListener(new zzb(this, onTokenCanceledListener));
        return this;
    }
}
