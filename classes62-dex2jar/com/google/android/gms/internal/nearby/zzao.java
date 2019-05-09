// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzao extends zzds
{
    private final ListenerHolder<Connections.EndpointDiscoveryListener> zzbe;
    
    zzao(final ListenerHolder<Connections.EndpointDiscoveryListener> listenerHolder) {
        this.zzbe = (ListenerHolder<Connections.EndpointDiscoveryListener>)Preconditions.checkNotNull((Object)listenerHolder);
    }
    
    @Override
    public final void zza(final zzer zzer) {
        this.zzbe.notifyListener((ListenerHolder$Notifier)new zzap(this, zzer));
    }
    
    @Override
    public final void zza(final zzet zzet) {
        this.zzbe.notifyListener((ListenerHolder$Notifier)new zzaq(this, zzet));
    }
    
    @Override
    public final void zza(final zzfd zzfd) {
    }
}
