package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;

final /* synthetic */ class zzbe implements zzbw {
    private final String zzce;
    private final String zzcf;
    private final ListenerHolder zzcg;

    zzbe(String str, String str2, ListenerHolder listenerHolder) {
        this.zzce = str;
        this.zzcf = str2;
        this.zzcg = listenerHolder;
    }

    public final void zza(zzx zzx, ResultHolder resultHolder) {
        zzx.zza(resultHolder, this.zzce, this.zzcf, this.zzcg);
    }
}
