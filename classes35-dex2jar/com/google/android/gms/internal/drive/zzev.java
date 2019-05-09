// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.IInterface;
import android.os.IBinder;

public abstract class zzev extends zzb implements zzeu
{
    public static zzeu zza(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.drive.internal.IEventReleaseCallback");
        if (queryLocalInterface instanceof zzeu) {
            return (zzeu)queryLocalInterface;
        }
        return new zzew(binder);
    }
}
