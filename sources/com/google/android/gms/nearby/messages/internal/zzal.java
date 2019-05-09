package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.PublishOptions;

final /* synthetic */ class zzal implements zzbd {
    private final zzak zzho;
    private final Message zzhp;
    private final zzbe zzhq;
    private final PublishOptions zzhr;

    zzal(zzak zzak, Message message, zzbe zzbe, PublishOptions publishOptions) {
        this.zzho = zzak;
        this.zzhp = message;
        this.zzhq = zzbe;
        this.zzhr = publishOptions;
    }

    public final void zza(zzah zzah, ListenerHolder listenerHolder) {
        this.zzho.zza(this.zzhp, this.zzhq, this.zzhr, zzah, listenerHolder);
    }
}
