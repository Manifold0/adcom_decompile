// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.location.LocationStatusCodes;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzbb extends zzan
{
    private BaseImplementation$ResultHolder<Status> zzdf;
    
    public zzbb(final BaseImplementation$ResultHolder<Status> zzdf) {
        this.zzdf = zzdf;
    }
    
    private final void zze(final int n) {
        if (this.zzdf == null) {
            Log.wtf("LocationClientImpl", "onRemoveGeofencesResult called multiple times");
            return;
        }
        this.zzdf.setResult((Object)LocationStatusCodes.zzd(LocationStatusCodes.zzc(n)));
        this.zzdf = null;
    }
    
    @Override
    public final void zza(final int n, final PendingIntent pendingIntent) {
        this.zze(n);
    }
    
    @Override
    public final void zza(final int n, final String[] array) {
        Log.wtf("LocationClientImpl", "Unexpected call to onAddGeofencesResult");
    }
    
    @Override
    public final void zzb(final int n, final String[] array) {
        this.zze(n);
    }
}
