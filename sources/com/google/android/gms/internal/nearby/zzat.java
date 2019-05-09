package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.Connections.MessageListener;

final class zzat extends zzau<MessageListener> {
    private final /* synthetic */ zzep zzbk;

    zzat(zzar zzar, zzep zzep) {
        this.zzbk = zzep;
        super();
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((MessageListener) obj).onDisconnected(this.zzbk.zzg());
    }
}
