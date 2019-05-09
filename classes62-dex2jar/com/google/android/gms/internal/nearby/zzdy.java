// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.IBinder;

public final class zzdy extends zza implements zzdw
{
    zzdy(final IBinder binder) {
        super(binder, "com.google.android.gms.nearby.internal.connection.IPayloadListener");
    }
    
    @Override
    public final void zza(final zzev zzev) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzev);
        this.transactOneway(2, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzex zzex) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzex);
        this.transactOneway(3, obtainAndWriteInterfaceToken);
    }
}
