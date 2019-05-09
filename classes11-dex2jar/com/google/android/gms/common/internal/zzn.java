// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.internal.common.zzb;

public abstract class zzn extends zzb implements zzm
{
    public static zzm zzc(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.IGoogleCertificatesApi");
        if (queryLocalInterface instanceof zzm) {
            return (zzm)queryLocalInterface;
        }
        return new zzo(binder);
    }
}
