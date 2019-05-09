package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.internal.ListenerHolder;

final class zzaw extends zzbg {
    private final /* synthetic */ ListenerHolder zzco;
    private final /* synthetic */ zzak zzia;

    zzaw(zzak zzak, ListenerHolder listenerHolder, ListenerHolder listenerHolder2) {
        this.zzia = zzak;
        this.zzco = listenerHolder2;
        super(listenerHolder);
    }

    public final void onExpired() {
        this.zzia.doUnregisterEventListener(this.zzco.getListenerKey());
        super.onExpired();
    }
}
