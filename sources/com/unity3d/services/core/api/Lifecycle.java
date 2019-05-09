package com.unity3d.services.core.api;

import android.annotation.TargetApi;
import com.unity3d.services.core.lifecycle.LifecycleError;
import com.unity3d.services.core.lifecycle.LifecycleListener;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

@TargetApi(14)
public class Lifecycle {
    private static LifecycleListener _listener;

    @WebViewExposed
    public static void register(JSONArray events, WebViewCallback callback) {
        if (ClientProperties.getApplication() == null) {
            callback.error(LifecycleError.APPLICATION_NULL, new Object[0]);
        } else if (getLifecycleListener() == null) {
            ArrayList<String> eventList = new ArrayList();
            int i = 0;
            while (i < events.length()) {
                try {
                    eventList.add((String) events.get(i));
                    i++;
                } catch (JSONException e) {
                    callback.error(LifecycleError.JSON_ERROR, new Object[0]);
                    return;
                }
            }
            setLifecycleListener(new LifecycleListener(eventList));
            ClientProperties.getApplication().registerActivityLifecycleCallbacks(getLifecycleListener());
            callback.invoke(new Object[0]);
        } else {
            callback.error(LifecycleError.LISTENER_NOT_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void unregister(WebViewCallback callback) {
        if (ClientProperties.getApplication() != null) {
            if (getLifecycleListener() != null) {
                ClientProperties.getApplication().unregisterActivityLifecycleCallbacks(getLifecycleListener());
                setLifecycleListener(null);
            }
            callback.invoke(new Object[0]);
            return;
        }
        callback.error(LifecycleError.APPLICATION_NULL, new Object[0]);
    }

    public static LifecycleListener getLifecycleListener() {
        return _listener;
    }

    public static void setLifecycleListener(LifecycleListener listener) {
        _listener = listener;
    }
}
