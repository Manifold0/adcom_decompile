// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.controller;

import java.util.Iterator;
import java.util.List;
import android.content.Intent;
import android.net.Uri;
import com.ironsource.sdk.utils.IronSourceSharedPrefHelper;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.View$OnSystemUiVisibilityChangeListener;
import com.ironsource.sdk.agent.IronSourceAdsPublisherAgent;
import android.os.Bundle;
import com.ironsource.sdk.utils.IronSourceAsyncHttpRequestTask;
import com.ironsource.sdk.utils.Logger;
import android.view.ViewGroup;
import android.webkit.WebViewClient;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.os.Build$VERSION;
import com.ironsource.sdk.utils.SDKUtils;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.os.Handler;
import android.widget.ProgressBar;
import android.app.Activity;

public class OpenUrlActivity extends Activity
{
    private static final int PROGRESS_BAR_VIEW_ID;
    private static final String TAG = "OpenUrlActivity";
    private static final int WEB_VIEW_VIEW_ID;
    private final Runnable decorViewSettings;
    boolean isSecondaryWebview;
    private boolean mIsImmersive;
    private ProgressBar mProgressBar;
    private Handler mUiThreadHandler;
    private String mUrl;
    private IronSourceWebView mWebViewController;
    private RelativeLayout mainLayout;
    private WebView webView;
    
    static {
        WEB_VIEW_VIEW_ID = SDKUtils.generateViewId();
        PROGRESS_BAR_VIEW_ID = SDKUtils.generateViewId();
    }
    
    public OpenUrlActivity() {
        this.webView = null;
        this.mUiThreadHandler = new Handler();
        this.mIsImmersive = false;
        this.decorViewSettings = new Runnable() {
            @Override
            public void run() {
                OpenUrlActivity.this.getWindow().getDecorView().setSystemUiVisibility(SDKUtils.getActivityUIFlags(OpenUrlActivity.this.mIsImmersive));
            }
        };
    }
    
    private void createProgressBarForWebView() {
        if (this.mProgressBar == null) {
            if (Build$VERSION.SDK_INT >= 11) {
                this.mProgressBar = new ProgressBar((Context)new ContextThemeWrapper((Context)this, 16973939));
            }
            else {
                this.mProgressBar = new ProgressBar((Context)this);
            }
            this.mProgressBar.setId(OpenUrlActivity.PROGRESS_BAR_VIEW_ID);
        }
        if (this.findViewById(OpenUrlActivity.PROGRESS_BAR_VIEW_ID) == null) {
            final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-2, -2);
            layoutParams.addRule(13);
            this.mProgressBar.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            this.mProgressBar.setVisibility(4);
            this.mainLayout.addView((View)this.mProgressBar);
        }
    }
    
    private void createWebView() {
        if (this.webView == null) {
            (this.webView = new WebView(this.getApplicationContext())).setId(OpenUrlActivity.WEB_VIEW_VIEW_ID);
            this.webView.getSettings().setJavaScriptEnabled(true);
            this.webView.setWebViewClient((WebViewClient)new Client());
            this.loadUrl(this.mUrl);
        }
        if (this.findViewById(OpenUrlActivity.WEB_VIEW_VIEW_ID) == null) {
            this.mainLayout.addView((View)this.webView, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
        }
        this.createProgressBarForWebView();
        if (this.mWebViewController != null) {
            this.mWebViewController.viewableChange(true, "secondary");
        }
    }
    
    private void destroyWebView() {
        if (this.webView != null) {
            this.webView.destroy();
        }
    }
    
    private void disableTouch() {
        this.getWindow().addFlags(16);
    }
    
    private void hideActivityTitle() {
        this.requestWindowFeature(1);
    }
    
    private void hideActivtiyStatusBar() {
        this.getWindow().setFlags(1024, 1024);
    }
    
    private void removeWebViewFromLayout() {
        if (this.mWebViewController != null) {
            this.mWebViewController.viewableChange(false, "secondary");
            if (this.mainLayout != null) {
                final ViewGroup viewGroup = (ViewGroup)this.webView.getParent();
                if (viewGroup != null) {
                    if (viewGroup.findViewById(OpenUrlActivity.WEB_VIEW_VIEW_ID) != null) {
                        viewGroup.removeView((View)this.webView);
                    }
                    if (viewGroup.findViewById(OpenUrlActivity.PROGRESS_BAR_VIEW_ID) != null) {
                        viewGroup.removeView((View)this.mProgressBar);
                    }
                }
            }
        }
    }
    
    public void finish() {
        if (this.isSecondaryWebview) {
            this.mWebViewController.engageEnd("secondaryClose");
        }
        super.finish();
    }
    
    public void loadUrl(final String s) {
        this.webView.stopLoading();
        this.webView.clearHistory();
        try {
            this.webView.loadUrl(s);
        }
        catch (Throwable t) {
            Logger.e("OpenUrlActivity", "OpenUrlActivity:: loadUrl: " + t.toString());
            new IronSourceAsyncHttpRequestTask().execute((Object[])new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + t.getStackTrace()[0].getMethodName() });
        }
    }
    
    public void onBackPressed() {
        if (this.webView.canGoBack()) {
            this.webView.goBack();
            return;
        }
        super.onBackPressed();
    }
    
    protected void onCreate(Bundle extras) {
        super.onCreate(extras);
        Logger.i("OpenUrlActivity", "onCreate()");
        try {
            this.mWebViewController = IronSourceAdsPublisherAgent.getInstance(this).getWebViewController();
            this.hideActivityTitle();
            this.hideActivtiyStatusBar();
            extras = this.getIntent().getExtras();
            this.mUrl = extras.getString(IronSourceWebView.EXTERNAL_URL);
            this.isSecondaryWebview = extras.getBoolean(IronSourceWebView.SECONDARY_WEB_VIEW);
            this.mIsImmersive = this.getIntent().getBooleanExtra("immersive", false);
            if (this.mIsImmersive) {
                this.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener((View$OnSystemUiVisibilityChangeListener)new View$OnSystemUiVisibilityChangeListener() {
                    public void onSystemUiVisibilityChange(final int n) {
                        if ((n & 0x1002) == 0x0) {
                            OpenUrlActivity.this.mUiThreadHandler.removeCallbacks(OpenUrlActivity.this.decorViewSettings);
                            OpenUrlActivity.this.mUiThreadHandler.postDelayed(OpenUrlActivity.this.decorViewSettings, 500L);
                        }
                    }
                });
                this.runOnUiThread(this.decorViewSettings);
            }
            this.setContentView((View)(this.mainLayout = new RelativeLayout((Context)this)), new ViewGroup$LayoutParams(-1, -1));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.finish();
        }
    }
    
    protected void onDestroy() {
        super.onDestroy();
        this.destroyWebView();
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (this.mIsImmersive && (n == 25 || n == 24)) {
            this.mUiThreadHandler.postDelayed(this.decorViewSettings, 500L);
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    protected void onPause() {
        super.onPause();
        this.removeWebViewFromLayout();
    }
    
    protected void onResume() {
        super.onResume();
        this.createWebView();
    }
    
    public void onWindowFocusChanged(final boolean b) {
        super.onWindowFocusChanged(b);
        if (this.mIsImmersive && b) {
            this.runOnUiThread(this.decorViewSettings);
        }
    }
    
    private class Client extends WebViewClient
    {
        public void onPageFinished(final WebView webView, final String s) {
            super.onPageFinished(webView, s);
            OpenUrlActivity.this.mProgressBar.setVisibility(4);
        }
        
        public void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);
            OpenUrlActivity.this.mProgressBar.setVisibility(0);
        }
        
        public void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
            super.onReceivedError(webView, n, s, s2);
        }
        
        public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
            final List<String> searchKeys = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getSearchKeys();
            if (searchKeys != null && !searchKeys.isEmpty()) {
                final Iterator<String> iterator = searchKeys.iterator();
                while (iterator.hasNext()) {
                    if (s.contains(iterator.next())) {
                        OpenUrlActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)));
                        OpenUrlActivity.this.mWebViewController.interceptedUrlToStore();
                        OpenUrlActivity.this.finish();
                        return true;
                    }
                }
            }
            return super.shouldOverrideUrlLoading(webView, s);
        }
    }
}
