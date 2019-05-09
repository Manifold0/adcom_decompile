// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.IInterface;
import android.os.Parcel;
import android.os.IBinder;

public final class zzxp extends zzej implements zzxn
{
    zzxp(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
    }
    
    public final zzxq zzbm(final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        final Parcel transactAndReadException = this.transactAndReadException(1, obtainAndWriteInterfaceToken);
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        zzxq zzxq;
        if (strongBinder == null) {
            zzxq = null;
        }
        else {
            final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            if (queryLocalInterface instanceof zzxq) {
                zzxq = (zzxq)queryLocalInterface;
            }
            else {
                zzxq = new zzxs(strongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzxq;
    }
    
    public final boolean zzbn(final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        final Parcel transactAndReadException = this.transactAndReadException(2, obtainAndWriteInterfaceToken);
        final boolean zza = zzel.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }
    
    public final zzzj zzbq(final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        final Parcel transactAndReadException = this.transactAndReadException(3, obtainAndWriteInterfaceToken);
        final zzzj zzt = zzzk.zzt(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzt;
    }
}
