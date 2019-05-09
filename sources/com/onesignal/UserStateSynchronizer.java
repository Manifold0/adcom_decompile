package com.onesignal;

import android.os.Handler;
import android.os.HandlerThread;
import com.google.android.gms.games.Games;
import com.onesignal.OneSignal.ChangeTagsUpdateHandler;
import com.onesignal.OneSignal.LOG_LEVEL;
import com.onesignal.OneSignal.SendTagsError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

abstract class UserStateSynchronizer {
    protected UserState currentUserState;
    private final Object networkHandlerSyncLock = new C13602();
    HashMap<Integer, NetworkHandlerThread> networkHandlerThreads = new HashMap();
    protected boolean nextSyncIsSession = false;
    private AtomicBoolean runningSyncUserState = new AtomicBoolean();
    private ArrayList<ChangeTagsUpdateHandler> sendTagsHandlers = new ArrayList();
    protected final Object syncLock = new C13591();
    protected UserState toSyncUserState;
    protected boolean waitingForSessionResponse = false;

    /* renamed from: com.onesignal.UserStateSynchronizer$1 */
    class C13591 {
        C13591() {
        }
    }

    /* renamed from: com.onesignal.UserStateSynchronizer$2 */
    class C13602 {
        C13602() {
        }
    }

    /* renamed from: com.onesignal.UserStateSynchronizer$3 */
    class C13613 extends ResponseHandler {
        C13613() {
        }

        void onFailure(int statusCode, String response, Throwable throwable) {
            OneSignal.Log(LOG_LEVEL.WARN, "Failed last request. statusCode: " + statusCode + "\nresponse: " + response);
            if (UserStateSynchronizer.this.response400WithErrorsContaining(statusCode, response, "already logged out of email")) {
                UserStateSynchronizer.this.logoutEmailSyncSuccess();
            } else if (UserStateSynchronizer.this.response400WithErrorsContaining(statusCode, response, "not a valid device_type")) {
                UserStateSynchronizer.this.handlePlayerDeletedFromServer();
            } else {
                UserStateSynchronizer.this.handleNetworkFailure();
            }
        }

        void onSuccess(String response) {
            UserStateSynchronizer.this.logoutEmailSyncSuccess();
        }
    }

    static class GetTagsResult {
        JSONObject result;
        boolean serverSuccess;

        GetTagsResult(boolean serverSuccess, JSONObject result) {
            this.serverSuccess = serverSuccess;
            this.result = result;
        }
    }

    class NetworkHandlerThread extends HandlerThread {
        static final int MAX_RETRIES = 3;
        static final int NETWORK_CALL_DELAY_TO_BUFFER_MS = 5000;
        protected static final int NETWORK_HANDLER_USERSTATE = 0;
        int currentRetry;
        Handler mHandler = null;
        int mType;

        /* renamed from: com.onesignal.UserStateSynchronizer$NetworkHandlerThread$1 */
        class C13641 implements Runnable {
            C13641() {
            }

            public void run() {
                if (!UserStateSynchronizer.this.runningSyncUserState.get()) {
                    UserStateSynchronizer.this.syncUserState(false);
                }
            }
        }

        NetworkHandlerThread(int type) {
            super("OSH_NetworkHandlerThread");
            this.mType = type;
            start();
            this.mHandler = new Handler(getLooper());
        }

        void runNewJobDelayed() {
            synchronized (this.mHandler) {
                this.currentRetry = 0;
                this.mHandler.removeCallbacksAndMessages(null);
                this.mHandler.postDelayed(getNewRunnable(), 5000);
            }
        }

        private Runnable getNewRunnable() {
            switch (this.mType) {
                case 0:
                    return new C13641();
                default:
                    return null;
            }
        }

        void stopScheduledRunnable() {
            this.mHandler.removeCallbacksAndMessages(null);
        }

        boolean doRetry() {
            boolean hasMessages;
            boolean doRetry = false;
            synchronized (this.mHandler) {
                if (this.currentRetry < 3) {
                    doRetry = true;
                }
                boolean futureSync = this.mHandler.hasMessages(0);
                if (doRetry && !futureSync) {
                    this.currentRetry++;
                    this.mHandler.postDelayed(getNewRunnable(), (long) (this.currentRetry * 15000));
                }
                hasMessages = this.mHandler.hasMessages(0);
            }
            return hasMessages;
        }
    }

    protected abstract void addOnSessionOrCreateExtras(JSONObject jSONObject);

    protected abstract void fireEventsForUpdateFailure(JSONObject jSONObject);

    protected abstract String getId();

    abstract boolean getSubscribed();

    abstract GetTagsResult getTags(boolean z);

    public abstract boolean getUserSubscribePreference();

    abstract void logoutEmail();

    protected abstract UserState newUserState(String str, boolean z);

    protected abstract void onSuccessfulSync(JSONObject jSONObject);

    protected abstract void scheduleSyncToServer();

    public abstract void setPermission(boolean z);

    abstract void setSubscription(boolean z);

    abstract void updateIdDependents(String str);

    abstract void updateState(JSONObject jSONObject);

    UserStateSynchronizer() {
    }

    String getRegistrationId() {
        return getToSyncUserState().syncValues.optString("identifier", null);
    }

    protected JSONObject generateJsonDiff(JSONObject cur, JSONObject changedTo, JSONObject baseOutput, Set<String> includeFields) {
        JSONObject generateJsonDiff;
        synchronized (this.syncLock) {
            generateJsonDiff = JSONUtils.generateJsonDiff(cur, changedTo, baseOutput, includeFields);
        }
        return generateJsonDiff;
    }

    protected UserState getToSyncUserState() {
        synchronized (this.syncLock) {
            if (this.toSyncUserState == null) {
                this.toSyncUserState = newUserState("TOSYNC_STATE", true);
            }
        }
        return this.toSyncUserState;
    }

    void initUserState() {
        synchronized (this.syncLock) {
            if (this.currentUserState == null) {
                this.currentUserState = newUserState("CURRENT_STATE", true);
            }
        }
        getToSyncUserState();
    }

    void clearLocation() {
        getToSyncUserState().clearLocation();
        getToSyncUserState().persistState();
    }

    boolean persist() {
        boolean unSynced = false;
        if (this.toSyncUserState != null) {
            synchronized (this.syncLock) {
                if (this.currentUserState.generateJsonDiff(this.toSyncUserState, isSessionCall()) != null) {
                    unSynced = true;
                }
                this.toSyncUserState.persistState();
            }
        }
        return unSynced;
    }

    private boolean isSessionCall() {
        return this.nextSyncIsSession && !this.waitingForSessionResponse;
    }

    private boolean syncEmailLogout() {
        return getToSyncUserState().dependValues.optBoolean("logoutEmail", false);
    }

    void syncUserState(boolean fromSyncService) {
        this.runningSyncUserState.set(true);
        internalSyncUserState(fromSyncService);
        this.runningSyncUserState.set(false);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void internalSyncUserState(boolean r11) {
        /*
        r10 = this;
        r4 = r10.getId();
        r5 = r10.syncEmailLogout();
        if (r5 == 0) goto L_0x0010;
    L_0x000a:
        if (r4 == 0) goto L_0x0010;
    L_0x000c:
        r10.doEmailLogout(r4);
    L_0x000f:
        return;
    L_0x0010:
        r5 = r10.currentUserState;
        if (r5 != 0) goto L_0x0017;
    L_0x0014:
        r10.initUserState();
    L_0x0017:
        r2 = r10.isSessionCall();
        r6 = r10.syncLock;
        monitor-enter(r6);
        r5 = r10.currentUserState;	 Catch:{ all -> 0x005f }
        r7 = r10.getToSyncUserState();	 Catch:{ all -> 0x005f }
        r3 = r5.generateJsonDiff(r7, r2);	 Catch:{ all -> 0x005f }
        r5 = r10.currentUserState;	 Catch:{ all -> 0x005f }
        r5 = r5.dependValues;	 Catch:{ all -> 0x005f }
        r7 = r10.getToSyncUserState();	 Catch:{ all -> 0x005f }
        r7 = r7.dependValues;	 Catch:{ all -> 0x005f }
        r8 = 0;
        r9 = 0;
        r0 = r10.generateJsonDiff(r5, r7, r8, r9);	 Catch:{ all -> 0x005f }
        if (r3 != 0) goto L_0x0069;
    L_0x003a:
        r5 = r10.currentUserState;	 Catch:{ all -> 0x005f }
        r7 = 0;
        r5.persistStateAfterSync(r0, r7);	 Catch:{ all -> 0x005f }
        r5 = r10.sendTagsHandlers;	 Catch:{ all -> 0x005f }
        r5 = r5.iterator();	 Catch:{ all -> 0x005f }
    L_0x0046:
        r7 = r5.hasNext();	 Catch:{ all -> 0x005f }
        if (r7 == 0) goto L_0x0062;
    L_0x004c:
        r1 = r5.next();	 Catch:{ all -> 0x005f }
        r1 = (com.onesignal.OneSignal.ChangeTagsUpdateHandler) r1;	 Catch:{ all -> 0x005f }
        if (r1 == 0) goto L_0x0046;
    L_0x0054:
        r7 = 0;
        r7 = com.onesignal.OneSignalStateSynchronizer.getTags(r7);	 Catch:{ all -> 0x005f }
        r7 = r7.result;	 Catch:{ all -> 0x005f }
        r1.onSuccess(r7);	 Catch:{ all -> 0x005f }
        goto L_0x0046;
    L_0x005f:
        r5 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x005f }
        throw r5;
    L_0x0062:
        r5 = r10.sendTagsHandlers;	 Catch:{ all -> 0x005f }
        r5.clear();	 Catch:{ all -> 0x005f }
        monitor-exit(r6);	 Catch:{ all -> 0x005f }
        goto L_0x000f;
    L_0x0069:
        r5 = r10.getToSyncUserState();	 Catch:{ all -> 0x005f }
        r5.persistState();	 Catch:{ all -> 0x005f }
        monitor-exit(r6);	 Catch:{ all -> 0x005f }
        if (r2 == 0) goto L_0x0075;
    L_0x0073:
        if (r11 == 0) goto L_0x0079;
    L_0x0075:
        r10.doPutSync(r4, r3, r0);
        goto L_0x000f;
    L_0x0079:
        r10.doCreateOrNewSession(r4, r3, r0);
        goto L_0x000f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.UserStateSynchronizer.internalSyncUserState(boolean):void");
    }

    private void doEmailLogout(String userId) {
        String urlStr = "players/" + userId + "/email_logout";
        JSONObject jsonBody = new JSONObject();
        try {
            JSONObject dependValues = this.currentUserState.dependValues;
            if (dependValues.has("email_auth_hash")) {
                jsonBody.put("email_auth_hash", dependValues.optString("email_auth_hash"));
            }
            JSONObject syncValues = this.currentUserState.syncValues;
            if (syncValues.has("parent_player_id")) {
                jsonBody.put("parent_player_id", syncValues.optString("parent_player_id"));
            }
            jsonBody.put("app_id", syncValues.optString("app_id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OneSignalRestClient.postSync(urlStr, jsonBody, new C13613());
    }

    private void logoutEmailSyncSuccess() {
        getToSyncUserState().dependValues.remove("logoutEmail");
        this.toSyncUserState.dependValues.remove("email_auth_hash");
        this.toSyncUserState.syncValues.remove("parent_player_id");
        this.toSyncUserState.persistState();
        this.currentUserState.dependValues.remove("email_auth_hash");
        this.currentUserState.syncValues.remove("parent_player_id");
        String emailLoggedOut = this.currentUserState.syncValues.optString("email");
        this.currentUserState.syncValues.remove("email");
        OneSignalStateSynchronizer.setSyncAsNewSessionForEmail();
        OneSignal.Log(LOG_LEVEL.INFO, "Device successfully logged out of email: " + emailLoggedOut);
        OneSignal.handleSuccessfulEmailLogout();
    }

    private void doPutSync(String userId, final JSONObject jsonBody, final JSONObject dependDiff) {
        if (userId == null) {
            Iterator it = this.sendTagsHandlers.iterator();
            while (it.hasNext()) {
                ChangeTagsUpdateHandler handler = (ChangeTagsUpdateHandler) it.next();
                if (handler != null) {
                    handler.onFailure(new SendTagsError(-1, "Unable to update tags: the current user is not registered with OneSignal"));
                }
            }
            this.sendTagsHandlers.clear();
            return;
        }
        final ArrayList<ChangeTagsUpdateHandler> tagsHandlers = (ArrayList) this.sendTagsHandlers.clone();
        this.sendTagsHandlers.clear();
        OneSignalRestClient.putSync("players/" + userId, jsonBody, new ResponseHandler() {
            void onFailure(int statusCode, String response, Throwable throwable) {
                OneSignal.Log(LOG_LEVEL.WARN, "Failed last request. statusCode: " + statusCode + "\nresponse: " + response);
                synchronized (UserStateSynchronizer.this.syncLock) {
                    if (UserStateSynchronizer.this.response400WithErrorsContaining(statusCode, response, "No user with this id found")) {
                        UserStateSynchronizer.this.handlePlayerDeletedFromServer();
                    } else {
                        UserStateSynchronizer.this.handleNetworkFailure();
                    }
                }
                if (jsonBody.has("tags")) {
                    Iterator it = tagsHandlers.iterator();
                    while (it.hasNext()) {
                        ChangeTagsUpdateHandler handler = (ChangeTagsUpdateHandler) it.next();
                        if (handler != null) {
                            handler.onFailure(new SendTagsError(statusCode, response));
                        }
                    }
                }
            }

            void onSuccess(String response) {
                synchronized (UserStateSynchronizer.this.syncLock) {
                    UserStateSynchronizer.this.currentUserState.persistStateAfterSync(dependDiff, jsonBody);
                    UserStateSynchronizer.this.onSuccessfulSync(jsonBody);
                }
                JSONObject tags = OneSignalStateSynchronizer.getTags(false).result;
                if (jsonBody.has("tags") && tags != null) {
                    Iterator it = tagsHandlers.iterator();
                    while (it.hasNext()) {
                        ChangeTagsUpdateHandler handler = (ChangeTagsUpdateHandler) it.next();
                        if (handler != null) {
                            handler.onSuccess(tags);
                        }
                    }
                }
            }
        });
    }

    private void doCreateOrNewSession(final String userId, final JSONObject jsonBody, final JSONObject dependDiff) {
        String urlStr;
        if (userId == null) {
            urlStr = Games.EXTRA_PLAYER_IDS;
        } else {
            urlStr = "players/" + userId + "/on_session";
        }
        this.waitingForSessionResponse = true;
        addOnSessionOrCreateExtras(jsonBody);
        OneSignalRestClient.postSync(urlStr, jsonBody, new ResponseHandler() {
            void onFailure(int statusCode, String response, Throwable throwable) {
                synchronized (UserStateSynchronizer.this.syncLock) {
                    UserStateSynchronizer.this.waitingForSessionResponse = false;
                    OneSignal.Log(LOG_LEVEL.WARN, "Failed last request. statusCode: " + statusCode + "\nresponse: " + response);
                    if (UserStateSynchronizer.this.response400WithErrorsContaining(statusCode, response, "not a valid device_type")) {
                        UserStateSynchronizer.this.handlePlayerDeletedFromServer();
                    } else {
                        UserStateSynchronizer.this.handleNetworkFailure();
                    }
                }
            }

            void onSuccess(String response) {
                synchronized (UserStateSynchronizer.this.syncLock) {
                    UserStateSynchronizer userStateSynchronizer = UserStateSynchronizer.this;
                    UserStateSynchronizer.this.waitingForSessionResponse = false;
                    userStateSynchronizer.nextSyncIsSession = false;
                    UserStateSynchronizer.this.currentUserState.persistStateAfterSync(dependDiff, jsonBody);
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (jsonResponse.has("id")) {
                            String newUserId = jsonResponse.optString("id");
                            UserStateSynchronizer.this.updateIdDependents(newUserId);
                            OneSignal.Log(LOG_LEVEL.INFO, "Device registered, UserId = " + newUserId);
                        } else {
                            OneSignal.Log(LOG_LEVEL.INFO, "session sent, UserId = " + userId);
                        }
                        OneSignal.updateOnSessionDependents();
                        UserStateSynchronizer.this.onSuccessfulSync(jsonBody);
                    } catch (Throwable t) {
                        OneSignal.Log(LOG_LEVEL.ERROR, "ERROR parsing on_session or create JSON Response.", t);
                    }
                }
            }
        });
    }

    private void handleNetworkFailure() {
        if (!getNetworkHandlerThread(Integer.valueOf(0)).doRetry()) {
            JSONObject jsonBody = this.currentUserState.generateJsonDiff(this.toSyncUserState, false);
            if (jsonBody != null) {
                fireEventsForUpdateFailure(jsonBody);
            }
            if (getToSyncUserState().dependValues.optBoolean("logoutEmail", false)) {
                OneSignal.handleFailedEmailLogout();
            }
        }
    }

    private boolean response400WithErrorsContaining(int statusCode, String response, String contains) {
        if (statusCode != 400 || response == null) {
            return false;
        }
        try {
            JSONObject responseJson = new JSONObject(response);
            if (responseJson.has("errors") && responseJson.optString("errors").contains(contains)) {
                return true;
            }
            return false;
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }

    protected NetworkHandlerThread getNetworkHandlerThread(Integer type) {
        NetworkHandlerThread networkHandlerThread;
        synchronized (this.networkHandlerSyncLock) {
            if (!this.networkHandlerThreads.containsKey(type)) {
                this.networkHandlerThreads.put(type, new NetworkHandlerThread(type.intValue()));
            }
            networkHandlerThread = (NetworkHandlerThread) this.networkHandlerThreads.get(type);
        }
        return networkHandlerThread;
    }

    protected UserState getUserStateForModification() {
        if (this.toSyncUserState == null) {
            this.toSyncUserState = this.currentUserState.deepClone("TOSYNC_STATE");
        }
        scheduleSyncToServer();
        return this.toSyncUserState;
    }

    void updateDeviceInfo(JSONObject deviceInfo) {
        JSONObject toSync = getUserStateForModification().syncValues;
        generateJsonDiff(toSync, deviceInfo, toSync, null);
    }

    void setSyncAsNewSession() {
        this.nextSyncIsSession = true;
    }

    void sendTags(JSONObject tags, ChangeTagsUpdateHandler handler) {
        this.sendTagsHandlers.add(handler);
        JSONObject userStateTags = getUserStateForModification().syncValues;
        generateJsonDiff(userStateTags, tags, userStateTags, null);
    }

    void syncHashedEmail(JSONObject emailFields) {
        JSONObject syncValues = getUserStateForModification().syncValues;
        generateJsonDiff(syncValues, emailFields, syncValues, null);
    }

    void setExternalUserId(String externalId) throws JSONException {
        getUserStateForModification().syncValues.put("external_user_id", externalId);
    }

    private void handlePlayerDeletedFromServer() {
        OneSignal.handleSuccessfulEmailLogout();
        resetCurrentState();
        this.nextSyncIsSession = true;
        scheduleSyncToServer();
    }

    void resetCurrentState() {
        this.currentUserState.syncValues = new JSONObject();
        this.currentUserState.persistState();
    }

    void updateLocation(LocationPoint point) {
        getUserStateForModification().setLocation(point);
    }
}
