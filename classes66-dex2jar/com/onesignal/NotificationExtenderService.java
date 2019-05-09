// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.support.v4.app.NotificationCompat$Extender;
import android.support.annotation.Nullable;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.os.Bundle;
import org.json.JSONException;
import java.util.List;
import android.content.pm.PackageManager;
import android.content.ComponentName;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.content.Context;
import org.json.JSONObject;

public abstract class NotificationExtenderService extends JobIntentService
{
    static final int EXTENDER_SERVICE_JOB_ID = 2071862121;
    private OverrideSettings currentBaseOverrideSettings;
    private JSONObject currentJsonPayload;
    private boolean currentlyRestoring;
    private OSNotificationDisplayedResult osNotificationDisplayedResult;
    private Long restoreTimestamp;
    
    public NotificationExtenderService() {
        this.currentBaseOverrideSettings = null;
    }
    
    private NotificationGenerationJob createNotifJobFromCurrent() {
        final NotificationGenerationJob notificationGenerationJob = new NotificationGenerationJob((Context)this);
        notificationGenerationJob.restoring = this.currentlyRestoring;
        notificationGenerationJob.jsonPayload = this.currentJsonPayload;
        notificationGenerationJob.shownTimeStamp = this.restoreTimestamp;
        notificationGenerationJob.overrideSettings = this.currentBaseOverrideSettings;
        return notificationGenerationJob;
    }
    
    static Intent getIntent(final Context context) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent setPackage = new Intent().setAction("com.onesignal.NotificationExtender").setPackage(context.getPackageName());
        final List queryIntentServices = packageManager.queryIntentServices(setPackage, 128);
        if (queryIntentServices.size() < 1) {
            return null;
        }
        setPackage.setComponent(new ComponentName(context, queryIntentServices.get(0).serviceInfo.name));
        return setPackage;
    }
    
    private void processIntent(final Intent intent) {
        final Bundle extras = intent.getExtras();
        if (extras == null) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "No extras sent to NotificationExtenderService in its Intent!\n" + intent);
        }
        else {
            final String string = extras.getString("json_payload");
            if (string == null) {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "json_payload key is nonexistent from bundle passed to NotificationExtenderService: " + extras);
                return;
            }
            try {
                this.currentJsonPayload = new JSONObject(string);
                this.currentlyRestoring = extras.getBoolean("restoring", false);
                if (extras.containsKey("android_notif_id")) {
                    this.currentBaseOverrideSettings = new OverrideSettings();
                    this.currentBaseOverrideSettings.androidNotificationId = extras.getInt("android_notif_id");
                }
                if (this.currentlyRestoring || !OneSignal.notValidOrDuplicated((Context)this, this.currentJsonPayload)) {
                    this.restoreTimestamp = extras.getLong("timestamp");
                    this.processJsonObject(this.currentJsonPayload, this.currentlyRestoring);
                }
            }
            catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    protected final OSNotificationDisplayedResult displayNotification(final OverrideSettings overrideSettings) {
        if (this.osNotificationDisplayedResult != null || overrideSettings == null) {
            return null;
        }
        overrideSettings.override(this.currentBaseOverrideSettings);
        this.osNotificationDisplayedResult = new OSNotificationDisplayedResult();
        final NotificationGenerationJob notifJobFromCurrent = this.createNotifJobFromCurrent();
        notifJobFromCurrent.overrideSettings = overrideSettings;
        this.osNotificationDisplayedResult.androidNotificationId = NotificationBundleProcessor.ProcessJobForDisplay(notifJobFromCurrent);
        return this.osNotificationDisplayedResult;
    }
    
    @Override
    protected final void onHandleWork(final Intent intent) {
        if (intent == null) {
            return;
        }
        this.processIntent(intent);
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
    
    protected abstract boolean onNotificationProcessing(final OSNotificationReceivedResult p0);
    
    void processJsonObject(final JSONObject jsonPayload, final boolean restoring) {
        OSNotificationReceivedResult osNotificationReceivedResult;
        boolean onNotificationProcessing;
        int n;
        NotificationGenerationJob notificationGenerationJob;
        Label_0079_Outer:Label_0142_Outer:
        while (true) {
            osNotificationReceivedResult = new OSNotificationReceivedResult();
            osNotificationReceivedResult.payload = NotificationBundleProcessor.OSNotificationPayloadFrom(jsonPayload);
            osNotificationReceivedResult.restoring = restoring;
            osNotificationReceivedResult.isAppInFocus = OneSignal.isAppActive();
            this.osNotificationDisplayedResult = null;
            onNotificationProcessing = false;
            while (true) {
                Label_0211: {
                Label_0194:
                    while (true) {
                    Label_0189:
                        while (true) {
                            try {
                                onNotificationProcessing = this.onNotificationProcessing(osNotificationReceivedResult);
                                if (this.osNotificationDisplayedResult == null) {
                                    if (onNotificationProcessing || !NotificationBundleProcessor.shouldDisplay(jsonPayload.optString("alert"))) {
                                        break Label_0189;
                                    }
                                    n = 1;
                                    if (n != 0) {
                                        break Label_0211;
                                    }
                                    if (restoring) {
                                        break Label_0194;
                                    }
                                    notificationGenerationJob = new NotificationGenerationJob((Context)this);
                                    notificationGenerationJob.jsonPayload = jsonPayload;
                                    notificationGenerationJob.overrideSettings = new OverrideSettings();
                                    notificationGenerationJob.overrideSettings.androidNotificationId = -1;
                                    NotificationBundleProcessor.saveNotification(notificationGenerationJob, true);
                                    OneSignal.handleNotificationReceived(NotificationBundleProcessor.newJsonArray(jsonPayload), false, false);
                                    if (restoring) {
                                        OSUtils.sleep(100);
                                    }
                                }
                                return;
                            }
                            catch (Throwable t) {
                                if (this.osNotificationDisplayedResult == null) {
                                    OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "onNotificationProcessing throw an exception. Displaying normal OneSignal notification.", t);
                                    continue Label_0079_Outer;
                                }
                                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "onNotificationProcessing throw an exception. Extended notification displayed but custom processing did not finish.", t);
                                continue Label_0079_Outer;
                            }
                            break;
                        }
                        n = 0;
                        continue Label_0142_Outer;
                    }
                    if (this.currentBaseOverrideSettings != null) {
                        NotificationBundleProcessor.markRestoredNotificationAsDismissed(this.createNotifJobFromCurrent());
                        continue;
                    }
                    continue;
                }
                NotificationBundleProcessor.ProcessJobForDisplay(this.createNotifJobFromCurrent());
                continue;
            }
        }
    }
    
    public static class OverrideSettings
    {
        public Integer androidNotificationId;
        public NotificationCompat$Extender extender;
        
        void override(final OverrideSettings overrideSettings) {
            if (overrideSettings != null && overrideSettings.androidNotificationId != null) {
                this.androidNotificationId = overrideSettings.androidNotificationId;
            }
        }
    }
}
