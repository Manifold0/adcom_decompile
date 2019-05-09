// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.Payload;
import android.util.Log;
import java.util.Iterator;
import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.common.internal.Preconditions;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.nearby.connection.PayloadTransferUpdate;
import java.util.Map;
import com.google.android.gms.nearby.connection.PayloadCallback;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzav extends zzdx
{
    private final ListenerHolder<PayloadCallback> zzbv;
    private final Map<zzaz, PayloadTransferUpdate> zzbw;
    
    zzav(final ListenerHolder<PayloadCallback> listenerHolder) {
        this.zzbw = (Map<zzaz, PayloadTransferUpdate>)new ArrayMap();
        this.zzbv = (ListenerHolder<PayloadCallback>)Preconditions.checkNotNull((Object)listenerHolder);
    }
    
    final void shutdown() {
        synchronized (this) {
            for (final Map.Entry<zzaz, PayloadTransferUpdate> entry : this.zzbw.entrySet()) {
                this.zzbv.notifyListener((ListenerHolder$Notifier)new zzay(this, entry.getKey().zze(), entry.getValue()));
            }
        }
        this.zzbw.clear();
    }
    // monitorexit(this)
    
    @Override
    public final void zza(final zzev zzev) {
        synchronized (this) {
            final Payload zza = zzfl.zza(zzev.zzl());
            if (zza == null) {
                Log.w("NearbyConnectionsClient", String.format("Failed to convert incoming ParcelablePayload %d to Payload.", zzev.zzl().getId()));
            }
            else {
                this.zzbw.put(new zzaz(zzev.zzg(), zzev.zzl().getId()), new PayloadTransferUpdate.Builder().setPayloadId(zzev.zzl().getId()).build());
                this.zzbv.notifyListener((ListenerHolder$Notifier)new zzaw(this, zzev, zza));
            }
        }
    }
    
    @Override
    public final void zza(final zzex zzex) {
        synchronized (this) {
            if (zzex.zzn().getStatus() == 3) {
                this.zzbw.put(new zzaz(zzex.zzg(), zzex.zzn().getPayloadId()), zzex.zzn());
            }
            else {
                this.zzbw.remove(new zzaz(zzex.zzg(), zzex.zzn().getPayloadId()));
            }
            this.zzbv.notifyListener((ListenerHolder$Notifier)new zzax(this, zzex));
        }
    }
}
