package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionResolution;

final class zzab extends zzau<ConnectionLifecycleCallback> {
    private final /* synthetic */ zzen zzbi;
    private final /* synthetic */ Status zzbj;

    zzab(zzz zzz, zzen zzen, Status status) {
        this.zzbi = zzen;
        this.zzbj = status;
        super();
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((ConnectionLifecycleCallback) obj).onConnectionResult(this.zzbi.zzg(), new ConnectionResolution(this.zzbj));
    }
}
