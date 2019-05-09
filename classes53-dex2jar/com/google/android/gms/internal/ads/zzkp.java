// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import android.os.IBinder;

public final class zzkp extends zzej implements zzkn
{
    zzkp(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }
    
    public final void zza(final PublisherAdViewOptions publisherAdViewOptions) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)publisherAdViewOptions);
        this.transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzpl zzpl) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzpl);
        this.transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzqw zzqw) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzqw);
        this.transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzqz zzqz) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzqz);
        this.transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzri zzri, final zzjn zzjn) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzri);
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzjn);
        this.transactAndReadExceptionReturnVoid(8, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzrl zzrl) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzrl);
        this.transactAndReadExceptionReturnVoid(10, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final String s, final zzrf zzrf, final zzrc zzrc) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzrf);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzrc);
        this.transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }
    
    public final void zzb(final zzkh zzkh) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzkh);
        this.transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }
    
    public final void zzb(final zzlg zzlg) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzlg);
        this.transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken);
    }
    
    public final zzkk zzdh() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(1, this.obtainAndWriteInterfaceToken());
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        zzkk zzkk;
        if (strongBinder == null) {
            zzkk = null;
        }
        else {
            final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoader");
            if (queryLocalInterface instanceof zzkk) {
                zzkk = (zzkk)queryLocalInterface;
            }
            else {
                zzkk = new zzkm(strongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzkk;
    }
}
