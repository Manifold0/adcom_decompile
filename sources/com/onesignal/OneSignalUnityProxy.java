package com.onesignal;

import android.app.Activity;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import com.onesignal.OneSignal.Builder;
import com.onesignal.OneSignal.EmailUpdateError;
import com.onesignal.OneSignal.EmailUpdateHandler;
import com.onesignal.OneSignal.GetTagsHandler;
import com.onesignal.OneSignal.IdsAvailableHandler;
import com.onesignal.OneSignal.NotificationOpenedHandler;
import com.onesignal.OneSignal.NotificationReceivedHandler;
import com.onesignal.OneSignal.PostNotificationResponseHandler;
import java.lang.reflect.Method;
import org.json.JSONObject;

public class OneSignalUnityProxy implements NotificationOpenedHandler, NotificationReceivedHandler, OSPermissionObserver, OSSubscriptionObserver, OSEmailSubscriptionObserver {
    private static String unityListenerName;
    private static Method unitySendMessage;

    /* renamed from: com.onesignal.OneSignalUnityProxy$1 */
    class C13451 implements EmailUpdateHandler {
        C13451() {
        }

        public void onSuccess() {
            OneSignalUnityProxy.unitySafeInvoke("onSetEmailSuccess", "{\"status\": \"success\"}");
        }

        public void onFailure(EmailUpdateError error) {
            OneSignalUnityProxy.unitySafeInvoke("onSetEmailFailure", "{\"error\": \"" + error.getMessage() + "\"}");
        }
    }

    /* renamed from: com.onesignal.OneSignalUnityProxy$2 */
    class C13462 implements EmailUpdateHandler {
        C13462() {
        }

        public void onSuccess() {
            OneSignalUnityProxy.unitySafeInvoke("onLogoutEmailSuccess", "{\"status\": \"success\"}");
        }

        public void onFailure(EmailUpdateError error) {
            OneSignalUnityProxy.unitySafeInvoke("onLogoutEmailFailure", "{\"error\": \"" + error.getMessage() + "\"}");
        }
    }

    /* renamed from: com.onesignal.OneSignalUnityProxy$3 */
    class C13473 implements GetTagsHandler {
        C13473() {
        }

        public void tagsAvailable(JSONObject tags) {
            String tagsStr;
            if (tags != null) {
                tagsStr = tags.toString();
            } else {
                tagsStr = "{}";
            }
            OneSignalUnityProxy.unitySafeInvoke("onTagsReceived", tagsStr);
        }
    }

    /* renamed from: com.onesignal.OneSignalUnityProxy$4 */
    class C13484 implements IdsAvailableHandler {
        C13484() {
        }

        public void idsAvailable(String userId, String registrationId) {
            JSONObject jsonIds = new JSONObject();
            try {
                jsonIds.put(ServerResponseWrapper.USER_ID_FIELD, userId);
                if (registrationId != null) {
                    jsonIds.put("pushToken", registrationId);
                } else {
                    jsonIds.put("pushToken", "");
                }
                OneSignalUnityProxy.unitySafeInvoke("onIdsAvailable", jsonIds.toString());
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    /* renamed from: com.onesignal.OneSignalUnityProxy$5 */
    class C13495 implements PostNotificationResponseHandler {
        C13495() {
        }

        public void onSuccess(JSONObject response) {
            OneSignalUnityProxy.unitySafeInvoke("onPostNotificationSuccess", response.toString());
        }

        public void onFailure(JSONObject response) {
            OneSignalUnityProxy.unitySafeInvoke("onPostNotificationFailed", response.toString());
        }
    }

    public OneSignalUnityProxy(String listenerName, String googleProjectNumber, String oneSignalAppId, int logLevel, int visualLogLevel, boolean requiresUserPrivacyConsent) {
        unityListenerName = listenerName;
        try {
            OneSignal.setRequiresUserPrivacyConsent(requiresUserPrivacyConsent);
            Class unityPlayerClass = Class.forName("com.unity3d.player.UnityPlayer");
            unitySendMessage = unityPlayerClass.getMethod("UnitySendMessage", new Class[]{String.class, String.class, String.class});
            OneSignal.sdkType = "unity";
            OneSignal.setLogLevel(logLevel, visualLogLevel);
            Builder builder = OneSignal.getCurrentOrNewInitBuilder();
            builder.unsubscribeWhenNotificationsAreDisabled(true);
            builder.filterOtherGCMReceivers(true);
            OneSignal.init((Activity) unityPlayerClass.getField("currentActivity").get(null), googleProjectNumber, oneSignalAppId, this, this);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void notificationOpened(OSNotificationOpenResult result) {
        unitySafeInvoke("onPushNotificationOpened", result.toJSONObject().toString());
    }

    public void notificationReceived(OSNotification notification) {
        unitySafeInvoke("onPushNotificationReceived", notification.toJSONObject().toString());
    }

    public void sendTag(String key, String value) {
        OneSignal.sendTag(key, value);
    }

    public void sendTags(String json) {
        OneSignal.sendTags(json);
    }

    public void setEmail(String email, String authHash) {
        OneSignal.setEmail(email, authHash, new C13451());
    }

    public void logoutEmail() {
        OneSignal.logoutEmail(new C13462());
    }

    public void getTags() {
        OneSignal.getTags(new C13473());
    }

    public void deleteTag(String key) {
        OneSignal.deleteTag(key);
    }

    public void deleteTags(String json) {
        OneSignal.deleteTags(json);
    }

    public void idsAvailable() {
        OneSignal.idsAvailable(new C13484());
    }

    public void enableSound(boolean enable) {
        OneSignal.enableSound(enable);
    }

    public void enableVibrate(boolean enable) {
        OneSignal.enableVibrate(enable);
    }

    public void setInFocusDisplaying(int displayOption) {
        OneSignal.setInFocusDisplaying(displayOption);
    }

    public void setSubscription(boolean enable) {
        OneSignal.setSubscription(enable);
    }

    public void postNotification(String json) {
        OneSignal.postNotification(json, new C13495());
    }

    public void promptLocation() {
        OneSignal.promptLocation();
    }

    public void syncHashedEmail(String email) {
        OneSignal.syncHashedEmail(email);
    }

    public void clearOneSignalNotifications() {
        OneSignal.clearOneSignalNotifications();
    }

    public void cancelNotification(int id) {
        OneSignal.cancelNotification(id);
    }

    public void cancelGroupedNotifications(String group) {
        OneSignal.cancelGroupedNotifications(group);
    }

    public void addPermissionObserver() {
        OneSignal.addPermissionObserver(this);
    }

    public void removePermissionObserver() {
        OneSignal.removePermissionObserver(this);
    }

    public void addSubscriptionObserver() {
        OneSignal.addSubscriptionObserver(this);
    }

    public void removeSubscriptionObserver() {
        OneSignal.removeSubscriptionObserver(this);
    }

    public void addEmailSubscriptionObserver() {
        OneSignal.addEmailSubscriptionObserver(this);
    }

    public void removeEmailSubscriptionObserver() {
        OneSignal.removeEmailSubscriptionObserver(this);
    }

    public boolean userProvidedPrivacyConsent() {
        return OneSignal.userProvidedPrivacyConsent();
    }

    public void provideUserConsent(boolean granted) {
        OneSignal.provideUserConsent(granted);
    }

    public void setRequiresUserPrivacyConsent(boolean required) {
        OneSignal.setRequiresUserPrivacyConsent(required);
    }

    public void setExternalUserId(String externalId) {
        OneSignal.setExternalUserId(externalId);
    }

    public void removeExternalUserId() {
        OneSignal.removeExternalUserId();
    }

    public String getPermissionSubscriptionState() {
        return OneSignal.getPermissionSubscriptionState().toJSONObject().toString();
    }

    public void setLocationShared(boolean shared) {
        OneSignal.setLocationShared(shared);
    }

    public void onOSPermissionChanged(OSPermissionStateChanges stateChanges) {
        unitySafeInvoke("onOSPermissionChanged", stateChanges.toJSONObject().toString());
    }

    public void onOSSubscriptionChanged(OSSubscriptionStateChanges stateChanges) {
        unitySafeInvoke("onOSSubscriptionChanged", stateChanges.toJSONObject().toString());
    }

    public void onOSEmailSubscriptionChanged(OSEmailSubscriptionStateChanges stateChanges) {
        unitySafeInvoke("onOSEmailSubscriptionChanged", stateChanges.toJSONObject().toString());
    }

    private static void unitySafeInvoke(String method, String params) {
        try {
            unitySendMessage.invoke(null, new Object[]{unityListenerName, method, params});
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
