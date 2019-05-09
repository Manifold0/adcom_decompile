// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamic;

import android.os.Bundle;
import android.app.Activity;

final class zab implements zaa
{
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ DeferredLifecycleHelper zarj;
    private final /* synthetic */ Bundle zark;
    private final /* synthetic */ Bundle zarl;
    
    zab(final DeferredLifecycleHelper zarj, final Activity val$activity, final Bundle zark, final Bundle zarl) {
        this.zarj = zarj;
        this.val$activity = val$activity;
        this.zark = zark;
        this.zarl = zarl;
    }
    
    @Override
    public final int getState() {
        return 0;
    }
    
    @Override
    public final void zaa(final LifecycleDelegate lifecycleDelegate) {
        this.zarj.zarf.onInflate(this.val$activity, this.zark, this.zarl);
    }
}
