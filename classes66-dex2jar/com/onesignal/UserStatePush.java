// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import org.json.JSONException;

class UserStatePush extends UserState
{
    UserStatePush(final String s, final boolean b) {
        super(s, b);
    }
    
    private int getNotificationTypes() {
        final int optInt = this.dependValues.optInt("subscribableStatus", 1);
        if (optInt < -2) {
            return optInt;
        }
        if (!this.dependValues.optBoolean("androidPermission", true)) {
            return 0;
        }
        if (!this.dependValues.optBoolean("userSubscribePref", true)) {
            return -2;
        }
        return 1;
    }
    
    @Override
    protected void addDependFields() {
        try {
            this.syncValues.put("notification_types", this.getNotificationTypes());
        }
        catch (JSONException ex) {}
    }
    
    @Override
    boolean isSubscribed() {
        return this.getNotificationTypes() > 0;
    }
    
    @Override
    UserState newInstance(final String s) {
        return new UserStatePush(s, false);
    }
}
