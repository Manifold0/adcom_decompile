package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.messages.StatusCallback;
import com.google.android.gms.nearby.messages.internal.zzy;

public final class zzhb extends zzy {
    private final ListenerHolder<StatusCallback> zzjj;

    public zzhb(ListenerHolder<StatusCallback> listenerHolder) {
        this.zzjj = listenerHolder;
    }

    public final void onPermissionChanged(boolean z) {
        this.zzjj.notifyListener(new zzhc(this, z));
    }
}
