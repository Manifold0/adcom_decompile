package com.vungle.warren.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.gms.drive.DriveFile;
import com.ironsource.sdk.constants.Constants;
import com.vungle.warren.AdvertisementPresenterFactory;
import com.vungle.warren.DirectDownloadAdapter;
import com.vungle.warren.VungleNativeAd;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.presenter.AdvertisementPresenter;
import com.vungle.warren.presenter.AdvertisementPresenter.EventListener;

public class VungleNativeView extends WebView implements AdView, VungleNativeAd {
    private static final String TAG = VungleNativeView.class.getName();
    private Handler handler;
    private AdvertisementPresenter presenter;
    private final AdvertisementPresenterFactory presenterFactory = new AdvertisementPresenterFactory();

    public void onResume() {
        super.onResume();
        Log.d(TAG, "Resuming Flex");
        this.presenter.play();
    }

    public void onPause() {
        super.onPause();
        this.presenter.stop(false, false);
    }

    public VungleNativeView(Context context, String placementID, DirectDownloadAdapter directDownloadAdapter, EventListener listener) {
        super(context);
        setLayerType(2, null);
        this.presenter = this.presenterFactory.getAdPresenter(placementID, null, null);
        if (this.presenter != null) {
            this.presenter.attach(this);
            if (directDownloadAdapter != null) {
                directDownloadAdapter.getSdkDownloadClient().setAdWebView(this);
                this.presenter.setDirectDownloadAdapter(directDownloadAdapter);
            }
            this.presenter.setEventListener(listener);
            this.presenter.prepare(null);
            this.handler = new Handler();
            prepare(null);
        } else if (listener != null) {
            listener.onError(new VungleException(10));
        }
    }

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    private void prepare(Bundle savedInstanceState) {
        setWebViewClient(this.presenter.getWebViewClient());
        WebSettings webSettings = getSettings();
        webSettings.setBuiltInZoomControls(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSaveFormData(true);
        webSettings.setUseWideViewPort(false);
        addJavascriptInterface(new JavascriptBridge(this.presenter), Constants.JAVASCRIPT_INTERFACE_NAME);
        setLayoutParams(new LayoutParams(-1, -1));
        if (VERSION.SDK_INT < 17) {
            setAdVisibility(true);
        } else if (VERSION.SDK_INT <= 19) {
            getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        if (VERSION.SDK_INT > 19) {
            setVisibility(4);
        }
    }

    public void finishDisplayingAd() {
        loadUrl("about:blank");
        this.presenter.stop(false, true);
    }

    public View renderNativeView() {
        return this;
    }

    public void setAdVisibility(boolean isVisible) {
        this.presenter.setAdVisibility(isVisible);
    }

    public void setOrientation(int orientation) {
    }

    public void playVideo(Uri uri, boolean startMuted) {
        throw new UnsupportedOperationException("VungleNativeView does not implement a video player.");
    }

    public void showWebsite(String url) {
        loadUrl(url);
    }

    public String getWebsiteUrl() {
        return getUrl();
    }

    public void close() {
        if (this.presenter.handleExit(null)) {
            finishDisplayingAd();
        }
    }

    public void showCloseButton() {
        throw new UnsupportedOperationException("VungleNativeView does not implement a close button");
    }

    public void showCTAOverlay(boolean clickEnabled, boolean enabled, int size) {
    }

    public void open(String url) {
        Log.v(TAG, "Opening " + url);
        try {
            Intent i = Intent.parseUri(url, 0);
            i.addFlags(DriveFile.MODE_READ_ONLY);
            getContext().startActivity(i);
        } catch (Exception ex) {
            Log.e(TAG, "Cannot open url " + ex.getLocalizedMessage());
        }
    }

    public void showDialog(String title, String body, String resume, String close, OnClickListener responseListener) {
        throw new UnsupportedOperationException("VungleNativeView does not implement a dialog.");
    }

    public void updateWindow(boolean isFlexView) {
    }

    public void setVisibility(boolean isVisible) {
        setVisibility(isVisible ? 0 : 4);
    }
}
