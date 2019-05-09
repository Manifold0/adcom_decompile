// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.Status;

final class zzm implements GetServerAuthCodeResult
{
    private final /* synthetic */ Status zzbc;
    
    zzm(final zzc zzc, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final String getCode() {
        return null;
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
}
