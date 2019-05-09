package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcp extends RegisterListenerMethod<zzaw, zzdi> {
    private final /* synthetic */ DriveResource zzfo;
    private final /* synthetic */ zzdi zzfp;

    zzcp(zzch zzch, ListenerHolder listenerHolder, DriveResource driveResource, zzdi zzdi) {
        this.zzfo = driveResource;
        this.zzfp = zzdi;
        super(listenerHolder);
    }

    protected final /* synthetic */ void registerListener(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzj(1, this.zzfo.getDriveId()), this.zzfp.zzgf, null, new zzhl(taskCompletionSource));
    }
}
