// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;

final class zzbp extends zzbv
{
    private final /* synthetic */ ListenerHolder zzco;
    
    zzbp(final zzbi zzbi, final GoogleApiClient googleApiClient, final ListenerHolder zzco) {
        this.zzco = zzco;
        super(googleApiClient);
    }
}
