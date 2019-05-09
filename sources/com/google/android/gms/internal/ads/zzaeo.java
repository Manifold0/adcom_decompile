package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public abstract class zzaeo extends zzek implements zzaen {
    public zzaeo() {
        super("com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzaet zzaet = null;
        IBinder readStrongBinder;
        IInterface queryLocalInterface;
        zzaey zzaey;
        switch (i) {
            case 1:
                Parcelable zzb = zzb((zzaef) zzel.zza(parcel, zzaef.CREATOR));
                parcel2.writeNoException();
                zzel.zzb(parcel2, zzb);
                break;
            case 2:
                zzaeq zzaes;
                zzaef zzaef = (zzaef) zzel.zza(parcel, zzaef.CREATOR);
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdResponseListener");
                    zzaes = queryLocalInterface instanceof zzaeq ? (zzaeq) queryLocalInterface : new zzaes(readStrongBinder);
                }
                zza(zzaef, zzaes);
                parcel2.writeNoException();
                break;
            case 4:
                zzaey = (zzaey) zzel.zza(parcel, zzaey.CREATOR);
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                    zzaet = queryLocalInterface instanceof zzaet ? (zzaet) queryLocalInterface : new zzaeu(readStrongBinder);
                }
                zza(zzaey, zzaet);
                parcel2.writeNoException();
                break;
            case 5:
                zzaey = (zzaey) zzel.zza(parcel, zzaey.CREATOR);
                readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                    zzaet = queryLocalInterface instanceof zzaet ? (zzaet) queryLocalInterface : new zzaeu(readStrongBinder);
                }
                zzb(zzaey, zzaet);
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
