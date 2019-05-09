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

public final class SendButton extends ShareButtonBase
{
    public SendButton(final Context context) {
        super(context, null, 0, "fb_send_button_create", "fb_send_button_did_tap");
    }
    
    public SendButton(final Context context, final AttributeSet set) {
        super(context, set, 0, "fb_send_button_create", "fb_send_button_did_tap");
    }
    
    public SendButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n, "fb_send_button_create", "fb_send_button_did_tap");
    }
    
    protected int getDefaultRequestCode() {
        return CallbackManagerImpl$RequestCodeOffset.Message.toRequestCode();
    }
    
    protected int getDefaultStyleResource() {
        return R$style.com_facebook_button_send;
    }
    
    @Override
    protected FacebookDialogBase<ShareContent, Sharer$Result> getDialog() {
        if (this.getFragment() != null) {
            return new MessageDialog(this.getFragment(), this.getRequestCode());
        }
        if (this.getNativeFragment() != null) {
            return new MessageDialog(this.getNativeFragment(), this.getRequestCode());
        }
        return new MessageDialog(this.getActivity(), this.getRequestCode());
    }
}
