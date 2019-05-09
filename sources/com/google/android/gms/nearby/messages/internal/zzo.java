package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.nearby.zza;
import com.google.android.gms.internal.nearby.zzc;
import java.util.List;

public final class zzo extends zza implements zzm {
    zzo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.nearby.messages.internal.IMessageListener");
    }

    public final void zza(zzaf zzaf) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzaf);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }

    public final void zza(List<Update> list) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeTypedList(list);
        transactOneway(4, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzaf zzaf) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzaf);
        transactOneway(2, obtainAndWriteInterfaceToken);
    }
}
