// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamic;

final class zaf implements zaa
{
    private final /* synthetic */ DeferredLifecycleHelper zarj;
    
    zaf(final DeferredLifecycleHelper zarj) {
        this.zarj = zarj;
    }
    
    @Override
    public final int getState() {
        return 4;
    }
    
    @Override
    public final void zaa(final LifecycleDelegate lifecycleDelegate) {
        this.zarj.zarf.onStart();
    }
}
