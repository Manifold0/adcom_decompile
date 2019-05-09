package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbo extends RegisterListenerMethod<zzx, Object> {
    private final /* synthetic */ String val$name;
    private final /* synthetic */ String zzcn;
    private final /* synthetic */ ListenerHolder zzco;
    private final /* synthetic */ AdvertisingOptions zzcp;
    private final /* synthetic */ zzbd zzcq;

    zzbo(zzbd zzbd, ListenerHolder listenerHolder, String str, String str2, ListenerHolder listenerHolder2, AdvertisingOptions advertisingOptions) {
        this.zzcq = zzbd;
        this.val$name = str;
        this.zzcn = str2;
        this.zzco = listenerHolder2;
        this.zzcp = advertisingOptions;
        super(listenerHolder);
    }

    protected final /* synthetic */ void registerListener(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzx) anyClient).zza(new zzby(this.zzcq, taskCompletionSource), this.val$name, this.zzcn, this.zzco, this.zzcp);
    }
}
