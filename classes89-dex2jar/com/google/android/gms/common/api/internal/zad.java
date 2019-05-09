// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zad<T> extends zac
{
    protected final TaskCompletionSource<T> zacn;
    
    public zad(final int n, final TaskCompletionSource<T> zacn) {
        super(n);
        this.zacn = zacn;
    }
    
    @Override
    public void zaa(@NonNull final Status status) {
        this.zacn.trySetException((Exception)new ApiException(status));
    }
    
    @Override
    public final void zaa(final GoogleApiManager.zaa<?> zaa) throws DeadObjectException {
        try {
            this.zad(zaa);
        }
        catch (DeadObjectException ex) {
            this.zaa(zaa((RemoteException)ex));
            throw ex;
        }
        catch (RemoteException ex2) {
            this.zaa(zaa(ex2));
        }
        catch (RuntimeException ex3) {
            this.zaa(ex3);
        }
    }
    
    @Override
    public void zaa(@NonNull final zaab zaab, final boolean b) {
    }
    
    @Override
    public void zaa(@NonNull final RuntimeException ex) {
        this.zacn.trySetException((Exception)ex);
    }
    
    protected abstract void zad(final GoogleApiManager.zaa<?> p0) throws RemoteException;
}
