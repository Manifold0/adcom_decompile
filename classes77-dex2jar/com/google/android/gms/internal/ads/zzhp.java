// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.os.IBinder;

public final class zzhp extends zzej implements zzho
{
    zzhp(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.cache.ICacheService");
    }
    
    public final zzhi zza(final zzhl zzhl) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzhl);
        final Parcel transactAndReadException = this.transactAndReadException(1, obtainAndWriteInterfaceToken);
        final zzhi zzhi = (zzhi)zzel.zza(transactAndReadException, (Parcelable$Creator)com.google.android.gms.internal.ads.zzhi.CREATOR);
        transactAndReadException.recycle();
        return zzhi;
    }
}
