package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.nearby.connection.Payload;

final /* synthetic */ class zzbh implements zzbw {
    private final String zzce;
    private final Payload zzci;

    zzbh(String str, Payload payload) {
        this.zzce = str;
        this.zzci = payload;
    }

    public final void zza(zzx zzx, ResultHolder resultHolder) {
        zzx.zza(resultHolder, new String[]{this.zzce}, this.zzci, false);
    }
}
