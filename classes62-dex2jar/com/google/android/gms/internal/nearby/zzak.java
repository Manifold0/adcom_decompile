// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import java.util.Iterator;
import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.common.internal.Preconditions;
import android.support.v4.util.ArraySet;
import java.util.Set;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzak extends zzds
{
    private final ListenerHolder<EndpointDiscoveryCallback> zzbe;
    private final Set<String> zzbq;
    
    zzak(final ListenerHolder<EndpointDiscoveryCallback> listenerHolder) {
        this.zzbq = (Set<String>)new ArraySet();
        this.zzbe = (ListenerHolder<EndpointDiscoveryCallback>)Preconditions.checkNotNull((Object)listenerHolder);
    }
    
    final void shutdown() {
        synchronized (this) {
            final Iterator<String> iterator = this.zzbq.iterator();
            while (iterator.hasNext()) {
                this.zzbe.notifyListener((ListenerHolder$Notifier)new zzan(this, iterator.next()));
            }
        }
        this.zzbq.clear();
    }
    // monitorexit(this)
    
    @Override
    public final void zza(final zzer zzer) {
        synchronized (this) {
            this.zzbq.add(zzer.zze());
            this.zzbe.notifyListener((ListenerHolder$Notifier)new zzal(this, zzer));
        }
    }
    
    @Override
    public final void zza(final zzet zzet) {
        synchronized (this) {
            this.zzbq.remove(zzet.zze());
            this.zzbe.notifyListener((ListenerHolder$Notifier)new zzam(this, zzet));
        }
    }
    
    @Override
    public final void zza(final zzfd zzfd) {
    }
}
