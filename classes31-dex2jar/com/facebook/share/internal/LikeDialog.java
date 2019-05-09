// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import android.util.Log;
import com.facebook.internal.DialogPresenter;
import android.content.Intent;
import com.facebook.internal.CallbackManagerImpl$Callback;
import com.facebook.FacebookCallback;
import com.facebook.internal.CallbackManagerImpl;
import java.util.ArrayList;
import java.util.List;
import com.facebook.internal.AppCall;
import com.facebook.internal.DialogFeature;
import android.os.Bundle;
import com.facebook.internal.FragmentWrapper;
import android.app.Fragment;
import android.app.Activity;
import com.facebook.internal.CallbackManagerImpl$RequestCodeOffset;
import com.facebook.internal.FacebookDialogBase;

@Deprecated
public class LikeDialog extends FacebookDialogBase<LikeContent, Result>
{
    private static final int DEFAULT_REQUEST_CODE;
    private static final String TAG = "LikeDialog";
    
    static {
        DEFAULT_REQUEST_CODE = CallbackManagerImpl$RequestCodeOffset.Like.toRequestCode();
    }
    
    @Deprecated
    public LikeDialog(final Activity activity) {
        super(activity, LikeDialog.DEFAULT_REQUEST_CODE);
    }
    
    @Deprecated
    public LikeDialog(final Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    @Deprecated
    public LikeDialog(final android.support.v4.app.Fragment fragment) {
        this(new FragmentWrapper(fragment));
    }
    
    @Deprecated
    public LikeDialog(final FragmentWrapper fragmentWrapper) {
        super(fragmentWrapper, LikeDialog.DEFAULT_REQUEST_CODE);
    }
    
    @Deprecated
    public static boolean canShowNativeDialog() {
        return false;
    }
    
    @Deprecated
    public static boolean canShowWebFallback() {
        return false;
    }
    
    private static Bundle createParameters(final LikeContent likeContent) {
        final Bundle bundle = new Bundle();
        bundle.putString("object_id", likeContent.getObjectId());
        bundle.putString("object_type", likeContent.getObjectType());
        return bundle;
    }
    
    private static DialogFeature getFeature() {
        return LikeDialogFeature.LIKE_DIALOG;
    }
    
    @Override
    protected AppCall createBaseAppCall() {
        return new AppCall(this.getRequestCode());
    }
    
    @Override
    protected List<ModeHandler> getOrderedModeHandlers() {
        final ArrayList<NativeHandler> list = (ArrayList<NativeHandler>)new ArrayList<ModeHandler>();
        list.add(new NativeHandler());
        list.add((NativeHandler)new WebFallbackHandler());
        return (List<ModeHandler>)list;
    }
    
    @Override
    protected void registerCallbackImpl(final CallbackManagerImpl callbackManagerImpl, final FacebookCallback<Result> facebookCallback) {
        ResultProcessor resultProcessor;
        if (facebookCallback == null) {
            resultProcessor = null;
        }
        else {
            resultProcessor = new ResultProcessor(facebookCallback) {
                @Override
                public void onSuccess(final AppCall appCall, final Bundle bundle) {
                    facebookCallback.onSuccess(new Result(bundle));
                }
            };
        }
        callbackManagerImpl.registerCallback(this.getRequestCode(), (CallbackManagerImpl$Callback)new CallbackManagerImpl$Callback() {
            public boolean onActivityResult(final int n, final Intent intent) {
                return ShareInternalUtility.handleActivityResult(LikeDialog.this.getRequestCode(), n, intent, resultProcessor);
            }
        });
    }
    
    @Deprecated
    @Override
    public void show(final LikeContent likeContent) {
    }
    
    private class NativeHandler extends ModeHandler
    {
        public boolean canShow(final LikeContent likeContent, final boolean b) {
            return false;
        }
        
        public AppCall createAppCall(final LikeContent likeContent) {
            final AppCall baseAppCall = LikeDialog.this.createBaseAppCall();
            DialogPresenter.setupAppCallForNativeDialog(baseAppCall, (DialogPresenter.ParameterProvider)new DialogPresenter.ParameterProvider() {
                @Override
                public Bundle getLegacyParameters() {
                    Log.e("LikeDialog", "Attempting to present the Like Dialog with an outdated Facebook app on the device");
                    return new Bundle();
                }
                
                @Override
                public Bundle getParameters() {
                    return createParameters(likeContent);
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
    
    private class WebFallbackHandler extends ModeHandler
    {
        public boolean canShow(final LikeContent likeContent, final boolean b) {
            return false;
        }
        
        public AppCall createAppCall(final LikeContent likeContent) {
            final AppCall baseAppCall = LikeDialog.this.createBaseAppCall();
            DialogPresenter.setupAppCallForWebFallbackDialog(baseAppCall, createParameters(likeContent), getFeature());
            return baseAppCall;
        }
    }
}
