// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;

final class zzcs extends zzcy
{
    private final /* synthetic */ String val$name;
    private final /* synthetic */ String zzcv;
    private final /* synthetic */ ListenerHolder zzdf;
    
    zzcs(final zzca zzca, final GoogleApiClient googleApiClient, final String val$name, final String zzcv, final ListenerHolder zzdf) {
        this.val$name = val$name;
        this.zzcv = zzcv;
        this.zzdf = zzdf;
        super(googleApiClient, null);
    }
}
