// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.Task;
import android.content.Context;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.GoogleApi;

public class SettingsClient extends GoogleApi<Api$ApiOptions$NoOptions>
{
    public SettingsClient(@NonNull final Activity activity) {
        super(activity, (Api)LocationServices.API, (Api$ApiOptions)null, (StatusExceptionMapper)new ApiExceptionMapper());
    }
    
    public SettingsClient(@NonNull final Context context) {
        super(context, (Api)LocationServices.API, (Api$ApiOptions)null, (StatusExceptionMapper)new ApiExceptionMapper());
    }
    
    public Task<LocationSettingsResponse> checkLocationSettings(final LocationSettingsRequest locationSettingsRequest) {
        return (Task<LocationSettingsResponse>)PendingResultUtil.toResponseTask((PendingResult)LocationServices.SettingsApi.checkLocationSettings(this.asGoogleApiClient(), locationSettingsRequest), (Response)new LocationSettingsResponse());
    }
}
