package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager.zaa;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zad<T> extends zac {
    protected final TaskCompletionSource<T> zacn;

    public zad(int i, TaskCompletionSource<T> taskCompletionSource) {
        super(i);
        this.zacn = taskCompletionSource;
    }

    protected abstract void zad(zaa<?> zaa) throws RemoteException;

    public void zaa(@NonNull Status status) {
        this.zacn.trySetException(new ApiException(status));
    }

    public void zaa(@NonNull RuntimeException runtimeException) {
        this.zacn.trySetException(runtimeException);
    }

    public void zaa(@NonNull zaab zaab, boolean z) {
    }

    public final void zaa(zaa<?> zaa) throws DeadObjectException {
        try {
            zad(zaa);
        } catch (RemoteException e) {
            zaa(zab.zaa(e));
            throw e;
        } catch (RemoteException e2) {
            zaa(zab.zaa(e2));
        } catch (RuntimeException e3) {
            zaa(e3);
        }
    }
}
