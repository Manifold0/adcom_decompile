// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.IBinder;

public abstract class zzato extends zzek implements zzatn
{
    public static zzatn zzab(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.omid.IOmid");
        if (queryLocalInterface instanceof zzatn) {
            return (zzatn)queryLocalInterface;
        }
        return new zzatp(binder);
    }
}
