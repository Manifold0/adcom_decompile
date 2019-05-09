// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;

public final class zzeb extends zza implements zzdz
{
    zzeb(final IBinder binder) {
        super(binder, "com.google.android.gms.nearby.internal.connection.IResultListener");
    }
    
    @Override
    public final void zzc(final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(n);
        this.transactOneway(2, obtainAndWriteInterfaceToken);
    }
}
