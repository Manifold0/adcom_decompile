package com.onesignal;

import com.tapjoy.TapjoyConstants;
import org.json.JSONException;
import org.json.JSONObject;

class UserStatePushSynchronizer extends UserStateSynchronizer {
    private static boolean serverSuccess;

    /* renamed from: com.onesignal.UserStatePushSynchronizer$1 */
    class C13581 extends ResponseHandler {
        C13581() {
        }

        void onSuccess(String responseStr) {
            UserStatePushSynchronizer.serverSuccess = true;
            try {
                JSONObject lastGetTagsResponse = new JSONObject(responseStr);
                if (lastGetTagsResponse.has("tags")) {
                    synchronized (UserStatePushSynchronizer.this.syncLock) {
                        JSONObject dependDiff = UserStatePushSynchronizer.this.generateJsonDiff(UserStatePushSynchronizer.this.currentUserState.syncValues.optJSONObject("tags"), UserStatePushSynchronizer.this.getToSyncUserState().syncValues.optJSONObject("tags"), null, null);
                        UserStatePushSynchronizer.this.currentUserState.syncValues.put("tags", lastGetTagsResponse.optJSONObject("tags"));
                        UserStatePushSynchronizer.this.currentUserState.persistState();
                        UserStatePushSynchronizer.this.getToSyncUserState().mergeTags(lastGetTagsResponse, dependDiff);
                        UserStatePushSynchronizer.this.getToSyncUserState().persistState();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    UserStatePushSynchronizer() {
    }

    protected UserState newUserState(String inPersistKey, boolean load) {
        return new UserStatePush(inPersistKey, load);
    }

    boolean getSubscribed() {
        return getToSyncUserState().isSubscribed();
    }

    GetTagsResult getTags(boolean fromServer) {
        GetTagsResult getTagsResult;
        if (fromServer) {
            String userId = OneSignal.getUserId();
            OneSignalRestClient.getSync("players/" + userId + "?app_id=" + OneSignal.getSavedAppId(), new C13581());
        }
        synchronized (this.syncLock) {
            getTagsResult = new GetTagsResult(serverSuccess, JSONUtils.getJSONObjectWithoutBlankValues(this.toSyncUserState.syncValues, "tags"));
        }
        return getTagsResult;
    }

    protected void scheduleSyncToServer() {
        getNetworkHandlerThread(Integer.valueOf(0)).runNewJobDelayed();
    }

    void updateState(JSONObject pushState) {
        try {
            JSONObject syncUpdate = new JSONObject();
            syncUpdate.putOpt("identifier", pushState.optString("identifier", null));
            if (pushState.has(TapjoyConstants.TJC_DEVICE_TYPE_NAME)) {
                syncUpdate.put(TapjoyConstants.TJC_DEVICE_TYPE_NAME, pushState.optInt(TapjoyConstants.TJC_DEVICE_TYPE_NAME));
            }
            syncUpdate.putOpt("parent_player_id", pushState.optString("parent_player_id", null));
            JSONObject toSync = getUserStateForModification().syncValues;
            generateJsonDiff(toSync, syncUpdate, toSync, null);
        } catch (JSONException t) {
            t.printStackTrace();
        }
        try {
            JSONObject dependUpdate = new JSONObject();
            if (pushState.has("subscribableStatus")) {
                dependUpdate.put("subscribableStatus", pushState.optInt("subscribableStatus"));
            }
            if (pushState.has("androidPermission")) {
                dependUpdate.put("androidPermission", pushState.optBoolean("androidPermission"));
            }
            JSONObject dependValues = getUserStateForModification().dependValues;
            generateJsonDiff(dependValues, dependUpdate, dependValues, null);
        } catch (JSONException t2) {
            t2.printStackTrace();
        }
    }

    void setEmail(String email, String emailAuthHash) {
        try {
            UserState userState = getUserStateForModification();
            userState.dependValues.put("email_auth_hash", emailAuthHash);
            JSONObject syncValues = userState.syncValues;
            generateJsonDiff(syncValues, new JSONObject().put("email", email), syncValues, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void setSubscription(boolean enable) {
        try {
            getUserStateForModification().dependValues.put("userSubscribePref", enable);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean getUserSubscribePreference() {
        return getToSyncUserState().dependValues.optBoolean("userSubscribePref", true);
    }

    public void setPermission(boolean enable) {
        try {
            getUserStateForModification().dependValues.put("androidPermission", enable);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected String getId() {
        return OneSignal.getUserId();
    }

    void updateIdDependents(String id) {
        OneSignal.updateUserIdDependents(id);
    }

    protected void addOnSessionOrCreateExtras(JSONObject jsonBody) {
    }

    void logoutEmail() {
        try {
            getUserStateForModification().dependValues.put("logoutEmail", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void fireEventsForUpdateFailure(JSONObject jsonFields) {
        if (jsonFields.has("email")) {
            OneSignal.fireEmailUpdateFailure();
        }
    }

    protected void onSuccessfulSync(JSONObject jsonFields) {
        if (jsonFields.has("email")) {
            OneSignal.fireEmailUpdateSuccess();
        }
        if (jsonFields.has("identifier")) {
            OneSignal.fireIdsAvailableCallback();
        }
    }
}
