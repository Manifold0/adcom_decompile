package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.ConnectionInfo;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionResolution;

final class zzbx extends ConnectionLifecycleCallback {
    private final /* synthetic */ zzbd zzcq;
    private final ConnectionLifecycleCallback zzct;

    zzbx(zzbd zzbd, ConnectionLifecycleCallback connectionLifecycleCallback) {
        this.zzcq = zzbd;
        this.zzct = connectionLifecycleCallback;
    }

    public final void onConnectionInitiated(String str, ConnectionInfo connectionInfo) {
        if (connectionInfo.isIncomingConnection()) {
            this.zzcq.zzb(str);
        }
        this.zzct.onConnectionInitiated(str, connectionInfo);
    }

    public final void onConnectionResult(String str, ConnectionResolution connectionResolution) {
        if (!connectionResolution.getStatus().isSuccess()) {
            this.zzcq.zzc(str);
        }
        this.zzct.onConnectionResult(str, connectionResolution);
    }

    public final void onDisconnected(String str) {
        this.zzcq.zzc(str);
        this.zzct.onDisconnected(str);
    }
}
