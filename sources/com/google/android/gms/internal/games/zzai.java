package com.google.android.gms.internal.games;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.multiplayer.Invitations.LoadInvitationsResult;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;

public final class zzai implements Invitations {
    public final Intent getInvitationInboxIntent(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzz();
    }

    public final PendingResult<LoadInvitationsResult> loadInvitations(GoogleApiClient googleApiClient) {
        return loadInvitations(googleApiClient, 0);
    }

    public final PendingResult<LoadInvitationsResult> loadInvitations(GoogleApiClient googleApiClient, int i) {
        return googleApiClient.enqueue(new zzaj(this, googleApiClient, i));
    }

    public final void registerInvitationListener(GoogleApiClient googleApiClient, OnInvitationReceivedListener onInvitationReceivedListener) {
        zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzb(googleApiClient.registerListener(onInvitationReceivedListener));
        }
    }

    public final void unregisterInvitationListener(GoogleApiClient googleApiClient) {
        zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzab();
        }
    }
}
