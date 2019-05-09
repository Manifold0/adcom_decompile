// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public final class zzqy extends zzej implements zzqw
{
    zzqy(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.formats.client.IOnAppInstallAdLoadedListener");
    }
    
    public final void zza(final zzqk zzqk) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzqk);
        this.transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
}
