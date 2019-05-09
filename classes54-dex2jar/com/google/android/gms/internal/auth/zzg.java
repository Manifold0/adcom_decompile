// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.AccountChangeEventsResponse;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.os.Bundle;
import android.accounts.Account;
import android.os.IBinder;

public final class zzg extends zza implements zze
{
    zzg(final IBinder binder) {
        super(binder, "com.google.android.auth.IAuthManagerService");
    }
    
    @Override
    public final Bundle zza(final Account account) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)account);
        final Parcel transactAndReadException = this.transactAndReadException(7, obtainAndWriteInterfaceToken);
        final Bundle bundle = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Bundle>)Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle;
    }
    
    @Override
    public final Bundle zza(final Account account, final String s, final Bundle bundle) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)account);
        obtainAndWriteInterfaceToken.writeString(s);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)bundle);
        final Parcel transactAndReadException = this.transactAndReadException(5, obtainAndWriteInterfaceToken);
        final Bundle bundle2 = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Bundle>)Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle2;
    }
    
    @Override
    public final Bundle zza(final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        final Parcel transactAndReadException = this.transactAndReadException(8, obtainAndWriteInterfaceToken);
        final Bundle bundle = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Bundle>)Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle;
    }
    
    @Override
    public final Bundle zza(final String s, Bundle bundle) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)bundle);
        final Parcel transactAndReadException = this.transactAndReadException(2, obtainAndWriteInterfaceToken);
        bundle = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Bundle>)Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle;
    }
    
    @Override
    public final AccountChangeEventsResponse zza(final AccountChangeEventsRequest accountChangeEventsRequest) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)accountChangeEventsRequest);
        final Parcel transactAndReadException = this.transactAndReadException(3, obtainAndWriteInterfaceToken);
        final AccountChangeEventsResponse accountChangeEventsResponse = zzc.zza(transactAndReadException, AccountChangeEventsResponse.CREATOR);
        transactAndReadException.recycle();
        return accountChangeEventsResponse;
    }
}
