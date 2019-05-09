// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import java.util.Set;
import com.facebook.share.model.ShareOpenGraphAction;
import java.util.HashSet;
import com.facebook.share.model.ShareOpenGraphContent;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import com.facebook.internal.CallbackManagerImpl$Callback;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.CallbackManager;
import android.os.ParcelFileDescriptor;
import java.io.FileNotFoundException;
import com.facebook.GraphRequest$ParcelableResourceWithMimeType;
import java.io.File;
import com.facebook.HttpMethod;
import android.os.Parcelable;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest$Callback;
import com.facebook.AccessToken;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.FacebookSdk;
import com.facebook.FacebookGraphResponseException;
import com.facebook.FacebookRequestError;
import com.facebook.GraphResponse;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.share.model.ShareVideoContent;
import java.util.Iterator;
import com.facebook.share.model.CameraEffectTextures;
import com.facebook.share.model.ShareCameraEffectContent;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.FacebookCallback;
import com.facebook.share.model.SharePhotoContent;
import android.support.annotation.Nullable;
import com.facebook.share.widget.LikeView;
import java.util.Collection;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility$Mapper;
import java.util.ArrayList;
import android.os.Bundle;
import java.util.List;
import com.facebook.share.model.ShareMediaContent;
import android.util.Pair;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.SharePhoto;
import com.facebook.internal.NativeAppCallAttachmentStore;
import android.graphics.Bitmap;
import android.net.Uri;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.AppCall;
import android.content.Intent;
import com.facebook.internal.NativeAppCallAttachmentStore$Attachment;
import com.facebook.share.model.ShareMedia;
import java.util.UUID;

public final class ShareInternalUtility
{
    public static final String MY_PHOTOS = "me/photos";
    private static final String MY_STAGING_RESOURCES = "me/staging_resources";
    private static final String STAGING_PARAM = "file";
    
    private static AppCall getAppCallFromActivityResult(final int n, final int n2, final Intent intent) {
        final UUID callIdFromIntent = NativeProtocol.getCallIdFromIntent(intent);
        if (callIdFromIntent == null) {
            return null;
        }
        return AppCall.finishPendingCall(callIdFromIntent, n);
    }
    
    private static NativeAppCallAttachmentStore$Attachment getAttachment(final UUID uuid, final Uri uri, final Bitmap bitmap) {
        final NativeAppCallAttachmentStore$Attachment nativeAppCallAttachmentStore$Attachment = null;
        NativeAppCallAttachmentStore$Attachment attachment;
        if (bitmap != null) {
            attachment = NativeAppCallAttachmentStore.createAttachment(uuid, bitmap);
        }
        else {
            attachment = nativeAppCallAttachmentStore$Attachment;
            if (uri != null) {
                return NativeAppCallAttachmentStore.createAttachment(uuid, uri);
            }
        }
        return attachment;
    }
    
    private static NativeAppCallAttachmentStore$Attachment getAttachment(final UUID uuid, final ShareMedia shareMedia) {
        final Bitmap bitmap = null;
        Uri uri = null;
        Bitmap bitmap2;
        if (shareMedia instanceof SharePhoto) {
            final SharePhoto sharePhoto = (SharePhoto)shareMedia;
            bitmap2 = sharePhoto.getBitmap();
            uri = sharePhoto.getImageUrl();
        }
        else {
            bitmap2 = bitmap;
            if (shareMedia instanceof ShareVideo) {
                uri = ((ShareVideo)shareMedia).getLocalUrl();
                bitmap2 = bitmap;
            }
        }
        return getAttachment(uuid, uri, bitmap2);
    }
    
    public static Pair<String, String> getFieldNameAndNamespaceFromFullName(String substring) {
        Object substring2 = null;
        final int index = substring.indexOf(58);
        if (index != -1 && substring.length() > index + 1) {
            substring2 = substring.substring(0, index);
            substring = substring.substring(index + 1);
        }
        return (Pair<String, String>)new Pair(substring2, (Object)substring);
    }
    
    public static List<Bundle> getMediaInfos(final ShareMediaContent shareMediaContent, final UUID uuid) {
        if (shareMediaContent != null) {
            final List<ShareMedia> media = shareMediaContent.getMedia();
            if (media != null) {
                final ArrayList list = new ArrayList();
                final List map = Utility.map((List)media, (Utility$Mapper)new Utility$Mapper<ShareMedia, Bundle>() {
                    public Bundle apply(final ShareMedia shareMedia) {
                        final NativeAppCallAttachmentStore$Attachment access$000 = getAttachment(uuid, shareMedia);
                        list.add(access$000);
                        final Bundle bundle = new Bundle();
                        bundle.putString("type", shareMedia.getMediaType().name());
                        bundle.putString("uri", access$000.getAttachmentUrl());
                        return bundle;
                    }
                });
                NativeAppCallAttachmentStore.addAttachments((Collection)list);
                return (List<Bundle>)map;
            }
        }
        return null;
    }
    
    @Nullable
    public static LikeView.ObjectType getMostSpecificObjectType(final LikeView.ObjectType objectType, final LikeView.ObjectType objectType2) {
        if (objectType != objectType2) {
            if (objectType == LikeView.ObjectType.UNKNOWN) {
                return objectType2;
            }
            if (objectType2 != LikeView.ObjectType.UNKNOWN) {
                return null;
            }
        }
        return objectType;
    }
    
    public static String getNativeDialogCompletionGesture(final Bundle bundle) {
        if (bundle.containsKey("completionGesture")) {
            return bundle.getString("completionGesture");
        }
        return bundle.getString("com.facebook.platform.extra.COMPLETION_GESTURE");
    }
    
    public static List<String> getPhotoUrls(final SharePhotoContent sharePhotoContent, final UUID uuid) {
        if (sharePhotoContent != null) {
            final List<SharePhoto> photos = sharePhotoContent.getPhotos();
            if (photos != null) {
                final List map = Utility.map((List)photos, (Utility$Mapper)new Utility$Mapper<SharePhoto, NativeAppCallAttachmentStore$Attachment>() {
                    public NativeAppCallAttachmentStore$Attachment apply(final SharePhoto sharePhoto) {
                        return getAttachment(uuid, sharePhoto);
                    }
                });
                final List map2 = Utility.map(map, (Utility$Mapper)new Utility$Mapper<NativeAppCallAttachmentStore$Attachment, String>() {
                    public String apply(final NativeAppCallAttachmentStore$Attachment nativeAppCallAttachmentStore$Attachment) {
                        return nativeAppCallAttachmentStore$Attachment.getAttachmentUrl();
                    }
                });
                NativeAppCallAttachmentStore.addAttachments((Collection)map);
                return (List<String>)map2;
            }
        }
        return null;
    }
    
    public static String getShareDialogPostId(final Bundle bundle) {
        if (bundle.containsKey("postId")) {
            return bundle.getString("postId");
        }
        if (bundle.containsKey("com.facebook.platform.extra.POST_ID")) {
            return bundle.getString("com.facebook.platform.extra.POST_ID");
        }
        return bundle.getString("post_id");
    }
    
    public static ResultProcessor getShareResultProcessor(final FacebookCallback<Sharer.Result> facebookCallback) {
        return new ResultProcessor(facebookCallback) {
            @Override
            public void onCancel(final AppCall appCall) {
                ShareInternalUtility.invokeOnCancelCallback(facebookCallback);
            }
            
            @Override
            public void onError(final AppCall appCall, final FacebookException ex) {
                ShareInternalUtility.invokeOnErrorCallback(facebookCallback, ex);
            }
            
            @Override
            public void onSuccess(final AppCall appCall, final Bundle bundle) {
                if (bundle != null) {
                    final String nativeDialogCompletionGesture = ShareInternalUtility.getNativeDialogCompletionGesture(bundle);
                    if (nativeDialogCompletionGesture == null || "post".equalsIgnoreCase(nativeDialogCompletionGesture)) {
                        ShareInternalUtility.invokeOnSuccessCallback(facebookCallback, ShareInternalUtility.getShareDialogPostId(bundle));
                    }
                    else {
                        if ("cancel".equalsIgnoreCase(nativeDialogCompletionGesture)) {
                            ShareInternalUtility.invokeOnCancelCallback(facebookCallback);
                            return;
                        }
                        ShareInternalUtility.invokeOnErrorCallback(facebookCallback, new FacebookException("UnknownError"));
                    }
                }
            }
        };
    }
    
    public static Bundle getTextureUrlBundle(final ShareCameraEffectContent shareCameraEffectContent, final UUID uuid) {
        if (shareCameraEffectContent != null) {
            final CameraEffectTextures textures = shareCameraEffectContent.getTextures();
            if (textures != null) {
                final Bundle bundle = new Bundle();
                final ArrayList<NativeAppCallAttachmentStore$Attachment> list = new ArrayList<NativeAppCallAttachmentStore$Attachment>();
                for (final String s : textures.keySet()) {
                    final NativeAppCallAttachmentStore$Attachment attachment = getAttachment(uuid, textures.getTextureUri(s), textures.getTextureBitmap(s));
                    list.add(attachment);
                    bundle.putString(s, attachment.getAttachmentUrl());
                }
                NativeAppCallAttachmentStore.addAttachments((Collection)list);
                return bundle;
            }
        }
        return null;
    }
    
    public static String getVideoUrl(final ShareVideoContent shareVideoContent, final UUID uuid) {
        if (shareVideoContent == null || shareVideoContent.getVideo() == null) {
            return null;
        }
        final NativeAppCallAttachmentStore$Attachment attachment = NativeAppCallAttachmentStore.createAttachment(uuid, shareVideoContent.getVideo().getLocalUrl());
        final ArrayList<NativeAppCallAttachmentStore$Attachment> list = new ArrayList<NativeAppCallAttachmentStore$Attachment>(1);
        list.add(attachment);
        NativeAppCallAttachmentStore.addAttachments((Collection)list);
        return attachment.getAttachmentUrl();
    }
    
    public static boolean handleActivityResult(final int n, final int n2, final Intent intent, final ResultProcessor resultProcessor) {
        boolean b = true;
        final AppCall appCallFromActivityResult = getAppCallFromActivityResult(n, n2, intent);
        if (appCallFromActivityResult == null) {
            b = false;
        }
        else {
            NativeAppCallAttachmentStore.cleanupAttachmentsForCall(appCallFromActivityResult.getCallId());
            if (resultProcessor != null) {
                final FacebookException exceptionFromErrorData = NativeProtocol.getExceptionFromErrorData(NativeProtocol.getErrorDataFromResultIntent(intent));
                if (exceptionFromErrorData == null) {
                    resultProcessor.onSuccess(appCallFromActivityResult, NativeProtocol.getSuccessResultsFromIntent(intent));
                    return true;
                }
                if (exceptionFromErrorData instanceof FacebookOperationCanceledException) {
                    resultProcessor.onCancel(appCallFromActivityResult);
                    return true;
                }
                resultProcessor.onError(appCallFromActivityResult, exceptionFromErrorData);
                return true;
            }
        }
        return b;
    }
    
    public static void invokeCallbackWithError(final FacebookCallback<Sharer.Result> facebookCallback, final String s) {
        invokeOnErrorCallback(facebookCallback, s);
    }
    
    public static void invokeCallbackWithException(final FacebookCallback<Sharer.Result> facebookCallback, final Exception ex) {
        if (ex instanceof FacebookException) {
            invokeOnErrorCallback(facebookCallback, (FacebookException)ex);
            return;
        }
        invokeCallbackWithError(facebookCallback, "Error preparing share content: " + ex.getLocalizedMessage());
    }
    
    public static void invokeCallbackWithResults(final FacebookCallback<Sharer.Result> facebookCallback, String errorMessage, final GraphResponse graphResponse) {
        final FacebookRequestError error = graphResponse.getError();
        if (error != null) {
            if (Utility.isNullOrEmpty(errorMessage = error.getErrorMessage())) {
                errorMessage = "Unexpected error sharing.";
            }
            invokeOnErrorCallback(facebookCallback, graphResponse, errorMessage);
            return;
        }
        invokeOnSuccessCallback(facebookCallback, errorMessage);
    }
    
    static void invokeOnCancelCallback(final FacebookCallback<Sharer.Result> facebookCallback) {
        logShareResult("cancelled", null);
        if (facebookCallback != null) {
            facebookCallback.onCancel();
        }
    }
    
    static void invokeOnErrorCallback(final FacebookCallback<Sharer.Result> facebookCallback, final FacebookException ex) {
        logShareResult("error", ex.getMessage());
        if (facebookCallback != null) {
            facebookCallback.onError(ex);
        }
    }
    
    static void invokeOnErrorCallback(final FacebookCallback<Sharer.Result> facebookCallback, final GraphResponse graphResponse, final String s) {
        logShareResult("error", s);
        if (facebookCallback != null) {
            facebookCallback.onError((FacebookException)new FacebookGraphResponseException(graphResponse, s));
        }
    }
    
    static void invokeOnErrorCallback(final FacebookCallback<Sharer.Result> facebookCallback, final String s) {
        logShareResult("error", s);
        if (facebookCallback != null) {
            facebookCallback.onError(new FacebookException(s));
        }
    }
    
    static void invokeOnSuccessCallback(final FacebookCallback<Sharer.Result> facebookCallback, final String s) {
        logShareResult("succeeded", null);
        if (facebookCallback != null) {
            facebookCallback.onSuccess(new Sharer.Result(s));
        }
    }
    
    private static void logShareResult(final String s, final String s2) {
        final AppEventsLogger logger = AppEventsLogger.newLogger(FacebookSdk.getApplicationContext());
        final Bundle bundle = new Bundle();
        bundle.putString("fb_share_dialog_outcome", s);
        if (s2 != null) {
            bundle.putString("error_message", s2);
        }
        logger.logSdkEvent("fb_share_dialog_result", (Double)null, bundle);
    }
    
    public static GraphRequest newUploadStagingResourceWithImageRequest(final AccessToken accessToken, final Bitmap bitmap, final GraphRequest$Callback graphRequest$Callback) {
        final Bundle bundle = new Bundle(1);
        bundle.putParcelable("file", (Parcelable)bitmap);
        return new GraphRequest(accessToken, "me/staging_resources", bundle, HttpMethod.POST, graphRequest$Callback);
    }
    
    public static GraphRequest newUploadStagingResourceWithImageRequest(final AccessToken accessToken, final Uri uri, final GraphRequest$Callback graphRequest$Callback) throws FileNotFoundException {
        if (Utility.isFileUri(uri)) {
            return newUploadStagingResourceWithImageRequest(accessToken, new File(uri.getPath()), graphRequest$Callback);
        }
        if (!Utility.isContentUri(uri)) {
            throw new FacebookException("The image Uri must be either a file:// or content:// Uri");
        }
        final GraphRequest$ParcelableResourceWithMimeType graphRequest$ParcelableResourceWithMimeType = new GraphRequest$ParcelableResourceWithMimeType((Parcelable)uri, "image/png");
        final Bundle bundle = new Bundle(1);
        bundle.putParcelable("file", (Parcelable)graphRequest$ParcelableResourceWithMimeType);
        return new GraphRequest(accessToken, "me/staging_resources", bundle, HttpMethod.POST, graphRequest$Callback);
    }
    
    public static GraphRequest newUploadStagingResourceWithImageRequest(final AccessToken accessToken, final File file, final GraphRequest$Callback graphRequest$Callback) throws FileNotFoundException {
        final GraphRequest$ParcelableResourceWithMimeType graphRequest$ParcelableResourceWithMimeType = new GraphRequest$ParcelableResourceWithMimeType((Parcelable)ParcelFileDescriptor.open(file, 268435456), "image/png");
        final Bundle bundle = new Bundle(1);
        bundle.putParcelable("file", (Parcelable)graphRequest$ParcelableResourceWithMimeType);
        return new GraphRequest(accessToken, "me/staging_resources", bundle, HttpMethod.POST, graphRequest$Callback);
    }
    
    public static void registerSharerCallback(final int n, final CallbackManager callbackManager, final FacebookCallback<Sharer.Result> facebookCallback) {
        if (!(callbackManager instanceof CallbackManagerImpl)) {
            throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
        }
        ((CallbackManagerImpl)callbackManager).registerCallback(n, (CallbackManagerImpl$Callback)new CallbackManagerImpl$Callback() {
            public boolean onActivityResult(final int n, final Intent intent) {
                return ShareInternalUtility.handleActivityResult(n, n, intent, ShareInternalUtility.getShareResultProcessor(facebookCallback));
            }
        });
    }
    
    public static void registerStaticShareCallback(final int n) {
        CallbackManagerImpl.registerStaticCallback(n, (CallbackManagerImpl$Callback)new CallbackManagerImpl$Callback() {
            public boolean onActivityResult(final int n, final Intent intent) {
                return ShareInternalUtility.handleActivityResult(n, n, intent, ShareInternalUtility.getShareResultProcessor(null));
            }
        });
    }
    
    public static JSONArray removeNamespacesFromOGJsonArray(final JSONArray jsonArray, final boolean b) throws JSONException {
        final JSONArray jsonArray2 = new JSONArray();
        for (int i = 0; i < jsonArray.length(); ++i) {
            final Object value = jsonArray.get(i);
            Object o;
            if (value instanceof JSONArray) {
                o = removeNamespacesFromOGJsonArray((JSONArray)value, b);
            }
            else {
                o = value;
                if (value instanceof JSONObject) {
                    o = removeNamespacesFromOGJsonObject((JSONObject)value, b);
                }
            }
            jsonArray2.put(o);
        }
        return jsonArray2;
    }
    
    public static JSONObject removeNamespacesFromOGJsonObject(JSONObject jsonObject, final boolean b) {
        if (jsonObject == null) {
            jsonObject = null;
        }
        else {
            JSONObject jsonObject2 = null;
            JSONObject jsonObject3;
            while (true) {
                while (true) {
                    int n = 0;
                    Label_0277: {
                        String string = null;
                        Object o = null;
                        String s = null;
                        String s2 = null;
                        Label_0212: {
                            try {
                                jsonObject2 = new JSONObject();
                                jsonObject3 = new JSONObject();
                                final JSONArray names = jsonObject.names();
                                n = 0;
                                if (n >= names.length()) {
                                    break;
                                }
                                string = names.getString(n);
                                final Object value = jsonObject.get(string);
                                if (value instanceof JSONObject) {
                                    o = removeNamespacesFromOGJsonObject((JSONObject)value, true);
                                }
                                else {
                                    o = value;
                                    if (value instanceof JSONArray) {
                                        o = removeNamespacesFromOGJsonArray((JSONArray)value, true);
                                    }
                                }
                                final Pair<String, String> fieldNameAndNamespaceFromFullName = getFieldNameAndNamespaceFromFullName(string);
                                s = (String)fieldNameAndNamespaceFromFullName.first;
                                s2 = (String)fieldNameAndNamespaceFromFullName.second;
                                if (!b) {
                                    break Label_0212;
                                }
                                if (s != null && s.equals("fbsdk")) {
                                    jsonObject2.put(string, o);
                                    break Label_0277;
                                }
                                if (s == null || s.equals("og")) {
                                    jsonObject2.put(s2, o);
                                    break Label_0277;
                                }
                            }
                            catch (JSONException ex) {
                                throw new FacebookException("Failed to create json object from share content");
                            }
                            jsonObject3.put(s2, o);
                            break Label_0277;
                        }
                        if (s != null && s.equals("fb")) {
                            jsonObject2.put(string, o);
                        }
                        else {
                            jsonObject2.put(s2, o);
                        }
                    }
                    ++n;
                    continue;
                }
            }
            jsonObject = jsonObject2;
            if (jsonObject3.length() > 0) {
                jsonObject2.put("data", (Object)jsonObject3);
                return jsonObject2;
            }
        }
        return jsonObject;
    }
    
    public static JSONObject toJSONObjectForCall(final UUID uuid, final ShareOpenGraphContent shareOpenGraphContent) throws JSONException {
        final ShareOpenGraphAction action = shareOpenGraphContent.getAction();
        final ArrayList list = new ArrayList();
        final JSONObject jsonObject = OpenGraphJSONUtility.toJSONObject(action, (OpenGraphJSONUtility.PhotoJSONProcessor)new OpenGraphJSONUtility.PhotoJSONProcessor() {
            @Override
            public JSONObject toJSONObject(final SharePhoto sharePhoto) {
                final NativeAppCallAttachmentStore$Attachment access$000 = getAttachment(uuid, sharePhoto);
                JSONObject jsonObject;
                if (access$000 == null) {
                    jsonObject = null;
                }
                else {
                    list.add(access$000);
                    final JSONObject jsonObject2 = new JSONObject();
                    try {
                        jsonObject2.put("url", (Object)access$000.getAttachmentUrl());
                        jsonObject = jsonObject2;
                        if (sharePhoto.getUserGenerated()) {
                            jsonObject2.put("user_generated", true);
                            return jsonObject2;
                        }
                    }
                    catch (JSONException ex) {
                        throw new FacebookException("Unable to attach images", (Throwable)ex);
                    }
                }
                return jsonObject;
            }
        });
        NativeAppCallAttachmentStore.addAttachments((Collection)list);
        if (shareOpenGraphContent.getPlaceId() != null && Utility.isNullOrEmpty(jsonObject.optString("place"))) {
            jsonObject.put("place", (Object)shareOpenGraphContent.getPlaceId());
        }
        if (shareOpenGraphContent.getPeopleIds() != null) {
            final JSONArray optJSONArray = jsonObject.optJSONArray("tags");
            Set<String> jsonArrayToSet;
            if (optJSONArray == null) {
                jsonArrayToSet = new HashSet<String>();
            }
            else {
                jsonArrayToSet = (Set<String>)Utility.jsonArrayToSet(optJSONArray);
            }
            final Iterator<String> iterator = shareOpenGraphContent.getPeopleIds().iterator();
            while (iterator.hasNext()) {
                jsonArrayToSet.add(iterator.next());
            }
            jsonObject.put("tags", (Object)new JSONArray((Collection)jsonArrayToSet));
        }
        return jsonObject;
    }
    
    public static JSONObject toJSONObjectForWeb(final ShareOpenGraphContent shareOpenGraphContent) throws JSONException {
        return OpenGraphJSONUtility.toJSONObject(shareOpenGraphContent.getAction(), (OpenGraphJSONUtility.PhotoJSONProcessor)new OpenGraphJSONUtility.PhotoJSONProcessor() {
            @Override
            public JSONObject toJSONObject(final SharePhoto sharePhoto) {
                final Uri imageUrl = sharePhoto.getImageUrl();
                if (!Utility.isWebUri(imageUrl)) {
                    throw new FacebookException("Only web images may be used in OG objects shared via the web dialog");
                }
                final JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("url", (Object)imageUrl.toString());
                    return jsonObject;
                }
                catch (JSONException ex) {
                    throw new FacebookException("Unable to attach images", (Throwable)ex);
                }
            }
        });
    }
}
