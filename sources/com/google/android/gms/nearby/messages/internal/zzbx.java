package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.internal.nearby.zzha;
import com.google.android.gms.nearby.messages.SubscribeCallback;

final class zzbx extends zzha<SubscribeCallback> {
    zzbx() {
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((SubscribeCallback) obj).onExpired();
    }
}
