package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.nearby.connection.Connections.EndpointDiscoveryListener;

@VisibleForTesting
final class zzao extends zzds {
    private final ListenerHolder<EndpointDiscoveryListener> zzbe;

    zzao(ListenerHolder<EndpointDiscoveryListener> listenerHolder) {
        this.zzbe = (ListenerHolder) Preconditions.checkNotNull(listenerHolder);
    }

    public final void zza(zzer zzer) {
        this.zzbe.notifyListener(new zzap(this, zzer));
    }

    public final void zza(zzet zzet) {
        this.zzbe.notifyListener(new zzaq(this, zzet));
    }

    public final void zza(zzfd zzfd) {
    }
}
