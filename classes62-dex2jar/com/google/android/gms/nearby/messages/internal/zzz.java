// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import android.os.Parcel;
import com.google.android.gms.internal.nearby.zzc;
import android.os.IBinder;
import com.google.android.gms.internal.nearby.zza;

public final class zzz extends zza implements zzx
{
    zzz(final IBinder binder) {
        super(binder, "com.google.android.gms.nearby.messages.internal.IStatusCallback");
    }
    
    @Override
    public final void onPermissionChanged(final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, b);
        this.transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
