// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.Set;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.HashMap;

abstract class UserStateSynchronizer
{
    protected UserState currentUserState;
    private final Object networkHandlerSyncLock;
    HashMap<Integer, NetworkHandlerThread> networkHandlerThreads;
    protected boolean nextSyncIsSession;
    private AtomicBoolean runningSyncUserState;
    private ArrayList<OneSignal.ChangeTagsUpdateHandler> sendTagsHandlers;
    protected final Object syncLock;
    protected UserState toSyncUserState;
    protected boolean waitingForSessionResponse;
    
    UserStateSynchronizer() {
        this.syncLock = new Object() {};
        this.runningSyncUserState = new AtomicBoolean();
        this.sendTagsHandlers = new ArrayList<OneSignal.ChangeTagsUpdateHandler>();
        this.networkHandlerThreads = new HashMap<Integer, NetworkHandlerThread>();
        this.networkHandlerSyncLock = new Object() {};
        this.nextSyncIsSession = false;
        this.waitingForSessionResponse = false;
    }
    
    private void doCreateOrNewSession(final String s, final JSONObject jsonObject, final JSONObject jsonObject2) {
        String string;
        if (s == null) {
            string = "players";
        }
        else {
            string = "players/" + s + "/on_session";
        }
        this.waitingForSessionResponse = true;
        this.addOnSessionOrCreateExtras(jsonObject);
        OneSignalRestClient.postSync(string, jsonObject, (OneSignalRestClient.ResponseHandler)new OneSignalRestClient.ResponseHandler() {
            @Override
            void onFailure(final int n, final String s, final Throwable t) {
                synchronized (UserStateSynchronizer.this.syncLock) {
                    UserStateSynchronizer.this.waitingForSessionResponse = false;
                    OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Failed last request. statusCode: " + n + "\nresponse: " + s);
                    if (UserStateSynchronizer.this.response400WithErrorsContaining(n, s, "not a valid device_type")) {
                        UserStateSynchronizer.this.handlePlayerDeletedFromServer();
                    }
                    else {
                        UserStateSynchronizer.this.handleNetworkFailure();
                    }
                }
            }
            
            @Override
            void onSuccess(String optString) {
                synchronized (UserStateSynchronizer.this.syncLock) {
                    final UserStateSynchronizer this$0 = UserStateSynchronizer.this;
                    UserStateSynchronizer.this.waitingForSessionResponse = false;
                    this$0.nextSyncIsSession = false;
                    UserStateSynchronizer.this.currentUserState.persistStateAfterSync(jsonObject2, jsonObject);
                    try {
                        final JSONObject jsonObject = new JSONObject(optString);
                        if (jsonObject.has("id")) {
                            optString = jsonObject.optString("id");
                            UserStateSynchronizer.this.updateIdDependents(optString);
                            OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "Device registered, UserId = " + optString);
                        }
                        else {
                            OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "session sent, UserId = " + s);
                        }
                        OneSignal.updateOnSessionDependents();
                        UserStateSynchronizer.this.onSuccessfulSync(jsonObject);
                    }
                    catch (Throwable t) {
                        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "ERROR parsing on_session or create JSON Response.", t);
                    }
                }
            }
        });
    }
    
    private void doEmailLogout(String string) {
        string = "players/" + string + "/email_logout";
        final JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                final JSONObject dependValues = this.currentUserState.dependValues;
                if (dependValues.has("email_auth_hash")) {
                    jsonObject.put("email_auth_hash", (Object)dependValues.optString("email_auth_hash"));
                }
                final JSONObject syncValues = this.currentUserState.syncValues;
                if (syncValues.has("parent_player_id")) {
                    jsonObject.put("parent_player_id", (Object)syncValues.optString("parent_player_id"));
                }
                jsonObject.put("app_id", (Object)syncValues.optString("app_id"));
                OneSignalRestClient.postSync(string, jsonObject, (OneSignalRestClient.ResponseHandler)new OneSignalRestClient.ResponseHandler() {
                    @Override
                    void onFailure(final int n, final String s, final Throwable t) {
                        OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Failed last request. statusCode: " + n + "\nresponse: " + s);
                        if (UserStateSynchronizer.this.response400WithErrorsContaining(n, s, "already logged out of email")) {
                            UserStateSynchronizer.this.logoutEmailSyncSuccess();
                            return;
                        }
                        if (UserStateSynchronizer.this.response400WithErrorsContaining(n, s, "not a valid device_type")) {
                            UserStateSynchronizer.this.handlePlayerDeletedFromServer();
                            return;
                        }
                        UserStateSynchronizer.this.handleNetworkFailure();
                    }
                    
                    @Override
                    void onSuccess(final String s) {
                        UserStateSynchronizer.this.logoutEmailSyncSuccess();
                    }
                });
            }
            catch (JSONException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    private void doPutSync(final String s, final JSONObject jsonObject, final JSONObject jsonObject2) {
        if (s == null) {
            for (final OneSignal.ChangeTagsUpdateHandler changeTagsUpdateHandler : this.sendTagsHandlers) {
                if (changeTagsUpdateHandler != null) {
                    changeTagsUpdateHandler.onFailure(new OneSignal.SendTagsError(-1, "Unable to update tags: the current user is not registered with OneSignal"));
                }
            }
            this.sendTagsHandlers.clear();
            return;
        }
        final ArrayList list = (ArrayList)this.sendTagsHandlers.clone();
        this.sendTagsHandlers.clear();
        OneSignalRestClient.putSync("players/" + s, jsonObject, (OneSignalRestClient.ResponseHandler)new OneSignalRestClient.ResponseHandler() {
            @Override
            void onFailure(final int n, final String s, Throwable t) {
                OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Failed last request. statusCode: " + n + "\nresponse: " + s);
                t = (Throwable)UserStateSynchronizer.this.syncLock;
                synchronized (t) {
                    if (UserStateSynchronizer.this.response400WithErrorsContaining(n, s, "No user with this id found")) {
                        UserStateSynchronizer.this.handlePlayerDeletedFromServer();
                    }
                    else {
                        UserStateSynchronizer.this.handleNetworkFailure();
                    }
                    // monitorexit(t)
                    if (jsonObject.has("tags")) {
                        t = (Throwable)list.iterator();
                        while (((Iterator)t).hasNext()) {
                            final OneSignal.ChangeTagsUpdateHandler changeTagsUpdateHandler = ((Iterator<OneSignal.ChangeTagsUpdateHandler>)t).next();
                            if (changeTagsUpdateHandler != null) {
                                changeTagsUpdateHandler.onFailure(new OneSignal.SendTagsError(n, s));
                            }
                        }
                    }
                }
            }
            
            @Override
            void onSuccess(String s) {
                s = (String)UserStateSynchronizer.this.syncLock;
                synchronized (s) {
                    UserStateSynchronizer.this.currentUserState.persistStateAfterSync(jsonObject2, jsonObject);
                    UserStateSynchronizer.this.onSuccessfulSync(jsonObject);
                    // monitorexit(s)
                    s = (String)OneSignalStateSynchronizer.getTags(false).result;
                    if (jsonObject.has("tags") && s != null) {
                        for (final OneSignal.ChangeTagsUpdateHandler changeTagsUpdateHandler : list) {
                            if (changeTagsUpdateHandler != null) {
                                changeTagsUpdateHandler.onSuccess((JSONObject)s);
                            }
                        }
                    }
                }
            }
        });
    }
    
    private void handleNetworkFailure() {
        if (!this.getNetworkHandlerThread(0).doRetry()) {
            final JSONObject generateJsonDiff = this.currentUserState.generateJsonDiff(this.toSyncUserState, false);
            if (generateJsonDiff != null) {
                this.fireEventsForUpdateFailure(generateJsonDiff);
            }
            if (this.getToSyncUserState().dependValues.optBoolean("logoutEmail", false)) {
                OneSignal.handleFailedEmailLogout();
            }
        }
    }
    
    private void handlePlayerDeletedFromServer() {
        OneSignal.handleSuccessfulEmailLogout();
        this.resetCurrentState();
        this.nextSyncIsSession = true;
        this.scheduleSyncToServer();
    }
    
    private void internalSyncUserState(final boolean b) {
        final String id = this.getId();
        if (this.syncEmailLogout() && id != null) {
            this.doEmailLogout(id);
            return;
        }
        if (this.currentUserState == null) {
            this.initUserState();
        }
        final boolean sessionCall = this.isSessionCall();
        // monitorexit(o)
        Object generateJsonDiff = null;
        final JSONObject generateJsonDiff2;
        Label_0169: {
            synchronized (this.syncLock) {
                generateJsonDiff = this.currentUserState.generateJsonDiff(this.getToSyncUserState(), sessionCall);
                generateJsonDiff2 = this.generateJsonDiff(this.currentUserState.dependValues, this.getToSyncUserState().dependValues, null, null);
                if (generateJsonDiff != null) {
                    break Label_0169;
                }
                this.currentUserState.persistStateAfterSync(generateJsonDiff2, null);
                final Iterator<OneSignal.ChangeTagsUpdateHandler> iterator = this.sendTagsHandlers.iterator();
                while (iterator.hasNext()) {
                    generateJsonDiff = iterator.next();
                    if (generateJsonDiff != null) {
                        ((OneSignal.ChangeTagsUpdateHandler)generateJsonDiff).onSuccess(OneSignalStateSynchronizer.getTags(false).result);
                    }
                }
            }
            this.sendTagsHandlers.clear();
            return;
        }
        this.getToSyncUserState().persistState();
        // monitorexit(o)
        final String s;
        if (!sessionCall || b) {
            this.doPutSync(s, (JSONObject)generateJsonDiff, generateJsonDiff2);
            return;
        }
        this.doCreateOrNewSession(s, (JSONObject)generateJsonDiff, generateJsonDiff2);
    }
    
    private boolean isSessionCall() {
        return this.nextSyncIsSession && !this.waitingForSessionResponse;
    }
    
    private void logoutEmailSyncSuccess() {
        this.getToSyncUserState().dependValues.remove("logoutEmail");
        this.toSyncUserState.dependValues.remove("email_auth_hash");
        this.toSyncUserState.syncValues.remove("parent_player_id");
        this.toSyncUserState.persistState();
        this.currentUserState.dependValues.remove("email_auth_hash");
        this.currentUserState.syncValues.remove("parent_player_id");
        final String optString = this.currentUserState.syncValues.optString("email");
        this.currentUserState.syncValues.remove("email");
        OneSignalStateSynchronizer.setSyncAsNewSessionForEmail();
        OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "Device successfully logged out of email: " + optString);
        OneSignal.handleSuccessfulEmailLogout();
    }
    
    private boolean response400WithErrorsContaining(final int n, final String s, final String s2) {
        boolean b2;
        final boolean b = b2 = false;
        if (n != 400) {
            return b2;
        }
        b2 = b;
        if (s == null) {
            return b2;
        }
        try {
            final JSONObject jsonObject = new JSONObject(s);
            b2 = b;
            if (jsonObject.has("errors")) {
                final boolean contains = jsonObject.optString("errors").contains(s2);
                b2 = b;
                if (contains) {
                    b2 = true;
                }
            }
            return b2;
        }
        catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }
    
    private boolean syncEmailLogout() {
        return this.getToSyncUserState().dependValues.optBoolean("logoutEmail", false);
    }
    
    protected abstract void addOnSessionOrCreateExtras(final JSONObject p0);
    
    void clearLocation() {
        this.getToSyncUserState().clearLocation();
        this.getToSyncUserState().persistState();
    }
    
    protected abstract void fireEventsForUpdateFailure(final JSONObject p0);
    
    protected JSONObject generateJsonDiff(JSONObject generateJsonDiff, final JSONObject jsonObject, final JSONObject jsonObject2, final Set<String> set) {
        synchronized (this.syncLock) {
            generateJsonDiff = JSONUtils.generateJsonDiff(generateJsonDiff, jsonObject, jsonObject2, set);
            return generateJsonDiff;
        }
    }
    
    protected abstract String getId();
    
    protected NetworkHandlerThread getNetworkHandlerThread(final Integer n) {
        synchronized (this.networkHandlerSyncLock) {
            if (!this.networkHandlerThreads.containsKey(n)) {
                this.networkHandlerThreads.put(n, new NetworkHandlerThread(n));
            }
            return this.networkHandlerThreads.get(n);
        }
    }
    
    String getRegistrationId() {
        return this.getToSyncUserState().syncValues.optString("identifier", (String)null);
    }
    
    abstract boolean getSubscribed();
    
    abstract GetTagsResult getTags(final boolean p0);
    
    protected UserState getToSyncUserState() {
        synchronized (this.syncLock) {
            if (this.toSyncUserState == null) {
                this.toSyncUserState = this.newUserState("TOSYNC_STATE", true);
            }
            // monitorexit(this.syncLock)
            return this.toSyncUserState;
        }
    }
    
    protected UserState getUserStateForModification() {
        if (this.toSyncUserState == null) {
            this.toSyncUserState = this.currentUserState.deepClone("TOSYNC_STATE");
        }
        this.scheduleSyncToServer();
        return this.toSyncUserState;
    }
    
    public abstract boolean getUserSubscribePreference();
    
    void initUserState() {
        synchronized (this.syncLock) {
            if (this.currentUserState == null) {
                this.currentUserState = this.newUserState("CURRENT_STATE", true);
            }
            // monitorexit(this.syncLock)
            this.getToSyncUserState();
        }
    }
    
    abstract void logoutEmail();
    
    protected abstract UserState newUserState(final String p0, final boolean p1);
    
    protected abstract void onSuccessfulSync(final JSONObject p0);
    
    boolean persist() {
        boolean b = false;
        if (this.toSyncUserState != null) {
            synchronized (this.syncLock) {
                if (this.currentUserState.generateJsonDiff(this.toSyncUserState, this.isSessionCall()) != null) {
                    b = true;
                }
                this.toSyncUserState.persistState();
                return b;
            }
        }
        return false;
    }
    
    void resetCurrentState() {
        this.currentUserState.syncValues = new JSONObject();
        this.currentUserState.persistState();
    }
    
    protected abstract void scheduleSyncToServer();
    
    void sendTags(final JSONObject jsonObject, final OneSignal.ChangeTagsUpdateHandler changeTagsUpdateHandler) {
        this.sendTagsHandlers.add(changeTagsUpdateHandler);
        final JSONObject syncValues = this.getUserStateForModification().syncValues;
        this.generateJsonDiff(syncValues, jsonObject, syncValues, null);
    }
    
    void setExternalUserId(final String s) throws JSONException {
        this.getUserStateForModification().syncValues.put("external_user_id", (Object)s);
    }
    
    public abstract void setPermission(final boolean p0);
    
    abstract void setSubscription(final boolean p0);
    
    void setSyncAsNewSession() {
        this.nextSyncIsSession = true;
    }
    
    void syncHashedEmail(final JSONObject jsonObject) {
        final JSONObject syncValues = this.getUserStateForModification().syncValues;
        this.generateJsonDiff(syncValues, jsonObject, syncValues, null);
    }
    
    void syncUserState(final boolean b) {
        this.runningSyncUserState.set(true);
        this.internalSyncUserState(b);
        this.runningSyncUserState.set(false);
    }
    
    void updateDeviceInfo(final JSONObject jsonObject) {
        final JSONObject syncValues = this.getUserStateForModification().syncValues;
        this.generateJsonDiff(syncValues, jsonObject, syncValues, null);
    }
    
    abstract void updateIdDependents(final String p0);
    
    void updateLocation(final LocationGMS.LocationPoint location) {
        this.getUserStateForModification().setLocation(location);
    }
    
    abstract void updateState(final JSONObject p0);
    
    static class GetTagsResult
    {
        JSONObject result;
        boolean serverSuccess;
        
        GetTagsResult(final boolean serverSuccess, final JSONObject result) {
            this.serverSuccess = serverSuccess;
            this.result = result;
        }
    }
    
    class NetworkHandlerThread extends HandlerThread
    {
        static final int MAX_RETRIES = 3;
        static final int NETWORK_CALL_DELAY_TO_BUFFER_MS = 5000;
        protected static final int NETWORK_HANDLER_USERSTATE = 0;
        int currentRetry;
        Handler mHandler;
        int mType;
        
        NetworkHandlerThread(final int mType) {
            super("OSH_NetworkHandlerThread");
            this.mHandler = null;
            this.mType = mType;
            this.start();
            this.mHandler = new Handler(this.getLooper());
        }
        
        private Runnable getNewRunnable() {
            switch (this.mType) {
                default: {
                    return null;
                }
                case 0: {
                    return new Runnable() {
                        @Override
                        public void run() {
                            if (!UserStateSynchronizer.this.runningSyncUserState.get()) {
                                UserStateSynchronizer.this.syncUserState(false);
                            }
                        }
                    };
                }
            }
        }
        
        boolean doRetry() {
            boolean b = false;
            synchronized (this.mHandler) {
                if (this.currentRetry < 3) {
                    b = true;
                }
                final boolean hasMessages = this.mHandler.hasMessages(0);
                if (b && !hasMessages) {
                    ++this.currentRetry;
                    this.mHandler.postDelayed(this.getNewRunnable(), (long)(this.currentRetry * 15000));
                }
                return this.mHandler.hasMessages(0);
            }
        }
        
        void runNewJobDelayed() {
            synchronized (this.mHandler) {
                this.currentRetry = 0;
                this.mHandler.removeCallbacksAndMessages((Object)null);
                this.mHandler.postDelayed(this.getNewRunnable(), 5000L);
            }
        }
        
        void stopScheduledRunnable() {
            this.mHandler.removeCallbacksAndMessages((Object)null);
        }
    }
}
