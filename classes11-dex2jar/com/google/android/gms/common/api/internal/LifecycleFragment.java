// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.content.Intent;
import android.app.Activity;
import android.support.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface LifecycleFragment
{
    @KeepForSdk
    void addCallback(final String p0, @NonNull final LifecycleCallback p1);
    
    @KeepForSdk
     <T extends LifecycleCallback> T getCallbackOrNull(final String p0, final Class<T> p1);
    
    @KeepForSdk
    Activity getLifecycleActivity();
    
    @KeepForSdk
    boolean isCreated();
    
    @KeepForSdk
    boolean isStarted();
    
    @KeepForSdk
    void startActivityForResult(final Intent p0, final int p1);
}
