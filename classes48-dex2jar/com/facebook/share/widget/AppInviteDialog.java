// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.widget;

import com.facebook.internal.DialogPresenter;
import com.facebook.internal.DialogPresenter$ParameterProvider;
import android.content.Intent;
import com.facebook.internal.CallbackManagerImpl$Callback;
import com.facebook.share.internal.ShareInternalUtility;
import com.facebook.share.internal.ResultProcessor;
import com.facebook.FacebookCallback;
import com.facebook.internal.CallbackManagerImpl;
import java.util.ArrayList;
import com.facebook.internal.FacebookDialogBase$ModeHandler;
import java.util.List;
import com.facebook.internal.AppCall;
import com.facebook.share.internal.AppInviteDialogFeature;
import org.json.JSONException;
import android.util.Log;
import org.json.JSONObject;
import android.text.TextUtils;
import com.facebook.internal.DialogFeature;
import android.os.Bundle;
import com.facebook.internal.FragmentWrapper;
import android.app.Fragment;
import android.app.Activity;
import com.facebook.internal.CallbackManagerImpl$RequestCodeOffset;
import com.facebook.share.model.AppInviteContent;
import com.facebook.internal.FacebookDialogBase;

@Deprecated
public class AppInviteDialog extends FacebookDialogBase<AppInviteContent, Result>
{
    private static final int DEFAULT_REQUEST_CODE;
    private static final String TAG = "AppInviteDialog";
    
    static {
        DEFAULT_REQUEST_CODE = CallbackManagerImpl$RequestCodeOffset.AppInvite.toRequestCode();
    }
    
    @Deprecated
    public AppInviteDialog(final Activity activity) {
        super(activity, AppInviteDialog.DEFAULT_REQUEST_CODE);
    }
    
    @Deprecated
    public AppInviteDialog(final Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    @Deprecated
    public AppInviteDialog(final android.support.v4.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    private AppInviteDialog(final FragmentWrapper fragmentWrapper) {
        super(fragmentWrapper, AppInviteDialog.DEFAULT_REQUEST_CODE);
    }
    
    @Deprecated
    public static boolean canShow() {
        return false;
    }
    
    private static boolean canShowNativeDialog() {
        return false;
    }
    
    private static boolean canShowWebFallback() {
        return false;
    }
    
    private static Bundle createParameters(final AppInviteContent appInviteContent) {
        final Bundle bundle = new Bundle();
        bundle.putString("app_link_url", appInviteContent.getApplinkUrl());
        bundle.putString("preview_image_url", appInviteContent.getPreviewImageUrl());
        bundle.putString("destination", appInviteContent.getDestination().toString());
        String promotionCode = appInviteContent.getPromotionCode();
        Label_0112: {
            if (promotionCode == null) {
                break Label_0112;
            }
            while (true) {
                final String promotionText = appInviteContent.getPromotionText();
                if (TextUtils.isEmpty((CharSequence)promotionText)) {
                    return bundle;
                }
                try {
                    final JSONObject jsonObject = new JSONObject();
                    jsonObject.put("promo_code", (Object)promotionCode);
                    jsonObject.put("promo_text", (Object)promotionText);
                    bundle.putString("deeplink_context", jsonObject.toString());
                    bundle.putString("promo_code", promotionCode);
                    bundle.putString("promo_text", promotionText);
                    return bundle;
                    promotionCode = "";
                    continue;
                }
                catch (JSONException ex) {
                    Log.e("AppInviteDialog", "Json Exception in creating deeplink context");
                    return bundle;
                }
                break;
            }
        }
    }
    
    private static DialogFeature getFeature() {
        return (DialogFeature)AppInviteDialogFeature.APP_INVITES_DIALOG;
    }
    
    @Deprecated
    public static void show(final Activity activity, final AppInviteContent appInviteContent) {
        new AppInviteDialog(activity).show(appInviteContent);
    }
    
    @Deprecated
    public static void show(final Fragment fragment, final AppInviteContent appInviteContent) {
        show(new FragmentWrapper(fragment), appInviteContent);
    }
    
    @Deprecated
    public static void show(final android.support.v4.app.Fragment fragment, final AppInviteContent appInviteContent) {
        show(new FragmentWrapper(fragment), appInviteContent);
    }
    
    private static void show(final FragmentWrapper fragmentWrapper, final AppInviteContent appInviteContent) {
        new AppInviteDialog(fragmentWrapper).show(appInviteContent);
    }
    
    protected AppCall createBaseAppCall() {
        return new AppCall(this.getRequestCode());
    }
    
    protected List<FacebookDialogBase$ModeHandler> getOrderedModeHandlers() {
        final ArrayList<NativeHandler> list = (ArrayList<NativeHandler>)new ArrayList<FacebookDialogBase$ModeHandler>();
        list.add(new NativeHandler());
        list.add((NativeHandler)new WebFallbackHandler());
        return (List<FacebookDialogBase$ModeHandler>)list;
    }
    
    protected void registerCallbackImpl(final CallbackManagerImpl callbackManagerImpl, final FacebookCallback<Result> facebookCallback) {
        ResultProcessor resultProcessor;
        if (facebookCallback == null) {
            resultProcessor = null;
        }
        else {
            resultProcessor = new ResultProcessor(facebookCallback) {
                public void onSuccess(final AppCall appCall, final Bundle bundle) {
                    if ("cancel".equalsIgnoreCase(ShareInternalUtility.getNativeDialogCompletionGesture(bundle))) {
                        facebookCallback.onCancel();
                        return;
                    }
                    facebookCallback.onSuccess((Object)new Result(bundle));
                }
            };
        }
        callbackManagerImpl.registerCallback(this.getRequestCode(), (CallbackManagerImpl$Callback)new CallbackManagerImpl$Callback() {
            public boolean onActivityResult(final int n, final Intent intent) {
                return ShareInternalUtility.handleActivityResult(AppInviteDialog.this.getRequestCode(), n, intent, resultProcessor);
            }
        });
    }
    
    @Deprecated
    public void show(final AppInviteContent appInviteContent) {
    }
    
    private class NativeHandler extends FacebookDialogBase$ModeHandler
    {
        private NativeHandler() {
            super((FacebookDialogBase)AppInviteDialog.this);
        }
        
        public boolean canShow(final AppInviteContent appInviteContent, final boolean b) {
            return false;
        }
        
        public AppCall createAppCall(final AppInviteContent appInviteContent) {
            final AppCall baseAppCall = AppInviteDialog.this.createBaseAppCall();
            DialogPresenter.setupAppCallForNativeDialog(baseAppCall, (DialogPresenter$ParameterProvider)new DialogPresenter$ParameterProvider() {
                public Bundle getLegacyParameters() {
                    Log.e("AppInviteDialog", "Attempting to present the AppInviteDialog with an outdated Facebook app on the device");
                    return new Bundle();
                }
                
                public Bundle getParameters() {
                    return createParameters(appInviteContent);
                }
            }, getFeature());
            return baseAppCall;
        }
    }
    
    @Deprecated
    public static final class Result
    {
        private final Bundle bundle;
        
        public Result(final Bundle bundle) {
            this.bundle = bundle;
        }
        
        public Bundle getData() {
            return this.bundle;
        }
    }
    
    private class WebFallbackHandler extends FacebookDialogBase$ModeHandler
    {
        private WebFallbackHandler() {
            super((FacebookDialogBase)AppInviteDialog.this);
        }
        
        public boolean canShow(final AppInviteContent appInviteContent, final boolean b) {
            return false;
        }
        
        public AppCall createAppCall(final AppInviteContent appInviteContent) {
            final AppCall baseAppCall = AppInviteDialog.this.createBaseAppCall();
            DialogPresenter.setupAppCallForWebFallbackDialog(baseAppCall, createParameters(appInviteContent), getFeature());
            return baseAppCall;
        }
    }
}
