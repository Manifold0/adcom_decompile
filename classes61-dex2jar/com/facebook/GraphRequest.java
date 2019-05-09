// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import java.io.InputStream;
import android.os.ParcelFileDescriptor$AutoCloseInputStream;
import android.graphics.Bitmap$CompressFormat;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.facebook.internal.ServerProtocol;
import java.net.URLEncoder;
import android.text.TextUtils;
import android.util.Pair;
import java.util.ArrayList;
import org.json.JSONException;
import java.util.Map;
import java.util.HashMap;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;
import java.io.File;
import android.os.Parcelable;
import android.location.Location;
import org.json.JSONArray;
import com.facebook.internal.AttributionIdentifiers;
import android.content.Context;
import java.util.Date;
import android.os.ParcelFileDescriptor;
import android.graphics.Bitmap;
import java.util.regex.Matcher;
import com.facebook.internal.InternalSettings;
import android.os.Handler;
import java.util.Arrays;
import java.net.URLConnection;
import java.util.Collection;
import com.facebook.internal.Validate;
import java.util.List;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Iterator;
import android.net.Uri$Builder;
import java.util.Locale;
import android.net.Uri;
import android.util.Log;
import com.facebook.internal.Utility;
import com.facebook.internal.Logger;
import java.net.URL;
import android.os.Bundle;
import org.json.JSONObject;
import java.util.regex.Pattern;

public class GraphRequest
{
    private static final String ACCEPT_LANGUAGE_HEADER = "Accept-Language";
    public static final String ACCESS_TOKEN_PARAM = "access_token";
    private static final String ATTACHED_FILES_PARAM = "attached_files";
    private static final String ATTACHMENT_FILENAME_PREFIX = "file";
    private static final String BATCH_APP_ID_PARAM = "batch_app_id";
    private static final String BATCH_BODY_PARAM = "body";
    private static final String BATCH_ENTRY_DEPENDS_ON_PARAM = "depends_on";
    private static final String BATCH_ENTRY_NAME_PARAM = "name";
    private static final String BATCH_ENTRY_OMIT_RESPONSE_ON_SUCCESS_PARAM = "omit_response_on_success";
    private static final String BATCH_METHOD_PARAM = "method";
    private static final String BATCH_PARAM = "batch";
    private static final String BATCH_RELATIVE_URL_PARAM = "relative_url";
    private static final String CAPTION_PARAM = "caption";
    private static final String CONTENT_ENCODING_HEADER = "Content-Encoding";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String DEBUG_KEY = "__debug__";
    private static final String DEBUG_MESSAGES_KEY = "messages";
    private static final String DEBUG_MESSAGE_KEY = "message";
    private static final String DEBUG_MESSAGE_LINK_KEY = "link";
    private static final String DEBUG_MESSAGE_TYPE_KEY = "type";
    private static final String DEBUG_PARAM = "debug";
    private static final String DEBUG_SEVERITY_INFO = "info";
    private static final String DEBUG_SEVERITY_WARNING = "warning";
    public static final String FIELDS_PARAM = "fields";
    private static final String FORMAT_JSON = "json";
    private static final String FORMAT_PARAM = "format";
    private static final String GRAPH_PATH_FORMAT = "%s/%s";
    private static final String ISO_8601_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final int MAXIMUM_BATCH_SIZE = 50;
    private static final String ME = "me";
    private static final String MIME_BOUNDARY = "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f";
    private static final String MY_FRIENDS = "me/friends";
    private static final String MY_PHOTOS = "me/photos";
    private static final String PICTURE_PARAM = "picture";
    private static final String SDK_ANDROID = "android";
    private static final String SDK_PARAM = "sdk";
    private static final String SEARCH = "search";
    public static final String TAG;
    private static final String USER_AGENT_BASE = "FBAndroidSDK";
    private static final String USER_AGENT_HEADER = "User-Agent";
    private static final String VIDEOS_SUFFIX = "/videos";
    private static String defaultBatchApplicationId;
    private static volatile String userAgent;
    private static Pattern versionPattern;
    private AccessToken accessToken;
    private String batchEntryDependsOn;
    private String batchEntryName;
    private boolean batchEntryOmitResultOnSuccess;
    private Callback callback;
    private JSONObject graphObject;
    private String graphPath;
    private HttpMethod httpMethod;
    private String overriddenURL;
    private Bundle parameters;
    private boolean skipClientToken;
    private Object tag;
    private String version;
    
    static {
        TAG = GraphRequest.class.getSimpleName();
        GraphRequest.versionPattern = Pattern.compile("^/?v\\d+\\.\\d+/(.*)");
    }
    
    public GraphRequest() {
        this(null, null, null, null, null);
    }
    
    public GraphRequest(final AccessToken accessToken, final String s) {
        this(accessToken, s, null, null, null);
    }
    
    public GraphRequest(final AccessToken accessToken, final String s, final Bundle bundle, final HttpMethod httpMethod) {
        this(accessToken, s, bundle, httpMethod, null);
    }
    
    public GraphRequest(final AccessToken accessToken, final String s, final Bundle bundle, final HttpMethod httpMethod, final Callback callback) {
        this(accessToken, s, bundle, httpMethod, callback, null);
    }
    
    public GraphRequest(final AccessToken accessToken, final String graphPath, final Bundle bundle, final HttpMethod httpMethod, final Callback callback, final String version) {
        this.batchEntryOmitResultOnSuccess = true;
        this.skipClientToken = false;
        this.accessToken = accessToken;
        this.graphPath = graphPath;
        this.version = version;
        this.setCallback(callback);
        this.setHttpMethod(httpMethod);
        if (bundle != null) {
            this.parameters = new Bundle(bundle);
        }
        else {
            this.parameters = new Bundle();
        }
        if (this.version == null) {
            this.version = FacebookSdk.getGraphApiVersion();
        }
    }
    
    GraphRequest(final AccessToken accessToken, final URL url) {
        this.batchEntryOmitResultOnSuccess = true;
        this.skipClientToken = false;
        this.accessToken = accessToken;
        this.overriddenURL = url.toString();
        this.setHttpMethod(HttpMethod.GET);
        this.parameters = new Bundle();
    }
    
    private void addCommonParameters() {
        if (this.accessToken != null) {
            if (!this.parameters.containsKey("access_token")) {
                final String token = this.accessToken.getToken();
                Logger.registerAccessToken(token);
                this.parameters.putString("access_token", token);
            }
        }
        else if (!this.skipClientToken && !this.parameters.containsKey("access_token")) {
            final String applicationId = FacebookSdk.getApplicationId();
            final String clientToken = FacebookSdk.getClientToken();
            if (!Utility.isNullOrEmpty(applicationId) && !Utility.isNullOrEmpty(clientToken)) {
                this.parameters.putString("access_token", applicationId + "|" + clientToken);
            }
            else {
                Log.d(GraphRequest.TAG, "Warning: Request without access token missing application ID or client token.");
            }
        }
        this.parameters.putString("sdk", "android");
        this.parameters.putString("format", "json");
        if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.GRAPH_API_DEBUG_INFO)) {
            this.parameters.putString("debug", "info");
        }
        else if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.GRAPH_API_DEBUG_WARNING)) {
            this.parameters.putString("debug", "warning");
        }
    }
    
    private String appendParametersToBaseUrl(String value, final Boolean b) {
        if (!b && this.httpMethod == HttpMethod.POST) {
            return value;
        }
        final Uri$Builder buildUpon = Uri.parse(value).buildUpon();
        for (final String s : this.parameters.keySet()) {
            if ((value = (String)this.parameters.get(s)) == null) {
                value = "";
            }
            if (isSupportedParameterType(value)) {
                buildUpon.appendQueryParameter(s, parameterToString(value).toString());
            }
            else {
                if (this.httpMethod == HttpMethod.GET) {
                    throw new IllegalArgumentException(String.format(Locale.US, "Unsupported parameter type for GET request: %s", value.getClass().getSimpleName()));
                }
                continue;
            }
        }
        return buildUpon.toString();
    }
    
    private static HttpURLConnection createConnection(final URL url) throws IOException {
        final HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestProperty("User-Agent", getUserAgent());
        httpURLConnection.setRequestProperty("Accept-Language", Locale.getDefault().toString());
        httpURLConnection.setChunkedStreamingMode(0);
        return httpURLConnection;
    }
    
    public static GraphResponse executeAndWait(final GraphRequest graphRequest) {
        final List<GraphResponse> executeBatchAndWait = executeBatchAndWait(graphRequest);
        if (executeBatchAndWait == null || executeBatchAndWait.size() != 1) {
            throw new FacebookException("invalid state: expected a single response");
        }
        return executeBatchAndWait.get(0);
    }
    
    public static List<GraphResponse> executeBatchAndWait(GraphRequestBatch executeConnectionAndWait) {
        Validate.notEmptyAndContainsNoNulls((Collection<Object>)executeConnectionAndWait, "requests");
        URLConnection httpConnection;
        URLConnection urlConnection = httpConnection = null;
        try {
            urlConnection = (httpConnection = toHttpConnection(executeConnectionAndWait));
            executeConnectionAndWait = (GraphRequestBatch)executeConnectionAndWait((HttpURLConnection)urlConnection, executeConnectionAndWait);
            return (List<GraphResponse>)executeConnectionAndWait;
        }
        catch (Exception ex) {
            httpConnection = urlConnection;
            final List<GraphResponse> constructErrorResponses = GraphResponse.constructErrorResponses(executeConnectionAndWait.getRequests(), null, new FacebookException(ex));
            httpConnection = urlConnection;
            runCallbacks(executeConnectionAndWait, constructErrorResponses);
            Utility.disconnectQuietly(null);
            return constructErrorResponses;
        }
        finally {
            Utility.disconnectQuietly(httpConnection);
        }
    }
    
    public static List<GraphResponse> executeBatchAndWait(final Collection<GraphRequest> collection) {
        return executeBatchAndWait(new GraphRequestBatch(collection));
    }
    
    public static List<GraphResponse> executeBatchAndWait(final GraphRequest... array) {
        Validate.notNull(array, "requests");
        return executeBatchAndWait(Arrays.asList(array));
    }
    
    public static GraphRequestAsyncTask executeBatchAsync(final GraphRequestBatch graphRequestBatch) {
        Validate.notEmptyAndContainsNoNulls((Collection<Object>)graphRequestBatch, "requests");
        final GraphRequestAsyncTask graphRequestAsyncTask = new GraphRequestAsyncTask(graphRequestBatch);
        graphRequestAsyncTask.executeOnExecutor(FacebookSdk.getExecutor(), (Object[])new Void[0]);
        return graphRequestAsyncTask;
    }
    
    public static GraphRequestAsyncTask executeBatchAsync(final Collection<GraphRequest> collection) {
        return executeBatchAsync(new GraphRequestBatch(collection));
    }
    
    public static GraphRequestAsyncTask executeBatchAsync(final GraphRequest... array) {
        Validate.notNull(array, "requests");
        return executeBatchAsync(Arrays.asList(array));
    }
    
    public static List<GraphResponse> executeConnectionAndWait(final HttpURLConnection httpURLConnection, final GraphRequestBatch graphRequestBatch) {
        final List<GraphResponse> fromHttpConnection = GraphResponse.fromHttpConnection(httpURLConnection, graphRequestBatch);
        Utility.disconnectQuietly(httpURLConnection);
        final int size = graphRequestBatch.size();
        if (size != fromHttpConnection.size()) {
            throw new FacebookException(String.format(Locale.US, "Received %d responses while expecting %d", fromHttpConnection.size(), size));
        }
        runCallbacks(graphRequestBatch, fromHttpConnection);
        AccessTokenManager.getInstance().extendAccessTokenIfNeeded();
        return fromHttpConnection;
    }
    
    public static List<GraphResponse> executeConnectionAndWait(final HttpURLConnection httpURLConnection, final Collection<GraphRequest> collection) {
        return executeConnectionAndWait(httpURLConnection, new GraphRequestBatch(collection));
    }
    
    public static GraphRequestAsyncTask executeConnectionAsync(final Handler callbackHandler, final HttpURLConnection httpURLConnection, final GraphRequestBatch graphRequestBatch) {
        Validate.notNull(httpURLConnection, "connection");
        final GraphRequestAsyncTask graphRequestAsyncTask = new GraphRequestAsyncTask(httpURLConnection, graphRequestBatch);
        graphRequestBatch.setCallbackHandler(callbackHandler);
        graphRequestAsyncTask.executeOnExecutor(FacebookSdk.getExecutor(), (Object[])new Void[0]);
        return graphRequestAsyncTask;
    }
    
    public static GraphRequestAsyncTask executeConnectionAsync(final HttpURLConnection httpURLConnection, final GraphRequestBatch graphRequestBatch) {
        return executeConnectionAsync(null, httpURLConnection, graphRequestBatch);
    }
    
    private static String getBatchAppId(final GraphRequestBatch graphRequestBatch) {
        if (!Utility.isNullOrEmpty(graphRequestBatch.getBatchApplicationId())) {
            return graphRequestBatch.getBatchApplicationId();
        }
        final Iterator<GraphRequest> iterator = graphRequestBatch.iterator();
        while (iterator.hasNext()) {
            final AccessToken accessToken = iterator.next().accessToken;
            if (accessToken != null) {
                final String applicationId = accessToken.getApplicationId();
                if (applicationId != null) {
                    return applicationId;
                }
                continue;
            }
        }
        if (!Utility.isNullOrEmpty(GraphRequest.defaultBatchApplicationId)) {
            return GraphRequest.defaultBatchApplicationId;
        }
        return FacebookSdk.getApplicationId();
    }
    
    public static final String getDefaultBatchApplicationId() {
        return GraphRequest.defaultBatchApplicationId;
    }
    
    private static String getDefaultPhotoPathIfNull(final String s) {
        String s2 = s;
        if (s == null) {
            s2 = "me/photos";
        }
        return s2;
    }
    
    private String getGraphPathWithVersion() {
        if (GraphRequest.versionPattern.matcher(this.graphPath).matches()) {
            return this.graphPath;
        }
        return String.format("%s/%s", this.version, this.graphPath);
    }
    
    private static String getMimeContentType() {
        return String.format("multipart/form-data; boundary=%s", "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
    }
    
    private static String getUserAgent() {
        if (GraphRequest.userAgent == null) {
            GraphRequest.userAgent = String.format("%s.%s", "FBAndroidSDK", "4.33.0");
            final String customUserAgent = InternalSettings.getCustomUserAgent();
            if (!Utility.isNullOrEmpty(customUserAgent)) {
                GraphRequest.userAgent = String.format(Locale.ROOT, "%s/%s", GraphRequest.userAgent, customUserAgent);
            }
        }
        return GraphRequest.userAgent;
    }
    
    private static boolean hasOnProgressCallbacks(final GraphRequestBatch graphRequestBatch) {
        final Iterator<GraphRequestBatch.Callback> iterator = graphRequestBatch.getCallbacks().iterator();
        while (iterator.hasNext()) {
            if (((GraphRequestBatch.Callback)iterator.next()) instanceof GraphRequestBatch.OnProgressCallback) {
                return true;
            }
        }
        final Iterator<GraphRequest> iterator2 = graphRequestBatch.iterator();
        while (iterator2.hasNext()) {
            if (iterator2.next().getCallback() instanceof OnProgressCallback) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean isGzipCompressible(final GraphRequestBatch graphRequestBatch) {
        for (final GraphRequest graphRequest : graphRequestBatch) {
            final Iterator iterator2 = graphRequest.parameters.keySet().iterator();
            while (iterator2.hasNext()) {
                if (isSupportedAttachmentType(graphRequest.parameters.get((String)iterator2.next()))) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private static boolean isMeRequest(String group) {
        final Matcher matcher = GraphRequest.versionPattern.matcher(group);
        if (matcher.matches()) {
            group = matcher.group(1);
        }
        return group.startsWith("me/") || group.startsWith("/me/");
    }
    
    private static boolean isSupportedAttachmentType(final Object o) {
        return o instanceof Bitmap || o instanceof byte[] || o instanceof Uri || o instanceof ParcelFileDescriptor || o instanceof ParcelableResourceWithMimeType;
    }
    
    private static boolean isSupportedParameterType(final Object o) {
        return o instanceof String || o instanceof Boolean || o instanceof Number || o instanceof Date;
    }
    
    public static GraphRequest newCustomAudienceThirdPartyIdRequest(final AccessToken accessToken, final Context context, final Callback callback) {
        return newCustomAudienceThirdPartyIdRequest(accessToken, context, null, callback);
    }
    
    public static GraphRequest newCustomAudienceThirdPartyIdRequest(final AccessToken accessToken, final Context context, String s, final Callback callback) {
        String applicationId = s;
        if (s == null) {
            applicationId = s;
            if (accessToken != null) {
                applicationId = accessToken.getApplicationId();
            }
        }
        if ((s = applicationId) == null) {
            s = Utility.getMetadataApplicationId(context);
        }
        if (s == null) {
            throw new FacebookException("Facebook App ID cannot be determined");
        }
        final String string = s + "/custom_audience_third_party_id";
        final AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(context);
        final Bundle bundle = new Bundle();
        if (accessToken == null) {
            if (attributionIdentifiers == null) {
                throw new FacebookException("There is no access token and attribution identifiers could not be retrieved");
            }
            if (attributionIdentifiers.getAttributionId() != null) {
                s = attributionIdentifiers.getAttributionId();
            }
            else {
                s = attributionIdentifiers.getAndroidAdvertiserId();
            }
            if (attributionIdentifiers.getAttributionId() != null) {
                bundle.putString("udid", s);
            }
        }
        if (FacebookSdk.getLimitEventAndDataUsage(context) || (attributionIdentifiers != null && attributionIdentifiers.isTrackingLimited())) {
            bundle.putString("limit_event_usage", "1");
        }
        return new GraphRequest(accessToken, string, bundle, HttpMethod.GET, callback);
    }
    
    public static GraphRequest newDeleteObjectRequest(final AccessToken accessToken, final String s, final Callback callback) {
        return new GraphRequest(accessToken, s, null, HttpMethod.DELETE, callback);
    }
    
    public static GraphRequest newGraphPathRequest(final AccessToken accessToken, final String s, final Callback callback) {
        return new GraphRequest(accessToken, s, null, null, callback);
    }
    
    public static GraphRequest newMeRequest(final AccessToken accessToken, final GraphJSONObjectCallback graphJSONObjectCallback) {
        return new GraphRequest(accessToken, "me", null, null, (Callback)new Callback() {
            @Override
            public void onCompleted(final GraphResponse graphResponse) {
                if (graphJSONObjectCallback != null) {
                    graphJSONObjectCallback.onCompleted(graphResponse.getJSONObject(), graphResponse);
                }
            }
        });
    }
    
    public static GraphRequest newMyFriendsRequest(final AccessToken accessToken, final GraphJSONArrayCallback graphJSONArrayCallback) {
        return new GraphRequest(accessToken, "me/friends", null, null, (Callback)new Callback() {
            @Override
            public void onCompleted(final GraphResponse graphResponse) {
                if (graphJSONArrayCallback != null) {
                    final JSONObject jsonObject = graphResponse.getJSONObject();
                    JSONArray optJSONArray;
                    if (jsonObject != null) {
                        optJSONArray = jsonObject.optJSONArray("data");
                    }
                    else {
                        optJSONArray = null;
                    }
                    graphJSONArrayCallback.onCompleted(optJSONArray, graphResponse);
                }
            }
        });
    }
    
    public static GraphRequest newPlacesSearchRequest(final AccessToken accessToken, final Location location, final int n, final int n2, final String s, final GraphJSONArrayCallback graphJSONArrayCallback) {
        if (location == null && Utility.isNullOrEmpty(s)) {
            throw new FacebookException("Either location or searchText must be specified.");
        }
        final Bundle bundle = new Bundle(5);
        bundle.putString("type", "place");
        bundle.putInt("limit", n2);
        if (location != null) {
            bundle.putString("center", String.format(Locale.US, "%f,%f", location.getLatitude(), location.getLongitude()));
            bundle.putInt("distance", n);
        }
        if (!Utility.isNullOrEmpty(s)) {
            bundle.putString("q", s);
        }
        return new GraphRequest(accessToken, "search", bundle, HttpMethod.GET, (Callback)new Callback() {
            @Override
            public void onCompleted(final GraphResponse graphResponse) {
                if (graphJSONArrayCallback != null) {
                    final JSONObject jsonObject = graphResponse.getJSONObject();
                    JSONArray optJSONArray;
                    if (jsonObject != null) {
                        optJSONArray = jsonObject.optJSONArray("data");
                    }
                    else {
                        optJSONArray = null;
                    }
                    graphJSONArrayCallback.onCompleted(optJSONArray, graphResponse);
                }
            }
        });
    }
    
    public static GraphRequest newPostRequest(final AccessToken accessToken, final String s, final JSONObject graphObject, final Callback callback) {
        final GraphRequest graphRequest = new GraphRequest(accessToken, s, null, HttpMethod.POST, callback);
        graphRequest.setGraphObject(graphObject);
        return graphRequest;
    }
    
    public static GraphRequest newUploadPhotoRequest(final AccessToken accessToken, String defaultPhotoPathIfNull, final Bitmap bitmap, final String s, final Bundle bundle, final Callback callback) {
        defaultPhotoPathIfNull = getDefaultPhotoPathIfNull(defaultPhotoPathIfNull);
        final Bundle bundle2 = new Bundle();
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        bundle2.putParcelable("picture", (Parcelable)bitmap);
        if (s != null && !s.isEmpty()) {
            bundle2.putString("caption", s);
        }
        return new GraphRequest(accessToken, defaultPhotoPathIfNull, bundle2, HttpMethod.POST, callback);
    }
    
    public static GraphRequest newUploadPhotoRequest(final AccessToken accessToken, String defaultPhotoPathIfNull, final Uri uri, final String s, final Bundle bundle, final Callback callback) throws FileNotFoundException {
        defaultPhotoPathIfNull = getDefaultPhotoPathIfNull(defaultPhotoPathIfNull);
        if (Utility.isFileUri(uri)) {
            return newUploadPhotoRequest(accessToken, defaultPhotoPathIfNull, new File(uri.getPath()), s, bundle, callback);
        }
        if (!Utility.isContentUri(uri)) {
            throw new FacebookException("The photo Uri must be either a file:// or content:// Uri");
        }
        final Bundle bundle2 = new Bundle();
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        bundle2.putParcelable("picture", (Parcelable)uri);
        if (s != null && !s.isEmpty()) {
            bundle2.putString("caption", s);
        }
        return new GraphRequest(accessToken, defaultPhotoPathIfNull, bundle2, HttpMethod.POST, callback);
    }
    
    public static GraphRequest newUploadPhotoRequest(final AccessToken accessToken, String defaultPhotoPathIfNull, final File file, final String s, final Bundle bundle, final Callback callback) throws FileNotFoundException {
        defaultPhotoPathIfNull = getDefaultPhotoPathIfNull(defaultPhotoPathIfNull);
        final ParcelFileDescriptor open = ParcelFileDescriptor.open(file, 268435456);
        final Bundle bundle2 = new Bundle();
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        bundle2.putParcelable("picture", (Parcelable)open);
        if (s != null && !s.isEmpty()) {
            bundle2.putString("caption", s);
        }
        return new GraphRequest(accessToken, defaultPhotoPathIfNull, bundle2, HttpMethod.POST, callback);
    }
    
    private static String parameterToString(final Object o) {
        if (o instanceof String) {
            return (String)o;
        }
        if (o instanceof Boolean || o instanceof Number) {
            return o.toString();
        }
        if (o instanceof Date) {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format(o);
        }
        throw new IllegalArgumentException("Unsupported parameter type.");
    }
    
    private static void processGraphObject(final JSONObject jsonObject, final String s, final KeyValueSerializer keyValueSerializer) throws IOException {
        boolean b = false;
        if (isMeRequest(s)) {
            final int index = s.indexOf(":");
            final int index2 = s.indexOf("?");
            if (index > 3 && (index2 == -1 || index < index2)) {
                b = true;
            }
            else {
                b = false;
            }
        }
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s2 = keys.next();
            processGraphObjectProperty(s2, jsonObject.opt(s2), keyValueSerializer, b && s2.equalsIgnoreCase("image"));
        }
    }
    
    private static void processGraphObjectProperty(final String s, final Object o, final KeyValueSerializer keyValueSerializer, final boolean b) throws IOException {
        final Class<?> class1 = o.getClass();
        if (JSONObject.class.isAssignableFrom(class1)) {
            final JSONObject jsonObject = (JSONObject)o;
            if (b) {
                final Iterator keys = jsonObject.keys();
                while (keys.hasNext()) {
                    final String s2 = keys.next();
                    processGraphObjectProperty(String.format("%s[%s]", s, s2), jsonObject.opt(s2), keyValueSerializer, b);
                }
            }
            else if (jsonObject.has("id")) {
                processGraphObjectProperty(s, jsonObject.optString("id"), keyValueSerializer, b);
            }
            else {
                if (jsonObject.has("url")) {
                    processGraphObjectProperty(s, jsonObject.optString("url"), keyValueSerializer, b);
                    return;
                }
                if (jsonObject.has("fbsdk:create_object")) {
                    processGraphObjectProperty(s, jsonObject.toString(), keyValueSerializer, b);
                }
            }
        }
        else if (JSONArray.class.isAssignableFrom(class1)) {
            final JSONArray jsonArray = (JSONArray)o;
            for (int length = jsonArray.length(), i = 0; i < length; ++i) {
                processGraphObjectProperty(String.format(Locale.ROOT, "%s[%d]", s, i), jsonArray.opt(i), keyValueSerializer, b);
            }
        }
        else {
            if (String.class.isAssignableFrom(class1) || Number.class.isAssignableFrom(class1) || Boolean.class.isAssignableFrom(class1)) {
                keyValueSerializer.writeString(s, o.toString());
                return;
            }
            if (Date.class.isAssignableFrom(class1)) {
                keyValueSerializer.writeString(s, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format((Date)o));
            }
        }
    }
    
    private static void processRequest(final GraphRequestBatch graphRequestBatch, final Logger logger, final int n, final URL url, final OutputStream outputStream, final boolean b) throws IOException, JSONException {
        final Serializer serializer = new Serializer(outputStream, logger, b);
        if (n == 1) {
            final GraphRequest value = graphRequestBatch.get(0);
            final HashMap<String, Attachment> hashMap = new HashMap<String, Attachment>();
            for (final String s : value.parameters.keySet()) {
                final Object value2 = value.parameters.get(s);
                if (isSupportedAttachmentType(value2)) {
                    hashMap.put(s, new Attachment(value, value2));
                }
            }
            if (logger != null) {
                logger.append("  Parameters:\n");
            }
            serializeParameters(value.parameters, serializer, value);
            if (logger != null) {
                logger.append("  Attachments:\n");
            }
            serializeAttachments(hashMap, serializer);
            if (value.graphObject != null) {
                processGraphObject(value.graphObject, url.getPath(), (KeyValueSerializer)serializer);
            }
            return;
        }
        final String batchAppId = getBatchAppId(graphRequestBatch);
        if (Utility.isNullOrEmpty(batchAppId)) {
            throw new FacebookException("App ID was not specified at the request or Settings.");
        }
        serializer.writeString("batch_app_id", batchAppId);
        final HashMap<String, Attachment> hashMap2 = new HashMap<String, Attachment>();
        serializeRequestsAsJSON(serializer, graphRequestBatch, hashMap2);
        if (logger != null) {
            logger.append("  Attachments:\n");
        }
        serializeAttachments(hashMap2, serializer);
    }
    
    static void runCallbacks(final GraphRequestBatch graphRequestBatch, final List<GraphResponse> list) {
        final int size = graphRequestBatch.size();
        final ArrayList<Pair> list2 = new ArrayList<Pair>();
        for (int i = 0; i < size; ++i) {
            final GraphRequest value = graphRequestBatch.get(i);
            if (value.callback != null) {
                list2.add(new Pair((Object)value.callback, (Object)list.get(i)));
            }
        }
        if (list2.size() > 0) {
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    for (final Pair pair : list2) {
                        ((Callback)pair.first).onCompleted((GraphResponse)pair.second);
                    }
                    final Iterator<GraphRequestBatch.Callback> iterator2 = graphRequestBatch.getCallbacks().iterator();
                    while (iterator2.hasNext()) {
                        ((GraphRequestBatch.Callback)iterator2.next()).onBatchCompleted(graphRequestBatch);
                    }
                }
            };
            final Handler callbackHandler = graphRequestBatch.getCallbackHandler();
            if (callbackHandler != null) {
                callbackHandler.post((Runnable)runnable);
                return;
            }
            runnable.run();
        }
    }
    
    private static void serializeAttachments(final Map<String, Attachment> map, final Serializer serializer) throws IOException {
        for (final String s : map.keySet()) {
            final Attachment attachment = map.get(s);
            if (isSupportedAttachmentType(attachment.getValue())) {
                serializer.writeObject(s, attachment.getValue(), attachment.getRequest());
            }
        }
    }
    
    private static void serializeParameters(final Bundle bundle, final Serializer serializer, final GraphRequest graphRequest) throws IOException {
        for (final String s : bundle.keySet()) {
            final Object value = bundle.get(s);
            if (isSupportedParameterType(value)) {
                serializer.writeObject(s, value, graphRequest);
            }
        }
    }
    
    private static void serializeRequestsAsJSON(final Serializer serializer, final Collection<GraphRequest> collection, final Map<String, Attachment> map) throws JSONException, IOException {
        final JSONArray jsonArray = new JSONArray();
        final Iterator<GraphRequest> iterator = collection.iterator();
        while (iterator.hasNext()) {
            iterator.next().serializeToBatch(jsonArray, map);
        }
        serializer.writeRequestsAsJson("batch", jsonArray, collection);
    }
    
    private void serializeToBatch(final JSONArray jsonArray, final Map<String, Attachment> map) throws JSONException, IOException {
        final JSONObject jsonObject = new JSONObject();
        if (this.batchEntryName != null) {
            jsonObject.put("name", (Object)this.batchEntryName);
            jsonObject.put("omit_response_on_success", this.batchEntryOmitResultOnSuccess);
        }
        if (this.batchEntryDependsOn != null) {
            jsonObject.put("depends_on", (Object)this.batchEntryDependsOn);
        }
        final String relativeUrlForBatchedRequest = this.getRelativeUrlForBatchedRequest();
        jsonObject.put("relative_url", (Object)relativeUrlForBatchedRequest);
        jsonObject.put("method", (Object)this.httpMethod);
        if (this.accessToken != null) {
            Logger.registerAccessToken(this.accessToken.getToken());
        }
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<String> iterator = this.parameters.keySet().iterator();
        while (iterator.hasNext()) {
            final Object value = this.parameters.get((String)iterator.next());
            if (isSupportedAttachmentType(value)) {
                final String format = String.format(Locale.ROOT, "%s%d", "file", map.size());
                list.add(format);
                map.put(format, new Attachment(this, value));
            }
        }
        if (!list.isEmpty()) {
            jsonObject.put("attached_files", (Object)TextUtils.join((CharSequence)",", (Iterable)list));
        }
        if (this.graphObject != null) {
            final ArrayList list2 = new ArrayList();
            processGraphObject(this.graphObject, relativeUrlForBatchedRequest, (KeyValueSerializer)new KeyValueSerializer() {
                @Override
                public void writeString(final String s, final String s2) throws IOException {
                    list2.add(String.format(Locale.US, "%s=%s", s, URLEncoder.encode(s2, "UTF-8")));
                }
            });
            jsonObject.put("body", (Object)TextUtils.join((CharSequence)"&", (Iterable)list2));
        }
        jsonArray.put((Object)jsonObject);
    }
    
    static final void serializeToUrlConnection(final GraphRequestBatch p0, final HttpURLConnection p1) throws IOException, JSONException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: getstatic       com/facebook/LoggingBehavior.REQUESTS:Lcom/facebook/LoggingBehavior;
        //     7: ldc_w           "Request"
        //    10: invokespecial   com/facebook/internal/Logger.<init>:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;)V
        //    13: astore          7
        //    15: aload_0        
        //    16: invokevirtual   com/facebook/GraphRequestBatch.size:()I
        //    19: istore_3       
        //    20: aload_0        
        //    21: invokestatic    com/facebook/GraphRequest.isGzipCompressible:(Lcom/facebook/GraphRequestBatch;)Z
        //    24: istore          4
        //    26: iload_3        
        //    27: iconst_1       
        //    28: if_icmpne       166
        //    31: aload_0        
        //    32: iconst_0       
        //    33: invokevirtual   com/facebook/GraphRequestBatch.get:(I)Lcom/facebook/GraphRequest;
        //    36: getfield        com/facebook/GraphRequest.httpMethod:Lcom/facebook/HttpMethod;
        //    39: astore          5
        //    41: aload_1        
        //    42: aload           5
        //    44: invokevirtual   com/facebook/HttpMethod.name:()Ljava/lang/String;
        //    47: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
        //    50: aload_1        
        //    51: iload           4
        //    53: invokestatic    com/facebook/GraphRequest.setConnectionContentType:(Ljava/net/HttpURLConnection;Z)V
        //    56: aload_1        
        //    57: invokevirtual   java/net/HttpURLConnection.getURL:()Ljava/net/URL;
        //    60: astore          8
        //    62: aload           7
        //    64: ldc_w           "Request:\n"
        //    67: invokevirtual   com/facebook/internal/Logger.append:(Ljava/lang/String;)V
        //    70: aload           7
        //    72: ldc_w           "Id"
        //    75: aload_0        
        //    76: invokevirtual   com/facebook/GraphRequestBatch.getId:()Ljava/lang/String;
        //    79: invokevirtual   com/facebook/internal/Logger.appendKeyValue:(Ljava/lang/String;Ljava/lang/Object;)V
        //    82: aload           7
        //    84: ldc_w           "URL"
        //    87: aload           8
        //    89: invokevirtual   com/facebook/internal/Logger.appendKeyValue:(Ljava/lang/String;Ljava/lang/Object;)V
        //    92: aload           7
        //    94: ldc_w           "Method"
        //    97: aload_1        
        //    98: invokevirtual   java/net/HttpURLConnection.getRequestMethod:()Ljava/lang/String;
        //   101: invokevirtual   com/facebook/internal/Logger.appendKeyValue:(Ljava/lang/String;Ljava/lang/Object;)V
        //   104: aload           7
        //   106: ldc             "User-Agent"
        //   108: aload_1        
        //   109: ldc             "User-Agent"
        //   111: invokevirtual   java/net/HttpURLConnection.getRequestProperty:(Ljava/lang/String;)Ljava/lang/String;
        //   114: invokevirtual   com/facebook/internal/Logger.appendKeyValue:(Ljava/lang/String;Ljava/lang/Object;)V
        //   117: aload           7
        //   119: ldc             "Content-Type"
        //   121: aload_1        
        //   122: ldc             "Content-Type"
        //   124: invokevirtual   java/net/HttpURLConnection.getRequestProperty:(Ljava/lang/String;)Ljava/lang/String;
        //   127: invokevirtual   com/facebook/internal/Logger.appendKeyValue:(Ljava/lang/String;Ljava/lang/Object;)V
        //   130: aload_1        
        //   131: aload_0        
        //   132: invokevirtual   com/facebook/GraphRequestBatch.getTimeout:()I
        //   135: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
        //   138: aload_1        
        //   139: aload_0        
        //   140: invokevirtual   com/facebook/GraphRequestBatch.getTimeout:()I
        //   143: invokevirtual   java/net/HttpURLConnection.setReadTimeout:(I)V
        //   146: aload           5
        //   148: getstatic       com/facebook/HttpMethod.POST:Lcom/facebook/HttpMethod;
        //   151: if_acmpne       174
        //   154: iconst_1       
        //   155: istore_2       
        //   156: iload_2        
        //   157: ifne            179
        //   160: aload           7
        //   162: invokevirtual   com/facebook/internal/Logger.log:()V
        //   165: return         
        //   166: getstatic       com/facebook/HttpMethod.POST:Lcom/facebook/HttpMethod;
        //   169: astore          5
        //   171: goto            41
        //   174: iconst_0       
        //   175: istore_2       
        //   176: goto            156
        //   179: aload_1        
        //   180: iconst_1       
        //   181: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //   184: aconst_null    
        //   185: astore          5
        //   187: new             Ljava/io/BufferedOutputStream;
        //   190: dup            
        //   191: aload_1        
        //   192: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   195: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   198: astore          6
        //   200: aload           6
        //   202: astore_1       
        //   203: iload           4
        //   205: ifeq            222
        //   208: aload           6
        //   210: astore          5
        //   212: new             Ljava/util/zip/GZIPOutputStream;
        //   215: dup            
        //   216: aload           6
        //   218: invokespecial   java/util/zip/GZIPOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   221: astore_1       
        //   222: aload_1        
        //   223: astore          5
        //   225: aload_0        
        //   226: invokestatic    com/facebook/GraphRequest.hasOnProgressCallbacks:(Lcom/facebook/GraphRequestBatch;)Z
        //   229: ifeq            338
        //   232: aload_1        
        //   233: astore          5
        //   235: new             Lcom/facebook/ProgressNoopOutputStream;
        //   238: dup            
        //   239: aload_0        
        //   240: invokevirtual   com/facebook/GraphRequestBatch.getCallbackHandler:()Landroid/os/Handler;
        //   243: invokespecial   com/facebook/ProgressNoopOutputStream.<init>:(Landroid/os/Handler;)V
        //   246: astore          6
        //   248: aload_1        
        //   249: astore          5
        //   251: aload_0        
        //   252: aconst_null    
        //   253: iload_3        
        //   254: aload           8
        //   256: aload           6
        //   258: iload           4
        //   260: invokestatic    com/facebook/GraphRequest.processRequest:(Lcom/facebook/GraphRequestBatch;Lcom/facebook/internal/Logger;ILjava/net/URL;Ljava/io/OutputStream;Z)V
        //   263: aload_1        
        //   264: astore          5
        //   266: aload           6
        //   268: invokevirtual   com/facebook/ProgressNoopOutputStream.getMaxProgress:()I
        //   271: istore_2       
        //   272: aload_1        
        //   273: astore          5
        //   275: new             Lcom/facebook/ProgressOutputStream;
        //   278: dup            
        //   279: aload_1        
        //   280: aload_0        
        //   281: aload           6
        //   283: invokevirtual   com/facebook/ProgressNoopOutputStream.getProgressMap:()Ljava/util/Map;
        //   286: iload_2        
        //   287: i2l            
        //   288: invokespecial   com/facebook/ProgressOutputStream.<init>:(Ljava/io/OutputStream;Lcom/facebook/GraphRequestBatch;Ljava/util/Map;J)V
        //   291: astore_1       
        //   292: aload_1        
        //   293: astore          5
        //   295: aload_0        
        //   296: aload           7
        //   298: iload_3        
        //   299: aload           8
        //   301: aload_1        
        //   302: iload           4
        //   304: invokestatic    com/facebook/GraphRequest.processRequest:(Lcom/facebook/GraphRequestBatch;Lcom/facebook/internal/Logger;ILjava/net/URL;Ljava/io/OutputStream;Z)V
        //   307: aload_1        
        //   308: ifnull          315
        //   311: aload_1        
        //   312: invokevirtual   java/io/OutputStream.close:()V
        //   315: aload           7
        //   317: invokevirtual   com/facebook/internal/Logger.log:()V
        //   320: return         
        //   321: astore_0       
        //   322: aload           5
        //   324: ifnull          332
        //   327: aload           5
        //   329: invokevirtual   java/io/OutputStream.close:()V
        //   332: aload_0        
        //   333: athrow         
        //   334: astore_0       
        //   335: goto            322
        //   338: goto            292
        //    Exceptions:
        //  throws java.io.IOException
        //  throws org.json.JSONException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  187    200    321    322    Any
        //  212    222    334    338    Any
        //  225    232    334    338    Any
        //  235    248    334    338    Any
        //  251    263    334    338    Any
        //  266    272    334    338    Any
        //  275    292    334    338    Any
        //  295    307    321    322    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0222:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static void setConnectionContentType(final HttpURLConnection httpURLConnection, final boolean b) {
        if (b) {
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
            return;
        }
        httpURLConnection.setRequestProperty("Content-Type", getMimeContentType());
    }
    
    public static final void setDefaultBatchApplicationId(final String defaultBatchApplicationId) {
        GraphRequest.defaultBatchApplicationId = defaultBatchApplicationId;
    }
    
    static final boolean shouldWarnOnMissingFieldsParam(final GraphRequest graphRequest) {
        final boolean b = false;
        final String version = graphRequest.getVersion();
        if (Utility.isNullOrEmpty(version)) {
            return true;
        }
        String substring = version;
        if (version.startsWith("v")) {
            substring = version.substring(1);
        }
        final String[] split = substring.split("\\.");
        if (split.length < 2 || Integer.parseInt(split[0]) <= 2) {
            boolean b2 = b;
            if (Integer.parseInt(split[0]) < 2) {
                return b2;
            }
            b2 = b;
            if (Integer.parseInt(split[1]) < 4) {
                return b2;
            }
        }
        return true;
    }
    
    public static HttpURLConnection toHttpConnection(final GraphRequestBatch p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokestatic    com/facebook/GraphRequest.validateFieldsParamForGetRequests:(Lcom/facebook/GraphRequestBatch;)V
        //     4: aload_0        
        //     5: invokevirtual   com/facebook/GraphRequestBatch.size:()I
        //     8: iconst_1       
        //     9: if_icmpne       48
        //    12: new             Ljava/net/URL;
        //    15: dup            
        //    16: aload_0        
        //    17: iconst_0       
        //    18: invokevirtual   com/facebook/GraphRequestBatch.get:(I)Lcom/facebook/GraphRequest;
        //    21: invokevirtual   com/facebook/GraphRequest.getUrlForSingleRequest:()Ljava/lang/String;
        //    24: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    27: astore_3       
        //    28: aconst_null    
        //    29: astore_2       
        //    30: aconst_null    
        //    31: astore_1       
        //    32: aload_3        
        //    33: invokestatic    com/facebook/GraphRequest.createConnection:(Ljava/net/URL;)Ljava/net/HttpURLConnection;
        //    36: astore_3       
        //    37: aload_3        
        //    38: astore_1       
        //    39: aload_3        
        //    40: astore_2       
        //    41: aload_0        
        //    42: aload_3        
        //    43: invokestatic    com/facebook/GraphRequest.serializeToUrlConnection:(Lcom/facebook/GraphRequestBatch;Ljava/net/HttpURLConnection;)V
        //    46: aload_3        
        //    47: areturn        
        //    48: new             Ljava/net/URL;
        //    51: dup            
        //    52: invokestatic    com/facebook/internal/ServerProtocol.getGraphUrlBase:()Ljava/lang/String;
        //    55: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    58: astore_3       
        //    59: goto            28
        //    62: astore_0       
        //    63: new             Lcom/facebook/FacebookException;
        //    66: dup            
        //    67: ldc_w           "could not construct URL for request"
        //    70: aload_0        
        //    71: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    74: athrow         
        //    75: astore_0       
        //    76: aload_1        
        //    77: invokestatic    com/facebook/internal/Utility.disconnectQuietly:(Ljava/net/URLConnection;)V
        //    80: new             Lcom/facebook/FacebookException;
        //    83: dup            
        //    84: ldc_w           "could not construct request body"
        //    87: aload_0        
        //    88: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    91: athrow         
        //    92: astore_0       
        //    93: aload_2        
        //    94: astore_1       
        //    95: goto            76
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  4      28     62     75     Ljava/net/MalformedURLException;
        //  32     37     92     98     Ljava/io/IOException;
        //  32     37     75     76     Lorg/json/JSONException;
        //  41     46     92     98     Ljava/io/IOException;
        //  41     46     75     76     Lorg/json/JSONException;
        //  48     59     62     75     Ljava/net/MalformedURLException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0048:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static HttpURLConnection toHttpConnection(final Collection<GraphRequest> collection) {
        Validate.notEmptyAndContainsNoNulls((Collection<Object>)collection, "requests");
        return toHttpConnection(new GraphRequestBatch(collection));
    }
    
    public static HttpURLConnection toHttpConnection(final GraphRequest... array) {
        return toHttpConnection(Arrays.asList(array));
    }
    
    static final void validateFieldsParamForGetRequests(final GraphRequestBatch graphRequestBatch) {
        for (final GraphRequest graphRequest : graphRequestBatch) {
            if (HttpMethod.GET.equals(graphRequest.getHttpMethod()) && shouldWarnOnMissingFieldsParam(graphRequest)) {
                final Bundle parameters = graphRequest.getParameters();
                if (parameters.containsKey("fields") && !Utility.isNullOrEmpty(parameters.getString("fields"))) {
                    continue;
                }
                Logger.log(LoggingBehavior.DEVELOPER_ERRORS, 5, "Request", "starting with Graph API v2.4, GET requests for /%s should contain an explicit \"fields\" parameter.", graphRequest.getGraphPath());
            }
        }
    }
    
    public final GraphResponse executeAndWait() {
        return executeAndWait(this);
    }
    
    public final GraphRequestAsyncTask executeAsync() {
        return executeBatchAsync(this);
    }
    
    public final AccessToken getAccessToken() {
        return this.accessToken;
    }
    
    public final String getBatchEntryDependsOn() {
        return this.batchEntryDependsOn;
    }
    
    public final String getBatchEntryName() {
        return this.batchEntryName;
    }
    
    public final boolean getBatchEntryOmitResultOnSuccess() {
        return this.batchEntryOmitResultOnSuccess;
    }
    
    public final Callback getCallback() {
        return this.callback;
    }
    
    public final JSONObject getGraphObject() {
        return this.graphObject;
    }
    
    public final String getGraphPath() {
        return this.graphPath;
    }
    
    public final HttpMethod getHttpMethod() {
        return this.httpMethod;
    }
    
    public final Bundle getParameters() {
        return this.parameters;
    }
    
    final String getRelativeUrlForBatchedRequest() {
        if (this.overriddenURL != null) {
            throw new FacebookException("Can't override URL for a batch request");
        }
        final String format = String.format("%s/%s", ServerProtocol.getGraphUrlBase(), this.getGraphPathWithVersion());
        this.addCommonParameters();
        final Uri parse = Uri.parse(this.appendParametersToBaseUrl(format, true));
        return String.format("%s?%s", parse.getPath(), parse.getQuery());
    }
    
    public final Object getTag() {
        return this.tag;
    }
    
    final String getUrlForSingleRequest() {
        if (this.overriddenURL != null) {
            return this.overriddenURL.toString();
        }
        String s;
        if (this.getHttpMethod() == HttpMethod.POST && this.graphPath != null && this.graphPath.endsWith("/videos")) {
            s = ServerProtocol.getGraphVideoUrlBase();
        }
        else {
            s = ServerProtocol.getGraphUrlBase();
        }
        final String format = String.format("%s/%s", s, this.getGraphPathWithVersion());
        this.addCommonParameters();
        return this.appendParametersToBaseUrl(format, false);
    }
    
    public final String getVersion() {
        return this.version;
    }
    
    public final void setAccessToken(final AccessToken accessToken) {
        this.accessToken = accessToken;
    }
    
    public final void setBatchEntryDependsOn(final String batchEntryDependsOn) {
        this.batchEntryDependsOn = batchEntryDependsOn;
    }
    
    public final void setBatchEntryName(final String batchEntryName) {
        this.batchEntryName = batchEntryName;
    }
    
    public final void setBatchEntryOmitResultOnSuccess(final boolean batchEntryOmitResultOnSuccess) {
        this.batchEntryOmitResultOnSuccess = batchEntryOmitResultOnSuccess;
    }
    
    public final void setCallback(final Callback callback) {
        if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.GRAPH_API_DEBUG_INFO) || FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.GRAPH_API_DEBUG_WARNING)) {
            this.callback = (Callback)new Callback() {
                @Override
                public void onCompleted(final GraphResponse graphResponse) {
                    final JSONObject jsonObject = graphResponse.getJSONObject();
                    JSONObject optJSONObject;
                    if (jsonObject != null) {
                        optJSONObject = jsonObject.optJSONObject("__debug__");
                    }
                    else {
                        optJSONObject = null;
                    }
                    JSONArray optJSONArray;
                    if (optJSONObject != null) {
                        optJSONArray = optJSONObject.optJSONArray("messages");
                    }
                    else {
                        optJSONArray = null;
                    }
                    if (optJSONArray != null) {
                        for (int i = 0; i < optJSONArray.length(); ++i) {
                            final JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                            String optString;
                            if (optJSONObject2 != null) {
                                optString = optJSONObject2.optString("message");
                            }
                            else {
                                optString = null;
                            }
                            String optString2;
                            if (optJSONObject2 != null) {
                                optString2 = optJSONObject2.optString("type");
                            }
                            else {
                                optString2 = null;
                            }
                            String optString3;
                            if (optJSONObject2 != null) {
                                optString3 = optJSONObject2.optString("link");
                            }
                            else {
                                optString3 = null;
                            }
                            if (optString != null && optString2 != null) {
                                LoggingBehavior loggingBehavior = LoggingBehavior.GRAPH_API_DEBUG_INFO;
                                if (optString2.equals("warning")) {
                                    loggingBehavior = LoggingBehavior.GRAPH_API_DEBUG_WARNING;
                                }
                                String string = optString;
                                if (!Utility.isNullOrEmpty(optString3)) {
                                    string = optString + " Link: " + optString3;
                                }
                                Logger.log(loggingBehavior, GraphRequest.TAG, string);
                            }
                        }
                    }
                    if (callback != null) {
                        callback.onCompleted(graphResponse);
                    }
                }
            };
            return;
        }
        this.callback = callback;
    }
    
    public final void setGraphObject(final JSONObject graphObject) {
        this.graphObject = graphObject;
    }
    
    public final void setGraphPath(final String graphPath) {
        this.graphPath = graphPath;
    }
    
    public final void setHttpMethod(HttpMethod get) {
        if (this.overriddenURL != null && get != HttpMethod.GET) {
            throw new FacebookException("Can't change HTTP method on request with overridden URL.");
        }
        if (get == null) {
            get = HttpMethod.GET;
        }
        this.httpMethod = get;
    }
    
    public final void setParameters(final Bundle parameters) {
        this.parameters = parameters;
    }
    
    public final void setSkipClientToken(final boolean skipClientToken) {
        this.skipClientToken = skipClientToken;
    }
    
    public final void setTag(final Object tag) {
        this.tag = tag;
    }
    
    public final void setVersion(final String version) {
        this.version = version;
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder().append("{Request: ").append(" accessToken: ");
        Object accessToken;
        if (this.accessToken == null) {
            accessToken = "null";
        }
        else {
            accessToken = this.accessToken;
        }
        return append.append(accessToken).append(", graphPath: ").append(this.graphPath).append(", graphObject: ").append(this.graphObject).append(", httpMethod: ").append(this.httpMethod).append(", parameters: ").append(this.parameters).append("}").toString();
    }
    
    private static class Attachment
    {
        private final GraphRequest request;
        private final Object value;
        
        public Attachment(final GraphRequest request, final Object value) {
            this.request = request;
            this.value = value;
        }
        
        public GraphRequest getRequest() {
            return this.request;
        }
        
        public Object getValue() {
            return this.value;
        }
    }
    
    public interface Callback
    {
        void onCompleted(final GraphResponse p0);
    }
    
    public interface GraphJSONArrayCallback
    {
        void onCompleted(final JSONArray p0, final GraphResponse p1);
    }
    
    public interface GraphJSONObjectCallback
    {
        void onCompleted(final JSONObject p0, final GraphResponse p1);
    }
    
    private interface KeyValueSerializer
    {
        void writeString(final String p0, final String p1) throws IOException;
    }
    
    public interface OnProgressCallback extends Callback
    {
        void onProgress(final long p0, final long p1);
    }
    
    public static class ParcelableResourceWithMimeType<RESOURCE extends Parcelable> implements Parcelable
    {
        public static final Parcelable$Creator<ParcelableResourceWithMimeType> CREATOR;
        private final String mimeType;
        private final RESOURCE resource;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<ParcelableResourceWithMimeType>() {
                public ParcelableResourceWithMimeType createFromParcel(final Parcel parcel) {
                    return new ParcelableResourceWithMimeType(parcel);
                }
                
                public ParcelableResourceWithMimeType[] newArray(final int n) {
                    return new ParcelableResourceWithMimeType[n];
                }
            };
        }
        
        private ParcelableResourceWithMimeType(final Parcel parcel) {
            this.mimeType = parcel.readString();
            this.resource = (RESOURCE)parcel.readParcelable(FacebookSdk.getApplicationContext().getClassLoader());
        }
        
        public ParcelableResourceWithMimeType(final RESOURCE resource, final String mimeType) {
            this.mimeType = mimeType;
            this.resource = resource;
        }
        
        public int describeContents() {
            return 1;
        }
        
        public String getMimeType() {
            return this.mimeType;
        }
        
        public RESOURCE getResource() {
            return this.resource;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            parcel.writeString(this.mimeType);
            parcel.writeParcelable((Parcelable)this.resource, n);
        }
    }
    
    private static class Serializer implements KeyValueSerializer
    {
        private boolean firstWrite;
        private final Logger logger;
        private final OutputStream outputStream;
        private boolean useUrlEncode;
        
        public Serializer(final OutputStream outputStream, final Logger logger, final boolean useUrlEncode) {
            this.firstWrite = true;
            this.useUrlEncode = false;
            this.outputStream = outputStream;
            this.logger = logger;
            this.useUrlEncode = useUrlEncode;
        }
        
        private RuntimeException getInvalidTypeError() {
            return new IllegalArgumentException("value is not a supported type.");
        }
        
        public void write(final String s, final Object... array) throws IOException {
            if (!this.useUrlEncode) {
                if (this.firstWrite) {
                    this.outputStream.write("--".getBytes());
                    this.outputStream.write("3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f".getBytes());
                    this.outputStream.write("\r\n".getBytes());
                    this.firstWrite = false;
                }
                this.outputStream.write(String.format(s, array).getBytes());
                return;
            }
            this.outputStream.write(URLEncoder.encode(String.format(Locale.US, s, array), "UTF-8").getBytes());
        }
        
        public void writeBitmap(final String s, final Bitmap bitmap) throws IOException {
            this.writeContentDisposition(s, s, "image/png");
            bitmap.compress(Bitmap$CompressFormat.PNG, 100, this.outputStream);
            this.writeLine("", new Object[0]);
            this.writeRecordBoundary();
            if (this.logger != null) {
                this.logger.appendKeyValue("    " + s, "<Image>");
            }
        }
        
        public void writeBytes(final String s, final byte[] array) throws IOException {
            this.writeContentDisposition(s, s, "content/unknown");
            this.outputStream.write(array);
            this.writeLine("", new Object[0]);
            this.writeRecordBoundary();
            if (this.logger != null) {
                this.logger.appendKeyValue("    " + s, String.format(Locale.ROOT, "<Data: %d>", array.length));
            }
        }
        
        public void writeContentDisposition(final String s, final String s2, final String s3) throws IOException {
            if (!this.useUrlEncode) {
                this.write("Content-Disposition: form-data; name=\"%s\"", s);
                if (s2 != null) {
                    this.write("; filename=\"%s\"", s2);
                }
                this.writeLine("", new Object[0]);
                if (s3 != null) {
                    this.writeLine("%s: %s", "Content-Type", s3);
                }
                this.writeLine("", new Object[0]);
                return;
            }
            this.outputStream.write(String.format("%s=", s).getBytes());
        }
        
        public void writeContentUri(final String s, final Uri uri, final String s2) throws IOException {
            String s3 = s2;
            if (s2 == null) {
                s3 = "content/unknown";
            }
            this.writeContentDisposition(s, s, s3);
            int n = 0;
            if (this.outputStream instanceof ProgressNoopOutputStream) {
                ((ProgressNoopOutputStream)this.outputStream).addProgress(Utility.getContentSize(uri));
            }
            else {
                n = 0 + Utility.copyAndCloseInputStream(FacebookSdk.getApplicationContext().getContentResolver().openInputStream(uri), this.outputStream);
            }
            this.writeLine("", new Object[0]);
            this.writeRecordBoundary();
            if (this.logger != null) {
                this.logger.appendKeyValue("    " + s, String.format(Locale.ROOT, "<Data: %d>", n));
            }
        }
        
        public void writeFile(final String s, final ParcelFileDescriptor parcelFileDescriptor, final String s2) throws IOException {
            String s3 = s2;
            if (s2 == null) {
                s3 = "content/unknown";
            }
            this.writeContentDisposition(s, s, s3);
            int n = 0;
            if (this.outputStream instanceof ProgressNoopOutputStream) {
                ((ProgressNoopOutputStream)this.outputStream).addProgress(parcelFileDescriptor.getStatSize());
            }
            else {
                n = 0 + Utility.copyAndCloseInputStream((InputStream)new ParcelFileDescriptor$AutoCloseInputStream(parcelFileDescriptor), this.outputStream);
            }
            this.writeLine("", new Object[0]);
            this.writeRecordBoundary();
            if (this.logger != null) {
                this.logger.appendKeyValue("    " + s, String.format(Locale.ROOT, "<Data: %d>", n));
            }
        }
        
        public void writeLine(final String s, final Object... array) throws IOException {
            this.write(s, array);
            if (!this.useUrlEncode) {
                this.write("\r\n", new Object[0]);
            }
        }
        
        public void writeObject(final String s, final Object o, final GraphRequest currentRequest) throws IOException {
            if (this.outputStream instanceof RequestOutputStream) {
                ((RequestOutputStream)this.outputStream).setCurrentRequest(currentRequest);
            }
            if (isSupportedParameterType(o)) {
                this.writeString(s, parameterToString(o));
                return;
            }
            if (o instanceof Bitmap) {
                this.writeBitmap(s, (Bitmap)o);
                return;
            }
            if (o instanceof byte[]) {
                this.writeBytes(s, (byte[])o);
                return;
            }
            if (o instanceof Uri) {
                this.writeContentUri(s, (Uri)o, null);
                return;
            }
            if (o instanceof ParcelFileDescriptor) {
                this.writeFile(s, (ParcelFileDescriptor)o, null);
                return;
            }
            if (!(o instanceof ParcelableResourceWithMimeType)) {
                throw this.getInvalidTypeError();
            }
            final ParcelableResourceWithMimeType parcelableResourceWithMimeType = (ParcelableResourceWithMimeType)o;
            final Parcelable resource = parcelableResourceWithMimeType.getResource();
            final String mimeType = parcelableResourceWithMimeType.getMimeType();
            if (resource instanceof ParcelFileDescriptor) {
                this.writeFile(s, (ParcelFileDescriptor)resource, mimeType);
                return;
            }
            if (resource instanceof Uri) {
                this.writeContentUri(s, (Uri)resource, mimeType);
                return;
            }
            throw this.getInvalidTypeError();
        }
        
        public void writeRecordBoundary() throws IOException {
            if (!this.useUrlEncode) {
                this.writeLine("--%s", "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
                return;
            }
            this.outputStream.write("&".getBytes());
        }
        
        public void writeRequestsAsJson(final String s, final JSONArray jsonArray, final Collection<GraphRequest> collection) throws IOException, JSONException {
            if (!(this.outputStream instanceof RequestOutputStream)) {
                this.writeString(s, jsonArray.toString());
            }
            else {
                final RequestOutputStream requestOutputStream = (RequestOutputStream)this.outputStream;
                this.writeContentDisposition(s, null, null);
                this.write("[", new Object[0]);
                int n = 0;
                for (final GraphRequest currentRequest : collection) {
                    final JSONObject jsonObject = jsonArray.getJSONObject(n);
                    requestOutputStream.setCurrentRequest(currentRequest);
                    if (n > 0) {
                        this.write(",%s", jsonObject.toString());
                    }
                    else {
                        this.write("%s", jsonObject.toString());
                    }
                    ++n;
                }
                this.write("]", new Object[0]);
                if (this.logger != null) {
                    this.logger.appendKeyValue("    " + s, jsonArray.toString());
                }
            }
        }
        
        @Override
        public void writeString(final String s, final String s2) throws IOException {
            this.writeContentDisposition(s, null, null);
            this.writeLine("%s", s2);
            this.writeRecordBoundary();
            if (this.logger != null) {
                this.logger.appendKeyValue("    " + s, s2);
            }
        }
    }
}
