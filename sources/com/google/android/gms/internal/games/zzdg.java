package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.multiplayer.ParticipantResult;

final class zzdg extends zzdw {
    private final /* synthetic */ String zzew;
    private final /* synthetic */ byte[] zzkj;
    private final /* synthetic */ String zzkk;
    private final /* synthetic */ ParticipantResult[] zzkl;

    zzdg(zzdb zzdb, GoogleApiClient googleApiClient, String str, byte[] bArr, String str2, ParticipantResult[] participantResultArr) {
        this.zzew = str;
        this.zzkj = bArr;
        this.zzkk = str2;
        this.zzkl = participantResultArr;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zza((ResultHolder) this, this.zzew, this.zzkj, this.zzkk, this.zzkl);
    }
}
