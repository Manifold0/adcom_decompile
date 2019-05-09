// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.auth.api.AuthProxy;
import android.text.TextUtils;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.auth.api.AuthProxyOptions;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.GmsClient;

public final class zzak extends GmsClient<zzan>
{
    private final Bundle zzbv;
    
    public zzak(final Context context, final Looper looper, final ClientSettings clientSettings, final AuthProxyOptions authProxyOptions, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 16, clientSettings, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        if (authProxyOptions == null) {
            this.zzbv = new Bundle();
            return;
        }
        throw new NoSuchMethodError();
    }
    
    protected final Bundle getGetServiceRequestExtraArgs() {
        return this.zzbv;
    }
    
    public final int getMinApkVersion() {
        return 12451000;
    }
    
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.auth.api.internal.IAuthService";
    }
    
    protected final String getStartServiceAction() {
        return "com.google.android.gms.auth.service.START";
    }
    
    public final boolean requiresSignIn() {
        final ClientSettings clientSettings = this.getClientSettings();
        return !TextUtils.isEmpty((CharSequence)clientSettings.getAccountName()) && !clientSettings.getApplicableScopes((Api)AuthProxy.API).isEmpty();
    }
}
