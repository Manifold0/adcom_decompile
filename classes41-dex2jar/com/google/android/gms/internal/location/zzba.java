// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import com.google.android.gms.location.LocationStatusCodes;
import android.util.Log;
import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzba extends zzan
{
    private BaseImplementation$ResultHolder<Status> zzdf;
    
    public zzba(final BaseImplementation$ResultHolder<Status> zzdf) {
        this.zzdf = zzdf;
    }
    
    @Override
    public final void zza(final int n, final PendingIntent pendingIntent) {
        Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByPendingIntentResult");
    }
    
    @Override
    public final void zza(final int n, final String[] array) {
        if (this.zzdf == null) {
            Log.wtf("LocationClientImpl", "onAddGeofenceResult called multiple times");
            return;
        }
        this.zzdf.setResult((Object)LocationStatusCodes.zzd(LocationStatusCodes.zzc(n)));
        this.zzdf = null;
    }
    
    @Override
    public final void zzb(final int n, final String[] array) {
        Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByRequestIdsResult");
    }
}
