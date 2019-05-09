// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.IInterface;
import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.content.IntentSender;
import android.os.IBinder;

public final class zzep extends zza implements zzeo
{
    zzep(final IBinder binder) {
        super(binder, "com.google.android.gms.drive.internal.IDriveService");
    }
    
    @Override
    public final IntentSender zza(final zzgg zzgg) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzgg);
        final Parcel transactAndReadException = this.transactAndReadException(10, obtainAndWriteInterfaceToken);
        final IntentSender intentSender = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<IntentSender>)IntentSender.CREATOR);
        transactAndReadException.recycle();
        return intentSender;
    }
    
    @Override
    public final IntentSender zza(final zzu zzu) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzu);
        final Parcel transactAndReadException = this.transactAndReadException(11, obtainAndWriteInterfaceToken);
        final IntentSender intentSender = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<IntentSender>)IntentSender.CREATOR);
        transactAndReadException.recycle();
        return intentSender;
    }
    
    @Override
    public final zzec zza(final zzgd zzgd, final zzeq zzeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzgd);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzeq);
        final Parcel transactAndReadException = this.transactAndReadException(7, obtainAndWriteInterfaceToken);
        final zzec zzec = zzc.zza(transactAndReadException, com.google.android.gms.internal.drive.zzec.CREATOR);
        transactAndReadException.recycle();
        return zzec;
    }
    
    @Override
    public final void zza(final zzab zzab, final zzeq zzeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzab);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzeq);
        this.transactAndReadExceptionReturnVoid(24, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzad zzad) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzad);
        this.transactAndReadExceptionReturnVoid(16, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzek zzek, final zzeq zzeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzek);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzeq);
        this.transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzeq zzeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzeq);
        this.transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzex zzex, final zzeq zzeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzex);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzeq);
        this.transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzgk zzgk, final zzeq zzeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzgk);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzeq);
        this.transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzgm zzgm, final zzes zzes, final String s, final zzeq zzeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzgm);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzes);
        obtainAndWriteInterfaceToken.writeString((String)null);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzeq);
        this.transactAndReadExceptionReturnVoid(15, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzgo zzgo, final zzeq zzeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzgo);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzeq);
        this.transactAndReadExceptionReturnVoid(36, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzgq zzgq, final zzeq zzeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzgq);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzeq);
        this.transactAndReadExceptionReturnVoid(28, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzgv zzgv, final zzeq zzeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzgv);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzeq);
        this.transactAndReadExceptionReturnVoid(17, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzgx zzgx, final zzeq zzeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzgx);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzeq);
        this.transactAndReadExceptionReturnVoid(38, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzgz zzgz, final zzeq zzeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzgz);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzeq);
        this.transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzj zzj, final zzes zzes, final String s, final zzeq zzeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzj);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzes);
        obtainAndWriteInterfaceToken.writeString((String)null);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzeq);
        this.transactAndReadExceptionReturnVoid(14, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzm zzm, final zzeq zzeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzm);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzeq);
        this.transactAndReadExceptionReturnVoid(18, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzo zzo, final zzeq zzeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzo);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzeq);
        this.transactAndReadExceptionReturnVoid(8, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzr zzr, final zzeq zzeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzr);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzeq);
        this.transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzw zzw, final zzeq zzeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzw);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzeq);
        this.transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzy zzy, final zzeq zzeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzy);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzeq);
        this.transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzb(final zzeq zzeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzeq);
        this.transactAndReadExceptionReturnVoid(35, obtainAndWriteInterfaceToken);
    }
}
