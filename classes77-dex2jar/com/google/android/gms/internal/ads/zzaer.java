// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcelable$Creator;
import android.os.Parcel;

public abstract class zzaer extends zzek implements zzaeq
{
    public zzaer() {
        super("com.google.android.gms.ads.internal.request.IAdResponseListener");
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 1) {
            this.zza((zzaej)zzel.zza(parcel, (Parcelable$Creator)zzaej.CREATOR));
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}
