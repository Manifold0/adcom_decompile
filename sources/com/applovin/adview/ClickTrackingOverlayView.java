package com.applovin.adview;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.applovin.impl.sdk.ee;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;

public class ClickTrackingOverlayView extends RelativeLayout {
    public ClickTrackingOverlayView(Context context, AppLovinSdk appLovinSdk) {
        super(context, null, new ee(appLovinSdk).m2676C());
        m1772a(context, appLovinSdk);
    }

    /* renamed from: a */
    private void m1772a(Context context, AppLovinSdk appLovinSdk) {
        LayoutParams layoutParams;
        ee eeVar = new ee(appLovinSdk);
        View progressBar = new ProgressBar(context);
        progressBar.setIndeterminate(true);
        int B = eeVar.m2675B();
        if (B == -2 || B == -1) {
            layoutParams = new RelativeLayout.LayoutParams(B, B);
        } else {
            B = AppLovinSdkUtils.dpToPx(context, B);
            layoutParams = new RelativeLayout.LayoutParams(B, B);
        }
        layoutParams.addRule(13);
        progressBar.setLayoutParams(layoutParams);
        setBackgroundColor(Color.parseColor(eeVar.m2674A()));
        addView(progressBar);
    }
}
