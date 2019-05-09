package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.nearby.zzb;
import com.google.android.gms.internal.nearby.zzc;

public abstract class zzq extends zzb implements zzp {
    public zzq() {
        super("com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zza((Status) zzc.zza(parcel, Status.CREATOR));
        return true;
    }
}
