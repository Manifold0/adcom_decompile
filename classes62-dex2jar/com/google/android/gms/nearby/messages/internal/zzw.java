// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import android.os.IBinder;
import com.google.android.gms.internal.nearby.zza;

public final class zzw extends zza implements zzu
{
    zzw(final IBinder binder) {
        super(binder, "com.google.android.gms.nearby.messages.internal.IPublishCallback");
    }
    
    @Override
    public final void onExpired() throws RemoteException {
        this.transactOneway(1, this.obtainAndWriteInterfaceToken());
    }
}
