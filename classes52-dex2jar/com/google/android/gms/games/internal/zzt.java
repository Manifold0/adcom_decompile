// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;

public abstract class zzt<L> extends UnregisterListenerMethod<zze, L>
{
    protected zzt(final ListenerHolder$ListenerKey<L> listenerHolder$ListenerKey) {
        super((ListenerHolder$ListenerKey)listenerHolder$ListenerKey);
    }
    
    protected abstract void zzc(final zze p0, final TaskCompletionSource<Boolean> p1) throws RemoteException;
}
