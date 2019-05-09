package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;

final class zzck extends zzcu {
    private final /* synthetic */ String zzka;
    private final /* synthetic */ boolean zzkb;
    private final /* synthetic */ int zzkc;

    zzck(zzci zzci, GoogleApiClient googleApiClient, String str, boolean z, int i) {
        this.zzka = str;
        this.zzkb = z;
        this.zzkc = i;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zza((ResultHolder) this, this.zzka, this.zzkb, this.zzkc);
    }
}
