package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.internal.zzt;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzz extends zzt<OnInvitationReceivedListener> {
    zzz(InvitationsClient invitationsClient, ListenerKey listenerKey) {
        super(listenerKey);
    }

    protected final void zzc(zze zze, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException, SecurityException {
        zze.zzaa();
        taskCompletionSource.setResult(Boolean.valueOf(true));
    }
}
