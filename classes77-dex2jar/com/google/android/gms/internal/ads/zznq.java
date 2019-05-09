// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;

@zzadh
public final class zznq
{
    public static boolean zza(@Nullable final zznx zznx, @Nullable final zznv zznv, final String... array) {
        return zznx != null && zznv != null && zznx.zza(zznv, array);
    }
    
    @Nullable
    public static zznv zzb(@Nullable final zznx zznx) {
        if (zznx == null) {
            return null;
        }
        return zznx.zzjj();
    }
}
