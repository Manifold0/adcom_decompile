package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zznp {
    public static void zza(zznn zznn, @Nullable zznm zznm) {
        if (zznm.getContext() == null) {
            throw new IllegalArgumentException("Context can't be null. Please set up context in CsiConfiguration.");
        } else if (TextUtils.isEmpty(zznm.zzfw())) {
            throw new IllegalArgumentException("AfmaVersion can't be null or empty. Please set up afmaVersion in CsiConfiguration.");
        } else {
            zznn.zza(zznm.getContext(), zznm.zzfw(), zznm.zzjd(), zznm.zzje());
        }
    }
}
