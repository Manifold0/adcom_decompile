// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import java.util.concurrent.atomic.AtomicReference;
import com.google.android.gms.common.api.GoogleApiClient;

final class zaay implements ConnectionCallbacks
{
    private final /* synthetic */ zaaw zahh;
    private final /* synthetic */ AtomicReference zahi;
    private final /* synthetic */ StatusPendingResult zahj;
    
    zaay(final zaaw zahh, final AtomicReference zahi, final StatusPendingResult zahj) {
        this.zahh = zahh;
        this.zahi = zahi;
        this.zahj = zahj;
    }
    
    @Override
    public final void onConnected(final Bundle bundle) {
        zaaw.zaa(this.zahh, this.zahi.get(), this.zahj, true);
    }
    
    @Override
    public final void onConnectionSuspended(final int n) {
    }
}
