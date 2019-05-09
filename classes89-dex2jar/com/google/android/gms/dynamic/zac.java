// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamic;

import android.os.Bundle;

final class zac implements zaa
{
    private final /* synthetic */ DeferredLifecycleHelper zarj;
    private final /* synthetic */ Bundle zarl;
    
    zac(final DeferredLifecycleHelper zarj, final Bundle zarl) {
        this.zarj = zarj;
        this.zarl = zarl;
    }
    
    @Override
    public final int getState() {
        return 1;
    }
    
    @Override
    public final void zaa(final LifecycleDelegate lifecycleDelegate) {
        this.zarj.zarf.onCreate(this.zarl);
    }
}
