package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.nearby.connection.Connections.ConnectionResponseCallback;

@Deprecated
@VisibleForTesting
final class zzai extends zzdn {
    private final ListenerHolder<ConnectionResponseCallback> zzbo;

    public zzai(ListenerHolder<ConnectionResponseCallback> listenerHolder) {
        this.zzbo = (ListenerHolder) Preconditions.checkNotNull(listenerHolder);
    }

    public final void zza(zzel zzel) {
        this.zzbo.notifyListener(new zzaj(this, zzel));
    }
}
