// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamic;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface LifecycleDelegate
{
    @KeepForSdk
    void onCreate(final Bundle p0);
    
    @KeepForSdk
    View onCreateView(final LayoutInflater p0, final ViewGroup p1, final Bundle p2);
    
    @KeepForSdk
    void onDestroy();
    
    @KeepForSdk
    void onDestroyView();
    
    @KeepForSdk
    void onInflate(final Activity p0, final Bundle p1, final Bundle p2);
    
    @KeepForSdk
    void onLowMemory();
    
    @KeepForSdk
    void onPause();
    
    @KeepForSdk
    void onResume();
    
    @KeepForSdk
    void onSaveInstanceState(final Bundle p0);
    
    @KeepForSdk
    void onStart();
    
    @KeepForSdk
    void onStop();
}
