// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.games.internal.zze;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.multiplayer.Invitations;

public final class zzai implements Invitations
{
    @Override
    public final Intent getInvitationInboxIntent(final GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzz();
    }
    
    @Override
    public final PendingResult<LoadInvitationsResult> loadInvitations(final GoogleApiClient googleApiClient) {
        return this.loadInvitations(googleApiClient, 0);
    }
    
    @Override
    public final PendingResult<LoadInvitationsResult> loadInvitations(final GoogleApiClient googleApiClient, final int n) {
        return (PendingResult<LoadInvitationsResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzaj(this, googleApiClient, n));
    }
    
    @Override
    public final void registerInvitationListener(final GoogleApiClient googleApiClient, final OnInvitationReceivedListener onInvitationReceivedListener) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzb((ListenerHolder<OnInvitationReceivedListener>)googleApiClient.registerListener((Object)onInvitationReceivedListener));
        }
    }
    
    @Override
    public final void unregisterInvitationListener(final GoogleApiClient googleApiClient) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzab();
        }
    }
}
