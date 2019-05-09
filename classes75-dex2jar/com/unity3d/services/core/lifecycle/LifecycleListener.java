// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.lifecycle;

import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.WebViewApp;
import android.os.Bundle;
import android.app.Activity;
import java.util.ArrayList;
import android.annotation.TargetApi;
import android.app.Application$ActivityLifecycleCallbacks;

@TargetApi(14)
public class LifecycleListener implements Application$ActivityLifecycleCallbacks
{
    private ArrayList<String> _events;
    
    public LifecycleListener(final ArrayList<String> events) {
        this._events = events;
    }
    
    public void onActivityCreated(final Activity activity, final Bundle bundle) {
        if (this._events.contains("onActivityCreated") && WebViewApp.getCurrentApp() != null) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.LIFECYCLE, LifecycleEvent.CREATED, activity.getClass().getName());
        }
    }
    
    public void onActivityDestroyed(final Activity activity) {
        if (this._events.contains("onActivityDestroyed") && WebViewApp.getCurrentApp() != null) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.LIFECYCLE, LifecycleEvent.DESTROYED, activity.getClass().getName());
        }
    }
    
    public void onActivityPaused(final Activity activity) {
        if (this._events.contains("onActivityPaused") && WebViewApp.getCurrentApp() != null) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.LIFECYCLE, LifecycleEvent.PAUSED, activity.getClass().getName());
        }
    }
    
    public void onActivityResumed(final Activity activity) {
        if (this._events.contains("onActivityResumed") && WebViewApp.getCurrentApp() != null) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.LIFECYCLE, LifecycleEvent.RESUMED, activity.getClass().getName());
        }
    }
    
    public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        if (this._events.contains("onActivitySaveInstanceState") && WebViewApp.getCurrentApp() != null) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.LIFECYCLE, LifecycleEvent.SAVE_INSTANCE_STATE, activity.getClass().getName());
        }
    }
    
    public void onActivityStarted(final Activity activity) {
        if (this._events.contains("onActivityStarted") && WebViewApp.getCurrentApp() != null) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.LIFECYCLE, LifecycleEvent.STARTED, activity.getClass().getName());
        }
    }
    
    public void onActivityStopped(final Activity activity) {
        if (this._events.contains("onActivityStopped") && WebViewApp.getCurrentApp() != null) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.LIFECYCLE, LifecycleEvent.STOPPED, activity.getClass().getName());
        }
    }
}
