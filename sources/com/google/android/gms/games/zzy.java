package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.internal.zzs;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzy extends zzs<OnInvitationReceivedListener> {
    private final /* synthetic */ ListenerHolder zzbi;

    zzy(InvitationsClient invitationsClient, ListenerHolder listenerHolder, ListenerHolder listenerHolder2) {
        this.zzbi = listenerHolder2;
        super(listenerHolder);
    }

    protected final void zzb(zze zze, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException, SecurityException {
        zze.zza(this.zzbi);
        taskCompletionSource.setResult(null);
    }
}
