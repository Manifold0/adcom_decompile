// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.IBinder;

public final class zzaax extends zzek implements zzaaw
{
    public static zzaaw zzv(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseListener");
        if (queryLocalInterface instanceof zzaaw) {
            return (zzaaw)queryLocalInterface;
        }
        return new zzaay(binder);
    }
}
