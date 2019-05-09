package com.google.android.gms.internal.nearby;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzdk extends zzb implements zzdj {
    public zzdk() {
        super("com.google.android.gms.nearby.internal.connection.IConnectionLifecycleListener");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 2:
                zza((zzeh) zzc.zza(parcel, zzeh.CREATOR));
                break;
            case 3:
                zza((zzen) zzc.zza(parcel, zzen.CREATOR));
                break;
            case 4:
                zza((zzep) zzc.zza(parcel, zzep.CREATOR));
                break;
            case 5:
                zza((zzef) zzc.zza(parcel, zzef.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}
