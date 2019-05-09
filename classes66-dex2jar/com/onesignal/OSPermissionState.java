// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import org.json.JSONObject;

public class OSPermissionState implements Cloneable
{
    private boolean enabled;
    OSObservable<Object, OSPermissionState> observable;
    
    OSPermissionState(final boolean b) {
        this.observable = new OSObservable<Object, OSPermissionState>("changed", false);
        if (b) {
            this.enabled = OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_ACCEPTED_NOTIFICATION_LAST", false);
            return;
        }
        this.refreshAsTo();
    }
    
    private void setEnabled(final boolean enabled) {
        int n;
        if (this.enabled != enabled) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.enabled = enabled;
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
    
    boolean compare(final OSPermissionState osPermissionState) {
        return this.enabled != osPermissionState.enabled;
    }
    
    public boolean getEnabled() {
        return this.enabled;
    }
    
    void persistAsFrom() {
        OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_ACCEPTED_NOTIFICATION_LAST", this.enabled);
    }
    
    void refreshAsTo() {
        this.setEnabled(OSUtils.areNotificationsEnabled(OneSignal.appContext));
    }
    
    public JSONObject toJSONObject() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("enabled", this.enabled);
            return jsonObject;
        }
        catch (Throwable t) {
            t.printStackTrace();
            return jsonObject;
        }
    }
    
    @Override
    public String toString() {
        return this.toJSONObject().toString();
    }
}
