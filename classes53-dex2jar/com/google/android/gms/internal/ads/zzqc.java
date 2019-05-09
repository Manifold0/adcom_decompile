// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import android.os.Parcel;
import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.RemoteException;
import android.os.IBinder;

public final class zzqc extends zzej implements zzqa
{
    zzqc(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
    }
    
    public final void destroy() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(4, this.obtainAndWriteInterfaceToken());
    }
    
    public final void zza(final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        this.transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }
    
    public final IObjectWrapper zzak(final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        final Parcel transactAndReadException = this.transactAndReadException(2, obtainAndWriteInterfaceToken);
        final IObjectWrapper interface1 = IObjectWrapper$Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return interface1;
    }
    
    public final void zzb(final IObjectWrapper objectWrapper, final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        obtainAndWriteInterfaceToken.writeInt(n);
        this.transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }
    
    public final void zzb(final String s, final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        this.transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
    
    public final void zzc(final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        this.transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }
}
