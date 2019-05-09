// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.adview;

import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAd;
import android.util.Log;
import com.applovin.impl.adview.AdViewControllerImpl;
import android.util.DisplayMetrics;
import android.view.View;
import android.graphics.Color;
import android.widget.TextView;
import android.util.TypedValue;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinAdSize;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.RelativeLayout;

public class AppLovinAdView extends RelativeLayout
{
    public static final String NAMESPACE = "http://schemas.applovin.com/android/1.0";
    private AdViewController a;
    
    public AppLovinAdView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public AppLovinAdView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.a(null, null, null, context, set);
    }
    
    public AppLovinAdView(final AppLovinAdSize appLovinAdSize, final Context context) {
        this(appLovinAdSize, null, context);
    }
    
    public AppLovinAdView(final AppLovinAdSize appLovinAdSize, final String s, final Context context) {
        super(context);
        this.a(appLovinAdSize, s, null, context, null);
    }
    
    public AppLovinAdView(final AppLovinSdk appLovinSdk, final AppLovinAdSize appLovinAdSize, final Context context) {
        super(context);
        this.a(appLovinAdSize, null, appLovinSdk, context, null);
    }
    
    private void a(final AttributeSet set, final Context context) {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        final int widthPixels = displayMetrics.widthPixels;
        final int n = (int)TypedValue.applyDimension(1, 50.0f, displayMetrics);
        final TextView textView = new TextView(context);
        textView.setBackgroundColor(Color.rgb(220, 220, 220));
        textView.setTextColor(-16777216);
        textView.setText((CharSequence)"AppLovin Ad");
        textView.setGravity(17);
        this.addView((View)textView, widthPixels, n);
    }
    
    private void a(final AppLovinAdSize appLovinAdSize, final String s, final AppLovinSdk appLovinSdk, final Context context, final AttributeSet set) {
        if (!this.isInEditMode()) {
            final AdViewControllerImpl a = new AdViewControllerImpl();
            a.initializeAdView(this, context, appLovinAdSize, s, appLovinSdk, set);
            this.a = a;
            return;
        }
        this.a(set, context);
    }
    
    public void destroy() {
        if (this.a != null) {
            this.a.destroy();
        }
    }
    
    public AdViewController getAdViewController() {
        return this.a;
    }
    
    public AppLovinAdSize getSize() {
        if (this.a != null) {
            return this.a.getSize();
        }
        return null;
    }
    
    public String getZoneId() {
        if (this.a != null) {
            return this.a.getZoneId();
        }
        return null;
    }
    
    @Deprecated
    public boolean isAdReadyToDisplay() {
        return this.a != null && this.a.isAdReadyToDisplay();
    }
    
    public void loadNextAd() {
        if (this.a != null) {
            this.a.loadNextAd();
            return;
        }
        Log.i("AppLovinSdk", "Unable to load next ad: AppLovinAdView is not initialized.");
    }
    
    protected void onDetachedFromWindow() {
        if (this.a != null) {
            this.a.onDetachedFromWindow();
        }
        super.onDetachedFromWindow();
    }
    
    protected void onVisibilityChanged(final View view, final int n) {
        super.onVisibilityChanged(view, n);
        if (this.a != null) {
            this.a.onVisibilityChanged(n);
        }
    }
    
    public void pause() {
        if (this.a != null) {
            this.a.pause();
        }
    }
    
    public void renderAd(final AppLovinAd appLovinAd) {
        this.renderAd(appLovinAd, null);
    }
    
    @Deprecated
    public void renderAd(final AppLovinAd appLovinAd, final String s) {
        if (this.a != null) {
            this.a.renderAd(appLovinAd, s);
        }
    }
    
    public void resume() {
        if (this.a != null) {
            this.a.resume();
        }
    }
    
    public void setAdClickListener(final AppLovinAdClickListener adClickListener) {
        if (this.a != null) {
            this.a.setAdClickListener(adClickListener);
        }
    }
    
    public void setAdDisplayListener(final AppLovinAdDisplayListener adDisplayListener) {
        if (this.a != null) {
            this.a.setAdDisplayListener(adDisplayListener);
        }
    }
    
    public void setAdLoadListener(final AppLovinAdLoadListener adLoadListener) {
        if (this.a != null) {
            this.a.setAdLoadListener(adLoadListener);
        }
    }
    
    public void setAdVideoPlaybackListener(final AppLovinAdVideoPlaybackListener adVideoPlaybackListener) {
        if (this.a != null) {
            this.a.setAdVideoPlaybackListener(adVideoPlaybackListener);
        }
    }
    
    public void setAdViewEventListener(final AppLovinAdViewEventListener adViewEventListener) {
        if (this.a != null) {
            this.a.setAdViewEventListener(adViewEventListener);
        }
    }
    
    public void setAutoDestroy(final boolean autoDestroy) {
        if (this.a != null) {
            this.a.setAutoDestroy(autoDestroy);
        }
    }
}
