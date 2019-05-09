// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import android.os.Parcel;
import com.google.android.gms.internal.nearby.zzb;

public abstract class zzv extends zzb implements zzu
{
    public zzv() {
        super("com.google.android.gms.nearby.messages.internal.IPublishCallback");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 1) {
            this.onExpired();
            return true;
        }
        return false;
    }
}
