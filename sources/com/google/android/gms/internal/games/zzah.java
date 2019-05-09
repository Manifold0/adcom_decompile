package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzah<TResult> extends TaskApiCall<zze, TResult> {
    protected /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        Exception e;
        try {
            zza((zze) anyClient, taskCompletionSource);
            return;
        } catch (RemoteException e2) {
            e = e2;
        } catch (SecurityException e3) {
            e = e3;
        }
        taskCompletionSource.trySetException(e);
    }

    protected abstract void zza(zze zze, TaskCompletionSource<TResult> taskCompletionSource) throws RemoteException;
}
