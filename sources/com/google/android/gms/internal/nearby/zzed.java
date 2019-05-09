package com.google.android.gms.internal.nearby;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzed extends zzb implements zzec {
    public zzed() {
        super("com.google.android.gms.nearby.internal.connection.IStartAdvertisingResultListener");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 2) {
            return false;
        }
        zza((zzez) zzc.zza(parcel, zzez.CREATOR));
        return true;
    }
}
