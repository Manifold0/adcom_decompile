// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import android.support.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;

@KeepForSdk
public abstract class RegisterListenerMethod<A extends Api.AnyClient, L>
{
    private final ListenerHolder<L> zaju;
    private final Feature[] zajv;
    private final boolean zajw;
    
    @KeepForSdk
    protected RegisterListenerMethod(final ListenerHolder<L> zaju) {
        this.zaju = zaju;
        this.zajv = null;
        this.zajw = false;
    }
    
    @KeepForSdk
    protected RegisterListenerMethod(final ListenerHolder<L> zaju, final Feature[] zajv, final boolean zajw) {
        this.zaju = zaju;
        this.zajv = zajv;
        this.zajw = zajw;
    }
    
    @KeepForSdk
    public void clearListener() {
        this.zaju.clear();
    }
    
    @KeepForSdk
    public ListenerHolder.ListenerKey<L> getListenerKey() {
        return this.zaju.getListenerKey();
    }
    
    @Nullable
    @KeepForSdk
    public Feature[] getRequiredFeatures() {
        return this.zajv;
    }
    
    @KeepForSdk
    protected abstract void registerListener(final A p0, final TaskCompletionSource<Void> p1) throws RemoteException;
    
    public final boolean shouldAutoResolveMissingFeatures() {
        return this.zajw;
    }
}
