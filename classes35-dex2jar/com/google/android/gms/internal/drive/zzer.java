// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.zza;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.api.Status;
import android.os.Parcel;

public abstract class zzer extends zzb implements zzeq
{
    public zzer() {
        super("com.google.android.gms.drive.internal.IDriveServiceCallbacks");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.zza(com.google.android.gms.internal.drive.zzc.zza(parcel, zzff.CREATOR));
                break;
            }
            case 2: {
                this.zza(com.google.android.gms.internal.drive.zzc.zza(parcel, zzfn.CREATOR));
                break;
            }
            case 3: {
                this.zza(com.google.android.gms.internal.drive.zzc.zza(parcel, zzfh.CREATOR));
                break;
            }
            case 4: {
                this.zza(com.google.android.gms.internal.drive.zzc.zza(parcel, zzfs.CREATOR));
                break;
            }
            case 5: {
                this.zza(com.google.android.gms.internal.drive.zzc.zza(parcel, zzfb.CREATOR));
                break;
            }
            case 6: {
                this.zza(com.google.android.gms.internal.drive.zzc.zza(parcel, (android.os.Parcelable$Creator<Status>)Status.CREATOR));
                break;
            }
            case 7: {
                this.onSuccess();
                break;
            }
            case 8: {
                this.zza(com.google.android.gms.internal.drive.zzc.zza(parcel, zzfp.CREATOR));
                break;
            }
            case 9: {
                this.zza(com.google.android.gms.internal.drive.zzc.zza(parcel, zzgb.CREATOR));
                break;
            }
            case 11: {
                this.zza(com.google.android.gms.internal.drive.zzc.zza(parcel, zzfr.CREATOR), zzim.zzb(parcel.readStrongBinder()));
                break;
            }
            case 12: {
                this.zza(com.google.android.gms.internal.drive.zzc.zza(parcel, zzfx.CREATOR));
                break;
            }
            case 13: {
                this.zza(com.google.android.gms.internal.drive.zzc.zza(parcel, zzfu.CREATOR));
                break;
            }
            case 14: {
                this.zza(com.google.android.gms.internal.drive.zzc.zza(parcel, zzfd.CREATOR));
                break;
            }
            case 15: {
                this.zzb(com.google.android.gms.internal.drive.zzc.zza(parcel));
                break;
            }
            case 16: {
                this.zza(com.google.android.gms.internal.drive.zzc.zza(parcel, zzfl.CREATOR));
                break;
            }
            case 17: {
                this.zza(com.google.android.gms.internal.drive.zzc.zza(parcel, zza.CREATOR));
                break;
            }
            case 18: {
                this.zza(com.google.android.gms.internal.drive.zzc.zza(parcel, zzez.CREATOR));
                break;
            }
            case 20: {
                this.zza(com.google.android.gms.internal.drive.zzc.zza(parcel, zzem.CREATOR));
                break;
            }
            case 21: {
                this.zza(com.google.android.gms.internal.drive.zzc.zza(parcel, zzgt.CREATOR));
                break;
            }
            case 22: {
                this.zza(com.google.android.gms.internal.drive.zzc.zza(parcel, zzfz.CREATOR));
                break;
            }
        }
        parcel2.writeNoException();
        return true;
    }
}
