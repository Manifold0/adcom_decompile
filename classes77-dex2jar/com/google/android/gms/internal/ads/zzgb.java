// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Activity;

final class zzgb implements zzgc
{
    private final /* synthetic */ Activity val$activity;
    
    zzgb(final zzfu zzfu, final Activity val$activity) {
        this.val$activity = val$activity;
    }
    
    @Override
    public final void zza(final Application$ActivityLifecycleCallbacks application$ActivityLifecycleCallbacks) {
        application$ActivityLifecycleCallbacks.onActivityDestroyed(this.val$activity);
    }
}
