// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.auth.api.proxy.ProxyResponse;

final class zzat extends zzaj
{
    private final /* synthetic */ zzas zzcf;
    
    zzat(final zzas zzcf) {
        this.zzcf = zzcf;
    }
    
    @Override
    public final void zza(final ProxyResponse proxyResponse) {
        this.zzcf.setResult((Result)new zzaw(proxyResponse));
    }
}
