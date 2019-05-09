// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.internal.zzs;

final class zzy extends zzs<OnInvitationReceivedListener>
{
    private final /* synthetic */ ListenerHolder zzbi;
    
    zzy(final InvitationsClient invitationsClient, final ListenerHolder listenerHolder, final ListenerHolder zzbi) {
        this.zzbi = zzbi;
        super(listenerHolder);
    }
    
    @Override
    protected final void zzb(final zze zze, final TaskCompletionSource<Void> taskCompletionSource) throws RemoteException, SecurityException {
        zze.zza((ListenerHolder<OnInvitationReceivedListener>)this.zzbi);
        taskCompletionSource.setResult((Object)null);
    }
}
