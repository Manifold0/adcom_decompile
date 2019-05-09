package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.connection.DiscoveryOptions;

final class zzcq extends zzcy {
    private final /* synthetic */ String zzcn;
    private final /* synthetic */ DiscoveryOptions zzcr;
    private final /* synthetic */ ListenerHolder zzdg;

    zzcq(zzca zzca, GoogleApiClient googleApiClient, String str, ListenerHolder listenerHolder, DiscoveryOptions discoveryOptions) {
        this.zzcn = str;
        this.zzdg = listenerHolder;
        this.zzcr = discoveryOptions;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzx) anyClient).zza((ResultHolder) this, this.zzcn, this.zzdg, this.zzcr);
    }
}
