// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import android.os.Parcel;

public abstract class zzet extends zzb implements zzes
{
    public zzet() {
        super("com.google.android.gms.drive.internal.IEventCallback");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 1) {
            this.zzc(com.google.android.gms.internal.drive.zzc.zza(parcel, zzfj.CREATOR));
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}
