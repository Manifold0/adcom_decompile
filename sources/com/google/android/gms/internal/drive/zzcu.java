package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.common.internal.ICancelToken.Stub;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.events.OpenFileCallback;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcu extends RegisterListenerMethod<zzaw, OpenFileCallback> {
    private final /* synthetic */ DriveFile zzfq;
    private final /* synthetic */ int zzfr;
    private final /* synthetic */ zzg zzfs;
    private final /* synthetic */ ListenerHolder zzft;
    private final /* synthetic */ zzch zzfu;

    zzcu(zzch zzch, ListenerHolder listenerHolder, DriveFile driveFile, int i, zzg zzg, ListenerHolder listenerHolder2) {
        this.zzfu = zzch;
        this.zzfq = driveFile;
        this.zzfr = i;
        this.zzfs = zzg;
        this.zzft = listenerHolder2;
        super(listenerHolder);
    }

    protected final /* synthetic */ void registerListener(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzaw zzaw = (zzaw) anyClient;
        this.zzfs.setCancelToken(Stub.asInterface(((zzeo) zzaw.getService()).zza(new zzgd(this.zzfq.getDriveId(), this.zzfr, 0), new zzdk(this.zzfu, this.zzfs, this.zzft)).zzgq));
        taskCompletionSource.setResult(null);
    }
}
