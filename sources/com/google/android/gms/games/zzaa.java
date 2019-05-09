package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.Invitations.LoadInvitationsResult;

final class zzaa implements ResultConverter<LoadInvitationsResult, InvitationBuffer> {
    zzaa() {
    }

    public final /* synthetic */ Object convert(Result result) {
        LoadInvitationsResult loadInvitationsResult = (LoadInvitationsResult) result;
        return loadInvitationsResult == null ? null : loadInvitationsResult.getInvitations();
    }
}
