// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;

final class zzbq extends RegisterListenerMethod<zzx, EndpointDiscoveryCallback>
{
    private final /* synthetic */ String zzcn;
    private final /* synthetic */ ListenerHolder zzco;
    private final /* synthetic */ zzbd zzcq;
    private final /* synthetic */ DiscoveryOptions zzcr;
    
    zzbq(final zzbd zzcq, final ListenerHolder listenerHolder, final String zzcn, final ListenerHolder zzco, final DiscoveryOptions zzcr) {
        this.zzcq = zzcq;
        this.zzcn = zzcn;
        this.zzco = zzco;
        this.zzcr = zzcr;
        super(listenerHolder);
    }
}
