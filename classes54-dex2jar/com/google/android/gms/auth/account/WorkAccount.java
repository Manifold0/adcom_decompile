// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.account;

import android.content.Context;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.internal.auth.zzh;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.internal.auth.zzr;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api;

public class WorkAccount
{
    public static final Api<Api$ApiOptions$NoOptions> API;
    private static final Api$AbstractClientBuilder<zzr, Api$ApiOptions$NoOptions> CLIENT_BUILDER;
    private static final Api$ClientKey<zzr> CLIENT_KEY;
    @Deprecated
    public static final WorkAccountApi WorkAccountApi;
    
    static {
        CLIENT_KEY = new Api$ClientKey();
        CLIENT_BUILDER = new zzf();
        API = new Api("WorkAccount.API", (Api$AbstractClientBuilder)WorkAccount.CLIENT_BUILDER, (Api$ClientKey)WorkAccount.CLIENT_KEY);
        WorkAccountApi = new zzh();
    }
    
    private WorkAccount() {
    }
    
    public static WorkAccountClient getClient(@NonNull final Activity activity) {
        return new WorkAccountClient(activity);
    }
    
    public static WorkAccountClient getClient(@NonNull final Context context) {
        return new WorkAccountClient(context);
    }
}
