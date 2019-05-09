// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamic;

import java.util.Iterator;
import android.os.Bundle;

final class zaa implements OnDelegateCreatedListener<Object>
{
    private final /* synthetic */ DeferredLifecycleHelper zarj;
    
    zaa(final DeferredLifecycleHelper zarj) {
        this.zarj = zarj;
    }
    
    public final void onDelegateCreated(final LifecycleDelegate lifecycleDelegate) {
        this.zarj.zarf = (T)lifecycleDelegate;
        final Iterator iterator = this.zarj.zarh.iterator();
        while (iterator.hasNext()) {
            iterator.next().zaa(this.zarj.zarf);
        }
        this.zarj.zarh.clear();
        DeferredLifecycleHelper.zaa(this.zarj, (Bundle)null);
    }
}
