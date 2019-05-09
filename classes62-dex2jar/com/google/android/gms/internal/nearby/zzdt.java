// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.IBinder;

public final class zzdt extends zza implements zzdr
{
    zzdt(final IBinder binder) {
        super(binder, "com.google.android.gms.nearby.internal.connection.IDiscoveryListener");
    }
    
    @Override
    public final void zza(final zzer zzer) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzer);
        this.transactOneway(2, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzet zzet) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzet);
        this.transactOneway(3, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzfd zzfd) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzfd);
        this.transactOneway(4, obtainAndWriteInterfaceToken);
    }
}
