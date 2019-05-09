// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import android.os.Parcel;

public abstract class zzea extends zzb implements zzdz
{
    public zzea() {
        super("com.google.android.gms.nearby.internal.connection.IResultListener");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 2) {
            this.zzc(parcel.readInt());
            return true;
        }
        return false;
    }
}
