// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import android.os.RemoteException;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.api.GoogleApiClient;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class BaseImplementation
{
    @KeepForSdk
    public abstract static class ApiMethodImpl<R extends Result, A extends Api.AnyClient> extends BasePendingResult<R> implements ResultHolder<R>
    {
        @KeepForSdk
        private final Api<?> mApi;
        @KeepForSdk
        private final Api.AnyClientKey<A> mClientKey;
        
        @Deprecated
        @KeepForSdk
        protected ApiMethodImpl(@NonNull final Api.AnyClientKey<A> anyClientKey, @NonNull final GoogleApiClient googleApiClient) {
            super((GoogleApiClient)Preconditions.checkNotNull((Object)googleApiClient, (Object)"GoogleApiClient must not be null"));
            this.mClientKey = (Api.AnyClientKey<A>)Preconditions.checkNotNull((Object)anyClientKey);
            this.mApi = null;
        }
        
        @KeepForSdk
        protected ApiMethodImpl(@NonNull final Api<?> mApi, @NonNull final GoogleApiClient googleApiClient) {
            super((GoogleApiClient)Preconditions.checkNotNull((Object)googleApiClient, (Object)"GoogleApiClient must not be null"));
            Preconditions.checkNotNull((Object)mApi, (Object)"Api must not be null");
            this.mClientKey = (Api.AnyClientKey<A>)mApi.getClientKey();
            this.mApi = mApi;
        }
        
        @VisibleForTesting
        @KeepForSdk
        protected ApiMethodImpl(@NonNull final CallbackHandler<R> callbackHandler) {
            super((CallbackHandler)callbackHandler);
            this.mClientKey = null;
            this.mApi = null;
        }
        
        @KeepForSdk
        private void setFailedResult(@NonNull final RemoteException ex) {
            this.setFailedResult(new Status(8, ex.getLocalizedMessage(), (PendingIntent)null));
        }
        
        @KeepForSdk
        protected abstract void doExecute(@NonNull final A p0) throws RemoteException;
        
        @KeepForSdk
        public final Api<?> getApi() {
            return this.mApi;
        }
        
        @KeepForSdk
        public final Api.AnyClientKey<A> getClientKey() {
            return this.mClientKey;
        }
        
        @KeepForSdk
        protected void onSetFailedResult(@NonNull final R r) {
        }
        
        @KeepForSdk
        public final void run(@NonNull final A a) throws DeadObjectException {
            Api.AnyClient client = a;
            if (a instanceof SimpleClientAdapter) {
                client = ((SimpleClientAdapter)a).getClient();
            }
            try {
                this.doExecute((A)client);
            }
            catch (DeadObjectException failedResult) {
                this.setFailedResult((RemoteException)failedResult);
                throw failedResult;
            }
            catch (RemoteException failedResult2) {
                this.setFailedResult(failedResult2);
            }
        }
        
        @KeepForSdk
        @Override
        public final void setFailedResult(@NonNull final Status status) {
            Preconditions.checkArgument(!status.isSuccess(), (Object)"Failed result must not be success");
            final Result failedResult = this.createFailedResult(status);
            this.setResult((R)failedResult);
            this.onSetFailedResult((R)failedResult);
        }
    }
    
    @KeepForSdk
    public interface ResultHolder<R>
    {
        @KeepForSdk
        void setFailedResult(final Status p0);
        
        @KeepForSdk
        void setResult(final R p0);
    }
}
