// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;

abstract class zzbv extends BaseImplementation$ApiMethodImpl<Status, zzah>
{
    private final ListenerHolder<BaseImplementation$ResultHolder<Status>> zzir;
    
    public zzbv(final GoogleApiClient googleApiClient) {
        super((Api)Nearby.MESSAGES_API, googleApiClient);
        this.zzir = (ListenerHolder<BaseImplementation$ResultHolder<Status>>)googleApiClient.registerListener((Object)this);
    }
    
    final ListenerHolder<BaseImplementation$ResultHolder<Status>> zzah() {
        return this.zzir;
    }
}
