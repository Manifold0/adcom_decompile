// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import java.io.OutputStream;
import java.util.ArrayList;
import org.json.JSONArray;
import com.facebook.HttpMethod;
import com.facebook.GraphRequest$Callback;
import com.facebook.GraphResponse;
import com.facebook.GraphRequest;
import android.content.Context;
import com.facebook.internal.PlatformServiceClient;
import com.facebook.internal.FragmentWrapper;
import android.app.Activity;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.CallbackManagerImpl$Callback;
import com.facebook.internal.CallbackManagerImpl$RequestCodeOffset;
import com.facebook.internal.FileLruCache$Limits;
import android.os.Looper;
import com.facebook.internal.NativeProtocol;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.internal.AppCall;
import com.facebook.FacebookCallback;
import java.util.Locale;
import com.facebook.internal.Logger;
import com.facebook.LoggingBehavior;
import com.facebook.GraphRequestBatch$Callback;
import com.facebook.GraphRequestBatch;
import org.json.JSONException;
import com.facebook.internal.BundleJSONConverter;
import org.json.JSONObject;
import java.io.InputStream;
import java.io.IOException;
import android.util.Log;
import java.io.Closeable;
import com.facebook.internal.Utility;
import com.facebook.FacebookException;
import com.facebook.AccessToken;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.FacebookSdk;
import com.facebook.FacebookRequestError;
import android.content.Intent;
import com.facebook.share.widget.LikeView;
import android.os.Bundle;
import com.facebook.appevents.AppEventsLogger;
import android.os.Handler;
import com.facebook.internal.WorkQueue;
import com.facebook.internal.FileLruCache;
import java.util.concurrent.ConcurrentHashMap;
import com.facebook.AccessTokenTracker;

@Deprecated
public class LikeActionController
{
    @Deprecated
    public static final String ACTION_LIKE_ACTION_CONTROLLER_DID_ERROR = "com.facebook.sdk.LikeActionController.DID_ERROR";
    @Deprecated
    public static final String ACTION_LIKE_ACTION_CONTROLLER_DID_RESET = "com.facebook.sdk.LikeActionController.DID_RESET";
    @Deprecated
    public static final String ACTION_LIKE_ACTION_CONTROLLER_UPDATED = "com.facebook.sdk.LikeActionController.UPDATED";
    @Deprecated
    public static final String ACTION_OBJECT_ID_KEY = "com.facebook.sdk.LikeActionController.OBJECT_ID";
    private static final int ERROR_CODE_OBJECT_ALREADY_LIKED = 3501;
    @Deprecated
    public static final String ERROR_INVALID_OBJECT_ID = "Invalid Object Id";
    @Deprecated
    public static final String ERROR_PUBLISH_ERROR = "Unable to publish the like/unlike action";
    private static final String JSON_BOOL_IS_OBJECT_LIKED_KEY = "is_object_liked";
    private static final String JSON_BUNDLE_FACEBOOK_DIALOG_ANALYTICS_BUNDLE = "facebook_dialog_analytics_bundle";
    private static final String JSON_INT_OBJECT_TYPE_KEY = "object_type";
    private static final String JSON_INT_VERSION_KEY = "com.facebook.share.internal.LikeActionController.version";
    private static final String JSON_STRING_LIKE_COUNT_WITHOUT_LIKE_KEY = "like_count_string_without_like";
    private static final String JSON_STRING_LIKE_COUNT_WITH_LIKE_KEY = "like_count_string_with_like";
    private static final String JSON_STRING_OBJECT_ID_KEY = "object_id";
    private static final String JSON_STRING_SOCIAL_SENTENCE_WITHOUT_LIKE_KEY = "social_sentence_without_like";
    private static final String JSON_STRING_SOCIAL_SENTENCE_WITH_LIKE_KEY = "social_sentence_with_like";
    private static final String JSON_STRING_UNLIKE_TOKEN_KEY = "unlike_token";
    private static final String LIKE_ACTION_CONTROLLER_STORE = "com.facebook.LikeActionController.CONTROLLER_STORE_KEY";
    private static final String LIKE_ACTION_CONTROLLER_STORE_OBJECT_SUFFIX_KEY = "OBJECT_SUFFIX";
    private static final String LIKE_ACTION_CONTROLLER_STORE_PENDING_OBJECT_ID_KEY = "PENDING_CONTROLLER_KEY";
    private static final int LIKE_ACTION_CONTROLLER_VERSION = 3;
    private static final String LIKE_DIALOG_RESPONSE_LIKE_COUNT_STRING_KEY = "like_count_string";
    private static final String LIKE_DIALOG_RESPONSE_OBJECT_IS_LIKED_KEY = "object_is_liked";
    private static final String LIKE_DIALOG_RESPONSE_SOCIAL_SENTENCE_KEY = "social_sentence";
    private static final String LIKE_DIALOG_RESPONSE_UNLIKE_TOKEN_KEY = "unlike_token";
    private static final int MAX_CACHE_SIZE = 128;
    private static final int MAX_OBJECT_SUFFIX = 1000;
    private static final String TAG;
    private static AccessTokenTracker accessTokenTracker;
    private static final ConcurrentHashMap<String, LikeActionController> cache;
    private static FileLruCache controllerDiskCache;
    private static WorkQueue diskIOWorkQueue;
    private static Handler handler;
    private static boolean isInitialized;
    private static WorkQueue mruCacheWorkQueue;
    private static String objectIdForPendingController;
    private static volatile int objectSuffix;
    private AppEventsLogger appEventsLogger;
    private Bundle facebookDialogAnalyticsBundle;
    private boolean isObjectLiked;
    private boolean isObjectLikedOnServer;
    private boolean isPendingLikeOrUnlike;
    private String likeCountStringWithLike;
    private String likeCountStringWithoutLike;
    private String objectId;
    private boolean objectIsPage;
    private LikeView.ObjectType objectType;
    private String socialSentenceWithLike;
    private String socialSentenceWithoutLike;
    private String unlikeToken;
    private String verifiedObjectId;
    
    static {
        TAG = LikeActionController.class.getSimpleName();
        cache = new ConcurrentHashMap<String, LikeActionController>();
        LikeActionController.mruCacheWorkQueue = new WorkQueue(1);
        LikeActionController.diskIOWorkQueue = new WorkQueue(1);
    }
    
    private LikeActionController(final String objectId, final LikeView.ObjectType objectType) {
        this.objectId = objectId;
        this.objectType = objectType;
    }
    
    private static void broadcastAction(final LikeActionController likeActionController, final String s) {
        broadcastAction(likeActionController, s, null);
    }
    
    private static void broadcastAction(final LikeActionController likeActionController, final String s, final Bundle bundle) {
        final Intent intent = new Intent(s);
        Bundle bundle2 = bundle;
        if (likeActionController != null) {
            if ((bundle2 = bundle) == null) {
                bundle2 = new Bundle();
            }
            bundle2.putString("com.facebook.sdk.LikeActionController.OBJECT_ID", likeActionController.getObjectId());
        }
        if (bundle2 != null) {
            intent.putExtras(bundle2);
        }
        LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext()).sendBroadcast(intent);
    }
    
    private boolean canUseOGPublish() {
        final AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        return !this.objectIsPage && this.verifiedObjectId != null && AccessToken.isCurrentAccessTokenActive() && currentAccessToken.getPermissions() != null && currentAccessToken.getPermissions().contains("publish_actions");
    }
    
    private void clearState() {
        this.facebookDialogAnalyticsBundle = null;
        storeObjectIdForPendingController(null);
    }
    
    private static void createControllerForObjectIdAndType(final String s, final LikeView.ObjectType objectType, final CreationCallback creationCallback) {
        final LikeActionController controllerFromInMemoryCache = getControllerFromInMemoryCache(s);
        if (controllerFromInMemoryCache != null) {
            verifyControllerAndInvokeCallback(controllerFromInMemoryCache, objectType, creationCallback);
            return;
        }
        LikeActionController deserializeFromDiskSynchronously;
        if ((deserializeFromDiskSynchronously = deserializeFromDiskSynchronously(s)) == null) {
            deserializeFromDiskSynchronously = new LikeActionController(s, objectType);
            serializeToDiskAsync(deserializeFromDiskSynchronously);
        }
        putControllerInMemoryCache(s, deserializeFromDiskSynchronously);
        LikeActionController.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                LikeActionController.this.refreshStatusAsync();
            }
        });
        invokeCallbackWithController(creationCallback, deserializeFromDiskSynchronously, null);
    }
    
    private static LikeActionController deserializeFromDiskSynchronously(String cacheKeyForObjectId) {
        final LikeActionController likeActionController = null;
        final LikeActionController likeActionController2 = null;
        LikeActionController likeActionController3 = null;
        LikeActionController likeActionController4 = likeActionController2;
        try {
            cacheKeyForObjectId = getCacheKeyForObjectId(cacheKeyForObjectId);
            likeActionController3 = likeActionController3;
            likeActionController4 = likeActionController2;
            final InputStream value = LikeActionController.controllerDiskCache.get(cacheKeyForObjectId);
            Object deserializeFromJson = likeActionController;
            if (value != null) {
                likeActionController3 = (LikeActionController)value;
                likeActionController4 = (LikeActionController)value;
                final String streamToString = Utility.readStreamToString(value);
                deserializeFromJson = likeActionController;
                likeActionController3 = (LikeActionController)value;
                likeActionController4 = (LikeActionController)value;
                if (!Utility.isNullOrEmpty(streamToString)) {
                    likeActionController3 = (LikeActionController)value;
                    likeActionController4 = (LikeActionController)value;
                    deserializeFromJson = deserializeFromJson(streamToString);
                }
            }
            likeActionController4 = (LikeActionController)deserializeFromJson;
            if (value != null) {
                Utility.closeQuietly((Closeable)value);
                likeActionController4 = (LikeActionController)deserializeFromJson;
            }
            return likeActionController4;
        }
        catch (IOException ex) {
            likeActionController4 = likeActionController3;
            Log.e(LikeActionController.TAG, "Unable to deserialize controller from disk", (Throwable)ex);
            likeActionController4 = null;
            return null;
        }
        finally {
            if (likeActionController4 != null) {
                Utility.closeQuietly((Closeable)likeActionController4);
            }
        }
    }
    
    private static LikeActionController deserializeFromJson(final String s) {
        LikeActionController likeActionController2;
        try {
            final JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.optInt("com.facebook.share.internal.LikeActionController.version", -1) != 3) {
                return null;
            }
            final LikeActionController likeActionController = new LikeActionController(jsonObject.getString("object_id"), LikeView.ObjectType.fromInt(jsonObject.optInt("object_type", LikeView.ObjectType.UNKNOWN.getValue())));
            likeActionController.likeCountStringWithLike = jsonObject.optString("like_count_string_with_like", (String)null);
            likeActionController.likeCountStringWithoutLike = jsonObject.optString("like_count_string_without_like", (String)null);
            likeActionController.socialSentenceWithLike = jsonObject.optString("social_sentence_with_like", (String)null);
            likeActionController.socialSentenceWithoutLike = jsonObject.optString("social_sentence_without_like", (String)null);
            likeActionController.isObjectLiked = jsonObject.optBoolean("is_object_liked");
            likeActionController.unlikeToken = jsonObject.optString("unlike_token", (String)null);
            final JSONObject optJSONObject = jsonObject.optJSONObject("facebook_dialog_analytics_bundle");
            likeActionController2 = likeActionController;
            if (optJSONObject != null) {
                likeActionController.facebookDialogAnalyticsBundle = BundleJSONConverter.convertToBundle(optJSONObject);
                return likeActionController;
            }
        }
        catch (JSONException ex) {
            Log.e(LikeActionController.TAG, "Unable to deserialize controller from JSON", (Throwable)ex);
            likeActionController2 = null;
        }
        return likeActionController2;
    }
    
    private void fetchVerifiedObjectId(final RequestCompletionCallback requestCompletionCallback) {
        if (!Utility.isNullOrEmpty(this.verifiedObjectId)) {
            if (requestCompletionCallback != null) {
                requestCompletionCallback.onComplete();
            }
            return;
        }
        final GetOGObjectIdRequestWrapper getOGObjectIdRequestWrapper = new GetOGObjectIdRequestWrapper(this.objectId, this.objectType);
        final GetPageIdRequestWrapper getPageIdRequestWrapper = new GetPageIdRequestWrapper(this.objectId, this.objectType);
        final GraphRequestBatch graphRequestBatch = new GraphRequestBatch();
        ((AbstractRequestWrapper)getOGObjectIdRequestWrapper).addToBatch(graphRequestBatch);
        ((AbstractRequestWrapper)getPageIdRequestWrapper).addToBatch(graphRequestBatch);
        graphRequestBatch.addCallback((GraphRequestBatch$Callback)new GraphRequestBatch$Callback() {
            public void onBatchCompleted(final GraphRequestBatch graphRequestBatch) {
                LikeActionController.this.verifiedObjectId = getOGObjectIdRequestWrapper.verifiedObjectId;
                if (Utility.isNullOrEmpty(LikeActionController.this.verifiedObjectId)) {
                    LikeActionController.this.verifiedObjectId = getPageIdRequestWrapper.verifiedObjectId;
                    LikeActionController.this.objectIsPage = getPageIdRequestWrapper.objectIsPage;
                }
                if (Utility.isNullOrEmpty(LikeActionController.this.verifiedObjectId)) {
                    Logger.log(LoggingBehavior.DEVELOPER_ERRORS, LikeActionController.TAG, "Unable to verify the FB id for '%s'. Verify that it is a valid FB object or page", new Object[] { LikeActionController.this.objectId });
                    final LikeActionController this$0 = LikeActionController.this;
                    FacebookRequestError facebookRequestError;
                    if (((AbstractRequestWrapper)getPageIdRequestWrapper).getError() != null) {
                        facebookRequestError = ((AbstractRequestWrapper)getPageIdRequestWrapper).getError();
                    }
                    else {
                        facebookRequestError = ((AbstractRequestWrapper)getOGObjectIdRequestWrapper).getError();
                    }
                    this$0.logAppEventForError("get_verified_id", facebookRequestError);
                }
                if (requestCompletionCallback != null) {
                    requestCompletionCallback.onComplete();
                }
            }
        });
        graphRequestBatch.executeAsync();
    }
    
    private AppEventsLogger getAppEventsLogger() {
        if (this.appEventsLogger == null) {
            this.appEventsLogger = AppEventsLogger.newLogger(FacebookSdk.getApplicationContext());
        }
        return this.appEventsLogger;
    }
    
    private static String getCacheKeyForObjectId(final String s) {
        String token = null;
        final AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        if (AccessToken.isCurrentAccessTokenActive()) {
            token = currentAccessToken.getToken();
        }
        String md5hash;
        if ((md5hash = token) != null) {
            md5hash = Utility.md5hash(token);
        }
        return String.format(Locale.ROOT, "%s|%s|com.fb.sdk.like|%d", s, Utility.coerceValueIfNullOrEmpty(md5hash, ""), LikeActionController.objectSuffix);
    }
    
    @Deprecated
    public static void getControllerForObjectId(final String s, final LikeView.ObjectType objectType, final CreationCallback creationCallback) {
        if (!LikeActionController.isInitialized) {
            performFirstInitialize();
        }
        final LikeActionController controllerFromInMemoryCache = getControllerFromInMemoryCache(s);
        if (controllerFromInMemoryCache != null) {
            verifyControllerAndInvokeCallback(controllerFromInMemoryCache, objectType, creationCallback);
            return;
        }
        LikeActionController.diskIOWorkQueue.addActiveWorkItem((Runnable)new CreateLikeActionControllerWorkItem(s, objectType, creationCallback));
    }
    
    private static LikeActionController getControllerFromInMemoryCache(String cacheKeyForObjectId) {
        cacheKeyForObjectId = getCacheKeyForObjectId(cacheKeyForObjectId);
        final LikeActionController likeActionController = LikeActionController.cache.get(cacheKeyForObjectId);
        if (likeActionController != null) {
            LikeActionController.mruCacheWorkQueue.addActiveWorkItem((Runnable)new MRUCacheWorkItem(cacheKeyForObjectId, false));
        }
        return likeActionController;
    }
    
    private ResultProcessor getResultProcessor(final Bundle bundle) {
        return new ResultProcessor(null) {
            @Override
            public void onCancel(final AppCall appCall) {
                this.onError(appCall, (FacebookException)new FacebookOperationCanceledException());
            }
            
            @Override
            public void onError(final AppCall appCall, final FacebookException ex) {
                Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Like Dialog failed with error : %s", new Object[] { ex });
                Bundle val$analyticsParameters;
                if (bundle == null) {
                    val$analyticsParameters = new Bundle();
                }
                else {
                    val$analyticsParameters = bundle;
                }
                val$analyticsParameters.putString("call_id", appCall.getCallId().toString());
                LikeActionController.this.logAppEventForError("present_dialog", val$analyticsParameters);
                broadcastAction(LikeActionController.this, "com.facebook.sdk.LikeActionController.DID_ERROR", NativeProtocol.createBundleForException(ex));
            }
            
            @Override
            public void onSuccess(final AppCall appCall, final Bundle bundle) {
                if (bundle == null || !bundle.containsKey("object_is_liked")) {
                    return;
                }
                final boolean boolean1 = bundle.getBoolean("object_is_liked");
                String access$700 = LikeActionController.this.likeCountStringWithLike;
                String s = LikeActionController.this.likeCountStringWithoutLike;
                if (bundle.containsKey("like_count_string")) {
                    access$700 = (s = bundle.getString("like_count_string"));
                }
                String access$701 = LikeActionController.this.socialSentenceWithLike;
                String s2 = LikeActionController.this.socialSentenceWithoutLike;
                if (bundle.containsKey("social_sentence")) {
                    access$701 = (s2 = bundle.getString("social_sentence"));
                }
                String s3;
                if (bundle.containsKey("object_is_liked")) {
                    s3 = bundle.getString("unlike_token");
                }
                else {
                    s3 = LikeActionController.this.unlikeToken;
                }
                Bundle val$analyticsParameters;
                if (bundle == null) {
                    val$analyticsParameters = new Bundle();
                }
                else {
                    val$analyticsParameters = bundle;
                }
                val$analyticsParameters.putString("call_id", appCall.getCallId().toString());
                LikeActionController.this.getAppEventsLogger().logSdkEvent("fb_like_control_dialog_did_succeed", (Double)null, val$analyticsParameters);
                LikeActionController.this.updateState(boolean1, access$700, s, access$701, s2, s3);
            }
        };
    }
    
    @Deprecated
    public static boolean handleOnActivityResult(final int n, final int n2, final Intent intent) {
        if (Utility.isNullOrEmpty(LikeActionController.objectIdForPendingController)) {
            LikeActionController.objectIdForPendingController = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).getString("PENDING_CONTROLLER_KEY", (String)null);
        }
        if (Utility.isNullOrEmpty(LikeActionController.objectIdForPendingController)) {
            return false;
        }
        getControllerForObjectId(LikeActionController.objectIdForPendingController, LikeView.ObjectType.UNKNOWN, (CreationCallback)new CreationCallback() {
            @Override
            public void onComplete(final LikeActionController likeActionController, final FacebookException ex) {
                if (ex == null) {
                    likeActionController.onActivityResult(n, n2, intent);
                    return;
                }
                Utility.logd(LikeActionController.TAG, (Exception)ex);
            }
        });
        return true;
    }
    
    private static void invokeCallbackWithController(final CreationCallback creationCallback, final LikeActionController likeActionController, final FacebookException ex) {
        if (creationCallback == null) {
            return;
        }
        LikeActionController.handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                creationCallback.onComplete(likeActionController, ex);
            }
        });
    }
    
    private void logAppEventForError(final String s, Bundle bundle) {
        bundle = new Bundle(bundle);
        bundle.putString("object_id", this.objectId);
        bundle.putString("object_type", this.objectType.toString());
        bundle.putString("current_action", s);
        this.getAppEventsLogger().logSdkEvent("fb_like_control_error", (Double)null, bundle);
    }
    
    private void logAppEventForError(final String s, final FacebookRequestError facebookRequestError) {
        final Bundle bundle = new Bundle();
        if (facebookRequestError != null) {
            final JSONObject requestResult = facebookRequestError.getRequestResult();
            if (requestResult != null) {
                bundle.putString("error", requestResult.toString());
            }
        }
        this.logAppEventForError(s, bundle);
    }
    
    private void onActivityResult(final int n, final int n2, final Intent intent) {
        ShareInternalUtility.handleActivityResult(n, n2, intent, this.getResultProcessor(this.facebookDialogAnalyticsBundle));
        this.clearState();
    }
    
    private static void performFirstInitialize() {
        synchronized (LikeActionController.class) {
            if (!LikeActionController.isInitialized) {
                LikeActionController.handler = new Handler(Looper.getMainLooper());
                LikeActionController.objectSuffix = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).getInt("OBJECT_SUFFIX", 1);
                LikeActionController.controllerDiskCache = new FileLruCache(LikeActionController.TAG, new FileLruCache$Limits());
                registerAccessTokenTracker();
                CallbackManagerImpl.registerStaticCallback(CallbackManagerImpl$RequestCodeOffset.Like.toRequestCode(), (CallbackManagerImpl$Callback)new CallbackManagerImpl$Callback() {
                    public boolean onActivityResult(final int n, final Intent intent) {
                        return LikeActionController.handleOnActivityResult(CallbackManagerImpl$RequestCodeOffset.Like.toRequestCode(), n, intent);
                    }
                });
                LikeActionController.isInitialized = true;
            }
        }
    }
    
    private void presentLikeDialog(final Activity activity, final FragmentWrapper fragmentWrapper, final Bundle bundle) {
        String s = null;
        if (LikeDialog.canShowNativeDialog()) {
            s = "fb_like_control_did_present_dialog";
        }
        else if (LikeDialog.canShowWebFallback()) {
            s = "fb_like_control_did_present_fallback_dialog";
        }
        else {
            this.logAppEventForError("present_dialog", bundle);
            Utility.logd(LikeActionController.TAG, "Cannot show the Like Dialog on this device.");
            broadcastAction(null, "com.facebook.sdk.LikeActionController.UPDATED");
        }
        if (s != null) {
            String objectType;
            if (this.objectType != null) {
                objectType = this.objectType.toString();
            }
            else {
                objectType = LikeView.ObjectType.UNKNOWN.toString();
            }
            final LikeContent build = new LikeContent.Builder().setObjectId(this.objectId).setObjectType(objectType).build();
            if (fragmentWrapper != null) {
                new LikeDialog(fragmentWrapper).show(build);
            }
            else {
                new LikeDialog(activity).show(build);
            }
            this.saveState(bundle);
            this.getAppEventsLogger().logSdkEvent("fb_like_control_did_present_dialog", (Double)null, bundle);
        }
    }
    
    private void publishAgainIfNeeded(final Bundle bundle) {
        if (this.isObjectLiked != this.isObjectLikedOnServer && !this.publishLikeOrUnlikeAsync(this.isObjectLiked, bundle)) {
            this.publishDidError(!this.isObjectLiked);
        }
    }
    
    private void publishDidError(final boolean b) {
        this.updateLikeState(b);
        final Bundle bundle = new Bundle();
        bundle.putString("com.facebook.platform.status.ERROR_DESCRIPTION", "Unable to publish the like/unlike action");
        broadcastAction(this, "com.facebook.sdk.LikeActionController.DID_ERROR", bundle);
    }
    
    private void publishLikeAsync(final Bundle bundle) {
        this.isPendingLikeOrUnlike = true;
        this.fetchVerifiedObjectId((RequestCompletionCallback)new RequestCompletionCallback() {
            @Override
            public void onComplete() {
                if (Utility.isNullOrEmpty(LikeActionController.this.verifiedObjectId)) {
                    final Bundle bundle = new Bundle();
                    bundle.putString("com.facebook.platform.status.ERROR_DESCRIPTION", "Invalid Object Id");
                    broadcastAction(LikeActionController.this, "com.facebook.sdk.LikeActionController.DID_ERROR", bundle);
                    return;
                }
                final GraphRequestBatch graphRequestBatch = new GraphRequestBatch();
                final PublishLikeRequestWrapper publishLikeRequestWrapper = new PublishLikeRequestWrapper(LikeActionController.this.verifiedObjectId, LikeActionController.this.objectType);
                ((AbstractRequestWrapper)publishLikeRequestWrapper).addToBatch(graphRequestBatch);
                graphRequestBatch.addCallback((GraphRequestBatch$Callback)new GraphRequestBatch$Callback() {
                    public void onBatchCompleted(final GraphRequestBatch graphRequestBatch) {
                        LikeActionController.this.isPendingLikeOrUnlike = false;
                        if (((AbstractRequestWrapper)publishLikeRequestWrapper).getError() != null) {
                            LikeActionController.this.publishDidError(false);
                            return;
                        }
                        LikeActionController.this.unlikeToken = Utility.coerceValueIfNullOrEmpty(publishLikeRequestWrapper.unlikeToken, (String)null);
                        LikeActionController.this.isObjectLikedOnServer = true;
                        LikeActionController.this.getAppEventsLogger().logSdkEvent("fb_like_control_did_like", (Double)null, bundle);
                        LikeActionController.this.publishAgainIfNeeded(bundle);
                    }
                });
                graphRequestBatch.executeAsync();
            }
        });
    }
    
    private boolean publishLikeOrUnlikeAsync(final boolean b, final Bundle bundle) {
        boolean b2 = false;
        if (this.canUseOGPublish()) {
            if (b) {
                b2 = true;
                this.publishLikeAsync(bundle);
            }
            else {
                b2 = b2;
                if (!Utility.isNullOrEmpty(this.unlikeToken)) {
                    this.publishUnlikeAsync(bundle);
                    return true;
                }
            }
        }
        return b2;
    }
    
    private void publishUnlikeAsync(final Bundle bundle) {
        this.isPendingLikeOrUnlike = true;
        final GraphRequestBatch graphRequestBatch = new GraphRequestBatch();
        final PublishUnlikeRequestWrapper publishUnlikeRequestWrapper = new PublishUnlikeRequestWrapper(this.unlikeToken);
        ((AbstractRequestWrapper)publishUnlikeRequestWrapper).addToBatch(graphRequestBatch);
        graphRequestBatch.addCallback((GraphRequestBatch$Callback)new GraphRequestBatch$Callback() {
            public void onBatchCompleted(final GraphRequestBatch graphRequestBatch) {
                LikeActionController.this.isPendingLikeOrUnlike = false;
                if (((AbstractRequestWrapper)publishUnlikeRequestWrapper).getError() != null) {
                    LikeActionController.this.publishDidError(true);
                    return;
                }
                LikeActionController.this.unlikeToken = null;
                LikeActionController.this.isObjectLikedOnServer = false;
                LikeActionController.this.getAppEventsLogger().logSdkEvent("fb_like_control_did_unlike", (Double)null, bundle);
                LikeActionController.this.publishAgainIfNeeded(bundle);
            }
        });
        graphRequestBatch.executeAsync();
    }
    
    private static void putControllerInMemoryCache(String cacheKeyForObjectId, final LikeActionController likeActionController) {
        cacheKeyForObjectId = getCacheKeyForObjectId(cacheKeyForObjectId);
        LikeActionController.mruCacheWorkQueue.addActiveWorkItem((Runnable)new MRUCacheWorkItem(cacheKeyForObjectId, true));
        LikeActionController.cache.put(cacheKeyForObjectId, likeActionController);
    }
    
    private void refreshStatusAsync() {
        if (!AccessToken.isCurrentAccessTokenActive()) {
            this.refreshStatusViaService();
            return;
        }
        this.fetchVerifiedObjectId((RequestCompletionCallback)new RequestCompletionCallback() {
            @Override
            public void onComplete() {
                LikeRequestWrapper likeRequestWrapper = null;
                switch (LikeActionController.this.objectType) {
                    default: {
                        likeRequestWrapper = new GetOGObjectLikesRequestWrapper(LikeActionController.this.verifiedObjectId, LikeActionController.this.objectType);
                        break;
                    }
                    case PAGE: {
                        likeRequestWrapper = new GetPageLikesRequestWrapper(LikeActionController.this.verifiedObjectId);
                        break;
                    }
                }
                final GetEngagementRequestWrapper getEngagementRequestWrapper = new GetEngagementRequestWrapper(LikeActionController.this.verifiedObjectId, LikeActionController.this.objectType);
                final GraphRequestBatch graphRequestBatch = new GraphRequestBatch();
                ((RequestWrapper)likeRequestWrapper).addToBatch(graphRequestBatch);
                ((AbstractRequestWrapper)getEngagementRequestWrapper).addToBatch(graphRequestBatch);
                graphRequestBatch.addCallback((GraphRequestBatch$Callback)new GraphRequestBatch$Callback() {
                    public void onBatchCompleted(final GraphRequestBatch graphRequestBatch) {
                        if (((RequestWrapper)likeRequestWrapper).getError() != null || ((AbstractRequestWrapper)getEngagementRequestWrapper).getError() != null) {
                            Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Unable to refresh like state for id: '%s'", new Object[] { LikeActionController.this.objectId });
                            return;
                        }
                        LikeActionController.this.updateState(likeRequestWrapper.isObjectLiked(), getEngagementRequestWrapper.likeCountStringWithLike, getEngagementRequestWrapper.likeCountStringWithoutLike, getEngagementRequestWrapper.socialSentenceStringWithLike, getEngagementRequestWrapper.socialSentenceStringWithoutLike, likeRequestWrapper.getUnlikeToken());
                    }
                });
                graphRequestBatch.executeAsync();
            }
        });
    }
    
    private void refreshStatusViaService() {
        final LikeStatusClient likeStatusClient = new LikeStatusClient(FacebookSdk.getApplicationContext(), FacebookSdk.getApplicationId(), this.objectId);
        if (!likeStatusClient.start()) {
            return;
        }
        likeStatusClient.setCompletedListener((PlatformServiceClient.CompletedListener)new PlatformServiceClient.CompletedListener() {
            @Override
            public void completed(final Bundle bundle) {
                if (bundle == null || !bundle.containsKey("com.facebook.platform.extra.OBJECT_IS_LIKED")) {
                    return;
                }
                final boolean boolean1 = bundle.getBoolean("com.facebook.platform.extra.OBJECT_IS_LIKED");
                String s;
                if (bundle.containsKey("com.facebook.platform.extra.LIKE_COUNT_STRING_WITH_LIKE")) {
                    s = bundle.getString("com.facebook.platform.extra.LIKE_COUNT_STRING_WITH_LIKE");
                }
                else {
                    s = LikeActionController.this.likeCountStringWithLike;
                }
                String s2;
                if (bundle.containsKey("com.facebook.platform.extra.LIKE_COUNT_STRING_WITHOUT_LIKE")) {
                    s2 = bundle.getString("com.facebook.platform.extra.LIKE_COUNT_STRING_WITHOUT_LIKE");
                }
                else {
                    s2 = LikeActionController.this.likeCountStringWithoutLike;
                }
                String s3;
                if (bundle.containsKey("com.facebook.platform.extra.SOCIAL_SENTENCE_WITH_LIKE")) {
                    s3 = bundle.getString("com.facebook.platform.extra.SOCIAL_SENTENCE_WITH_LIKE");
                }
                else {
                    s3 = LikeActionController.this.socialSentenceWithLike;
                }
                String s4;
                if (bundle.containsKey("com.facebook.platform.extra.SOCIAL_SENTENCE_WITHOUT_LIKE")) {
                    s4 = bundle.getString("com.facebook.platform.extra.SOCIAL_SENTENCE_WITHOUT_LIKE");
                }
                else {
                    s4 = LikeActionController.this.socialSentenceWithoutLike;
                }
                String s5;
                if (bundle.containsKey("com.facebook.platform.extra.UNLIKE_TOKEN")) {
                    s5 = bundle.getString("com.facebook.platform.extra.UNLIKE_TOKEN");
                }
                else {
                    s5 = LikeActionController.this.unlikeToken;
                }
                LikeActionController.this.updateState(boolean1, s, s2, s3, s4, s5);
            }
        });
    }
    
    private static void registerAccessTokenTracker() {
        LikeActionController.accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(final AccessToken accessToken, final AccessToken accessToken2) {
                final Context applicationContext = FacebookSdk.getApplicationContext();
                if (accessToken2 == null) {
                    LikeActionController.objectSuffix = (LikeActionController.objectSuffix + 1) % 1000;
                    applicationContext.getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).edit().putInt("OBJECT_SUFFIX", LikeActionController.objectSuffix).apply();
                    LikeActionController.cache.clear();
                    LikeActionController.controllerDiskCache.clearCache();
                }
                broadcastAction(null, "com.facebook.sdk.LikeActionController.DID_RESET");
            }
        };
    }
    
    private void saveState(final Bundle facebookDialogAnalyticsBundle) {
        storeObjectIdForPendingController(this.objectId);
        this.facebookDialogAnalyticsBundle = facebookDialogAnalyticsBundle;
        serializeToDiskAsync(this);
    }
    
    private static void serializeToDiskAsync(final LikeActionController likeActionController) {
        final String serializeToJson = serializeToJson(likeActionController);
        final String cacheKeyForObjectId = getCacheKeyForObjectId(likeActionController.objectId);
        if (!Utility.isNullOrEmpty(serializeToJson) && !Utility.isNullOrEmpty(cacheKeyForObjectId)) {
            LikeActionController.diskIOWorkQueue.addActiveWorkItem((Runnable)new SerializeToDiskWorkItem(cacheKeyForObjectId, serializeToJson));
        }
    }
    
    private static void serializeToDiskSynchronously(final String s, final String s2) {
        Closeable closeable = null;
        Closeable openPutStream = null;
        try {
            ((OutputStream)(closeable = (openPutStream = LikeActionController.controllerDiskCache.openPutStream(s)))).write(s2.getBytes());
        }
        catch (IOException ex) {
            closeable = openPutStream;
            Log.e(LikeActionController.TAG, "Unable to serialize controller to disk", (Throwable)ex);
        }
        finally {
            if (closeable != null) {
                Utility.closeQuietly(closeable);
            }
        }
    }
    
    private static String serializeToJson(final LikeActionController likeActionController) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("com.facebook.share.internal.LikeActionController.version", 3);
            jsonObject.put("object_id", (Object)likeActionController.objectId);
            jsonObject.put("object_type", likeActionController.objectType.getValue());
            jsonObject.put("like_count_string_with_like", (Object)likeActionController.likeCountStringWithLike);
            jsonObject.put("like_count_string_without_like", (Object)likeActionController.likeCountStringWithoutLike);
            jsonObject.put("social_sentence_with_like", (Object)likeActionController.socialSentenceWithLike);
            jsonObject.put("social_sentence_without_like", (Object)likeActionController.socialSentenceWithoutLike);
            jsonObject.put("is_object_liked", likeActionController.isObjectLiked);
            jsonObject.put("unlike_token", (Object)likeActionController.unlikeToken);
            if (likeActionController.facebookDialogAnalyticsBundle != null) {
                final JSONObject convertToJSON = BundleJSONConverter.convertToJSON(likeActionController.facebookDialogAnalyticsBundle);
                if (convertToJSON != null) {
                    jsonObject.put("facebook_dialog_analytics_bundle", (Object)convertToJSON);
                }
            }
            return jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e(LikeActionController.TAG, "Unable to serialize controller to JSON", (Throwable)ex);
            return null;
        }
    }
    
    private static void storeObjectIdForPendingController(final String objectIdForPendingController) {
        LikeActionController.objectIdForPendingController = objectIdForPendingController;
        FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).edit().putString("PENDING_CONTROLLER_KEY", LikeActionController.objectIdForPendingController).apply();
    }
    
    private void updateLikeState(final boolean b) {
        this.updateState(b, this.likeCountStringWithLike, this.likeCountStringWithoutLike, this.socialSentenceWithLike, this.socialSentenceWithoutLike, this.unlikeToken);
    }
    
    private void updateState(final boolean isObjectLiked, String coerceValueIfNullOrEmpty, String coerceValueIfNullOrEmpty2, String coerceValueIfNullOrEmpty3, String coerceValueIfNullOrEmpty4, String coerceValueIfNullOrEmpty5) {
        coerceValueIfNullOrEmpty = Utility.coerceValueIfNullOrEmpty(coerceValueIfNullOrEmpty, (String)null);
        coerceValueIfNullOrEmpty2 = Utility.coerceValueIfNullOrEmpty(coerceValueIfNullOrEmpty2, (String)null);
        coerceValueIfNullOrEmpty3 = Utility.coerceValueIfNullOrEmpty(coerceValueIfNullOrEmpty3, (String)null);
        coerceValueIfNullOrEmpty4 = Utility.coerceValueIfNullOrEmpty(coerceValueIfNullOrEmpty4, (String)null);
        coerceValueIfNullOrEmpty5 = Utility.coerceValueIfNullOrEmpty(coerceValueIfNullOrEmpty5, (String)null);
        int n;
        if (isObjectLiked != this.isObjectLiked || !Utility.areObjectsEqual((Object)coerceValueIfNullOrEmpty, (Object)this.likeCountStringWithLike) || !Utility.areObjectsEqual((Object)coerceValueIfNullOrEmpty2, (Object)this.likeCountStringWithoutLike) || !Utility.areObjectsEqual((Object)coerceValueIfNullOrEmpty3, (Object)this.socialSentenceWithLike) || !Utility.areObjectsEqual((Object)coerceValueIfNullOrEmpty4, (Object)this.socialSentenceWithoutLike) || !Utility.areObjectsEqual((Object)coerceValueIfNullOrEmpty5, (Object)this.unlikeToken)) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n == 0) {
            return;
        }
        this.isObjectLiked = isObjectLiked;
        this.likeCountStringWithLike = coerceValueIfNullOrEmpty;
        this.likeCountStringWithoutLike = coerceValueIfNullOrEmpty2;
        this.socialSentenceWithLike = coerceValueIfNullOrEmpty3;
        this.socialSentenceWithoutLike = coerceValueIfNullOrEmpty4;
        this.unlikeToken = coerceValueIfNullOrEmpty5;
        serializeToDiskAsync(this);
        broadcastAction(this, "com.facebook.sdk.LikeActionController.UPDATED");
    }
    
    private static void verifyControllerAndInvokeCallback(LikeActionController likeActionController, final LikeView.ObjectType objectType, final CreationCallback creationCallback) {
        final LikeView.ObjectType mostSpecificObjectType = ShareInternalUtility.getMostSpecificObjectType(objectType, likeActionController.objectType);
        final FacebookException ex = null;
        FacebookException ex2;
        if (mostSpecificObjectType == null) {
            ex2 = new FacebookException("Object with id:\"%s\" is already marked as type:\"%s\". Cannot change the type to:\"%s\"", new Object[] { likeActionController.objectId, likeActionController.objectType.toString(), objectType.toString() });
            likeActionController = null;
        }
        else {
            likeActionController.objectType = mostSpecificObjectType;
            ex2 = ex;
        }
        invokeCallbackWithController(creationCallback, likeActionController, ex2);
    }
    
    @Deprecated
    public String getLikeCountString() {
        if (this.isObjectLiked) {
            return this.likeCountStringWithLike;
        }
        return this.likeCountStringWithoutLike;
    }
    
    @Deprecated
    public String getObjectId() {
        return this.objectId;
    }
    
    @Deprecated
    public String getSocialSentence() {
        if (this.isObjectLiked) {
            return this.socialSentenceWithLike;
        }
        return this.socialSentenceWithoutLike;
    }
    
    @Deprecated
    public boolean isObjectLiked() {
        return this.isObjectLiked;
    }
    
    @Deprecated
    public boolean shouldEnableView() {
        return false;
    }
    
    @Deprecated
    public void toggleLike(final Activity activity, final FragmentWrapper fragmentWrapper, final Bundle bundle) {
        final boolean b = true;
        final boolean b2 = !this.isObjectLiked;
        if (this.canUseOGPublish()) {
            this.updateLikeState(b2);
            if (this.isPendingLikeOrUnlike) {
                this.getAppEventsLogger().logSdkEvent("fb_like_control_did_undo_quickly", (Double)null, bundle);
            }
            else if (!this.publishLikeOrUnlikeAsync(b2, bundle)) {
                this.updateLikeState(!b2 && b);
                this.presentLikeDialog(activity, fragmentWrapper, bundle);
            }
            return;
        }
        this.presentLikeDialog(activity, fragmentWrapper, bundle);
    }
    
    private abstract class AbstractRequestWrapper implements RequestWrapper
    {
        protected FacebookRequestError error;
        protected String objectId;
        protected LikeView.ObjectType objectType;
        private GraphRequest request;
        
        protected AbstractRequestWrapper(final String objectId, final LikeView.ObjectType objectType) {
            this.objectId = objectId;
            this.objectType = objectType;
        }
        
        @Override
        public void addToBatch(final GraphRequestBatch graphRequestBatch) {
            graphRequestBatch.add(this.request);
        }
        
        @Override
        public FacebookRequestError getError() {
            return this.error;
        }
        
        protected void processError(final FacebookRequestError facebookRequestError) {
            Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error running request for object '%s' with type '%s' : %s", new Object[] { this.objectId, this.objectType, facebookRequestError });
        }
        
        protected abstract void processSuccess(final GraphResponse p0);
        
        protected void setRequest(final GraphRequest request) {
            (this.request = request).setVersion(FacebookSdk.getGraphApiVersion());
            request.setCallback((GraphRequest$Callback)new GraphRequest$Callback() {
                public void onCompleted(final GraphResponse graphResponse) {
                    AbstractRequestWrapper.this.error = graphResponse.getError();
                    if (AbstractRequestWrapper.this.error != null) {
                        AbstractRequestWrapper.this.processError(AbstractRequestWrapper.this.error);
                        return;
                    }
                    AbstractRequestWrapper.this.processSuccess(graphResponse);
                }
            });
        }
    }
    
    private static class CreateLikeActionControllerWorkItem implements Runnable
    {
        private CreationCallback callback;
        private String objectId;
        private LikeView.ObjectType objectType;
        
        CreateLikeActionControllerWorkItem(final String objectId, final LikeView.ObjectType objectType, final CreationCallback callback) {
            this.objectId = objectId;
            this.objectType = objectType;
            this.callback = callback;
        }
        
        @Override
        public void run() {
            createControllerForObjectIdAndType(this.objectId, this.objectType, this.callback);
        }
    }
    
    @Deprecated
    public interface CreationCallback
    {
        void onComplete(final LikeActionController p0, final FacebookException p1);
    }
    
    private class GetEngagementRequestWrapper extends AbstractRequestWrapper
    {
        String likeCountStringWithLike;
        String likeCountStringWithoutLike;
        String socialSentenceStringWithLike;
        String socialSentenceStringWithoutLike;
        
        GetEngagementRequestWrapper(final String s, final LikeView.ObjectType objectType) {
            super(s, objectType);
            this.likeCountStringWithLike = LikeActionController.this.likeCountStringWithLike;
            this.likeCountStringWithoutLike = LikeActionController.this.likeCountStringWithoutLike;
            this.socialSentenceStringWithLike = LikeActionController.this.socialSentenceWithLike;
            this.socialSentenceStringWithoutLike = LikeActionController.this.socialSentenceWithoutLike;
            final Bundle bundle = new Bundle();
            bundle.putString("fields", "engagement.fields(count_string_with_like,count_string_without_like,social_sentence_with_like,social_sentence_without_like)");
            bundle.putString("locale", Locale.getDefault().toString());
            ((AbstractRequestWrapper)this).setRequest(new GraphRequest(AccessToken.getCurrentAccessToken(), s, bundle, HttpMethod.GET));
        }
        
        @Override
        protected void processError(final FacebookRequestError facebookRequestError) {
            Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error fetching engagement for object '%s' with type '%s' : %s", new Object[] { this.objectId, this.objectType, facebookRequestError });
            LikeActionController.this.logAppEventForError("get_engagement", facebookRequestError);
        }
        
        @Override
        protected void processSuccess(final GraphResponse graphResponse) {
            final JSONObject tryGetJSONObjectFromResponse = Utility.tryGetJSONObjectFromResponse(graphResponse.getJSONObject(), "engagement");
            if (tryGetJSONObjectFromResponse != null) {
                this.likeCountStringWithLike = tryGetJSONObjectFromResponse.optString("count_string_with_like", this.likeCountStringWithLike);
                this.likeCountStringWithoutLike = tryGetJSONObjectFromResponse.optString("count_string_without_like", this.likeCountStringWithoutLike);
                this.socialSentenceStringWithLike = tryGetJSONObjectFromResponse.optString("social_sentence_with_like", this.socialSentenceStringWithLike);
                this.socialSentenceStringWithoutLike = tryGetJSONObjectFromResponse.optString("social_sentence_without_like", this.socialSentenceStringWithoutLike);
            }
        }
    }
    
    private class GetOGObjectIdRequestWrapper extends AbstractRequestWrapper
    {
        String verifiedObjectId;
        
        GetOGObjectIdRequestWrapper(final String s, final LikeView.ObjectType objectType) {
            super(s, objectType);
            final Bundle bundle = new Bundle();
            bundle.putString("fields", "og_object.fields(id)");
            bundle.putString("ids", s);
            ((AbstractRequestWrapper)this).setRequest(new GraphRequest(AccessToken.getCurrentAccessToken(), "", bundle, HttpMethod.GET));
        }
        
        @Override
        protected void processError(final FacebookRequestError facebookRequestError) {
            if (facebookRequestError.getErrorMessage().contains("og_object")) {
                this.error = null;
                return;
            }
            Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error getting the FB id for object '%s' with type '%s' : %s", new Object[] { this.objectId, this.objectType, facebookRequestError });
        }
        
        @Override
        protected void processSuccess(final GraphResponse graphResponse) {
            final JSONObject tryGetJSONObjectFromResponse = Utility.tryGetJSONObjectFromResponse(graphResponse.getJSONObject(), this.objectId);
            if (tryGetJSONObjectFromResponse != null) {
                final JSONObject optJSONObject = tryGetJSONObjectFromResponse.optJSONObject("og_object");
                if (optJSONObject != null) {
                    this.verifiedObjectId = optJSONObject.optString("id");
                }
            }
        }
    }
    
    private class GetOGObjectLikesRequestWrapper extends AbstractRequestWrapper implements LikeRequestWrapper
    {
        private final String objectId;
        private boolean objectIsLiked;
        private final LikeView.ObjectType objectType;
        private String unlikeToken;
        
        GetOGObjectLikesRequestWrapper(final String objectId, final LikeView.ObjectType objectType) {
            super(objectId, objectType);
            this.objectIsLiked = LikeActionController.this.isObjectLiked;
            this.objectId = objectId;
            this.objectType = objectType;
            final Bundle bundle = new Bundle();
            bundle.putString("fields", "id,application");
            bundle.putString("object", this.objectId);
            ((AbstractRequestWrapper)this).setRequest(new GraphRequest(AccessToken.getCurrentAccessToken(), "me/og.likes", bundle, HttpMethod.GET));
        }
        
        @Override
        public String getUnlikeToken() {
            return this.unlikeToken;
        }
        
        @Override
        public boolean isObjectLiked() {
            return this.objectIsLiked;
        }
        
        @Override
        protected void processError(final FacebookRequestError facebookRequestError) {
            Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error fetching like status for object '%s' with type '%s' : %s", new Object[] { this.objectId, this.objectType, facebookRequestError });
            LikeActionController.this.logAppEventForError("get_og_object_like", facebookRequestError);
        }
        
        @Override
        protected void processSuccess(final GraphResponse graphResponse) {
            final JSONArray tryGetJSONArrayFromResponse = Utility.tryGetJSONArrayFromResponse(graphResponse.getJSONObject(), "data");
            if (tryGetJSONArrayFromResponse != null) {
                for (int i = 0; i < tryGetJSONArrayFromResponse.length(); ++i) {
                    final JSONObject optJSONObject = tryGetJSONArrayFromResponse.optJSONObject(i);
                    if (optJSONObject != null) {
                        this.objectIsLiked = true;
                        final JSONObject optJSONObject2 = optJSONObject.optJSONObject("application");
                        final AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
                        if (optJSONObject2 != null && AccessToken.isCurrentAccessTokenActive() && Utility.areObjectsEqual((Object)currentAccessToken.getApplicationId(), (Object)optJSONObject2.optString("id"))) {
                            this.unlikeToken = optJSONObject.optString("id");
                        }
                    }
                }
            }
        }
    }
    
    private class GetPageIdRequestWrapper extends AbstractRequestWrapper
    {
        boolean objectIsPage;
        String verifiedObjectId;
        
        GetPageIdRequestWrapper(final String s, final LikeView.ObjectType objectType) {
            super(s, objectType);
            final Bundle bundle = new Bundle();
            bundle.putString("fields", "id");
            bundle.putString("ids", s);
            ((AbstractRequestWrapper)this).setRequest(new GraphRequest(AccessToken.getCurrentAccessToken(), "", bundle, HttpMethod.GET));
        }
        
        @Override
        protected void processError(final FacebookRequestError facebookRequestError) {
            Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error getting the FB id for object '%s' with type '%s' : %s", new Object[] { this.objectId, this.objectType, facebookRequestError });
        }
        
        @Override
        protected void processSuccess(final GraphResponse graphResponse) {
            final JSONObject tryGetJSONObjectFromResponse = Utility.tryGetJSONObjectFromResponse(graphResponse.getJSONObject(), this.objectId);
            if (tryGetJSONObjectFromResponse != null) {
                this.verifiedObjectId = tryGetJSONObjectFromResponse.optString("id");
                this.objectIsPage = !Utility.isNullOrEmpty(this.verifiedObjectId);
            }
        }
    }
    
    private class GetPageLikesRequestWrapper extends AbstractRequestWrapper implements LikeRequestWrapper
    {
        private boolean objectIsLiked;
        private String pageId;
        
        GetPageLikesRequestWrapper(final String pageId) {
            super(pageId, LikeView.ObjectType.PAGE);
            this.objectIsLiked = LikeActionController.this.isObjectLiked;
            this.pageId = pageId;
            final Bundle bundle = new Bundle();
            bundle.putString("fields", "id");
            ((AbstractRequestWrapper)this).setRequest(new GraphRequest(AccessToken.getCurrentAccessToken(), "me/likes/" + pageId, bundle, HttpMethod.GET));
        }
        
        @Override
        public String getUnlikeToken() {
            return null;
        }
        
        @Override
        public boolean isObjectLiked() {
            return this.objectIsLiked;
        }
        
        @Override
        protected void processError(final FacebookRequestError facebookRequestError) {
            Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error fetching like status for page id '%s': %s", new Object[] { this.pageId, facebookRequestError });
            LikeActionController.this.logAppEventForError("get_page_like", facebookRequestError);
        }
        
        @Override
        protected void processSuccess(final GraphResponse graphResponse) {
            final JSONArray tryGetJSONArrayFromResponse = Utility.tryGetJSONArrayFromResponse(graphResponse.getJSONObject(), "data");
            if (tryGetJSONArrayFromResponse != null && tryGetJSONArrayFromResponse.length() > 0) {
                this.objectIsLiked = true;
            }
        }
    }
    
    private interface LikeRequestWrapper extends RequestWrapper
    {
        String getUnlikeToken();
        
        boolean isObjectLiked();
    }
    
    private static class MRUCacheWorkItem implements Runnable
    {
        private static ArrayList<String> mruCachedItems;
        private String cacheItem;
        private boolean shouldTrim;
        
        static {
            MRUCacheWorkItem.mruCachedItems = new ArrayList<String>();
        }
        
        MRUCacheWorkItem(final String cacheItem, final boolean shouldTrim) {
            this.cacheItem = cacheItem;
            this.shouldTrim = shouldTrim;
        }
        
        @Override
        public void run() {
            if (this.cacheItem != null) {
                MRUCacheWorkItem.mruCachedItems.remove(this.cacheItem);
                MRUCacheWorkItem.mruCachedItems.add(0, this.cacheItem);
            }
            if (this.shouldTrim && MRUCacheWorkItem.mruCachedItems.size() >= 128) {
                while (64 < MRUCacheWorkItem.mruCachedItems.size()) {
                    LikeActionController.cache.remove(MRUCacheWorkItem.mruCachedItems.remove(MRUCacheWorkItem.mruCachedItems.size() - 1));
                }
            }
        }
    }
    
    private class PublishLikeRequestWrapper extends AbstractRequestWrapper
    {
        String unlikeToken;
        
        PublishLikeRequestWrapper(final String s, final LikeView.ObjectType objectType) {
            super(s, objectType);
            final Bundle bundle = new Bundle();
            bundle.putString("object", s);
            ((AbstractRequestWrapper)this).setRequest(new GraphRequest(AccessToken.getCurrentAccessToken(), "me/og.likes", bundle, HttpMethod.POST));
        }
        
        @Override
        protected void processError(final FacebookRequestError facebookRequestError) {
            if (facebookRequestError.getErrorCode() == 3501) {
                this.error = null;
                return;
            }
            Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error liking object '%s' with type '%s' : %s", new Object[] { this.objectId, this.objectType, facebookRequestError });
            LikeActionController.this.logAppEventForError("publish_like", facebookRequestError);
        }
        
        @Override
        protected void processSuccess(final GraphResponse graphResponse) {
            this.unlikeToken = Utility.safeGetStringFromResponse(graphResponse.getJSONObject(), "id");
        }
    }
    
    private class PublishUnlikeRequestWrapper extends AbstractRequestWrapper
    {
        private String unlikeToken;
        
        PublishUnlikeRequestWrapper(final String unlikeToken) {
            super(null, null);
            this.unlikeToken = unlikeToken;
            ((AbstractRequestWrapper)this).setRequest(new GraphRequest(AccessToken.getCurrentAccessToken(), unlikeToken, (Bundle)null, HttpMethod.DELETE));
        }
        
        @Override
        protected void processError(final FacebookRequestError facebookRequestError) {
            Logger.log(LoggingBehavior.REQUESTS, LikeActionController.TAG, "Error unliking object with unlike token '%s' : %s", new Object[] { this.unlikeToken, facebookRequestError });
            LikeActionController.this.logAppEventForError("publish_unlike", facebookRequestError);
        }
        
        @Override
        protected void processSuccess(final GraphResponse graphResponse) {
        }
    }
    
    private interface RequestCompletionCallback
    {
        void onComplete();
    }
    
    private interface RequestWrapper
    {
        void addToBatch(final GraphRequestBatch p0);
        
        FacebookRequestError getError();
    }
    
    private static class SerializeToDiskWorkItem implements Runnable
    {
        private String cacheKey;
        private String controllerJson;
        
        SerializeToDiskWorkItem(final String cacheKey, final String controllerJson) {
            this.cacheKey = cacheKey;
            this.controllerJson = controllerJson;
        }
        
        @Override
        public void run() {
            serializeToDiskSynchronously(this.cacheKey, this.controllerJson);
        }
    }
}
