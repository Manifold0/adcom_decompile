package com.google.android.gms.internal.drive;

import android.content.IntentSender;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.OpenFileActivityOptions;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbf extends TaskApiCall<zzaw, IntentSender> {
    private final /* synthetic */ OpenFileActivityOptions zzeo;

    zzbf(zzbb zzbb, OpenFileActivityOptions openFileActivityOptions) {
        this.zzeo = openFileActivityOptions;
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(((zzeo) ((zzaw) anyClient).getService()).zza(new zzgg(this.zzeo.zzay, this.zzeo.zzaz, this.zzeo.zzbb, this.zzeo.zzbc)));
    }
}
