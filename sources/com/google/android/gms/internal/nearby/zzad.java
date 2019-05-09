package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.zze;

final class zzad extends zzau<ConnectionLifecycleCallback> {
    private final /* synthetic */ zzef zzbl;

    zzad(zzz zzz, zzef zzef) {
        this.zzbl = zzef;
        super();
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        this.zzbl.zzg();
        zze zze = new zze(this.zzbl.getQuality());
    }
}
