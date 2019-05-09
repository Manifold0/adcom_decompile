// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import org.json.JSONObject;

public class OSPermissionSubscriptionState
{
    OSEmailSubscriptionState emailSubscriptionStatus;
    OSPermissionState permissionStatus;
    OSSubscriptionState subscriptionStatus;
    
    public OSEmailSubscriptionState getEmailSubscriptionStatus() {
        return this.emailSubscriptionStatus;
    }
    
    public OSPermissionState getPermissionStatus() {
        return this.permissionStatus;
    }
    
    public OSSubscriptionState getSubscriptionStatus() {
        return this.subscriptionStatus;
    }
    
    public JSONObject toJSONObject() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("permissionStatus", (Object)this.permissionStatus.toJSONObject());
            jsonObject.put("subscriptionStatus", (Object)this.subscriptionStatus.toJSONObject());
            jsonObject.put("emailSubscriptionStatus", (Object)this.emailSubscriptionStatus.toJSONObject());
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
