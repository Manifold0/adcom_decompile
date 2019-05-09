package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

final /* synthetic */ class zzci implements Continuation {
    private final ListenerHolder zzfm;

    zzci(ListenerHolder listenerHolder) {
        this.zzfm = listenerHolder;
    }

    public final Object then(Task task) {
        return zzch.zza(this.zzfm, task);
    }
}
