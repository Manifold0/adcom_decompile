// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.broadcast;

import java.util.Iterator;
import android.os.Bundle;
import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.WebViewApp;
import org.json.JSONException;
import com.unity3d.services.core.log.DeviceLog;
import org.json.JSONObject;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class BroadcastEventReceiver extends BroadcastReceiver
{
    private String _name;
    
    public BroadcastEventReceiver(final String name) {
        this._name = name;
    }
    
    public void onReceive(Context dataString, final Intent intent) {
        final String action = intent.getAction();
        if (action != null) {
            dataString = (Context)"";
            if (intent.getDataString() != null) {
                dataString = (Context)intent.getDataString();
            }
            final JSONObject jsonObject = new JSONObject();
            try {
                if (intent.getExtras() != null) {
                    final Bundle extras = intent.getExtras();
                    for (final String s : extras.keySet()) {
                        jsonObject.put(s, extras.get(s));
                    }
                }
            }
            catch (JSONException ex) {
                DeviceLog.debug("JSONException when composing extras for broadcast action " + action + ": " + ex.getMessage());
            }
            final WebViewApp currentApp = WebViewApp.getCurrentApp();
            if (currentApp != null && currentApp.isWebAppLoaded()) {
                currentApp.sendEvent(WebViewEventCategory.BROADCAST, BroadcastEvent.ACTION, this._name, action, dataString, jsonObject);
            }
        }
    }
}
