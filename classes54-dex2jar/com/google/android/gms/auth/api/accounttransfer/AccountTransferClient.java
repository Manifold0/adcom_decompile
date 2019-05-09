// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.internal.auth.zzy;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzz;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.internal.auth.zzs;
import com.google.android.gms.internal.auth.zzah;
import android.app.PendingIntent;
import com.google.android.gms.internal.auth.zzaf;
import com.google.android.gms.internal.auth.zzad;
import com.google.android.gms.internal.auth.zzab;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.auth.zzv;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import android.content.Context;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.GoogleApi$Settings$Builder;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.internal.auth.zzu;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.common.api.GoogleApi;

public class AccountTransferClient extends GoogleApi<zzn>
{
    private static final Api$ClientKey<zzu> zzaj;
    private static final Api$AbstractClientBuilder<zzu, zzn> zzak;
    private static final Api<zzn> zzal;
    
    static {
        zzaj = new Api$ClientKey();
        zzak = new com.google.android.gms.auth.api.accounttransfer.zzc();
        zzal = new Api("AccountTransfer.ACCOUNT_TRANSFER_API", (Api$AbstractClientBuilder)AccountTransferClient.zzak, (Api$ClientKey)AccountTransferClient.zzaj);
    }
    
    AccountTransferClient(@NonNull final Activity activity) {
        super(activity, (Api)AccountTransferClient.zzal, (Api$ApiOptions)null, new GoogleApi$Settings$Builder().setMapper((StatusExceptionMapper)new ApiExceptionMapper()).build());
    }
    
    AccountTransferClient(@NonNull final Context context) {
        super(context, (Api)AccountTransferClient.zzal, (Api$ApiOptions)null, new GoogleApi$Settings$Builder().setMapper((StatusExceptionMapper)new ApiExceptionMapper()).build());
    }
    
    private static void zza(final TaskCompletionSource taskCompletionSource, final Status status) {
        taskCompletionSource.setException((Exception)new AccountTransferException(status));
    }
    
    public Task<DeviceMetaData> getDeviceMetaData(final String s) {
        Preconditions.checkNotNull((Object)s);
        return (Task<DeviceMetaData>)this.doRead((TaskApiCall)new zzg(this, new zzv(s)));
    }
    
    public Task<Void> notifyCompletion(final String s, final int n) {
        Preconditions.checkNotNull((Object)s);
        return (Task<Void>)this.doWrite((TaskApiCall)new zzj(this, new zzab(s, n)));
    }
    
    public Task<byte[]> retrieveData(final String s) {
        Preconditions.checkNotNull((Object)s);
        return (Task<byte[]>)this.doRead((TaskApiCall)new zze(this, new zzad(s)));
    }
    
    public Task<Void> sendData(final String s, final byte[] array) {
        Preconditions.checkNotNull((Object)s);
        Preconditions.checkNotNull((Object)array);
        return (Task<Void>)this.doWrite((TaskApiCall)new zzd(this, new zzaf(s, array)));
    }
    
    public Task<Void> showUserChallenge(final String s, final PendingIntent pendingIntent) {
        Preconditions.checkNotNull((Object)s);
        Preconditions.checkNotNull((Object)pendingIntent);
        return (Task<Void>)this.doWrite((TaskApiCall)new zzi(this, new zzah(s, pendingIntent)));
    }
    
    private static class zza<T> extends zzs
    {
        private AccountTransferClient.zzb<T> zzav;
        
        public zza(final AccountTransferClient.zzb<T> zzav) {
            this.zzav = zzav;
        }
        
        @Override
        public final void onFailure(final Status status) {
            this.zzav.zza(status);
        }
    }
    
    private abstract static class zzb<T> extends TaskApiCall<zzu, T>
    {
        private TaskCompletionSource<T> zzaw;
        
        protected final void setResult(final T result) {
            this.zzaw.setResult((Object)result);
        }
        
        protected final void zza(final Status status) {
            zza(this.zzaw, status);
        }
        
        protected abstract void zza(final zzz p0) throws RemoteException;
    }
    
    private abstract static class zzc extends zzb<Void>
    {
        zzy zzax;
        
        private zzc() {
            super(null);
            this.zzax = new zzk(this);
        }
    }
}
