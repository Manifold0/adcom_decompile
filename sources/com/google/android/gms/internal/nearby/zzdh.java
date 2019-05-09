package com.google.android.gms.internal.nearby;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzdh extends zzb implements zzdg {
    public zzdh() {
        super("com.google.android.gms.nearby.internal.connection.IConnectionEventListener");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 2:
                zza((zzev) zzc.zza(parcel, zzev.CREATOR));
                break;
            case 3:
                zza((zzep) zzc.zza(parcel, zzep.CREATOR));
                break;
            case 4:
                zza((zzex) zzc.zza(parcel, zzex.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}
