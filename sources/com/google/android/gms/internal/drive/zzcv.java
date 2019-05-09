package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.drive.events.OpenFileCallback;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcv extends UnregisterListenerMethod<zzaw, OpenFileCallback> {
    private final /* synthetic */ zzg zzfs;

    zzcv(zzch zzch, ListenerKey listenerKey, zzg zzg) {
        this.zzfs = zzg;
        super(listenerKey);
    }

    protected final /* synthetic */ void unregisterListener(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(Boolean.valueOf(this.zzfs.cancel()));
    }
}
