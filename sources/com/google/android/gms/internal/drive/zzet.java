package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzet extends zzb implements zzes {
    public zzet() {
        super("com.google.android.gms.drive.internal.IEventCallback");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zzc((zzfj) zzc.zza(parcel, zzfj.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
