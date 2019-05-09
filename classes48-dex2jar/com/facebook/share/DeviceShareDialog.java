// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share;

import android.os.Parcelable;
import com.facebook.FacebookActivity;
import com.facebook.FacebookSdk;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import android.content.Intent;
import com.facebook.internal.CallbackManagerImpl$Callback;
import com.facebook.FacebookCallback;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.FacebookDialogBase$ModeHandler;
import java.util.List;
import com.facebook.internal.AppCall;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.internal.FragmentWrapper;
import android.app.Fragment;
import android.app.Activity;
import com.facebook.internal.CallbackManagerImpl$RequestCodeOffset;
import com.facebook.share.model.ShareContent;
import com.facebook.internal.FacebookDialogBase;

public class DeviceShareDialog extends FacebookDialogBase<ShareContent, Result>
{
    private static final int DEFAULT_REQUEST_CODE;
    
    static {
        DEFAULT_REQUEST_CODE = CallbackManagerImpl$RequestCodeOffset.DeviceShare.toRequestCode();
    }
    
    public DeviceShareDialog(final Activity activity) {
        super(activity, DeviceShareDialog.DEFAULT_REQUEST_CODE);
    }
    
    public DeviceShareDialog(final Fragment fragment) {
        super(new FragmentWrapper(fragment), DeviceShareDialog.DEFAULT_REQUEST_CODE);
    }
    
    public DeviceShareDialog(final android.support.v4.app.Fragment fragment) {
        super(new FragmentWrapper(fragment), DeviceShareDialog.DEFAULT_REQUEST_CODE);
    }
    
    protected boolean canShowImpl(final ShareContent shareContent, final Object o) {
        return shareContent instanceof ShareLinkContent || shareContent instanceof ShareOpenGraphContent;
    }
    
    protected AppCall createBaseAppCall() {
        return null;
    }
    
    protected List<FacebookDialogBase$ModeHandler> getOrderedModeHandlers() {
        return null;
    }
    
    protected void registerCallbackImpl(final CallbackManagerImpl callbackManagerImpl, final FacebookCallback<Result> facebookCallback) {
        callbackManagerImpl.registerCallback(this.getRequestCode(), (CallbackManagerImpl$Callback)new CallbackManagerImpl$Callback() {
            public boolean onActivityResult(final int n, final Intent intent) {
                if (intent.hasExtra("error")) {
                    facebookCallback.onError(((FacebookRequestError)intent.getParcelableExtra("error")).getException());
                    return true;
                }
                facebookCallback.onSuccess((Object)new Result());
                return true;
            }
        });
    }
    
    protected void showImpl(final ShareContent shareContent, final Object o) {
        if (shareContent == null) {
            throw new FacebookException("Must provide non-null content to share");
        }
        if (!(shareContent instanceof ShareLinkContent) && !(shareContent instanceof ShareOpenGraphContent)) {
            throw new FacebookException(this.getClass().getSimpleName() + " only supports ShareLinkContent or ShareOpenGraphContent");
        }
        final Intent intent = new Intent();
        intent.setClass(FacebookSdk.getApplicationContext(), (Class)FacebookActivity.class);
        intent.setAction("DeviceShareDialogFragment");
        intent.putExtra("content", (Parcelable)shareContent);
        this.startActivityForResult(intent, this.getRequestCode());
    }
    
    public static class Result
    {
    }
}
