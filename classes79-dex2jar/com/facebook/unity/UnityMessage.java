// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.unity;

import android.util.Log;
import java.util.HashMap;
import java.io.Serializable;
import java.util.Map;

public class UnityMessage
{
    private String methodName;
    private Map<String, Serializable> params;
    
    public UnityMessage(final String methodName) {
        this.params = new HashMap<String, Serializable>();
        this.methodName = methodName;
    }
    
    public static UnityMessage createWithCallbackFromParams(final String s, final UnityParams unityParams) {
        final UnityMessage unityMessage = new UnityMessage(s);
        if (unityParams.hasString("callback_id")) {
            unityMessage.put("callback_id", unityParams.getString("callback_id"));
        }
        return unityMessage;
    }
    
    public UnityMessage put(final String s, final Serializable s2) {
        this.params.put(s, s2);
        return this;
    }
    
    public UnityMessage putCancelled() {
        this.put("cancelled", true);
        return this;
    }
    
    public UnityMessage putID(final String s) {
        this.put("id", s);
        return this;
    }
    
    public void send() {
        assert this.methodName != null : "no method specified";
        final String string = new UnityParams(this.params).toString();
        Log.v(FB.TAG, "sending to Unity " + this.methodName + "(" + string + ")");
        try {
            UnityReflection.SendMessage("UnityFacebookSDKPlugin", this.methodName, string);
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            Log.v(FB.TAG, "message not send, Unity not initialized");
        }
    }
    
    public void sendError(final String s) {
        this.put("error", s);
        this.send();
    }
}
