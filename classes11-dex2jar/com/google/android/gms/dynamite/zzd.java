// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamite;

import android.content.Context;

final class zzd implements VersionPolicy
{
    @Override
    public final zzb zza(final Context context, final String s, final zza zza) throws LoadingException {
        final zzb zzb = new zzb();
        zzb.zzir = zza.getLocalVersion(context, s);
        zzb.zzis = zza.zza(context, s, true);
        if (zzb.zzir == 0 && zzb.zzis == 0) {
            zzb.zzit = 0;
            return zzb;
        }
        if (zzb.zzir >= zzb.zzis) {
            zzb.zzit = -1;
            return zzb;
        }
        zzb.zzit = 1;
        return zzb;
    }
}
