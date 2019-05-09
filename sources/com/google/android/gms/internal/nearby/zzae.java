package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionResolution;

final class zzae extends zzau<ConnectionLifecycleCallback> {
    private final /* synthetic */ String zzbm;

    zzae(zzz zzz, String str) {
        this.zzbm = str;
        super();
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((ConnectionLifecycleCallback) obj).onConnectionResult(this.zzbm, new ConnectionResolution(zzx.zza(13)));
    }
}
