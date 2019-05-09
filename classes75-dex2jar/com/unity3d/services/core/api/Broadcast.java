// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.api;

import com.unity3d.services.core.webview.bridge.WebViewExposed;
import org.json.JSONException;
import com.unity3d.services.core.broadcast.BroadcastError;
import com.unity3d.services.core.broadcast.BroadcastMonitor;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import org.json.JSONArray;

public class Broadcast
{
    @WebViewExposed
    public static void addBroadcastListener(final String s, final String s2, final JSONArray jsonArray, final WebViewCallback webViewCallback) {
        try {
            if (jsonArray.length() > 0) {
                final String[] array = new String[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); ++i) {
                    array[i] = jsonArray.getString(i);
                }
                BroadcastMonitor.addBroadcastListener(s, s2, array);
            }
            webViewCallback.invoke(new Object[0]);
        }
        catch (JSONException ex) {
            webViewCallback.error(BroadcastError.JSON_ERROR, new Object[0]);
        }
    }
    
    @WebViewExposed
    public static void addBroadcastListener(final String s, final JSONArray jsonArray, final WebViewCallback webViewCallback) {
        addBroadcastListener(s, null, jsonArray, webViewCallback);
    }
    
    @WebViewExposed
    public static void removeAllBroadcastListeners(final WebViewCallback webViewCallback) {
        BroadcastMonitor.removeAllBroadcastListeners();
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void removeBroadcastListener(final String s, final WebViewCallback webViewCallback) {
        BroadcastMonitor.removeBroadcastListener(s);
        webViewCallback.invoke(new Object[0]);
    }
}
