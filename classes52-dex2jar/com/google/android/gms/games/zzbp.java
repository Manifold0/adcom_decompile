// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.games.multiplayer.realtime.zzh;
import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;

final class zzbp implements ListenerHolder$Notifier<zzh>
{
    private final /* synthetic */ zzbo zzds;
    
    zzbp(final zzbo zzds) {
        this.zzds = zzds;
    }
    
    public final void onNotifyListenerFailed() {
        this.zzds.zzdr.zzdo.getRoomUpdateCallback().onLeftRoom(0, this.zzds.zzdr.zzdg);
    }
}
