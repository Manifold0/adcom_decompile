package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.nearby.zzb;
import com.google.android.gms.internal.nearby.zzc;

public abstract class zzn extends zzb implements zzm {
    public zzn() {
        super("com.google.android.gms.nearby.messages.internal.IMessageListener");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza((zzaf) zzc.zza(parcel, zzaf.CREATOR));
                break;
            case 2:
                zzb((zzaf) zzc.zza(parcel, zzaf.CREATOR));
                break;
            case 4:
                zza(parcel.createTypedArrayList(Update.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}
