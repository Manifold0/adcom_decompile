// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.TaskApiCall;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Continuation;

final class zzbn implements Continuation<Boolean, Task<Void>>
{
    final /* synthetic */ String zzdg;
    final /* synthetic */ RoomConfig zzdo;
    final /* synthetic */ ListenerHolder zzdp;
    private final /* synthetic */ RealTimeMultiplayerClient zzdq;
    
    zzbn(final RealTimeMultiplayerClient zzdq, final ListenerHolder zzdp, final String zzdg, final RoomConfig zzdo) {
        this.zzdq = zzdq;
        this.zzdp = zzdp;
        this.zzdg = zzdg;
        this.zzdo = zzdo;
    }
}
