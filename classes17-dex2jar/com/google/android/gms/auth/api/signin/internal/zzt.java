// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import android.os.Parcelable$Creator;
import com.google.android.gms.internal.auth-api.zze;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import android.os.Parcel;
import com.google.android.gms.internal.auth-api.zzd;

public abstract class zzt extends zzd implements zzs
{
    public zzt() {
        super("com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 101: {
                this.zzc(com.google.android.gms.internal.auth-api.zze.zzc(parcel, (android.os.Parcelable$Creator<GoogleSignInAccount>)GoogleSignInAccount.CREATOR), com.google.android.gms.internal.auth-api.zze.zzc(parcel, (android.os.Parcelable$Creator<Status>)Status.CREATOR));
                break;
            }
            case 102: {
                this.zze(com.google.android.gms.internal.auth-api.zze.zzc(parcel, (android.os.Parcelable$Creator<Status>)Status.CREATOR));
                break;
            }
            case 103: {
                this.zzf(com.google.android.gms.internal.auth-api.zze.zzc(parcel, (android.os.Parcelable$Creator<Status>)Status.CREATOR));
                break;
            }
        }
        parcel2.writeNoException();
        return true;
    }
}
