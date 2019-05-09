// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api;

import android.os.Bundle;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.internal.GmsClient;

public final class zzr extends GmsClient<zzw>
{
    @Nullable
    private final Auth.AuthCredentialsOptions zzaq;
    
    public zzr(final Context context, final Looper looper, final ClientSettings clientSettings, final Auth.AuthCredentialsOptions zzaq, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 68, clientSettings, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        this.zzaq = zzaq;
    }
    
    protected final Bundle getGetServiceRequestExtraArgs() {
        if (this.zzaq == null) {
            return new Bundle();
        }
        return this.zzaq.toBundle();
    }
    
    public final int getMinApkVersion() {
        return 12800000;
    }
    
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.auth.api.credentials.internal.ICredentialsService";
    }
    
    protected final String getStartServiceAction() {
        return "com.google.android.gms.auth.api.credentials.service.START";
    }
    
    final Auth.AuthCredentialsOptions zzd() {
        return this.zzaq;
    }
}
