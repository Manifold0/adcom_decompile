// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.internal.location.zzc;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.internal.location.zzb;

public abstract class zzv extends zzb implements zzu
{
    public zzv() {
        super("com.google.android.gms.location.ILocationCallback");
    }
    
    public static zzu zzb(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
        if (queryLocalInterface instanceof zzu) {
            return (zzu)queryLocalInterface;
        }
        return new zzw(binder);
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.onLocationResult(com.google.android.gms.internal.location.zzc.zza(parcel, LocationResult.CREATOR));
                break;
            }
            case 2: {
                this.onLocationAvailability(com.google.android.gms.internal.location.zzc.zza(parcel, LocationAvailability.CREATOR));
                break;
            }
        }
        return true;
    }
}
