// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.app.Application$ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.app.Activity;

final class zzga implements zzgc
{
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ Bundle zzrn;
    
    zzga(final zzfu zzfu, final Activity val$activity, final Bundle zzrn) {
        this.val$activity = val$activity;
        this.zzrn = zzrn;
    }
    
    @Override
    public final void zza(final Application$ActivityLifecycleCallbacks application$ActivityLifecycleCallbacks) {
        application$ActivityLifecycleCallbacks.onActivitySaveInstanceState(this.val$activity, this.zzrn);
    }
}
