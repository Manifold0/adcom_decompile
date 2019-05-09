package com.google.android.gms.gcm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;

public final class zze extends zzed implements zzd {
    zze(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.gcm.INetworkTaskCallback");
    }

    public final void zzdr(int i) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeInt(i);
        zzb(2, zzaz);
    }
}
