// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.adview;

import android.view.View;
import android.graphics.Color;
import android.view.ViewGroup$LayoutParams;
import com.applovin.sdk.AppLovinSdkUtils;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.ProgressBar;
import android.util.AttributeSet;
import com.applovin.impl.sdk.ee;
import com.applovin.sdk.AppLovinSdk;
import android.content.Context;
import android.widget.RelativeLayout;

public class ClickTrackingOverlayView extends RelativeLayout
{
    public ClickTrackingOverlayView(final Context context, final AppLovinSdk appLovinSdk) {
        super(context, (AttributeSet)null, new ee(appLovinSdk).C());
        this.a(context, appLovinSdk);
    }
    
    private void a(final Context context, final AppLovinSdk appLovinSdk) {
        final ee ee = new ee(appLovinSdk);
        final ProgressBar progressBar = new ProgressBar(context);
        progressBar.setIndeterminate(true);
        final int b = ee.B();
        RelativeLayout$LayoutParams layoutParams;
        if (b == -2 || b == -1) {
            layoutParams = new RelativeLayout$LayoutParams(b, b);
        }
        else {
            final int dpToPx = AppLovinSdkUtils.dpToPx(context, b);
            layoutParams = new RelativeLayout$LayoutParams(dpToPx, dpToPx);
        }
        layoutParams.addRule(13);
        progressBar.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.setBackgroundColor(Color.parseColor(ee.A()));
        this.addView((View)progressBar);
    }
}
