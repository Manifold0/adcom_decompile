// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import java.util.List;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.Task;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.GoogleApi;

public class GeofencingClient extends GoogleApi<Api$ApiOptions$NoOptions>
{
    public GeofencingClient(@NonNull final Activity activity) {
        super(activity, (Api)LocationServices.API, (Api$ApiOptions)null, (StatusExceptionMapper)new ApiExceptionMapper());
    }
    
    public GeofencingClient(@NonNull final Context context) {
        super(context, (Api)LocationServices.API, (Api$ApiOptions)null, (StatusExceptionMapper)new ApiExceptionMapper());
    }
    
    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public Task<Void> addGeofences(final GeofencingRequest geofencingRequest, final PendingIntent pendingIntent) {
        return (Task<Void>)PendingResultUtil.toVoidTask((PendingResult)LocationServices.GeofencingApi.addGeofences(this.asGoogleApiClient(), geofencingRequest, pendingIntent));
    }
    
    public Task<Void> removeGeofences(final PendingIntent pendingIntent) {
        return (Task<Void>)PendingResultUtil.toVoidTask((PendingResult)LocationServices.GeofencingApi.removeGeofences(this.asGoogleApiClient(), pendingIntent));
    }
    
    public Task<Void> removeGeofences(final List<String> list) {
        return (Task<Void>)PendingResultUtil.toVoidTask((PendingResult)LocationServices.GeofencingApi.removeGeofences(this.asGoogleApiClient(), list));
    }
}
