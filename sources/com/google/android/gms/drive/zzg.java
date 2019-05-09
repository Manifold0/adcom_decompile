package com.google.android.gms.drive;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.drive.Drive.zza;
import com.google.android.gms.internal.drive.zzaw;

final class zzg extends AbstractClientBuilder<zzaw, zza> {
    zzg() {
    }

    public final /* synthetic */ Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        return new zzaw(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener, ((zza) obj).zzg());
    }
}
