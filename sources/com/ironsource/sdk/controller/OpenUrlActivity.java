package com.ironsource.sdk.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.InputDeviceCompat;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.ironsource.sdk.agent.IronSourceAdsPublisherAgent;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.ironsource.sdk.utils.IronSourceAsyncHttpRequestTask;
import com.ironsource.sdk.utils.IronSourceSharedPrefHelper;
import com.ironsource.sdk.utils.Logger;
import com.ironsource.sdk.utils.SDKUtils;
import java.util.List;

public class OpenUrlActivity extends Activity {
    private static final int PROGRESS_BAR_VIEW_ID = SDKUtils.generateViewId();
    private static final String TAG = "OpenUrlActivity";
    private static final int WEB_VIEW_VIEW_ID = SDKUtils.generateViewId();
    private final Runnable decorViewSettings = new C07382();
    boolean isSecondaryWebview;
    private boolean mIsImmersive = false;
    private ProgressBar mProgressBar;
    private Handler mUiThreadHandler = new Handler();
    private String mUrl;
    private IronSourceWebView mWebViewController;
    private RelativeLayout mainLayout;
    private WebView webView = null;

    /* renamed from: com.ironsource.sdk.controller.OpenUrlActivity$1 */
    class C07371 implements OnSystemUiVisibilityChangeListener {
        C07371() {
        }

        public void onSystemUiVisibilityChange(int visibility) {
            if ((visibility & InputDeviceCompat.SOURCE_TOUCHSCREEN) == 0) {
                OpenUrlActivity.this.mUiThreadHandler.removeCallbacks(OpenUrlActivity.this.decorViewSettings);
                OpenUrlActivity.this.mUiThreadHandler.postDelayed(OpenUrlActivity.this.decorViewSettings, 500);
            }
        }
    }

    /* renamed from: com.ironsource.sdk.controller.OpenUrlActivity$2 */
    class C07382 implements Runnable {
        C07382() {
        }

        public void run() {
            OpenUrlActivity.this.getWindow().getDecorView().setSystemUiVisibility(SDKUtils.getActivityUIFlags(OpenUrlActivity.this.mIsImmersive));
        }
    }

    private class Client extends WebViewClient {
        private Client() {
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            OpenUrlActivity.this.mProgressBar.setVisibility(0);
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            OpenUrlActivity.this.mProgressBar.setVisibility(4);
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            List<String> searchKeys = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getSearchKeys();
            if (!(searchKeys == null || searchKeys.isEmpty())) {
                for (String key : searchKeys) {
                    if (url.contains(key)) {
                        OpenUrlActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                        OpenUrlActivity.this.mWebViewController.interceptedUrlToStore();
                        OpenUrlActivity.this.finish();
                        return true;
                    }
                }
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.m1212i(TAG, "onCreate()");
        try {
            this.mWebViewController = IronSourceAdsPublisherAgent.getInstance(this).getWebViewController();
            hideActivityTitle();
            hideActivtiyStatusBar();
            Bundle bundle = getIntent().getExtras();
            this.mUrl = bundle.getString(IronSourceWebView.EXTERNAL_URL);
            this.isSecondaryWebview = bundle.getBoolean(IronSourceWebView.SECONDARY_WEB_VIEW);
            this.mIsImmersive = getIntent().getBooleanExtra(ParametersKeys.IMMERSIVE, false);
            if (this.mIsImmersive) {
                getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new C07371());
                runOnUiThread(this.decorViewSettings);
            }
            this.mainLayout = new RelativeLayout(this);
            setContentView(this.mainLayout, new LayoutParams(-1, -1));
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }

    protected void onResume() {
        super.onResume();
        createWebView();
    }

    private void createWebView() {
        if (this.webView == null) {
            this.webView = new WebView(getApplicationContext());
            this.webView.setId(WEB_VIEW_VIEW_ID);
            this.webView.getSettings().setJavaScriptEnabled(true);
            this.webView.setWebViewClient(new Client());
            loadUrl(this.mUrl);
        }
        if (findViewById(WEB_VIEW_VIEW_ID) == null) {
            this.mainLayout.addView(this.webView, new RelativeLayout.LayoutParams(-1, -1));
        }
        createProgressBarForWebView();
        if (this.mWebViewController != null) {
            this.mWebViewController.viewableChange(true, ParametersKeys.SECONDARY);
        }
    }

    private void createProgressBarForWebView() {
        if (this.mProgressBar == null) {
            if (VERSION.SDK_INT >= 11) {
                this.mProgressBar = new ProgressBar(new ContextThemeWrapper(this, 16973939));
            } else {
                this.mProgressBar = new ProgressBar(this);
            }
            this.mProgressBar.setId(PROGRESS_BAR_VIEW_ID);
        }
        if (findViewById(PROGRESS_BAR_VIEW_ID) == null) {
            RelativeLayout.LayoutParams progressBarLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
            progressBarLayoutParams.addRule(13);
            this.mProgressBar.setLayoutParams(progressBarLayoutParams);
            this.mProgressBar.setVisibility(4);
            this.mainLayout.addView(this.mProgressBar);
        }
    }

    private void removeWebViewFromLayout() {
        if (this.mWebViewController != null) {
            this.mWebViewController.viewableChange(false, ParametersKeys.SECONDARY);
            if (this.mainLayout != null) {
                ViewGroup parent = (ViewGroup) this.webView.getParent();
                if (parent != null) {
                    if (parent.findViewById(WEB_VIEW_VIEW_ID) != null) {
                        parent.removeView(this.webView);
                    }
                    if (parent.findViewById(PROGRESS_BAR_VIEW_ID) != null) {
                        parent.removeView(this.mProgressBar);
                    }
                }
            }
        }
    }

    private void destroyWebView() {
        if (this.webView != null) {
            this.webView.destroy();
        }
    }

    protected void onPause() {
        super.onPause();
        removeWebViewFromLayout();
    }

    public void loadUrl(String url) {
        this.webView.stopLoading();
        this.webView.clearHistory();
        try {
            this.webView.loadUrl(url);
        } catch (Throwable e) {
            Logger.m1210e(TAG, "OpenUrlActivity:: loadUrl: " + e.toString());
            new IronSourceAsyncHttpRequestTask().execute(new String[]{Constants.NATIVE_EXCEPTION_BASE_URL + e.getStackTrace()[0].getMethodName()});
        }
    }

    private void hideActivityTitle() {
        requestWindowFeature(1);
    }

    private void hideActivtiyStatusBar() {
        getWindow().setFlags(1024, 1024);
    }

    private void disableTouch() {
        getWindow().addFlags(16);
    }

    public void onBackPressed() {
        if (this.webView.canGoBack()) {
            this.webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        destroyWebView();
    }

    public void finish() {
        if (this.isSecondaryWebview) {
            this.mWebViewController.engageEnd(ParametersKeys.SECONDARY_CLOSE);
        }
        super.finish();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (this.mIsImmersive && hasFocus) {
            runOnUiThread(this.decorViewSettings);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (this.mIsImmersive && (keyCode == 25 || keyCode == 24)) {
            this.mUiThreadHandler.postDelayed(this.decorViewSettings, 500);
        }
        return super.onKeyDown(keyCode, event);
    }
}
