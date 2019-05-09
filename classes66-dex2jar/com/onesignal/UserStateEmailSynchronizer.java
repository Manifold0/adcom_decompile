// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class UserStateEmailSynchronizer extends UserStateSynchronizer
{
    @Override
    protected void addOnSessionOrCreateExtras(final JSONObject jsonObject) {
        try {
            jsonObject.put("device_type", 11);
            jsonObject.putOpt("device_player_id", (Object)OneSignal.getUserId());
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    protected void fireEventsForUpdateFailure(final JSONObject jsonObject) {
        if (jsonObject.has("identifier")) {
            OneSignal.fireEmailUpdateFailure();
        }
    }
    
    @Override
    protected String getId() {
        return OneSignal.getEmailId();
    }
    
    @Override
    boolean getSubscribed() {
        return false;
    }
    
    @Override
    GetTagsResult getTags(final boolean b) {
        return null;
    }
    
    @Override
    public boolean getUserSubscribePreference() {
        return false;
    }
    
    @Override
    void logoutEmail() {
        OneSignal.saveEmailId("");
        this.resetCurrentState();
        this.getToSyncUserState().syncValues.remove("identifier");
        this.toSyncUserState.syncValues.remove("email_auth_hash");
        this.toSyncUserState.syncValues.remove("device_player_id");
        this.toSyncUserState.persistState();
        OneSignal.getPermissionSubscriptionState().emailSubscriptionStatus.clearEmailAndId();
    }
    
    @Override
    protected UserState newUserState(final String s, final boolean b) {
        return new UserStateEmail(s, b);
    }
    
    @Override
    protected void onSuccessfulSync(final JSONObject jsonObject) {
        if (jsonObject.has("identifier")) {
            OneSignal.fireEmailUpdateSuccess();
        }
    }
    
    void refresh() {
        this.scheduleSyncToServer();
    }
    
    @Override
    protected void scheduleSyncToServer() {
        boolean b;
        if (this.getId() == null && this.getRegistrationId() == null) {
            b = true;
        }
        else {
            b = false;
        }
        if (b || OneSignal.getUserId() == null) {
            return;
        }
        this.getNetworkHandlerThread(0).runNewJobDelayed();
    }
    
    void setEmail(final String s, final String s2) {
        final JSONObject syncValues = this.getUserStateForModification().syncValues;
        while (true) {
            Label_0066: {
                if (!s.equals(syncValues.optString("identifier"))) {
                    break Label_0066;
                }
                final String optString = syncValues.optString("email_auth_hash");
                String s3;
                if (s2 == null) {
                    s3 = "";
                }
                else {
                    s3 = s2;
                }
                if (!optString.equals(s3)) {
                    break Label_0066;
                }
                final int n = 1;
                if (n != 0) {
                    OneSignal.fireEmailUpdateSuccess();
                    return;
                }
                final String optString2 = syncValues.optString("identifier", (String)null);
                if (optString2 == null) {
                    this.setSyncAsNewSession();
                }
                try {
                    final JSONObject jsonObject = new JSONObject();
                    jsonObject.put("identifier", (Object)s);
                    if (s2 != null) {
                        jsonObject.put("email_auth_hash", (Object)s2);
                    }
                    if (s2 == null && optString2 != null && !optString2.equals(s)) {
                        OneSignal.saveEmailId("");
                        this.resetCurrentState();
                        this.setSyncAsNewSession();
                    }
                    this.generateJsonDiff(syncValues, jsonObject, syncValues, null);
                    this.scheduleSyncToServer();
                    return;
                }
                catch (JSONException ex) {
                    ex.printStackTrace();
                    return;
                }
            }
            final int n = 0;
            continue;
        }
    }
    
    @Override
    public void setPermission(final boolean b) {
    }
    
    @Override
    void setSubscription(final boolean b) {
    }
    
    @Override
    void updateIdDependents(final String s) {
        OneSignal.updateEmailIdDependents(s);
    }
    
    @Override
    void updateState(final JSONObject jsonObject) {
    }
}
