package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.internal.ListenerHolder.Notifier;
import com.google.android.gms.drive.DriveFile.DownloadProgressListener;

final class zzbq implements Notifier<DownloadProgressListener> {
    private final /* synthetic */ long zzez;
    private final /* synthetic */ long zzfa;

    zzbq(zzbp zzbp, long j, long j2) {
        this.zzez = j;
        this.zzfa = j2;
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((DownloadProgressListener) obj).onProgress(this.zzez, this.zzfa);
    }

    public final void onNotifyListenerFailed() {
    }
}
