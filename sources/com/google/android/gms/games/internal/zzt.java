package com.google.android.gms.games.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzt<L> extends UnregisterListenerMethod<zze, L> {
    protected zzt(ListenerKey<L> listenerKey) {
        super(listenerKey);
    }

    protected /* synthetic */ void unregisterListener(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        try {
            zzc((zze) anyClient, taskCompletionSource);
        } catch (Exception e) {
            taskCompletionSource.trySetException(e);
        }
    }

    protected abstract void zzc(zze zze, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException;
}
