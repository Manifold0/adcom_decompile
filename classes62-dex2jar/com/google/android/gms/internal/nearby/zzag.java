// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.util.VisibleForTesting;

@Deprecated
@VisibleForTesting
final class zzag extends zzde
{
    private final ListenerHolder<Connections.ConnectionRequestListener> zzbe;
    
    zzag(final ListenerHolder<Connections.ConnectionRequestListener> listenerHolder) {
        this.zzbe = (ListenerHolder<Connections.ConnectionRequestListener>)Preconditions.checkNotNull((Object)listenerHolder);
    }
    
    @Override
    public final void zza(final zzej zzej) {
        this.zzbe.notifyListener((ListenerHolder$Notifier)new zzah(this, zzej));
    }
    
    @Override
    public final void zza(final zzfb zzfb) {
    }
}
