// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONArray;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.List;

public class OSNotification
{
    public int androidNotificationId;
    public DisplayType displayType;
    public List<OSNotificationPayload> groupedNotifications;
    public boolean isAppInFocus;
    public OSNotificationPayload payload;
    public boolean shown;
    
    public OSNotification() {
    }
    
    public OSNotification(final JSONObject jsonObject) {
        this.isAppInFocus = jsonObject.optBoolean("isAppInFocus");
        this.shown = jsonObject.optBoolean("shown", this.shown);
        this.androidNotificationId = jsonObject.optInt("androidNotificationId");
        this.displayType = DisplayType.values()[jsonObject.optInt("displayType")];
        if (jsonObject.has("groupedNotifications")) {
            final JSONArray optJSONArray = jsonObject.optJSONArray("groupedNotifications");
            this.groupedNotifications = new ArrayList<OSNotificationPayload>();
            for (int i = 0; i < optJSONArray.length(); ++i) {
                this.groupedNotifications.add(new OSNotificationPayload(optJSONArray.optJSONObject(i)));
            }
        }
        if (jsonObject.has("payload")) {
            this.payload = new OSNotificationPayload(jsonObject.optJSONObject("payload"));
        }
    }
    
    @Deprecated
    public String stringify() {
        final JSONObject jsonObject = this.toJSONObject();
        try {
            if (jsonObject.has("additionalData")) {
                jsonObject.put("additionalData", (Object)jsonObject.optJSONObject("additionalData").toString());
            }
            return jsonObject.toString();
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return jsonObject.toString();
        }
    }
    
    public JSONObject toJSONObject() {
        final JSONObject jsonObject = new JSONObject();
        Label_0124: {
            JSONArray jsonArray;
            try {
                jsonObject.put("isAppInFocus", this.isAppInFocus);
                jsonObject.put("shown", this.shown);
                jsonObject.put("androidNotificationId", this.androidNotificationId);
                jsonObject.put("displayType", this.displayType.ordinal());
                if (this.groupedNotifications == null) {
                    break Label_0124;
                }
                jsonArray = new JSONArray();
                final Iterator<OSNotificationPayload> iterator = this.groupedNotifications.iterator();
                while (iterator.hasNext()) {
                    jsonArray.put((Object)iterator.next().toJSONObject());
                }
            }
            catch (Throwable t) {
                t.printStackTrace();
                return jsonObject;
            }
            jsonObject.put("groupedNotifications", (Object)jsonArray);
        }
        jsonObject.put("payload", (Object)this.payload.toJSONObject());
        return jsonObject;
    }
    
    public enum DisplayType
    {
        InAppAlert, 
        None, 
        Notification;
    }
}
