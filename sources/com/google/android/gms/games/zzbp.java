package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.ListenerHolder.Notifier;
import com.google.android.gms.games.multiplayer.realtime.zzh;

final class zzbp implements Notifier<zzh> {
    private final /* synthetic */ zzbo zzds;

    zzbp(zzbo zzbo) {
        this.zzds = zzbo;
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((zzh) obj).onLeftRoom(0, this.zzds.zzdr.zzdg);
    }

    public final void onNotifyListenerFailed() {
        this.zzds.zzdr.zzdo.getRoomUpdateCallback().onLeftRoom(0, this.zzds.zzdr.zzdg);
    }
}
