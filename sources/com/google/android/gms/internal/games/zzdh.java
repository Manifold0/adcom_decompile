package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.multiplayer.ParticipantResult;

final class zzdh extends zzdw {
    private final /* synthetic */ String zzew;
    private final /* synthetic */ byte[] zzkj;
    private final /* synthetic */ ParticipantResult[] zzkl;

    zzdh(zzdb zzdb, GoogleApiClient googleApiClient, String str, byte[] bArr, ParticipantResult[] participantResultArr) {
        this.zzew = str;
        this.zzkj = bArr;
        this.zzkl = participantResultArr;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zza((ResultHolder) this, this.zzew, this.zzkj, this.zzkl);
    }
}
