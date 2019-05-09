// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.support.annotation.NonNull;
import android.text.Html;
import com.facebook.common.R$string;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.Button;
import com.facebook.common.R$id;
import android.view.ViewGroup;
import com.facebook.common.R$layout;
import android.content.Context;
import com.facebook.common.R$style;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import org.json.JSONObject;
import org.json.JSONException;
import com.facebook.GraphResponse;
import com.facebook.GraphRequest$Callback;
import com.facebook.HttpMethod;
import com.facebook.internal.Validate;
import java.util.concurrent.TimeUnit;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareLinkContent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import android.content.Intent;
import android.support.v4.app.Fragment;
import com.facebook.FacebookRequestError;
import com.facebook.share.model.ShareContent;
import android.widget.ProgressBar;
import android.app.Dialog;
import android.widget.TextView;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import android.support.v4.app.DialogFragment;

public class DeviceShareDialogFragment extends DialogFragment
{
    private static final String DEVICE_SHARE_ENDPOINT = "device/share";
    private static final String EXTRA_ERROR = "error";
    private static final String REQUEST_STATE_KEY = "request_state";
    public static final String TAG = "DeviceShareDialogFragment";
    private static ScheduledThreadPoolExecutor backgroundExecutor;
    private volatile ScheduledFuture codeExpiredFuture;
    private TextView confirmationCode;
    private volatile RequestState currentRequestState;
    private Dialog dialog;
    private ProgressBar progressBar;
    private ShareContent shareContent;
    
    private void detach() {
        if (this.isAdded()) {
            this.getFragmentManager().beginTransaction().remove((Fragment)this).commit();
        }
    }
    
    private void finishActivity(final int n, final Intent intent) {
        if (this.currentRequestState != null) {
            DeviceRequestsHelper.cleanUpAdvertisementService(this.currentRequestState.getUserCode());
        }
        final FacebookRequestError facebookRequestError = (FacebookRequestError)intent.getParcelableExtra("error");
        if (facebookRequestError != null) {
            Toast.makeText(this.getContext(), (CharSequence)facebookRequestError.getErrorMessage(), 0).show();
        }
        if (this.isAdded()) {
            final FragmentActivity activity = this.getActivity();
            ((Activity)activity).setResult(n, intent);
            ((Activity)activity).finish();
        }
    }
    
    private void finishActivityWithError(final FacebookRequestError facebookRequestError) {
        this.detach();
        final Intent intent = new Intent();
        intent.putExtra("error", (Parcelable)facebookRequestError);
        this.finishActivity(-1, intent);
    }
    
    private static ScheduledThreadPoolExecutor getBackgroundExecutor() {
        synchronized (DeviceShareDialogFragment.class) {
            if (DeviceShareDialogFragment.backgroundExecutor == null) {
                DeviceShareDialogFragment.backgroundExecutor = new ScheduledThreadPoolExecutor(1);
            }
            return DeviceShareDialogFragment.backgroundExecutor;
        }
    }
    
    private Bundle getGraphParametersForShareContent() {
        final ShareContent shareContent = this.shareContent;
        if (shareContent != null) {
            if (shareContent instanceof ShareLinkContent) {
                return WebDialogParameters.create((ShareLinkContent)shareContent);
            }
            if (shareContent instanceof ShareOpenGraphContent) {
                return WebDialogParameters.create((ShareOpenGraphContent)shareContent);
            }
        }
        return null;
    }
    
    private void setCurrentRequestState(final RequestState currentRequestState) {
        this.currentRequestState = currentRequestState;
        this.confirmationCode.setText((CharSequence)currentRequestState.getUserCode());
        this.confirmationCode.setVisibility(0);
        this.progressBar.setVisibility(8);
        this.codeExpiredFuture = getBackgroundExecutor().schedule(new Runnable() {
            @Override
            public void run() {
                DeviceShareDialogFragment.this.dialog.dismiss();
            }
        }, currentRequestState.getExpiresIn(), TimeUnit.SECONDS);
    }
    
    private void startShare() {
        final Bundle graphParametersForShareContent = this.getGraphParametersForShareContent();
        if (graphParametersForShareContent == null || graphParametersForShareContent.size() == 0) {
            this.finishActivityWithError(new FacebookRequestError(0, "", "Failed to get share content"));
        }
        graphParametersForShareContent.putString("access_token", Validate.hasAppID() + "|" + Validate.hasClientToken());
        graphParametersForShareContent.putString("device_info", DeviceRequestsHelper.getDeviceInfo());
        new GraphRequest((AccessToken)null, "device/share", graphParametersForShareContent, HttpMethod.POST, (GraphRequest$Callback)new GraphRequest$Callback() {
            public void onCompleted(final GraphResponse graphResponse) {
                final FacebookRequestError error = graphResponse.getError();
                if (error != null) {
                    DeviceShareDialogFragment.this.finishActivityWithError(error);
                    return;
                }
                final JSONObject jsonObject = graphResponse.getJSONObject();
                final RequestState requestState = new RequestState();
                try {
                    requestState.setUserCode(jsonObject.getString("user_code"));
                    requestState.setExpiresIn(jsonObject.getLong("expires_in"));
                    DeviceShareDialogFragment.this.setCurrentRequestState(requestState);
                }
                catch (JSONException ex) {
                    DeviceShareDialogFragment.this.finishActivityWithError(new FacebookRequestError(0, "", "Malformed server response"));
                }
            }
        }).executeAsync();
    }
    
    @NonNull
    public Dialog onCreateDialog(final Bundle bundle) {
        this.dialog = new Dialog((Context)this.getActivity(), R$style.com_facebook_auth_dialog);
        final View inflate = this.getActivity().getLayoutInflater().inflate(R$layout.com_facebook_device_auth_dialog_fragment, (ViewGroup)null);
        this.progressBar = (ProgressBar)inflate.findViewById(R$id.progress_bar);
        this.confirmationCode = (TextView)inflate.findViewById(R$id.confirmation_code);
        ((Button)inflate.findViewById(R$id.cancel_button)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                DeviceShareDialogFragment.this.dialog.dismiss();
            }
        });
        ((TextView)inflate.findViewById(R$id.com_facebook_device_auth_instructions)).setText((CharSequence)Html.fromHtml(this.getString(R$string.com_facebook_device_auth_instructions)));
        this.dialog.setContentView(inflate);
        this.startShare();
        return this.dialog;
    }
    
    @Nullable
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (bundle != null) {
            final RequestState currentRequestState = (RequestState)bundle.getParcelable("request_state");
            if (currentRequestState != null) {
                this.setCurrentRequestState(currentRequestState);
            }
        }
        return onCreateView;
    }
    
    public void onDismiss(final DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.codeExpiredFuture != null) {
            this.codeExpiredFuture.cancel(true);
        }
        this.finishActivity(-1, new Intent());
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.currentRequestState != null) {
            bundle.putParcelable("request_state", (Parcelable)this.currentRequestState);
        }
    }
    
    public void setShareContent(final ShareContent shareContent) {
        this.shareContent = shareContent;
    }
    
    private static class RequestState implements Parcelable
    {
        public static final Parcelable$Creator<RequestState> CREATOR;
        private long expiresIn;
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
            this.expiresIn = parcel.readLong();
        }
        
        public int describeContents() {
            return 0;
        }
        
        public long getExpiresIn() {
            return this.expiresIn;
        }
        
        public String getUserCode() {
            return this.userCode;
        }
        
        public void setExpiresIn(final long expiresIn) {
            this.expiresIn = expiresIn;
        }
        
        public void setUserCode(final String userCode) {
            this.userCode = userCode;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            parcel.writeString(this.userCode);
            parcel.writeLong(this.expiresIn);
        }
    }
}
