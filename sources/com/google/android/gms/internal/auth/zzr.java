package com.google.android.gms.internal.auth;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.auth.account.zzc;
import com.google.android.gms.auth.account.zzd;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.kongregate.android.internal.browser.C0462b;

public final class zzr extends GmsClient<zzc> {
    public zzr(Context context, Looper looper, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, C0462b.f362a, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }

    protected final String getStartServiceAction() {
        return "com.google.android.gms.auth.account.workaccount.START";
    }

    protected final String getServiceDescriptor() {
        return "com.google.android.gms.auth.account.IWorkAccountService";
    }

    public final int getMinApkVersion() {
        return 12451000;
    }

    protected final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        return zzd.zzc(iBinder);
    }
}
