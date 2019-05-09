package com.onesignal;

import com.tapjoy.TapjoyConstants;
import org.json.JSONException;
import org.json.JSONObject;

class UserStateEmailSynchronizer extends UserStateSynchronizer {
    UserStateEmailSynchronizer() {
    }

    protected UserState newUserState(String inPersistKey, boolean load) {
        return new UserStateEmail(inPersistKey, load);
    }

    boolean getSubscribed() {
        return false;
    }

    GetTagsResult getTags(boolean fromServer) {
        return null;
    }

    void setSubscription(boolean enable) {
    }

    public boolean getUserSubscribePreference() {
        return false;
    }

    public void setPermission(boolean enable) {
    }

    void updateState(JSONObject state) {
    }

    void refresh() {
        scheduleSyncToServer();
    }

    protected void scheduleSyncToServer() {
        boolean neverEmail;
        if (getId() == null && getRegistrationId() == null) {
            neverEmail = true;
        } else {
            neverEmail = false;
        }
        if (!neverEmail && OneSignal.getUserId() != null) {
            getNetworkHandlerThread(Integer.valueOf(0)).runNewJobDelayed();
        }
    }

    void setEmail(String email, String emailAuthHash) {
        boolean noChange;
        String existingEmail;
        JSONObject emailJSON;
        JSONObject syncValues = getUserStateForModification().syncValues;
        if (email.equals(syncValues.optString("identifier"))) {
            Object obj;
            String optString = syncValues.optString("email_auth_hash");
            if (emailAuthHash == null) {
                obj = "";
            } else {
                String str = emailAuthHash;
            }
            if (optString.equals(obj)) {
                noChange = true;
                if (noChange) {
                    existingEmail = syncValues.optString("identifier", null);
                    if (existingEmail == null) {
                        setSyncAsNewSession();
                    }
                    try {
                        emailJSON = new JSONObject();
                        emailJSON.put("identifier", email);
                        if (emailAuthHash != null) {
                            emailJSON.put("email_auth_hash", emailAuthHash);
                        }
                        if (!(emailAuthHash != null || existingEmail == null || existingEmail.equals(email))) {
                            OneSignal.saveEmailId("");
                            resetCurrentState();
                            setSyncAsNewSession();
                        }
                        generateJsonDiff(syncValues, emailJSON, syncValues, null);
                        scheduleSyncToServer();
                        return;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                OneSignal.fireEmailUpdateSuccess();
            }
        }
        noChange = false;
        if (noChange) {
            existingEmail = syncValues.optString("identifier", null);
            if (existingEmail == null) {
                setSyncAsNewSession();
            }
            emailJSON = new JSONObject();
            emailJSON.put("identifier", email);
            if (emailAuthHash != null) {
                emailJSON.put("email_auth_hash", emailAuthHash);
            }
            OneSignal.saveEmailId("");
            resetCurrentState();
            setSyncAsNewSession();
            generateJsonDiff(syncValues, emailJSON, syncValues, null);
            scheduleSyncToServer();
            return;
        }
        OneSignal.fireEmailUpdateSuccess();
    }

    protected String getId() {
        return OneSignal.getEmailId();
    }

    void updateIdDependents(String id) {
        OneSignal.updateEmailIdDependents(id);
    }

    protected void addOnSessionOrCreateExtras(JSONObject jsonBody) {
        try {
            jsonBody.put(TapjoyConstants.TJC_DEVICE_TYPE_NAME, 11);
            jsonBody.putOpt("device_player_id", OneSignal.getUserId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void logoutEmail() {
        OneSignal.saveEmailId("");
        resetCurrentState();
        getToSyncUserState().syncValues.remove("identifier");
        this.toSyncUserState.syncValues.remove("email_auth_hash");
        this.toSyncUserState.syncValues.remove("device_player_id");
        this.toSyncUserState.persistState();
        OneSignal.getPermissionSubscriptionState().emailSubscriptionStatus.clearEmailAndId();
    }

    protected void fireEventsForUpdateFailure(JSONObject jsonFields) {
        if (jsonFields.has("identifier")) {
            OneSignal.fireEmailUpdateFailure();
        }
    }

    protected void onSuccessfulSync(JSONObject jsonFields) {
        if (jsonFields.has("identifier")) {
            OneSignal.fireEmailUpdateSuccess();
        }
    }
}
