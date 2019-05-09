package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.zze;

final class zzaq extends zzay {
    private final /* synthetic */ String zzbq;
    private final /* synthetic */ boolean zzjg;
    private final /* synthetic */ int zzjm;
    private final /* synthetic */ int zzjn;
    private final /* synthetic */ int zzjo;

    zzaq(zzam zzam, GoogleApiClient googleApiClient, String str, int i, int i2, int i3, boolean z) {
        this.zzbq = str;
        this.zzjm = i;
        this.zzjn = i2;
        this.zzjo = i3;
        this.zzjg = z;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zza(this, this.zzbq, this.zzjm, this.zzjn, this.zzjo, this.zzjg);
    }
}
