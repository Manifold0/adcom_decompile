package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.connection.DiscoveryOptions.Builder;
import com.google.android.gms.nearby.connection.Strategy;

final class zzci extends zzcy {
    private final /* synthetic */ String zzcn;
    private final /* synthetic */ long zzcy;
    private final /* synthetic */ ListenerHolder zzda;

    zzci(zzca zzca, GoogleApiClient googleApiClient, String str, long j, ListenerHolder listenerHolder) {
        this.zzcn = str;
        this.zzcy = j;
        this.zzda = listenerHolder;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zzx zzx = (zzx) anyClient;
        String str = this.zzcn;
        long j = this.zzcy;
        ListenerHolder listenerHolder = this.zzda;
        ((zzdu) zzx.getService()).zza(new zzge().zzf(new zzba(this)).zzk(str).zze(j).zza(new zzao(listenerHolder)).zze(new Builder().setStrategy(Strategy.P2P_CLUSTER).build()).zzw());
    }
}
