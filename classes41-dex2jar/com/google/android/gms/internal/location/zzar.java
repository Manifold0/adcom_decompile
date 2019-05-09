// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.location.LocationSettingsResult;
import android.os.Parcel;

public abstract class zzar extends zzb implements zzaq
{
    public zzar() {
        super("com.google.android.gms.location.internal.ISettingsCallbacks");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 1) {
            this.zza(com.google.android.gms.internal.location.zzc.zza(parcel, LocationSettingsResult.CREATOR));
            return true;
        }
        return false;
    }
}
