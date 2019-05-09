// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.controller;

import android.os.Parcelable;
import android.media.AudioManager$OnAudioFocusChangeListener;
import android.media.AudioManager;
import android.view.KeyEvent;
import android.view.ViewGroup$LayoutParams;
import com.ironsource.sdk.data.SSAEnums;
import android.text.TextUtils;
import android.view.View$OnSystemUiVisibilityChangeListener;
import com.ironsource.sdk.agent.IronSourceAdsPublisherAgent;
import android.os.Bundle;
import com.ironsource.sdk.handlers.BackButtonHandler;
import com.ironsource.sdk.utils.Logger;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.content.Context;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.sdk.utils.SDKUtils;
import android.widget.FrameLayout;
import android.os.Handler;
import com.ironsource.sdk.data.AdUnitsState;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout$LayoutParams;
import com.ironsource.sdk.listeners.OnWebViewChangeListener;
import android.app.Activity;

public class ControllerActivity extends Activity implements OnWebViewChangeListener, VideoEventsListener
{
    private static final String TAG;
    private static final int WEB_VIEW_VIEW_ID = 1;
    final RelativeLayout$LayoutParams MATCH_PARENT_LAYOUT_PARAMS;
    private boolean calledFromOnCreate;
    public int currentRequestedRotation;
    private final Runnable decorViewSettings;
    private RelativeLayout mContainer;
    private boolean mIsImmersive;
    private String mProductType;
    private AdUnitsState mState;
    private Handler mUiThreadHandler;
    private IronSourceWebView mWebViewController;
    private FrameLayout mWebViewFrameContainer;
    
    static {
        TAG = ControllerActivity.class.getSimpleName();
    }
    
    public ControllerActivity() {
        this.currentRequestedRotation = -1;
        this.mIsImmersive = false;
        this.mUiThreadHandler = new Handler();
        this.decorViewSettings = new Runnable() {
            @Override
            public void run() {
                ControllerActivity.this.getWindow().getDecorView().setSystemUiVisibility(SDKUtils.getActivityUIFlags(ControllerActivity.this.mIsImmersive));
            }
        };
        this.MATCH_PARENT_LAYOUT_PARAMS = new RelativeLayout$LayoutParams(-1, -1);
        this.calledFromOnCreate = false;
    }
    
    private void cancelScreenOn() {
        this.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                ControllerActivity.this.getWindow().clearFlags(128);
            }
        });
    }
    
    private void handleOrientationState(final String s, final int n) {
        if (s != null) {
            if ("landscape".equalsIgnoreCase(s)) {
                this.setInitiateLandscapeOrientation();
            }
            else {
                if ("portrait".equalsIgnoreCase(s)) {
                    this.setInitiatePortraitOrientation();
                    return;
                }
                if ("device".equalsIgnoreCase(s)) {
                    if (DeviceStatus.isDeviceOrientationLocked((Context)this)) {
                        this.setRequestedOrientation(1);
                    }
                }
                else if (this.getRequestedOrientation() == -1) {
                    this.setRequestedOrientation(4);
                }
            }
        }
    }
    
    private void hideActivityTitle() {
        this.requestWindowFeature(1);
    }
    
    private void hideActivtiyStatusBar() {
        this.getWindow().setFlags(1024, 1024);
    }
    
    private void initOrientationState() {
        final Intent intent = this.getIntent();
        this.handleOrientationState(intent.getStringExtra("orientation_set_flag"), intent.getIntExtra("rotation_set_flag", 0));
    }
    
    private void keepScreenOn() {
        this.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                ControllerActivity.this.getWindow().addFlags(128);
            }
        });
    }
    
    private void removeWebViewContainerView() {
        if (this.mContainer != null) {
            final ViewGroup viewGroup = (ViewGroup)this.mWebViewFrameContainer.getParent();
            if (viewGroup.findViewById(1) != null) {
                viewGroup.removeView((View)this.mWebViewFrameContainer);
            }
        }
    }
    
    private void setInitiateLandscapeOrientation() {
        final int applicationRotation = DeviceStatus.getApplicationRotation((Context)this);
        Logger.i(ControllerActivity.TAG, "setInitiateLandscapeOrientation");
        if (applicationRotation == 0) {
            Logger.i(ControllerActivity.TAG, "ROTATION_0");
            this.setRequestedOrientation(0);
            return;
        }
        if (applicationRotation == 2) {
            Logger.i(ControllerActivity.TAG, "ROTATION_180");
            this.setRequestedOrientation(8);
            return;
        }
        if (applicationRotation == 3) {
            Logger.i(ControllerActivity.TAG, "ROTATION_270 Right Landscape");
            this.setRequestedOrientation(8);
            return;
        }
        if (applicationRotation == 1) {
            Logger.i(ControllerActivity.TAG, "ROTATION_90 Left Landscape");
            this.setRequestedOrientation(0);
            return;
        }
        Logger.i(ControllerActivity.TAG, "No Rotation");
    }
    
    private void setInitiatePortraitOrientation() {
        final int applicationRotation = DeviceStatus.getApplicationRotation((Context)this);
        Logger.i(ControllerActivity.TAG, "setInitiatePortraitOrientation");
        if (applicationRotation == 0) {
            Logger.i(ControllerActivity.TAG, "ROTATION_0");
            this.setRequestedOrientation(1);
            return;
        }
        if (applicationRotation == 2) {
            Logger.i(ControllerActivity.TAG, "ROTATION_180");
            this.setRequestedOrientation(9);
            return;
        }
        if (applicationRotation == 1) {
            Logger.i(ControllerActivity.TAG, "ROTATION_270 Right Landscape");
            this.setRequestedOrientation(1);
            return;
        }
        if (applicationRotation == 3) {
            Logger.i(ControllerActivity.TAG, "ROTATION_90 Left Landscape");
            this.setRequestedOrientation(1);
            return;
        }
        Logger.i(ControllerActivity.TAG, "No Rotation");
    }
    
    public boolean onBackButtonPressed() {
        this.onBackPressed();
        return true;
    }
    
    public void onBackPressed() {
        Logger.i(ControllerActivity.TAG, "onBackPressed");
        if (!BackButtonHandler.getInstance().handleBackButton(this)) {
            super.onBackPressed();
        }
    }
    
    public void onCloseRequested() {
        this.finish();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        try {
            Logger.i(ControllerActivity.TAG, "onCreate");
            this.hideActivityTitle();
            this.hideActivtiyStatusBar();
            (this.mWebViewController = IronSourceAdsPublisherAgent.getInstance(this).getWebViewController()).setId(1);
            this.mWebViewController.setOnWebViewControllerChangeListener(this);
            this.mWebViewController.setVideoEventsListener(this);
            final Intent intent = this.getIntent();
            this.mProductType = intent.getStringExtra("productType");
            this.mIsImmersive = intent.getBooleanExtra("immersive", false);
            if (this.mIsImmersive) {
                this.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener((View$OnSystemUiVisibilityChangeListener)new View$OnSystemUiVisibilityChangeListener() {
                    public void onSystemUiVisibilityChange(final int n) {
                        if ((n & 0x1002) == 0x0) {
                            ControllerActivity.this.mUiThreadHandler.removeCallbacks(ControllerActivity.this.decorViewSettings);
                            ControllerActivity.this.mUiThreadHandler.postDelayed(ControllerActivity.this.decorViewSettings, 500L);
                        }
                    }
                });
                this.runOnUiThread(this.decorViewSettings);
            }
            if (!TextUtils.isEmpty((CharSequence)this.mProductType) && SSAEnums.ProductType.OfferWall.toString().equalsIgnoreCase(this.mProductType)) {
                if (bundle != null) {
                    final AdUnitsState mState = (AdUnitsState)bundle.getParcelable("state");
                    if (mState != null) {
                        this.mState = mState;
                        this.mWebViewController.restoreState(mState);
                    }
                    this.finish();
                }
                else {
                    this.mState = this.mWebViewController.getSavedState();
                }
            }
            this.setContentView((View)(this.mContainer = new RelativeLayout((Context)this)), (ViewGroup$LayoutParams)this.MATCH_PARENT_LAYOUT_PARAMS);
            this.mWebViewFrameContainer = this.mWebViewController.getLayout();
            if (this.mContainer.findViewById(1) == null && this.mWebViewFrameContainer.getParent() != null) {
                this.calledFromOnCreate = true;
                this.finish();
            }
            this.initOrientationState();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.finish();
        }
    }
    
    protected void onDestroy() {
        super.onDestroy();
        Logger.i(ControllerActivity.TAG, "onDestroy");
        if (this.calledFromOnCreate) {
            this.removeWebViewContainerView();
        }
        if (this.mWebViewController != null) {
            this.mWebViewController.setState(IronSourceWebView.State.Gone);
            this.mWebViewController.removeVideoEventsListener();
            this.mWebViewController.notifyLifeCycle(this.mProductType, "onDestroy");
        }
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4 && this.mWebViewController.inCustomView()) {
            this.mWebViewController.hideCustomView();
            return true;
        }
        if (this.mIsImmersive && (n == 25 || n == 24)) {
            this.mUiThreadHandler.removeCallbacks(this.decorViewSettings);
            this.mUiThreadHandler.postDelayed(this.decorViewSettings, 500L);
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    public void onOrientationChanged(final String s, final int n) {
        this.handleOrientationState(s, n);
    }
    
    protected void onPause() {
        super.onPause();
        Logger.i(ControllerActivity.TAG, "onPause");
        ((AudioManager)this.getSystemService("audio")).abandonAudioFocus((AudioManager$OnAudioFocusChangeListener)null);
        if (this.mWebViewController != null) {
            this.mWebViewController.unregisterConnectionReceiver((Context)this);
            this.mWebViewController.pause();
            this.mWebViewController.viewableChange(false, "main");
        }
        this.removeWebViewContainerView();
    }
    
    protected void onResume() {
        super.onResume();
        Logger.i(ControllerActivity.TAG, "onResume");
        this.mContainer.addView((View)this.mWebViewFrameContainer, (ViewGroup$LayoutParams)this.MATCH_PARENT_LAYOUT_PARAMS);
        if (this.mWebViewController != null) {
            this.mWebViewController.registerConnectionReceiver((Context)this);
            this.mWebViewController.resume();
            this.mWebViewController.viewableChange(true, "main");
        }
        ((AudioManager)this.getSystemService("audio")).requestAudioFocus((AudioManager$OnAudioFocusChangeListener)null, 3, 2);
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (!TextUtils.isEmpty((CharSequence)this.mProductType) && SSAEnums.ProductType.OfferWall.toString().equalsIgnoreCase(this.mProductType)) {
            this.mState.setShouldRestore(true);
            bundle.putParcelable("state", (Parcelable)this.mState);
        }
    }
    
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Logger.i(ControllerActivity.TAG, "onUserLeaveHint");
    }
    
    public void onVideoEnded() {
        this.toggleKeepScreen(false);
    }
    
    public void onVideoPaused() {
        this.toggleKeepScreen(false);
    }
    
    public void onVideoResumed() {
        this.toggleKeepScreen(true);
    }
    
    public void onVideoStarted() {
        this.toggleKeepScreen(true);
    }
    
    public void onVideoStopped() {
        this.toggleKeepScreen(false);
    }
    
    public void onWindowFocusChanged(final boolean b) {
        super.onWindowFocusChanged(b);
        if (this.mIsImmersive && b) {
            this.runOnUiThread(this.decorViewSettings);
        }
    }
    
    public void setRequestedOrientation(final int currentRequestedRotation) {
        if (this.currentRequestedRotation != currentRequestedRotation) {
            Logger.i(ControllerActivity.TAG, "Rotation: Req = " + currentRequestedRotation + " Curr = " + this.currentRequestedRotation);
            super.setRequestedOrientation(this.currentRequestedRotation = currentRequestedRotation);
        }
    }
    
    public void toggleKeepScreen(final boolean b) {
        if (b) {
            this.keepScreenOn();
            return;
        }
        this.cancelScreenOn();
    }
}
