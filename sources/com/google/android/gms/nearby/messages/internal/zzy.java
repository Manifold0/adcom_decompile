package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.nearby.zzb;
import com.google.android.gms.internal.nearby.zzc;

public abstract class zzy extends zzb implements zzx {
    public zzy() {
        super("com.google.android.gms.nearby.messages.internal.IStatusCallback");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        onPermissionChanged(zzc.zza(parcel));
        return true;
    }
}
