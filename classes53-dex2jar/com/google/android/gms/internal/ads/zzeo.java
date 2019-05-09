// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.IBinder;

public abstract class zzeo extends zzek implements zzen
{
    public static zzen zza(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.clearcut.IClearcut");
        if (queryLocalInterface instanceof zzen) {
            return (zzen)queryLocalInterface;
        }
        return new zzep(binder);
    }
}
