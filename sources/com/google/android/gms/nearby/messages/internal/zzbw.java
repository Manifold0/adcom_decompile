package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.internal.nearby.zzha;
import com.google.android.gms.nearby.messages.SubscribeCallback;

final class zzbw extends zzab {
    private static final zzha<SubscribeCallback> zzih = new zzbx();
    private final ListenerHolder<SubscribeCallback> zzii;

    public zzbw(ListenerHolder<SubscribeCallback> listenerHolder) {
        this.zzii = listenerHolder;
    }

    public final void onExpired() {
        this.zzii.notifyListener(zzih);
    }
}
