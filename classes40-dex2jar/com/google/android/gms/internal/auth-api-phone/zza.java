// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api-phone;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;

public class zza implements IInterface
{
    private final IBinder zza;
    private final String zzb;
    
    protected zza(final IBinder zza, final String zzb) {
        this.zza = zza;
        this.zzb = zzb;
    }
    
    public IBinder asBinder() {
        return this.zza;
    }
    
    protected final Parcel obtainAndWriteInterfaceToken() {
        final Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.zzb);
        return obtain;
    }
    
    protected final void transactAndReadExceptionReturnVoid(final int n, final Parcel parcel) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        try {
            this.zza.transact(1, parcel, obtain, 0);
            obtain.readException();
        }
        finally {
            parcel.recycle();
            obtain.recycle();
        }
    }
}
