// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.os.Bundle;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.GmsClient;

public class zzk extends GmsClient<zzao>
{
    private final String zzca;
    protected final zzbj<zzao> zzcb;
    
    public zzk(final Context context, final Looper looper, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final String zzca, final ClientSettings clientSettings) {
        super(context, looper, 23, clientSettings, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        this.zzcb = new zzl(this);
        this.zzca = zzca;
    }
    
    protected Bundle getGetServiceRequestExtraArgs() {
        final Bundle bundle = new Bundle();
        bundle.putString("client_name", this.zzca);
        return bundle;
    }
    
    public int getMinApkVersion() {
        return 11925000;
    }
    
    protected String getServiceDescriptor() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }
    
    protected String getStartServiceAction() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }
}
