// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import java.util.Iterator;
import org.json.JSONArray;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.List;

public class OSNotificationPayload
{
    public List<ActionButton> actionButtons;
    public JSONObject additionalData;
    public BackgroundImageLayout backgroundImageLayout;
    public String bigPicture;
    public String body;
    public String collapseId;
    public String fromProjectNumber;
    public String groupKey;
    public String groupMessage;
    public String largeIcon;
    public String launchURL;
    public String ledColor;
    public int lockScreenVisibility;
    public String notificationID;
    public int priority;
    public String rawPayload;
    public String smallIcon;
    public String smallIconAccentColor;
    public String sound;
    public String templateId;
    public String templateName;
    public String title;
    
    public OSNotificationPayload() {
        this.lockScreenVisibility = 1;
    }
    
    public OSNotificationPayload(final JSONObject jsonObject) {
        this.lockScreenVisibility = 1;
        this.notificationID = jsonObject.optString("notificationID");
        this.title = jsonObject.optString("title");
        this.body = jsonObject.optString("body");
        this.additionalData = jsonObject.optJSONObject("additionalData");
        this.smallIcon = jsonObject.optString("smallIcon");
        this.largeIcon = jsonObject.optString("largeIcon");
        this.bigPicture = jsonObject.optString("bigPicture");
        this.smallIconAccentColor = jsonObject.optString("smallIconAccentColor");
        this.launchURL = jsonObject.optString("launchURL");
        this.sound = jsonObject.optString("sound");
        this.ledColor = jsonObject.optString("ledColor");
        this.lockScreenVisibility = jsonObject.optInt("lockScreenVisibility");
        this.groupKey = jsonObject.optString("groupKey");
        this.groupMessage = jsonObject.optString("groupMessage");
        if (jsonObject.has("actionButtons")) {
            this.actionButtons = new ArrayList<ActionButton>();
            final JSONArray optJSONArray = jsonObject.optJSONArray("actionButtons");
            for (int i = 0; i < optJSONArray.length(); ++i) {
                this.actionButtons.add(new ActionButton(optJSONArray.optJSONObject(i)));
            }
        }
        this.fromProjectNumber = jsonObject.optString("fromProjectNumber");
        this.collapseId = jsonObject.optString("collapseId");
        this.priority = jsonObject.optInt("priority");
        this.rawPayload = jsonObject.optString("rawPayload");
    }
    
    public JSONObject toJSONObject() {
        final JSONObject jsonObject = new JSONObject();
        Label_0238: {
            JSONArray jsonArray;
            try {
                jsonObject.put("notificationID", (Object)this.notificationID);
                jsonObject.put("title", (Object)this.title);
                jsonObject.put("body", (Object)this.body);
                if (this.additionalData != null) {
                    jsonObject.put("additionalData", (Object)this.additionalData);
                }
                jsonObject.put("smallIcon", (Object)this.smallIcon);
                jsonObject.put("largeIcon", (Object)this.largeIcon);
                jsonObject.put("bigPicture", (Object)this.bigPicture);
                jsonObject.put("smallIconAccentColor", (Object)this.smallIconAccentColor);
                jsonObject.put("launchURL", (Object)this.launchURL);
                jsonObject.put("sound", (Object)this.sound);
                jsonObject.put("ledColor", (Object)this.ledColor);
                jsonObject.put("lockScreenVisibility", this.lockScreenVisibility);
                jsonObject.put("groupKey", (Object)this.groupKey);
                jsonObject.put("groupMessage", (Object)this.groupMessage);
                if (this.actionButtons == null) {
                    break Label_0238;
                }
                jsonArray = new JSONArray();
                final Iterator<ActionButton> iterator = this.actionButtons.iterator();
                while (iterator.hasNext()) {
                    jsonArray.put((Object)iterator.next().toJSONObject());
                }
            }
            catch (Throwable t) {
                t.printStackTrace();
                return jsonObject;
            }
            jsonObject.put("actionButtons", (Object)jsonArray);
        }
        jsonObject.put("fromProjectNumber", (Object)this.fromProjectNumber);
        jsonObject.put("collapseId", (Object)this.collapseId);
        jsonObject.put("priority", this.priority);
        jsonObject.put("rawPayload", (Object)this.rawPayload);
        return jsonObject;
    }
    
    public static class ActionButton
    {
        public String icon;
        public String id;
        public String text;
        
        public ActionButton() {
        }
        
        public ActionButton(final JSONObject jsonObject) {
            this.id = jsonObject.optString("id");
            this.text = jsonObject.optString("text");
            this.icon = jsonObject.optString("icon");
        }
        
        public JSONObject toJSONObject() {
            final JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("id", (Object)this.id);
                jsonObject.put("text", (Object)this.text);
                jsonObject.put("icon", (Object)this.icon);
                return jsonObject;
            }
            catch (Throwable t) {
                t.printStackTrace();
                return jsonObject;
            }
        }
    }
    
    public static class BackgroundImageLayout
    {
        public String bodyTextColor;
        public String image;
        public String titleTextColor;
    }
}
