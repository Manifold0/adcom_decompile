// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.os.Bundle;
import java.lang.reflect.Method;
import android.content.Context;
import java.util.concurrent.atomic.AtomicLong;

class TrackFirebaseAnalytics
{
    private static final String EVENT_NOTIFICATION_INFLUENCE_OPEN = "os_notification_influence_open";
    private static final String EVENT_NOTIFICATION_OPENED = "os_notification_opened";
    private static final String EVENT_NOTIFICATION_RECEIVED = "os_notification_received";
    private static Class<?> FirebaseAnalyticsClass;
    private static AtomicLong lastOpenedTime;
    private static OSNotificationPayload lastReceivedPayload;
    private static AtomicLong lastReceivedTime;
    private Context appContext;
    private Object mFirebaseAnalyticsInstance;
    
    TrackFirebaseAnalytics(final Context appContext) {
        this.appContext = appContext;
    }
    
    static boolean CanTrack() {
        try {
            TrackFirebaseAnalytics.FirebaseAnalyticsClass = Class.forName("com.google.firebase.analytics.FirebaseAnalytics");
            return true;
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    private String getCampaignNameFromPayload(final OSNotificationPayload osNotificationPayload) {
        if (!osNotificationPayload.templateName.isEmpty() && !osNotificationPayload.templateId.isEmpty()) {
            return osNotificationPayload.templateName + " - " + osNotificationPayload.templateId;
        }
        if (osNotificationPayload.title != null) {
            return osNotificationPayload.title.substring(0, Math.min(10, osNotificationPayload.title.length()));
        }
        return "";
    }
    
    private Object getFirebaseAnalyticsInstance(final Context context) {
        Label_0031: {
            if (this.mFirebaseAnalyticsInstance != null) {
                break Label_0031;
            }
            final Method instanceMethod = getInstanceMethod(TrackFirebaseAnalytics.FirebaseAnalyticsClass);
            try {
                this.mFirebaseAnalyticsInstance = instanceMethod.invoke(null, context);
                return this.mFirebaseAnalyticsInstance;
            }
            catch (Throwable t) {
                t.printStackTrace();
                return null;
            }
        }
    }
    
    private static Method getInstanceMethod(final Class clazz) {
        try {
            return clazz.getMethod("getInstance", Context.class);
        }
        catch (NoSuchMethodException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private static Method getTrackMethod(final Class clazz) {
        try {
            return clazz.getMethod("logEvent", String.class, Bundle.class);
        }
        catch (NoSuchMethodException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    void trackInfluenceOpenEvent() {
        if (TrackFirebaseAnalytics.lastReceivedTime != null && TrackFirebaseAnalytics.lastReceivedPayload != null) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - TrackFirebaseAnalytics.lastReceivedTime.get() <= 120000L) {
                if (TrackFirebaseAnalytics.lastOpenedTime != null) {
                    if (currentTimeMillis - TrackFirebaseAnalytics.lastOpenedTime.get() < 30000L) {
                        return;
                    }
                }
                try {
                    final Object firebaseAnalyticsInstance = this.getFirebaseAnalyticsInstance(this.appContext);
                    final Method trackMethod = getTrackMethod(TrackFirebaseAnalytics.FirebaseAnalyticsClass);
                    final Bundle bundle = new Bundle();
                    bundle.putString("source", "OneSignal");
                    bundle.putString("medium", "notification");
                    bundle.putString("notification_id", TrackFirebaseAnalytics.lastReceivedPayload.notificationID);
                    bundle.putString("campaign", this.getCampaignNameFromPayload(TrackFirebaseAnalytics.lastReceivedPayload));
                    trackMethod.invoke(firebaseAnalyticsInstance, "os_notification_influence_open", bundle);
                }
                catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }
    }
    
    void trackOpenedEvent(final OSNotificationOpenResult osNotificationOpenResult) {
        if (TrackFirebaseAnalytics.lastOpenedTime == null) {
            TrackFirebaseAnalytics.lastOpenedTime = new AtomicLong();
        }
        TrackFirebaseAnalytics.lastOpenedTime.set(System.currentTimeMillis());
        try {
            final Object firebaseAnalyticsInstance = this.getFirebaseAnalyticsInstance(this.appContext);
            final Method trackMethod = getTrackMethod(TrackFirebaseAnalytics.FirebaseAnalyticsClass);
            final Bundle bundle = new Bundle();
            bundle.putString("source", "OneSignal");
            bundle.putString("medium", "notification");
            bundle.putString("notification_id", osNotificationOpenResult.notification.payload.notificationID);
            bundle.putString("campaign", this.getCampaignNameFromPayload(osNotificationOpenResult.notification.payload));
            trackMethod.invoke(firebaseAnalyticsInstance, "os_notification_opened", bundle);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    void trackReceivedEvent(final OSNotificationOpenResult osNotificationOpenResult) {
        try {
            final Object firebaseAnalyticsInstance = this.getFirebaseAnalyticsInstance(this.appContext);
            final Method trackMethod = getTrackMethod(TrackFirebaseAnalytics.FirebaseAnalyticsClass);
            final Bundle bundle = new Bundle();
            bundle.putString("source", "OneSignal");
            bundle.putString("medium", "notification");
            bundle.putString("notification_id", osNotificationOpenResult.notification.payload.notificationID);
            bundle.putString("campaign", this.getCampaignNameFromPayload(osNotificationOpenResult.notification.payload));
            trackMethod.invoke(firebaseAnalyticsInstance, "os_notification_received", bundle);
            if (TrackFirebaseAnalytics.lastReceivedTime == null) {
                TrackFirebaseAnalytics.lastReceivedTime = new AtomicLong();
            }
            TrackFirebaseAnalytics.lastReceivedTime.set(System.currentTimeMillis());
            TrackFirebaseAnalytics.lastReceivedPayload = osNotificationOpenResult.notification.payload;
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
