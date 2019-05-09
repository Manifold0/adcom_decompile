package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.internal.ListenerHolder.Notifier;
import com.google.android.gms.drive.events.OpenFileCallback;

final class zzdo implements Notifier<OpenFileCallback> {
    private final /* synthetic */ zzdg zzgn;

    zzdo(zzdk zzdk, zzdg zzdg) {
        this.zzgn = zzdg;
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        this.zzgn.accept((OpenFileCallback) obj);
    }

    public final void onNotifyListenerFailed() {
    }
}
