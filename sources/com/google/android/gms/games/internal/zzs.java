package com.google.android.gms.games.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzs<L> extends RegisterListenerMethod<zze, L> {
    protected zzs(ListenerHolder<L> listenerHolder) {
        super(listenerHolder);
    }

    protected /* synthetic */ void registerListener(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        try {
            zzb((zze) anyClient, taskCompletionSource);
        } catch (Exception e) {
            taskCompletionSource.trySetException(e);
        }
    }

    protected abstract void zzb(zze zze, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException;
}
