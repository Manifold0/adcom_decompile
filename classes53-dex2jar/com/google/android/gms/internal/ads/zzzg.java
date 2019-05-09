// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;

public final class zzzg extends zzej implements zzzf
{
    zzzg(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.mediation.client.rtb.IBannerCallback");
    }
    
    public final void zzbr(final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }
}
