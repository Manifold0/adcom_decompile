// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.games.multiplayer.realtime.zzh;
import com.google.android.gms.games.internal.zzt;

final class zzbk extends zzt<zzh>
{
    zzbk(final RealTimeMultiplayerClient realTimeMultiplayerClient, final ListenerHolder$ListenerKey listenerHolder$ListenerKey) {
        super(listenerHolder$ListenerKey);
    }
    
    @Override
    protected final void zzc(final zze zze, final TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException, SecurityException {
        taskCompletionSource.setResult((Object)true);
    }
}
