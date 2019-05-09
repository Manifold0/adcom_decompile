// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;

@KeepForSdk
public abstract class UnregisterListenerMethod<A extends Api.AnyClient, L>
{
    private final ListenerHolder.ListenerKey<L> zajl;
    
    @KeepForSdk
    protected UnregisterListenerMethod(final ListenerHolder.ListenerKey<L> zajl) {
        this.zajl = zajl;
    }
    
    @KeepForSdk
    public ListenerHolder.ListenerKey<L> getListenerKey() {
        return this.zajl;
    }
    
    @KeepForSdk
    protected abstract void unregisterListener(final A p0, final TaskCompletionSource<Boolean> p1) throws RemoteException;
}
