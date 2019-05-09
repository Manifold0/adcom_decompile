// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.ui;

import android.content.DialogInterface$OnClickListener;
import android.view.View;
import android.net.Uri;
import android.content.Intent;
import android.util.Log;
import android.annotation.SuppressLint;
import android.webkit.WebSettings;
import android.os.Build$VERSION;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.vungle.warren.error.VungleException;
import android.os.Bundle;
import android.graphics.Paint;
import com.vungle.warren.DirectDownloadAdapter;
import android.content.Context;
import com.vungle.warren.AdvertisementPresenterFactory;
import com.vungle.warren.presenter.AdvertisementPresenter;
import android.os.Handler;
import com.vungle.warren.VungleNativeAd;
import android.webkit.WebView;

public class VungleNativeView extends WebView implements AdView, VungleNativeAd
{
    private static final String TAG;
    private Handler handler;
    private AdvertisementPresenter presenter;
    private final AdvertisementPresenterFactory presenterFactory;
    
    static {
        TAG = VungleNativeView.class.getName();
    }
    
    public VungleNativeView(final Context context, final String s, final DirectDownloadAdapter directDownloadAdapter, final AdvertisementPresenter.EventListener eventListener) {
        super(context);
        this.presenterFactory = new AdvertisementPresenterFactory();
        this.setLayerType(2, (Paint)null);
        this.presenter = this.presenterFactory.getAdPresenter(s, null, null);
        if (this.presenter == null) {
            if (eventListener != null) {
                eventListener.onError(new VungleException(10));
            }
            return;
        }
        this.presenter.attach(this);
        if (directDownloadAdapter != null) {
            directDownloadAdapter.getSdkDownloadClient().setAdWebView(this);
            this.presenter.setDirectDownloadAdapter(directDownloadAdapter);
        }
        this.presenter.setEventListener(eventListener);
        this.presenter.prepare(null);
        this.handler = new Handler();
        this.prepare(null);
    }
    
    @SuppressLint({ "AddJavascriptInterface", "SetJavaScriptEnabled" })
    private void prepare(final Bundle bundle) {
        this.setWebViewClient(this.presenter.getWebViewClient());
        final WebSettings settings = this.getSettings();
        settings.setBuiltInZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSaveFormData(true);
        settings.setUseWideViewPort(false);
        this.addJavascriptInterface((Object)new JavascriptBridge((JavascriptBridge.ActionHandler)this.presenter), "Android");
        this.setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
        if (Build$VERSION.SDK_INT < 17) {
            this.setAdVisibility(true);
        }
        else if (Build$VERSION.SDK_INT <= 19) {
            this.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        if (Build$VERSION.SDK_INT > 19) {
            this.setVisibility(4);
        }
    }
    
    public void close() {
        if (this.presenter.handleExit(null)) {
            this.finishDisplayingAd();
        }
    }
    
    public void finishDisplayingAd() {
        this.loadUrl("about:blank");
        this.presenter.stop(false, true);
    }
    
    public String getWebsiteUrl() {
        return this.getUrl();
    }
    
    public void onPause() {
        super.onPause();
        this.presenter.stop(false, false);
    }
    
    public void onResume() {
        super.onResume();
        Log.d(VungleNativeView.TAG, "Resuming Flex");
        this.presenter.play();
    }
    
    public void open(final String s) {
        Log.v(VungleNativeView.TAG, "Opening " + s);
        try {
            final Intent uri = Intent.parseUri(s, 0);
            uri.addFlags(268435456);
            this.getContext().startActivity(uri);
        }
        catch (Exception ex) {
            Log.e(VungleNativeView.TAG, "Cannot open url " + ex.getLocalizedMessage());
        }
    }
    
    public void playVideo(final Uri uri, final boolean b) {
        throw new UnsupportedOperationException("VungleNativeView does not implement a video player.");
    }
    
    public View renderNativeView() {
        return (View)this;
    }
    
    public void setAdVisibility(final boolean adVisibility) {
        this.presenter.setAdVisibility(adVisibility);
    }
    
    public void setOrientation(final int n) {
    }
    
    public void setVisibility(final boolean b) {
        int visibility;
        if (b) {
            visibility = 0;
        }
        else {
            visibility = 4;
        }
        this.setVisibility(visibility);
    }
    
    public void showCTAOverlay(final boolean b, final boolean b2, final int n) {
    }
    
    public void showCloseButton() {
        throw new UnsupportedOperationException("VungleNativeView does not implement a close button");
    }
    
    public void showDialog(final String s, final String s2, final String s3, final String s4, final DialogInterface$OnClickListener dialogInterface$OnClickListener) {
        throw new UnsupportedOperationException("VungleNativeView does not implement a dialog.");
    }
    
    public void showWebsite(final String s) {
        this.loadUrl(s);
    }
    
    public void updateWindow(final boolean b) {
    }
}
