// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.widget;

import com.facebook.FacebookSdk;
import com.facebook.share.internal.ShareInternalUtility;
import com.facebook.FacebookCallback;
import com.facebook.CallbackManager;
import android.view.View$OnClickListener;
import com.facebook.share.Sharer$Result;
import com.facebook.internal.FacebookDialogBase;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import com.facebook.share.model.ShareContent;
import com.facebook.FacebookButtonBase;

public abstract class ShareButtonBase extends FacebookButtonBase
{
    private boolean enabledExplicitlySet;
    private int requestCode;
    private ShareContent shareContent;
    
    protected ShareButtonBase(final Context context, final AttributeSet set, int defaultRequestCode, final String s, final String s2) {
        super(context, set, defaultRequestCode, 0, s, s2);
        this.requestCode = 0;
        this.enabledExplicitlySet = false;
        if (this.isInEditMode()) {
            defaultRequestCode = 0;
        }
        else {
            defaultRequestCode = this.getDefaultRequestCode();
        }
        this.requestCode = defaultRequestCode;
        this.internalSetEnabled(false);
    }
    
    static /* synthetic */ void access$000(final ShareButtonBase shareButtonBase, final View view) {
        shareButtonBase.callExternalOnClickListener(view);
    }
    
    private void internalSetEnabled(final boolean enabled) {
        this.setEnabled(enabled);
        this.enabledExplicitlySet = false;
    }
    
    protected boolean canShare() {
        return this.getDialog().canShow((Object)this.getShareContent());
    }
    
    protected void configureButton(final Context context, final AttributeSet set, final int n, final int n2) {
        super.configureButton(context, set, n, n2);
        this.setInternalOnClickListener(this.getShareOnClickListener());
    }
    
    protected abstract FacebookDialogBase<ShareContent, Sharer$Result> getDialog();
    
    public int getRequestCode() {
        return this.requestCode;
    }
    
    public ShareContent getShareContent() {
        return this.shareContent;
    }
    
    protected View$OnClickListener getShareOnClickListener() {
        return (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                ShareButtonBase.access$000(ShareButtonBase.this, view);
                ShareButtonBase.this.getDialog().show((Object)ShareButtonBase.this.getShareContent());
            }
        };
    }
    
    public void registerCallback(final CallbackManager callbackManager, final FacebookCallback<Sharer$Result> facebookCallback) {
        ShareInternalUtility.registerSharerCallback(this.getRequestCode(), callbackManager, (FacebookCallback)facebookCallback);
    }
    
    public void registerCallback(final CallbackManager callbackManager, final FacebookCallback<Sharer$Result> facebookCallback, final int requestCode) {
        this.setRequestCode(requestCode);
        this.registerCallback(callbackManager, facebookCallback);
    }
    
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        this.enabledExplicitlySet = true;
    }
    
    protected void setRequestCode(final int requestCode) {
        if (FacebookSdk.isFacebookRequestCode(requestCode)) {
            throw new IllegalArgumentException("Request code " + requestCode + " cannot be within the range reserved by the Facebook SDK.");
        }
        this.requestCode = requestCode;
    }
    
    public void setShareContent(final ShareContent shareContent) {
        this.shareContent = shareContent;
        if (!this.enabledExplicitlySet) {
            this.internalSetEnabled(this.canShare());
        }
    }
}
