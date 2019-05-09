// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.Feature;
import android.os.RemoteException;
import android.os.DeadObjectException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zag<ResultT> extends zac
{
    private final TaskCompletionSource<ResultT> zacn;
    private final TaskApiCall<Api.AnyClient, ResultT> zacr;
    private final StatusExceptionMapper zacs;
    
    public zag(final int n, final TaskApiCall<Api.AnyClient, ResultT> zacr, final TaskCompletionSource<ResultT> zacn, final StatusExceptionMapper zacs) {
        super(n);
        this.zacn = zacn;
        this.zacr = zacr;
        this.zacs = zacs;
    }
    
    @Override
    public final void zaa(@NonNull final Status status) {
        this.zacn.trySetException(this.zacs.getException(status));
    }
    
    @Override
    public final void zaa(final GoogleApiManager.zaa<?> zaa) throws DeadObjectException {
        try {
            this.zacr.doExecute(zaa.zaab(), this.zacn);
        }
        catch (DeadObjectException ex) {
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
    public final void zaa(@NonNull final zaab zaab, final boolean b) {
        zaab.zaa(this.zacn, b);
    }
    
    @Override
    public final void zaa(@NonNull final RuntimeException ex) {
        this.zacn.trySetException((Exception)ex);
    }
    
    @Nullable
    @Override
    public final Feature[] zab(final GoogleApiManager.zaa<?> zaa) {
        return this.zacr.zabt();
    }
    
    @Override
    public final boolean zac(final GoogleApiManager.zaa<?> zaa) {
        return this.zacr.shouldAutoResolveMissingFeatures();
    }
}
