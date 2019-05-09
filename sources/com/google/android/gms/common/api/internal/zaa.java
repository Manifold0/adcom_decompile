package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public final class zaa extends ActivityLifecycleObserver {
    private final WeakReference<zaa> zacl;

    @VisibleForTesting(otherwise = 2)
    static class zaa extends LifecycleCallback {
        private List<Runnable> zacm = new ArrayList();

        private static zaa zaa(Activity activity) {
            zaa zaa;
            synchronized (activity) {
                LifecycleFragment fragment = getFragment(activity);
                zaa = (zaa) fragment.getCallbackOrNull("LifecycleObserverOnStop", zaa.class);
                if (zaa == null) {
                    zaa = new zaa(fragment);
                }
            }
            return zaa;
        }

        private zaa(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
        }

        private final synchronized void zaa(Runnable runnable) {
            this.zacm.add(runnable);
        }

        @MainThread
        public void onStop() {
            synchronized (this) {
                List<Runnable> list = this.zacm;
                this.zacm = new ArrayList();
            }
            for (Runnable run : list) {
                run.run();
            }
        }
    }

    public zaa(Activity activity) {
        this(zaa.zaa(activity));
    }

    @VisibleForTesting(otherwise = 2)
    private zaa(zaa zaa) {
        this.zacl = new WeakReference(zaa);
    }

    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        zaa zaa = (zaa) this.zacl.get();
        if (zaa == null) {
            throw new IllegalStateException("The target activity has already been GC'd");
        }
        zaa.zaa(runnable);
        return this;
    }
}
