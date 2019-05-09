// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzwn
{
    @VisibleForTesting
    private static final zzalo<zzuu> zzbrf;
    @VisibleForTesting
    private static final zzalo<zzuu> zzbrg;
    private final zzvf zzbrh;
    
    static {
        zzbrf = new zzwo();
        zzbrg = new zzwp();
    }
    
    public zzwn(final Context context, final zzang zzang, final String s) {
        this.zzbrh = new zzvf(context, zzang, s, zzwn.zzbrf, zzwn.zzbrg);
    }
    
    public final <I, O> zzwf<I, O> zza(final String s, final zzwi<I> zzwi, final zzwh<O> zzwh) {
        return new zzwq<I, O>(this.zzbrh, s, zzwi, zzwh);
    }
}
