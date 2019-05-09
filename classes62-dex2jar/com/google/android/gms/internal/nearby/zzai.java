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
final class zzai extends zzdn
{
    private final ListenerHolder<Connections.ConnectionResponseCallback> zzbo;
    
    public zzai(final ListenerHolder<Connections.ConnectionResponseCallback> listenerHolder) {
        this.zzbo = (ListenerHolder<Connections.ConnectionResponseCallback>)Preconditions.checkNotNull((Object)listenerHolder);
    }
    
    @Override
    public final void zza(final zzel zzel) {
        this.zzbo.notifyListener((ListenerHolder$Notifier)new zzaj(this, zzel));
    }
}
