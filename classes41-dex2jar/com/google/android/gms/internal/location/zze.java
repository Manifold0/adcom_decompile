// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognitionApi;

public final class zze implements ActivityRecognitionApi
{
    @Override
    public final PendingResult<Status> removeActivityUpdates(final GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzg(this, googleApiClient, pendingIntent));
    }
    
    @Override
    public final PendingResult<Status> requestActivityUpdates(final GoogleApiClient googleApiClient, final long n, final PendingIntent pendingIntent) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzf(this, googleApiClient, n, pendingIntent));
    }
    
    @Override
    public final PendingResult<Status> zza(final GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzi(this, googleApiClient, pendingIntent));
    }
    
    @Override
    public final PendingResult<Status> zza(final GoogleApiClient googleApiClient, final ActivityTransitionRequest activityTransitionRequest, final PendingIntent pendingIntent) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzh(this, googleApiClient, activityTransitionRequest, pendingIntent));
    }
}
