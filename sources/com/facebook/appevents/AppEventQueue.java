package com.facebook.appevents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.Callback;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger.FlushBehavior;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;

class AppEventQueue {
    private static final int FLUSH_PERIOD_IN_SECONDS = 15;
    private static final int NUM_LOG_EVENTS_TO_TRY_TO_FLUSH_AFTER = 100;
    private static final String TAG = AppEventQueue.class.getName();
    private static volatile AppEventCollection appEventCollection = new AppEventCollection();
    private static final Runnable flushRunnable = new C11751();
    private static ScheduledFuture scheduledFuture;
    private static final ScheduledExecutorService singleThreadExecutor = Executors.newSingleThreadScheduledExecutor();

    /* renamed from: com.facebook.appevents.AppEventQueue$1 */
    static class C11751 implements Runnable {
        C11751() {
        }

        public void run() {
            AppEventQueue.scheduledFuture = null;
            if (AppEventsLogger.getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
                AppEventQueue.flushAndWait(FlushReason.TIMER);
            }
        }
    }

    /* renamed from: com.facebook.appevents.AppEventQueue$2 */
    static class C11762 implements Runnable {
        C11762() {
        }

        public void run() {
            AppEventStore.persistEvents(AppEventQueue.appEventCollection);
            AppEventQueue.appEventCollection = new AppEventCollection();
        }
    }

    AppEventQueue() {
    }

    public static void persistToDisk() {
        singleThreadExecutor.execute(new C11762());
    }

    public static void flush(final FlushReason reason) {
        singleThreadExecutor.execute(new Runnable() {
            public void run() {
                AppEventQueue.flushAndWait(reason);
            }
        });
    }

    public static void add(final AccessTokenAppIdPair accessTokenAppId, final AppEvent appEvent) {
        singleThreadExecutor.execute(new Runnable() {
            public void run() {
                AppEventQueue.appEventCollection.addEvent(accessTokenAppId, appEvent);
                if (AppEventsLogger.getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY && AppEventQueue.appEventCollection.getEventCount() > 100) {
                    AppEventQueue.flushAndWait(FlushReason.EVENT_THRESHOLD);
                } else if (AppEventQueue.scheduledFuture == null) {
                    AppEventQueue.scheduledFuture = AppEventQueue.singleThreadExecutor.schedule(AppEventQueue.flushRunnable, 15, TimeUnit.SECONDS);
                }
            }
        });
    }

    public static Set<AccessTokenAppIdPair> getKeySet() {
        return appEventCollection.keySet();
    }

    static void flushAndWait(FlushReason reason) {
        appEventCollection.addPersistedEvents(AppEventStore.readAndClearStore());
        try {
            FlushStatistics flushResults = sendEventsToServer(reason, appEventCollection);
            if (flushResults != null) {
                Intent intent = new Intent(AppEventsLogger.ACTION_APP_EVENTS_FLUSHED);
                intent.putExtra(AppEventsLogger.APP_EVENTS_EXTRA_NUM_EVENTS_FLUSHED, flushResults.numEvents);
                intent.putExtra(AppEventsLogger.APP_EVENTS_EXTRA_FLUSH_RESULT, flushResults.result);
                LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext()).sendBroadcast(intent);
            }
        } catch (Exception e) {
            Log.w(TAG, "Caught unexpected exception while flushing app events: ", e);
        }
    }

    private static FlushStatistics sendEventsToServer(FlushReason reason, AppEventCollection appEventCollection) {
        FlushStatistics flushResults = new FlushStatistics();
        boolean limitEventUsage = FacebookSdk.getLimitEventAndDataUsage(FacebookSdk.getApplicationContext());
        List<GraphRequest> requestsToExecute = new ArrayList();
        for (AccessTokenAppIdPair accessTokenAppId : appEventCollection.keySet()) {
            GraphRequest request = buildRequestForSession(accessTokenAppId, appEventCollection.get(accessTokenAppId), limitEventUsage, flushResults);
            if (request != null) {
                requestsToExecute.add(request);
            }
        }
        if (requestsToExecute.size() <= 0) {
            return null;
        }
        Logger.log(LoggingBehavior.APP_EVENTS, TAG, "Flushing %d events due to %s.", Integer.valueOf(flushResults.numEvents), reason.toString());
        for (GraphRequest request2 : requestsToExecute) {
            request2.executeAndWait();
        }
        return flushResults;
    }

    private static GraphRequest buildRequestForSession(final AccessTokenAppIdPair accessTokenAppId, final SessionEventsState appEvents, boolean limitEventUsage, final FlushStatistics flushState) {
        FetchedAppSettings fetchedAppSettings = FetchedAppSettingsManager.queryAppSettings(accessTokenAppId.getApplicationId(), false);
        final GraphRequest postRequest = GraphRequest.newPostRequest(null, String.format("%s/activities", new Object[]{applicationId}), null, null);
        Bundle requestParameters = postRequest.getParameters();
        if (requestParameters == null) {
            requestParameters = new Bundle();
        }
        requestParameters.putString("access_token", accessTokenAppId.getAccessTokenString());
        String pushNotificationsRegistrationId = AppEventsLogger.getPushNotificationsRegistrationId();
        if (pushNotificationsRegistrationId != null) {
            requestParameters.putString("device_token", pushNotificationsRegistrationId);
        }
        postRequest.setParameters(requestParameters);
        boolean supportsImplicitLogging = false;
        if (fetchedAppSettings != null) {
            supportsImplicitLogging = fetchedAppSettings.supportsImplicitLogging();
        }
        int numEvents = appEvents.populateRequest(postRequest, FacebookSdk.getApplicationContext(), supportsImplicitLogging, limitEventUsage);
        if (numEvents == 0) {
            return null;
        }
        flushState.numEvents += numEvents;
        postRequest.setCallback(new Callback() {
            public void onCompleted(GraphResponse response) {
                AppEventQueue.handleResponse(accessTokenAppId, postRequest, response, appEvents, flushState);
            }
        });
        return postRequest;
    }

    private static void handleResponse(final AccessTokenAppIdPair accessTokenAppId, GraphRequest request, GraphResponse response, SessionEventsState appEvents, FlushStatistics flushState) {
        FacebookRequestError error = response.getError();
        String resultDescription = "Success";
        FlushResult flushResult = FlushResult.SUCCESS;
        if (error != null) {
            if (error.getErrorCode() == -1) {
                resultDescription = "Failed: No Connectivity";
                flushResult = FlushResult.NO_CONNECTIVITY;
            } else {
                resultDescription = String.format("Failed:\n  Response: %s\n  Error %s", new Object[]{response.toString(), error.toString()});
                flushResult = FlushResult.SERVER_ERROR;
            }
        }
        if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.APP_EVENTS)) {
            String prettyPrintedEvents;
            try {
                prettyPrintedEvents = new JSONArray((String) request.getTag()).toString(2);
            } catch (JSONException e) {
                prettyPrintedEvents = "<Can't encode events for debug logging>";
            }
            Logger.log(LoggingBehavior.APP_EVENTS, TAG, "Flush completed\nParams: %s\n  Result: %s\n  Events JSON: %s", request.getGraphObject().toString(), resultDescription, prettyPrintedEvents);
        }
        appEvents.clearInFlightAndStats(error != null);
        if (flushResult == FlushResult.NO_CONNECTIVITY) {
            final SessionEventsState sessionEventsState = appEvents;
            FacebookSdk.getExecutor().execute(new Runnable() {
                public void run() {
                    AppEventStore.persistEvents(accessTokenAppId, sessionEventsState);
                }
            });
        }
        if (flushResult != FlushResult.SUCCESS && flushState.result != FlushResult.NO_CONNECTIVITY) {
            flushState.result = flushResult;
        }
    }
}
