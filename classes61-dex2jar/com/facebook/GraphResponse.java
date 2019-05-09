// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import java.util.Locale;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.Closeable;
import org.json.JSONTokener;
import com.facebook.internal.Logger;
import java.io.InputStream;
import java.io.IOException;
import org.json.JSONException;
import com.facebook.internal.Utility;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.HttpURLConnection;

public class GraphResponse
{
    private static final String BODY_KEY = "body";
    private static final String CODE_KEY = "code";
    public static final String NON_JSON_RESPONSE_PROPERTY = "FACEBOOK_NON_JSON_RESULT";
    private static final String RESPONSE_LOG_TAG = "Response";
    public static final String SUCCESS_KEY = "success";
    private static final String TAG;
    private final HttpURLConnection connection;
    private final FacebookRequestError error;
    private final JSONObject graphObject;
    private final JSONArray graphObjectArray;
    private final String rawResponse;
    private final GraphRequest request;
    
    static {
        TAG = GraphResponse.class.getSimpleName();
    }
    
    GraphResponse(final GraphRequest graphRequest, final HttpURLConnection httpURLConnection, final FacebookRequestError facebookRequestError) {
        this(graphRequest, httpURLConnection, null, null, null, facebookRequestError);
    }
    
    GraphResponse(final GraphRequest graphRequest, final HttpURLConnection httpURLConnection, final String s, final JSONArray jsonArray) {
        this(graphRequest, httpURLConnection, s, null, jsonArray, null);
    }
    
    GraphResponse(final GraphRequest graphRequest, final HttpURLConnection httpURLConnection, final String s, final JSONObject jsonObject) {
        this(graphRequest, httpURLConnection, s, jsonObject, null, null);
    }
    
    GraphResponse(final GraphRequest request, final HttpURLConnection connection, final String rawResponse, final JSONObject graphObject, final JSONArray graphObjectArray, final FacebookRequestError error) {
        this.request = request;
        this.connection = connection;
        this.rawResponse = rawResponse;
        this.graphObject = graphObject;
        this.graphObjectArray = graphObjectArray;
        this.error = error;
    }
    
    static List<GraphResponse> constructErrorResponses(final List<GraphRequest> list, final HttpURLConnection httpURLConnection, final FacebookException ex) {
        final int size = list.size();
        final ArrayList list2 = new ArrayList<GraphResponse>(size);
        for (int i = 0; i < size; ++i) {
            list2.add(new GraphResponse(list.get(i), httpURLConnection, new FacebookRequestError(httpURLConnection, ex)));
        }
        return (List<GraphResponse>)list2;
    }
    
    private static GraphResponse createResponseFromObject(final GraphRequest graphRequest, final HttpURLConnection httpURLConnection, Object stringPropertyAsJSON, final Object o) throws JSONException {
        Object null = stringPropertyAsJSON;
        if (stringPropertyAsJSON instanceof JSONObject) {
            final JSONObject jsonObject = (JSONObject)stringPropertyAsJSON;
            final FacebookRequestError checkResponseAndCreateError = FacebookRequestError.checkResponseAndCreateError(jsonObject, o, httpURLConnection);
            if (checkResponseAndCreateError != null) {
                Log.e(GraphResponse.TAG, checkResponseAndCreateError.toString());
                if (checkResponseAndCreateError.getErrorCode() == 190 && Utility.isCurrentAccessToken(graphRequest.getAccessToken())) {
                    if (checkResponseAndCreateError.getSubErrorCode() != 493) {
                        AccessToken.setCurrentAccessToken(null);
                    }
                    else if (!AccessToken.getCurrentAccessToken().isExpired()) {
                        AccessToken.expireCurrentAccessToken();
                    }
                }
                return new GraphResponse(graphRequest, httpURLConnection, checkResponseAndCreateError);
            }
            stringPropertyAsJSON = Utility.getStringPropertyAsJSON(jsonObject, "body", "FACEBOOK_NON_JSON_RESULT");
            if (stringPropertyAsJSON instanceof JSONObject) {
                return new GraphResponse(graphRequest, httpURLConnection, stringPropertyAsJSON.toString(), (JSONObject)stringPropertyAsJSON);
            }
            if (stringPropertyAsJSON instanceof JSONArray) {
                return new GraphResponse(graphRequest, httpURLConnection, stringPropertyAsJSON.toString(), (JSONArray)stringPropertyAsJSON);
            }
            null = JSONObject.NULL;
        }
        if (null == JSONObject.NULL) {
            return new GraphResponse(graphRequest, httpURLConnection, null.toString(), (JSONObject)null);
        }
        throw new FacebookException("Got unexpected object type in response, class: " + null.getClass().getSimpleName());
    }
    
    private static List<GraphResponse> createResponsesFromObject(final HttpURLConnection httpURLConnection, final List<GraphRequest> list, final Object o) throws FacebookException, JSONException {
        final int size = list.size();
        final ArrayList list2 = new ArrayList<GraphResponse>(size);
        Object o2 = o;
        while (true) {
            if (size == 1) {
                final GraphRequest graphRequest = list.get(0);
                try {
                    final JSONObject jsonObject = new JSONObject();
                    jsonObject.put("body", o);
                    int responseCode;
                    if (httpURLConnection != null) {
                        responseCode = httpURLConnection.getResponseCode();
                    }
                    else {
                        responseCode = 200;
                    }
                    jsonObject.put("code", responseCode);
                    o2 = new JSONArray();
                    ((JSONArray)o2).put((Object)jsonObject);
                    if (!(o2 instanceof JSONArray) || ((JSONArray)o2).length() != size) {
                        throw new FacebookException("Unexpected number of results");
                    }
                }
                catch (JSONException ex) {
                    list2.add(new GraphResponse(graphRequest, httpURLConnection, new FacebookRequestError(httpURLConnection, (Exception)ex)));
                    o2 = o;
                    continue;
                }
                catch (IOException ex2) {
                    list2.add(new GraphResponse(graphRequest, httpURLConnection, new FacebookRequestError(httpURLConnection, ex2)));
                    o2 = o;
                    continue;
                }
                final JSONArray jsonArray = (JSONArray)o2;
                int i = 0;
            Label_0254_Outer:
                while (i < jsonArray.length()) {
                    final GraphRequest graphRequest2 = list.get(i);
                    while (true) {
                        try {
                            list2.add(createResponseFromObject(graphRequest2, httpURLConnection, jsonArray.get(i), o));
                            ++i;
                            continue Label_0254_Outer;
                        }
                        catch (JSONException ex3) {
                            list2.add(new GraphResponse(graphRequest2, httpURLConnection, new FacebookRequestError(httpURLConnection, (Exception)ex3)));
                            continue;
                        }
                        catch (FacebookException ex4) {
                            list2.add(new GraphResponse(graphRequest2, httpURLConnection, new FacebookRequestError(httpURLConnection, ex4)));
                            continue;
                        }
                        break;
                    }
                    break;
                }
                return (List<GraphResponse>)list2;
            }
            continue;
        }
    }
    
    static List<GraphResponse> createResponsesFromStream(final InputStream inputStream, final HttpURLConnection httpURLConnection, final GraphRequestBatch graphRequestBatch) throws FacebookException, JSONException, IOException {
        final String streamToString = Utility.readStreamToString(inputStream);
        Logger.log(LoggingBehavior.INCLUDE_RAW_RESPONSES, "Response", "Response (raw)\n  Size: %d\n  Response:\n%s\n", streamToString.length(), streamToString);
        return createResponsesFromString(streamToString, httpURLConnection, graphRequestBatch);
    }
    
    static List<GraphResponse> createResponsesFromString(final String s, final HttpURLConnection httpURLConnection, final GraphRequestBatch graphRequestBatch) throws FacebookException, JSONException, IOException {
        final List<GraphResponse> responsesFromObject = createResponsesFromObject(httpURLConnection, graphRequestBatch, new JSONTokener(s).nextValue());
        Logger.log(LoggingBehavior.REQUESTS, "Response", "Response\n  Id: %s\n  Size: %d\n  Responses:\n%s\n", graphRequestBatch.getId(), s.length(), responsesFromObject);
        return responsesFromObject;
    }
    
    static List<GraphResponse> fromHttpConnection(final HttpURLConnection httpURLConnection, final GraphRequestBatch graphRequestBatch) {
        final Closeable closeable = null;
        final Closeable closeable2 = null;
        Closeable closeable4;
        final Closeable closeable3 = closeable4 = null;
        Closeable closeable5 = closeable;
        Closeable closeable6 = closeable2;
        try {
            InputStream inputStream;
            if (httpURLConnection.getResponseCode() >= 400) {
                closeable4 = closeable3;
                closeable5 = closeable;
                closeable6 = closeable2;
                inputStream = httpURLConnection.getErrorStream();
            }
            else {
                closeable4 = closeable3;
                closeable5 = closeable;
                closeable6 = closeable2;
                inputStream = httpURLConnection.getInputStream();
            }
            closeable4 = inputStream;
            closeable5 = inputStream;
            closeable6 = inputStream;
            return createResponsesFromStream(inputStream, httpURLConnection, graphRequestBatch);
        }
        catch (FacebookException ex) {
            closeable6 = closeable4;
            Logger.log(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", ex);
            closeable6 = closeable4;
            return constructErrorResponses(graphRequestBatch, httpURLConnection, ex);
        }
        catch (Exception ex2) {
            closeable6 = closeable5;
            Logger.log(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", ex2);
            closeable6 = closeable5;
            return constructErrorResponses(graphRequestBatch, httpURLConnection, new FacebookException(ex2));
        }
        finally {
            Utility.closeQuietly(closeable6);
        }
    }
    
    public final HttpURLConnection getConnection() {
        return this.connection;
    }
    
    public final FacebookRequestError getError() {
        return this.error;
    }
    
    public final JSONArray getJSONArray() {
        return this.graphObjectArray;
    }
    
    public final JSONObject getJSONObject() {
        return this.graphObject;
    }
    
    public String getRawResponse() {
        return this.rawResponse;
    }
    
    public GraphRequest getRequest() {
        return this.request;
    }
    
    public GraphRequest getRequestForPagedResults(final PagingDirection pagingDirection) {
        String s2;
        final String s = s2 = null;
        if (this.graphObject != null) {
            final JSONObject optJSONObject = this.graphObject.optJSONObject("paging");
            s2 = s;
            if (optJSONObject != null) {
                if (pagingDirection == PagingDirection.NEXT) {
                    s2 = optJSONObject.optString("next");
                }
                else {
                    s2 = optJSONObject.optString("previous");
                }
            }
        }
        if (Utility.isNullOrEmpty(s2)) {
            return null;
        }
        if (s2 != null && s2.equals(this.request.getUrlForSingleRequest())) {
            return null;
        }
        try {
            return new GraphRequest(this.request.getAccessToken(), new URL(s2));
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    @Override
    public String toString() {
        try {
            final Locale us = Locale.US;
            int responseCode;
            if (this.connection != null) {
                responseCode = this.connection.getResponseCode();
            }
            else {
                responseCode = 200;
            }
            final String format = String.format(us, "%d", responseCode);
            return "{Response: " + " responseCode: " + format + ", graphObject: " + this.graphObject + ", error: " + this.error + "}";
        }
        catch (IOException ex) {
            final String format = "unknown";
            return "{Response: " + " responseCode: " + format + ", graphObject: " + this.graphObject + ", error: " + this.error + "}";
        }
    }
    
    public enum PagingDirection
    {
        NEXT, 
        PREVIOUS;
    }
}
