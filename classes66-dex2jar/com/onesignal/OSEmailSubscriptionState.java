// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import org.json.JSONObject;
import android.support.annotation.NonNull;

public class OSEmailSubscriptionState implements Cloneable
{
    private String emailAddress;
    private String emailUserId;
    OSObservable<Object, OSEmailSubscriptionState> observable;
    
    OSEmailSubscriptionState(final boolean b) {
        this.observable = new OSObservable<Object, OSEmailSubscriptionState>("changed", false);
        if (b) {
            this.emailUserId = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_ONESIGNAL_EMAIL_ID_LAST", null);
            this.emailAddress = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_ONESIGNAL_EMAIL_ADDRESS_LAST", null);
            return;
        }
        this.emailUserId = OneSignal.getEmailId();
        this.emailAddress = OneSignalStateSynchronizer.getEmailStateSynchronizer().getRegistrationId();
    }
    
    void clearEmailAndId() {
        int n;
        if (this.emailUserId != null || this.emailAddress != null) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.emailUserId = null;
        this.emailAddress = null;
        if (n != 0) {
            this.observable.notifyChange(this);
        }
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
    
    boolean compare(final OSEmailSubscriptionState osEmailSubscriptionState) {
        String emailUserId;
        if (this.emailUserId != null) {
            emailUserId = this.emailUserId;
        }
        else {
            emailUserId = "";
        }
        String emailUserId2;
        if (osEmailSubscriptionState.emailUserId != null) {
            emailUserId2 = osEmailSubscriptionState.emailUserId;
        }
        else {
            emailUserId2 = "";
        }
        if (emailUserId.equals(emailUserId2)) {
            String emailAddress;
            if (this.emailAddress != null) {
                emailAddress = this.emailAddress;
            }
            else {
                emailAddress = "";
            }
            String emailAddress2;
            if (osEmailSubscriptionState.emailAddress != null) {
                emailAddress2 = osEmailSubscriptionState.emailAddress;
            }
            else {
                emailAddress2 = "";
            }
            if (emailAddress.equals(emailAddress2)) {
                return false;
            }
        }
        return true;
    }
    
    public String getEmailAddress() {
        return this.emailAddress;
    }
    
    public String getEmailUserId() {
        return this.emailUserId;
    }
    
    public boolean getSubscribed() {
        return this.emailUserId != null && this.emailAddress != null;
    }
    
    void persistAsFrom() {
        OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_ONESIGNAL_EMAIL_ID_LAST", this.emailUserId);
        OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "PREFS_ONESIGNAL_EMAIL_ADDRESS_LAST", this.emailAddress);
    }
    
    void setEmailAddress(@NonNull final String emailAddress) {
        int n;
        if (!emailAddress.equals(this.emailAddress)) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.emailAddress = emailAddress;
        if (n != 0) {
            this.observable.notifyChange(this);
        }
    }
    
    void setEmailUserId(@NonNull final String emailUserId) {
        int n;
        if (!emailUserId.equals(this.emailUserId)) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.emailUserId = emailUserId;
        if (n != 0) {
            this.observable.notifyChange(this);
        }
    }
    
    public JSONObject toJSONObject() {
        while (true) {
            final JSONObject jsonObject = new JSONObject();
            while (true) {
                try {
                    if (this.emailUserId != null) {
                        jsonObject.put("emailUserId", (Object)this.emailUserId);
                    }
                    else {
                        jsonObject.put("emailUserId", JSONObject.NULL);
                    }
                    if (this.emailAddress != null) {
                        jsonObject.put("emailAddress", (Object)this.emailAddress);
                        jsonObject.put("subscribed", this.getSubscribed());
                        return jsonObject;
                    }
                }
                catch (Throwable t) {
                    t.printStackTrace();
                    return jsonObject;
                }
                jsonObject.put("emailAddress", JSONObject.NULL);
                continue;
            }
        }
    }
    
    @Override
    public String toString() {
        return this.toJSONObject().toString();
    }
}
