package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.ConnectionInfo;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;

final class zzaa extends zzau<ConnectionLifecycleCallback> {
    private final /* synthetic */ zzeh zzbh;

    zzaa(zzz zzz, zzeh zzeh) {
        this.zzbh = zzeh;
        super();
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((ConnectionLifecycleCallback) obj).onConnectionInitiated(this.zzbh.zzg(), new ConnectionInfo(this.zzbh.zzh(), this.zzbh.getAuthenticationToken(), this.zzbh.zzi()));
    }
}
