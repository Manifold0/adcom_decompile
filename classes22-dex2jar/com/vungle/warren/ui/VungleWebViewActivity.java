// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.ui;

import android.content.res.Resources;
import android.view.View$OnClickListener;
import android.view.View;
import com.vungle.warren.utility.ViewUtility;
import android.widget.ImageView;
import android.util.TypedValue;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.RelativeLayout;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.annotation.TargetApi;
import android.webkit.WebSettings;
import android.graphics.Paint;
import android.net.Uri;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;
import com.vungle.warren.locale.LocaleString;
import com.vungle.warren.download.APKDirectDownloadManager;
import android.webkit.DownloadListener;
import android.webkit.WebViewClient;
import android.webkit.WebSettings$RenderPriority;
import android.webkit.WebView;
import android.app.Activity;

public class VungleWebViewActivity extends Activity
{
    public static final String INTENT_URL = "intent_url";
    public static final String TAG = "VungleWebViewActivity";
    private String url;
    private WebView wvMain;
    
    @TargetApi(16)
    private void configWebview() {
        final WebSettings settings = this.wvMain.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(-1);
        settings.setAppCacheMaxSize(8388608L);
        settings.setRenderPriority(WebSettings$RenderPriority.HIGH);
        settings.setCacheMode(2);
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setDefaultTextEncodingName("utf-8");
        this.wvMain.setWebViewClient((WebViewClient)new WebViewClient() {
            public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
                VungleWebViewActivity.this.wvMain.loadUrl(s);
                return true;
            }
        });
        this.wvMain.setDownloadListener((DownloadListener)new DownloadListener() {
            public void onDownloadStart(final String s, final String s2, final String s3, final String s4, final long n) {
                if (APKDirectDownloadManager.isApkUrl(s)) {
                    Toast.makeText((Context)VungleWebViewActivity.this, (CharSequence)LocaleString.getLocaleText(1), 1).show();
                    APKDirectDownloadManager.download(s);
                    return;
                }
                final Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(s));
                VungleWebViewActivity.this.startActivity(intent);
            }
        });
        this.wvMain.setLayerType(1, (Paint)null);
    }
    
    private void loadURL() {
        try {
            this.wvMain.loadUrl(this.url);
        }
        catch (Exception ex) {
            Log.e("VungleWebViewActivity", ex.getMessage());
            this.finish();
        }
    }
    
    protected void onCreate(@Nullable final Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(1);
        this.getWindow().setFlags(16777216, 16777216);
        final Resources resources = this.getResources();
        final RelativeLayout relativeLayout = new RelativeLayout((Context)this);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-1, -1);
        relativeLayout.setLayoutParams((ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        final int n = (int)TypedValue.applyDimension(1, 42.0f, resources.getDisplayMetrics());
        final RelativeLayout relativeLayout2 = new RelativeLayout((Context)this);
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, n);
        relativeLayout2.setBackgroundColor(this.getResources().getColor(17170446));
        relativeLayout2.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        final int n2 = (int)TypedValue.applyDimension(1, 24.0f, resources.getDisplayMetrics());
        final int n3 = (int)TypedValue.applyDimension(1, 12.0f, resources.getDisplayMetrics());
        final ImageView imageView = new ImageView((Context)this);
        imageView.setImageBitmap(ViewUtility.getBitmap(ViewUtility.Asset.close, (Context)this));
        final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams(n2, n2);
        layoutParams2.addRule(11);
        layoutParams2.setMargins(n3, n3, n3, n3);
        imageView.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
        relativeLayout2.addView((View)imageView);
        relativeLayout.addView((View)relativeLayout2, (ViewGroup$LayoutParams)layoutParams);
        (this.wvMain = new WebView((Context)this)).setLayoutParams((ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(-1, -1);
        relativeLayout$LayoutParams2.setMargins(0, n, 0, 0);
        relativeLayout.addView((View)this.wvMain, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
        this.setContentView((View)relativeLayout, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        imageView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                VungleWebViewActivity.this.finish();
            }
        });
        if (this.getIntent() != null) {
            this.url = this.getIntent().getStringExtra("intent_url");
            this.configWebview();
            this.loadURL();
            return;
        }
        Log.e("VungleWebViewActivity", "No url passed.");
        this.finish();
    }
}
