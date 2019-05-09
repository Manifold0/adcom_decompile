package com.google.android.gms.internal.nearby;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzea extends zzb implements zzdz {
    public zzea() {
        super("com.google.android.gms.nearby.internal.connection.IResultListener");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 2) {
            return false;
        }
        zzc(parcel.readInt());
        return true;
    }
}
