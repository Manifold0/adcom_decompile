// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.widget;

import com.facebook.share.Sharer$Result;
import com.facebook.share.model.ShareContent;
import com.facebook.internal.FacebookDialogBase;
import com.facebook.share.R$style;
import com.facebook.internal.CallbackManagerImpl$RequestCodeOffset;
import android.util.AttributeSet;
import android.content.Context;

public final class ShareButton extends ShareButtonBase
{
    public ShareButton(final Context context) {
        super(context, null, 0, "fb_share_button_create", "fb_share_button_did_tap");
    }
    
    public ShareButton(final Context context, final AttributeSet set) {
        super(context, set, 0, "fb_share_button_create", "fb_share_button_did_tap");
    }
    
    public ShareButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n, "fb_share_button_create", "fb_share_button_did_tap");
    }
    
    protected int getDefaultRequestCode() {
        return CallbackManagerImpl$RequestCodeOffset.Share.toRequestCode();
    }
    
    protected int getDefaultStyleResource() {
        return R$style.com_facebook_button_share;
    }
    
    @Override
    protected FacebookDialogBase<ShareContent, Sharer$Result> getDialog() {
        if (this.getFragment() != null) {
            return (FacebookDialogBase<ShareContent, Sharer$Result>)new ShareDialog(this.getFragment(), this.getRequestCode());
        }
        if (this.getNativeFragment() != null) {
            return (FacebookDialogBase<ShareContent, Sharer$Result>)new ShareDialog(this.getNativeFragment(), this.getRequestCode());
        }
        return (FacebookDialogBase<ShareContent, Sharer$Result>)new ShareDialog(this.getActivity(), this.getRequestCode());
    }
}
