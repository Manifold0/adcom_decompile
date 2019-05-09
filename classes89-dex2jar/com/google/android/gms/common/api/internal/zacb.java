// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api;

final class zacb extends UnregisterListenerMethod<Object, Object>
{
    private final /* synthetic */ RegistrationMethods.Builder zakh;
    
    zacb(final RegistrationMethods.Builder zakh, final ListenerHolder.ListenerKey listenerKey) {
        this.zakh = zakh;
        super(listenerKey);
    }
    
    @Override
    protected final void unregisterListener(final Api.AnyClient anyClient, final TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zakh.zakc.accept(anyClient, taskCompletionSource);
    }
}
