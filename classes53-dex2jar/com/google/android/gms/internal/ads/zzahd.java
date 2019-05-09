// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.IBinder;

public final class zzahd extends zzej implements zzahc
{
    zzahd(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdCreator");
    }
    
    public final IBinder zza(final IObjectWrapper objectWrapper, final zzxn zzxn, final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzxn);
        obtainAndWriteInterfaceToken.writeInt(12451000);
        final Parcel transactAndReadException = this.transactAndReadException(1, obtainAndWriteInterfaceToken);
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        transactAndReadException.recycle();
        return strongBinder;
    }
}
