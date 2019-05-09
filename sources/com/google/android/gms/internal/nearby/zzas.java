package com.google.android.gms.internal.nearby;

import android.util.Log;
import com.google.android.gms.nearby.connection.Connections.MessageListener;
import com.google.android.gms.nearby.connection.Payload;

final class zzas extends zzau<MessageListener> {
    private final /* synthetic */ zzev zzbu;

    zzas(zzar zzar, zzev zzev) {
        this.zzbu = zzev;
        super();
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        MessageListener messageListener = (MessageListener) obj;
        Payload zza = zzfl.zza(this.zzbu.zzl());
        if (zza == null) {
            Log.w("NearbyConnectionsClient", String.format("Failed to convert incoming ParcelablePayload %d to Payload.", new Object[]{Long.valueOf(this.zzbu.zzl().getId())}));
        } else if (zza.getType() == 1) {
            messageListener.onMessageReceived(this.zzbu.zzg(), zza.asBytes(), this.zzbu.zzm());
        }
    }
}
