package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.internal.nearby.zzha;
import com.google.android.gms.nearby.messages.PublishCallback;

final class zzbu extends zzha<PublishCallback> {
    zzbu() {
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((PublishCallback) obj).onExpired();
    }
}
