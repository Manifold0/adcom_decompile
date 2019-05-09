// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import android.os.Parcel;
import com.google.android.gms.internal.nearby.zzb;

public abstract class zzab extends zzb implements zzaa
{
    public zzab() {
        super("com.google.android.gms.nearby.messages.internal.ISubscribeCallback");
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
