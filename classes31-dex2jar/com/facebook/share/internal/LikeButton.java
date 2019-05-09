// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import com.facebook.common.R$style;
import com.facebook.common.R$string;
import com.facebook.common.R$drawable;
import android.util.AttributeSet;
import android.content.Context;
import com.facebook.FacebookButtonBase;

@Deprecated
public class LikeButton extends FacebookButtonBase
{
    @Deprecated
    public LikeButton(final Context context, final boolean selected) {
        super(context, null, 0, 0, "fb_like_button_create", "fb_like_button_did_tap");
        this.setSelected(selected);
    }
    
    private void updateForLikeStatus() {
        if (this.isSelected()) {
            this.setCompoundDrawablesWithIntrinsicBounds(R$drawable.com_facebook_button_like_icon_selected, 0, 0, 0);
            this.setText((CharSequence)this.getResources().getString(R$string.com_facebook_like_button_liked));
            return;
        }
        this.setCompoundDrawablesWithIntrinsicBounds(R$drawable.com_facebook_button_icon, 0, 0, 0);
        this.setText((CharSequence)this.getResources().getString(R$string.com_facebook_like_button_not_liked));
    }
    
    @Override
    protected void configureButton(final Context context, final AttributeSet set, final int n, final int n2) {
        super.configureButton(context, set, n, n2);
        this.updateForLikeStatus();
    }
    
    @Override
    protected int getDefaultRequestCode() {
        return 0;
    }
    
    @Override
    protected int getDefaultStyleResource() {
        return R$style.com_facebook_button_like;
    }
    
    @Deprecated
    public void setSelected(final boolean selected) {
        super.setSelected(selected);
        this.updateForLikeStatus();
    }
}
