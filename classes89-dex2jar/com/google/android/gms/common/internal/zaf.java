// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.support.annotation.Nullable;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;

final class zaf implements BaseGmsClient$BaseConnectionCallbacks
{
    private final /* synthetic */ GoogleApiClient.ConnectionCallbacks zaoj;
    
    zaf(final GoogleApiClient.ConnectionCallbacks zaoj) {
        this.zaoj = zaoj;
    }
    
    public final void onConnected(@Nullable final Bundle bundle) {
        this.zaoj.onConnected(bundle);
    }
    
    public final void onConnectionSuspended(final int n) {
        this.zaoj.onConnectionSuspended(n);
    }
}
