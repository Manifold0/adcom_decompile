package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;

final class zzcj extends zzcy {
    private final /* synthetic */ String val$name;
    private final /* synthetic */ String zzcv;
    private final /* synthetic */ byte[] zzdb;
    private final /* synthetic */ ListenerHolder zzdc;
    private final /* synthetic */ ListenerHolder zzdd;

    zzcj(zzca zzca, GoogleApiClient googleApiClient, String str, String str2, byte[] bArr, ListenerHolder listenerHolder, ListenerHolder listenerHolder2) {
        this.val$name = str;
        this.zzcv = str2;
        this.zzdb = bArr;
        this.zzdc = listenerHolder;
        this.zzdd = listenerHolder2;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zzx zzx = (zzx) anyClient;
        String str = this.val$name;
        String str2 = this.zzcv;
        byte[] bArr = this.zzdb;
        ListenerHolder listenerHolder = this.zzdc;
        ((zzdu) zzx.getService()).zza(new zzfs().zzd(new zzba(this)).zzg(str).zzh(str2).zzc(bArr).zzb(new zzar(this.zzdd)).zza(new zzai(listenerHolder)).zzt());
    }
}
