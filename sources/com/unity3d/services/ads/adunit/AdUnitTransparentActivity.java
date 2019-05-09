package com.unity3d.services.ads.adunit;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import com.unity3d.services.core.misc.ViewUtilities;

public class AdUnitTransparentActivity extends AdUnitActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtilities.setBackground(this._layout, new ColorDrawable(0));
    }

    protected void createLayout() {
        super.createLayout();
        ViewUtilities.setBackground(this._layout, new ColorDrawable(0));
    }
}
