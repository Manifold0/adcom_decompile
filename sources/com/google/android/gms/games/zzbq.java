package com.google.android.gms.games;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

final class zzbq implements Continuation<String, Task<Boolean>> {
    private final /* synthetic */ ListenerHolder zzdp;
    private final /* synthetic */ RealTimeMultiplayerClient zzdq;

    zzbq(RealTimeMultiplayerClient realTimeMultiplayerClient, ListenerHolder listenerHolder) {
        this.zzdq = realTimeMultiplayerClient;
        this.zzdp = listenerHolder;
    }

    public final /* synthetic */ Object then(@NonNull Task task) throws Exception {
        return this.zzdq.doUnregisterEventListener(this.zzdp.getListenerKey());
    }
}
