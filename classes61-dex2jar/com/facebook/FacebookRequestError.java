// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import org.json.JSONException;
import com.facebook.internal.Utility;
import com.facebook.internal.FacebookRequestErrorClassification;
import android.os.Parcel;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public final class FacebookRequestError implements Parcelable
{
    private static final String BODY_KEY = "body";
    private static final String CODE_KEY = "code";
    public static final Parcelable$Creator<FacebookRequestError> CREATOR;
    private static final String ERROR_CODE_FIELD_KEY = "code";
    private static final String ERROR_CODE_KEY = "error_code";
    private static final String ERROR_IS_TRANSIENT_KEY = "is_transient";
    private static final String ERROR_KEY = "error";
    private static final String ERROR_MESSAGE_FIELD_KEY = "message";
    private static final String ERROR_MSG_KEY = "error_msg";
    private static final String ERROR_REASON_KEY = "error_reason";
    private static final String ERROR_SUB_CODE_KEY = "error_subcode";
    private static final String ERROR_TYPE_FIELD_KEY = "type";
    private static final String ERROR_USER_MSG_KEY = "error_user_msg";
    private static final String ERROR_USER_TITLE_KEY = "error_user_title";
    static final Range HTTP_RANGE_SUCCESS;
    public static final int INVALID_ERROR_CODE = -1;
    public static final int INVALID_HTTP_STATUS_CODE = -1;
    private final Object batchRequestResult;
    private final Category category;
    private final HttpURLConnection connection;
    private final int errorCode;
    private final String errorMessage;
    private final String errorRecoveryMessage;
    private final String errorType;
    private final String errorUserMessage;
    private final String errorUserTitle;
    private final FacebookException exception;
    private final JSONObject requestResult;
    private final JSONObject requestResultBody;
    private final int requestStatusCode;
    private final int subErrorCode;
    
    static {
        HTTP_RANGE_SUCCESS = new Range(200, 299);
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<FacebookRequestError>() {
            public FacebookRequestError createFromParcel(final Parcel parcel) {
                return new FacebookRequestError(parcel, null);
            }
            
            public FacebookRequestError[] newArray(final int n) {
                return new FacebookRequestError[n];
            }
        };
    }
    
    private FacebookRequestError(int requestStatusCode, final int errorCode, final int subErrorCode, final String errorType, final String errorMessage, final String errorUserTitle, final String errorUserMessage, final boolean b, final JSONObject requestResultBody, final JSONObject requestResult, final Object batchRequestResult, final HttpURLConnection connection, final FacebookException exception) {
        this.requestStatusCode = requestStatusCode;
        this.errorCode = errorCode;
        this.subErrorCode = subErrorCode;
        this.errorType = errorType;
        this.errorMessage = errorMessage;
        this.requestResultBody = requestResultBody;
        this.requestResult = requestResult;
        this.batchRequestResult = batchRequestResult;
        this.connection = connection;
        this.errorUserTitle = errorUserTitle;
        this.errorUserMessage = errorUserMessage;
        requestStatusCode = 0;
        if (exception != null) {
            this.exception = exception;
            requestStatusCode = 1;
        }
        else {
            this.exception = new FacebookServiceException(this, errorMessage);
        }
        final FacebookRequestErrorClassification errorClassification = getErrorClassification();
        Enum<Category> category;
        if (requestStatusCode != 0) {
            category = Category.OTHER;
        }
        else {
            category = errorClassification.classify(errorCode, subErrorCode, b);
        }
        this.category = (Category)category;
        this.errorRecoveryMessage = errorClassification.getRecoveryMessage(this.category);
    }
    
    public FacebookRequestError(final int n, final String s, final String s2) {
        this(-1, n, -1, s, s2, null, null, false, null, null, null, null, null);
    }
    
    private FacebookRequestError(final Parcel parcel) {
        this(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), false, null, null, null, null, null);
    }
    
    FacebookRequestError(final HttpURLConnection httpURLConnection, final Exception ex) {
        FacebookException ex2;
        if (ex instanceof FacebookException) {
            ex2 = (FacebookException)ex;
        }
        else {
            ex2 = new FacebookException(ex);
        }
        this(-1, -1, -1, null, null, null, null, false, null, null, null, httpURLConnection, ex2);
    }
    
    static FacebookRequestError checkResponseAndCreateError(final JSONObject jsonObject, final Object o, final HttpURLConnection httpURLConnection) {
        try {
            if (jsonObject.has("code")) {
                final int int1 = jsonObject.getInt("code");
                final Object stringPropertyAsJSON = Utility.getStringPropertyAsJSON(jsonObject, "body", "FACEBOOK_NON_JSON_RESULT");
                if (stringPropertyAsJSON != null && stringPropertyAsJSON instanceof JSONObject) {
                    final JSONObject jsonObject2 = (JSONObject)stringPropertyAsJSON;
                    String s = null;
                    String s2 = null;
                    final String s3 = null;
                    final String s4 = null;
                    final boolean b = false;
                    int n = -1;
                    int n2 = -1;
                    int n3 = 0;
                    String optString = null;
                    String optString2 = null;
                    boolean optBoolean = false;
                    Label_0165: {
                        if (jsonObject2.has("error")) {
                            final JSONObject jsonObject3 = (JSONObject)Utility.getStringPropertyAsJSON(jsonObject2, "error", null);
                            s = jsonObject3.optString("type", (String)null);
                            s2 = jsonObject3.optString("message", (String)null);
                            n = jsonObject3.optInt("code", -1);
                            n2 = jsonObject3.optInt("error_subcode", -1);
                            optString = jsonObject3.optString("error_user_msg", (String)null);
                            optString2 = jsonObject3.optString("error_user_title", (String)null);
                            optBoolean = jsonObject3.optBoolean("is_transient", false);
                            n3 = 1;
                        }
                        else {
                            if (!jsonObject2.has("error_code") && !jsonObject2.has("error_msg")) {
                                optString2 = s4;
                                optString = s3;
                                optBoolean = b;
                                if (!jsonObject2.has("error_reason")) {
                                    break Label_0165;
                                }
                            }
                            s = jsonObject2.optString("error_reason", (String)null);
                            s2 = jsonObject2.optString("error_msg", (String)null);
                            n = jsonObject2.optInt("error_code", -1);
                            n2 = jsonObject2.optInt("error_subcode", -1);
                            n3 = 1;
                            optString2 = s4;
                            optString = s3;
                            optBoolean = b;
                        }
                    }
                    if (n3 != 0) {
                        return new FacebookRequestError(int1, n, n2, s, s2, optString2, optString, optBoolean, jsonObject2, jsonObject, o, httpURLConnection, null);
                    }
                }
                if (!FacebookRequestError.HTTP_RANGE_SUCCESS.contains(int1)) {
                    JSONObject jsonObject4;
                    if (jsonObject.has("body")) {
                        jsonObject4 = (JSONObject)Utility.getStringPropertyAsJSON(jsonObject, "body", "FACEBOOK_NON_JSON_RESULT");
                    }
                    else {
                        jsonObject4 = null;
                    }
                    return new FacebookRequestError(int1, -1, -1, null, null, null, null, false, jsonObject4, jsonObject, o, httpURLConnection, null);
                }
            }
        }
        catch (JSONException ex) {}
        return null;
    }
    
    static FacebookRequestErrorClassification getErrorClassification() {
        synchronized (FacebookRequestError.class) {
            final FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
            FacebookRequestErrorClassification facebookRequestErrorClassification;
            if (appSettingsWithoutQuery == null) {
                facebookRequestErrorClassification = FacebookRequestErrorClassification.getDefaultErrorClassification();
            }
            else {
                facebookRequestErrorClassification = appSettingsWithoutQuery.getErrorClassification();
            }
            return facebookRequestErrorClassification;
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Object getBatchRequestResult() {
        return this.batchRequestResult;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public HttpURLConnection getConnection() {
        return this.connection;
    }
    
    public int getErrorCode() {
        return this.errorCode;
    }
    
    public String getErrorMessage() {
        if (this.errorMessage != null) {
            return this.errorMessage;
        }
        return this.exception.getLocalizedMessage();
    }
    
    public String getErrorRecoveryMessage() {
        return this.errorRecoveryMessage;
    }
    
    public String getErrorType() {
        return this.errorType;
    }
    
    public String getErrorUserMessage() {
        return this.errorUserMessage;
    }
    
    public String getErrorUserTitle() {
        return this.errorUserTitle;
    }
    
    public FacebookException getException() {
        return this.exception;
    }
    
    public JSONObject getRequestResult() {
        return this.requestResult;
    }
    
    public JSONObject getRequestResultBody() {
        return this.requestResultBody;
    }
    
    public int getRequestStatusCode() {
        return this.requestStatusCode;
    }
    
    public int getSubErrorCode() {
        return this.subErrorCode;
    }
    
    @Override
    public String toString() {
        return "{HttpStatus: " + this.requestStatusCode + ", errorCode: " + this.errorCode + ", subErrorCode: " + this.subErrorCode + ", errorType: " + this.errorType + ", errorMessage: " + this.getErrorMessage() + "}";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeInt(this.requestStatusCode);
        parcel.writeInt(this.errorCode);
        parcel.writeInt(this.subErrorCode);
        parcel.writeString(this.errorType);
        parcel.writeString(this.errorMessage);
        parcel.writeString(this.errorUserTitle);
        parcel.writeString(this.errorUserMessage);
    }
    
    public enum Category
    {
        LOGIN_RECOVERABLE, 
        OTHER, 
        TRANSIENT;
    }
    
    private static class Range
    {
        private final int end;
        private final int start;
        
        private Range(final int start, final int end) {
            this.start = start;
            this.end = end;
        }
        
        boolean contains(final int n) {
            return this.start <= n && n <= this.end;
        }
    }
}
