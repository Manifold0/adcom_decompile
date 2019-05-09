package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games.zza;
import com.google.android.gms.games.multiplayer.Invitations.LoadInvitationsResult;

abstract class zzak extends zza<LoadInvitationsResult> {
    private zzak(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    public /* synthetic */ Result createFailedResult(Status status) {
        return new zzal(this, status);
    }
}
