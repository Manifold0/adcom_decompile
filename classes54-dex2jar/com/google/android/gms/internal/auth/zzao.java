// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import android.os.Parcelable;
import com.google.android.gms.auth.api.proxy.ProxyRequest;
import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public final class zzao extends zza implements zzan
{
    zzao(final IBinder binder) {
        super(binder, "com.google.android.gms.auth.api.internal.IAuthService");
    }
    
    @Override
    public final void zza(final zzal zzal) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzal);
        this.transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzal zzal, final ProxyRequest proxyRequest) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzal);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)proxyRequest);
        this.transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
}
