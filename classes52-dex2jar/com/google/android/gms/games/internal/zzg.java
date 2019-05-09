// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.BaseGmsClient$SignOutCallbacks;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzg implements BaseImplementation$ResultHolder<Status>
{
    private final /* synthetic */ BaseGmsClient$SignOutCallbacks zzgb;
    
    zzg(final zze zze, final BaseGmsClient$SignOutCallbacks zzgb) {
        this.zzgb = zzgb;
    }
    
    public final void setFailedResult(final Status status) {
        this.zzgb.onSignOutComplete();
    }
}
