// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

final class zzs implements OnTokenCanceledListener
{
    private final /* synthetic */ TaskCompletionSource zzv;
    
    zzs(final TaskCompletionSource zzv) {
        this.zzv = zzv;
    }
    
    @Override
    public final void onCanceled() {
        this.zzv.zza.zza();
    }
}
