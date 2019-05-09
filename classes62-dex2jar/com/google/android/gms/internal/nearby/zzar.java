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
final class zzar extends zzdh
{
    private final ListenerHolder<Connections.MessageListener> zzbt;
    
    zzar(final ListenerHolder<Connections.MessageListener> listenerHolder) {
        this.zzbt = (ListenerHolder<Connections.MessageListener>)Preconditions.checkNotNull((Object)listenerHolder);
    }
    
    @Override
    public final void zza(final zzep zzep) {
        this.zzbt.notifyListener((ListenerHolder$Notifier)new zzat(this, zzep));
    }
    
    @Override
    public final void zza(final zzev zzev) {
        this.zzbt.notifyListener((ListenerHolder$Notifier)new zzas(this, zzev));
    }
    
    @Override
    public final void zza(final zzex zzex) {
    }
}
