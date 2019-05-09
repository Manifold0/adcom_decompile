package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzk extends zzc {
    private final /* synthetic */ String zzaq;

    zzk(GoogleApiClient googleApiClient, String str) {
        this.zzaq = str;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zza(this.zzaq, (ResultHolder) this);
    }
}
