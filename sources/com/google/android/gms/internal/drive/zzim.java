package com.google.android.gms.internal.drive;

import android.os.IBinder;
import android.os.IInterface;

public final class zzim extends zzb implements zzil {
    public static zzil zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
        return queryLocalInterface instanceof zzil ? (zzil) queryLocalInterface : new zzin(iBinder);
    }
}
