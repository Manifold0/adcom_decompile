// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal;

import android.os.RemoteException;
import android.os.Parcelable;
import com.google.android.gms.internal.games.zzc;
import android.os.Parcel;
import com.google.android.gms.internal.games.zzb;

public abstract class zzx extends zzb implements zzw
{
    public zzx() {
        super("com.google.android.gms.games.internal.IGamesClient");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 1001) {
            final zzaa zzn = this.zzn();
            parcel2.writeNoException();
            com.google.android.gms.internal.games.zzc.zzb(parcel2, (Parcelable)zzn);
            return true;
        }
        return false;
    }
}
