// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;

public final class zae<A extends BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient>> extends zab
{
    private final A zaco;
    
    public zae(final int n, final A zaco) {
        super(n);
        this.zaco = zaco;
    }
    
    @Override
    public final void zaa(@NonNull final Status failedResult) {
        ((BaseImplementation.ApiMethodImpl)this.zaco).setFailedResult(failedResult);
    }
    
    @Override
    public final void zaa(final GoogleApiManager.zaa<?> zaa) throws DeadObjectException {
        try {
            ((BaseImplementation.ApiMethodImpl<R, Api.Client>)this.zaco).run(zaa.zaab());
        }
        catch (RuntimeException ex) {
            this.zaa(ex);
        }
    }
    
    @Override
    public final void zaa(@NonNull final zaab zaab, final boolean b) {
        zaab.zaa(this.zaco, b);
    }
    
    @Override
    public final void zaa(@NonNull final RuntimeException ex) {
        final String simpleName = ex.getClass().getSimpleName();
        final String localizedMessage = ex.getLocalizedMessage();
        ((BaseImplementation.ApiMethodImpl)this.zaco).setFailedResult(new Status(10, new StringBuilder(String.valueOf(simpleName).length() + 2 + String.valueOf(localizedMessage).length()).append(simpleName).append(": ").append(localizedMessage).toString()));
    }
}
