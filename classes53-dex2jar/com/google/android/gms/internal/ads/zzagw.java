// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;

public final class zzagw extends zzej implements zzagu
{
    zzagw(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.reward.client.IRewardItem");
    }
    
    public final int getAmount() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(2, this.obtainAndWriteInterfaceToken());
        final int int1 = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return int1;
    }
    
    public final String getType() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(1, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
}
