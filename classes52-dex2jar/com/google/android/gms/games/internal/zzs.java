// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;

public abstract class zzs<L> extends RegisterListenerMethod<zze, L>
{
    protected zzs(final ListenerHolder<L> listenerHolder) {
        super((ListenerHolder)listenerHolder);
    }
    
    protected abstract void zzb(final zze p0, final TaskCompletionSource<Void> p1) throws RemoteException;
}
