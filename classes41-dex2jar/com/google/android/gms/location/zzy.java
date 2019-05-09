// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import android.os.RemoteException;
import android.os.Parcelable$Creator;
import com.google.android.gms.internal.location.zzc;
import android.location.Location;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.internal.location.zzb;

public abstract class zzy extends zzb implements zzx
{
    public zzy() {
        super("com.google.android.gms.location.ILocationListener");
    }
    
    public static zzx zzc(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.location.ILocationListener");
        if (queryLocalInterface instanceof zzx) {
            return (zzx)queryLocalInterface;
        }
        return new zzz(binder);
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 1) {
            this.onLocationChanged(com.google.android.gms.internal.location.zzc.zza(parcel, (android.os.Parcelable$Creator<Location>)Location.CREATOR));
            return true;
        }
        return false;
    }
}
