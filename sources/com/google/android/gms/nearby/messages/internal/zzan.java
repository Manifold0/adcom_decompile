package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.messages.SubscribeOptions;

final /* synthetic */ class zzan implements zzbd {
    private final ListenerHolder zzch;
    private final zzak zzho;
    private final zzbg zzht;
    private final SubscribeOptions zzhu;

    zzan(zzak zzak, ListenerHolder listenerHolder, zzbg zzbg, SubscribeOptions subscribeOptions) {
        this.zzho = zzak;
        this.zzch = listenerHolder;
        this.zzht = zzbg;
        this.zzhu = subscribeOptions;
    }

    public final void zza(zzah zzah, ListenerHolder listenerHolder) {
        this.zzho.zza(this.zzch, this.zzht, this.zzhu, zzah, listenerHolder);
    }
}
