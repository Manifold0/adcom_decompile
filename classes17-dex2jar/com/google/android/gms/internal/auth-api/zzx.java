// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api;

import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public final class zzx extends zzc implements zzw
{
    zzx(final IBinder binder) {
        super(binder, "com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
    }
    
    @Override
    public final void zzc(final zzu zzu) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zze.zzc(obtainAndWriteInterfaceToken, (IInterface)zzu);
        this.transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzc(final zzu zzu, final CredentialRequest credentialRequest) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zze.zzc(obtainAndWriteInterfaceToken, (IInterface)zzu);
        zze.zzc(obtainAndWriteInterfaceToken, (Parcelable)credentialRequest);
        this.transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzc(final zzu zzu, final zzs zzs) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zze.zzc(obtainAndWriteInterfaceToken, (IInterface)zzu);
        zze.zzc(obtainAndWriteInterfaceToken, (Parcelable)zzs);
        this.transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzc(final zzu zzu, final zzy zzy) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zze.zzc(obtainAndWriteInterfaceToken, (IInterface)zzu);
        zze.zzc(obtainAndWriteInterfaceToken, (Parcelable)zzy);
        this.transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }
}
