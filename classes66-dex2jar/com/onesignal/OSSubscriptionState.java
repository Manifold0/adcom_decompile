// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import org.json.JSONObject;

public class OSSubscriptionState implements Cloneable
{
    private boolean accepted;
    OSObservable<Object, OSSubscriptionState> observable;
    private String pushToken;
    private String userId;
    private boolean userSubscriptionSetting;
    
    OSSubscriptionState(final boolean b, final boolean accepted) {
        this.observable = new OSObservable<Object, OSSubscriptionState>("changed", false);
        if (b) {
            this.userSubscriptionSetting = OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_SUBSCRIPTION_LAST", false);
            this.userId = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_PLAYER_ID_LAST", null);
            this.pushToken = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_PUSH_TOKEN_LAST", null);
            this.accepted = OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_PERMISSION_ACCEPTED_LAST", false);
            return;
        }
        this.userSubscriptionSetting = OneSignalStateSynchronizer.getUserSubscribePreference();
        this.userId = OneSignal.getUserId();
        this.pushToken = OneSignalStateSynchronizer.getRegistrationId();
        this.accepted = accepted;
    }
    
    private void setAccepted(final boolean accepted) {
        final boolean subscribed = this.getSubscribed();
        this.accepted = accepted;
        if (subscribed != this.getSubscribed()) {
            this.observable.notifyChange(this);
        }
    }
    
    void changed(final OSPermissionState osPermissionState) {
        this.setAccepted(osPermissionState.getEnabled());
    }
    
    @Override
    protected Object clone() {
        try {
            return super.clone();
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    boolean compare(final OSSubscriptionState osSubscriptionState) {
        if (this.userSubscriptionSetting == osSubscriptionState.userSubscriptionSetting) {
            String userId;
            if (this.userId != null) {
                userId = this.userId;
            }
            else {
                userId = "";
            }
            String userId2;
            if (osSubscriptionState.userId != null) {
                userId2 = osSubscriptionState.userId;
            }
            else {
                userId2 = "";
            }
            if (userId.equals(userId2)) {
                String pushToken;
                if (this.pushToken != null) {
                    pushToken = this.pushToken;
                }
                else {
                    pushToken = "";
                }
                String pushToken2;
                if (osSubscriptionState.pushToken != null) {
                    pushToken2 = osSubscriptionState.pushToken;
                }
                else {
                    pushToken2 = "";
                }
                if (pushToken.equals(pushToken2) && this.accepted == osSubscriptionState.accepted) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public String getPushToken() {
        return this.pushToken;
    }
    
    public boolean getSubscribed() {
        return this.userId != null && this.pushToken != null && this.userSubscriptionSetting && this.accepted;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public boolean getUserSubscriptionSetting() {
        return this.userSubscriptionSetting;
    }
    
    void persistAsFrom() {
        OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_SUBSCRIPTION_LAST", this.userSubscriptionSetting);
        OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_PLAYER_ID_LAST", this.userId);
        OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_PUSH_TOKEN_LAST", this.pushToken);
        OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_PERMISSION_ACCEPTED_LAST", this.accepted);
    }
    
    void setPushToken(final String pushToken) {
        if (pushToken != null) {
            int n;
            if (!pushToken.equals(this.pushToken)) {
                n = 1;
            }
            else {
                n = 0;
            }
            this.pushToken = pushToken;
            if (n != 0) {
                this.observable.notifyChange(this);
            }
        }
    }
    
    void setUserId(final String userId) {
        int n;
        if (!userId.equals(this.userId)) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.userId = userId;
        if (n != 0) {
            this.observable.notifyChange(this);
        }
    }
    
    void setUserSubscriptionSetting(final boolean userSubscriptionSetting) {
        int n;
        if (this.userSubscriptionSetting != userSubscriptionSetting) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.userSubscriptionSetting = userSubscriptionSetting;
        if (n != 0) {
            this.observable.notifyChange(this);
        }
    }
    
    public JSONObject toJSONObject() {
        while (true) {
            final JSONObject jsonObject = new JSONObject();
            while (true) {
                try {
                    if (this.userId != null) {
                        jsonObject.put("userId", (Object)this.userId);
                    }
                    else {
                        jsonObject.put("userId", JSONObject.NULL);
                    }
                    if (this.pushToken != null) {
                        jsonObject.put("pushToken", (Object)this.pushToken);
                        jsonObject.put("userSubscriptionSetting", this.userSubscriptionSetting);
                        jsonObject.put("subscribed", this.getSubscribed());
                        return jsonObject;
                    }
                }
                catch (Throwable t) {
                    t.printStackTrace();
                    return jsonObject;
                }
                jsonObject.put("pushToken", JSONObject.NULL);
                continue;
            }
        }
    }
    
    @Override
    public String toString() {
        return this.toJSONObject().toString();
    }
}
