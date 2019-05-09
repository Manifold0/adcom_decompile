// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.IBinder;

public final class zzep extends zzej implements zzen
{
    zzep(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.clearcut.IClearcut");
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final String s, final String s2) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeString((String)null);
        this.transactAndReadExceptionReturnVoid(8, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final int[] array) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeIntArray((int[])null);
        this.transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }
    
    public final void zzbd() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(3, this.obtainAndWriteInterfaceToken());
    }
    
    public final void zzc(final byte[] array) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeByteArray(array);
        this.transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }
    
    public final void zzg(final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(n);
        this.transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }
    
    public final void zzh(final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(n);
        this.transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken);
    }
}
