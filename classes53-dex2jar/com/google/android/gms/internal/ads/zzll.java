// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;

public final class zzll extends zzej implements zzlj
{
    zzll(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
    }
    
    public final void setAppMuted(final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }
    
    public final void setAppVolume(final float n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeFloat(n);
        this.transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }
    
    public final void zza() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(1, this.obtainAndWriteInterfaceToken());
    }
    
    public final void zza(final String s, final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        this.transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }
    
    public final void zzb(final IObjectWrapper objectWrapper, final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }
    
    public final float zzdo() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(7, this.obtainAndWriteInterfaceToken());
        final float float1 = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return float1;
    }
    
    public final boolean zzdp() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(8, this.obtainAndWriteInterfaceToken());
        final boolean zza = zzel.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }
    
    public final void zzt(final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }
}
