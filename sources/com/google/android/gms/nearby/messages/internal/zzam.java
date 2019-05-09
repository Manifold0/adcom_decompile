package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.messages.Message;

final /* synthetic */ class zzam implements zzbd {
    private final Message zzhs;

    zzam(Message message) {
        this.zzhs = message;
    }

    public final void zza(zzah zzah, ListenerHolder listenerHolder) {
        zzah.zza(listenerHolder, zzaf.zza(this.zzhs));
    }
}
