package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;

final class zzdc extends zzdo {
    private final /* synthetic */ TurnBasedMatchConfig zzki;

    zzdc(zzdb zzdb, GoogleApiClient googleApiClient, TurnBasedMatchConfig turnBasedMatchConfig) {
        this.zzki = turnBasedMatchConfig;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zza((ResultHolder) this, this.zzki);
    }
}
