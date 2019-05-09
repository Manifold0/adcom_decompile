// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public final class zzrn extends zzej implements zzrl
{
    zzrn(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.formats.client.IOnUnifiedNativeAdLoadedListener");
    }
    
    public final void zza(final zzrr zzrr) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzrr);
        this.transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
}
