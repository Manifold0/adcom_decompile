// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.DeviceProperties;
import android.content.Context;

final class zzadw implements zzady
{
    private final /* synthetic */ Context val$context;
    
    zzadw(final Context val$context) {
        this.val$context = val$context;
    }
    
    @Override
    public final boolean zza(final zzang zzang) {
        final boolean b = false;
        zzkb.zzif();
        final boolean zzbe = zzamu.zzbe(this.val$context);
        boolean b2;
        if ((boolean)zzkb.zzik().zzd(zznk.zzbeq) && zzang.zzcvg) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (zzc(this.val$context, zzang.zzcvg) && zzbe && !b2) {
            boolean b3 = b;
            if (!DeviceProperties.isSidewinder(this.val$context)) {
                return b3;
            }
            final zzna zzawa = zznk.zzawa;
            b3 = b;
            if (zzkb.zzik().zzd(zzawa)) {
                return b3;
            }
        }
        return true;
    }
}
