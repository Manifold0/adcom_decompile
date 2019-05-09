// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.messages.internal.zzq;

public final class zzgy extends zzq
{
    private final ListenerHolder<BaseImplementation$ResultHolder<Status>> zzjj;
    private boolean zzjl;
    
    public zzgy(final ListenerHolder<BaseImplementation$ResultHolder<Status>> zzjj) {
        this.zzjl = false;
        this.zzjj = zzjj;
    }
    
    @Override
    public final void zza(final Status status) throws RemoteException {
        synchronized (this) {
            if (!this.zzjl) {
                this.zzjj.notifyListener((ListenerHolder$Notifier)new zzgz(this, status));
                this.zzjl = true;
            }
            else {
                final String value = String.valueOf(status);
                Log.wtf("NearbyMessagesCallbackWrapper", new StringBuilder(String.valueOf(value).length() + 28).append("Received multiple statuses: ").append(value).toString(), (Throwable)new Exception());
            }
        }
    }
}
