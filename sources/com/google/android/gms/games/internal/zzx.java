package com.google.android.gms.games.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.games.zzb;
import com.google.android.gms.internal.games.zzc;

public abstract class zzx extends zzb implements zzw {
    public zzx() {
        super("com.google.android.gms.games.internal.IGamesClient");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1001) {
            return false;
        }
        Parcelable zzn = zzn();
        parcel2.writeNoException();
        zzc.zzb(parcel2, zzn);
        return true;
    }
}
