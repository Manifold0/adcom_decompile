// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.internal.zzt;

final class zzz extends zzt<OnInvitationReceivedListener>
{
    zzz(final InvitationsClient invitationsClient, final ListenerHolder$ListenerKey listenerHolder$ListenerKey) {
        super(listenerHolder$ListenerKey);
    }
    
    @Override
    protected final void zzc(final zze zze, final TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException, SecurityException {
        zze.zzaa();
        taskCompletionSource.setResult((Object)true);
    }
}
