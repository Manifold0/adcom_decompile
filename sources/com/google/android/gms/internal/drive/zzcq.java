package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcq extends UnregisterListenerMethod<zzaw, zzdi> {
    private final /* synthetic */ DriveResource zzfo;
    private final /* synthetic */ zzdi zzfp;

    zzcq(zzch zzch, ListenerKey listenerKey, DriveResource driveResource, zzdi zzdi) {
        this.zzfo = driveResource;
        this.zzfp = zzdi;
        super(listenerKey);
    }

    protected final /* synthetic */ void unregisterListener(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzgm(this.zzfo.getDriveId(), 1), this.zzfp.zzgf, null, new zzhk(taskCompletionSource));
    }
}
