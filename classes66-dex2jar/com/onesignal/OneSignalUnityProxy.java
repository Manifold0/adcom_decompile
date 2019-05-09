// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import org.json.JSONObject;
import android.content.Context;
import android.app.Activity;
import java.lang.reflect.Method;

public class OneSignalUnityProxy implements NotificationOpenedHandler, NotificationReceivedHandler, OSPermissionObserver, OSSubscriptionObserver, OSEmailSubscriptionObserver
{
    private static String unityListenerName;
    private static Method unitySendMessage;
    
    public OneSignalUnityProxy(final String unityListenerName, final String s, final String s2, final int n, final int n2, final boolean requiresUserPrivacyConsent) {
        OneSignalUnityProxy.unityListenerName = unityListenerName;
        try {
            OneSignal.setRequiresUserPrivacyConsent(requiresUserPrivacyConsent);
            final Class<?> forName = Class.forName("com.unity3d.player.UnityPlayer");
            OneSignalUnityProxy.unitySendMessage = forName.getMethod("UnitySendMessage", String.class, String.class, String.class);
            OneSignal.sdkType = "unity";
            OneSignal.setLogLevel(n, n2);
            final OneSignal.Builder currentOrNewInitBuilder = OneSignal.getCurrentOrNewInitBuilder();
            currentOrNewInitBuilder.unsubscribeWhenNotificationsAreDisabled(true);
            currentOrNewInitBuilder.filterOtherGCMReceivers(true);
            OneSignal.init((Context)forName.getField("currentActivity").get(null), s, s2, (OneSignal.NotificationOpenedHandler)this, (OneSignal.NotificationReceivedHandler)this);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    private static void unitySafeInvoke(final String s, final String s2) {
        try {
            OneSignalUnityProxy.unitySendMessage.invoke(null, OneSignalUnityProxy.unityListenerName, s, s2);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public void addEmailSubscriptionObserver() {
        OneSignal.addEmailSubscriptionObserver(this);
    }
    
    public void addPermissionObserver() {
        OneSignal.addPermissionObserver(this);
    }
    
    public void addSubscriptionObserver() {
        OneSignal.addSubscriptionObserver(this);
    }
    
    public void cancelGroupedNotifications(final String s) {
        OneSignal.cancelGroupedNotifications(s);
    }
    
    public void cancelNotification(final int n) {
        OneSignal.cancelNotification(n);
    }
    
    public void clearOneSignalNotifications() {
        OneSignal.clearOneSignalNotifications();
    }
    
    public void deleteTag(final String s) {
        OneSignal.deleteTag(s);
    }
    
    public void deleteTags(final String s) {
        OneSignal.deleteTags(s);
    }
    
    public void enableSound(final boolean b) {
        OneSignal.enableSound(b);
    }
    
    public void enableVibrate(final boolean b) {
        OneSignal.enableVibrate(b);
    }
    
    public String getPermissionSubscriptionState() {
        return OneSignal.getPermissionSubscriptionState().toJSONObject().toString();
    }
    
    public void getTags() {
        OneSignal.getTags((OneSignal.GetTagsHandler)new GetTagsHandler() {
            @Override
            public void tagsAvailable(final JSONObject jsonObject) {
                String string;
                if (jsonObject != null) {
                    string = jsonObject.toString();
                }
                else {
                    string = "{}";
                }
                unitySafeInvoke("onTagsReceived", string);
            }
        });
    }
    
    public void idsAvailable() {
        OneSignal.idsAvailable((OneSignal.IdsAvailableHandler)new IdsAvailableHandler() {
            @Override
            public void idsAvailable(final String s, final String s2) {
                final JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("userId", (Object)s);
                    if (s2 != null) {
                        jsonObject.put("pushToken", (Object)s2);
                    }
                    else {
                        jsonObject.put("pushToken", (Object)"");
                    }
                    unitySafeInvoke("onIdsAvailable", jsonObject.toString());
                }
                catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        });
    }
    
    public void logoutEmail() {
        OneSignal.logoutEmail((OneSignal.EmailUpdateHandler)new EmailUpdateHandler() {
            @Override
            public void onFailure(final EmailUpdateError emailUpdateError) {
                unitySafeInvoke("onLogoutEmailFailure", "{\"error\": \"" + emailUpdateError.getMessage() + "\"}");
            }
            
            @Override
            public void onSuccess() {
                unitySafeInvoke("onLogoutEmailSuccess", "{\"status\": \"success\"}");
            }
        });
    }
    
    @Override
    public void notificationOpened(final OSNotificationOpenResult osNotificationOpenResult) {
        unitySafeInvoke("onPushNotificationOpened", osNotificationOpenResult.toJSONObject().toString());
    }
    
    @Override
    public void notificationReceived(final OSNotification osNotification) {
        unitySafeInvoke("onPushNotificationReceived", osNotification.toJSONObject().toString());
    }
    
    @Override
    public void onOSEmailSubscriptionChanged(final OSEmailSubscriptionStateChanges osEmailSubscriptionStateChanges) {
        unitySafeInvoke("onOSEmailSubscriptionChanged", osEmailSubscriptionStateChanges.toJSONObject().toString());
    }
    
    @Override
    public void onOSPermissionChanged(final OSPermissionStateChanges osPermissionStateChanges) {
        unitySafeInvoke("onOSPermissionChanged", osPermissionStateChanges.toJSONObject().toString());
    }
    
    @Override
    public void onOSSubscriptionChanged(final OSSubscriptionStateChanges osSubscriptionStateChanges) {
        unitySafeInvoke("onOSSubscriptionChanged", osSubscriptionStateChanges.toJSONObject().toString());
    }
    
    public void postNotification(final String s) {
        OneSignal.postNotification(s, (OneSignal.PostNotificationResponseHandler)new PostNotificationResponseHandler() {
            @Override
            public void onFailure(final JSONObject jsonObject) {
                unitySafeInvoke("onPostNotificationFailed", jsonObject.toString());
            }
            
            @Override
            public void onSuccess(final JSONObject jsonObject) {
                unitySafeInvoke("onPostNotificationSuccess", jsonObject.toString());
            }
        });
    }
    
    public void promptLocation() {
        OneSignal.promptLocation();
    }
    
    public void provideUserConsent(final boolean b) {
        OneSignal.provideUserConsent(b);
    }
    
    public void removeEmailSubscriptionObserver() {
        OneSignal.removeEmailSubscriptionObserver(this);
    }
    
    public void removeExternalUserId() {
        OneSignal.removeExternalUserId();
    }
    
    public void removePermissionObserver() {
        OneSignal.removePermissionObserver(this);
    }
    
    public void removeSubscriptionObserver() {
        OneSignal.removeSubscriptionObserver(this);
    }
    
    public void sendTag(final String s, final String s2) {
        OneSignal.sendTag(s, s2);
    }
    
    public void sendTags(final String s) {
        OneSignal.sendTags(s);
    }
    
    public void setEmail(final String s, final String s2) {
        OneSignal.setEmail(s, s2, (OneSignal.EmailUpdateHandler)new EmailUpdateHandler() {
            @Override
            public void onFailure(final EmailUpdateError emailUpdateError) {
                unitySafeInvoke("onSetEmailFailure", "{\"error\": \"" + emailUpdateError.getMessage() + "\"}");
            }
            
            @Override
            public void onSuccess() {
                unitySafeInvoke("onSetEmailSuccess", "{\"status\": \"success\"}");
            }
        });
    }
    
    public void setExternalUserId(final String externalUserId) {
        OneSignal.setExternalUserId(externalUserId);
    }
    
    public void setInFocusDisplaying(final int inFocusDisplaying) {
        OneSignal.setInFocusDisplaying(inFocusDisplaying);
    }
    
    public void setLocationShared(final boolean locationShared) {
        OneSignal.setLocationShared(locationShared);
    }
    
    public void setRequiresUserPrivacyConsent(final boolean requiresUserPrivacyConsent) {
        OneSignal.setRequiresUserPrivacyConsent(requiresUserPrivacyConsent);
    }
    
    public void setSubscription(final boolean subscription) {
        OneSignal.setSubscription(subscription);
    }
    
    public void syncHashedEmail(final String s) {
        OneSignal.syncHashedEmail(s);
    }
    
    public boolean userProvidedPrivacyConsent() {
        return OneSignal.userProvidedPrivacyConsent();
    }
}
