// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import com.google.android.gms.internal.location.zzad;
import com.google.android.gms.internal.location.zzak;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.internal.location.zzbm;
import com.google.android.gms.internal.location.zzbd;
import android.support.annotation.Nullable;
import android.os.Looper;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.api.internal.ListenerHolders;
import android.app.PendingIntent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.internal.TaskApiCall;
import android.location.Location;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.internal.location.zzaj;
import com.google.android.gms.tasks.TaskCompletionSource;
import android.content.Context;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.GoogleApi;

public class FusedLocationProviderClient extends GoogleApi<Api$ApiOptions$NoOptions>
{
    public static final String KEY_VERTICAL_ACCURACY = "verticalAccuracy";
    
    public FusedLocationProviderClient(@NonNull final Activity activity) {
        super(activity, (Api)LocationServices.API, (Api$ApiOptions)null, (StatusExceptionMapper)new ApiExceptionMapper());
    }
    
    public FusedLocationProviderClient(@NonNull final Context context) {
        super(context, (Api)LocationServices.API, (Api$ApiOptions)null, (StatusExceptionMapper)new ApiExceptionMapper());
    }
    
    private final zzaj zza(final TaskCompletionSource<Boolean> taskCompletionSource) {
        return new zzp(this, taskCompletionSource);
    }
    
    public Task<Void> flushLocations() {
        return (Task<Void>)PendingResultUtil.toVoidTask((PendingResult)LocationServices.FusedLocationApi.flushLocations(this.asGoogleApiClient()));
    }
    
    @RequiresPermission(anyOf = { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" })
    public Task<Location> getLastLocation() {
        return (Task<Location>)this.doRead((TaskApiCall)new zzl(this));
    }
    
    @RequiresPermission(anyOf = { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" })
    public Task<LocationAvailability> getLocationAvailability() {
        return (Task<LocationAvailability>)this.doRead((TaskApiCall)new zzm(this));
    }
    
    public Task<Void> removeLocationUpdates(final PendingIntent pendingIntent) {
        return (Task<Void>)PendingResultUtil.toVoidTask((PendingResult)LocationServices.FusedLocationApi.removeLocationUpdates(this.asGoogleApiClient(), pendingIntent));
    }
    
    public Task<Void> removeLocationUpdates(final LocationCallback locationCallback) {
        return (Task<Void>)TaskUtil.toVoidTaskThatFailsOnFalse(this.doUnregisterEventListener(ListenerHolders.createListenerKey((Object)locationCallback, LocationCallback.class.getSimpleName())));
    }
    
    @RequiresPermission(anyOf = { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" })
    public Task<Void> requestLocationUpdates(final LocationRequest locationRequest, final PendingIntent pendingIntent) {
        return (Task<Void>)PendingResultUtil.toVoidTask((PendingResult)LocationServices.FusedLocationApi.requestLocationUpdates(this.asGoogleApiClient(), locationRequest, pendingIntent));
    }
    
    @RequiresPermission(anyOf = { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" })
    public Task<Void> requestLocationUpdates(final LocationRequest locationRequest, final LocationCallback locationCallback, @Nullable final Looper looper) {
        final zzbd zza = zzbd.zza(locationRequest);
        final ListenerHolder listenerHolder = ListenerHolders.createListenerHolder((Object)locationCallback, zzbm.zza(looper), LocationCallback.class.getSimpleName());
        return (Task<Void>)this.doRegisterEventListener((RegisterListenerMethod)new zzn(this, listenerHolder, zza, listenerHolder), (UnregisterListenerMethod)new zzo(this, listenerHolder.getListenerKey()));
    }
    
    @RequiresPermission(anyOf = { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" })
    public Task<Void> setMockLocation(final Location location) {
        return (Task<Void>)PendingResultUtil.toVoidTask((PendingResult)LocationServices.FusedLocationApi.setMockLocation(this.asGoogleApiClient(), location));
    }
    
    @RequiresPermission(anyOf = { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" })
    public Task<Void> setMockMode(final boolean b) {
        return (Task<Void>)PendingResultUtil.toVoidTask((PendingResult)LocationServices.FusedLocationApi.setMockMode(this.asGoogleApiClient(), b));
    }
    
    private static final class zza extends zzak
    {
        private final TaskCompletionSource<Void> zzac;
        
        public zza(final TaskCompletionSource<Void> zzac) {
            this.zzac = zzac;
        }
        
        @Override
        public final void zza(final zzad zzad) {
            TaskUtil.setResultOrApiException(zzad.getStatus(), (TaskCompletionSource)this.zzac);
        }
    }
}
