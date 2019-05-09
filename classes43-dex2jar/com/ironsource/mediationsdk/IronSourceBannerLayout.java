// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import android.view.ViewGroup$LayoutParams;
import android.os.Handler;
import android.os.Looper;
import android.widget.FrameLayout$LayoutParams;
import android.content.Context;
import android.view.View;
import com.ironsource.mediationsdk.sdk.BannerListener;
import android.app.Activity;
import android.widget.FrameLayout;

public class IronSourceBannerLayout extends FrameLayout
{
    private boolean isDestroyed;
    private Activity mActivity;
    private BannerListener mBannerListener;
    private View mBannerView;
    private boolean mIsBannerDisplayed;
    private String mPlacementName;
    private ISBannerSize mSize;
    
    public IronSourceBannerLayout(final Activity mActivity, final ISBannerSize isBannerSize) {
        super((Context)mActivity);
        this.isDestroyed = false;
        this.mIsBannerDisplayed = false;
        this.mActivity = mActivity;
        ISBannerSize banner = isBannerSize;
        if (isBannerSize == null) {
            banner = ISBannerSize.BANNER;
        }
        this.mSize = banner;
    }
    
    void addViewWithFrameLayoutParams(final View view, final FrameLayout$LayoutParams frameLayout$LayoutParams) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                IronSourceBannerLayout.this.removeAllViews();
                IronSourceBannerLayout.this.mBannerView = view;
                IronSourceBannerLayout.this.addView(view, 0, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
            }
        });
    }
    
    protected void destroyBanner() {
        this.isDestroyed = true;
        this.mBannerListener = null;
        this.mActivity = null;
        this.mSize = null;
        this.mPlacementName = null;
        this.mBannerView = null;
    }
    
    public Activity getActivity() {
        return this.mActivity;
    }
    
    public BannerListener getBannerListener() {
        return this.mBannerListener;
    }
    
    public View getBannerView() {
        return this.mBannerView;
    }
    
    public String getPlacementName() {
        return this.mPlacementName;
    }
    
    public ISBannerSize getSize() {
        return this.mSize;
    }
    
    public boolean isDestroyed() {
        return this.isDestroyed;
    }
    
    public void removeBannerListener() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, "removeBannerListener()", 1);
        this.mBannerListener = null;
    }
    
    void sendBannerAdClicked() {
        if (this.mBannerListener != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onBannerAdClicked()", 1);
            this.mBannerListener.onBannerAdClicked();
        }
    }
    
    void sendBannerAdLeftApplication() {
        if (this.mBannerListener != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onBannerAdLeftApplication()", 1);
            this.mBannerListener.onBannerAdLeftApplication();
        }
    }
    
    void sendBannerAdLoadFailed(final IronSourceError ironSourceError) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (IronSourceBannerLayout.this.mIsBannerDisplayed) {
                    IronSourceBannerLayout.this.mBannerListener.onBannerAdLoadFailed(ironSourceError);
                }
                else {
                    IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "onBannerAdLoadFailed() | internal | " + ironSourceError, 0);
                    while (true) {
                        try {
                            if (IronSourceBannerLayout.this.mBannerView != null) {
                                IronSourceBannerLayout.this.removeView(IronSourceBannerLayout.this.mBannerView);
                                IronSourceBannerLayout.this.mBannerView = null;
                            }
                            if (IronSourceBannerLayout.this.mBannerListener != null) {
                                IronSourceBannerLayout.this.mBannerListener.onBannerAdLoadFailed(ironSourceError);
                            }
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            continue;
                        }
                        break;
                    }
                }
            }
        });
    }
    
    void sendBannerAdLoaded(final BannerSmash bannerSmash) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "onBannerAdLoaded() | internal | adapter: " + bannerSmash.getName(), 0);
        if (this.mBannerListener != null && !this.mIsBannerDisplayed) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onBannerAdLoaded()", 1);
            this.mBannerListener.onBannerAdLoaded();
        }
        this.mIsBannerDisplayed = true;
    }
    
    void sendBannerAdScreenDismissed() {
        if (this.mBannerListener != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onBannerAdScreenDismissed()", 1);
            this.mBannerListener.onBannerAdScreenDismissed();
        }
    }
    
    void sendBannerAdScreenPresented() {
        if (this.mBannerListener != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onBannerAdScreenPresented()", 1);
            this.mBannerListener.onBannerAdScreenPresented();
        }
    }
    
    public void setBannerListener(final BannerListener mBannerListener) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, "setBannerListener()", 1);
        this.mBannerListener = mBannerListener;
    }
    
    public void setPlacementName(final String mPlacementName) {
        this.mPlacementName = mPlacementName;
    }
}
