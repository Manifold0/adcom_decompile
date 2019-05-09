package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbt extends RegisterListenerMethod<zzx, String> {
    zzbt(zzbd zzbd, ListenerHolder listenerHolder) {
        super(listenerHolder);
    }

    protected final /* synthetic */ void registerListener(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(null);
    }
}
