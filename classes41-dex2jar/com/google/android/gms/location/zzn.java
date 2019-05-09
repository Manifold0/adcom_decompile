// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.internal.location.zzaj;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.internal.location.zzbd;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;

final class zzn extends RegisterListenerMethod<zzaz, LocationCallback>
{
    private final /* synthetic */ zzbd zzy;
    private final /* synthetic */ ListenerHolder zzz;
    
    zzn(final FusedLocationProviderClient fusedLocationProviderClient, final ListenerHolder listenerHolder, final zzbd zzy, final ListenerHolder zzz) {
        this.zzy = zzy;
        this.zzz = zzz;
        super(listenerHolder);
    }
}
