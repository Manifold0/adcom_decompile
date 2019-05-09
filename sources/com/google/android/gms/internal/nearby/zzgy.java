package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.messages.internal.zzq;

public final class zzgy extends zzq {
    private final ListenerHolder<ResultHolder<Status>> zzjj;
    private boolean zzjl = false;

    public zzgy(ListenerHolder<ResultHolder<Status>> listenerHolder) {
        this.zzjj = listenerHolder;
    }

    public final synchronized void zza(Status status) throws RemoteException {
        if (this.zzjl) {
            String valueOf = String.valueOf(status);
            Log.wtf("NearbyMessagesCallbackWrapper", new StringBuilder(String.valueOf(valueOf).length() + 28).append("Received multiple statuses: ").append(valueOf).toString(), new Exception());
        } else {
            this.zzjj.notifyListener(new zzgz(this, status));
            this.zzjl = true;
        }
    }
}
