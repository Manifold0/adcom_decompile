// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.common;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public class zzb extends Binder implements IInterface
{
    private static zzd zzc;
    
    static {
        zzb.zzc = null;
    }
    
    protected zzb(final String s) {
        this.attachInterface((IInterface)this, s);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
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
        return onTransact || this.zza(n, parcel, parcel2, n2);
    }
    
    protected boolean zza(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        return false;
    }
}
