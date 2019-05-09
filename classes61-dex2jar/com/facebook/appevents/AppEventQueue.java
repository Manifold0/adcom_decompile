// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.appevents;

import java.util.Iterator;
import java.util.ArrayList;
import com.facebook.FacebookRequestError;
import org.json.JSONException;
import com.facebook.internal.Logger;
import com.facebook.LoggingBehavior;
import org.json.JSONArray;
import java.util.Set;
import android.util.Log;
import android.support.v4.content.LocalBroadcastManager;
import java.io.Serializable;
import android.content.Intent;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.FacebookSdk;
import android.os.Bundle;
import org.json.JSONObject;
import com.facebook.AccessToken;
import com.facebook.internal.FetchedAppSettingsManager;
import java.util.concurrent.TimeUnit;
import com.facebook.GraphResponse;
import com.facebook.GraphRequest;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

class AppEventQueue
{
    private static final int FLUSH_PERIOD_IN_SECONDS = 15;
    private static final int NUM_LOG_EVENTS_TO_TRY_TO_FLUSH_AFTER = 100;
    private static final String TAG;
    private static volatile AppEventCollection appEventCollection;
    private static final Runnable flushRunnable;
    private static ScheduledFuture scheduledFuture;
    private static final ScheduledExecutorService singleThreadExecutor;
    
    static {
        TAG = AppEventQueue.class.getName();
        AppEventQueue.appEventCollection = new AppEventCollection();
        singleThreadExecutor = Executors.newSingleThreadScheduledExecutor();
        flushRunnable = new Runnable() {
            @Override
            public void run() {
                AppEventQueue.scheduledFuture = null;
                if (AppEventsLogger.getFlushBehavior() != AppEventsLogger.FlushBehavior.EXPLICIT_ONLY) {
                    AppEventQueue.flushAndWait(FlushReason.TIMER);
                }
            }
        };
    }
    
    public static void add(final AccessTokenAppIdPair accessTokenAppIdPair, final AppEvent appEvent) {
        AppEventQueue.singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                AppEventQueue.appEventCollection.addEvent(accessTokenAppIdPair, appEvent);
                if (AppEventsLogger.getFlushBehavior() != AppEventsLogger.FlushBehavior.EXPLICIT_ONLY && AppEventQueue.appEventCollection.getEventCount() > 100) {
                    AppEventQueue.flushAndWait(FlushReason.EVENT_THRESHOLD);
                }
                else if (AppEventQueue.scheduledFuture == null) {
                    AppEventQueue.scheduledFuture = AppEventQueue.singleThreadExecutor.schedule(AppEventQueue.flushRunnable, 15L, TimeUnit.SECONDS);
                }
            }
        });
    }
    
    private static GraphRequest buildRequestForSession(final AccessTokenAppIdPair accessTokenAppIdPair, final SessionEventsState sessionEventsState, final boolean b, final FlushStatistics flushStatistics) {
        final String applicationId = accessTokenAppIdPair.getApplicationId();
        final FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(applicationId, false);
        final GraphRequest postRequest = GraphRequest.newPostRequest(null, String.format("%s/activities", applicationId), null, null);
        Bundle parameters;
        if ((parameters = postRequest.getParameters()) == null) {
            parameters = new Bundle();
        }
        parameters.putString("access_token", accessTokenAppIdPair.getAccessTokenString());
        final String pushNotificationsRegistrationId = AppEventsLogger.getPushNotificationsRegistrationId();
        if (pushNotificationsRegistrationId != null) {
            parameters.putString("device_token", pushNotificationsRegistrationId);
        }
        postRequest.setParameters(parameters);
        boolean supportsImplicitLogging = false;
        if (queryAppSettings != null) {
            supportsImplicitLogging = queryAppSettings.supportsImplicitLogging();
        }
        final int populateRequest = sessionEventsState.populateRequest(postRequest, FacebookSdk.getApplicationContext(), supportsImplicitLogging, b);
        if (populateRequest == 0) {
            return null;
        }
        flushStatistics.numEvents += populateRequest;
        postRequest.setCallback((GraphRequest.Callback)new GraphRequest.Callback() {
            @Override
            public void onCompleted(final GraphResponse graphResponse) {
                handleResponse(accessTokenAppIdPair, postRequest, graphResponse, sessionEventsState, flushStatistics);
            }
        });
        return postRequest;
    }
    
    public static void flush(final FlushReason flushReason) {
        AppEventQueue.singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                AppEventQueue.flushAndWait(flushReason);
            }
        });
    }
    
    static void flushAndWait(final FlushReason flushReason) {
        AppEventQueue.appEventCollection.addPersistedEvents(AppEventStore.readAndClearStore());
        try {
            final FlushStatistics sendEventsToServer = sendEventsToServer(flushReason, AppEventQueue.appEventCollection);
            if (sendEventsToServer != null) {
                final Intent intent = new Intent("com.facebook.sdk.APP_EVENTS_FLUSHED");
                intent.putExtra("com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED", sendEventsToServer.numEvents);
                intent.putExtra("com.facebook.sdk.APP_EVENTS_FLUSH_RESULT", (Serializable)sendEventsToServer.result);
                LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext()).sendBroadcast(intent);
            }
        }
        catch (Exception ex) {
            Log.w(AppEventQueue.TAG, "Caught unexpected exception while flushing app events: ", (Throwable)ex);
        }
    }
    
    public static Set<AccessTokenAppIdPair> getKeySet() {
        return AppEventQueue.appEventCollection.keySet();
    }
    
    private static void handleResponse(final AccessTokenAppIdPair accessTokenAppIdPair, final GraphRequest graphRequest, final GraphResponse graphResponse, final SessionEventsState sessionEventsState, final FlushStatistics flushStatistics) {
        final FacebookRequestError error = graphResponse.getError();
        String format = "Success";
        FlushResult result = FlushResult.SUCCESS;
        while (true) {
            while (true) {
                Label_0039: {
                    if (error == null) {
                        break Label_0039;
                    }
                    if (error.getErrorCode() == -1) {
                        format = "Failed: No Connectivity";
                        result = FlushResult.NO_CONNECTIVITY;
                        break Label_0039;
                    }
                    Label_0170: {
                        break Label_0170;
                    Label_0112_Outer:
                        while (true) {
                            final String s = (String)graphRequest.getTag();
                            while (true) {
                            Label_0213:
                                while (true) {
                                    try {
                                        final String string = new JSONArray(s).toString(2);
                                        Logger.log(LoggingBehavior.APP_EVENTS, AppEventQueue.TAG, "Flush completed\nParams: %s\n  Result: %s\n  Events JSON: %s", graphRequest.getGraphObject().toString(), format, string);
                                        if (error != null) {
                                            final boolean b = true;
                                            sessionEventsState.clearInFlightAndStats(b);
                                            if (result == FlushResult.NO_CONNECTIVITY) {
                                                FacebookSdk.getExecutor().execute(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        AppEventStore.persistEvents(accessTokenAppIdPair, sessionEventsState);
                                                    }
                                                });
                                            }
                                            if (result != FlushResult.SUCCESS && flushStatistics.result != FlushResult.NO_CONNECTIVITY) {
                                                flushStatistics.result = result;
                                            }
                                            return;
                                        }
                                        break Label_0213;
                                        format = String.format("Failed:\n  Response: %s\n  Error %s", graphResponse.toString(), error.toString());
                                        result = FlushResult.SERVER_ERROR;
                                        break;
                                    }
                                    catch (JSONException ex) {
                                        final String string = "<Can't encode events for debug logging>";
                                        continue Label_0112_Outer;
                                    }
                                    break;
                                }
                                final boolean b = false;
                                continue;
                            }
                        }
                    }
                }
                if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.APP_EVENTS)) {
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    public static void persistToDisk() {
        AppEventQueue.singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                AppEventStore.persistEvents(AppEventQueue.appEventCollection);
                AppEventQueue.appEventCollection = new AppEventCollection();
            }
        });
    }
    
    private static FlushStatistics sendEventsToServer(final FlushReason flushReason, final AppEventCollection collection) {
        final FlushStatistics flushStatistics = new FlushStatistics();
        final boolean limitEventAndDataUsage = FacebookSdk.getLimitEventAndDataUsage(FacebookSdk.getApplicationContext());
        final ArrayList<GraphRequest> list = new ArrayList<GraphRequest>();
        for (final AccessTokenAppIdPair accessTokenAppIdPair : collection.keySet()) {
            final GraphRequest buildRequestForSession = buildRequestForSession(accessTokenAppIdPair, collection.get(accessTokenAppIdPair), limitEventAndDataUsage, flushStatistics);
            if (buildRequestForSession != null) {
                list.add(buildRequestForSession);
            }
        }
        FlushStatistics flushStatistics2;
        if (list.size() > 0) {
            Logger.log(LoggingBehavior.APP_EVENTS, AppEventQueue.TAG, "Flushing %d events due to %s.", flushStatistics.numEvents, flushReason.toString());
            final Iterator<Object> iterator2 = list.iterator();
            while (true) {
                flushStatistics2 = flushStatistics;
                if (!iterator2.hasNext()) {
                    break;
                }
                iterator2.next().executeAndWait();
            }
        }
        else {
            flushStatistics2 = null;
        }
        return flushStatistics2;
    }
}
