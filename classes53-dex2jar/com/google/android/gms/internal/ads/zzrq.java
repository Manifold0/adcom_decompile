// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import android.os.IBinder;

public final class zzrq extends zzej implements zzro
{
    zzrq(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.formats.client.IUnconfirmedClickListener");
    }
    
    public final void onUnconfirmedClickCancelled() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(2, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onUnconfirmedClickReceived(final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
}
