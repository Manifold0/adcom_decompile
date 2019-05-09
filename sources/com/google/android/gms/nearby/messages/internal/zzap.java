package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.messages.SubscribeOptions;

final /* synthetic */ class zzap implements zzbd {
    private final zzak zzho;
    private final zzbg zzht;
    private final SubscribeOptions zzhu;
    private final PendingIntent zzhw;

    zzap(zzak zzak, PendingIntent pendingIntent, zzbg zzbg, SubscribeOptions subscribeOptions) {
        this.zzho = zzak;
        this.zzhw = pendingIntent;
        this.zzht = zzbg;
        this.zzhu = subscribeOptions;
    }

    public final void zza(zzah zzah, ListenerHolder listenerHolder) {
        this.zzho.zza(this.zzhw, this.zzht, this.zzhu, zzah, listenerHolder);
    }
}
