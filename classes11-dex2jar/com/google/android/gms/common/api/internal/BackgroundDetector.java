// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager$RunningAppProcessInfo;
import com.google.android.gms.common.util.PlatformVersion;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentCallbacks;
import android.app.Application;
import javax.annotation.concurrent.GuardedBy;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import com.google.android.gms.common.annotation.KeepForSdk;
import android.content.ComponentCallbacks2;
import android.app.Application$ActivityLifecycleCallbacks;

@KeepForSdk
public final class BackgroundDetector implements Application$ActivityLifecycleCallbacks, ComponentCallbacks2
{
    private static final BackgroundDetector zzat;
    private final AtomicBoolean zzau;
    private final AtomicBoolean zzav;
    @GuardedBy("sInstance")
    private final ArrayList<BackgroundStateChangeListener> zzaw;
    @GuardedBy("sInstance")
    private boolean zzax;
    
    static {
        zzat = new BackgroundDetector();
    }
    
    @KeepForSdk
    private BackgroundDetector() {
        this.zzau = new AtomicBoolean();
        this.zzav = new AtomicBoolean();
        this.zzaw = new ArrayList<BackgroundStateChangeListener>();
        this.zzax = false;
    }
    
    @KeepForSdk
    public static BackgroundDetector getInstance() {
        return BackgroundDetector.zzat;
    }
    
    @KeepForSdk
    public static void initialize(final Application application) {
        synchronized (BackgroundDetector.zzat) {
            if (!BackgroundDetector.zzat.zzax) {
                application.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)BackgroundDetector.zzat);
                application.registerComponentCallbacks((ComponentCallbacks)BackgroundDetector.zzat);
                BackgroundDetector.zzat.zzax = true;
            }
        }
    }
    
    private final void onBackgroundStateChanged(final boolean b) {
        synchronized (BackgroundDetector.zzat) {
            final ArrayList<BackgroundStateChangeListener> list = this.zzaw;
            final int size = list.size();
            int i = 0;
            while (i < size) {
                final BackgroundStateChangeListener value = list.get(i);
                ++i;
                value.onBackgroundStateChanged(b);
            }
        }
    }
    // monitorexit(backgroundDetector)
    
    @KeepForSdk
    public final void addListener(final BackgroundStateChangeListener backgroundStateChangeListener) {
        synchronized (BackgroundDetector.zzat) {
            this.zzaw.add(backgroundStateChangeListener);
        }
    }
    
    @KeepForSdk
    public final boolean isInBackground() {
        return this.zzau.get();
    }
    
    public final void onActivityCreated(final Activity activity, final Bundle bundle) {
        final boolean compareAndSet = this.zzau.compareAndSet(true, false);
        this.zzav.set(true);
        if (compareAndSet) {
            this.onBackgroundStateChanged(false);
        }
    }
    
    public final void onActivityDestroyed(final Activity activity) {
    }
    
    public final void onActivityPaused(final Activity activity) {
    }
    
    public final void onActivityResumed(final Activity activity) {
        final boolean compareAndSet = this.zzau.compareAndSet(true, false);
        this.zzav.set(true);
        if (compareAndSet) {
            this.onBackgroundStateChanged(false);
        }
    }
    
    public final void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
    }
    
    public final void onActivityStarted(final Activity activity) {
    }
    
    public final void onActivityStopped(final Activity activity) {
    }
    
    public final void onConfigurationChanged(final Configuration configuration) {
    }
    
    public final void onLowMemory() {
    }
    
    public final void onTrimMemory(final int n) {
        if (n == 20 && this.zzau.compareAndSet(false, true)) {
            this.zzav.set(true);
            this.onBackgroundStateChanged(true);
        }
    }
    
    @TargetApi(16)
    @KeepForSdk
    public final boolean readCurrentStateIfPossible(boolean inBackground) {
        if (!this.zzav.get()) {
            if (!PlatformVersion.isAtLeastJellyBean()) {
                return inBackground;
            }
            final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo = new ActivityManager$RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(activityManager$RunningAppProcessInfo);
            if (!this.zzav.getAndSet(true) && activityManager$RunningAppProcessInfo.importance > 100) {
                this.zzau.set(true);
            }
        }
        inBackground = this.isInBackground();
        return inBackground;
    }
    
    @KeepForSdk
    public interface BackgroundStateChangeListener
    {
        @KeepForSdk
        void onBackgroundStateChanged(final boolean p0);
    }
}
