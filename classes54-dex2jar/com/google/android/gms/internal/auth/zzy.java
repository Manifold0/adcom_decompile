// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import android.os.RemoteException;
import com.google.android.gms.auth.api.accounttransfer.DeviceMetaData;
import com.google.android.gms.auth.api.accounttransfer.zzl;
import com.google.android.gms.auth.api.accounttransfer.zzt;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.api.Status;
import android.os.Parcel;

public abstract class zzy extends zzb implements zzx
{
    public zzy() {
        super("com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferCallbacks");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.zzb(com.google.android.gms.internal.auth.zzc.zza(parcel, (android.os.Parcelable$Creator<Status>)Status.CREATOR));
                break;
            }
            case 2: {
                this.zza(com.google.android.gms.internal.auth.zzc.zza(parcel, (android.os.Parcelable$Creator<Status>)Status.CREATOR), com.google.android.gms.internal.auth.zzc.zza(parcel, zzt.CREATOR));
                break;
            }
            case 3: {
                this.zza(com.google.android.gms.internal.auth.zzc.zza(parcel, (android.os.Parcelable$Creator<Status>)Status.CREATOR), com.google.android.gms.internal.auth.zzc.zza(parcel, zzl.CREATOR));
                break;
            }
            case 4: {
                this.zzd();
                break;
            }
            case 5: {
                this.onFailure(com.google.android.gms.internal.auth.zzc.zza(parcel, (android.os.Parcelable$Creator<Status>)Status.CREATOR));
                break;
            }
            case 6: {
                this.zza(parcel.createByteArray());
                break;
            }
            case 7: {
                this.zza(com.google.android.gms.internal.auth.zzc.zza(parcel, DeviceMetaData.CREATOR));
                break;
            }
        }
        return true;
    }
}
