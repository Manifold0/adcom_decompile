// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.os.Bundle;
import android.app.Activity;
import android.app.Application$ActivityLifecycleCallbacks;

class ActivityLifecycleListener implements Application$ActivityLifecycleCallbacks
{
    public void onActivityCreated(final Activity activity, final Bundle bundle) {
        ActivityLifecycleHandler.onActivityCreated(activity);
    }
    
    public void onActivityDestroyed(final Activity activity) {
        ActivityLifecycleHandler.onActivityDestroyed(activity);
    }
    
    public void onActivityPaused(final Activity activity) {
        ActivityLifecycleHandler.onActivityPaused(activity);
    }
    
    public void onActivityResumed(final Activity activity) {
        ActivityLifecycleHandler.onActivityResumed(activity);
    }
    
    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
    }
    
    public void onActivityStarted(final Activity activity) {
        ActivityLifecycleHandler.onActivityStarted(activity);
    }
    
    public void onActivityStopped(final Activity activity) {
        ActivityLifecycleHandler.onActivityStopped(activity);
    }
}
