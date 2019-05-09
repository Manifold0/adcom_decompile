// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.controller;

import com.ironsource.sdk.handlers.BackButtonHandler;
import com.ironsource.environment.DeviceStatus;
import android.graphics.Rect;
import android.os.Build$VERSION;
import android.view.View;
import android.app.Activity;
import android.view.ViewGroup;
import android.content.Context;
import com.ironsource.sdk.listeners.OnWebViewChangeListener;
import android.widget.FrameLayout;

public class ControllerView extends FrameLayout implements OnWebViewChangeListener
{
    private Context mContext;
    private IronSourceWebView mWebViewController;
    
    public ControllerView(final Context mContext) {
        super(mContext);
        this.mContext = mContext;
        this.setClickable(true);
    }
    
    private void addViewToWindow() {
        ((Activity)this.mContext).runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                final ViewGroup access$000 = ControllerView.this.getWindowDecorViewGroup();
                if (access$000 != null) {
                    access$000.addView((View)ControllerView.this);
                }
            }
        });
    }
    
    private int getNavigationBarPadding() {
        final Activity activity = (Activity)this.mContext;
        try {
            if (Build$VERSION.SDK_INT > 9) {
                final Rect rect = new Rect();
                activity.getWindow().getDecorView().getDrawingRect(rect);
                final Rect rect2 = new Rect();
                activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect2);
                if (DeviceStatus.getDeviceOrientation((Context)activity) == 1) {
                    if (rect.bottom - rect2.bottom > 0) {
                        return rect.bottom - rect2.bottom;
                    }
                }
                else if (rect.right - rect2.right > 0) {
                    return rect.right - rect2.right;
                }
            }
        }
        catch (Exception ex) {}
        return 0;
    }
    
    private int getStatusBarHeight() {
        int dimensionPixelSize = 0;
        try {
            if (this.mContext != null) {
                final int identifier = this.mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
                dimensionPixelSize = dimensionPixelSize;
                if (identifier > 0) {
                    dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(identifier);
                }
            }
            return dimensionPixelSize;
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    private int getStatusBarPadding() {
        int n;
        if ((((Activity)this.mContext).getWindow().getAttributes().flags & 0x400) != 0x0) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            return 0;
        }
        int statusBarHeight = this.getStatusBarHeight();
        if (statusBarHeight <= 0) {
            statusBarHeight = 0;
        }
        return statusBarHeight;
    }
    
    private ViewGroup getWindowDecorViewGroup() {
        final Activity activity = (Activity)this.mContext;
        if (activity != null) {
            return (ViewGroup)activity.getWindow().getDecorView();
        }
        return null;
    }
    
    private void removeViewFromWindow() {
        ((Activity)this.mContext).runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                final ViewGroup access$000 = ControllerView.this.getWindowDecorViewGroup();
                if (access$000 != null) {
                    access$000.removeView((View)ControllerView.this);
                }
            }
        });
    }
    
    private void setPaddingByOrientation(final int n, final int n2) {
        try {
            if (this.mContext != null) {
                final int deviceOrientation = DeviceStatus.getDeviceOrientation(this.mContext);
                if (deviceOrientation == 1) {
                    this.setPadding(0, n, 0, n2);
                    return;
                }
                if (deviceOrientation == 2) {
                    this.setPadding(0, n, n2, 0);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mWebViewController.resume();
        this.mWebViewController.viewableChange(true, "main");
    }
    
    public boolean onBackButtonPressed() {
        return BackButtonHandler.getInstance().handleBackButton((Activity)this.mContext);
    }
    
    public void onCloseRequested() {
        this.removeViewFromWindow();
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mWebViewController.pause();
        this.mWebViewController.viewableChange(false, "main");
        if (this.mWebViewController != null) {
            this.mWebViewController.setState(IronSourceWebView.State.Gone);
            this.mWebViewController.removeVideoEventsListener();
        }
        this.removeAllViews();
    }
    
    public void onOrientationChanged(final String s, final int n) {
    }
    
    public void showInterstitial(final IronSourceWebView mWebViewController) {
        (this.mWebViewController = mWebViewController).setOnWebViewControllerChangeListener(this);
        this.mWebViewController.requestFocus();
        this.mContext = this.mWebViewController.getCurrentActivityContext();
        this.setPaddingByOrientation(this.getStatusBarPadding(), this.getNavigationBarPadding());
        this.addViewToWindow();
    }
}
