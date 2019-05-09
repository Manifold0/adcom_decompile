package com.google.android.gms.internal.nearby;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzde extends zzb implements zzdd {
    public zzde() {
        super("com.google.android.gms.nearby.internal.connection.IAdvertisingCallback");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 2:
                zza((zzej) zzc.zza(parcel, zzej.CREATOR));
                break;
            case 3:
                zza((zzfb) zzc.zza(parcel, zzfb.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}
