// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.account.zzd;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.auth.account.zzc;
import com.google.android.gms.common.internal.GmsClient;

public final class zzr extends GmsClient<zzc>
{
    public zzr(final Context context, final Looper looper, final ClientSettings clientSettings, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 120, clientSettings, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
    
    public final int getMinApkVersion() {
        return 12451000;
    }
    
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.auth.account.IWorkAccountService";
    }
    
    protected final String getStartServiceAction() {
        return "com.google.android.gms.auth.account.workaccount.START";
    }
}
