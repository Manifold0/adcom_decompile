// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.net.Uri;
import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;

public final class zzpy extends zzej implements zzpw
{
    zzpy(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.formats.client.INativeAdImage");
    }
    
    public final double getScale() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(3, this.obtainAndWriteInterfaceToken());
        final double double1 = transactAndReadException.readDouble();
        transactAndReadException.recycle();
        return double1;
    }
    
    public final Uri getUri() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(2, this.obtainAndWriteInterfaceToken());
        final Uri uri = (Uri)zzel.zza(transactAndReadException, Uri.CREATOR);
        transactAndReadException.recycle();
        return uri;
    }
    
    public final IObjectWrapper zzjy() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(1, this.obtainAndWriteInterfaceToken());
        final IObjectWrapper interface1 = IObjectWrapper$Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return interface1;
    }
}
