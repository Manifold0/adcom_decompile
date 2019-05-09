package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;

final class zzas extends zzay {
    private final /* synthetic */ int zzjo;
    private final /* synthetic */ LeaderboardScoreBuffer zzjp;
    private final /* synthetic */ int zzjq;

    zzas(zzam zzam, GoogleApiClient googleApiClient, LeaderboardScoreBuffer leaderboardScoreBuffer, int i, int i2) {
        this.zzjp = leaderboardScoreBuffer;
        this.zzjo = i;
        this.zzjq = i2;
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zze) anyClient).zza((ResultHolder) this, this.zzjp, this.zzjo, this.zzjq);
    }
}
