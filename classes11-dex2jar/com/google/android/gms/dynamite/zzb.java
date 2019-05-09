// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamite;

import android.content.Context;

final class zzb implements VersionPolicy
{
    @Override
    public final VersionPolicy.zzb zza(final Context context, final String s, final zza zza) throws LoadingException {
        final VersionPolicy.zzb zzb = new VersionPolicy.zzb();
        zzb.zzis = zza.zza(context, s, true);
        if (zzb.zzis != 0) {
            zzb.zzit = 1;
        }
        else {
            zzb.zzir = zza.getLocalVersion(context, s);
            if (zzb.zzir != 0) {
                zzb.zzit = -1;
                return zzb;
            }
        }
        return zzb;
    }
}
