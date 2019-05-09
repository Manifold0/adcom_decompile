// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public class zzek extends Binder implements IInterface
{
    private static zzem zzuh;
    
    static {
        zzek.zzuh = null;
    }
    
    protected zzek(final String s) {
        this.attachInterface((IInterface)this, s);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    protected boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        return false;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        boolean onTransact;
        if (n > 16777215) {
            onTransact = super.onTransact(n, parcel, parcel2, n2);
        }
        else {
            parcel.enforceInterface(this.getInterfaceDescriptor());
            onTransact = false;
        }
        return onTransact || this.dispatchTransaction(n, parcel, parcel2, n2);
    }
}