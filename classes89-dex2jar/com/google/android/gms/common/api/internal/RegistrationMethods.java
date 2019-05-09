// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.Feature;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;

@KeepForSdk
public class RegistrationMethods<A extends Api.AnyClient, L>
{
    public final RegisterListenerMethod<A, L> zajz;
    public final UnregisterListenerMethod<A, L> zaka;
    
    private RegistrationMethods(final RegisterListenerMethod<A, L> zajz, final UnregisterListenerMethod<A, L> zaka) {
        this.zajz = zajz;
        this.zaka = zaka;
    }
    
    @KeepForSdk
    public static <A extends Api.AnyClient, L> Builder<A, L> builder() {
        return new Builder<A, L>(null);
    }
    
    @KeepForSdk
    public static class Builder<A extends Api.AnyClient, L>
    {
        private boolean zajw;
        private RemoteCall<A, TaskCompletionSource<Void>> zakb;
        private RemoteCall<A, TaskCompletionSource<Boolean>> zakc;
        private ListenerHolder<L> zakd;
        private Feature[] zake;
        
        private Builder() {
            this.zajw = true;
        }
        
        @KeepForSdk
        public RegistrationMethods<A, L> build() {
            final boolean b = true;
            Preconditions.checkArgument(this.zakb != null, (Object)"Must set register function");
            Preconditions.checkArgument(this.zakc != null, (Object)"Must set unregister function");
            Preconditions.checkArgument(this.zakd != null && b, (Object)"Must set holder");
            return new RegistrationMethods<A, L>(new zaca(this, this.zakd, this.zake, this.zajw), new zacb(this, (ListenerHolder.ListenerKey)this.zakd.getListenerKey()), null);
        }
        
        @KeepForSdk
        public Builder<A, L> register(final RemoteCall<A, TaskCompletionSource<Void>> zakb) {
            this.zakb = zakb;
            return this;
        }
        
        @Deprecated
        @KeepForSdk
        public Builder<A, L> register(final BiConsumer<A, TaskCompletionSource<Void>> biConsumer) {
            this.zakb = (RemoteCall<A, TaskCompletionSource<Void>>)new zaby(biConsumer);
            return this;
        }
        
        @KeepForSdk
        public Builder<A, L> setAutoResolveMissingFeatures(final boolean zajw) {
            this.zajw = zajw;
            return this;
        }
        
        @KeepForSdk
        public Builder<A, L> setFeatures(final Feature[] zake) {
            this.zake = zake;
            return this;
        }
        
        @KeepForSdk
        public Builder<A, L> unregister(final RemoteCall<A, TaskCompletionSource<Boolean>> zakc) {
            this.zakc = zakc;
            return this;
        }
        
        @Deprecated
        @KeepForSdk
        public Builder<A, L> unregister(final BiConsumer<A, TaskCompletionSource<Boolean>> biConsumer) {
            this.zakb = (RemoteCall<A, TaskCompletionSource<Void>>)new zabz(this);
            return this;
        }
        
        @KeepForSdk
        public Builder<A, L> withHolder(final ListenerHolder<L> zakd) {
            this.zakd = zakd;
            return this;
        }
    }
}
