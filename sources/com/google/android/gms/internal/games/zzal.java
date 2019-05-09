package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.Invitations.LoadInvitationsResult;

final class zzal implements LoadInvitationsResult {
    private final /* synthetic */ Status zzbc;

    zzal(zzak zzak, Status status) {
        this.zzbc = status;
    }

    public final InvitationBuffer getInvitations() {
        return new InvitationBuffer(DataHolder.empty(14));
    }

    public final Status getStatus() {
        return this.zzbc;
    }

    public final void release() {
    }
}
