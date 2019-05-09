// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.IBinder;

public final class zzqe extends zzej implements zzqd
{
    zzqe(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegateCreator");
    }
    
    public final IBinder zza(final IObjectWrapper objectWrapper, final IObjectWrapper objectWrapper2, final IObjectWrapper objectWrapper3, final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper2);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper3);
        obtainAndWriteInterfaceToken.writeInt(12451000);
        final Parcel transactAndReadException = this.transactAndReadException(1, obtainAndWriteInterfaceToken);
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        transactAndReadException.recycle();
        return strongBinder;
    }
}
