// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.internal.location.zzaj;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;

final class zzo extends UnregisterListenerMethod<zzaz, LocationCallback>
{
    private final /* synthetic */ FusedLocationProviderClient zzaa;
    
    zzo(final FusedLocationProviderClient zzaa, final ListenerHolder$ListenerKey listenerHolder$ListenerKey) {
        this.zzaa = zzaa;
        super(listenerHolder$ListenerKey);
    }
}
