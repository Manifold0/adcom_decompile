package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;

final class zzaf extends zzau<ConnectionLifecycleCallback> {
    private final /* synthetic */ String zzbm;

    zzaf(zzz zzz, String str) {
        this.zzbm = str;
        super();
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((ConnectionLifecycleCallback) obj).onDisconnected(this.zzbm);
    }
}
