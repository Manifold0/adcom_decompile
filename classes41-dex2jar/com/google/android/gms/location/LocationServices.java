// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Api$AnyClientKey;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.api.GoogleApiClient;
import android.content.Context;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.internal.location.zzbk;
import com.google.android.gms.internal.location.zzaf;
import com.google.android.gms.internal.location.zzq;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api;

public class LocationServices
{
    public static final Api<Api$ApiOptions$NoOptions> API;
    private static final Api$AbstractClientBuilder<zzaz, Api$ApiOptions$NoOptions> CLIENT_BUILDER;
    private static final Api$ClientKey<zzaz> CLIENT_KEY;
    @Deprecated
    public static final FusedLocationProviderApi FusedLocationApi;
    @Deprecated
    public static final GeofencingApi GeofencingApi;
    @Deprecated
    public static final SettingsApi SettingsApi;
    
    static {
        CLIENT_KEY = new Api$ClientKey();
        CLIENT_BUILDER = new zzad();
        API = new Api("LocationServices.API", (Api$AbstractClientBuilder)LocationServices.CLIENT_BUILDER, (Api$ClientKey)LocationServices.CLIENT_KEY);
        FusedLocationApi = new zzq();
        GeofencingApi = new zzaf();
        SettingsApi = new zzbk();
    }
    
    private LocationServices() {
    }
    
    public static FusedLocationProviderClient getFusedLocationProviderClient(@NonNull final Activity activity) {
        return new FusedLocationProviderClient(activity);
    }
    
    public static FusedLocationProviderClient getFusedLocationProviderClient(@NonNull final Context context) {
        return new FusedLocationProviderClient(context);
    }
    
    public static GeofencingClient getGeofencingClient(@NonNull final Activity activity) {
        return new GeofencingClient(activity);
    }
    
    public static GeofencingClient getGeofencingClient(@NonNull final Context context) {
        return new GeofencingClient(context);
    }
    
    public static SettingsClient getSettingsClient(@NonNull final Activity activity) {
        return new SettingsClient(activity);
    }
    
    public static SettingsClient getSettingsClient(@NonNull final Context context) {
        return new SettingsClient(context);
    }
    
    public static zzaz zza(final GoogleApiClient googleApiClient) {
        final boolean b = true;
        Preconditions.checkArgument(googleApiClient != null, (Object)"GoogleApiClient parameter is required.");
        final zzaz zzaz = (zzaz)googleApiClient.getClient((Api$AnyClientKey)LocationServices.CLIENT_KEY);
        Preconditions.checkState(zzaz != null && b, (Object)"GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        return zzaz;
    }
    
    public abstract static class zza<R extends Result> extends BaseImplementation$ApiMethodImpl<R, zzaz>
    {
        public zza(final GoogleApiClient googleApiClient) {
            super((Api)LocationServices.API, googleApiClient);
        }
    }
}
