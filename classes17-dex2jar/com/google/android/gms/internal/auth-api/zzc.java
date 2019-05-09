// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;

public class zzc implements IInterface
{
    private final IBinder zzc;
    private final String zzd;
    
    protected zzc(final IBinder zzc, final String zzd) {
        this.zzc = zzc;
        this.zzd = zzd;
    }
    
    public IBinder asBinder() {
        return this.zzc;
    }
    
    protected final Parcel obtainAndWriteInterfaceToken() {
        final Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.zzd);
        return obtain;
    }
    
    protected final void transactAndReadExceptionReturnVoid(final int n, final Parcel parcel) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        try {
            this.zzc.transact(n, parcel, obtain, 0);
            obtain.readException();
        }
        finally {
            parcel.recycle();
            obtain.recycle();
        }
    }
}
