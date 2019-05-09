// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.adunit;

import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.unity3d.services.core.misc.ViewUtilities;
import android.graphics.drawable.ColorDrawable;

public class AdUnitTransparentActivity extends AdUnitActivity
{
    @Override
    protected void createLayout() {
        super.createLayout();
        ViewUtilities.setBackground((View)this._layout, (Drawable)new ColorDrawable(0));
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        ViewUtilities.setBackground((View)super._layout, (Drawable)new ColorDrawable(0));
    }
}
