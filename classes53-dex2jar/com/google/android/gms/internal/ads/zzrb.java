// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public final class zzrb extends zzej implements zzqz
{
    zzrb(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.formats.client.IOnContentAdLoadedListener");
    }
    
    public final void zza(final zzqo zzqo) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzqo);
        this.transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
}
