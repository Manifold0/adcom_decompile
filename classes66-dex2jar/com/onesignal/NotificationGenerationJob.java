// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import java.security.SecureRandom;
import android.net.Uri;
import org.json.JSONObject;
import android.content.Context;

class NotificationGenerationJob
{
    Context context;
    JSONObject jsonPayload;
    Integer orgFlags;
    Uri orgSound;
    CharSequence overriddenBodyFromExtender;
    Integer overriddenFlags;
    Uri overriddenSound;
    CharSequence overriddenTitleFromExtender;
    NotificationExtenderService.OverrideSettings overrideSettings;
    boolean restoring;
    boolean showAsAlert;
    Long shownTimeStamp;
    
    NotificationGenerationJob(final Context context) {
        this.context = context;
    }
    
    Integer getAndroidId() {
        if (this.overrideSettings == null) {
            this.overrideSettings = new NotificationExtenderService.OverrideSettings();
        }
        if (this.overrideSettings.androidNotificationId == null) {
            this.overrideSettings.androidNotificationId = new SecureRandom().nextInt();
        }
        return this.overrideSettings.androidNotificationId;
    }
    
    int getAndroidIdWithoutCreate() {
        if (this.overrideSettings == null || this.overrideSettings.androidNotificationId == null) {
            return -1;
        }
        return this.overrideSettings.androidNotificationId;
    }
    
    CharSequence getBody() {
        if (this.overriddenBodyFromExtender != null) {
            return this.overriddenBodyFromExtender;
        }
        return this.jsonPayload.optString("alert", (String)null);
    }
    
    CharSequence getTitle() {
        if (this.overriddenTitleFromExtender != null) {
            return this.overriddenTitleFromExtender;
        }
        return this.jsonPayload.optString("title", (String)null);
    }
    
    boolean hasExtender() {
        return this.overrideSettings != null && this.overrideSettings.extender != null;
    }
    
    void setAndroidIdWithOutOverriding(final Integer androidNotificationId) {
        if (androidNotificationId != null && (this.overrideSettings == null || this.overrideSettings.androidNotificationId == null)) {
            if (this.overrideSettings == null) {
                this.overrideSettings = new NotificationExtenderService.OverrideSettings();
            }
            this.overrideSettings.androidNotificationId = androidNotificationId;
        }
    }
}
