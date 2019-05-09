package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzwn {
    @VisibleForTesting
    private static final zzalo<zzuu> zzbrf = new zzwo();
    @VisibleForTesting
    private static final zzalo<zzuu> zzbrg = new zzwp();
    private final zzvf zzbrh;

    public zzwn(Context context, zzang zzang, String str) {
        this.zzbrh = new zzvf(context, zzang, str, zzbrf, zzbrg);
    }

    public final <I, O> zzwf<I, O> zza(String str, zzwi<I> zzwi, zzwh<O> zzwh) {
        return new zzwq(this.zzbrh, str, zzwi, zzwh);
    }
}
