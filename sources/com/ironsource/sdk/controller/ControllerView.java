package com.ironsource.sdk.controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.ironsource.sdk.controller.IronSourceWebView.State;
import com.ironsource.sdk.handlers.BackButtonHandler;
import com.ironsource.sdk.listeners.OnWebViewChangeListener;
import com.tapjoy.TapjoyConstants;

public class ControllerView extends FrameLayout implements OnWebViewChangeListener {
    private Context mContext;
    private IronSourceWebView mWebViewController;

    /* renamed from: com.ironsource.sdk.controller.ControllerView$1 */
    class C07161 implements Runnable {
        C07161() {
        }

        public void run() {
            ViewGroup decorView = ControllerView.this.getWindowDecorViewGroup();
            if (decorView != null) {
                decorView.addView(ControllerView.this);
            }
        }
    }

    /* renamed from: com.ironsource.sdk.controller.ControllerView$2 */
    class C07172 implements Runnable {
        C07172() {
        }

        public void run() {
            ViewGroup decorView = ControllerView.this.getWindowDecorViewGroup();
            if (decorView != null) {
                decorView.removeView(ControllerView.this);
            }
        }
    }

    public ControllerView(Context context) {
        super(context);
        this.mContext = context;
        setClickable(true);
    }

    public void showInterstitial(IronSourceWebView webView) {
        this.mWebViewController = webView;
        this.mWebViewController.setOnWebViewControllerChangeListener(this);
        this.mWebViewController.requestFocus();
        this.mContext = this.mWebViewController.getCurrentActivityContext();
        setPaddingByOrientation(getStatusBarPadding(), getNavigationBarPadding());
        addViewToWindow();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mWebViewController.resume();
        this.mWebViewController.viewableChange(true, ParametersKeys.MAIN);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mWebViewController.pause();
        this.mWebViewController.viewableChange(false, ParametersKeys.MAIN);
        if (this.mWebViewController != null) {
            this.mWebViewController.setState(State.Gone);
            this.mWebViewController.removeVideoEventsListener();
        }
        removeAllViews();
    }

    private void addViewToWindow() {
        this.mContext.runOnUiThread(new C07161());
    }

    private void removeViewFromWindow() {
        this.mContext.runOnUiThread(new C07172());
    }

    private ViewGroup getWindowDecorViewGroup() {
        Activity activity = this.mContext;
        if (activity != null) {
            return (ViewGroup) activity.getWindow().getDecorView();
        }
        return null;
    }

    private void setPaddingByOrientation(int statusBarHeight, int navigationBarSize) {
        try {
            if (this.mContext != null) {
                int orientation = DeviceStatus.getDeviceOrientation(this.mContext);
                if (orientation == 1) {
                    setPadding(0, statusBarHeight, 0, navigationBarSize);
                } else if (orientation == 2) {
                    setPadding(0, statusBarHeight, navigationBarSize, 0);
                }
            }
        } catch (Exception e) {
        }
    }

    private int getStatusBarPadding() {
        boolean isFullScreen;
        if ((this.mContext.getWindow().getAttributes().flags & 1024) != 0) {
            isFullScreen = true;
        } else {
            isFullScreen = false;
        }
        if (isFullScreen) {
            return 0;
        }
        int top = getStatusBarHeight();
        if (top <= 0) {
            top = 0;
        }
        return top;
    }

    private int getStatusBarHeight() {
        int result = 0;
        try {
            if (this.mContext != null) {
                int resourceId = this.mContext.getResources().getIdentifier("status_bar_height", "dimen", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
                if (resourceId > 0) {
                    result = this.mContext.getResources().getDimensionPixelSize(resourceId);
                }
            }
        } catch (Exception e) {
        }
        return result;
    }

    private int getNavigationBarPadding() {
        Activity activity = this.mContext;
        try {
            if (VERSION.SDK_INT <= 9) {
                return 0;
            }
            Rect screenRect = new Rect();
            activity.getWindow().getDecorView().getDrawingRect(screenRect);
            Rect visibleFrame = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(visibleFrame);
            if (DeviceStatus.getDeviceOrientation(activity) == 1) {
                if (screenRect.bottom - visibleFrame.bottom > 0) {
                    return screenRect.bottom - visibleFrame.bottom;
                }
                return 0;
            } else if (screenRect.right - visibleFrame.right > 0) {
                return screenRect.right - visibleFrame.right;
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    public void onCloseRequested() {
        removeViewFromWindow();
    }

    public void onOrientationChanged(String orientation, int rotation) {
    }

    public boolean onBackButtonPressed() {
        return BackButtonHandler.getInstance().handleBackButton((Activity) this.mContext);
    }
}
