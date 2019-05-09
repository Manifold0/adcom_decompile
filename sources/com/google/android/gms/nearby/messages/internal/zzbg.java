package com.google.android.gms.nearby.messages.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.internal.nearby.zzha;
import com.google.android.gms.nearby.messages.SubscribeCallback;

class zzbg extends zzab {
    private static final zzha<SubscribeCallback> zzih = new zzbh();
    @Nullable
    private final ListenerHolder<SubscribeCallback> zzii;

    public zzbg(@Nullable ListenerHolder<SubscribeCallback> listenerHolder) {
        this.zzii = listenerHolder;
    }

    public void onExpired() {
        if (this.zzii != null) {
            this.zzii.notifyListener(zzih);
        }
    }
}
