// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzxo extends zzek implements zzxn
{
    public zzxo() {
        super("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
    }
    
    public static zzxn zzr(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
        if (queryLocalInterface instanceof zzxn) {
            return (zzxn)queryLocalInterface;
        }
        return new zzxp(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                final zzxq zzbm = this.zzbm(parcel.readString());
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzbm);
                break;
            }
            case 2: {
                final boolean zzbn = this.zzbn(parcel.readString());
                parcel2.writeNoException();
                zzel.zza(parcel2, zzbn);
                break;
            }
            case 3: {
                final zzzj zzbq = this.zzbq(parcel.readString());
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzbq);
                break;
            }
        }
        return true;
    }
}
