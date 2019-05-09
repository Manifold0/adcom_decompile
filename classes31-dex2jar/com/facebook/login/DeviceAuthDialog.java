// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.login;

import java.util.Locale;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.facebook.internal.Validate;
import android.text.TextUtils;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.facebook.FacebookActivity;
import android.support.annotation.NonNull;
import android.content.Context;
import com.facebook.common.R$style;
import com.facebook.appevents.AppEventsLogger;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;
import java.util.concurrent.TimeUnit;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import org.json.JSONObject;
import com.facebook.internal.SmartLoginOption;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import android.view.LayoutInflater;
import android.text.Html;
import com.facebook.common.R$string;
import android.view.View$OnClickListener;
import android.widget.Button;
import com.facebook.common.R$id;
import android.view.ViewGroup;
import com.facebook.common.R$layout;
import com.facebook.AccessToken;
import com.facebook.FacebookRequestError;
import org.json.JSONException;
import com.facebook.GraphResponse;
import com.facebook.GraphRequest$Callback;
import com.facebook.HttpMethod;
import android.os.Bundle;
import com.facebook.GraphRequest;
import java.util.Date;
import java.util.Collection;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookSdk;
import android.view.View;
import com.facebook.internal.Utility$PermissionsPair;
import com.facebook.FacebookException;
import java.util.concurrent.ScheduledFuture;
import android.widget.ProgressBar;
import android.app.Dialog;
import com.facebook.GraphRequestAsyncTask;
import android.widget.TextView;
import java.util.concurrent.atomic.AtomicBoolean;
import android.support.v4.app.DialogFragment;

public class DeviceAuthDialog extends DialogFragment
{
    private static final String DEVICE_LOGIN_ENDPOINT = "device/login";
    private static final String DEVICE_LOGIN_STATUS_ENDPOINT = "device/login_status";
    private static final int LOGIN_ERROR_SUBCODE_AUTHORIZATION_DECLINED = 1349173;
    private static final int LOGIN_ERROR_SUBCODE_AUTHORIZATION_PENDING = 1349174;
    private static final int LOGIN_ERROR_SUBCODE_CODE_EXPIRED = 1349152;
    private static final int LOGIN_ERROR_SUBCODE_EXCESSIVE_POLLING = 1349172;
    private static final String REQUEST_STATE_KEY = "request_state";
    private AtomicBoolean completed;
    private TextView confirmationCode;
    private volatile GraphRequestAsyncTask currentGraphRequestPoll;
    private volatile RequestState currentRequestState;
    private DeviceAuthMethodHandler deviceAuthMethodHandler;
    private Dialog dialog;
    private TextView instructions;
    private boolean isBeingDestroyed;
    private boolean isRetry;
    private LoginClient.Request mRequest;
    private ProgressBar progressBar;
    private volatile ScheduledFuture scheduledPoll;
    
    public DeviceAuthDialog() {
        this.completed = new AtomicBoolean();
        this.isBeingDestroyed = false;
        this.isRetry = false;
        this.mRequest = null;
    }
    
    private void completeLogin(final String s, final Utility$PermissionsPair utility$PermissionsPair, final String s2) {
        this.deviceAuthMethodHandler.onSuccess(s2, FacebookSdk.getApplicationId(), s, utility$PermissionsPair.getGrantedPermissions(), utility$PermissionsPair.getDeclinedPermissions(), AccessTokenSource.DEVICE_AUTH, null, null);
        this.dialog.dismiss();
    }
    
    private GraphRequest getPollRequest() {
        final Bundle bundle = new Bundle();
        bundle.putString("code", this.currentRequestState.getRequestCode());
        return new GraphRequest((AccessToken)null, "device/login_status", bundle, HttpMethod.POST, (GraphRequest$Callback)new GraphRequest$Callback() {
            public void onCompleted(final GraphResponse graphResponse) {
                if (DeviceAuthDialog.this.completed.get()) {
                    return;
                }
                final FacebookRequestError error = graphResponse.getError();
                if (error != null) {
                    switch (error.getSubErrorCode()) {
                        default: {
                            DeviceAuthDialog.this.onError(graphResponse.getError().getException());
                        }
                        case 1349172:
                        case 1349174: {
                            DeviceAuthDialog.this.schedulePoll();
                        }
                        case 1349152:
                        case 1349173: {
                            DeviceAuthDialog.this.onCancel();
                        }
                    }
                }
                else {
                    try {
                        DeviceAuthDialog.this.onSuccess(graphResponse.getJSONObject().getString("access_token"));
                    }
                    catch (JSONException ex) {
                        DeviceAuthDialog.this.onError(new FacebookException((Throwable)ex));
                    }
                }
            }
        });
    }
    
    private View initializeContentView(final boolean b) {
        final LayoutInflater layoutInflater = this.getActivity().getLayoutInflater();
        View view;
        if (b) {
            view = layoutInflater.inflate(R$layout.com_facebook_smart_device_dialog_fragment, (ViewGroup)null);
        }
        else {
            view = layoutInflater.inflate(R$layout.com_facebook_device_auth_dialog_fragment, (ViewGroup)null);
        }
        this.progressBar = (ProgressBar)view.findViewById(R$id.progress_bar);
        this.confirmationCode = (TextView)view.findViewById(R$id.confirmation_code);
        ((Button)view.findViewById(R$id.cancel_button)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                DeviceAuthDialog.this.onCancel();
            }
        });
        (this.instructions = (TextView)view.findViewById(R$id.com_facebook_device_auth_instructions)).setText((CharSequence)Html.fromHtml(this.getString(R$string.com_facebook_device_auth_instructions)));
        return view;
    }
    
    private void onCancel() {
        if (!this.completed.compareAndSet(false, true)) {
            return;
        }
        if (this.currentRequestState != null) {
            DeviceRequestsHelper.cleanUpAdvertisementService(this.currentRequestState.getUserCode());
        }
        if (this.deviceAuthMethodHandler != null) {
            this.deviceAuthMethodHandler.onCancel();
        }
        this.dialog.dismiss();
    }
    
    private void onError(final FacebookException ex) {
        if (!this.completed.compareAndSet(false, true)) {
            return;
        }
        if (this.currentRequestState != null) {
            DeviceRequestsHelper.cleanUpAdvertisementService(this.currentRequestState.getUserCode());
        }
        this.deviceAuthMethodHandler.onError((Exception)ex);
        this.dialog.dismiss();
    }
    
    private void onSuccess(final String s) {
        final Bundle bundle = new Bundle();
        bundle.putString("fields", "id,permissions,name");
        new GraphRequest(new AccessToken(s, FacebookSdk.getApplicationId(), "0", (Collection)null, (Collection)null, (AccessTokenSource)null, (Date)null, (Date)null), "me", bundle, HttpMethod.GET, (GraphRequest$Callback)new GraphRequest$Callback() {
            public void onCompleted(final GraphResponse graphResponse) {
                if (DeviceAuthDialog.this.completed.get()) {
                    return;
                }
                if (graphResponse.getError() != null) {
                    DeviceAuthDialog.this.onError(graphResponse.getError().getException());
                    return;
                }
                String string;
                Utility$PermissionsPair handlePermissionResponse;
                try {
                    final JSONObject jsonObject = graphResponse.getJSONObject();
                    string = jsonObject.getString("id");
                    handlePermissionResponse = Utility.handlePermissionResponse(jsonObject);
                    final String string2 = jsonObject.getString("name");
                    DeviceRequestsHelper.cleanUpAdvertisementService(DeviceAuthDialog.this.currentRequestState.getUserCode());
                    if (FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId()).getSmartLoginOptions().contains(SmartLoginOption.RequireConfirm) && !DeviceAuthDialog.this.isRetry) {
                        DeviceAuthDialog.this.isRetry = true;
                        DeviceAuthDialog.this.presentConfirmation(string, handlePermissionResponse, s, string2);
                        return;
                    }
                }
                catch (JSONException ex) {
                    DeviceAuthDialog.this.onError(new FacebookException((Throwable)ex));
                    return;
                }
                DeviceAuthDialog.this.completeLogin(string, handlePermissionResponse, s);
            }
        }).executeAsync();
    }
    
    private void poll() {
        this.currentRequestState.setLastPoll(new Date().getTime());
        this.currentGraphRequestPoll = this.getPollRequest().executeAsync();
    }
    
    private void presentConfirmation(final String s, final Utility$PermissionsPair utility$PermissionsPair, final String s2, String format) {
        final String string = this.getResources().getString(R$string.com_facebook_smart_login_confirmation_title);
        final String string2 = this.getResources().getString(R$string.com_facebook_smart_login_confirmation_continue_as);
        final String string3 = this.getResources().getString(R$string.com_facebook_smart_login_confirmation_cancel);
        format = String.format(string2, format);
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.getContext());
        alertDialog$Builder.setMessage((CharSequence)string).setCancelable(true).setNegativeButton((CharSequence)format, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                DeviceAuthDialog.this.completeLogin(s, utility$PermissionsPair, s2);
            }
        }).setPositiveButton((CharSequence)string3, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                DeviceAuthDialog.this.dialog.setContentView(DeviceAuthDialog.this.initializeContentView(false));
                DeviceAuthDialog.this.startLogin(DeviceAuthDialog.this.mRequest);
            }
        });
        alertDialog$Builder.create().show();
    }
    
    private void schedulePoll() {
        this.scheduledPoll = DeviceAuthMethodHandler.getBackgroundExecutor().schedule(new Runnable() {
            @Override
            public void run() {
                DeviceAuthDialog.this.poll();
            }
        }, this.currentRequestState.getInterval(), TimeUnit.SECONDS);
    }
    
    private void setCurrentRequestState(final RequestState currentRequestState) {
        this.currentRequestState = currentRequestState;
        this.confirmationCode.setText((CharSequence)currentRequestState.getUserCode());
        this.instructions.setCompoundDrawablesWithIntrinsicBounds((Drawable)null, (Drawable)new BitmapDrawable(this.getResources(), DeviceRequestsHelper.generateQRCode(currentRequestState.getAuthorizationUri())), (Drawable)null, (Drawable)null);
        this.confirmationCode.setVisibility(0);
        this.progressBar.setVisibility(8);
        if (!this.isRetry && DeviceRequestsHelper.startAdvertisementService(currentRequestState.getUserCode())) {
            AppEventsLogger.newLogger(this.getContext()).logSdkEvent("fb_smart_login_service", (Double)null, (Bundle)null);
        }
        if (currentRequestState.withinLastRefreshWindow()) {
            this.schedulePoll();
            return;
        }
        this.poll();
    }
    
    @NonNull
    public Dialog onCreateDialog(final Bundle bundle) {
        (this.dialog = new Dialog((Context)this.getActivity(), R$style.com_facebook_auth_dialog)).setContentView(this.initializeContentView(DeviceRequestsHelper.isAvailable() && !this.isRetry));
        return this.dialog;
    }
    
    @Nullable
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.deviceAuthMethodHandler = (DeviceAuthMethodHandler)((LoginFragment)((FacebookActivity)this.getActivity()).getCurrentFragment()).getLoginClient().getCurrentHandler();
        if (bundle != null) {
            final RequestState currentRequestState = (RequestState)bundle.getParcelable("request_state");
            if (currentRequestState != null) {
                this.setCurrentRequestState(currentRequestState);
            }
        }
        return onCreateView;
    }
    
    public void onDestroy() {
        this.isBeingDestroyed = true;
        this.completed.set(true);
        super.onDestroy();
        if (this.currentGraphRequestPoll != null) {
            this.currentGraphRequestPoll.cancel(true);
        }
        if (this.scheduledPoll != null) {
            this.scheduledPoll.cancel(true);
        }
    }
    
    public void onDismiss(final DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (!this.isBeingDestroyed) {
            this.onCancel();
        }
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.currentRequestState != null) {
            bundle.putParcelable("request_state", (Parcelable)this.currentRequestState);
        }
    }
    
    public void startLogin(final LoginClient.Request mRequest) {
        this.mRequest = mRequest;
        final Bundle bundle = new Bundle();
        bundle.putString("scope", TextUtils.join((CharSequence)",", (Iterable)mRequest.getPermissions()));
        final String deviceRedirectUriString = mRequest.getDeviceRedirectUriString();
        if (deviceRedirectUriString != null) {
            bundle.putString("redirect_uri", deviceRedirectUriString);
        }
        bundle.putString("access_token", Validate.hasAppID() + "|" + Validate.hasClientToken());
        bundle.putString("device_info", DeviceRequestsHelper.getDeviceInfo());
        new GraphRequest((AccessToken)null, "device/login", bundle, HttpMethod.POST, (GraphRequest$Callback)new GraphRequest$Callback() {
            public void onCompleted(final GraphResponse graphResponse) {
                if (DeviceAuthDialog.this.isBeingDestroyed) {
                    return;
                }
                if (graphResponse.getError() != null) {
                    DeviceAuthDialog.this.onError(graphResponse.getError().getException());
                    return;
                }
                final JSONObject jsonObject = graphResponse.getJSONObject();
                final RequestState requestState = new RequestState();
                try {
                    requestState.setUserCode(jsonObject.getString("user_code"));
                    requestState.setRequestCode(jsonObject.getString("code"));
                    requestState.setInterval(jsonObject.getLong("interval"));
                    DeviceAuthDialog.this.setCurrentRequestState(requestState);
                }
                catch (JSONException ex) {
                    DeviceAuthDialog.this.onError(new FacebookException((Throwable)ex));
                }
            }
        }).executeAsync();
    }
    
    private static class RequestState implements Parcelable
    {
        public static final Parcelable$Creator<RequestState> CREATOR;
        private String authorizationUri;
        private long interval;
        private long lastPoll;
        private String requestCode;
        private String userCode;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<RequestState>() {
                public RequestState createFromParcel(final Parcel parcel) {
                    return new RequestState(parcel);
                }
                
                public RequestState[] newArray(final int n) {
                    return new RequestState[n];
                }
            };
        }
        
        RequestState() {
        }
        
        protected RequestState(final Parcel parcel) {
            this.userCode = parcel.readString();
            this.requestCode = parcel.readString();
            this.interval = parcel.readLong();
            this.lastPoll = parcel.readLong();
        }
        
        public int describeContents() {
            return 0;
        }
        
        public String getAuthorizationUri() {
            return this.authorizationUri;
        }
        
        public long getInterval() {
            return this.interval;
        }
        
        public String getRequestCode() {
            return this.requestCode;
        }
        
        public String getUserCode() {
            return this.userCode;
        }
        
        public void setInterval(final long interval) {
            this.interval = interval;
        }
        
        public void setLastPoll(final long lastPoll) {
            this.lastPoll = lastPoll;
        }
        
        public void setRequestCode(final String requestCode) {
            this.requestCode = requestCode;
        }
        
        public void setUserCode(final String userCode) {
            this.userCode = userCode;
            this.authorizationUri = String.format(Locale.ENGLISH, "https://facebook.com/device?user_code=%1$s&qr=1", userCode);
        }
        
        public boolean withinLastRefreshWindow() {
            return this.lastPoll != 0L && new Date().getTime() - this.lastPoll - this.interval * 1000L < 0L;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            parcel.writeString(this.userCode);
            parcel.writeString(this.requestCode);
            parcel.writeLong(this.interval);
            parcel.writeLong(this.lastPoll);
        }
    }
}
