// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;

public final class zzxy extends zzej implements zzxw
{
    zzxy(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.mediation.client.IMediationResponseMetadata");
    }
    
    public final int zzmm() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(1, this.obtainAndWriteInterfaceToken());
        final int int1 = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return int1;
    }
}
