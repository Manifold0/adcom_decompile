// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import com.google.android.gms.common.internal.Preconditions;
import android.os.Looper;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationCallback;
import android.app.PendingIntent;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationServices;
import android.location.Location;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.location.FusedLocationProviderApi;

@VisibleForTesting
public final class zzq implements FusedLocationProviderApi
{
    @Override
    public final PendingResult<Status> flushLocations(final GoogleApiClient googleApiClient) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzv(this, googleApiClient));
    }
    
    @Override
    public final Location getLastLocation(final GoogleApiClient googleApiClient) {
        final zzaz zza = LocationServices.zza(googleApiClient);
        try {
            return zza.getLastLocation();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    @Override
    public final LocationAvailability getLocationAvailability(final GoogleApiClient googleApiClient) {
        final zzaz zza = LocationServices.zza(googleApiClient);
        try {
            return zza.zza();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    @Override
    public final PendingResult<Status> removeLocationUpdates(final GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzaa(this, googleApiClient, pendingIntent));
    }
    
    @Override
    public final PendingResult<Status> removeLocationUpdates(final GoogleApiClient googleApiClient, final LocationCallback locationCallback) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzs(this, googleApiClient, locationCallback));
    }
    
    @Override
    public final PendingResult<Status> removeLocationUpdates(final GoogleApiClient googleApiClient, final LocationListener locationListener) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzz(this, googleApiClient, locationListener));
    }
    
    @Override
    public final PendingResult<Status> requestLocationUpdates(final GoogleApiClient googleApiClient, final LocationRequest locationRequest, final PendingIntent pendingIntent) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzy(this, googleApiClient, locationRequest, pendingIntent));
    }
    
    @Override
    public final PendingResult<Status> requestLocationUpdates(final GoogleApiClient googleApiClient, final LocationRequest locationRequest, final LocationCallback locationCallback, final Looper looper) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzx(this, googleApiClient, locationRequest, locationCallback, looper));
    }
    
    @Override
    public final PendingResult<Status> requestLocationUpdates(final GoogleApiClient googleApiClient, final LocationRequest locationRequest, final LocationListener locationListener) {
        Preconditions.checkNotNull((Object)Looper.myLooper(), (Object)"Calling thread must be a prepared Looper thread.");
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzr(this, googleApiClient, locationRequest, locationListener));
    }
    
    @Override
    public final PendingResult<Status> requestLocationUpdates(final GoogleApiClient googleApiClient, final LocationRequest locationRequest, final LocationListener locationListener, final Looper looper) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzw(this, googleApiClient, locationRequest, locationListener, looper));
    }
    
    @Override
    public final PendingResult<Status> setMockLocation(final GoogleApiClient googleApiClient, final Location location) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzu(this, googleApiClient, location));
    }
    
    @Override
    public final PendingResult<Status> setMockMode(final GoogleApiClient googleApiClient, final boolean b) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzt(this, googleApiClient, b));
    }
}
