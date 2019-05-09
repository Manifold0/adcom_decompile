// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ParcelFileDescriptor;
import android.os.IBinder;

public final class zzsl extends zzej implements zzsk
{
    zzsl(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.httpcache.IHttpAssetsCacheService");
    }
    
    public final ParcelFileDescriptor zza(final zzsg zzsg) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzsg);
        final Parcel transactAndReadException = this.transactAndReadException(1, obtainAndWriteInterfaceToken);
        final ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)zzel.zza(transactAndReadException, ParcelFileDescriptor.CREATOR);
        transactAndReadException.recycle();
        return parcelFileDescriptor;
    }
}
