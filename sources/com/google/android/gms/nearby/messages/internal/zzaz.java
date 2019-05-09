package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzaz extends RegisterListenerMethod<zzah, T> {
    private final /* synthetic */ zzak zzia;
    private final /* synthetic */ zzbd zzid;

    zzaz(zzak zzak, ListenerHolder listenerHolder, zzbd zzbd) {
        this.zzia = zzak;
        this.zzid = zzbd;
        super(listenerHolder);
    }

    protected final /* synthetic */ void registerListener(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzid.zza((zzah) anyClient, this.zzia.zza(taskCompletionSource));
    }
}
