package com.google.android.gms.internal.nearby;

import android.database.ContentObserver;
import android.os.Handler;

final class zzhf extends ContentObserver {
    zzhf(Handler handler) {
        super(null);
    }

    public final void onChange(boolean z) {
        zzhe.zzjq.set(true);
    }
}
