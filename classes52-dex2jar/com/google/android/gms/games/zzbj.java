// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.multiplayer.realtime.zzh;
import com.google.android.gms.games.internal.zzs;

final class zzbj extends zzs<zzh>
{
    private final /* synthetic */ ListenerHolder zzbi;
    private final /* synthetic */ RoomConfig zzdo;
    
    zzbj(final RealTimeMultiplayerClient realTimeMultiplayerClient, final ListenerHolder listenerHolder, final ListenerHolder zzbi, final RoomConfig zzdo) {
        this.zzbi = zzbi;
        this.zzdo = zzdo;
        super(listenerHolder);
    }
    
    @Override
    protected final void zzb(final zze zze, final TaskCompletionSource<Void> taskCompletionSource) throws RemoteException, SecurityException {
        zze.zza((ListenerHolder<? extends RoomUpdateListener>)this.zzbi, (ListenerHolder<? extends RoomStatusUpdateListener>)this.zzbi, (ListenerHolder<? extends RealTimeMessageReceivedListener>)this.zzbi, this.zzdo);
        taskCompletionSource.setResult((Object)null);
    }
}
