package com.vungle.warren.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.facebook.ads.AudienceNetworkActivity;
import com.vungle.warren.download.APKDirectDownloadManager;
import com.vungle.warren.locale.LocaleString;
import com.vungle.warren.utility.ViewUtility;
import com.vungle.warren.utility.ViewUtility.Asset;

public class VungleWebViewActivity extends Activity {
    public static final String INTENT_URL = "intent_url";
    public static final String TAG = "VungleWebViewActivity";
    private String url;
    private WebView wvMain;

    /* renamed from: com.vungle.warren.ui.VungleWebViewActivity$1 */
    class C01681 implements OnClickListener {
        C01681() {
        }

        public void onClick(View view) {
            VungleWebViewActivity.this.finish();
        }
    }

    /* renamed from: com.vungle.warren.ui.VungleWebViewActivity$2 */
    class C01692 extends WebViewClient {
        C01692() {
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            VungleWebViewActivity.this.wvMain.loadUrl(url);
            return true;
        }
    }

    /* renamed from: com.vungle.warren.ui.VungleWebViewActivity$3 */
    class C01703 implements DownloadListener {
        C01703() {
        }

        public void onDownloadStart(String s, String s1, String s2, String s3, long l) {
            if (APKDirectDownloadManager.isApkUrl(s)) {
                Toast.makeText(VungleWebViewActivity.this, LocaleString.getLocaleText(1), 1).show();
                APKDirectDownloadManager.download(s);
                return;
            }
            Intent i = new Intent("android.intent.action.VIEW");
            i.setData(Uri.parse(s));
            VungleWebViewActivity.this.startActivity(i);
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(16777216, 16777216);
        Resources resources = getResources();
        RelativeLayout parentLayout = new RelativeLayout(this);
        LayoutParams matchParentLayoutParams = new LayoutParams(-1, -1);
        parentLayout.setLayoutParams(matchParentLayoutParams);
        int actionbarHeight = (int) TypedValue.applyDimension(1, 42.0f, resources.getDisplayMetrics());
        RelativeLayout actionBarLayout = new RelativeLayout(this);
        LayoutParams actionbarParams = new LayoutParams(-1, actionbarHeight);
        actionBarLayout.setBackgroundColor(getResources().getColor(17170446));
        actionBarLayout.setLayoutParams(actionbarParams);
        int imageViewDimension = (int) TypedValue.applyDimension(1, 24.0f, resources.getDisplayMetrics());
        int imageViewMargin = (int) TypedValue.applyDimension(1, 12.0f, resources.getDisplayMetrics());
        ImageView ivClose = new ImageView(this);
        ivClose.setImageBitmap(ViewUtility.getBitmap(Asset.close, this));
        LayoutParams ivCloseLayoutParams = new LayoutParams(imageViewDimension, imageViewDimension);
        ivCloseLayoutParams.addRule(11);
        ivCloseLayoutParams.setMargins(imageViewMargin, imageViewMargin, imageViewMargin, imageViewMargin);
        ivClose.setLayoutParams(ivCloseLayoutParams);
        actionBarLayout.addView(ivClose);
        parentLayout.addView(actionBarLayout, actionbarParams);
        this.wvMain = new WebView(this);
        this.wvMain.setLayoutParams(matchParentLayoutParams);
        LayoutParams webviewLayoutParams = new LayoutParams(-1, -1);
        webviewLayoutParams.setMargins(0, actionbarHeight, 0, 0);
        parentLayout.addView(this.wvMain, webviewLayoutParams);
        setContentView(parentLayout, matchParentLayoutParams);
        ivClose.setOnClickListener(new C01681());
        if (getIntent() != null) {
            this.url = getIntent().getStringExtra(INTENT_URL);
            configWebview();
            loadURL();
            return;
        }
        Log.e(TAG, "No url passed.");
        finish();
    }

    private void loadURL() {
        try {
            this.wvMain.loadUrl(this.url);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            finish();
        }
    }

    @TargetApi(16)
    private void configWebview() {
        WebSettings settings = this.wvMain.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(-1);
        settings.setAppCacheMaxSize(8388608);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setCacheMode(2);
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setDefaultTextEncodingName(AudienceNetworkActivity.WEBVIEW_ENCODING);
        this.wvMain.setWebViewClient(new C01692());
        this.wvMain.setDownloadListener(new C01703());
        this.wvMain.setLayerType(1, null);
    }
}
