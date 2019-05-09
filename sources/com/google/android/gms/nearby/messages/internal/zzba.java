package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzba extends UnregisterListenerMethod<zzah, T> {
    private final /* synthetic */ zzak zzia;
    private final /* synthetic */ zzbd zzie;

    zzba(zzak zzak, ListenerKey listenerKey, zzbd zzbd) {
        this.zzia = zzak;
        this.zzie = zzbd;
        super(listenerKey);
    }

    protected final /* synthetic */ void unregisterListener(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzie.zza((zzah) anyClient, this.zzia.zza(taskCompletionSource));
    }
}
