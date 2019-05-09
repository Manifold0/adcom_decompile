// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;

public final class zzew extends zza implements zzeu
{
    zzew(final IBinder binder) {
        super(binder, "com.google.android.gms.drive.internal.IEventReleaseCallback");
    }
    
    @Override
    public final void zza(final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, b);
        this.transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
