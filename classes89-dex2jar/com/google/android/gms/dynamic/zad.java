// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamic;

import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.os.Bundle;
import android.view.ViewGroup;

final class zad implements zaa
{
    private final /* synthetic */ ViewGroup val$container;
    private final /* synthetic */ DeferredLifecycleHelper zarj;
    private final /* synthetic */ Bundle zarl;
    private final /* synthetic */ FrameLayout zarm;
    private final /* synthetic */ LayoutInflater zarn;
    
    zad(final DeferredLifecycleHelper zarj, final FrameLayout zarm, final LayoutInflater zarn, final ViewGroup val$container, final Bundle zarl) {
        this.zarj = zarj;
        this.zarm = zarm;
        this.zarn = zarn;
        this.val$container = val$container;
        this.zarl = zarl;
    }
    
    @Override
    public final int getState() {
        return 2;
    }
    
    @Override
    public final void zaa(final LifecycleDelegate lifecycleDelegate) {
        this.zarm.removeAllViews();
        this.zarm.addView(this.zarj.zarf.onCreateView(this.zarn, this.val$container, this.zarl));
    }
}
