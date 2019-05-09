// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public final class zzre extends zzej implements zzrc
{
    zzre(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
    }
    
    public final void zzb(final zzqs zzqs, final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzqs);
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
}
