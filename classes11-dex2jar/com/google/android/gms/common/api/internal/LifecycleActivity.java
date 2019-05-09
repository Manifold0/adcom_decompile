// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.support.v4.app.FragmentActivity;
import android.content.ContextWrapper;
import com.google.android.gms.common.internal.Preconditions;
import android.app.Activity;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class LifecycleActivity
{
    private final Object zzbd;
    
    public LifecycleActivity(final Activity zzbd) {
        Preconditions.checkNotNull(zzbd, "Activity must not be null");
        this.zzbd = zzbd;
    }
    
    @KeepForSdk
    public LifecycleActivity(final ContextWrapper contextWrapper) {
        throw new UnsupportedOperationException();
    }
    
    @KeepForSdk
    public Activity asActivity() {
        return (Activity)this.zzbd;
    }
    
    @KeepForSdk
    public FragmentActivity asFragmentActivity() {
        return (FragmentActivity)this.zzbd;
    }
    
    @KeepForSdk
    public Object asObject() {
        return this.zzbd;
    }
    
    @KeepForSdk
    public boolean isChimera() {
        return false;
    }
    
    @KeepForSdk
    public boolean isSupport() {
        return this.zzbd instanceof FragmentActivity;
    }
    
    public final boolean zzh() {
        return this.zzbd instanceof Activity;
    }
}
