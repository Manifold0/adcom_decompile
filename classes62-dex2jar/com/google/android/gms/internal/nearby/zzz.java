// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.Status;
import java.util.Iterator;
import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.common.internal.Preconditions;
import android.support.v4.util.ArraySet;
import java.util.Set;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzz extends zzdk
{
    private final ListenerHolder<ConnectionLifecycleCallback> zzbe;
    private final Set<String> zzbf;
    private final Set<String> zzbg;
    
    zzz(final ListenerHolder<ConnectionLifecycleCallback> listenerHolder) {
        this.zzbf = (Set<String>)new ArraySet();
        this.zzbg = (Set<String>)new ArraySet();
        this.zzbe = (ListenerHolder<ConnectionLifecycleCallback>)Preconditions.checkNotNull((Object)listenerHolder);
    }
    
    final void shutdown() {
        synchronized (this) {
            final Iterator<String> iterator = this.zzbf.iterator();
            while (iterator.hasNext()) {
                this.zzbe.notifyListener((ListenerHolder$Notifier)new zzae(this, iterator.next()));
            }
        }
        this.zzbf.clear();
        final Iterator<String> iterator2 = this.zzbg.iterator();
        while (iterator2.hasNext()) {
            this.zzbe.notifyListener((ListenerHolder$Notifier)new zzaf(this, iterator2.next()));
        }
        this.zzbg.clear();
    }
    // monitorexit(this)
    
    @Override
    public final void zza(final zzef zzef) {
        this.zzbe.notifyListener((ListenerHolder$Notifier)new zzad(this, zzef));
    }
    
    @Override
    public final void zza(final zzeh zzeh) {
        synchronized (this) {
            this.zzbf.add(zzeh.zzg());
            this.zzbe.notifyListener((ListenerHolder$Notifier)new zzaa(this, zzeh));
        }
    }
    
    @Override
    public final void zza(final zzen zzen) {
        synchronized (this) {
            this.zzbf.remove(zzen.zzg());
            final Status zzb = zza(zzen.getStatusCode());
            if (zzb.isSuccess()) {
                this.zzbg.add(zzen.zzg());
            }
            this.zzbe.notifyListener((ListenerHolder$Notifier)new zzab(this, zzen, zzb));
        }
    }
    
    @Override
    public final void zza(final zzep zzep) {
        synchronized (this) {
            this.zzbg.remove(zzep.zzg());
            this.zzbe.notifyListener((ListenerHolder$Notifier)new zzac(this, zzep));
        }
    }
}
