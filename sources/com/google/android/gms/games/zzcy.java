package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.internal.zzs;
import com.google.android.gms.games.video.Videos.CaptureOverlayStateListener;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcy extends zzs<CaptureOverlayStateListener> {
    private final /* synthetic */ ListenerHolder zzbi;

    zzcy(VideosClient videosClient, ListenerHolder listenerHolder, ListenerHolder listenerHolder2) {
        this.zzbi = listenerHolder2;
        super(listenerHolder);
    }

    protected final void zzb(zze zze, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException, SecurityException {
        zze.zzg(this.zzbi);
        taskCompletionSource.setResult(null);
    }
}
