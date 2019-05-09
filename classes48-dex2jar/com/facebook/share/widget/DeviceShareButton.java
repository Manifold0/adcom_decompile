// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.widget;

import com.facebook.FacebookCallback;
import com.facebook.CallbackManager;
import android.view.View$OnClickListener;
import com.facebook.share.R$style;
import com.facebook.internal.CallbackManagerImpl$RequestCodeOffset;
import com.facebook.FacebookSdk;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import com.facebook.share.model.ShareContent;
import com.facebook.share.DeviceShareDialog;
import com.facebook.FacebookButtonBase;

public final class DeviceShareButton extends FacebookButtonBase
{
    private DeviceShareDialog dialog;
    private boolean enabledExplicitlySet;
    private int requestCode;
    private ShareContent shareContent;
    
    public DeviceShareButton(final Context context) {
        this(context, null, 0);
    }
    
    public DeviceShareButton(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    private DeviceShareButton(final Context context, final AttributeSet set, int defaultRequestCode) {
        super(context, set, defaultRequestCode, 0, "fb_device_share_button_create", "fb_device_share_button_did_tap");
        this.requestCode = 0;
        this.enabledExplicitlySet = false;
        this.dialog = null;
        if (this.isInEditMode()) {
            defaultRequestCode = 0;
        }
        else {
            defaultRequestCode = this.getDefaultRequestCode();
        }
        this.requestCode = defaultRequestCode;
        this.internalSetEnabled(false);
    }
    
    static /* synthetic */ void access$000(final DeviceShareButton deviceShareButton, final View view) {
        deviceShareButton.callExternalOnClickListener(view);
    }
    
    private boolean canShare() {
        return new DeviceShareDialog(this.getActivity()).canShow((Object)this.getShareContent());
    }
    
    private DeviceShareDialog getDialog() {
        if (this.dialog != null) {
            return this.dialog;
        }
        if (this.getFragment() != null) {
            this.dialog = new DeviceShareDialog(this.getFragment());
        }
        else if (this.getNativeFragment() != null) {
            this.dialog = new DeviceShareDialog(this.getNativeFragment());
        }
        else {
            this.dialog = new DeviceShareDialog(this.getActivity());
        }
        return this.dialog;
    }
    
    private void internalSetEnabled(final boolean enabled) {
        this.setEnabled(enabled);
        this.enabledExplicitlySet = false;
    }
    
    private void setRequestCode(final int requestCode) {
        if (FacebookSdk.isFacebookRequestCode(requestCode)) {
            throw new IllegalArgumentException("Request code " + requestCode + " cannot be within the range reserved by the Facebook SDK.");
        }
        this.requestCode = requestCode;
    }
    
    protected void configureButton(final Context context, final AttributeSet set, final int n, final int n2) {
        super.configureButton(context, set, n, n2);
        this.setInternalOnClickListener(this.getShareOnClickListener());
    }
    
    protected int getDefaultRequestCode() {
        return CallbackManagerImpl$RequestCodeOffset.Share.toRequestCode();
    }
    
    protected int getDefaultStyleResource() {
        return R$style.com_facebook_button_share;
    }
    
    public int getRequestCode() {
        return this.requestCode;
    }
    
    public ShareContent getShareContent() {
        return this.shareContent;
    }
    
    protected View$OnClickListener getShareOnClickListener() {
        return (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                DeviceShareButton.access$000(DeviceShareButton.this, view);
                DeviceShareButton.this.getDialog().show((Object)DeviceShareButton.this.getShareContent());
            }
        };
    }
    
    public void registerCallback(final CallbackManager callbackManager, final FacebookCallback<DeviceShareDialog.Result> facebookCallback) {
        this.getDialog().registerCallback(callbackManager, (FacebookCallback)facebookCallback);
    }
    
    public void registerCallback(final CallbackManager callbackManager, final FacebookCallback<DeviceShareDialog.Result> facebookCallback, final int requestCode) {
        this.setRequestCode(requestCode);
        this.getDialog().registerCallback(callbackManager, (FacebookCallback)facebookCallback, requestCode);
    }
    
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        this.enabledExplicitlySet = true;
    }
    
    public void setShareContent(final ShareContent shareContent) {
        this.shareContent = shareContent;
        if (!this.enabledExplicitlySet) {
            this.internalSetEnabled(this.canShare());
        }
    }
}
