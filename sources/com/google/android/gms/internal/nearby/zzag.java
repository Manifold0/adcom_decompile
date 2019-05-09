package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.nearby.connection.Connections.ConnectionRequestListener;

@Deprecated
@VisibleForTesting
final class zzag extends zzde {
    private final ListenerHolder<ConnectionRequestListener> zzbe;

    zzag(ListenerHolder<ConnectionRequestListener> listenerHolder) {
        this.zzbe = (ListenerHolder) Preconditions.checkNotNull(listenerHolder);
    }

    public final void zza(zzej zzej) {
        this.zzbe.notifyListener(new zzah(this, zzej));
    }

    public final void zza(zzfb zzfb) {
    }
}
