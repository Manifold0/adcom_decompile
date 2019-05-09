package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.DowngradeableSafeParcel;
import com.google.android.gms.common.util.GmsVersion;

public abstract class GamesDowngradeableSafeParcel extends DowngradeableSafeParcel {
    protected static boolean zzb(Integer num) {
        return num == null ? false : GmsVersion.isAtLeastFenacho(num.intValue());
    }

    public boolean prepareForClientVersion(int i) {
        setShouldDowngrade(!zzb(Integer.valueOf(i)));
        return true;
    }
}
