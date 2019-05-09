// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.account;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.IInterface;
import android.accounts.Account;
import android.os.IBinder;
import com.google.android.gms.internal.auth.zza;

public final class zze extends zza implements zzc
{
    zze(final IBinder binder) {
        super(binder, "com.google.android.gms.auth.account.IWorkAccountService");
    }
    
    @Override
    public final void zza(final com.google.android.gms.auth.account.zza zza, final Account account) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.auth.zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zza);
        com.google.android.gms.internal.auth.zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)account);
        this.transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final com.google.android.gms.auth.account.zza zza, final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.auth.zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zza);
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzb(final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.auth.zzc.writeBoolean(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
}
