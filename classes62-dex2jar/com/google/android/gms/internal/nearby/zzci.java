// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.nearby.connection.Strategy;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;

final class zzci extends zzcy
{
    private final /* synthetic */ String zzcn;
    private final /* synthetic */ long zzcy;
    private final /* synthetic */ ListenerHolder zzda;
    
    zzci(final zzca zzca, final GoogleApiClient googleApiClient, final String zzcn, final long zzcy, final ListenerHolder zzda) {
        this.zzcn = zzcn;
        this.zzcy = zzcy;
        this.zzda = zzda;
        super(googleApiClient, null);
    }
}
