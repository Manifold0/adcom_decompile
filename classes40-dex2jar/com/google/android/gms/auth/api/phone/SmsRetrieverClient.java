// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.phone;

import com.google.android.gms.tasks.Task;
import android.content.Context;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.internal.auth-api-phone.zzi;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.GoogleApi;

public abstract class SmsRetrieverClient extends GoogleApi<Api$ApiOptions$NoOptions> implements SmsRetrieverApi
{
    private static final Api<Api$ApiOptions$NoOptions> API;
    private static final Api$AbstractClientBuilder<zzi, Api$ApiOptions$NoOptions> CLIENT_BUILDER;
    private static final Api$ClientKey<zzi> CLIENT_KEY;
    
    static {
        CLIENT_KEY = new Api$ClientKey();
        CLIENT_BUILDER = new zza();
        API = new Api("SmsRetriever.API", (Api$AbstractClientBuilder)SmsRetrieverClient.CLIENT_BUILDER, (Api$ClientKey)SmsRetrieverClient.CLIENT_KEY);
    }
    
    public SmsRetrieverClient(@NonNull final Activity activity) {
        super(activity, (Api)SmsRetrieverClient.API, (Api$ApiOptions)null, (StatusExceptionMapper)new ApiExceptionMapper());
    }
    
    public SmsRetrieverClient(@NonNull final Context context) {
        super(context, (Api)SmsRetrieverClient.API, (Api$ApiOptions)null, (StatusExceptionMapper)new ApiExceptionMapper());
    }
    
    public abstract Task<Void> startSmsRetriever();
}
