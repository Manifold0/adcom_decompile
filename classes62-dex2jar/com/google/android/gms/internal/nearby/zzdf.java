// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.IBinder;

public final class zzdf extends zza implements zzdd
{
    zzdf(final IBinder binder) {
        super(binder, "com.google.android.gms.nearby.internal.connection.IAdvertisingCallback");
    }
    
    @Override
    public final void zza(final zzej zzej) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzej);
        this.transactOneway(2, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzfb zzfb) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzfb);
        this.transactOneway(3, obtainAndWriteInterfaceToken);
    }
}
