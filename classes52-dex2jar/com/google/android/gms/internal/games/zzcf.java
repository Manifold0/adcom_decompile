// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.request.Requests;

final class zzcf implements LoadRequestsResult
{
    private final /* synthetic */ Status zzbc;
    
    zzcf(final zzce zzce, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final GameRequestBuffer getRequests(final int n) {
        return new GameRequestBuffer(DataHolder.empty(this.zzbc.getStatusCode()));
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
    
    public final void release() {
    }
}
