package com.applovin.adview;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.applovin.impl.adview.AdViewControllerImpl;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;

public class AppLovinAdView extends RelativeLayout {
    public static final String NAMESPACE = "http://schemas.applovin.com/android/1.0";
    /* renamed from: a */
    private AdViewController f1518a;

    public AppLovinAdView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AppLovinAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m1670a(null, null, null, context, attributeSet);
    }

    public AppLovinAdView(AppLovinAdSize appLovinAdSize, Context context) {
        this(appLovinAdSize, null, context);
    }

    public AppLovinAdView(AppLovinAdSize appLovinAdSize, String str, Context context) {
        super(context);
        m1670a(appLovinAdSize, str, null, context, null);
    }

    public AppLovinAdView(AppLovinSdk appLovinSdk, AppLovinAdSize appLovinAdSize, Context context) {
        super(context);
        m1670a(appLovinAdSize, null, appLovinSdk, context, null);
    }

    /* renamed from: a */
    private void m1669a(AttributeSet attributeSet, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int applyDimension = (int) TypedValue.applyDimension(1, 50.0f, displayMetrics);
        View textView = new TextView(context);
        textView.setBackgroundColor(Color.rgb(220, 220, 220));
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        textView.setText("AppLovin Ad");
        textView.setGravity(17);
        addView(textView, i, applyDimension);
    }

    /* renamed from: a */
    private void m1670a(AppLovinAdSize appLovinAdSize, String str, AppLovinSdk appLovinSdk, Context context, AttributeSet attributeSet) {
        if (isInEditMode()) {
            m1669a(attributeSet, context);
            return;
        }
        AdViewController adViewControllerImpl = new AdViewControllerImpl();
        adViewControllerImpl.initializeAdView(this, context, appLovinAdSize, str, appLovinSdk, attributeSet);
        this.f1518a = adViewControllerImpl;
    }

    public void destroy() {
        if (this.f1518a != null) {
            this.f1518a.destroy();
        }
    }

    public AdViewController getAdViewController() {
        return this.f1518a;
    }

    public AppLovinAdSize getSize() {
        return this.f1518a != null ? this.f1518a.getSize() : null;
    }

    public String getZoneId() {
        return this.f1518a != null ? this.f1518a.getZoneId() : null;
    }

    @Deprecated
    public boolean isAdReadyToDisplay() {
        return this.f1518a != null && this.f1518a.isAdReadyToDisplay();
    }

    public void loadNextAd() {
        if (this.f1518a != null) {
            this.f1518a.loadNextAd();
        } else {
            Log.i(AppLovinLogger.SDK_TAG, "Unable to load next ad: AppLovinAdView is not initialized.");
        }
    }

    protected void onDetachedFromWindow() {
        if (this.f1518a != null) {
            this.f1518a.onDetachedFromWindow();
        }
        super.onDetachedFromWindow();
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (this.f1518a != null) {
            this.f1518a.onVisibilityChanged(i);
        }
    }

    public void pause() {
        if (this.f1518a != null) {
            this.f1518a.pause();
        }
    }

    public void renderAd(AppLovinAd appLovinAd) {
        renderAd(appLovinAd, null);
    }

    @Deprecated
    public void renderAd(AppLovinAd appLovinAd, String str) {
        if (this.f1518a != null) {
            this.f1518a.renderAd(appLovinAd, str);
        }
    }

    public void resume() {
        if (this.f1518a != null) {
            this.f1518a.resume();
        }
    }

    public void setAdClickListener(AppLovinAdClickListener appLovinAdClickListener) {
        if (this.f1518a != null) {
            this.f1518a.setAdClickListener(appLovinAdClickListener);
        }
    }

    public void setAdDisplayListener(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        if (this.f1518a != null) {
            this.f1518a.setAdDisplayListener(appLovinAdDisplayListener);
        }
    }

    public void setAdLoadListener(AppLovinAdLoadListener appLovinAdLoadListener) {
        if (this.f1518a != null) {
            this.f1518a.setAdLoadListener(appLovinAdLoadListener);
        }
    }

    public void setAdVideoPlaybackListener(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
        if (this.f1518a != null) {
            this.f1518a.setAdVideoPlaybackListener(appLovinAdVideoPlaybackListener);
        }
    }

    public void setAdViewEventListener(AppLovinAdViewEventListener appLovinAdViewEventListener) {
        if (this.f1518a != null) {
            this.f1518a.setAdViewEventListener(appLovinAdViewEventListener);
        }
    }

    public void setAutoDestroy(boolean z) {
        if (this.f1518a != null) {
            this.f1518a.setAutoDestroy(z);
        }
    }
}
