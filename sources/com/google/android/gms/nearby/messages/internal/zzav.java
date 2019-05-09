package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.internal.ListenerHolder;

final class zzav extends zzbe {
    private final /* synthetic */ ListenerHolder zzhz;
    private final /* synthetic */ zzak zzia;

    zzav(zzak zzak, ListenerHolder listenerHolder, ListenerHolder listenerHolder2) {
        this.zzia = zzak;
        this.zzhz = listenerHolder2;
        super(listenerHolder);
    }

    public final void onExpired() {
        this.zzia.doUnregisterEventListener(this.zzhz.getListenerKey());
        super.onExpired();
    }
}
