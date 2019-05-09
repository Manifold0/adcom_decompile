// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api;

import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.Credential;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.api.Status;
import android.os.Parcel;

public abstract class zzv extends zzd implements zzu
{
    public zzv() {
        super("com.google.android.gms.auth.api.credentials.internal.ICredentialsCallbacks");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.zzc(com.google.android.gms.internal.auth-api.zze.zzc(parcel, (android.os.Parcelable$Creator<Status>)Status.CREATOR), com.google.android.gms.internal.auth-api.zze.zzc(parcel, Credential.CREATOR));
                break;
            }
            case 2: {
                this.zzc(com.google.android.gms.internal.auth-api.zze.zzc(parcel, (android.os.Parcelable$Creator<Status>)Status.CREATOR));
                break;
            }
            case 3: {
                this.zzc(com.google.android.gms.internal.auth-api.zze.zzc(parcel, (android.os.Parcelable$Creator<Status>)Status.CREATOR), parcel.readString());
                break;
            }
        }
        parcel2.writeNoException();
        return true;
    }
}
