// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import org.json.JSONObject;

public class OSSubscriptionStateChanges
{
    OSSubscriptionState from;
    OSSubscriptionState to;
    
    public OSSubscriptionState getFrom() {
        return this.from;
    }
    
    public OSSubscriptionState getTo() {
        return this.to;
    }
    
    public JSONObject toJSONObject() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("from", (Object)this.from.toJSONObject());
            jsonObject.put("to", (Object)this.to.toJSONObject());
            return jsonObject;
        }
        catch (Throwable t) {
            t.printStackTrace();
            return jsonObject;
        }
    }
    
    @Override
    public String toString() {
        return this.toJSONObject().toString();
    }
}
