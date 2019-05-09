package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.api.internal.ListenerHolder;

final /* synthetic */ class zzaq implements zzbd {
    private final PendingIntent zzhx;

    zzaq(PendingIntent pendingIntent) {
        this.zzhx = pendingIntent;
    }

    public final void zza(zzah zzah, ListenerHolder listenerHolder) {
        zzah.zza(listenerHolder, this.zzhx);
    }
}
