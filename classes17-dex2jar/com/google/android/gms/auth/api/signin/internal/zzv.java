// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.IInterface;
import com.google.android.gms.internal.auth-api.zze;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import android.os.IBinder;
import com.google.android.gms.internal.auth-api.zzc;

public final class zzv extends zzc implements zzu
{
    zzv(final IBinder binder) {
        super(binder, "com.google.android.gms.auth.api.signin.internal.ISignInService");
    }
    
    @Override
    public final void zzc(final zzs zzs, final GoogleSignInOptions googleSignInOptions) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zze.zzc(obtainAndWriteInterfaceToken, (IInterface)zzs);
        zze.zzc(obtainAndWriteInterfaceToken, (Parcelable)googleSignInOptions);
        this.transactAndReadExceptionReturnVoid(101, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzd(final zzs zzs, final GoogleSignInOptions googleSignInOptions) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zze.zzc(obtainAndWriteInterfaceToken, (IInterface)zzs);
        zze.zzc(obtainAndWriteInterfaceToken, (Parcelable)googleSignInOptions);
        this.transactAndReadExceptionReturnVoid(102, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zze(final zzs zzs, final GoogleSignInOptions googleSignInOptions) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zze.zzc(obtainAndWriteInterfaceToken, (IInterface)zzs);
        zze.zzc(obtainAndWriteInterfaceToken, (Parcelable)googleSignInOptions);
        this.transactAndReadExceptionReturnVoid(103, obtainAndWriteInterfaceToken);
    }
}
