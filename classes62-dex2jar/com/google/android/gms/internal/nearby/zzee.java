// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.IBinder;

public final class zzee extends zza implements zzec
{
    zzee(final IBinder binder) {
        super(binder, "com.google.android.gms.nearby.internal.connection.IStartAdvertisingResultListener");
    }
    
    @Override
    public final void zza(final zzez zzez) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzez);
        this.transactOneway(2, obtainAndWriteInterfaceToken);
    }
}
