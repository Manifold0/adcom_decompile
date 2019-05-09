// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.PendingResult;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;

@Deprecated
public interface Invitations
{
    Intent getInvitationInboxIntent(final GoogleApiClient p0);
    
    PendingResult<LoadInvitationsResult> loadInvitations(final GoogleApiClient p0);
    
    PendingResult<LoadInvitationsResult> loadInvitations(final GoogleApiClient p0, final int p1);
    
    void registerInvitationListener(final GoogleApiClient p0, final OnInvitationReceivedListener p1);
    
    void unregisterInvitationListener(final GoogleApiClient p0);
    
    @Deprecated
    public interface LoadInvitationsResult extends Releasable, Result
    {
        InvitationBuffer getInvitations();
    }
}
