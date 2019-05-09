package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcz extends TaskApiCall<zzaw, MetadataBuffer> {
    private final /* synthetic */ Query zzds;

    zzcz(zzch zzch, Query query) {
        this.zzds = query;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzgk(this.zzds), new zzhh(taskCompletionSource));
    }
}
