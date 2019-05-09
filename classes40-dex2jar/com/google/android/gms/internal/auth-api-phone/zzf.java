// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api-phone;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public final class zzf extends zza implements zze
{
    zzf(final IBinder binder) {
        super(binder, "com.google.android.gms.auth.api.phone.internal.ISmsRetrieverApiService");
    }
    
    @Override
    public final void zza(final zzg zzg) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzg);
        this.transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
}
