package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;

final /* synthetic */ class zzbf implements zzbw {
    private final String zzce;
    private final ListenerHolder zzch;

    zzbf(String str, ListenerHolder listenerHolder) {
        this.zzce = str;
        this.zzch = listenerHolder;
    }

    public final void zza(zzx zzx, ResultHolder resultHolder) {
        zzx.zza(resultHolder, this.zzce, this.zzch);
    }
}
