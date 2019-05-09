// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.GmsClient;

public final class zzf extends GmsClient<zzi>
{
    public zzf(final Context context, final Looper looper, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final ClientSettings clientSettings) {
        super(context, looper, 69, clientSettings, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
    
    public final int getMinApkVersion() {
        return 12451000;
    }
    
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.nearby.bootstrap.internal.INearbyBootstrapService";
    }
    
    protected final String getStartServiceAction() {
        return "com.google.android.gms.nearby.bootstrap.service.NearbyBootstrapService.START";
    }
}
