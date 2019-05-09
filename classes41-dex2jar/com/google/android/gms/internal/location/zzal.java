// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.IBinder;

public final class zzal extends zza implements zzaj
{
    zzal(final IBinder binder) {
        super(binder, "com.google.android.gms.location.internal.IFusedLocationProviderCallback");
    }
    
    @Override
    public final void zza(final zzad zzad) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzad);
        this.transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
