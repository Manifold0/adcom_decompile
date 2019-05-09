// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.IBinder;

public final class zzabd extends zzek implements zzabc
{
    public static zzabc zzx(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IPlayStorePurchaseListener");
        if (queryLocalInterface instanceof zzabc) {
            return (zzabc)queryLocalInterface;
        }
        return new zzabe(binder);
    }
}
