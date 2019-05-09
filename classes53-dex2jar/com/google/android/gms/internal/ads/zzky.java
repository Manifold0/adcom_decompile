// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzky extends zzek implements zzkx
{
    public zzky() {
        super("com.google.android.gms.ads.internal.client.IAdMetadataListener");
    }
    
    public static zzkx zzc(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdMetadataListener");
        if (queryLocalInterface instanceof zzkx) {
            return (zzkx)queryLocalInterface;
        }
        return new zzkz(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 1) {
            this.zzt();
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}
