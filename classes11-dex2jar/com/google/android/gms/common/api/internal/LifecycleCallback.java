// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.MainThread;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.content.ContextWrapper;
import android.app.Activity;
import android.support.annotation.Keep;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class LifecycleCallback
{
    @KeepForSdk
    protected final LifecycleFragment mLifecycleFragment;
    
    @KeepForSdk
    protected LifecycleCallback(final LifecycleFragment mLifecycleFragment) {
        this.mLifecycleFragment = mLifecycleFragment;
    }
    
    @Keep
    private static LifecycleFragment getChimeraLifecycleFragmentImpl(final LifecycleActivity lifecycleActivity) {
        throw new IllegalStateException("Method not available in SDK.");
    }
    
    @KeepForSdk
    public static LifecycleFragment getFragment(final Activity activity) {
        return getFragment(new LifecycleActivity(activity));
    }
    
    @KeepForSdk
    public static LifecycleFragment getFragment(final ContextWrapper contextWrapper) {
        throw new UnsupportedOperationException();
    }
    
    @KeepForSdk
    protected static LifecycleFragment getFragment(final LifecycleActivity lifecycleActivity) {
        if (lifecycleActivity.isSupport()) {
            return zzc.zza(lifecycleActivity.asFragmentActivity());
        }
        if (lifecycleActivity.zzh()) {
            return zza.zza(lifecycleActivity.asActivity());
        }
        throw new IllegalArgumentException("Can't get fragment for unexpected activity.");
    }
    
    @MainThread
    @KeepForSdk
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
    }
    
    @KeepForSdk
    public Activity getActivity() {
        return this.mLifecycleFragment.getLifecycleActivity();
    }
    
    @MainThread
    @KeepForSdk
    public void onActivityResult(final int n, final int n2, final Intent intent) {
    }
    
    @MainThread
    @KeepForSdk
    public void onCreate(final Bundle bundle) {
    }
    
    @MainThread
    @KeepForSdk
    public void onDestroy() {
    }
    
    @MainThread
    @KeepForSdk
    public void onResume() {
    }
    
    @MainThread
    @KeepForSdk
    public void onSaveInstanceState(final Bundle bundle) {
    }
    
    @MainThread
    @KeepForSdk
    public void onStart() {
    }
    
    @MainThread
    @KeepForSdk
    public void onStop() {
    }
}
