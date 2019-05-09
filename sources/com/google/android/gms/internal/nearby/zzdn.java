package com.google.android.gms.internal.nearby;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzdn extends zzb implements zzdm {
    public zzdn() {
        super("com.google.android.gms.nearby.internal.connection.IConnectionResponseListener");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 2) {
            return false;
        }
        zza((zzel) zzc.zza(parcel, zzel.CREATOR));
        return true;
    }
}
