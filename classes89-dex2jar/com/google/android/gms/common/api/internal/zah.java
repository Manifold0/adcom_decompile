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

public final class zah extends zad<Boolean>
{
    private final ListenerHolder.ListenerKey<?> zact;
    
    public zah(final ListenerHolder.ListenerKey<?> zact, final TaskCompletionSource<Boolean> taskCompletionSource) {
        super(4, taskCompletionSource);
        this.zact = zact;
    }
    
    @Nullable
    @Override
    public final Feature[] zab(final GoogleApiManager.zaa<?> zaa) {
        final zabw zabw = zaa.zabk().get(this.zact);
        if (zabw == null) {
            return null;
        }
        return zabw.zajx.getRequiredFeatures();
    }
    
    @Override
    public final boolean zac(final GoogleApiManager.zaa<?> zaa) {
        final zabw zabw = zaa.zabk().get(this.zact);
        return zabw != null && zabw.zajx.shouldAutoResolveMissingFeatures();
    }
    
    public final void zad(final GoogleApiManager.zaa<?> zaa) throws RemoteException {
        final zabw zabw = zaa.zabk().remove(this.zact);
        if (zabw != null) {
            zabw.zajy.unregisterListener(zaa.zaab(), (TaskCompletionSource<Boolean>)this.zacn);
            zabw.zajx.clearListener();
            return;
        }
        this.zacn.trySetResult((Object)false);
    }
}
