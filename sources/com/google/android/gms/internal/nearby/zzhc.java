package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.messages.StatusCallback;

final class zzhc extends zzha<StatusCallback> {
    private final /* synthetic */ boolean zzjm;

    zzhc(zzhb zzhb, boolean z) {
        this.zzjm = z;
    }

    public final /* synthetic */ void notifyListener(Object obj) {
        ((StatusCallback) obj).onPermissionChanged(this.zzjm);
    }
}
