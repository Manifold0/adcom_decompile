// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.Feature;

final class zack extends TaskApiCall<Object, Object>
{
    private final /* synthetic */ Builder zakn;
    
    zack(final Builder zakn, final Feature[] array, final boolean b) {
        this.zakn = zakn;
        super(array, b, null);
    }
    
    @Override
    protected final void doExecute(final Api.AnyClient anyClient, final TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zakn.zakm.accept(anyClient, taskCompletionSource);
    }
}
