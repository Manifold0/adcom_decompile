package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;

final class zzck extends zzcy {
    private final /* synthetic */ String zzcv;
    private final /* synthetic */ byte[] zzdb;
    private final /* synthetic */ ListenerHolder zzdd;

    zzck(zzca zzca, GoogleApiClient googleApiClient, String str, byte[] bArr, ListenerHolder listenerHolder) {
        this.zzcv = str;
        this.zzdb = bArr;
        this.zzdd = listenerHolder;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zzx zzx = (zzx) anyClient;
        String str = this.zzcv;
        ((zzdu) zzx.getService()).zza(new zzo().zza(new zzba(this)).zza(str).zza(this.zzdb).zza(new zzar(this.zzdd)).zzb());
    }
}
