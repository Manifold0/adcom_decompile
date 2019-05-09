// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.nearby.zzc;
import android.os.Parcel;
import com.google.android.gms.internal.nearby.zzb;

public abstract class zzy extends zzb implements zzx
{
    public zzy() {
        super("com.google.android.gms.nearby.messages.internal.IStatusCallback");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 1) {
            this.onPermissionChanged(com.google.android.gms.internal.nearby.zzc.zza(parcel));
            return true;
        }
        return false;
    }
}
