// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.api;

import com.unity3d.services.core.webview.bridge.WebViewExposed;
import android.app.Application$ActivityLifecycleCallbacks;
import org.json.JSONException;
import java.util.ArrayList;
import com.unity3d.services.core.lifecycle.LifecycleError;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import org.json.JSONArray;
import com.unity3d.services.core.lifecycle.LifecycleListener;
import android.annotation.TargetApi;

@TargetApi(14)
public class Lifecycle
{
    private static LifecycleListener _listener;
    
    public static LifecycleListener getLifecycleListener() {
        return Lifecycle._listener;
    }
    
    @WebViewExposed
    public static void register(final JSONArray jsonArray, final WebViewCallback webViewCallback) {
        if (ClientProperties.getApplication() == null) {
            webViewCallback.error(LifecycleError.APPLICATION_NULL, new Object[0]);
            return;
        }
        if (getLifecycleListener() == null) {
            final ArrayList<String> list = new ArrayList<String>();
            int i = 0;
            while (i < jsonArray.length()) {
                try {
                    list.add((String)jsonArray.get(i));
                    ++i;
                    continue;
                }
                catch (JSONException ex) {
                    webViewCallback.error(LifecycleError.JSON_ERROR, new Object[0]);
                    return;
                }
                break;
            }
            setLifecycleListener(new LifecycleListener(list));
            ClientProperties.getApplication().registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)getLifecycleListener());
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(LifecycleError.LISTENER_NOT_NULL, new Object[0]);
    }
    
    public static void setLifecycleListener(final LifecycleListener listener) {
        Lifecycle._listener = listener;
    }
    
    @WebViewExposed
    public static void unregister(final WebViewCallback webViewCallback) {
        if (ClientProperties.getApplication() != null) {
            if (getLifecycleListener() != null) {
                ClientProperties.getApplication().unregisterActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)getLifecycleListener());
                setLifecycleListener(null);
            }
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(LifecycleError.APPLICATION_NULL, new Object[0]);
    }
}
