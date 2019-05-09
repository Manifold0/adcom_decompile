// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.location.zzc;
import android.os.IBinder;
import com.google.android.gms.internal.location.zza;

public final class zzw extends zza implements zzu
{
    zzw(final IBinder binder) {
        super(binder, "com.google.android.gms.location.ILocationCallback");
    }
    
    @Override
    public final void onLocationAvailability(final LocationAvailability locationAvailability) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)locationAvailability);
        this.transactOneway(2, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void onLocationResult(final LocationResult locationResult) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)locationResult);
        this.transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
