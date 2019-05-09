// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;

public final class zzoc extends zzej implements zzoa
{
    zzoc(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
    }
    
    public final String getContent() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(2, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
    
    public final void recordClick() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(4, this.obtainAndWriteInterfaceToken());
    }
    
    public final void recordImpression() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(5, this.obtainAndWriteInterfaceToken());
    }
    
    public final void zzg(final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        this.transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }
    
    public final String zzjn() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(1, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
}
