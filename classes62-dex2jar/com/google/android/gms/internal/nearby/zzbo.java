// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;

final class zzbo extends RegisterListenerMethod<zzx, Object>
{
    private final /* synthetic */ String val$name;
    private final /* synthetic */ String zzcn;
    private final /* synthetic */ ListenerHolder zzco;
    private final /* synthetic */ AdvertisingOptions zzcp;
    private final /* synthetic */ zzbd zzcq;
    
    zzbo(final zzbd zzcq, final ListenerHolder listenerHolder, final String val$name, final String zzcn, final ListenerHolder zzco, final AdvertisingOptions zzcp) {
        this.zzcq = zzcq;
        this.val$name = val$name;
        this.zzcn = zzcn;
        this.zzco = zzco;
        this.zzcp = zzcp;
        super(listenerHolder);
    }
}
