package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.internal.nearby.zzha;
import com.google.android.gms.nearby.messages.SubscribeCallback;

final class zzbh extends zzha<SubscribeCallback> {
    zzbh() {
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((SubscribeCallback) obj).onExpired();
    }
}
