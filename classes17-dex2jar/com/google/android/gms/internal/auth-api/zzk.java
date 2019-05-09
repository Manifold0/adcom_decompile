// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzk extends zzg
{
    private final /* synthetic */ zzj zzan;
    
    zzk(final zzj zzan) {
        this.zzan = zzan;
    }
    
    @Override
    public final void zzc(final Status status) {
        this.zzan.setResult((Result)zzh.zzd(status));
    }
    
    @Override
    public final void zzc(final Status status, final Credential credential) {
        this.zzan.setResult((Result)new zzh(status, credential));
    }
}
