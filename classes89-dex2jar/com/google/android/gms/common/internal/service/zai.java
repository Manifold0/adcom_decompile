// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal.service;

import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.GmsClient;

public final class zai extends GmsClient<zal>
{
    public zai(final Context context, final Looper looper, final ClientSettings clientSettings, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 39, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }
    
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.common.internal.service.ICommonService";
    }
    
    public final String getStartServiceAction() {
        return "com.google.android.gms.common.service.START";
    }
}
