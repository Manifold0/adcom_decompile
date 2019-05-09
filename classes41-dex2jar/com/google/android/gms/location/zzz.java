// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.location.zzc;
import android.location.Location;
import android.os.IBinder;
import com.google.android.gms.internal.location.zza;

public final class zzz extends zza implements zzx
{
    zzz(final IBinder binder) {
        super(binder, "com.google.android.gms.location.ILocationListener");
    }
    
    @Override
    public final void onLocationChanged(final Location location) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)location);
        this.transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
