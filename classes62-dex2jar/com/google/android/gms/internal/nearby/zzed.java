// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import android.os.Parcel;

public abstract class zzed extends zzb implements zzec
{
    public zzed() {
        super("com.google.android.gms.nearby.internal.connection.IStartAdvertisingResultListener");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 2) {
            this.zza(com.google.android.gms.internal.nearby.zzc.zza(parcel, zzez.CREATOR));
            return true;
        }
        return false;
    }
}
