// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.nearby.messages.StatusCallback;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;

final class zzbs extends zzbv
{
    private final /* synthetic */ ListenerHolder zzik;
    
    zzbs(final zzbi zzbi, final GoogleApiClient googleApiClient, final ListenerHolder zzik) {
        this.zzik = zzik;
        super(googleApiClient);
    }
}
