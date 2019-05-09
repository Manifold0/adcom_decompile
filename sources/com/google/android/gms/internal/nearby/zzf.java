package com.google.android.gms.internal.nearby;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

public final class zzf extends GmsClient<zzi> {
    public zzf(Context context, Looper looper, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, ClientSettings clientSettings) {
        super(context, looper, 69, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }

    protected final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.bootstrap.internal.INearbyBootstrapService");
        return queryLocalInterface instanceof zzi ? (zzi) queryLocalInterface : new zzj(iBinder);
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
