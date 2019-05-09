// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.Feature;

final class zaca extends RegisterListenerMethod<Object, Object>
{
    private final /* synthetic */ RegistrationMethods.Builder zakh;
    
    zaca(final RegistrationMethods.Builder zakh, final ListenerHolder listenerHolder, final Feature[] array, final boolean b) {
        this.zakh = zakh;
        super(listenerHolder, array, b);
    }
    
    @Override
    protected final void registerListener(final Api.AnyClient anyClient, final TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zakh.zakb.accept(anyClient, taskCompletionSource);
    }
}
