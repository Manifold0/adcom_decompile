// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login;

import android.app.Activity;
import android.text.TextUtils;
import com.facebook.internal.Validate;
import java.util.Iterator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import android.content.Intent;
import java.util.ArrayList;
import android.support.v4.app.FragmentActivity;
import com.facebook.common.R$string;
import com.facebook.FacebookException;
import com.facebook.internal.CallbackManagerImpl$RequestCodeOffset;
import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Collection;
import com.facebook.AccessToken;
import java.util.HashMap;
import com.facebook.internal.Utility;
import android.os.Parcel;
import java.util.Map;
import android.support.v4.app.Fragment;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

class LoginClient implements Parcelable
{
    public static final Parcelable$Creator<LoginClient> CREATOR;
    BackgroundProcessingListener backgroundProcessingListener;
    boolean checkedInternetPermission;
    int currentHandler;
    Fragment fragment;
    LoginMethodHandler[] handlersToTry;
    Map<String, String> loggingExtras;
    private LoginLogger loginLogger;
    OnCompletedListener onCompletedListener;
    Request pendingRequest;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator() {
            public LoginClient createFromParcel(final Parcel parcel) {
                return new LoginClient(parcel);
            }
            
            public LoginClient[] newArray(final int n) {
                return new LoginClient[n];
            }
        };
    }
    
    public LoginClient(final Parcel parcel) {
        this.currentHandler = -1;
        final Parcelable[] parcelableArray = parcel.readParcelableArray(LoginMethodHandler.class.getClassLoader());
        this.handlersToTry = new LoginMethodHandler[parcelableArray.length];
        for (int i = 0; i < parcelableArray.length; ++i) {
            (this.handlersToTry[i] = (LoginMethodHandler)parcelableArray[i]).setLoginClient(this);
        }
        this.currentHandler = parcel.readInt();
        this.pendingRequest = (Request)parcel.readParcelable(Request.class.getClassLoader());
        this.loggingExtras = (Map<String, String>)Utility.readStringMapFromParcel(parcel);
    }
    
    public LoginClient(final Fragment fragment) {
        this.currentHandler = -1;
        this.fragment = fragment;
    }
    
    private void addLoggingExtra(final String s, final String s2, final boolean b) {
        if (this.loggingExtras == null) {
            this.loggingExtras = new HashMap<String, String>();
        }
        String string = s2;
        if (this.loggingExtras.containsKey(s)) {
            string = s2;
            if (b) {
                string = this.loggingExtras.get(s) + "," + s2;
            }
        }
        this.loggingExtras.put(s, string);
    }
    
    private void completeWithFailure() {
        this.complete(Result.createErrorResult(this.pendingRequest, "Login attempt failed.", null));
    }
    
    private static AccessToken createFromTokenWithRefreshedPermissions(final AccessToken accessToken, final Collection<String> collection, final Collection<String> collection2) {
        return new AccessToken(accessToken.getToken(), accessToken.getApplicationId(), accessToken.getUserId(), (Collection)collection, (Collection)collection2, accessToken.getSource(), accessToken.getExpires(), accessToken.getLastRefresh());
    }
    
    static String getE2E() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("init", System.currentTimeMillis());
            return jsonObject.toString();
        }
        catch (JSONException ex) {
            return jsonObject.toString();
        }
    }
    
    private LoginLogger getLogger() {
        if (this.loginLogger == null || !this.loginLogger.getApplicationId().equals(this.pendingRequest.getApplicationId())) {
            this.loginLogger = new LoginLogger((Context)this.getActivity(), this.pendingRequest.getApplicationId());
        }
        return this.loginLogger;
    }
    
    public static int getLoginRequestCode() {
        return CallbackManagerImpl$RequestCodeOffset.Login.toRequestCode();
    }
    
    private void logAuthorizationMethodComplete(final String s, final Result result, final Map<String, String> map) {
        this.logAuthorizationMethodComplete(s, result.code.getLoggingValue(), result.errorMessage, result.errorCode, map);
    }
    
    private void logAuthorizationMethodComplete(final String s, final String s2, final String s3, final String s4, final Map<String, String> map) {
        if (this.pendingRequest == null) {
            this.getLogger().logUnexpectedError("fb_mobile_login_method_complete", "Unexpected call to logCompleteLogin with null pendingAuthorizationRequest.", s);
            return;
        }
        this.getLogger().logAuthorizationMethodComplete(this.pendingRequest.getAuthId(), s, s2, s3, s4, map);
    }
    
    private void notifyOnCompleteListener(final Result result) {
        if (this.onCompletedListener != null) {
            this.onCompletedListener.onCompleted(result);
        }
    }
    
    void authorize(final Request pendingRequest) {
        if (pendingRequest != null) {
            if (this.pendingRequest != null) {
                throw new FacebookException("Attempted to authorize while a request is pending.");
            }
            if (!AccessToken.isCurrentAccessTokenActive() || this.checkInternetPermission()) {
                this.pendingRequest = pendingRequest;
                this.handlersToTry = this.getHandlersToTry(pendingRequest);
                this.tryNextHandler();
            }
        }
    }
    
    void cancelCurrentHandler() {
        if (this.currentHandler >= 0) {
            this.getCurrentHandler().cancel();
        }
    }
    
    boolean checkInternetPermission() {
        if (this.checkedInternetPermission) {
            return true;
        }
        if (this.checkPermission("android.permission.INTERNET") != 0) {
            final FragmentActivity activity = this.getActivity();
            this.complete(Result.createErrorResult(this.pendingRequest, ((Activity)activity).getString(R$string.com_facebook_internet_permission_error_title), ((Activity)activity).getString(R$string.com_facebook_internet_permission_error_message)));
            return false;
        }
        return this.checkedInternetPermission = true;
    }
    
    int checkPermission(final String s) {
        return this.getActivity().checkCallingOrSelfPermission(s);
    }
    
    void complete(final Result result) {
        final LoginMethodHandler currentHandler = this.getCurrentHandler();
        if (currentHandler != null) {
            this.logAuthorizationMethodComplete(currentHandler.getNameForLogging(), result, currentHandler.methodLoggingExtras);
        }
        if (this.loggingExtras != null) {
            result.loggingExtras = this.loggingExtras;
        }
        this.handlersToTry = null;
        this.currentHandler = -1;
        this.pendingRequest = null;
        this.loggingExtras = null;
        this.notifyOnCompleteListener(result);
    }
    
    void completeAndValidate(final Result result) {
        if (result.token != null && AccessToken.isCurrentAccessTokenActive()) {
            this.validateSameFbidAndFinish(result);
            return;
        }
        this.complete(result);
    }
    
    public int describeContents() {
        return 0;
    }
    
    FragmentActivity getActivity() {
        return this.fragment.getActivity();
    }
    
    BackgroundProcessingListener getBackgroundProcessingListener() {
        return this.backgroundProcessingListener;
    }
    
    LoginMethodHandler getCurrentHandler() {
        if (this.currentHandler >= 0) {
            return this.handlersToTry[this.currentHandler];
        }
        return null;
    }
    
    public Fragment getFragment() {
        return this.fragment;
    }
    
    protected LoginMethodHandler[] getHandlersToTry(final Request request) {
        final ArrayList<GetTokenLoginMethodHandler> list = new ArrayList<GetTokenLoginMethodHandler>();
        final LoginBehavior loginBehavior = request.getLoginBehavior();
        if (loginBehavior.allowsGetTokenAuth()) {
            list.add(new GetTokenLoginMethodHandler(this));
        }
        if (loginBehavior.allowsKatanaAuth()) {
            list.add((GetTokenLoginMethodHandler)new KatanaProxyLoginMethodHandler(this));
        }
        if (loginBehavior.allowsFacebookLiteAuth()) {
            list.add((GetTokenLoginMethodHandler)new FacebookLiteLoginMethodHandler(this));
        }
        if (loginBehavior.allowsCustomTabAuth()) {
            list.add((GetTokenLoginMethodHandler)new CustomTabLoginMethodHandler(this));
        }
        if (loginBehavior.allowsWebViewAuth()) {
            list.add((GetTokenLoginMethodHandler)new WebViewLoginMethodHandler(this));
        }
        if (loginBehavior.allowsDeviceAuth()) {
            list.add((GetTokenLoginMethodHandler)new DeviceAuthMethodHandler(this));
        }
        final LoginMethodHandler[] array = new LoginMethodHandler[list.size()];
        list.toArray(array);
        return array;
    }
    
    boolean getInProgress() {
        return this.pendingRequest != null && this.currentHandler >= 0;
    }
    
    OnCompletedListener getOnCompletedListener() {
        return this.onCompletedListener;
    }
    
    public Request getPendingRequest() {
        return this.pendingRequest;
    }
    
    void notifyBackgroundProcessingStart() {
        if (this.backgroundProcessingListener != null) {
            this.backgroundProcessingListener.onBackgroundProcessingStarted();
        }
    }
    
    void notifyBackgroundProcessingStop() {
        if (this.backgroundProcessingListener != null) {
            this.backgroundProcessingListener.onBackgroundProcessingStopped();
        }
    }
    
    public boolean onActivityResult(final int n, final int n2, final Intent intent) {
        return this.pendingRequest != null && this.getCurrentHandler().onActivityResult(n, n2, intent);
    }
    
    void setBackgroundProcessingListener(final BackgroundProcessingListener backgroundProcessingListener) {
        this.backgroundProcessingListener = backgroundProcessingListener;
    }
    
    void setFragment(final Fragment fragment) {
        if (this.fragment != null) {
            throw new FacebookException("Can't set fragment once it is already set.");
        }
        this.fragment = fragment;
    }
    
    void setOnCompletedListener(final OnCompletedListener onCompletedListener) {
        this.onCompletedListener = onCompletedListener;
    }
    
    void startOrContinueAuth(final Request request) {
        if (!this.getInProgress()) {
            this.authorize(request);
        }
    }
    
    boolean tryCurrentHandler() {
        final LoginMethodHandler currentHandler = this.getCurrentHandler();
        if (currentHandler.needsInternetPermission() && !this.checkInternetPermission()) {
            this.addLoggingExtra("no_internet_permission", "1", false);
            return false;
        }
        final boolean tryAuthorize = currentHandler.tryAuthorize(this.pendingRequest);
        if (tryAuthorize) {
            this.getLogger().logAuthorizationMethodStart(this.pendingRequest.getAuthId(), currentHandler.getNameForLogging());
            return tryAuthorize;
        }
        this.getLogger().logAuthorizationMethodNotTried(this.pendingRequest.getAuthId(), currentHandler.getNameForLogging());
        this.addLoggingExtra("not_tried", currentHandler.getNameForLogging(), true);
        return tryAuthorize;
    }
    
    void tryNextHandler() {
        if (this.currentHandler >= 0) {
            this.logAuthorizationMethodComplete(this.getCurrentHandler().getNameForLogging(), "skipped", null, null, this.getCurrentHandler().methodLoggingExtras);
        }
        while (this.handlersToTry != null && this.currentHandler < this.handlersToTry.length - 1) {
            ++this.currentHandler;
            if (this.tryCurrentHandler()) {
                return;
            }
        }
        if (this.pendingRequest != null) {
            this.completeWithFailure();
        }
    }
    
    void validateSameFbidAndFinish(Result result) {
        if (result.token == null) {
            throw new FacebookException("Can't validate without a token");
        }
        final AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        final AccessToken token = result.token;
        Label_0067: {
            if (currentAccessToken == null || token == null) {
                break Label_0067;
            }
            try {
                if (currentAccessToken.getUserId().equals(token.getUserId())) {
                    result = Result.createTokenResult(this.pendingRequest, result.token);
                }
                else {
                    result = Result.createErrorResult(this.pendingRequest, "User logged in as different Facebook user.", null);
                }
                this.complete(result);
            }
            catch (Exception ex) {
                this.complete(Result.createErrorResult(this.pendingRequest, "Caught exception", ex.getMessage()));
            }
        }
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeParcelableArray((Parcelable[])this.handlersToTry, n);
        parcel.writeInt(this.currentHandler);
        parcel.writeParcelable((Parcelable)this.pendingRequest, n);
        Utility.writeStringMapToParcel(parcel, (Map)this.loggingExtras);
    }
    
    interface BackgroundProcessingListener
    {
        void onBackgroundProcessingStarted();
        
        void onBackgroundProcessingStopped();
    }
    
    public interface OnCompletedListener
    {
        void onCompleted(final Result p0);
    }
    
    public static class Request implements Parcelable
    {
        public static final Parcelable$Creator<Request> CREATOR;
        private final String applicationId;
        private final String authId;
        private final DefaultAudience defaultAudience;
        private String deviceRedirectUriString;
        private boolean isRerequest;
        private final LoginBehavior loginBehavior;
        private Set<String> permissions;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator() {
                public Request createFromParcel(final Parcel parcel) {
                    return new Request(parcel);
                }
                
                public Request[] newArray(final int n) {
                    return new Request[n];
                }
            };
        }
        
        private Request(final Parcel parcel) {
            final DefaultAudience defaultAudience = null;
            this.isRerequest = false;
            final String string = parcel.readString();
            LoginBehavior value;
            if (string != null) {
                value = LoginBehavior.valueOf(string);
            }
            else {
                value = null;
            }
            this.loginBehavior = value;
            final ArrayList<String> list = new ArrayList<String>();
            parcel.readStringList((List)list);
            this.permissions = new HashSet<String>(list);
            final String string2 = parcel.readString();
            DefaultAudience value2 = defaultAudience;
            if (string2 != null) {
                value2 = DefaultAudience.valueOf(string2);
            }
            this.defaultAudience = value2;
            this.applicationId = parcel.readString();
            this.authId = parcel.readString();
            this.isRerequest = (parcel.readByte() != 0);
            this.deviceRedirectUriString = parcel.readString();
        }
        
        Request(final LoginBehavior loginBehavior, Set<String> permissions, final DefaultAudience defaultAudience, final String applicationId, final String authId) {
            this.isRerequest = false;
            this.loginBehavior = loginBehavior;
            if (permissions == null) {
                permissions = new HashSet<String>();
            }
            this.permissions = permissions;
            this.defaultAudience = defaultAudience;
            this.applicationId = applicationId;
            this.authId = authId;
        }
        
        public int describeContents() {
            return 0;
        }
        
        String getApplicationId() {
            return this.applicationId;
        }
        
        String getAuthId() {
            return this.authId;
        }
        
        DefaultAudience getDefaultAudience() {
            return this.defaultAudience;
        }
        
        String getDeviceRedirectUriString() {
            return this.deviceRedirectUriString;
        }
        
        LoginBehavior getLoginBehavior() {
            return this.loginBehavior;
        }
        
        Set<String> getPermissions() {
            return this.permissions;
        }
        
        boolean hasPublishPermission() {
            final Iterator<String> iterator = this.permissions.iterator();
            while (iterator.hasNext()) {
                if (LoginManager.isPublishPermission(iterator.next())) {
                    return true;
                }
            }
            return false;
        }
        
        boolean isRerequest() {
            return this.isRerequest;
        }
        
        void setDeviceRedirectUriString(final String deviceRedirectUriString) {
            this.deviceRedirectUriString = deviceRedirectUriString;
        }
        
        void setPermissions(final Set<String> permissions) {
            Validate.notNull((Object)permissions, "permissions");
            this.permissions = permissions;
        }
        
        void setRerequest(final boolean isRerequest) {
            this.isRerequest = isRerequest;
        }
        
        public void writeToParcel(final Parcel parcel, int n) {
            final String s = null;
            String name;
            if (this.loginBehavior != null) {
                name = this.loginBehavior.name();
            }
            else {
                name = null;
            }
            parcel.writeString(name);
            parcel.writeStringList((List)new ArrayList(this.permissions));
            String name2 = s;
            if (this.defaultAudience != null) {
                name2 = this.defaultAudience.name();
            }
            parcel.writeString(name2);
            parcel.writeString(this.applicationId);
            parcel.writeString(this.authId);
            if (this.isRerequest) {
                n = 1;
            }
            else {
                n = 0;
            }
            parcel.writeByte((byte)n);
            parcel.writeString(this.deviceRedirectUriString);
        }
    }
    
    public static class Result implements Parcelable
    {
        public static final Parcelable$Creator<Result> CREATOR;
        final Code code;
        final String errorCode;
        final String errorMessage;
        public Map<String, String> loggingExtras;
        final Request request;
        final AccessToken token;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator() {
                public Result createFromParcel(final Parcel parcel) {
                    return new Result(parcel);
                }
                
                public Result[] newArray(final int n) {
                    return new Result[n];
                }
            };
        }
        
        private Result(final Parcel parcel) {
            this.code = Code.valueOf(parcel.readString());
            this.token = (AccessToken)parcel.readParcelable(AccessToken.class.getClassLoader());
            this.errorMessage = parcel.readString();
            this.errorCode = parcel.readString();
            this.request = (Request)parcel.readParcelable(Request.class.getClassLoader());
            this.loggingExtras = (Map<String, String>)Utility.readStringMapFromParcel(parcel);
        }
        
        Result(final Request request, final Code code, final AccessToken token, final String errorMessage, final String errorCode) {
            Validate.notNull((Object)code, "code");
            this.request = request;
            this.token = token;
            this.errorMessage = errorMessage;
            this.code = code;
            this.errorCode = errorCode;
        }
        
        static Result createCancelResult(final Request request, final String s) {
            return new Result(request, Code.CANCEL, null, s, null);
        }
        
        static Result createErrorResult(final Request request, final String s, final String s2) {
            return createErrorResult(request, s, s2, null);
        }
        
        static Result createErrorResult(final Request request, String join, final String s, final String s2) {
            join = TextUtils.join((CharSequence)": ", (Iterable)Utility.asListNoNulls((Object[])new String[] { join, s }));
            return new Result(request, Code.ERROR, null, join, s2);
        }
        
        static Result createTokenResult(final Request request, final AccessToken accessToken) {
            return new Result(request, Code.SUCCESS, accessToken, null, null);
        }
        
        public int describeContents() {
            return 0;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            parcel.writeString(this.code.name());
            parcel.writeParcelable((Parcelable)this.token, n);
            parcel.writeString(this.errorMessage);
            parcel.writeString(this.errorCode);
            parcel.writeParcelable((Parcelable)this.request, n);
            Utility.writeStringMapToParcel(parcel, (Map)this.loggingExtras);
        }
        
        enum Code
        {
            CANCEL("cancel"), 
            ERROR("error"), 
            SUCCESS("success");
            
            private final String loggingValue;
            
            private Code(final String loggingValue) {
                this.loggingValue = loggingValue;
            }
            
            String getLoggingValue() {
                return this.loggingValue;
            }
        }
    }
}
