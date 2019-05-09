package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.nearby.connection.Payload;
import java.util.List;

final /* synthetic */ class zzbi implements zzbw {
    private final Payload zzci;
    private final List zzcj;

    zzbi(List list, Payload payload) {
        this.zzcj = list;
        this.zzci = payload;
    }

    public final void zza(zzx zzx, ResultHolder resultHolder) {
        zzx.zza(resultHolder, (String[]) this.zzcj.toArray(new String[0]), this.zzci, false);
    }
}
