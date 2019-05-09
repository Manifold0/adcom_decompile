// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.webview.bridge;

import android.webkit.JavascriptInterface;
import com.unity3d.services.core.log.DeviceLog;
import org.json.JSONException;
import org.json.JSONArray;

public class WebViewBridgeInterface
{
    private Object[] getParameters(final JSONArray jsonArray) throws JSONException {
        final Object[] array = new Object[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); ++i) {
            array[i] = jsonArray.get(i);
        }
        return array;
    }
    
    @JavascriptInterface
    public void handleCallback(final String s, final String s2, final String s3) throws Exception {
        DeviceLog.debug("handleCallback " + s + " " + s2 + " " + s3);
        final JSONArray jsonArray = new JSONArray(s3);
        Object[] array = null;
        if (jsonArray.length() > 0) {
            final Object[] array2 = new Object[jsonArray.length()];
            int n = 0;
            while (true) {
                array = array2;
                if (n >= jsonArray.length()) {
                    break;
                }
                array2[n] = jsonArray.get(n);
                ++n;
            }
        }
        WebViewBridge.handleCallback(s, s2, array);
    }
    
    @JavascriptInterface
    public void handleInvocation(final String s) throws JSONException {
        DeviceLog.debug("handleInvocation " + s);
        final JSONArray jsonArray = new JSONArray(s);
        final Invocation invocation = new Invocation();
        for (int i = 0; i < jsonArray.length(); ++i) {
            final JSONArray jsonArray2 = (JSONArray)jsonArray.get(i);
            invocation.addInvocation((String)jsonArray2.get(0), (String)jsonArray2.get(1), this.getParameters((JSONArray)jsonArray2.get(2)), new WebViewCallback((String)jsonArray2.get(3), invocation.getId()));
        }
        for (int j = 0; j < jsonArray.length(); ++j) {
            invocation.nextInvocation();
        }
        invocation.sendInvocationCallback();
    }
}
