// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api-phone;

import android.os.RemoteException;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.api.Status;
import android.os.Parcel;

public abstract class zzh extends zzb implements zzg
{
    public zzh() {
        super("com.google.android.gms.auth.api.phone.internal.ISmsRetrieverResultCallback");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 1) {
            this.zza(com.google.android.gms.internal.auth-api-phone.zzc.zza(parcel, (android.os.Parcelable$Creator<Status>)Status.CREATOR));
            return true;
        }
        return false;
    }
}
