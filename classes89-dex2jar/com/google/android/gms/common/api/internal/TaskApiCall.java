// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.Nullable;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;

@KeepForSdk
public abstract class TaskApiCall<A extends Api.AnyClient, ResultT>
{
    private final Feature[] zake;
    private final boolean zakl;
    
    @Deprecated
    @KeepForSdk
    public TaskApiCall() {
        this.zake = null;
        this.zakl = false;
    }
    
    @KeepForSdk
    private TaskApiCall(final Feature[] zake, final boolean zakl) {
        this.zake = zake;
        this.zakl = zakl;
    }
    
    @KeepForSdk
    public static <A extends Api.AnyClient, ResultT> Builder<A, ResultT> builder() {
        return new Builder<A, ResultT>(null);
    }
    
    @KeepForSdk
    protected abstract void doExecute(final A p0, final TaskCompletionSource<ResultT> p1) throws RemoteException;
    
    @KeepForSdk
    public boolean shouldAutoResolveMissingFeatures() {
        return this.zakl;
    }
    
    @Nullable
    public final Feature[] zabt() {
        return this.zake;
    }
    
    @KeepForSdk
    public static class Builder<A extends Api.AnyClient, ResultT>
    {
        private Feature[] zake;
        private boolean zakl;
        private RemoteCall<A, TaskCompletionSource<ResultT>> zakm;
        
        private Builder() {
            this.zakl = true;
        }
        
        @KeepForSdk
        public TaskApiCall<A, ResultT> build() {
            Preconditions.checkArgument(this.zakm != null, (Object)"execute parameter required");
            return (TaskApiCall<A, ResultT>)new zack(this, this.zake, this.zakl);
        }
        
        @Deprecated
        @KeepForSdk
        public Builder<A, ResultT> execute(final BiConsumer<A, TaskCompletionSource<ResultT>> biConsumer) {
            this.zakm = (RemoteCall<A, TaskCompletionSource<ResultT>>)new zacj(biConsumer);
            return this;
        }
        
        @KeepForSdk
        public Builder<A, ResultT> run(final RemoteCall<A, TaskCompletionSource<ResultT>> zakm) {
            this.zakm = zakm;
            return this;
        }
        
        @KeepForSdk
        public Builder<A, ResultT> setAutoResolveMissingFeatures(final boolean zakl) {
            this.zakl = zakl;
            return this;
        }
        
        @KeepForSdk
        public Builder<A, ResultT> setFeatures(final Feature... zake) {
            this.zake = zake;
            return this;
        }
    }
}
