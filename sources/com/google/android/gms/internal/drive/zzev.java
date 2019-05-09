package com.google.android.gms.internal.drive;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzev extends zzb implements zzeu {
    public static zzeu zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.drive.internal.IEventReleaseCallback");
        return queryLocalInterface instanceof zzeu ? (zzeu) queryLocalInterface : new zzew(iBinder);
    }
}
