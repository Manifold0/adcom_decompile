package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;

final class zzac extends zzau<ConnectionLifecycleCallback> {
    private final /* synthetic */ zzep zzbk;

    zzac(zzz zzz, zzep zzep) {
        this.zzbk = zzep;
        super();
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((ConnectionLifecycleCallback) obj).onDisconnected(this.zzbk.zzg());
    }
}
