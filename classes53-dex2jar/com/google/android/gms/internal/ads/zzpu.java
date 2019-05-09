// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;
import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;

public final class zzpu extends zzej implements zzps
{
    zzpu(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
    }
    
    public final String getText() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(2, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
    
    public final List<zzpw> zzjr() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(3, this.obtainAndWriteInterfaceToken());
        final ArrayList zzb = zzel.zzb(transactAndReadException);
        transactAndReadException.recycle();
        return (List<zzpw>)zzb;
    }
}
