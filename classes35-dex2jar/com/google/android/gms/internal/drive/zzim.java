// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.IInterface;
import android.os.IBinder;

public final class zzim extends zzb implements zzil
{
    public static zzil zzb(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
        if (queryLocalInterface instanceof zzil) {
            return (zzil)queryLocalInterface;
        }
        return new zzin(binder);
    }
}
