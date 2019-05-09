// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.RemoteException;
import android.os.IBinder;

public final class zzqh extends zzej implements zzqf
{
    zzqh(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.formats.client.INativeAdViewHolderDelegate");
    }
    
    public final void unregisterNativeAd() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(2, this.obtainAndWriteInterfaceToken());
    }
    
    public final void zza(final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        this.transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
    
    public final void zzc(final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        this.transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }
}
