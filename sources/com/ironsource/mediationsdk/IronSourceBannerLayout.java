package com.ironsource.mediationsdk;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.BannerListener;

public class IronSourceBannerLayout extends FrameLayout {
    private boolean isDestroyed = false;
    private Activity mActivity;
    private BannerListener mBannerListener;
    private View mBannerView;
    private boolean mIsBannerDisplayed = false;
    private String mPlacementName;
    private ISBannerSize mSize;

    public IronSourceBannerLayout(Activity activity, ISBannerSize size) {
        super(activity);
        this.mActivity = activity;
        if (size == null) {
            size = ISBannerSize.BANNER;
        }
        this.mSize = size;
    }

    protected void destroyBanner() {
        this.isDestroyed = true;
        this.mBannerListener = null;
        this.mActivity = null;
        this.mSize = null;
        this.mPlacementName = null;
        this.mBannerView = null;
    }

    public boolean isDestroyed() {
        return this.isDestroyed;
    }

    public View getBannerView() {
        return this.mBannerView;
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    public ISBannerSize getSize() {
        return this.mSize;
    }

    public String getPlacementName() {
        return this.mPlacementName;
    }

    public void setPlacementName(String placementName) {
        this.mPlacementName = placementName;
    }

    public void setBannerListener(BannerListener listener) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.API, "setBannerListener()", 1);
        this.mBannerListener = listener;
    }

    public void removeBannerListener() {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.API, "removeBannerListener()", 1);
        this.mBannerListener = null;
    }

    public BannerListener getBannerListener() {
        return this.mBannerListener;
    }

    void sendBannerAdLoaded(BannerSmash smash) {
        IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onBannerAdLoaded() | internal | adapter: " + smash.getName(), 0);
        if (!(this.mBannerListener == null || this.mIsBannerDisplayed)) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onBannerAdLoaded()", 1);
            this.mBannerListener.onBannerAdLoaded();
        }
        this.mIsBannerDisplayed = true;
    }

    void sendBannerAdLoadFailed(final IronSourceError error) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                if (IronSourceBannerLayout.this.mIsBannerDisplayed) {
                    IronSourceBannerLayout.this.mBannerListener.onBannerAdLoadFailed(error);
                    return;
                }
                IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "onBannerAdLoadFailed() | internal | " + error, 0);
                try {
                    if (IronSourceBannerLayout.this.mBannerView != null) {
                        IronSourceBannerLayout.this.removeView(IronSourceBannerLayout.this.mBannerView);
                        IronSourceBannerLayout.this.mBannerView = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (IronSourceBannerLayout.this.mBannerListener != null) {
                    IronSourceBannerLayout.this.mBannerListener.onBannerAdLoadFailed(error);
                }
            }
        });
    }

    void sendBannerAdClicked() {
        if (this.mBannerListener != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onBannerAdClicked()", 1);
            this.mBannerListener.onBannerAdClicked();
        }
    }

    void sendBannerAdScreenPresented() {
        if (this.mBannerListener != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onBannerAdScreenPresented()", 1);
            this.mBannerListener.onBannerAdScreenPresented();
        }
    }

    void sendBannerAdScreenDismissed() {
        if (this.mBannerListener != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onBannerAdScreenDismissed()", 1);
            this.mBannerListener.onBannerAdScreenDismissed();
        }
    }

    void sendBannerAdLeftApplication() {
        if (this.mBannerListener != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.CALLBACK, "onBannerAdLeftApplication()", 1);
            this.mBannerListener.onBannerAdLeftApplication();
        }
    }

    void addViewWithFrameLayoutParams(final View adView, final LayoutParams layoutParams) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                IronSourceBannerLayout.this.removeAllViews();
                IronSourceBannerLayout.this.mBannerView = adView;
                IronSourceBannerLayout.this.addView(adView, 0, layoutParams);
            }
        });
    }
}
