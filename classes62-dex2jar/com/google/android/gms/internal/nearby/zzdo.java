// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.IBinder;

public final class zzdo extends zza implements zzdm
{
    zzdo(final IBinder binder) {
        super(binder, "com.google.android.gms.nearby.internal.connection.IConnectionResponseListener");
    }
    
    @Override
    public final void zza(final zzel zzel) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzel);
        this.transactOneway(2, obtainAndWriteInterfaceToken);
    }
}
