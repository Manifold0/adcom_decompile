// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;

public class zzej implements IInterface
{
    private final IBinder zzuf;
    private final String zzug;
    
    protected zzej(final IBinder zzuf, final String zzug) {
        this.zzuf = zzuf;
        this.zzug = zzug;
    }
    
    public IBinder asBinder() {
        return this.zzuf;
    }
    
    protected final Parcel obtainAndWriteInterfaceToken() {
        final Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.zzug);
        return obtain;
    }
    
    protected final Parcel transactAndReadException(final int n, final Parcel parcel) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        try {
            this.zzuf.transact(n, parcel, obtain, 0);
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
            this.zzuf.transact(n, parcel, obtain, 0);
            obtain.readException();
        }
        finally {
            parcel.recycle();
            obtain.recycle();
        }
    }
}
