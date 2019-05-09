// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.base;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;

public class zaa implements IInterface
{
    private final IBinder zaa;
    private final String zab;
    
    protected zaa(final IBinder zaa, final String zab) {
        this.zaa = zaa;
        this.zab = zab;
    }
    
    public IBinder asBinder() {
        return this.zaa;
    }
    
    protected final Parcel zaa() {
        final Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.zab);
        return obtain;
    }
    
    protected final Parcel zaa(final int n, final Parcel parcel) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        try {
            this.zaa.transact(n, parcel, obtain, 0);
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
    
    protected final void zab(final int n, final Parcel parcel) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        try {
            this.zaa.transact(n, parcel, obtain, 0);
            obtain.readException();
        }
        finally {
            parcel.recycle();
            obtain.recycle();
        }
    }
    
    protected final void zac(final int n, final Parcel parcel) throws RemoteException {
        try {
            this.zaa.transact(1, parcel, (Parcel)null, 1);
        }
        finally {
            parcel.recycle();
        }
    }
}
