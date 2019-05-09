// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import com.google.android.gms.location.Geofence;
import java.util.List;
import android.app.PendingIntent;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.zzal;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.GeofencingApi;

public final class zzaf implements GeofencingApi
{
    private final PendingResult<Status> zza(final GoogleApiClient googleApiClient, final zzal zzal) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzah(this, googleApiClient, zzal));
    }
    
    @Override
    public final PendingResult<Status> addGeofences(final GoogleApiClient googleApiClient, final GeofencingRequest geofencingRequest, final PendingIntent pendingIntent) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzag(this, googleApiClient, geofencingRequest, pendingIntent));
    }
    
    @Deprecated
    @Override
    public final PendingResult<Status> addGeofences(final GoogleApiClient googleApiClient, final List<Geofence> list, final PendingIntent pendingIntent) {
        final GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.addGeofences(list);
        builder.setInitialTrigger(5);
        return this.addGeofences(googleApiClient, builder.build(), pendingIntent);
    }
    
    @Override
    public final PendingResult<Status> removeGeofences(final GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        return this.zza(googleApiClient, zzal.zza(pendingIntent));
    }
    
    @Override
    public final PendingResult<Status> removeGeofences(final GoogleApiClient googleApiClient, final List<String> list) {
        return this.zza(googleApiClient, zzal.zza(list));
    }
}
