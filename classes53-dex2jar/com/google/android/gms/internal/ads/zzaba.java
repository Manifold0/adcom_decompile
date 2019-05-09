// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.IBinder;

public final class zzaba extends zzek implements zzaaz
{
    public static zzaaz zzw(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
        if (queryLocalInterface instanceof zzaaz) {
            return (zzaaz)queryLocalInterface;
        }
        return new zzabb(binder);
    }
}
