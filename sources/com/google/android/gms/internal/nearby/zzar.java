package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.nearby.connection.Connections.MessageListener;

@Deprecated
@VisibleForTesting
final class zzar extends zzdh {
    private final ListenerHolder<MessageListener> zzbt;

    zzar(ListenerHolder<MessageListener> listenerHolder) {
        this.zzbt = (ListenerHolder) Preconditions.checkNotNull(listenerHolder);
    }

    public final void zza(zzep zzep) {
        this.zzbt.notifyListener(new zzat(this, zzep));
    }

    public final void zza(zzev zzev) {
        this.zzbt.notifyListener(new zzas(this, zzev));
    }

    public final void zza(zzex zzex) {
    }
}
