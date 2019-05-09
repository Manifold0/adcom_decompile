// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.Result;
import android.content.Context;
import android.app.Activity;
import com.google.android.gms.internal.location.zze;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api;

public class ActivityRecognition
{
    public static final Api<Api$ApiOptions$NoOptions> API;
    @Deprecated
    public static final ActivityRecognitionApi ActivityRecognitionApi;
    private static final Api$AbstractClientBuilder<zzaz, Api$ApiOptions$NoOptions> CLIENT_BUILDER;
    private static final Api$ClientKey<zzaz> CLIENT_KEY;
    public static final String CLIENT_NAME = "activity_recognition";
    
    static {
        CLIENT_KEY = new Api$ClientKey();
        CLIENT_BUILDER = new com.google.android.gms.location.zza();
        API = new Api("ActivityRecognition.API", (Api$AbstractClientBuilder)ActivityRecognition.CLIENT_BUILDER, (Api$ClientKey)ActivityRecognition.CLIENT_KEY);
        ActivityRecognitionApi = new zze();
    }
    
    private ActivityRecognition() {
    }
    
    public static ActivityRecognitionClient getClient(final Activity activity) {
        return new ActivityRecognitionClient(activity);
    }
    
    public static ActivityRecognitionClient getClient(final Context context) {
        return new ActivityRecognitionClient(context);
    }
    
    public abstract static class zza<R extends Result> extends BaseImplementation$ApiMethodImpl<R, zzaz>
    {
        public zza(final GoogleApiClient googleApiClient) {
            super((Api)ActivityRecognition.API, googleApiClient);
        }
    }
}
