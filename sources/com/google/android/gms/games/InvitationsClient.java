package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.Games.GamesOptions;
import com.google.android.gms.games.internal.zzi;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.InvitationCallback;
import com.google.android.gms.games.multiplayer.Invitations.LoadInvitationsResult;
import com.google.android.gms.internal.games.zzu;
import com.google.android.gms.tasks.Task;

public class InvitationsClient extends zzu {
    private static final ResultConverter<LoadInvitationsResult, InvitationBuffer> zzbh = new zzaa();

    InvitationsClient(@NonNull Activity activity, @NonNull GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    InvitationsClient(@NonNull Context context, @NonNull GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<Intent> getInvitationInboxIntent() {
        return doRead(new zzx(this));
    }

    public Task<AnnotatedData<InvitationBuffer>> loadInvitations() {
        return loadInvitations(0);
    }

    public Task<AnnotatedData<InvitationBuffer>> loadInvitations(int i) {
        return zzi.zzb(Games.Invitations.loadInvitations(asGoogleApiClient(), i), zzbh);
    }

    public Task<Void> registerInvitationCallback(@NonNull InvitationCallback invitationCallback) {
        ListenerHolder registerListener = registerListener(invitationCallback, InvitationCallback.class.getSimpleName());
        return doRegisterEventListener(new zzy(this, registerListener, registerListener), new zzz(this, registerListener.getListenerKey()));
    }

    public Task<Boolean> unregisterInvitationCallback(@NonNull InvitationCallback invitationCallback) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(invitationCallback, InvitationCallback.class.getSimpleName()));
    }
}
