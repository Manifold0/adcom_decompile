// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Result;

final class zzac implements Result
{
    private final /* synthetic */ Status zzbc;
    
    zzac(final zzab zzab, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
}
