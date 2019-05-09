package com.google.android.gms.internal.nearby;

import android.support.v4.util.ArraySet;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import java.util.Set;

@VisibleForTesting
final class zzak extends zzds {
    private final ListenerHolder<EndpointDiscoveryCallback> zzbe;
    private final Set<String> zzbq = new ArraySet();

    zzak(ListenerHolder<EndpointDiscoveryCallback> listenerHolder) {
        this.zzbe = (ListenerHolder) Preconditions.checkNotNull(listenerHolder);
    }

    final synchronized void shutdown() {
        for (String zzan : this.zzbq) {
            this.zzbe.notifyListener(new zzan(this, zzan));
        }
        this.zzbq.clear();
    }

    public final synchronized void zza(zzer zzer) {
        this.zzbq.add(zzer.zze());
        this.zzbe.notifyListener(new zzal(this, zzer));
    }

    public final synchronized void zza(zzet zzet) {
        this.zzbq.remove(zzet.zze());
        this.zzbe.notifyListener(new zzam(this, zzet));
    }

    public final void zza(zzfd zzfd) {
    }
}
