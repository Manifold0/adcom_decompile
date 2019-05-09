// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import android.os.Parcelable$Creator;
import com.google.android.gms.internal.nearby.zzc;
import com.google.android.gms.common.api.Status;
import android.os.Parcel;
import com.google.android.gms.internal.nearby.zzb;

public abstract class zzq extends zzb implements zzp
{
    public zzq() {
        super("com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 1) {
            this.zza(com.google.android.gms.internal.nearby.zzc.zza(parcel, (android.os.Parcelable$Creator<Status>)Status.CREATOR));
            return true;
        }
        return false;
    }
}
