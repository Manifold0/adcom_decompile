// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.IBinder;

public final class zzkw extends zzej implements zzkv
{
    zzkw(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.client.IAdManagerCreator");
    }
    
    public final IBinder zza(final IObjectWrapper objectWrapper, final zzjn zzjn, final String s, final zzxn zzxn, final int n, final int n2) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzjn);
        obtainAndWriteInterfaceToken.writeString(s);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzxn);
        obtainAndWriteInterfaceToken.writeInt(12451000);
        obtainAndWriteInterfaceToken.writeInt(n2);
        final Parcel transactAndReadException = this.transactAndReadException(2, obtainAndWriteInterfaceToken);
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        transactAndReadException.recycle();
        return strongBinder;
    }
}
