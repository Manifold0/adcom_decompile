// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.IBinder;

public final class zzaes extends zzej implements zzaeq
{
    zzaes(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.request.IAdResponseListener");
    }
    
    public final void zza(final zzaej zzaej) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzaej);
        this.transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
}
