// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;

public final class zzlc extends zzej implements zzla
{
    zzlc(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.client.IAppEventListener");
    }
    
    public final void onAppEvent(final String s, final String s2) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeString(s2);
        this.transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
}
