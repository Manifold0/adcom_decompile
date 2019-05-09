// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import android.support.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zznp
{
    public static void zza(final zznn zznn, @Nullable final zznm zznm) {
        if (zznm.getContext() == null) {
            throw new IllegalArgumentException("Context can't be null. Please set up context in CsiConfiguration.");
        }
        if (TextUtils.isEmpty((CharSequence)zznm.zzfw())) {
            throw new IllegalArgumentException("AfmaVersion can't be null or empty. Please set up afmaVersion in CsiConfiguration.");
        }
        zznn.zza(zznm.getContext(), zznm.zzfw(), zznm.zzjd(), zznm.zzje());
    }
}
