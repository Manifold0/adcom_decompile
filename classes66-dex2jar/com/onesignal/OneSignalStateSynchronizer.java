// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import org.json.JSONException;
import org.json.JSONObject;

class OneSignalStateSynchronizer
{
    private static UserStateEmailSynchronizer userStateEmailSynchronizer;
    private static UserStatePushSynchronizer userStatePushSynchronizer;
    
    static void clearLocation() {
        getPushStateSynchronizer().clearLocation();
        getEmailStateSynchronizer().clearLocation();
    }
    
    static UserStateEmailSynchronizer getEmailStateSynchronizer() {
        if (OneSignalStateSynchronizer.userStateEmailSynchronizer == null) {
            OneSignalStateSynchronizer.userStateEmailSynchronizer = new UserStateEmailSynchronizer();
        }
        return OneSignalStateSynchronizer.userStateEmailSynchronizer;
    }
    
    static UserStatePushSynchronizer getPushStateSynchronizer() {
        if (OneSignalStateSynchronizer.userStatePushSynchronizer == null) {
            OneSignalStateSynchronizer.userStatePushSynchronizer = new UserStatePushSynchronizer();
        }
        return OneSignalStateSynchronizer.userStatePushSynchronizer;
    }
    
    static String getRegistrationId() {
        return getPushStateSynchronizer().getRegistrationId();
    }
    
    static boolean getSubscribed() {
        return getPushStateSynchronizer().getSubscribed();
    }
    
    static UserStateSynchronizer.GetTagsResult getTags(final boolean b) {
        return getPushStateSynchronizer().getTags(b);
    }
    
    static boolean getUserSubscribePreference() {
        return getPushStateSynchronizer().getUserSubscribePreference();
    }
    
    static void initUserState() {
        getPushStateSynchronizer().initUserState();
        getEmailStateSynchronizer().initUserState();
    }
    
    static void logoutEmail() {
        getPushStateSynchronizer().logoutEmail();
        getEmailStateSynchronizer().logoutEmail();
    }
    
    static boolean persist() {
        boolean b = false;
        final boolean persist = getPushStateSynchronizer().persist();
        boolean persist2;
        if (persist2 = getEmailStateSynchronizer().persist()) {
            if (getEmailStateSynchronizer().getRegistrationId() != null) {
                persist2 = true;
            }
            else {
                persist2 = false;
            }
        }
        if (persist || persist2) {
            b = true;
        }
        return b;
    }
    
    static void refreshEmailState() {
        getEmailStateSynchronizer().refresh();
    }
    
    static void resetCurrentState() {
        getPushStateSynchronizer().resetCurrentState();
        getEmailStateSynchronizer().resetCurrentState();
        OneSignal.saveUserId(null);
        OneSignal.saveEmailId(null);
        OneSignal.setLastSessionTime(-3660L);
    }
    
    static void sendTags(JSONObject put, final OneSignal.ChangeTagsUpdateHandler changeTagsUpdateHandler) {
        try {
            put = new JSONObject().put("tags", (Object)put);
            getPushStateSynchronizer().sendTags(put, changeTagsUpdateHandler);
            getEmailStateSynchronizer().sendTags(put, changeTagsUpdateHandler);
        }
        catch (JSONException ex) {
            changeTagsUpdateHandler.onFailure(new OneSignal.SendTagsError(-1, "Encountered an error attempting to serialize your tags into JSON: " + ex.getMessage() + "\n" + ex.getStackTrace()));
            ex.printStackTrace();
        }
    }
    
    static void setEmail(final String s, final String s2) {
        getPushStateSynchronizer().setEmail(s, s2);
        getEmailStateSynchronizer().setEmail(s, s2);
    }
    
    static void setExternalUserId(final String s) throws JSONException {
        getPushStateSynchronizer().setExternalUserId(s);
        getEmailStateSynchronizer().setExternalUserId(s);
    }
    
    static void setPermission(final boolean permission) {
        getPushStateSynchronizer().setPermission(permission);
    }
    
    static void setSubscription(final boolean subscription) {
        getPushStateSynchronizer().setSubscription(subscription);
    }
    
    static void setSyncAsNewSession() {
        getPushStateSynchronizer().setSyncAsNewSession();
        getEmailStateSynchronizer().setSyncAsNewSession();
    }
    
    static void setSyncAsNewSessionForEmail() {
        getEmailStateSynchronizer().setSyncAsNewSession();
    }
    
    static void syncHashedEmail(final String s) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("em_m", (Object)OSUtils.hexDigest(s, "MD5"));
            jsonObject.put("em_s", (Object)OSUtils.hexDigest(s, "SHA-1"));
            getPushStateSynchronizer().syncHashedEmail(jsonObject);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    static void syncUserState(final boolean b) {
        getPushStateSynchronizer().syncUserState(b);
        getEmailStateSynchronizer().syncUserState(b);
    }
    
    static void updateDeviceInfo(final JSONObject jsonObject) {
        getPushStateSynchronizer().updateDeviceInfo(jsonObject);
        getEmailStateSynchronizer().updateDeviceInfo(jsonObject);
    }
    
    static void updateLocation(final LocationGMS.LocationPoint locationPoint) {
        getPushStateSynchronizer().updateLocation(locationPoint);
        getEmailStateSynchronizer().updateLocation(locationPoint);
    }
    
    static void updatePushState(final JSONObject jsonObject) {
        getPushStateSynchronizer().updateState(jsonObject);
    }
}
