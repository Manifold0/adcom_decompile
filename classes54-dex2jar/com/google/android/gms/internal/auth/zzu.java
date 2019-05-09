// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.auth.api.accounttransfer.zzn;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.GmsClient;

public final class zzu extends GmsClient<zzz>
{
    private final Bundle zzbv;
    
    public zzu(final Context context, final Looper looper, final ClientSettings clientSettings, final zzn zzn, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 128, clientSettings, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        if (zzn == null) {
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
        return "com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferService";
    }
    
    protected final String getStartServiceAction() {
        return "com.google.android.gms.auth.api.accounttransfer.service.START";
    }
}
