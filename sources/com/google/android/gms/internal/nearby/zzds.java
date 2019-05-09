package com.google.android.gms.internal.nearby;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzds extends zzb implements zzdr {
    public zzds() {
        super("com.google.android.gms.nearby.internal.connection.IDiscoveryListener");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 2:
                zza((zzer) zzc.zza(parcel, zzer.CREATOR));
                break;
            case 3:
                zza((zzet) zzc.zza(parcel, zzet.CREATOR));
                break;
            case 4:
                zza((zzfd) zzc.zza(parcel, zzfd.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}
