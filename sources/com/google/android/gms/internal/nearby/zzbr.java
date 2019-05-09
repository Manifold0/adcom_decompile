package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbr extends UnregisterListenerMethod<zzx, EndpointDiscoveryCallback> {
    zzbr(zzbd zzbd, ListenerKey listenerKey) {
        super(listenerKey);
    }

    protected final /* synthetic */ void unregisterListener(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzx) anyClient).stopDiscovery();
        taskCompletionSource.setResult(Boolean.valueOf(true));
    }
}
