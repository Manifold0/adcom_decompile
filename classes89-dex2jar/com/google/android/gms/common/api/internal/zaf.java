// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.Feature;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api;

public final class zaf extends zad<Void>
{
    private final RegisterListenerMethod<Api.AnyClient, ?> zacp;
    private final UnregisterListenerMethod<Api.AnyClient, ?> zacq;
    
    public zaf(final zabw zabw, final TaskCompletionSource<Void> taskCompletionSource) {
        super(3, taskCompletionSource);
        this.zacp = zabw.zajx;
        this.zacq = zabw.zajy;
    }
    
    @Nullable
    @Override
    public final Feature[] zab(final GoogleApiManager.zaa<?> zaa) {
        return this.zacp.getRequiredFeatures();
    }
    
    @Override
    public final boolean zac(final GoogleApiManager.zaa<?> zaa) {
        return this.zacp.shouldAutoResolveMissingFeatures();
    }
    
    public final void zad(final GoogleApiManager.zaa<?> zaa) throws RemoteException {
        this.zacp.registerListener(zaa.zaab(), (TaskCompletionSource<Void>)this.zacn);
        if (this.zacp.getListenerKey() != null) {
            zaa.zabk().put(this.zacp.getListenerKey(), new zabw(this.zacp, this.zacq));
        }
    }
}
