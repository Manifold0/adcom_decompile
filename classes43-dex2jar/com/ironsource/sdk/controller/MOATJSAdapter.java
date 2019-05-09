// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.controller;

import android.webkit.WebView;
import org.json.JSONException;
import org.json.JSONObject;
import com.ironsource.sdk.analytics.moat.MOATManager;
import android.app.Application;

public class MOATJSAdapter
{
    private static final String createAdTracker = "createAdTracker";
    private static final String fail = "fail";
    private static final String initWithOptions = "initWithOptions";
    private static final String moatFunction = "moatFunction";
    private static final String moatParams = "moatParams";
    private static final String startTracking = "startTracking";
    private static final String stopTracking = "stopTracking";
    private static final String success = "success";
    private Application mApplication;
    
    public MOATJSAdapter(final Application mApplication) {
        this.mApplication = mApplication;
    }
    
    private MOATManager.EventsListener createEventListener(final IronSourceWebView.JSInterface.JSCallbackTask jsCallbackTask, final String s, final String s2) {
        return new MOATManager.EventsListener() {
            @Override
            public void onTrackingFailedToStart(final String s) {
                if (jsCallbackTask != null) {
                    jsCallbackTask.sendMessage(false, s2, s);
                }
            }
            
            @Override
            public void onTrackingStarted(final String s) {
                if (jsCallbackTask != null) {
                    jsCallbackTask.sendMessage(true, s, s);
                }
            }
            
            @Override
            public void onTrackingStopped(final String s) {
                if (jsCallbackTask != null) {
                    jsCallbackTask.sendMessage(true, s, s);
                }
            }
        };
    }
    
    private FunctionCall fetchFunctionCall(final String s) throws JSONException {
        final JSONObject jsonObject = new JSONObject(s);
        final FunctionCall functionCall = new FunctionCall();
        functionCall.name = jsonObject.optString("moatFunction");
        functionCall.params = jsonObject.optJSONObject("moatParams");
        functionCall.successCallback = jsonObject.optString("success");
        functionCall.failCallback = jsonObject.optString("fail");
        return functionCall;
    }
    
    void call(final String s, final IronSourceWebView.JSInterface.JSCallbackTask jsCallbackTask, final WebView webView) throws Exception {
        final FunctionCall fetchFunctionCall = this.fetchFunctionCall(s);
        if ("initWithOptions".equals(fetchFunctionCall.name)) {
            MOATManager.initWithOptions(fetchFunctionCall.params, this.mApplication);
        }
        else {
            if ("createAdTracker".equals(fetchFunctionCall.name) && webView != null) {
                MOATManager.createAdTracker(webView);
                return;
            }
            if ("startTracking".equals(fetchFunctionCall.name)) {
                MOATManager.setEventListener(this.createEventListener(jsCallbackTask, fetchFunctionCall.successCallback, fetchFunctionCall.failCallback));
                MOATManager.startTracking();
                return;
            }
            if ("stopTracking".equals(fetchFunctionCall.name)) {
                MOATManager.setEventListener(this.createEventListener(jsCallbackTask, fetchFunctionCall.successCallback, fetchFunctionCall.failCallback));
                MOATManager.stopTracking();
            }
        }
    }
    
    private static class FunctionCall
    {
        String failCallback;
        String name;
        JSONObject params;
        String successCallback;
    }
}
