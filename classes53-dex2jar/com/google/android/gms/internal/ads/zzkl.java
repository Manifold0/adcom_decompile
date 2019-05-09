// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcelable$Creator;
import android.os.Parcel;

public abstract class zzkl extends zzek implements zzkk
{
    public zzkl() {
        super("com.google.android.gms.ads.internal.client.IAdLoader");
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.zzd((zzjj)zzel.zza(parcel, (Parcelable$Creator)zzjj.CREATOR));
                parcel2.writeNoException();
                break;
            }
            case 2: {
                final String mediationAdapterClassName = this.getMediationAdapterClassName();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            }
            case 3: {
                final boolean loading = this.isLoading();
                parcel2.writeNoException();
                zzel.zza(parcel2, loading);
                break;
            }
            case 4: {
                final String zzck = this.zzck();
                parcel2.writeNoException();
                parcel2.writeString(zzck);
                break;
            }
            case 5: {
                this.zza((zzjj)zzel.zza(parcel, (Parcelable$Creator)zzjj.CREATOR), parcel.readInt());
                parcel2.writeNoException();
                break;
            }
        }
        return true;
    }
}
