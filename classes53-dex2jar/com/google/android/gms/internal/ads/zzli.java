// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;

public final class zzli extends zzej implements zzlg
{
    zzli(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
    }
    
    public final long getValue() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(1, this.obtainAndWriteInterfaceToken());
        final long long1 = transactAndReadException.readLong();
        transactAndReadException.recycle();
        return long1;
    }
}
