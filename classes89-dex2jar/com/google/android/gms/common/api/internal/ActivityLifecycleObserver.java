// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.app.Activity;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class ActivityLifecycleObserver
{
    @KeepForSdk
    public static final ActivityLifecycleObserver of(final Activity activity) {
        return new zaa(activity);
    }
    
    @KeepForSdk
    public abstract ActivityLifecycleObserver onStopCallOnce(final Runnable p0);
}
