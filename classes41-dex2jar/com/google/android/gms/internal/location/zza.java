// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

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
    
    protected final Parcel transactAndReadException(final int n, final Parcel parcel) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        try {
            this.zza.transact(n, parcel, obtain, 0);
            obtain.readException();
            return obtain;
        }
        catch (RuntimeException ex) {
            obtain.recycle();
            throw ex;
        }
        finally {
            parcel.recycle();
        }
    }
    
    protected final void transactAndReadExceptionReturnVoid(final int n, final Parcel parcel) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        try {
            this.zza.transact(n, parcel, obtain, 0);
            obtain.readException();
        }
        finally {
            parcel.recycle();
            obtain.recycle();
        }
    }
    
    protected final void transactOneway(final int n, final Parcel parcel) throws RemoteException {
        try {
            this.zza.transact(n, parcel, (Parcel)null, 1);
        }
        finally {
            parcel.recycle();
        }
    }
}
