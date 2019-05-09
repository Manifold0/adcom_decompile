// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import org.json.JSONException;
import org.json.JSONObject;

public class OSNotificationOpenResult
{
    public OSNotificationAction action;
    public OSNotification notification;
    
    @Deprecated
    public String stringify() {
        final JSONObject jsonObject = new JSONObject();
        try {
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("actionID", (Object)this.action.actionID);
            jsonObject2.put("type", this.action.type.ordinal());
            jsonObject.put("action", (Object)jsonObject2);
            jsonObject.put("notification", (Object)new JSONObject(this.notification.stringify()));
            return jsonObject.toString();
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return jsonObject.toString();
        }
    }
    
    public JSONObject toJSONObject() {
        final JSONObject jsonObject = new JSONObject();
        try {
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("actionID", (Object)this.action.actionID);
            jsonObject2.put("type", this.action.type.ordinal());
            jsonObject.put("action", (Object)jsonObject2);
            jsonObject.put("notification", (Object)this.notification.toJSONObject());
            return jsonObject;
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return jsonObject;
        }
    }
}
