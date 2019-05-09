// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.support.annotation.MainThread;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import android.support.annotation.VisibleForTesting;
import android.app.Activity;
import java.lang.ref.WeakReference;

public final class zaa extends ActivityLifecycleObserver
{
    private final WeakReference<zaa> zacl;
    
    public zaa(final Activity activity) {
        this(zaa(activity));
    }
    
    @VisibleForTesting(otherwise = 2)
    private zaa(final zaa zaa) {
        this.zacl = new WeakReference<zaa>(zaa);
    }
    
    @Override
    public final ActivityLifecycleObserver onStopCallOnce(final Runnable runnable) {
        final zaa zaa = this.zacl.get();
        if (zaa == null) {
            throw new IllegalStateException("The target activity has already been GC'd");
        }
        zaa.zaa(runnable);
        return this;
    }
    
    @VisibleForTesting(otherwise = 2)
    static class zaa extends LifecycleCallback
    {
        private List<Runnable> zacm;
        
        private zaa(final LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.zacm = new ArrayList<Runnable>();
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", (LifecycleCallback)this);
        }
        
        private static zaa zaa(final Activity activity) {
            synchronized (activity) {
                final LifecycleFragment fragment = getFragment(activity);
                zaa zaa;
                if ((zaa = (zaa)fragment.getCallbackOrNull("LifecycleObserverOnStop", (Class)zaa.class)) == null) {
                    zaa = new zaa(fragment);
                }
                return zaa;
            }
        }
        
        private final void zaa(final Runnable runnable) {
            synchronized (this) {
                this.zacm.add(runnable);
            }
        }
        
        @MainThread
        public void onStop() {
            synchronized (this) {
                final List<Runnable> zacm = this.zacm;
                this.zacm = new ArrayList<Runnable>();
                // monitorexit(this)
                final Iterator<Runnable> iterator = zacm.iterator();
                while (iterator.hasNext()) {
                    iterator.next().run();
                }
            }
        }
    }
}
